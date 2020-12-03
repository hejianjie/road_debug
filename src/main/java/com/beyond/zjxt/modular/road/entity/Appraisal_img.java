package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Appraisal_img implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 评估照片
     */
    @TableId(value = "appraisal_img_id", type = IdType.AUTO)
    private Integer appraisal_img_id;
    /**
     * 照片名称
     */
    private String appraisal_img_name;
    /**
     * 评估Id
     */
    private Integer appraisal_id;
}
