package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_role implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    private Long role_id;

    /**
     * 父角色id
     */
    private Long pid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 提示
     */
    private String description;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 修改时间
     */
    private LocalDateTime update_time;

    /**
     * 创建用户
     */
    private Long create_user;

    /**
     * 修改用户
     */
    private Long update_user;


    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public Long getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Long create_user) {
        this.create_user = create_user;
    }

    public Long getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Long update_user) {
        this.update_user = update_user;
    }

    @Override
    public String toString() {
        return "Sys_role{" +
        "role_id=" + role_id +
        ", pid=" + pid +
        ", name=" + name +
        ", description=" + description +
        ", sort=" + sort +
        ", version=" + version +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", create_user=" + create_user +
        ", update_user=" + update_user +
        "}";
    }
}
