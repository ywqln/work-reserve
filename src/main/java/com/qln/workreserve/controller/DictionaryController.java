package com.qln.workreserve.controller;

import com.qln.workreserve.dbo.DicNode;
import com.qln.workreserve.dbo.Dictionary;
import com.qln.workreserve.repository.DicNodeRepository;
import com.qln.workreserve.repository.DictionaryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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

        Pageable pageable = PageRequest.of(10, 10);
//        Page<DicNode> dicNodes = dicNodeRepository.findAll(pageable);
        List<DicNode> dicNodes = dicNodeRepository.findAll();

        Dictionary dictionary = new Dictionary();
        for (DicNode dicNode : dicNodes) {
            String name = dicNode.getName();
            String code = dicNode.getCode();
            int length = code.length();
            System.out.println(length);
            // 1.code的长度=1就是根节点，然后root为1，生成uuid
            if (code.length() == 1) {
                dictionary.setId(generateUUID());
                dictionary.setRoot(true);
                dictionary.setCode(code);
                dictionary.setName(name);
                dictionary.setParentId(generateUUID());
            } else if (code.length() >= 2) {
                if (!name.equals(dictionary.getName())) {
                    code = code.substring(0, 1);
                    if (code.equals(dictionary.getCode())) {
                        dictionary.setId(dictionary.getId());
                        dictionary.setRoot(false);
                        dictionary.setCode(code);
                        dictionary.setName(name);
                        dictionary.setParentId(dictionary.getId());
                    }
                } else {

                }
            }

            dictionaryRepository.save(dictionary);
        }
    }

    @Override
    public void workFlow() {
        dictionaryDispose();
    }
}
