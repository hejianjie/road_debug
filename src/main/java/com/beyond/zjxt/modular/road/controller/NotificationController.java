package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.beyond.zjxt.core.common.annotion.BussinessLog;
import com.beyond.zjxt.core.common.constant.dictmap.ConstructTypeDict;
import com.beyond.zjxt.core.common.constant.dictmap.NotificationDict;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.entity.Notification;
import com.beyond.zjxt.modular.road.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.beetl.ext.simulate.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 通知表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/notification")
public class NotificationController extends BaseController {

    @Autowired
    private NotificationService notificationService;
    private String PREFIX = "/modular/system/notification";

    @RequestMapping("/notification")
    public String notification() {
        return PREFIX + "/notification.html";
    }

    @RequestMapping("/notificationAdd")
    public String notificationAdd() {
        return PREFIX + "/notification_add.html";
    }

    @RequestMapping("/notificationEdit")
    public String notificationEdit() {
        return PREFIX + "/notification_edit.html";
    }

    @RequestMapping("/notificationContent")
    public String notificationContent() {
        return PREFIX + "/notification_content.html";
    }

    @RequestMapping("/notificationView")
    public String notificationView() {
        return PREFIX + "/notification_view.html";
    }

    private  static  SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @ApiOperation("获取通知列表")
    @ResponseBody
    @GetMapping("/getAllNotification")
    public Object getAllNotification(@RequestParam String title,
                                     @RequestParam(defaultValue = "") String beginTime,
                                     @RequestParam(defaultValue = "")String endTime) throws ParseException {

        Date beginDate = new Date();
        Date endDate = new Date();
        if("".equals(beginTime)){
            beginDate=null;
        }else if(beginTime!=null){
             beginDate = simpleDateFormat.parse(beginTime);
        }
        if("".equals(endTime)){
            endDate=null;
        }else if(endTime!=null){
             endDate = simpleDateFormat.parse(endTime);
        }
        Long uid = ShiroKit.getUser().getId();
        return  LayuiPageFactory.createPageInfo(notificationService.getAllNotification(uid,title,beginDate,endDate));

    }

    @ApiOperation("获取通知详情")
    @ResponseBody
    @GetMapping("/getNotificationById")
    public Object getNotificationById(@RequestParam("id") int id){
        return  notificationService.getNotificationById(id);
    }

    @ApiOperation("添加通知")
    @ResponseBody
    @PostMapping("/insertNotification")
    public Object insertNotification(@RequestParam Map<String, String> params  ) {
        Long publisher = ShiroKit.getUser().getId();
        ShiroKit.getUser().getDeptId();
        Notification notification= new Notification();
        notification.setContent(params.get("content"));
        notification.setForward_to(params.get("forward_to"));
        notification.setPublisher(publisher.toString());
        notification.setTitle(params.get("title"));
        notification.setStatus(Integer.parseInt(params.get("status")));
        notification.setPublish_time( LocalDateTime.now());
        notificationService.insertNotification(notification);
        return SUCCESS_TIP;
    }

    @ApiOperation("修改通知")
    @ResponseBody
    @PostMapping("/updateNotification")
    public Object updateNotification(@RequestParam Map<String, String> params) {
        int id=Integer.parseInt(params.get("id"));
        String title = params.get("title");
        String content = params.get("content");
        String forward_to = params.get("forward_to");
        int status =  Integer.parseInt(params.get("status"));
        Long publisher = ShiroKit.getUser().getId();

        Notification notification = new Notification();
        notification.setPublish_time(LocalDateTime.now());
        notification.setStatus(status);
        notification.setContent(content);
        notification.setTitle(title);
        notification.setForward_to(forward_to);
        notification.setPublisher(publisher.toString());
        notification.setNotification_id(id);
        notificationService.updateNotification(notification);
        return SUCCESS_TIP;
    }

    @ApiOperation("删除通知")
    @ResponseBody
    @PostMapping("/deleteNotification")
    public Object deleteNotification(int id) {
         notificationService.deleteNotification(id);
         return SUCCESS_TIP;
    }

    @ApiOperation("获取下级通知单位列表")
    @ResponseBody
    @GetMapping("/getDepartmentList")
    public Object getDepartmentList(HttpServletResponse response) {

       Long did= ShiroKit.getUser().getDeptId();
        return notificationService.getDepartmentList(did);
    }
    @ApiOperation("获取我的通知列表")
    @ResponseBody
    @GetMapping("/getMyNotification")
    public Object getMyNotification(@RequestParam String title,@RequestParam(defaultValue = "") String beginTime,@RequestParam(defaultValue = "")String endTime) throws ParseException {
        Date beginDate = new Date();
        Date endDate = new Date();
        if("".equals(beginTime)){
            beginDate=null;
        }else if(beginTime!=null){
            beginDate = simpleDateFormat.parse(beginTime);
        }
        if("".equals(endTime)){
            endDate=null;
        }else if(endTime!=null){
            endDate = simpleDateFormat.parse(endTime);
        }

        Long did = ShiroKit.getUser().getDeptId();
        return  LayuiPageFactory.createPageInfo(notificationService.getMyNotification(did,title,beginDate,endDate));
    }

}

