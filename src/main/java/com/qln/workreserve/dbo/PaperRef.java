package com.qln.workreserve.dbo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bigdata on 2019/3/27.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "paper_ref")
public class PaperRef {
    @Id
    private String paperId;
    private String refId;
    private String paperName; // 最长的一段

    private String name;
    private String url;
    private String org;

    public PaperRef() {
    }
}
