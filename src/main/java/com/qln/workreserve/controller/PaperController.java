package com.qln.workreserve.controller;

import com.alibaba.fastjson.JSONObject;
import com.qln.workreserve.bo.AuthorJson;
import com.qln.workreserve.repository.*;
import com.qln.workreserve.dbo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: 处理表
 * @Author 秦莉娜
 * @Date 2019/6/6$ 10:01$
 * @Version V2.0
 **/
@Component
public class PaperController extends BaseController {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private PaperRefRepository paperRefRepository;
    @Autowired
    private PaperAuthorRepository paperAuthorRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PaperKeywordsRepository paperKeywordsRepository;
    @Autowired
    private KeywordsRepository keywordsRepository;

    public void workFlow() {
        List<Paper> jsonPapers = readJson2Paper();
        // 论文名
        List<String> chineseTitles = new ArrayList<>();
        // 作者名数组
        List<List<AuthorJson>> authors = new ArrayList<>();
        // 引文
        List<String> refs = new ArrayList<>();
        // 论文
        List<Paper> paper = new ArrayList<>();
        // 关键词
        List<String> keywords = new ArrayList<>();
        // 一级领域
        List<String> fields = new ArrayList<>();
        // 二级领域
        List<String> fieldSubs = new ArrayList<>();

        List<String> firstAuthors = new ArrayList<>();

        for (Paper item : jsonPapers) {
            chineseTitles.add(item.getChineseTitle());
            authors.add(JSONObject.parseArray(item.getAuthors(), AuthorJson.class));
            refs.add(item.getRefer());
            keywords.add(item.getKeywords());
            fields.add(item.getLv1());
            fieldSubs.add(item.getLv2());
            paper.add(item);
            String firstAuthor = item.getFirstAuthor();
            if (!StringUtils.isEmpty(firstAuthor)) {
                // 代码执行完，发现第一作者List长度为0，说明json文件里，所有的firstAuthor字段都是null
                firstAuthors.add(firstAuthor);
            }
        }

//        dealPaper(jsonPapers);
//        ywqPaper(jsonPapers);
//        dealPaperAuthor(jsonPapers);
//        dealPerson(jsonPapers);
        dealKeyword(jsonPapers);
//        dealField(jsonPapers);
//        dealFieldSub(jsonPapers);

        int stop = 0;
    }


    private void dealPerson(List<Paper> jsonPapers) {
//        List<List<Person>> listPersons = new ArrayList<>();
//        for (Paper jsonPaper : jsonPapers) {
//            jsonPaper.getAuthors().contains()
//            List<Person> allByChineseName = personRepository.findAByChineseName();
//            if (allByChineseName.size() > 0) {
//                listPersons.add(allByChineseName);
//                continue;
//            }
//
//        }
    }

//    private void ywqPaper(List<Paper> jsonPapers) {
//        List<Paper> jsonPapersL = JSONObject.parseArray(JSONObject.toJSONString(jsonPapers), Paper.class);
//        PaperKeyword paperKeyword = new PaperKeyword();
//        for (Paper jsonPaper : jsonPapersL) {
//            // 如果每次保存，都用jsonPaper保存可以吗？单独修改下keyword只要数据能对上就行
//            String keywords = jsonPaper.getKeywords();
//            String[] split = keywords.split(",");
//
//            List<KeywordJson> list = new ArrayList<>();
//            for (String s : split) {
//                KeywordJson k = new KeywordJson();
//                s = s.replace("[", StringUtils.EMPTY);
//                s = s.replace("]", StringUtils.EMPTY);
//                s = s.replace("\"", StringUtils.EMPTY);
//                k.setKeyword(s);
//                list.add(k);
//            }
//
//            String json = JSONObject.toJSONString(list);
//            jsonPaper.setKeywords(json);
//            jsonPaper.setAchievementId(generateUUID());
//            String achievementId = jsonPaper.getAchievementId();
//            String strkeywords = jsonPaper.getKeywords();
//            String chineseTitle = jsonPaper.getChineseTitle();
//            paperKeyword.setPaperId(achievementId);
//            paperKeyword.setKeyword(strkeywords);
//            paperKeyword.setKeywordsId(generateUUID());
//            paperKeyword.setChineseTitle(chineseTitle);
//            paperKeywordsRepository.save(paperKeyword);
//        }
//    }


    private void dealPaper(List<Paper> jsonPapers) {
        List<List<Paper>> existPapers = new ArrayList<>();

        for (Paper item : jsonPapers) {
//            List<Paper> searchPapers = paperRepository.findAllByChineseTitle(item.getChineseTitle());
//            if (searchPapers.size() > 0) {
//                existPapers.add(searchPapers);
//                continue;
//            }
            // 没有的数据，直接添加
            insertPaper(item);
        }

        // ====得到论文ids====
//        List<String> existPaperIds = new ArrayList<>();
//        for (List<Paper> existPaper : existPapers) {
//            for (Paper paper : existPaper) {
//                existPaperIds.add(paper.getAchievementId());
//            }
//        }
//        // 得到PaperAuthor表所有的作者
//        List<PaperAuthor> paperAuthors = paperAuthorRepository.findAllByPaperIdIn(existPaperIds);
//
//        List<String> personIds = new ArrayList<>();
//        for (PaperAuthor paperAuthor : paperAuthors) {
//            personIds.add(paperAuthor.getPersonId());
//        }
//
//        List<String> dealPersonName = new ArrayList<>(Arrays.asList("谭铁牛", "怀进鹏"));
//        // 将数组转化成list对象
//        // List<String> dealPersonName = new ArrayList<>(Arrays.asList("谭铁牛"));
//        for (String person : dealPersonName) {
//            // stream().filter 按条件对集合进行过滤
//            // Collectors.toList() 将查询出来的对象转化成list
//            List<PaperAuthor> target = paperAuthors.stream().filter(s -> s.getPersonName().equals(person)).collect(Collectors.toList());
//            String personId = target.get(0).getPersonId();
//
//            for (Paper paper : jsonPapers) {
//                String authors = paper.getAuthors();
//                if (StringUtils.isEmpty(authors)) {
//                    continue;
//                }
//                if (paper.getAuthors().contains(person)) {
//                    if (personIds.contains(personId)) {
//                        // 已经存在了
//                        updatePaper(paper);
//                    } else {
//                        insertPaper(paper);
//                        // 不存在，插入一条论文，把你为论文生成的uuid保存方便中间表添加记录
//                    }
//                }
//            }
//        }
        // ====论文end====
    }

    private void dealPaperAuthor(List<Paper> jsonPapers) {
        for (Paper jsonPaper : jsonPapers) {
            String jsonChineseTitle = jsonPaper.getChineseTitle();
            List<AuthorJson> jsonAuthorJsonList = JSONObject.parseArray(jsonPaper.getAuthors(), AuthorJson.class);
            for (AuthorJson jsonAuthorJson : jsonAuthorJsonList) {
                List<Person> person = personRepository.findAllByChineseName(jsonAuthorJson.getName());
                if (person == null || person.size() <= 0) {
                    // todo：因为现在得不到这么多的person信息，所以先跳过
                    insertPerson(jsonAuthorJson.getName());
                    continue;
                }

                // 找到json中某篇论文中某一个作者的Id
                String personId = person.get(0).getPersonId();
                // 根据这个作者的Id去找到这个作者在[dbo.paper_author]中所有的论文关系
                List<PaperAuthor> paperAuthors = paperAuthorRepository.findAllByPersonId(personId);
                int relation = 0;
                // 循环所有的论文作者关系
                for (PaperAuthor paperAuthor : paperAuthors) {
                    // 取出每一个关系里的paperId
                    String paperId = paperAuthor.getPaperId();
                    // 根据paperId，去[dbo.paper]表里查询得到一行论文数据
                    Paper paper = paperRepository.findByAchievementId(paperId);
                    // 如果有一行论文名和当前json里的论文名一致，说明在[dbo.paper_author]表里存在这个作者和论文的关联关系数据，直接跳出循环体
                    if (paper.getChineseTitle().equals(jsonChineseTitle)) {
                        relation = 1;
                        break;
                    }
                }
                // 如果relation是0，代表[dbo.paper_author]表里，没有这个作者和当前论文的关系，那么就新增一个关系
                if (relation <= 0) {
                    // 去[dbo.paper]找到这边文章的paperId
                    String paperId = paperRepository.findByChineseTitle(jsonChineseTitle).getAchievementId();
                    // 得到当前论文的第一作者firstAuthor
                    String jsonFirstAuthor = jsonPaper.getFirstAuthor();
                    if (StringUtils.isEmpty(jsonFirstAuthor)) {
                        jsonFirstAuthor = StringUtils.EMPTY;
                    }
                    // 得到当前作者的姓名
                    String jsonAuthorName = jsonAuthorJson.getName();
                    // 得到当前作者的机构名称
                    String jsonOrgName = jsonAuthorJson.getOrgin();

                    String orgId = StringUtils.EMPTY;
                    Organization organizationObj = organizationRepository.findByCnName(jsonOrgName);
                    if (organizationObj != null) {
                        orgId = organizationRepository.findByCnName(jsonOrgName).getOrgId();
                    }

                    PaperAuthor paperAuthor = new PaperAuthor();
                    paperAuthor.setPaperAuthorId(generateUUID());
                    paperAuthor.setPersonId(personId);
                    paperAuthor.setPersonName(jsonAuthorName);
                    paperAuthor.setPaperId(paperId);
                    paperAuthor.setIsFirstAuthor(jsonFirstAuthor.equals(jsonAuthorName) ? 1 : 0);
                    paperAuthor.setOrgName(jsonOrgName);
                    paperAuthor.setOrgId(orgId);
                    // 添加关系到[dbo.paper_author]表里
                    paperAuthorRepository.save(paperAuthor);
                    print("添加PaperAuthor " + jsonAuthorName);
                }
            }
        }
    }

    //    private void dealRefs(List<Paper> jsonPapers) {
//        List<String> paperidsList = new ArrayList<>();
//        String refs = null;
//        for (Paper jsonPaper : jsonPapers) {
//            // 引文
//            refs = jsonPaper.getRefer();
//            if (!StringUtils.isEmpty(refs) && !refs.equals("[]")) {
//                List<PaperRef> paperRefs = JSONObject.parseArray(refs, PaperRef.class);
//                for (PaperRef paperRef : paperRefs) {
//                    String name = paperRef.getName();
//                    String achievementIds = paperRepository.findByChineseTitle(name).getAchievementId();
//                    if (!StringUtils.isEmpty(achievementIds)){
//                        paperidsList.add(achievementIds);
//                        List<PaperAuthor> allByPaperIdIn = paperAuthorRepository.findAllByPaperIdIn(paperidsList);
//                        if (allByPaperIdIn == null || allByPaperIdIn.size() < 0) {
//                            insertPaperRefer(paperRef);
//                        }
//                    }
//                }
//            }
//        }
//        int stop = 0;
//    }

    public void in(List<Paper> jsonPapers) {
        // 读取原json文件 然后保存关键词，论文标题，关键词UUID,论文标题UUID
//        Keyword entityKeyword = new Keyword();
//
//        for (Paper jsonPaper : jsonPapers) {
//            String keywords = jsonPaper.getKeywords();
//            String[] split = keywords.split(",");
//
//            List<KeywordJson> list = new ArrayList<>();
//            for (String s : split) {
//                KeywordJson k = new KeywordJson();
//                s = s.replace("[", StringUtils.EMPTY);
//                s = s.replace("]", StringUtils.EMPTY);
//                s = s.replace("\"", StringUtils.EMPTY);
//                k.setKeyword(s);
//                list.add(k);
//            }
//            String json = JSONObject.toJSONString(list);
//
//            entityKeyword.setKeyword(json);
//            entityKeyword.setKeywordsId(generateUUID());
//            keywordsRepository.save(entityKeyword);
//        }
    }

    private void dealKeyword(List<Paper> jsonPapers) {

        // hjp_paper_keywords 中间表 paper_keywordsId 是keyword的id，paperId=json源文件的id，keyword，chineseTitle


        int stop = 0;
    }

    private void dealField(List<Paper> jsonPapers) {
        List<String> fields = new ArrayList<>();
        Field f = new Field();
        for (Paper jsonPaper : jsonPapers) {
            String field = jsonPaper.getLv1();
            if (!StringUtils.isEmpty(field)) {
                fields.add(field);
            }
            f.setFieldId(generateUUID());

        }


        int stop = 0;
    }

    private void dealFieldSub(List<Paper> jsonPapers) {
        List<String> fieldSubs = new ArrayList<>();
        for (Paper jsonPaper : jsonPapers) {
            String fieldSub = jsonPaper.getLv2();
            if (!StringUtils.isEmpty(fieldSub)) {
                fieldSubs.add(fieldSub);
            }
        }
        int stop = 0;
    }


    private void insertPerson(String personName) {
        // 程序运行后，发现并没有执行这个方法，所以判断，json文件里涉及到的所有作者信息，在mysql的person表里都存在，不需要额外添加，因此，这块代码逻辑暂时未实现，优先级放低
        print("插入人表：" + personName);
    }

    private void updatePaper(Paper paper) {
        // todo: 补全论文 -- 好像不需要做补全
        print("更新论文：" + paper.getChineseTitle());
    }

    private void insertPaper(Paper paper) {
        paper.setAchievementId(generateUUID());
        paperRepository.save(paper);
        print("插入一条数据：" + paper.getChineseTitle());
    }

    private void insertPaperRefer(PaperRef paperRef) {
        paperRef.setRefId(generateUUID());
        paperRefRepository.save(paperRef);
        print("插入一条数据：" + paperRef.getName());
    }
}
