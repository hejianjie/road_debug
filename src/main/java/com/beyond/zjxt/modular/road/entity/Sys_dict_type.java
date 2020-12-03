package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_dict_type implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典类型id
     */
    private Long dict_type_id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典类型名称
     */
    private String name;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 是否是系统字典，Y-是，N-否
     */
    private String system_flag;

    /**
     * 状态(字典)
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 添加时间
     */
    private LocalDateTime create_time;

    /**
     * 创建人
     */
    private Long create_user;

    /**
     * 修改时间
     */
    private LocalDateTime update_time;

    /**
     * 修改人
     */
    private Long update_user;


    public Long getDict_type_id() {
        return dict_type_id;
    }

    public void setDict_type_id(Long dict_type_id) {
        this.dict_type_id = dict_type_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getSystem_flag() {
        return system_flag;
    }

    public void setSystem_flag(String system_flag) {
        this.system_flag = system_flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public Long getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Long create_user) {
        this.create_user = create_user;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public Long getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Long update_user) {
        this.update_user = update_user;
    }

    @Override
    public String toString() {
        return "Sys_dict_type{" +
        "dict_type_id=" + dict_type_id +
        ", code=" + code +
        ", name=" + name +
        ", description=" + description +
        ", system_flag=" + system_flag +
        ", status=" + status +
        ", sort=" + sort +
        ", create_time=" + create_time +
        ", create_user=" + create_user +
        ", update_time=" + update_time +
        ", update_user=" + update_user +
        "}";
    }
}
