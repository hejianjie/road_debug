package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Data
@TableName("sys_operation_log")

public class Sys_operation_log implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "operation_log_id",type = IdType.AUTO)
    private Long operation_log_id;

    /**
     * 日志类型(字典)
     */
    private String log_type;

    /**
     * 日志名称
     */
    private String log_name;

    /**
     * 用户id
     */
    private Long user_id;

    /**
     * 类名称
     */
    private String class_name;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 是否成功(字典)
     */
    private String succeed;

    /**
     * 备注
     */
    private String message;


    public Long getOperation_log_id() {
        return operation_log_id;
    }

    public void setOperation_log_id(Long operation_log_id) {
        this.operation_log_id = operation_log_id;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    @Override
    public String toString() {
        return "Sys_operation_log{" +
        "operation_log_id=" + operation_log_id +
        ", log_type=" + log_type +
        ", log_name=" + log_name +
        ", user_id=" + user_id +
        ", class_name=" + class_name +
        ", method=" + method +
        ", create_time=" + create_time +
        ", succeed=" + succeed +
        ", message=" + message +
        "}";
    }
}
