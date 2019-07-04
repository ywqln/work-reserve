package com.qln.workreserve.controller;

import com.alibaba.fastjson.JSONObject;
import com.qln.workreserve.dbo.YwqDicNode;
import com.qln.workreserve.dbo.YwqDictionary;
import com.qln.workreserve.repository.YwqDicNodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/7/4
 */
@Component
public class YwqController extends BaseController {
    @Autowired
    private YwqDicNodeRepository ywqDicNodeRepository;

    @Override
    public void workFlow() {
        treeNode();
    }

    private void treeNode() {
        // A23.2.2 A23.2.2.5
        // A23 A23.2 A23.2.2
        // A23 A23/2 A23/2/2
        // [A] [A23] [A23/2] [A23/2/5]
        // 从数据库中读取到源数据
        List<YwqDicNode> dataSource = ywqDicNodeRepository.findAll();

        // 最终要保存的对象
        List<YwqDictionary> ywqDictionaries = selectRoot(dataSource);
        // 找子节点
        tree(dataSource, ywqDictionaries);

    }

    private void tree(List<YwqDicNode> dataSource, List<YwqDictionary> ywqDictionaries) {
        List<YwqDictionary> loopDic = copyList(ywqDictionaries, YwqDictionary.class);
        List<YwqDictionary> temp = new ArrayList<>();

        while (loopDic.size() > 0) {
            for (YwqDictionary ywqDictionary : loopDic) {
                // 得到当前节点的code
                String parentId = ywqDictionary.getDicId();
                String parentCode = ywqDictionary.getCode();

                // 找到所有的子节点
                List<YwqDictionary> childes = findChildes(dataSource, parentCode);
                // 给这些子节点的paerntId赋值，并添加到要返回的Dictionary
                for (YwqDictionary child : childes) {
                    child.setParentId(parentId);
                    child.setRoot(false);
                    ywqDictionaries.add(child);
                    temp.add(copyObject(child, YwqDictionary.class));
                }
            }

            // 找到下一次要找子节点的节点
            loopDic = copyList(temp, YwqDictionary.class);
            // 清空临时列表
            temp.clear();
        }

        int stop = 0;
    }

    private List<YwqDictionary> findChildes(List<YwqDicNode> dataSource, String parentCode) {
        List<YwqDictionary> _dataSource = copyList(dataSource, YwqDictionary.class);
        // 找到所有以当前节点开头的数据
        List<YwqDictionary> childes = _dataSource.stream().filter(
                q -> q.getCode().startsWith(parentCode)
        ).collect(Collectors.toList());

        // 移除自身
        childes.removeIf(q -> q.getCode().equals(parentCode));
        // 移除掉子节点的子节点
        String[] symbol = new String[]{".", "-", "/"};
        for (String s : symbol) {
            childes.removeIf(
                    q -> q.getCode().replace(parentCode, StringUtils.EMPTY).indexOf(s)
                            != q.getCode().replace(parentCode, StringUtils.EMPTY).lastIndexOf(s)
            );
        }

        // 尝试查找有没有只比父节点多一个数字的节点
        List<YwqDictionary> result = new ArrayList<>();
        // 查询不到的时候继续多一个查，或者到第10个层级就停止
        int count = 1;
        while (result.size() <= 0 && count <= 10) {
            int finalCount = count;
            result = childes.stream().filter(
                    q -> q.getCode().replace(parentCode, StringUtils.EMPTY).length() == finalCount
            ).collect(Collectors.toList());
            count++;
        }
        // 如果有结果，就用查出来的做结果
        if (result.size() > 0) {
            childes = result;
        }

        return childes;
    }

    // 找出所有的根节点，并设置字段
    private List<YwqDictionary> selectRoot(List<YwqDicNode> dataSource) {
        List<YwqDictionary> ywqDictionaries = new ArrayList<>();
        // 先获取到根节点List 比如：A、B、C、[U]
        /**
         * todo: 获取根节点可能需要修改下，ASDF123 的根节点 应该是ASDF
         * 思路：应该先拿到所有的数字前的字母，然后去重，就可以得到所有的根节点code了，最好再排序一下
         */
        List<YwqDicNode> nodes = dataSource.stream().filter(
                q -> q.getCode().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).length() == 1)
                .collect(Collectors.toList());
        // 给这些这些根节点设置root字段为true，表示他们是根节点
        for (YwqDicNode node : nodes) {
            // 偷个懒，不想一个一个的赋值，因为他们的字段都一样，所以可以这样偷懒
            YwqDictionary ywqDictionary = copyObject(node, YwqDictionary.class);
            // 设置根节点标志字段
            ywqDictionary.setRoot(true);
            ywqDictionary.setParentId("none");
            ywqDictionaries.add(ywqDictionary);
        }

        return ywqDictionaries;
    }

    private void json2Sql() {
        String dicJson = readFile(new File("/Users/yanwenqiang/Desktop/dicJson.txt"));
        List<YwqDicNode> dicNodes = JSONObject.parseArray(dicJson, YwqDicNode.class);
        for (YwqDicNode dicNode : dicNodes) {
            dicNode.setDicId(generateUUID());
        }
        ywqDicNodeRepository.saveAll(dicNodes);
        System.out.println("报告大王！保存json到数据库完毕！");
    }

    private void saveNode2Txt() {
        List<YwqDicNode> dicNodes = ywqDicNodeRepository.findAll();
        String json = JSONObject.toJSONString(dicNodes);
        writeFile("/Users/yanwenqiang/Desktop/dicJson.txt", json);
    }
}
