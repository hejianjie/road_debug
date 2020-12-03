package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.service.Patrol_resultService;
import com.beyond.zjxt.modular.road.service.VPatrolInfoService;
import com.beyond.zjxt.modular.road.warpper.PatrolInfoWrapper;
import com.beyond.zjxt.modular.road.warpper.PatrolResultWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 巡检情况表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/patrolresult")
public class PatrolResultController extends BaseController {
    private String PREFIX = "/modular/system/patrolResult/";
    @Autowired
    private Patrol_resultService patrolResultService;
    @Autowired
    private VPatrolInfoService vPatrolInfoService;
    /**
     * 跳转到巡查信息首页
     *
     * @author zjk
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "patrolResult.html";
    }

    /**
     * 获取所有巡查信息列表
     *
     * @author zjk
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "patrolResultId", required = false) Integer patrolResultId,
                       @RequestParam(value = "supervisorId" ,required = false) Integer supervisorId,
                       @RequestParam(value = "patrolerName", required = false) String patrolerName,
                       @RequestParam(value = "beginTime",required = false) Date beginTime,
                       @RequestParam(value = "endTime",required = false) Date endTime,
                       @RequestParam(value = "roadSectionId",required = false) Integer roadSectionId,
                       @RequestParam(value = "highwayId",required = false) Integer highwayId,
                       @RequestParam(value = "stakeId",required = false) Integer stakeId,
                       @RequestParam(value = "pid",required = false) Long pid
                       ) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ParsePosition pos = new ParsePosition(0);
//        ParsePosition pos1=new ParsePosition(1);
//        Date dBeginTime = formatter.parse(beginTime, pos);
//        Date dEndTime = formatter.parse(endTime, pos);
//        System.out.println(dBeginTime);
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println(dEndTime);
//        System.out.println(endTime);
        Long deptId = ShiroKit.getUser().getDeptId();
        Page<Map<String, Object>> list = this.vPatrolInfoService.list(patrolResultId,supervisorId,patrolerName,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,pid);
        Page<Map<String, Object>> wrap = new PatrolInfoWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }


    //TODO:/patrolresult/deptpatrolresult查看所有下级的巡查信息

    @GetMapping("/deptpatrolresult")
    public String deptpatrolresult(){
        return "/modular/system/deptpatrolresult/deptpatrolresult.html";
    }
    @GetMapping("/citypatrolresult")
    public String citypatrolresult(){
        return "/modular/system/citypatrolresult/citypatrolresult.html";
    }
    @RequestMapping(value = "/cityList")
    @ResponseBody
    public Object cityList(@RequestParam(value = "patrolResultId", required = false) Integer patrolResultId,
                           @RequestParam(value = "supervisorId" ,required = false) Integer supervisorId,
                           @RequestParam(value = "patrolerName", required = false) String patrolerName,
                           @RequestParam(value = "beginTime",required = false) Date beginTime,
                           @RequestParam(value = "endTime",required = false) Date endTime,
                           @RequestParam(value = "roadSectionId",required = false) Integer roadSectionId,
                           @RequestParam(value = "highwayId",required = false) Integer highwayId,
                           @RequestParam(value = "stakeId",required = false) Integer stakeId,
                           @RequestParam(value = "pid",required = false) Long pid
    ) {
        Long deptId = ShiroKit.getUser().getDeptId();
        Page<Map<String, Object>> list = this.vPatrolInfoService.cityList(patrolResultId,supervisorId,patrolerName,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,pid);
        Page<Map<String, Object>> wrap = new PatrolInfoWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping(value = "/deptList")
    @ResponseBody
    public Object deptList(@RequestParam(value = "patrolResultId", required = false) Integer patrolResultId,
                       @RequestParam(value = "supervisorId" ,required = false) Integer supervisorId,
                       @RequestParam(value = "patrolerName", required = false) String patrolerName,
                       @RequestParam(value = "beginTime",required = false) Date beginTime,
                       @RequestParam(value = "endTime",required = false) Date endTime,
                       @RequestParam(value = "roadSectionId",required = false) Integer roadSectionId,
                       @RequestParam(value = "highwayId",required = false) Integer highwayId,
                       @RequestParam(value = "stakeId",required = false) Integer stakeId,
                           @RequestParam(value = "pid",required = false) Long pid
    ) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ParsePosition pos = new ParsePosition(0);
//        ParsePosition pos1=new ParsePosition(1);
//        Date dBeginTime = formatter.parse(beginTime, pos);
//        Date dEndTime = formatter.parse(endTime, pos);
//        System.out.println(dBeginTime);
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println(dEndTime);
//        System.out.println(endTime);
        Long deptId = ShiroKit.getUser().getDeptId();
        Page<Map<String, Object>> list = this.vPatrolInfoService.deptList(patrolResultId,supervisorId,patrolerName,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,pid);
        Page<Map<String, Object>> wrap = new PatrolInfoWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @ApiOperation("获取巡查视图")
    @GetMapping("/getView")
    @ResponseBody
    public Object getView(Integer roadHazardId) {
        return patrolResultService.getView(roadHazardId);
    }
}

