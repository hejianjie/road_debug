package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface NotificationMapper extends BaseMapper<Notification> {

    Page<Map<String, Object>> getAllNotification(@Param(("Page")) Page page,@Param(("uid")) long uid, @Param(("title")) String title,@Param(("beginTime")) Date beginTime, @Param(("endTime")) Date endTime);
    int insertNotification(@Param("title")String title,@Param("content") String content,@Param("publisher") Long publisher,@Param("forward_to") String forward_to,@Param("status") int status);
    int updateNotification(@Param("id")int id,@Param("title")String title, @Param("content")String content,@Param("publisher") Long publisher,@Param("forward_to") String forward_to,@Param("status") int status);
    int deleteNotification(@Param("id") int id);
    List<Map<String, Object>> getDepartmentList(@Param("did") long did);
    Map<String, Object> getNotificationById(@Param("id") int id);
    Page<Map<String, Object>> getMyNotification(@Param(("Page")) Page page,@Param("did") long did,@Param("title")  String title,@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
    Map<String, Object> getCurrentDepartment(@Param("did") long did);
}
