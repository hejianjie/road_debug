package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 第三方单位表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Third_party implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "third_party_id", type = IdType.AUTO)
    private Integer third_party_id;

    /**
     * 名称
     */
    private String name;

    /**
     * 负责人
     */
    private Integer boss;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 单位性质：0-造价核算 1-验收
     */
    private Integer type;


    public Integer getThird_party_id() {
        return third_party_id;
    }

    public void setThird_party_id(Integer third_party_id) {
        this.third_party_id = third_party_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Third_party{" +
        "third_party_id=" + third_party_id +
        ", name=" + name +
        ", boss=" + boss +
        ", telephone=" + telephone +
        ", email=" + email +
        ", account=" + account +
        ", password=" + password +
        ", type=" + type +
        "}";
    }
}
