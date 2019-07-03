package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/7/3$ 9:35$
 * @Version V2.0
 **/
@Getter
@Setter
@Entity
@Table(name = "qln_dictionary_clc_category")
public class Dictionary {
    @Id
    @GenericGenerator(name = "id", strategy = "uuid")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String code;
    private String name;
    private String parentId;
    private boolean root;
}
