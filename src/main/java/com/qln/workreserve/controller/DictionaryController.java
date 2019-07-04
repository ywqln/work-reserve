package com.qln.workreserve.controller;

import com.alibaba.fastjson.JSONObject;
import com.qln.workreserve.dbo.DicNode;
import com.qln.workreserve.dbo.Dictionary;
import com.qln.workreserve.repository.DicNodeRepository;
import com.qln.workreserve.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/7/3$ 9:33$
 * @Version V2.0
 **/
@Component
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private DicNodeRepository dicNodeRepository;

    public void dictionaryDispose() {

        //   判断code的长度是不是为1，
        //   等于1   就是父节点写入数据库
        //   大于1
        //      判断code和现有的code是否相同
        //      相同   continue;
        //      不相同  当前code切掉后面一位，再去对比
        //              相同   存库 得到父级id，然后存款
        //              不相同  继续切割

//        Pageable pageable = PageRequest.of(10, 10);
//        Page<DicNode> dicNodes = dicNodeRepository.findAll(pageable);
        List<DicNode> dicNodes = dicNodeRepository.findAll();

        Dictionary dictionary = new Dictionary();
        for (DicNode dicNode : dicNodes) {
            DicNode oDicNode = JSONObject.parseObject(JSONObject.toJSONString(dicNode), DicNode.class);
            String name = oDicNode.getName();
            String code = oDicNode.getCode();
            int length = code.length();
            System.out.println(length);
            // 1.code的长度=1就是根节点，然后root为1，生成uuid
            if (code.length() == 1) {
                dictionary.setId(generateUUID());
                dictionary.setRoot(true);
                dictionary.setCode(code);
                dictionary.setName(name);
                dictionary.setParentId(generateUUID());
            } else {
                String name1 = dictionary.getName();
                if (!name.equals(dictionary.getName())) {
                    code = code.substring(0, code.length() - 1);
                    code = code.replace(".", "");
                    code = code.replace("+", "");
                    code = code.replace("-", "");
                    if (code.equals(dictionary.getCode())) {
                        dictionary.setId(generateUUID());
                        dictionary.setRoot(false);
                        dictionary.setCode(dictionary.getCode());
                        dictionary.setName(name);
                        dictionary.setParentId(dictionary.getId());
                        continue;
                    }
                }
            }
            dictionaryRepository.save(dictionary);
        }
    }


    private int length = 1;

    public void qln() {
        List<DicNode> dicNodes = dicNodeRepository.findAll();
        List<DicNode> node;
        length = 1;
        // 查询长度
        while (dicNodes.stream().filter(q -> q.getCode().length() == length).collect(Collectors.toList()).size() > 0) {
            node = dicNodes.stream().filter(q -> q.getCode().length() == length).collect(Collectors.toList());

            for (DicNode dicNode : node) {
                String parentCode = dicNode.getCode();
                // 所以父类下的子类菜单
                List<DicNode> childs = dicNodes.stream().filter(q -> q.getCode().startsWith(parentCode)).collect(Collectors.toList());
                String[] symblo = new String[]{".", "-", "/", "+"};
                for (String s : symblo) {
                    childs.removeIf(q -> q.getCode().replace(parentCode, "").indexOf(s) != q.getCode().replace(parentCode, "").lastIndexOf(s));
                    getChildParentId(node, childs);
                }
            }
        }
        length++;
    }

    private void getChildParentId(List<DicNode> node, List<DicNode> childs) {
        Dictionary dictionary = new Dictionary();
        List<Dictionary> dictionaryList = new ArrayList<>();
        for (DicNode dicNode : node) {
            for (DicNode child : childs) {
                if (dicNode.getCode().equals(child.getCode())) {
                    dictionary.setId(generateUUID());
                    dictionary.setCode(child.getCode());
                    dictionary.setName(child.getName());
                    dictionary.setParentId(generateUUID());
                    dictionary.setRoot(true);
                    dictionaryList.add(dictionary);
                    dictionaryRepository.save(dictionary);
                } else {
                    for (Dictionary dictionary1 : dictionaryList) {
                        dictionary.setId(generateUUID());
                        dictionary.setCode(child.getCode());
                        dictionary.setName(child.getName());
                        dictionary.setParentId(dictionary1.getId());
                        dictionary.setRoot(false);
                        dictionaryRepository.save(dictionary);
                    }
                }
            }
        }
    }


    @Override
    public void workFlow() {
//        dictionaryDispose();
//        saveNode2Txt();
//        qln();
        DicNode dicNode = new DicNode();
        List<DicNode> nodeList = new ArrayList<>();
        List<DicNode> nodeList2 = new ArrayList<>();
        nodeList.add(dicNode);
        nodeList2.add(dicNode);
        System.out.println(nodeList);
        System.out.println(nodeList2);
    }

    /**
     * 写入TXT文件
     */
    private void saveNode2Txt() {
        List<DicNode> dicNodes = dicNodeRepository.findAll();
        String json = JSONObject.toJSONString(dicNodes);
        writeFile("C:\\json/dicJson.txt", json);
    }
}
