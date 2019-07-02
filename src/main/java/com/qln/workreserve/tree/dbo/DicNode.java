package com.qln.workreserve.tree.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 描述：字典节点
 * </p>
 *
 * @author QinLiNa
 * @data 2019/7/2
 */
@Setter
@Getter
@Entity
public class DicNode {
//    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    // 主键唯一
    private String id;
    // code：A11
    private String code;
    // 名称：马克思不读了
    private String name;
    // 父节点的Id
    private String parentId;
    // 当前节点是不是根节点
    private boolean root;
}
