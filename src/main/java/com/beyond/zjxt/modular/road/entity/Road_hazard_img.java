package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 巡检病害图片表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Road_hazard_img implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "img_id", type = IdType.AUTO)
    private Integer img_id;

    /**
     * 路况病害ID
     */
    private Integer road_hazard_id;

    /**
     * 图片名称
     */
    private String name;


    public Integer getImg_id() {
        return img_id;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }

    public Integer getRoad_hazard_id() {
        return road_hazard_id;
    }

    public void setRoad_hazard_id(Integer road_hazard_id) {
        this.road_hazard_id = road_hazard_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Road_hazard_img{" +
        "img_id=" + img_id +
        ", road_hazard_id=" + road_hazard_id +
        ", name=" + name +
        "}";
    }
}
