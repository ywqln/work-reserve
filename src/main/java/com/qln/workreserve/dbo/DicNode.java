package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/7/3$ 12:47$
 * @Version V2.0
 **/
@Getter
@Setter
@Entity
@Table(name = "dictionary_clc_category")
public class DicNode {
    @Id
    private String code;
    private String name;

}
