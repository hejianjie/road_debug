package com.beyond.zjxt.modular.road.entity;

import java.io.Serializable;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_relation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Long relation_id;

    /**
     * 菜单id
     */
    private Long menu_id;

    /**
     * 角色id
     */
    private Long role_id;


    public Long getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(Long relation_id) {
        this.relation_id = relation_id;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Sys_relation{" +
        "relation_id=" + relation_id +
        ", menu_id=" + menu_id +
        ", role_id=" + role_id +
        "}";
    }
}
