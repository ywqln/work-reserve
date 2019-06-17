package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/6/5
 */
@Setter
@Getter
@Table(name = "hjp_paper")
@Entity
public class Paper {

    public Paper() {
    }

    @Id
    @Column(name = "achievementId")
    private String achievementId;

    @Column(name = "paperType")
    private String paperType;
    @Column(name = "chineseTitle")
    private String chineseTitle;
    @Column(name = "englisthTitle")
    private String englishTitle;
    @Column(name = "doi")
    private String doi;
    @Column(name = "publishDate")
    private String publishDate;


    @Column(name = "handle")
    private String handle;
    @Column(name = "firstAuthor")
    private String firstAuthor;
    @Column(name = "correspondingAuthor")
    private String correspondingAuthor;

    @Column(name = "authors")
    private String authors;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "include")
    private String include;
    @Column(name = "refs")
    private String refer;
    @Column(name = "fundProjec")
    private String fundProject;
    @Column(name = "paperAward")
    private String paperAward;
    //学位论文
//    private List<PaperAdvisor> advisor;
    @Column(name = "advisor")
    private String advisor;

    @Column(name = "journal")
    private String journal;

    @Column(name = "language")
    private String language;
    @Column(name = "volume")
    private String volume;
    @Column(name = "issue")
    private String issue;
    @Column(name = "pageStart")
    private String pageStart;  // 学位论文为0
    @Column(name = "pageEnd")
    private String pageEnd;


    @Column(name = "hasFullText")
    private String hasFullText;
    @Column(name = "fullTextUrl")
    private String fullTextUrl;
    @Column(name = "url")
    private String url;
    @Column(name = "abstract")
    private String abstractWords;
    @Column(name = "citation")
    private Long citation;
    @Column(name = "source")
    private String source;
    @Column(name = "lastUpdate")
    private String lastUpdate;
    @Column(name = "personId")
    private String personId;

    // 会议相关
    @Column(name = "conference")
    private String conference;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "startDate")
    private String startDate;
    @Column(name = "endDate")
    private String endDate;

    //学位论文类型：0，phd论文；1，master论文；2：本科论文
    @Column(name = "thesisType")
    private String thesisType;

    //学位论文的页数，就是pageEnd，开始页码为0
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publisherHref")
    private String publisherHref;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "pdf")
    private String pdf;

    @Column(name = "field")
    private String lv1; // 一级领域
    @Column(name = "fieldSub")
    private String lv2;// 二级领域
    @Column(name = "fieldId")
    private String fieldId;
    @Column(name = "fieldSubId")
    private String fieldSubId;
    @Column(name = "journalId")
    private String journalId;


    @Override
    public String toString() {
        return "Paper{" +
                "achievementId='" + achievementId + '\'' +
                ", paperType='" + paperType + '\'' +
                ", chineseTitle='" + chineseTitle + '\'' +
                ", englishTitle='" + englishTitle + '\'' +
                ", doi='" + doi + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", handle='" + handle + '\'' +
                ", firstAuthor='" + firstAuthor + '\'' +
                ", correspondingAuthor='" + correspondingAuthor + '\'' +
                ", authors='" + authors + '\'' +
                ", keywords='" + keywords + '\'' +
                ", include='" + include + '\'' +
                ", refer='" + refer + '\'' +
                ", fundProject='" + fundProject + '\'' +
                ", paperAward='" + paperAward + '\'' +
                ", advisor='" + advisor + '\'' +
                ", journal='" + journal + '\'' +
                ", language='" + language + '\'' +
                ", volume='" + volume + '\'' +
                ", issue='" + issue + '\'' +
                ", pageStart='" + pageStart + '\'' +
                ", pageEnd='" + pageEnd + '\'' +
                ", hasFullText='" + hasFullText + '\'' +
                ", fullTextUrl='" + fullTextUrl + '\'' +
                ", url='" + url + '\'' +
                ", abstractWords='" + abstractWords + '\'' +
                ", citation=" + citation +
                ", source='" + source + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", personId='" + personId + '\'' +
                ", conference='" + conference + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", thesisType='" + thesisType + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publisherHref='" + publisherHref + '\'' +
                ", isbn='" + isbn + '\'' +
                ", pdf='" + pdf + '\'' +
                ", field='" + lv1 + '\'' +
                ", fieldSub='" + lv2 + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fieldSubId='" + fieldSubId + '\'' +
                ", journalId='" + journalId + '\'' +
                '}';
    }
}
