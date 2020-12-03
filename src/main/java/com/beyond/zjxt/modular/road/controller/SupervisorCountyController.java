package com.beyond.zjxt.modular.road.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.log.LogObjectHolder;
import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.beyond.zjxt.modular.road.service.Road_userService;
import com.beyond.zjxt.modular.road.service.SupervisorService;
import com.beyond.zjxt.modular.system.warpper.SupervisorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/supervisorCounty")
public class SupervisorCountyController  extends BaseController {

    @Autowired
    private SupervisorService supervisorService;
    @Autowired
    private Road_userService roadUserService;

    private String PREFIX = "/modular/system/supervisorCounty/";
    /***
     * 跳转到巡查单位管理页面
     * @return
     */
    @RequestMapping("")
    public String supervisorIndex(){
        return PREFIX+"supervisorCounty.html";
    }
    /***
     * 跳转到巡查单位养护站添加页面,传递上级的parentId，这个是取登录角色的organizationId作为parentId
     * @return
     */
    @RequestMapping("/supervisorCounty_add")
    public String supervisorAdd(Model model){
        int organizationId = 2;
        Supervisor supervisor = this.supervisorService.selectSupervisorById(organizationId);
        model.addAllAttributes(BeanUtil.beanToMap(supervisor));
        LogObjectHolder.me().set(supervisor);
        return PREFIX+"supervisorCounty_add.html";
    }
    /***
     * 跳转到巡查单位养护区编辑页面
     *
     * @return
     */
    @RequestMapping("/supervisorCounty_edit/{supervisorId}")
    public String supervisorEdit(@PathVariable int supervisorId, Model model){
        Supervisor supervisor = this.supervisorService.selectSupervisorById(supervisorId);
        model.addAllAttributes(BeanUtil.beanToMap(supervisor));
        LogObjectHolder.me().set(supervisor);
        return PREFIX + "supervisorCounty_edit.html";
    }
    @RequestMapping(value = "/supervisorCountyList")
    @ResponseBody
    public Object selectSupervisorCountyListByParentId(String supervisorNameCondition){
       //这里应该从登陆的县区角色里面取它的organizationId,作为被添加的下级的parentId
        int organizationId = 2;//用户是存在session然乎后台取即可
        Page<Map<String,Object>> list = this.supervisorService.selectSupervisorCountyListByParentId(supervisorNameCondition,organizationId);
        Page<Map<String,Object>> wrap = new SupervisorWrapper(list).wrap();
        return  LayuiPageFactory.createPageInfo(wrap);
    }
    /**
     * 新增添加巡查养护站单位信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/supervisorCountyAdd")
    @ResponseBody
    public Object add(Supervisor supervisor) {
        this.supervisorService.addSupervisorCounty(supervisor);
        return SUCCESS_TIP;
    }
    /**
     * 修改巡查单位养护单位
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseData update(Supervisor supervisor) {
        this.supervisorService.updateSupervisor(supervisor);
        return SUCCESS_TIP;
    }

    /**
     * 删除国道
     *删除的时候是否能删除监管人，如果这个负责人负责的有两个那么就不能删除该负责人，否则需要删除
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam int supervisorId) {
        Supervisor supervisor = this.supervisorService.selectSupervisorById(supervisorId);
        List<Supervisor> supervisorList1 = this.supervisorService.selectSupervisorByUserId(supervisor.getUserId());
        if(supervisorList1.size()==1){
            roadUserService.removeById(supervisor.getUserId());
        }
        this.supervisorService.removeSupervisorById(supervisorId);
        return SUCCESS_TIP;
    }
}
