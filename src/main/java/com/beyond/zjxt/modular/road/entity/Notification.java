package com.beyond.zjxt.modular.road.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
@Data
public class Notification implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "notification_id", type = IdType.AUTO)
    private Integer notification_id;

    /**
     * 标题
     */

    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private LocalDateTime publish_time;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 被通知单位
     */
    private String forward_to;

    /**
     * 状态： 0-未通知 1-已通知
     */
    private Integer status;

}
