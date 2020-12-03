package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 基础字典
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_dict implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字典id
     */
    private Long dict_id;

    /**
     * 所属字典类型的id
     */
    private Long dict_type_id;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 上级代码id
     */
    private Long parent_id;

    /**
     * 所有上级id
     */
    private String parent_ids;

    /**
     * 状态（字典）
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字典的描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 更新时间
     */
    private LocalDateTime update_time;

    /**
     * 创建人
     */
    private Long create_user;

    /**
     * 修改人
     */
    private Long update_user;


    public Long getDict_id() {
        return dict_id;
    }

    public void setDict_id(Long dict_id) {
        this.dict_id = dict_id;
    }

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

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Sys_dict{" +
        "dict_id=" + dict_id +
        ", dict_type_id=" + dict_type_id +
        ", code=" + code +
        ", name=" + name +
        ", parent_id=" + parent_id +
        ", parent_ids=" + parent_ids +
        ", status=" + status +
        ", sort=" + sort +
        ", description=" + description +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", create_user=" + create_user +
        ", update_user=" + update_user +
        "}";
    }
}
