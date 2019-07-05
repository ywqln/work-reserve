package com.qln.workreserve.controller;

import com.alibaba.fastjson.JSONObject;
import com.qln.workreserve.dbo.Dictionary;
import com.qln.workreserve.dbo.YwqDicNode;
import com.qln.workreserve.dbo.YwqDictionary;
import com.qln.workreserve.repository.YwqDicNodeRepository;
import com.qln.workreserve.repository.YwqDictionaryRepository;
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
    @Autowired
    private YwqDictionaryRepository ywqDictionaryRepository;

    @Override
    public void workFlow() {
        method2();
    }

    protected void method2() {
        List<YwqDicNode> dataSource = ywqDicNodeRepository.findAll();
        findParents(dataSource);
    }

    protected void findParents(List<YwqDicNode> dataSource) {
        int noParentCount = 0;
        for (YwqDicNode ywqDicNode : dataSource) {
            String code = ywqDicNode.getCode();
            int length = code.length();
            // 如果code长度是1，必然是根节点，设置根节点属性，并跳出本次循环。
            if (length == 1) {
                // 这些就是祖宗了
                ywqDicNode.setRoot(true);
                ywqDicNode.setParentId("none");
                continue;
            }

            String subCode = code;
            boolean find = false;
            while (subCode.length() > 1 && !find) {
                // 从后面砍掉一个
                subCode = removeLast(subCode);
                // 再砍掉一个
                // 单独处理长得有点畸形的子孙
                if (subCode.endsWith(".") || subCode.endsWith("-") || subCode.endsWith("/")) {
                    subCode = removeLast(subCode);
                }
                String finalSubCode = subCode;
                List<YwqDicNode> parents = dataSource.stream().filter(q -> q.getCode().equals(finalSubCode)).collect(Collectors.toList());
                if (parents.size() > 0) {
                    // 给父子节点挂关系（儿子找爸爸 / 小蝌蚪找爸爸）
                    YwqDicNode parentNode = parents.get(0);
                    ywqDicNode.setParentId(parentNode.getDicId());
                    ywqDicNode.setRoot(false);

                    if (parents.size() > 1) {
                        // 他们的长辈关系应该是很乱，道德缺失啊...
                        System.out.println(code + "查询到" + parents.size() + "个父节点，我们选择第一个");
                    }
                    find = true;
                }
            }

            if (find) {
                continue;
            }

            // 找到最后都没有找到父节点
            // 这些就是石头缝里蹦出来的小孩儿，没有爹，野孩子啊 waif
            System.out.println(code + "没有查询到父节点");
            ywqDicNode.setParentId("waif");
            // 统计下野孩子的数量
            noParentCount++;
        }

        List<YwqDictionary> result = copyList(dataSource, YwqDictionary.class);
        ywqDictionaryRepository.saveAll(result);

        System.out.println("报告大王！族谱统计完毕！其中有" + noParentCount + "个野孩子！");
    }

    private String removeLast(String value) {
        return value.substring(0, value.length() - 1);
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

    protected void tree(List<YwqDicNode> dataSource, List<YwqDictionary> ywqDictionaries) {
        List<YwqDictionary> loopDic = copyList(ywqDictionaries, YwqDictionary.class);
        List<YwqDictionary> temp = new ArrayList<>();

        while (loopDic.size() > 0) {
            for (YwqDictionary ywqDictionary : loopDic) {
                // 得到当前节点的code
                String parentId = ywqDictionary.getDicId();
                String parentCode = ywqDictionary.getCode();

                System.out.println("现在统计了" + ywqDictionaries.size() + "条数据");

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
    protected List<YwqDictionary> selectRoot(List<YwqDicNode> dataSource) {
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
