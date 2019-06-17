package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bigdata on 2019/3/28.
 * 用来处理String类型的内嵌
 */
@Getter
@Setter
@Entity
@Table(name = "hjp_paper_keywords")
public class PaperKeyword {
    @Id
    @Column(name = "paper_keywordsId")
    private String keywordsId;
    private String paperId;
    private String keyword;
    private String chineseTitle;
}
