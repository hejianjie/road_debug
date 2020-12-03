package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.annotion.BussinessLog;
import com.beyond.zjxt.core.common.annotion.Permission;
import com.beyond.zjxt.core.common.constant.dictmap.UserDict;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.node.ZTreeNode;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.system.entity.User;
import com.beyond.zjxt.modular.system.model.UserDto;
import com.beyond.zjxt.modular.system.service.RoleService;
import com.beyond.zjxt.modular.system.service.UserService;
import com.beyond.zjxt.modular.system.warpper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/DeptUser")
public class DeptUserController extends BaseController {
    private String PREFIX = "/modular/system/deptUser/";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    @RequestMapping("user_add")
    public String userAdd() {
        return PREFIX + "user_add.html";
    }

    @RequestMapping("user_edit")
    public String userEdit() {
        return PREFIX + "user_edit.html";
    }

    /**
     * 跳转到角色分配页面
     */
    @RequestMapping("/role_assign")
    public String roleAssign(@RequestParam Long userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("userId", userId);
        return PREFIX + "user_roleassign.html";
    }

    @RequestMapping("/add")
    @BussinessLog(value = "为本部门添加用户", key = "account", dict = UserDict.class)
    @ResponseBody
    public ResponseData add(@Valid UserDto user, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
        user.setDeptId(ShiroKit.getUser().getDeptId());
        this.userService.addUser(user);
        return SUCCESS_TIP;
    }



    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long deptId) {

        //拼接查询条件
        String beginTime = "";
        String endTime = "";

        if (ToolUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }
        if (ShiroKit.isAdmin()) {
            Page<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            List<Long> longList = new LinkedList<>();
            longList.add(ShiroKit.getUser().getDeptId());
            dataScope.setDeptIds(longList);
            Page<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        }
    }

    /**
     * 获取角色列表，通过用户id
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Long userId) {
        User theUser = this.userService.getById(ShiroKit.getUser().getId());
        String roleId = theUser.getRoleId();


            String[] strArray = roleId.split(",");

            //转化成Long[]
            Long[] longArray = new Long[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                longArray[i] = Long.valueOf(strArray[i]);
            }

            return this.roleService.roleTreeListUseByDept(longArray);

    }



}
