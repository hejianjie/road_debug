package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 管理单位
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Supervisor implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 主键
     */
    //  @TableId(value = "supervisor_id", type = IdType.AUTO)
    private Integer supervisorId;
    /**
     * 管理单位名称
     */
    private String supervisorName;

    /**
     * 父级管理单位ID
     */
    private Integer parentId;

    /**
     * 下级单位数量
     */
    private int number;

    /**
     * 负责人
     */
    private Integer userId;

    /***
     * 负责任名称
     */
    private String userName;

    /***
     * 电话号码
     */
    private String telephone;

    /***
     * 账户
     */
    private String account;

    /***
     * 密码
     */
    private String password;

    /***
     * 图标
     * @return
     */
    private String icon;

    /**
     * 机构总人数
     */
    public int personCount;

    /**
     * 上级机构名
     */
    public String parentBodyName;


    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public String getParentBodyName() {
        return parentBodyName;
    }

    public void setParentBodyName(String parentBodyName) {
        this.parentBodyName = parentBodyName;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public String toString() {
        return "Supervisor{" +
                "supervisor_id=" + supervisorId +
                ", name=" + supervisorName +
                ", parent_id=" + parentId +
                ", boss=" + userName +
                ", account =" + account +
                ", password  =" + password +
                ", telephone  =" + password +
                "}";
    }
}
