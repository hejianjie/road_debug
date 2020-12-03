package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author :zjk
 * @Date :Create in 21:05 2019-12-03
 * @Description zjk
 **/
public class CityAudit implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer applicationId;

    private Long user_id;

    private String user_name;

    private String audit_status;

    private String auditfeedback;

    private Date reffaudit_time;

    private String audit_role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(String audit_status) {
        this.audit_status = audit_status;
    }

    public String getAuditfeedback() {
        return auditfeedback;
    }

    public void setAuditfeedback(String auditfeedback) {
        this.auditfeedback = auditfeedback;
    }

    public Date getReffaudit_time() {
        return reffaudit_time;
    }

    public void setReffaudit_time(Date reffaudit_time) {
        this.reffaudit_time = reffaudit_time;
    }

    public String getAudit_role() {
        return audit_role;
    }

    public void setAudit_role(String audit_role) {
        this.audit_role = audit_role;
    }

    @Override
    public String toString() {
        return "CityAudit{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", audit_status='" + audit_status + '\'' +
                ", auditfeedback='" + auditfeedback + '\'' +
                ", reffaudit_time='" + reffaudit_time + '\'' +
                ", audit_role='" + audit_role + '\'' +
                '}';
    }
}
