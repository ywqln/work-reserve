package com.qln.workreserve.paper.dbo;

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
@Table(name = "hjp_paper_field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fieldId;
    private String fieldName;
    private String paperId;

    public Field() {
    }
}
