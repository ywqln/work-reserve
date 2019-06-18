package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "paper_keywordsId")
    private String keywordsId;
    private String paperId;
    private String keyword;
    private String chineseTitle;
}
