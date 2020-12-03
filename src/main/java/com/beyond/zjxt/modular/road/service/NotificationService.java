package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface NotificationService extends IService<Notification> {
    Page<Map<String, Object>> getAllNotification(long uid,String title,Date beginTime,Date endTime);
    void insertNotification(Notification notification);
    void updateNotification(Notification notification);
    void deleteNotification(int id);
    List<Map<String, Object>> getDepartmentList(Long did);
    Object getNotificationById(int id);
    Page<Map<String, Object>> getMyNotification(long did,String title,Date beginTime,Date endTime);
}
