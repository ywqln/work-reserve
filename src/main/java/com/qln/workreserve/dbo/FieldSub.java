package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
@Table(name = "hjp_paper_field_sub")
public class FieldSub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fieldSubId;
    private String fieldSubName;
    private String paperId;

    public FieldSub() {
    }
}
