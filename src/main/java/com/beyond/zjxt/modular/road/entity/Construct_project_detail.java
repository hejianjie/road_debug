package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 建设项目细目表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Data
public class Construct_project_detail implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "construct_project_detail_id", type = IdType.AUTO)
    private Integer construct_project_detail_id;

    /**
     * 建设项目细目表名称
     */
    private String construct_project_detail_name;

    /**
     * 建设项目表ID
     */
    private Integer construct_project_id;


    public Integer getConstruct_project_detail_id() {
        return construct_project_detail_id;
    }

    public void setConstruct_project_detail_id(Integer construct_project_detail_id) {
        this.construct_project_detail_id = construct_project_detail_id;
    }

    public String getConstruct_project_detail_name() {
        return construct_project_detail_name;
    }

    public void setConstruct_project_detail_name(String construct_project_detail_name) {
        this.construct_project_detail_name = construct_project_detail_name;
    }

    public Integer getConstruct_project_id() {
        return construct_project_id;
    }

    public void setConstruct_project_id(Integer construct_project_id) {
        this.construct_project_id = construct_project_id;
    }

    @Override
    public String toString() {
        return "Construct_project_detail{" +
        "construct_project_detail_id=" + construct_project_detail_id +
        ", construct_project_detail_name=" + construct_project_detail_name +
        ", construct_project_id=" + construct_project_id +
        "}";
    }
}
