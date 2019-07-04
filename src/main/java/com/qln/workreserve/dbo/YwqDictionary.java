package com.qln.workreserve.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/7/4
 */
@Entity
@Getter
@Setter
@Table(name = "dictionary_result")
public class YwqDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String dicId;
    private String code;
    private String name;
    private String parentId;
    private boolean root;
}
