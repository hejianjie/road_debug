package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 病害基础信息
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Hazard_base_info implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Integer info_id;

    /**
     * 病害名称
     */
    private String name;


    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hazard_base_info{" +
        "info_id=" + info_id +
        ", name=" + name +
        "}";
    }
}
