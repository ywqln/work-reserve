package com.qln.workreserve.paper.dbo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String paperId;
    private String refId;
    private String paperName; // 最长的一段

    private String name;
    private String url;
    private String org;

    public PaperRef() {
    }
}
