package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 建设项目表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Construct_project implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "construct_project_id", type = IdType.AUTO)
    private Integer construct_project_id;

    /**
     * 建设项目名称
     */
    private String construct_project_name;

    /**
     * 建设性质ID
     */
    private Integer construct_type_id;


    public Integer getConstruct_project_id() {
        return construct_project_id;
    }

    public void setConstruct_project_id(Integer construct_project_id) {
        this.construct_project_id = construct_project_id;
    }

    public String getConstruct_project_name() {
        return construct_project_name;
    }

    public void setConstruct_project_name(String construct_project_name) {
        this.construct_project_name = construct_project_name;
    }

    public Integer getConstruct_type_id() {
        return construct_type_id;
    }

    public void setConstruct_type_id(Integer construct_type_id) {
        this.construct_type_id = construct_type_id;
    }

    @Override
    public String toString() {
        return "Construct_project{" +
        "construct_project_id=" + construct_project_id +
        ", construct_project_name=" + construct_project_name +
        ", construct_type_id=" + construct_type_id +
        "}";
    }
}
