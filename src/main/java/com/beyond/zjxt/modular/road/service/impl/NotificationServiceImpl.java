package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Notification;
import com.beyond.zjxt.modular.road.mapper.NotificationMapper;
import com.beyond.zjxt.modular.road.service.NotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public Page<Map<String, Object>> getAllNotification(long uid,String title,Date beginTime,Date endTime){
        Page page = LayuiPageFactory.defaultPage();
        return notificationMapper.getAllNotification(page,uid,title,beginTime,endTime);
    }

    @Override

    public void insertNotification(Notification notification) {
        int res = 0 ;
        res = notificationMapper.insert(notification);
        if(res <1){
            throw new ServiceException(BizExceptionEnum.INSERT_FAIL);
        }
    }

    @Override
    public void updateNotification(Notification notification) {
        UpdateWrapper<Notification> notificationUpdateWrapper = new UpdateWrapper<>();//创建条件生成器
        notificationUpdateWrapper.eq("notification_id",notification.getNotification_id());//条件：notification_id = id
        int res = 0 ;
        res = notificationMapper.update(notification,notificationUpdateWrapper);
        if(res <1){
            throw new ServiceException(BizExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void deleteNotification(int id) {
        int res = 0 ;
         res =  notificationMapper.deleteById(id);;
        if(res<1){
            throw new ServiceException(BizExceptionEnum.DELETE_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String, Object>> getDepartmentList(Long did) {
        List<Map<String, Object>> mapList = notificationMapper.getDepartmentList(did);
        Map<String, Object> map = notificationMapper.getCurrentDepartment(did);
        mapList.add(0,map);
        return mapList;
    }

    @Override
    public Object getNotificationById(int id) {
        return notificationMapper.getNotificationById(id);
    }

    @Override
    public Page<Map<String, Object>> getMyNotification(long did,String title,Date beginTime,Date endTime){
        Page page = LayuiPageFactory.defaultPage();
        return notificationMapper.getMyNotification(page,did,title,beginTime,endTime);
    }

}
