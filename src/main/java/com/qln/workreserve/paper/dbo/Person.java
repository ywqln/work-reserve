package com.qln.workreserve.paper.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "personId")
    private String personId;
    @Column(name = "chineseName")
    private String chineseName;
    @Column(name = "englishName")
    private String englishName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "ethnicity")
    private String ethnicity;               //民族
    @Column(name = "birthplace")
    private String birthplace;
    @Column(name = "nationality")
    private String nationality;            // 国籍
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "emails")
    private String emails;
    @Column(name = "mailAddress")
    private String mailAddress;
    @Column(name = "briefDescription")
    private String briefDescription;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "currentOrganization")
    private String currentOrganization;
    @Column(name = "position")
    private String position;
//    @Column(name = "ctitle")
//    private String ctitle;
    @Column(name = "title")
    private String title;
    @Column(name = "political")
    private String political;
    @Column(name = "workExperience")
    private String workExperience;
    @Column(name = "outstandings")
    private String outstandings;

    @Column(name = "socialTitle")
    private String socialTitle;
    @Column(name = "academicTitle")
    private String academicTitle;
    @Column(name = "reviewExpert")
    private String reviewExpert;
    @Column(name = "researchArea")
    private String researchArea;
    @Column(name = "degree")
    private String degree;
    @Column(name = "education")
    private String education;
    @Column(name = "postdoctor")
    private String postdoctor;
    @Column(name = "languages")
    private String languages;
    @Column(name = "awards")
    private String awards;
    @Column(name = "leader")
    private String leader;

    @Column(name = "isPostdoctoral")
    private Integer isPostdoctoral;
    @Column(name = "isTuring")
    private Integer isTuring;
    @Column(name = "isAcademician")
    private Integer isAcademician;
    @Column(name = "isOverseasAcademician")
    private Integer isOverseasAcademician;
    @Column(name = "isOutstanding")
    private Integer isOutstanding;
    @Column(name = "source")
    private String source;
    @Column(name = "lastUpdate")
    private Date lastUpdate;
    @Column(name = "influenceScore")
    private Long influenceScore;

}
