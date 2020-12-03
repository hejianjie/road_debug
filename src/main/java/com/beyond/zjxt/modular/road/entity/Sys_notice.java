package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 通知表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_notice implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Long notice_id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
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


    public Long getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Long notice_id) {
        this.notice_id = notice_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Sys_notice{" +
        "notice_id=" + notice_id +
        ", title=" + title +
        ", content=" + content +
        ", create_time=" + create_time +
        ", create_user=" + create_user +
        ", update_time=" + update_time +
        ", update_user=" + update_user +
        "}";
    }
}
