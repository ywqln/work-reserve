package com.qln.workreserve.controller;

import com.qln.workreserve.dbo.DicNode;
import com.qln.workreserve.dbo.Dictionary;
import com.qln.workreserve.repository.DicNodeRepository;
import com.qln.workreserve.repository.DictionaryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/7/3$ 9:33$
 * @Version V2.0
 **/
@Component
public class DictionaryController {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private DicNodeRepository dicNodeRepository;

    @Test
    public void dictionaryDispose() {

        List<DicNode> dicNodes = dicNodeRepository.findAll();

        Iterator<DicNode> iterator = dicNodes.iterator();
        Dictionary dictionary = new Dictionary();
        while (iterator.hasNext()) {
            for (DicNode dicNode : dicNodes) {
                String name = dicNode.getName();
                String code = dicNode.getCode();


                if (code.length() >= 1) {
                    dictionary.setRoot(true);
                    code = code.substring(0, code.length() - 1);
//                    if (code.split(code,1)) {
                    dictionary.setCode(code);
                    dictionary.setName(name);
//                        dictionary.setParentId();
                    continue;
//                    }
                } else {
                    dictionary.setRoot(false);
                }
                dictionaryRepository.save(dictionary);
            }
        }


    }

}
