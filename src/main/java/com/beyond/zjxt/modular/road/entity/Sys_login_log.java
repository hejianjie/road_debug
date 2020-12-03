package com.beyond.zjxt.modular.road.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public class Sys_login_log implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Long login_log_id;

    /**
     * 日志名称
     */
    private String log_name;

    /**
     * 管理员id
     */
    private Long user_id;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 是否执行成功
     */
    private String succeed;

    /**
     * 具体消息
     */
    private String message;

    /**
     * 登录ip
     */
    private String ip_address;


    public Long getLogin_log_id() {
        return login_log_id;
    }

    public void setLogin_log_id(Long login_log_id) {
        this.login_log_id = login_log_id;
    }

    public String getLog_name() {
        return log_name;
    }

    public void setLog_name(String log_name) {
        this.log_name = log_name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    @Override
    public String toString() {
        return "Sys_login_log{" +
        "login_log_id=" + login_log_id +
        ", log_name=" + log_name +
        ", user_id=" + user_id +
        ", create_time=" + create_time +
        ", succeed=" + succeed +
        ", message=" + message +
        ", ip_address=" + ip_address +
        "}";
    }
}
