package com.qln.workreserve.controller;

import com.qln.workreserve.dbo.DicNode;
import com.qln.workreserve.dbo.YwqDicNode;
import com.qln.workreserve.dbo.YwqDictionary;
import com.qln.workreserve.repository.DicNodeRepository;
import com.qln.workreserve.repository.YwqDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/7/4
 */
@Component
public class DicController extends YwqController {
    @Autowired
    private DicNodeRepository dicNodeRepository;
    @Autowired
    private YwqDictionaryRepository ywqDictionaryRepository;

    @Override
    public void workFlow() {
        method2();
//        treeNode();
//        test();
    }

    private void test() {
        List<DicNode> qlnSql = dicNodeRepository.findAll();
        List<YwqDicNode> dataSource = copyList(qlnSql, YwqDicNode.class);
        List<String> codes = new ArrayList<>();
        for (DicNode dicNode : qlnSql) {
            String childCode = dicNode.getCode();
            char[] chars = childCode.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (char aChar : chars) {
                int ascii = aChar;
                // 48-57 : 0-9
                // 65-90 : A-Z
                // 97-122: a-z
                if (ascii >= 65 && ascii <= 90) {
                    stringBuilder.append(aChar);
                    continue;
                }
                break;
            }
            codes.add(stringBuilder.toString());
        }

        List<String> result = codes.stream().distinct().collect(Collectors.toList());
        for (String s : result) {
            System.out.println("root : " + s);
        }

        int stop = 0;
    }

    private void treeNode() {
        long start = System.currentTimeMillis();
        // A23.2.2 A23.2.2.5
        // A23 A23.2 A23.2.2
        // A23 A23/2 A23/2/2
        // [A] [A23] [A23/2] [A23/2/5]
        // 从数据库中读取到源数据
        List<DicNode> qlnSql = dicNodeRepository.findAll();
        // 将两列的对象数组浅拷贝为YwqDicNode数组
        List<YwqDicNode> dataSource = copyList(qlnSql, YwqDicNode.class);
        for (YwqDicNode ywqDicNode : dataSource) {
            ywqDicNode.setDicId(generateUUID());
        }

        // 最终要保存的对象
        List<YwqDictionary> ywqDictionaries = selectRoot(dataSource);
        // 找子节点
        tree(dataSource, ywqDictionaries);

        // 保存
        ywqDictionaryRepository.saveAll(ywqDictionaries);

        long end = System.currentTimeMillis();
        System.out.println("报告大王！树结构节点数据已经保存到数据库！耗时：" + ((end - start) / 1000) + "秒");
    }

    @Override
    protected void method2() {
        System.out.println("开始执行");
        List<DicNode> qlnSql = dicNodeRepository.findAll();
        System.out.println("查出所有");
        // 将两列的对象数组浅拷贝为YwqDicNode数组
        List<YwqDicNode> dataSource = copyList(qlnSql, YwqDicNode.class);
        for (YwqDicNode ywqDicNode : dataSource) {
            ywqDicNode.setDicId(generateUUID());
        }
        System.out.println("追加dicId完毕");
        findParents(dataSource);
    }
}
