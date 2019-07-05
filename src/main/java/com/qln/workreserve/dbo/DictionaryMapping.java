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
 * @Date 2019/7/5$ 18:26$
 * @Version V2.0
 **/
@Getter
@Setter
@Entity
@Table(name = "mapping_clc_subject")
public class DictionaryMapping {
    @Id
    private String id;
    private String code;
    private String dicId;
    private String name;
    private String parentId;
    private String root;
    private String map_name_1;//匹配的一级学科名
    private String map_no_1;//匹配的一级学科代码
    private String map_id_1;//匹配的一级id
    private String map_is_match_1;//是否匹配一级学科
    private String map_match_type_1;//匹配方式
    private String map_name_2;//匹配的二级学科名
    private String map_no_2;//匹配的二级学科代码
    private String map_id_2;//匹配的二级id
    private String map_is_match_2;//是否匹配二级学科
    private String map_match_type_2;//匹配方式
    private String map_name_3;//匹配的三级学科名
    private String map_no_3;//匹配的三级学科代码
    private String map_id_3;//匹配的三级id
    private String map_is_match_3;//是否匹配三级学科
    private String map_match_type_3;//匹配方式
    private String map_is_match_12;//匹配的一级学科和二级学科时候具有从属关系
    private String map_is_match_23;//匹配的二级学科和三级学科时候具有从属关系
}
