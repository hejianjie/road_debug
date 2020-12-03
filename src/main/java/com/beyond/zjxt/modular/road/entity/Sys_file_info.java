package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 文件信息表
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_file_info implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    private String file_id;

    /**
     * base64编码的文件
     */
    private String file_data;

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


    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_data() {
        return file_data;
    }

    public void setFile_data(String file_data) {
        this.file_data = file_data;
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
        return "Sys_file_info{" +
        "file_id=" + file_id +
        ", file_data=" + file_data +
        ", create_time=" + create_time +
        ", update_time=" + update_time +
        ", create_user=" + create_user +
        ", update_user=" + update_user +
        "}";
    }
}
