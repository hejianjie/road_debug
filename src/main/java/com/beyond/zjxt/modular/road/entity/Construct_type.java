package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Construct_type implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "construct_type_id", type = IdType.AUTO)
    private Integer constructTypeId;

    /**
     * 建设性质名称
     */
    private String constructTypeName;

    public Integer getConstructTypeId() {
        return constructTypeId;
    }

    public void setConstructTypeId(Integer constructTypeId) {
        this.constructTypeId = constructTypeId;
    }

    public String getConstructTypeName() {
        return constructTypeName;
    }

    public void setConstructTypeName(String constructTypeName) {
        this.constructTypeName = constructTypeName;
    }

    @Override
    public String toString() {
        return "Construct_type{" +
                "construct_type_id=" + constructTypeId +
                ", construct_type_name=" + constructTypeName +
                "}";
    }
}
