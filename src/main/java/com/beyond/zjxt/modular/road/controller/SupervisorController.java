package com.beyond.zjxt.modular.road.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.beyond.zjxt.core.common.page.LayuiPageInfo;
import com.beyond.zjxt.core.log.LogObjectHolder;
import com.beyond.zjxt.modular.road.entity.Supervisor;
import com.beyond.zjxt.modular.road.service.Road_userService;
import com.beyond.zjxt.modular.road.service.SupervisorService;
import com.beyond.zjxt.modular.system.warpper.SupervisorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理单位 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/supervisor")
public class SupervisorController extends BaseController {
    private String PREFIX = "/modular/system/supervisor/";

    @Autowired
    private SupervisorService supervisorService;
    @Autowired
    private Road_userService roadUserService;
    /***
     * 跳转到巡查单位管理页面
     * @return
     */
    @RequestMapping("")
    public String supervisorIndex(){
        return PREFIX+"supervisor.html";
    }

    @RequestMapping("/a")
    public String a(){
        return PREFIX+"a.html";
    }

    /***
     * 跳转到巡查单位添加页面,传递上级的parentId
     * @return
     */
    @RequestMapping("/supervisor_add")
    public String supervisorAdd(Model model){
        int  parentId = 1;
        String parentBodyName = "大连市管理局";
        model.addAttribute("parentId",parentId);
        model.addAttribute("parentBodyName",parentBodyName);
        return PREFIX+"supervisor_add.html";
    }

    /***
     * 跳转到巡查单位添加页面,根据巡查单位的编号这一级的supervisorId,supervisorName作为下级的parentId,parentBodyName
     * @return
     */
    @RequestMapping("/supervisorStationAdd/{supervisorId}")
    public String supervisorMaintenanceStationAdd(@PathVariable int supervisorId, Model model){
        Supervisor supervisor = this.supervisorService.selectSupervisorById(supervisorId);
        int  parentId = supervisor.getSupervisorId();
        String parentBodyName = supervisor.getSupervisorName();
        model.addAttribute("parentId",parentId);
        model.addAttribute("parentBodyName",parentBodyName);
        return PREFIX+"supervisor_add.html";
    }


    /***
     * 跳转到巡查单位编辑页面
     *
     * @return
     */
    @RequestMapping("/supervisor_edit/{supervisorId}")
    public String supervisorEdit(@PathVariable int supervisorId, Model model){
        Supervisor supervisor = this.supervisorService.selectSupervisorById(supervisorId);
        model.addAllAttributes(BeanUtil.beanToMap(supervisor));
        LogObjectHolder.me().set(supervisor);
        return PREFIX + "supervisor_edit.html";
    }


    @RequestMapping(value = "/supervisorList")
    @ResponseBody
    public Object supervisorList(@RequestParam(required = false) String supervisorNameCondition){
        System.out.println("查询条件"+supervisorNameCondition);
        List<Map<String, Object>> menus = this.supervisorService.selectSupervisorList(supervisorNameCondition);
        List<Map<String, Object>> menusWrap = new SupervisorWrapper(menus).wrap();
        LayuiPageInfo result = new LayuiPageInfo();
        result.setData(menusWrap);
        return result;
    }

    /**
     * 新增添加巡查单位信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/supervisorAdd")
    @ResponseBody
    public Object add(Supervisor supervisor) {
        this.supervisorService.addSupervisor(supervisor);
        return SUCCESS_TIP;
    }
    /**
     * 修改巡查单位
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

    /***
     * 一个下拉框的级联
     */
    @RequestMapping(value = "/all")
    @ResponseBody Object getAll(){
        Object List = this.supervisorService.CountyList();
        //  Map<String,Object> map = new HashMap<>();
        //  map.put("",List);

        //  List<Map<String, Object>> l = new ArrayList<>();
        //  l.add(map);
        //   List<Map<String, Object>> menusWrap = new SupervisorWrapper(l).wrap();
        //  LayuiPageInfo result = new LayuiPageInfo();
        //   result.setData(menusWrap)
        ResponseData res = new ResponseData();
        res.setData(List);
        res.setCode(0);
        res.setSuccess(true);
        res.setMessage("成功");


        return res;

    }

}

