package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_menu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    private Long menu_id;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单父编号
     */
    private String pcode;

    /**
     * 当前菜单的所有父菜单编号
     */
    private String pcodes;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * url地址
     */
    private String url;

    /**
     * 菜单排序号
     */
    private Integer sort;

    /**
     * 菜单层级
     */
    private Integer levels;

    /**
     * 是否是菜单(字典)
     */
    private String menu_flag;

    /**
     * 备注
     */
    private String description;

    /**
     * 菜单状态(字典)
     */
    private String status;

    /**
     * 是否打开新页面的标识(字典)
     */
    private String new_page_flag;

    /**
     * 是否打开(字典)
     */
    private String open_flag;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 修改时间
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


    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPcodes() {
        return pcodes;
    }

    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getMenu_flag() {
        return menu_flag;
    }

    public void setMenu_flag(String menu_flag) {
        this.menu_flag = menu_flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNew_page_flag() {
        return new_page_flag;
    }

    public void setNew_page_flag(String new_page_flag) {
        this.new_page_flag = new_page_flag;
    }

    public String getOpen_flag() {
        return open_flag;
    }

    public void setOpen_flag(String open_flag) {
        this.open_flag = open_flag;
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
        return "Sys_menu{" +
        "menu_id=" + menu_id +
        ", code=" + code +
        ", pcode=" + pcode +
        ", pcodes=" + pcodes +
        ", name=" + name +
        ", icon=" + icon +
        ", url=" + url +
        ", sort=" + sort +
        ", levels=" + levels +
        ", menu_flag=" + menu_flag +
        ", description=" + description +
        ", status=" + status +
        ", new_page_flag=" + new_page_flag +
        ", open_flag=" + open_flag +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", create_user=" + create_user +
        ", update_user=" + update_user +
        "}";
    }
}
