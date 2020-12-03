package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.service.Sys_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/sys_user")
public class Sys_userController {
    private String PREFIX = "/modular/system/sysUser/";

    @Autowired
    private Sys_userService sys_userService;

    @GetMapping("getbyrole")
    @ResponseBody
    public Object getByRoleId()
    {
        String s="1203521696926789634";
        Long l=Long.valueOf(s);
        return LayuiPageFactory.createPageInfo(sys_userService.selectByRoleId(l));
    }

    @PostMapping("delete")
    @ResponseBody
    public int delete(int userId)
    {
        return sys_userService.deleteByUserId(userId);
    }

    @PostMapping("reset")
    @ResponseBody
    public int resetPassword(int userId){return sys_userService.resetPassword(userId);}

    @PostMapping("add")
    @ResponseBody
    public int addUser(String name,String account){return sys_userService.addUser(name,account);}


    @RequestMapping("/page/referenceUser")
    public String referenceUser(){return PREFIX+"userList.html";}

    @RequestMapping("/page/addReferenceUser")
    public String addReferenceUser(){return PREFIX+"userList_add.html";}
}

