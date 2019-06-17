package com.qln.workreserve.controller;

import com.alibaba.fastjson.JSONObject;
import com.qln.workreserve.WorkReserveApplication;
import com.qln.workreserve.bo.KeywordJson;
import com.qln.workreserve.repository.*;
import com.qln.workreserve.dbo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：添加hjp信息
 * </p>
 *
 * @author QinLiNa
 * @data 2019/6/10
 */
@Component
public class AddHJPTableController extends BaseController {

    @Autowired
    private KeywordsRepository keywordsRepository;
    @Autowired
    private PaperKeywordsRepository paperKeywordsRepository;
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FieldSubRepository fieldSubRepository;
    @Autowired
    private FieldSubWithRepository fieldSubWithRepository;

    @Override
    public void workFlow() {
        List<Paper> jsonPapers = readJson2Paper();
        List<Paper> hjpPapers = new ArrayList<>();
        for (Paper jsonPaper : jsonPapers) {
            Paper hjpPaper = paper(jsonPaper);
            hjpPapers.add(hjpPaper);
            keywords(hjpPaper);
            Field lv1 = field(hjpPaper);
            FieldSub lv2 = fieldSub(hjpPaper);
            fieldSubWith(lv1, lv2);
        }
        // 插入所有论文表
        paperRepository.saveAll(hjpPapers);
    }

    // 处理paper表
    private Paper paper(Paper jsonPaper) {
        Paper jsonPapersCopy = JSONObject.parseObject(JSONObject.toJSONString(jsonPaper), Paper.class);

        String keywords = jsonPapersCopy.getKeywords();
        keywords = keywords.replace("[", StringUtils.EMPTY)
                .replace("]", StringUtils.EMPTY)
                .replace("\"", StringUtils.EMPTY);

        String[] split = keywords.split(",");
        List<KeywordJson> list = new ArrayList<>();
        for (String s : split) {
            KeywordJson k = new KeywordJson();
            k.setKeyword(s);
            list.add(k);
        }

        String json = JSONObject.toJSONString(list);
        jsonPapersCopy.setKeywords(json);
        jsonPapersCopy.setAchievementId(generateUUID());

        return jsonPapersCopy;
    }

    // 处理关键词和中间表
    private void keywords(Paper jsonPaper) {
        List<Keyword> keywords = new ArrayList<>();
        List<PaperKeyword> paperKeywords = new ArrayList<>();

        List<KeywordJson> keywordJsonList = JSONObject.parseArray(jsonPaper.getKeywords(), KeywordJson.class);
        for (KeywordJson item : keywordJsonList) {
            // 关键词表
            Keyword keyword = new Keyword();
            keyword.setKeywordsId(generateUUID());
            keyword.setKeyword(item.getKeyword());
            keywords.add(keyword);

            // 论文关键词中间表
            PaperKeyword paperKeyword = new PaperKeyword();
            paperKeyword.setPaperId(jsonPaper.getAchievementId());
            paperKeyword.setKeyword(item.getKeyword());
            paperKeyword.setChineseTitle(jsonPaper.getChineseTitle());
            paperKeyword.setKeywordsId(keyword.getKeywordsId());
            paperKeywords.add(paperKeyword);
        }

        // 插入关键词表
        keywordsRepository.saveAll(keywords);
        // 插入论文关键词中间表
        paperKeywordsRepository.saveAll(paperKeywords);
    }

    // 处理一级领域表
    private Field field(Paper jsonPaper) {
        String lv1 = jsonPaper.getLv1();

        Field field = new Field();
        field.setFieldId(generateUUID());
        field.setFieldName(lv1);
        field.setPaperId(jsonPaper.getAchievementId());

        // 插入一级领域
        fieldRepository.save(field);
        return field;
    }

    // 处理二级领域表
    private FieldSub fieldSub(Paper jsonPaper) {
        String lv2 = jsonPaper.getLv2();

        FieldSub fieldSub = new FieldSub();
        fieldSub.setFieldSubId(generateUUID());
        fieldSub.setFieldSubName(lv2);
        fieldSub.setPaperId(jsonPaper.getAchievementId());

        // 插入二级领域
        fieldSubRepository.save(fieldSub);
        return fieldSub;
    }

    // 处理一二级领域关联中间表
    private void fieldSubWith(Field field, FieldSub fieldSub) {
        FieldSubWith fieldSubWith = new FieldSubWith();

        fieldSubWith.setFieldId(field.getFieldId());
        fieldSubWith.setFieldName(field.getFieldName());
        fieldSubWith.setFieldSubId(fieldSub.getFieldSubId());
        fieldSubWith.setFieldSubName(fieldSub.getFieldSubName());

        // 插入一二级领域中间关联表
        fieldSubWithRepository.save(fieldSubWith);
    }
}