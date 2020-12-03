package com.beyond.zjxt.modular.road.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.core.shiro.ShiroUser;
import com.beyond.zjxt.modular.road.entity.Acceptance;
import com.beyond.zjxt.modular.road.entity.SaveAcceptanceDTO;
import com.beyond.zjxt.modular.road.entity.User;
import com.beyond.zjxt.modular.road.service.*;
import com.beyond.zjxt.modular.road.warpper.PatrolInfoWrapper;
import com.beyond.zjxt.modular.road.warpper.PendingQuantityWrapper;
import com.beyond.zjxt.modular.road.warpper.RoadHazardWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 验收核算表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/acceptance")
public class AcceptanceController {
    @Autowired
    private AcceptanceService acceptanceService;
    @Autowired
    private Road_hazardService road_hazardService;
    @Autowired
    private Road_hazard_imgService roadHazardImgService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private Audit_imgService audit_imgService;
    @ApiOperation("获取一条数据")
    @GetMapping("/selectOne")
    @ResponseBody
    public Object selectOne(int acceptanceId){ return acceptanceService.selectOne(acceptanceId);
    };

    @ApiOperation("初审")
    @PostMapping("/firstAuditor")
    @ResponseBody
    public int firstAuditor(@RequestParam("id")int userId,
                            @RequestParam("result")int result,
                            @RequestParam("acceptanceId")int acceptanceId,
                            @RequestParam("applicationId")int applicationId,
                            @RequestParam("status")int status){
        applicationService.changeStatus(status,applicationId);
        acceptanceService.addStatus(acceptanceId);
        return acceptanceService.firstAuditor(userId,result,acceptanceId);
    }
    @ApiOperation("二审")
    @PostMapping("/secondAuditor")
    @ResponseBody
    public int secondAuditor(@RequestParam("id")int userId,
                            @RequestParam("result")int result,
                            @RequestParam("acceptanceId")int acceptanceId,
                            @RequestParam("applicationId")int applicationId,
                            @RequestParam("status")int status){
        applicationService.changeStatus(status,applicationId);
        acceptanceService.addStatus(acceptanceId);
        return acceptanceService.secondAuditor(userId,result,acceptanceId);
    }
    /**
     * 市部门领导查看核量列表页面
     */
    @RequestMapping("/PendingQuantityIndex")
    public String selectPendingQuantityIndex(){
        return "/modular/system/PendingQuantity/selectPendingQuantity.html";
    }


    /**
     * 市部门领导查看核量列表页面
     */
    @RequestMapping("/acceptanceList")
    public String acceptanceList(){
        return "/modular/system/PendingQuantity/acceptanceList.html";
    }


    /***
     *
     *
     * 区县部门领导审核病害接口,该登录账号属于哪个部门
     */
    @RequestMapping(value = "/selectPendingQuantityList")
    @ResponseBody
    public Object selectPendingQuantityList(){
        ShiroUser user =  ShiroKit.getUser();
        Page<Map<String, Object>> list = this.acceptanceService.selectPendingQuantity(user.getDeptId());
        Page<Map<String, Object>> wrap = new PendingQuantityWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
    /**
     * 区县领导查看核量列表页面
     */
    @RequestMapping("/PendingQuantityCountyIndex")
    public String selectPendingQuantityCountyIndex(){
        return "/modular/system/PendingQuantity/selectPendingQuantityCounty.html";
    }
    /***
     * 区县部门领导审核病害接口,该登录账号属于哪个部门
     */
    @RequestMapping(value = "/selectPendingQuantityCountyList")
    @ResponseBody
    public Object selectPendingQuantityCountyList(){
        ShiroUser user =  ShiroKit.getUser();
        System.out.println("部门id"+user.getDeptId());
        Page<Map<String, Object>> list = this.acceptanceService.selectPendingQuantityCounty(user.getDeptId(),user.getRoleList().get(0));
        Page<Map<String, Object>> wrap = new PendingQuantityWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
    /**
     * 查看核量列表页面
     */
    @RequestMapping("/FindAudit/{acceptanceId}/{roadHazardId}")
    public String selectPendingQuantityIndex(@PathVariable String acceptanceId,@PathVariable int roadHazardId, Model model){
        System.out.println(roadHazardId);
        Map roadHazard = road_hazardService.selectRoadHazardByroadHazardId(roadHazardId);
        model.addAttribute("roadHazard",roadHazard);
        String spSize= roadHazard.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        model.addAttribute("specific_size",spSize);
        String detect_time= roadHazard.get("detect_time").toString();
        detect_time= (String) detect_time.subSequence(0,19);
        model.addAttribute("detect_time",detect_time);

        List<Map<String,Object>> imgList = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        model.addAttribute("hazardImgs",imgList);
        int acId = Integer.valueOf(acceptanceId);
        model.addAttribute("acceptanceId",acId);
        ShiroUser user =  ShiroKit.getUser();
        model.addAttribute("userId",user.getId());
        System.out.println("-------------------------------------"+acceptanceId);
        return "/modular/system/audit/county_check.html";
    }
    /**
     * 查看核量
     * wdz
     */
    @RequestMapping("/findbyacceptanceId")
    @ResponseBody
    public Map<String,Object> find(@RequestParam("acceptanceId")Integer acceptanceId){
        Map map = this.acceptanceService.findByAcceptanceId(acceptanceId);
        List<Map<String,Object>> list = audit_imgService.selectByAcceptanceId(acceptanceId);
        Map<String,Object> map23 =new HashMap<>();
        map23.put("data",map);
        map23.put("auditImgs",list);
        return map23;
    }
    @GetMapping("/getByApplicationId")
    @ResponseBody
    public Object getByApplicationId(@RequestParam("applicationId")int applicationId){
        Page<Map<String, Object>> list = this.acceptanceService.getByApplicationId(applicationId);
        Page<Map<String, Object>> wrap = new PatrolInfoWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
    /**
     * @Author YuJunMing
     * @Date 2020/1/14 16:04
     * DESCRIPTION: 区县一级添加核量信息页面
     */
    @GetMapping("/addHl")
    public String addHlPage(@RequestParam("roadHazardId") int  roadHazardId,Model model){
        return  "/modular/system/audit/county_quantity.html";
    }

    /**
     * @Author YuJunMing
     * @Date 2020/1/15 9:24
     * DESCRIPTION: 县区一级添加核量信息
     */
    @PostMapping("add")
    @ResponseBody
    public int  add(@RequestParam("roadHazardId") Integer roadHazardId,
                      @RequestParam("hazard_unit") Integer hazard_unit,
                      @RequestParam("specificSize") String specificSize,
                      @RequestParam("work_amount") BigDecimal work_amount,
                      @RequestParam("unit_price") BigDecimal unit_price,
                      @RequestParam("cost") BigDecimal cost,
                      @RequestParam("date") String date,
                      @RequestParam("applicationId")Integer applicationId) throws ParseException {
        ShiroUser user = ShiroKit.getUser();
        System.out.println(roadHazardId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date finishDate = sdf.parse(date);
        Instant instant = finishDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime finishDateTime = instant.atZone(zoneId).toLocalDateTime();

        System.out.println(hazard_unit);//
        System.out.println(specificSize);//
        System.out.println(work_amount);//
        System.out.println(unit_price);//
        System.out.println(cost);//
        System.out.println(finishDateTime);//

        Acceptance acceptance = new Acceptance();
        acceptance.setUnit(hazard_unit);//单位
        acceptance.setUnit_price(unit_price);
        acceptance.setApplication_id(applicationId);
        acceptance.setWork_amount(work_amount);
        acceptance.setSpecific_size(specificSize);
        acceptance.setFinish_time(finishDateTime);
        acceptance.setCost_price(cost);
        LocalDateTime now = LocalDateTime.now();
        acceptance.setAudit_time(now);
        acceptance.setAccept_organisation(user.getId().intValue());
        acceptanceService.save(acceptance);
//        System.out.println(acceptance);
        return 1;

    }



    /**
     * app核量
     * wdz
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> save(SaveAcceptanceDTO saveAcceptanceDTO){
        LocalDateTime nowtime = LocalDateTime.now();
        Acceptance acceptance1 = new Acceptance();
        acceptance1.setAudit_time(nowtime);
        acceptance1.setApplication_id(saveAcceptanceDTO.getApplicationId());
        acceptance1.setUnit(saveAcceptanceDTO.getUnit());
        acceptance1.setSpecific_size(saveAcceptanceDTO.getSpecificSize());
        acceptance1.setWork_amount(saveAcceptanceDTO.getWorkAmount());
        acceptance1.setUnit_price(saveAcceptanceDTO.getUnitPrice());
        acceptance1.setCost_price(saveAcceptanceDTO.getCostPrice());
        acceptance1.setFirst_audit_result(saveAcceptanceDTO.getFirstAuditResult());
        acceptance1.setDeduction(saveAcceptanceDTO.getDeduction());
        acceptance1.setSecond_audit_price(saveAcceptanceDTO.getSecondAuditPrice());
        acceptance1.setDescription(saveAcceptanceDTO.getDescription());
        acceptance1.setRoleId(Long.parseLong(saveAcceptanceDTO.getRoleId()));
        acceptance1.setAccept_organisation(saveAcceptanceDTO.getAcceptOrganisation());
        acceptance1.setStatus(0);
        this.acceptanceService.save(acceptance1);
        Integer num = acceptance1.getAcceptance_id();
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data",num);
        acceptanceService.changeStatus(saveAcceptanceDTO.getApplicationId(),saveAcceptanceDTO.getAcceptOrganisation());
        return map;
    }

    @ApiOperation("更改状态")
    @GetMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(@RequestParam("applicationId")int applicationId,@RequestParam("userId")int userId){
        return acceptanceService.changeStatus(applicationId,userId);
    }
}

