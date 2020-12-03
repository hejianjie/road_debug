package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 尺寸类型表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Size_type implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "size_type_id", type = IdType.AUTO)
    private Integer size_type_id;


    public Integer getSize_type_id() {
        return size_type_id;
    }

    public void setSize_type_id(Integer size_type_id) {
        this.size_type_id = size_type_id;
    }

    @Override
    public String toString() {
        return "Size_type{" +
        "size_type_id=" + size_type_id +
        "}";
    }
}
