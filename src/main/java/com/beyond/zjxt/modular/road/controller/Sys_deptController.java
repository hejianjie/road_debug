package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.core.shiro.ShiroUser;
import com.beyond.zjxt.modular.road.entity.Sys_user;
import com.beyond.zjxt.modular.road.service.Sys_deptService;
import com.beyond.zjxt.modular.road.service.Sys_userService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/sys_dept")
public class Sys_deptController {
    @Autowired
    private Sys_deptService sys_deptService;

    @ApiOperation("获取一个申请")
    @GetMapping("/getPid")
    @ResponseBody
    public Object getPid() {
        ShiroUser user =  ShiroKit.getUser();
        List<Integer> pidList = sys_deptService.getPid(user.getDeptId());
        Integer[] pidArr = new Integer[pidList.size()];
        pidList.toArray(pidArr);
        int pid = 0;
        if(pidArr.length == 1){
            pid = pidArr[0];
        }
        return pid;
    }
}

