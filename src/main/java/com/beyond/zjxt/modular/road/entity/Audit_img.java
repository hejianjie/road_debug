package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 审核照片
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Audit_img implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 审核照片
     */
    @TableId(value = "audit_img_id", type = IdType.AUTO)
    private Integer audit_img_id;

    /**
     * 照片名称
     */
    private String audit_img_name;

    /**
     * 验收核算ID
     */
    private Integer acceptance_id;


    public Integer getAudit_img_id() {
        return audit_img_id;
    }

    public void setAudit_img_id(Integer audit_img_id) {
        this.audit_img_id = audit_img_id;
    }

    public String getAudit_img_name() {
        return audit_img_name;
    }

    public void setAudit_img_name(String audit_img_name) {
        this.audit_img_name = audit_img_name;
    }

    public Integer getAcceptance_id() {
        return acceptance_id;
    }

    public void setAcceptance_id(Integer acceptance_id) {
        this.acceptance_id = acceptance_id;
    }

    @Override
    public String toString() {
        return "Audit_img{" +
        "audit_img_id=" + audit_img_id +
        ", audit_img_name=" + audit_img_name +
        ", acceptance_id=" + acceptance_id +
        "}";
    }
}
