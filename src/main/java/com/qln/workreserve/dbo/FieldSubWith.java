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
@Table(name = "hjp_field_sub_with")
public class FieldSubWith {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fieldId;
    private String fieldName;
    private String fieldSubId;
    private String fieldSubName;

    public FieldSubWith() {
    }
}
