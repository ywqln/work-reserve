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
@Table(name = "hjp_keywords")
public class Keyword {
    @Id
    @Column(name = "keywordsId")
    private String keywordsId;
    private String keyword;
}
