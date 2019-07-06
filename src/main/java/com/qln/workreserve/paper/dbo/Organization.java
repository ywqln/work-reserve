package com.qln.workreserve.paper.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Title: Organization$
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/6/5$ 17:25$
 * @Version V2.0
 **/
@Setter
@Getter
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orgId;
    private String code;
    private String alias;
    private String cnName;
    private String enName;
    private String type;
    private String establelishDate;
    private String creator;
    private String districtCode;
    private String zipCode;
    private String parentOrganization;
    private String url;
    private String source;
    private String lastUpdate;
    private String influenceScore;
    private String nationality;
    private String province;
    private String city;
    private String address;
}
