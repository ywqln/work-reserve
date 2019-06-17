package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

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
@Table(name = "hjp_field_sub_with")
public class FieldSubWith {
    @Id
    private String fieldId;
    private String fieldName;
    private String fieldSubId;
    private String fieldSubName;

    public FieldSubWith() {
    }
}
