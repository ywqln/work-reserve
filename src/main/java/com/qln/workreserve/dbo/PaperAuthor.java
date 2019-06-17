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
 * @data 2019/6/7
 */
@Entity
@Getter
@Setter
@Table(name = "paper_author")
public class PaperAuthor {
    @Id
    private String paperId;
    private String personId;
    private String personName;
    private String orgId;
    private String orgName;
    private String email;
    private int isFirstAuthor;
    @Column(name = "paper_author_id")
    private String paperAuthorId;

    public PaperAuthor() {
    }
}
