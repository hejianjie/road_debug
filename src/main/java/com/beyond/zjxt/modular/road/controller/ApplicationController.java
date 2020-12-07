package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.entity.*;
import com.beyond.zjxt.core.shiro.ShiroUser;
import com.beyond.zjxt.modular.road.service.*;
import com.beyond.zjxt.modular.road.warpper.ApplicationWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 申请表 前端控制器
 * </p>
 *  <h1>
 *           sssss
 *       </h1>
 *  <h>
 *      sdads
 *
 *  </h>
 * @author lhd
 * @since 2019-11-21
 */
@Controller
@CrossOrigin

@RequestMapping("/application")
public class ApplicationController extends BaseController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private V_ApplicationInfoService vApplicationInfoService;
    @Autowired
    private Road_hazard_imgService roadHazardImgService;
    @Autowired
    private Road_hazardService road_hazardService;
    @Autowired
    private CityAuditService cityAuditService;
    @Autowired
    private Sys_userService sys_userService;
    @Autowired
    private Sys_deptService sys_deptService;
    @Autowired
    private supremoLimitmoneyService supremoLimitmoneyService;
    @Autowired
    private AppraisalService appraisalService;

    private String PREFIX = "/modular/system/audit/";

    @ApiOperation("获取一个申请")
    @GetMapping("/selectOne")
    @ResponseBody
    public Object selectOne(int applicationId) {
        return applicationService.selectOne(applicationId);
    }

    @ApiOperation("获取一个申请信息")
    @GetMapping("/getView")
    @ResponseBody
    public Object getView(int applicationId) {
        return applicationService.getView(applicationId);
    }

    @ApiOperation("根据病害Id获取一个申请")
    @GetMapping("/selectByRoadHazardId")
    @ResponseBody
    public Object selectByRoadHazardId(Integer roadHazardId) {
        return applicationService.selectByRoadHazardId(roadHazardId);
    }


    @ApiOperation("通过申请id获得当前状态")
    @GetMapping("/getStatus")
    @ResponseBody
    public int getStatus(@RequestParam(value = "applicationId") Integer applicationId) {
        return applicationService.getStatus(applicationId);
    }

    @RequestMapping("/cityDeptUnderAuditPage")
    public String cityUnderAuditPage() {
        return PREFIX + "city_under_audit.html";
    }

    @RequestMapping("/cityExecutiveUnderAuditPage")
    public String cityExecutiveUnderAuditPage() {
        return PREFIX + "city_Executive_under_audit.html";
    }

    /**
     * zjk
     *
     * @param role
     * @return
     */
    @RequestMapping("/cityDeptUnderAuditList")
    @ResponseBody
    public Object cityDeptUnderAuditList(String role) {
        System.out.println("角色" + role);
        Page<Map<String, Object>> list = applicationService.selectCityDeptUnderAudit(role);
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/cityExecutiveUnderAuditList")
    @ResponseBody
    public Object cityExecutiveUnderAuditList() {
        Page<Map<String, Object>> list = applicationService.selectCityExecutiveUnderAudit();
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * zjk
     *
     * @return
     */
    @RequestMapping("/cityAuditPage")
    public String cityAuditPage(@RequestParam("audited")Integer audited,@RequestParam("applicationId") Integer applicationId, Model model) {
        Map<String, Object> map = vApplicationInfoService.selectByApplicationId(applicationId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String applyTime = format.format(map.get("apply_time"));
        String auditorOneTime = format.format(map.get("auditor_one_time"));
        String auditorTwoTime = format.format(map.get("auditor_two_time"));
        if (map.get("city_dept_head_time")!=null){
            String deptHeadTime = format.format(map.get("city_dept_head_time"));
            map.put("city_dept_head_time", deptHeadTime);

        }
        if (map.get("city_audito_date")!=null){
            String city_audito_date = format.format(map.get("city_audito_date"));
            map.put("city_audito_date", city_audito_date);
        }
        if (map.get("supremo_audit_date")!=null){
            String supremo_audit_date = format.format(map.get("supremo_audit_date"));
            map.put("supremo_audit_date", supremo_audit_date);
        }
        System.out.println(map.get("city_audito_date"));
        map.put("apply_time", applyTime);
        map.put("auditor_one_time", auditorOneTime);
        map.put("auditor_two_time", auditorTwoTime);
        Integer status=(Integer) map.get("status");
        if (audited==1){
            super.setAttr("audited",1);
        }
        else
            super.setAttr("audited",0);
        ShiroUser user =  ShiroKit.getUser();
        super.setAttr("zzapplicationId", applicationId);
        Long userId = ShiroKit.getUser().getId();
        Long executiveId = (Long) map.get("city_executive");
        Long deptHeadId = (Long) map.get("city_dept_head");
        if (audited==0){
            if (status==5){
                List<Map<String,Object>> list = cityAuditService.selectByApplicationId(applicationId);
                if (list.size()==0){
                    super.setAttr("reffInfo","参照审核人未审核");
                }
                else{

                    for (Map<String, Object> objectMap : list) {
                        String reffTime = format.format(objectMap.get("reffaudit_time"));
                        objectMap.put("reffaudit_time", reffTime);
                    }
                    super.setAttr("reffInfo",list);
                }
                super.setAttr("roleRight", 1);//1为市级主管领导审核页面
            }
            else if (status==4){
                List<Map<String,Object>> list = cityAuditService.selectByApplicationId(applicationId);
                if (list.size()==0){
                    super.setAttr("reffInfo","参照审核人未审核");
                }
                else{

                    for (Map<String, Object> objectMap : list) {
                        String reffTime = format.format(objectMap.get("reffaudit_time"));
                        objectMap.put("reffaudit_time", reffTime);
                    }
                    super.setAttr("reffInfo",list);
                }
                super.setAttr("roleRight", 2);//2为市级部门领导审核页面
            }
            else {
                List<Map<String,Object>> list = cityAuditService.selectByApplicationId(applicationId);
                if (list.size()==0){
                    super.setAttr("reffInfo","参照审核人未审核");
                }
                else{

                    for (Map<String, Object> objectMap : list) {
                        String reffTime = format.format(objectMap.get("reffaudit_time"));
                        objectMap.put("reffaudit_time", reffTime);
                    }
                    super.setAttr("reffInfo",list);
                }
                super.setAttr("roleRight", 3);//3为市级参考人员审核页面
            }
        }
        else
        {
            List<Map<String,Object>> list = cityAuditService.selectByApplicationId(applicationId);
            if (list.size()==0){
                super.setAttr("reffInfo","参照审核人未审核");
            }
            else{

                for (Map<String, Object> objectMap : list) {
                    System.out.println(map.get("reffaudit_time"));
                    String reffTime = format.format(objectMap.get("reffaudit_time"));
                    objectMap.put("reffaudit_time", reffTime);
                }
                super.setAttr("reffInfo",list);
            }
            super.setAttr("roleRight",4);
        }

        String[] specificSize = map.get("specific_size").toString().split(",");
        String spSize=map.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        map.put("specific_size", spSize);
        System.out.println(specificSize);
        if (map.get("hazard_unit_name").equals("面积")) {
            super.setAttr("lengths", specificSize[0]);
            super.setAttr("widths", specificSize[1]);
        }
        if (map.get("hazard_unit_name").equals("体积")) {
            super.setAttr("lengths", specificSize[0]);
            super.setAttr("widths", specificSize[1]);
            super.setAttr("heights", specificSize[2]);

        }
        if (map.get("hazard_unit_name").equals("长度")) {
            super.setAttr("miles", specificSize[0]);

        }
        if (map.get("hazard_unit_name").equals("个数")) {
            super.setAttr("numbers", specificSize[0]);

        }
        model.addAttribute("application", map);
        super.setAttr("btnflag", 1);
        super.setAttr("status",status);
        System.out.println(status);
        System.out.println(map);
        System.out.println("44444444444444444444444444444444444444444");
        System.out.println(super.getPara("reffInfo"));
        System.out.println(super.getPara("roleRight"));
        System.out.println(super.getPara("audited"));
        System.out.println(model);
        System.out.println(map.get("roleRight"));
        return PREFIX + "city_audit.html";
    }

    /**
     * zjk
     * 市区参照员审核页面
     */
    @RequestMapping("cityReffAudit")
    public String cityReffAudit(@RequestParam("applicationId") Integer applicationId,@RequestParam("audited")Integer audited, Model model){
        super.setAttr("audited",audited);
        Map<String, Object> map = vApplicationInfoService.selectByApplicationId(applicationId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String applyTime = format.format(map.get("apply_time"));
        String auditorOneTime = format.format(map.get("auditor_one_time"));
        String auditorTwoTime = format.format(map.get("auditor_two_time"));
        List<Map<String,Object>> list = cityAuditService.selectByApplicationId(applicationId);
        if (list.size()==0){
            super.setAttr("reffInfo","参照审核人未审核");
        }
        else{

            for (Map<String, Object> objectMap : list) {
                String reffTime = format.format(objectMap.get("reffaudit_time"));
                objectMap.put("reffaudit_time", reffTime);
            }
            super.setAttr("reffInfo",list);
        }
        map.put("apply_time", applyTime);
        map.put("auditor_one_time", auditorOneTime);
        map.put("auditor_two_time", auditorTwoTime);
        super.setAttr("zzapplicationId", applicationId);
        String[] specificSize = map.get("specific_size").toString().split(",");
        String spSize=map.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        map.put("specific_size", spSize);
        System.out.println(specificSize);
        if (map.get("hazard_unit_name").equals("面积")) {
            super.setAttr("lengths", specificSize[0]);
            super.setAttr("widths", specificSize[1]);
        }
        if (map.get("hazard_unit_name").equals("体积")) {
            super.setAttr("lengths", specificSize[0]);
            super.setAttr("widths", specificSize[1]);
            super.setAttr("heights", specificSize[2]);

        }
        if (map.get("hazard_unit_name").equals("长度")) {
            super.setAttr("miles", specificSize[0]);

        }
        if (map.get("hazard_unit_name").equals("个数")) {
            super.setAttr("numbers", specificSize[0]);

        }
        model.addAttribute("application", map);
        return PREFIX + "cityReffAudit.html";
    }

    /**
     * zjk
     * 参照人员审核历史
     */
    @RequestMapping("/reffAuditHistoryPage")
    public String reffAuditHistoryPage(){
        return PREFIX + "ReffAuditHistory.html";
    }

    /**
     * zjk
     * 参照人员审核历史
     */
    @RequestMapping("/reffAuditHistoryList")
    @ResponseBody
    public Object reffAuditHistoryList(){
        Long userId = ShiroKit.getUser().getId();
        Page<Map<String, Object>> list = applicationService.ReffAuditHistory(userId);
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }


    /**
     * zjk
     * 市区最高领导人待审核列表页面
     */

    @RequestMapping("/supremoUnderAuditPage")
    public String supremoUnderAuditPage(){
        return  PREFIX+"supremoUnderAudit.html";
    }

    /**
     * zjk
     * 最高领导人审核页面
     *
     */
    @RequestMapping("/supremoAuditPage")
    public String supremoAuditPage(Integer applicationId,Model model){
        Map<String, Object> map = vApplicationInfoService.selectByApplicationId(applicationId);
        super.setAttr("zzapplicationId", applicationId);
        String[] specificSize = map.get("specific_size").toString().split(",");
        String spSize=map.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        map.put("specific_size", spSize);
        System.out.println(specificSize);
        if (map.get("hazard_unit_name").equals("面积")) {
            super.setAttr("lengths", specificSize[0]);
            super.setAttr("widths", specificSize[1]);
        }
        if (map.get("hazard_unit_name").equals("体积")) {
            super.setAttr("lengths", specificSize[0]);
            super.setAttr("widths", specificSize[1]);
            super.setAttr("heights", specificSize[2]);

        }
        if (map.get("hazard_unit_name").equals("长度")) {
            super.setAttr("miles", specificSize[0]);

        }
        if (map.get("hazard_unit_name").equals("个数")) {
            super.setAttr("numbers", specificSize[0]);

        }
        model.addAttribute("application", map);
        return PREFIX + "supremoAuditPage.html";
    }

    /**
     * zjk
     * 市区最高领导人待审核列表
     */
    @RequestMapping("/supremoUnderAuditList")
    @ResponseBody
    public Object supremoUnderAuditList(){
        Page<Map<String, Object>> list = applicationService.supremoUnderAuditList();
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * zjk
     * 最高领导人审核
     */
    @RequestMapping("/updateSupremoStatus")
    @ResponseBody
    public ResponseData updateSupremoStatus(String status,Integer applicationId){
        Long userId=ShiroKit.getUser().getId();
        applicationService.updateSupremoStatus(userId,status,new Date(),applicationId);
        return SUCCESS_TIP;
    }

    /**
     * zjk
     *最高领导人审核历史页面
     */
    @RequestMapping("/supremoAuditHistoryPage")
    public String supremoAuditHistoryPage(){
        return PREFIX + "supremoAuditHistory.html";
    }

    /**
     * zjk
     * 最高领导人审核历史列表
     */
    @RequestMapping("/supremoAuditHistoryList")
    @ResponseBody
    public Object supremoAuditHistoryList(){
        Page<Map<String, Object>> list = applicationService.supremoAuditHistory();
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }


    /**
     * zjk
     *
     * @param auditSuggestion
     * @param feedback
     * @param applicationId
     * @return
     */
    @RequestMapping("updateCityAuditResult")
    @ResponseBody
    public Object updateCityAuditResult(String auditSuggestion, String feedback, Integer applicationId,Integer reff) {
//        List role= ShiroKit.getUser().getRoleList();
        Long userId = ShiroKit.getUser().getId();
        Map<String, Object> map = applicationService.selectByApplicationId(applicationId);
        Integer status = (Integer) map.get("status");
        System.out.println("object对象");
        System.out.println(map.get("city_executive"));
        System.out.println(userId);
        System.out.println(map.get("city_dept_head"));
        System.out.println();
        Long executiveId = (Long) map.get("city_executive");
        Long deptHead = (Long) map.get("city_dept_head");
        System.out.println("----------------------------");
        System.out.println(executiveId);
        System.out.println("++++++++++++++++++++");
        System.out.println(deptHead);
        System.out.println("userId"+userId);
        if (reff==0){
            if (status==5) {
                System.out.println("zgzgzgzgzgzgzgzgzgzgzgzggzgzgzgzgzgz");
                //调用主管领导审核
                Date auditDate = new Date();
                String role = "市区审核人1";
                if (feedback.equals("") || feedback == null)
                    feedback = "无";
                System.out.println(feedback);
                int i = applicationService.updateCityExecutiveAuditResult(userId,role, auditSuggestion, feedback, applicationId, auditDate);
                Map<String,Object>map1=supremoLimitmoneyService.selectTopOne();
                BigDecimal limitMoney = (BigDecimal) map1.get("limit_money");
                applicationService.autoAuditAfterExeAudit(limitMoney,applicationId,new Date());
                System.out.println("i等于");
            } if (status==4) {
                System.out.println("bmbmbmbmbmbmbmbmbmmbmbmbmmbmbmbm");
                //调用部门领导
                Date auditDate = new Date();
                String role = "市区审核人1";
                if (feedback.equals("") || feedback == null)
                    feedback = "无";
                System.out.println(feedback);
                int i = applicationService.updateCityDeptAuditResult(userId,role, auditSuggestion, feedback, applicationId, auditDate);
                System.out.println("i等于" + i);
            }
            return SUCCESS_TIP;
        }
        if (reff==1){
            System.out.println("rfrfrfrfrfrfrfrfrfrfrfrfrfrfrfrf");
            Map<String, Object> checkmap = cityAuditService.selectByAppliIdAndUserId(applicationId, userId);
            if (checkmap==null){
                CityAudit cityAudit = new CityAudit();
                cityAudit.setId(null);
                cityAudit.setAudit_role(null);
                cityAudit.setUser_id(userId);
                cityAudit.setReffaudit_time(new Date());
                cityAudit.setUser_name(ShiroKit.getUser().getName());
                cityAudit.setApplicationId(applicationId);
                cityAudit.setAudit_status(auditSuggestion);
                cityAudit.setAuditfeedback(feedback);
                cityAuditService.save(cityAudit);
            }
            else {
                cityAuditService.updateStatus(auditSuggestion,new Date(),applicationId,userId);
            }
            //市级参考人员
            return SUCCESS_TIP;
        }
        return ResponseData.error(600,"重复审核");
    }

    @CrossOrigin
    @ApiOperation("添加申请")
    @PostMapping("/add")
    @ResponseBody
    public int addOne(@RequestParam("roadHazardId")int roadHazardId, @RequestParam("userId")int userId, @RequestParam("construct_type")int construct_type,
                      @RequestParam("construct_project")int construct_project, @RequestParam("construct_project_detail")int construct_project_detail,
                      @RequestParam("hazard_unit")int hazard_unit, @RequestParam("specificSize")String specificSize, @RequestParam("work_amount")float work_amount,
                      @RequestParam("unit_price")float unit_price, @RequestParam("unit_price_type")int unit_price_type, @RequestParam("work_frequency")String work_frequency,
                      @RequestParam("appraisal_cost")float appraisal_cost, @RequestParam("date")String date, @RequestParam("estimated_finish_time")int estimated_finish_time,
                      @RequestParam("dateTime")String dateTime){

        specificSize = specificSize.replaceAll("& #39;","");
        work_frequency = work_frequency.replaceAll("& #39;","");
        date = date.replaceAll("& #39;","");
        Sys_user user = sys_userService.selectOne(userId);
        Long dept_id = user.getDept_id();
        List<Integer> pidList = sys_deptService.getPid(dept_id);
        Integer[] pidArr = new Integer[pidList.size()];
        pidList.toArray(pidArr);
        int pid = 0;
        if(pidArr.length == 1){
            pid = pidArr[0];
        }
        List<Integer> auditorList1 = sys_userService.selectCountyAuditor1(dept_id);//区县1
        Integer[] auditorArr1 = new Integer[auditorList1.size()];
        auditorList1.toArray(auditorArr1);

        List<Integer> auditorList2 = sys_userService.selectCountyAuditor2(dept_id);//区县2
        Integer[] auditorArr2 = new Integer[auditorList2.size()];
        auditorList2.toArray(auditorArr2);

//        List<Integer> auditorList3 = sys_userService.selectCityAuditor1((long) pid);//市区1
//        Integer[] auditorArr3 = new Integer[auditorList3.size()];
//        auditorList3.toArray(auditorArr3);
//
//        List<Integer> auditorList4 = sys_userService.selectCityAuditor2((long) pid);//市区2
//        Integer[] auditorArr4 = new Integer[auditorList4.size()];
//        auditorList4.toArray(auditorArr4);
        int auditor_one = 0; //
        int auditor_two = 0; //
        int city_dept_head = 0; //
        int city_executive = 0; //
        if(auditorArr1.length == 1){
            auditor_one = auditorArr1[0];
        }
        if(auditorArr2.length == 1){
            auditor_two = auditorArr2[0];
        }
//        if(auditorArr3.length == 1){
//            city_dept_head = auditorArr3[0];
//        }
//        if(auditorArr4.length == 1){
//            city_executive = auditorArr4[0];
//        }
        Long date1 = Long.parseLong(dateTime);
        Date Time = new Date(date1);

        return applicationService.addOne(roadHazardId, userId, construct_type, construct_project, construct_project_detail, hazard_unit, specificSize, work_amount, unit_price, unit_price_type, work_frequency, appraisal_cost, date, estimated_finish_time,Time);
    }

    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Object updateOne(@RequestParam("roadHazardId")int roadHazardId,  @RequestParam("construct_type")int construct_type,
                      @RequestParam("construct_project")int construct_project, @RequestParam("construct_project_detail")int construct_project_detail,
                      @RequestParam("hazard_unit")int hazard_unit, @RequestParam("specificSize")String specificSize, @RequestParam("work_amount")float work_amount,
                      @RequestParam("unit_price")float unit_price, @RequestParam("unit_price_type")int unit_price_type, @RequestParam("work_frequency")String work_frequency,
                      @RequestParam("appraisal_cost")float appraisal_cost, @RequestParam("date")String date, @RequestParam("estimated_finish_time")int estimated_finish_time,
                      @RequestParam("dateTime")String dateTime){

        specificSize = specificSize.replaceAll("& #39;","");
        work_frequency = work_frequency.replaceAll("& #39;","");
        date = date.replaceAll("& #39;","");


        Long date1 = Long.parseLong(dateTime);
        Date Time = new Date(date1);




        UpdateWrapper<Application> uw=new UpdateWrapper<>();
        uw.eq("road_hazard",roadHazardId);
        uw.set("type_selection",construct_type);
        uw.set("project_name",construct_project);
        uw.set("detail_name",construct_project_detail);
        uw.set("organization_id",hazard_unit);
        uw.set("work_amount",work_amount);
        uw.set("specific_size",specificSize);
        uw.set("unit_price",unit_price);
        uw.set("unit_price_type",unit_price_type);
        uw.set("work_frequency",work_frequency);
        uw.set("appraisal_cost",appraisal_cost);
        uw.set(" estimated_finish_time", date);
        uw.set("estimated_finish_duration",estimated_finish_time);

        applicationService.update(uw);

        return ResponseData.success();
    }

    @CrossOrigin
    @PostMapping("/updateOneR")
    @ResponseBody
    public Object updateOneR(@RequestParam("roadHazardId")int roadHazardId){

        QueryWrapper<Application> qw=new QueryWrapper<>();
        qw.eq("road_hazard",roadHazardId);
        applicationService.getBaseMapper().delete(qw);

        UpdateWrapper<Road_hazard> uw=new UpdateWrapper<>();
        uw.eq("road_hazard_id",roadHazardId);
        uw.set("status",1);
        road_hazardService.update(uw);

        return ResponseData.success();
    }

    /**
     * zjk
     */
    @RequestMapping("/cityAuditHistory")
    @ResponseBody
    public Object selectCityAuditHis(String role) {
        Page<Map<String, Object>> list = applicationService.cityDeptAuditHistory(role);
        System.out.println(list);
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * zjk
     * 市区主管领导审核历史
     */

    @RequestMapping("/cityExecutiveAuditHistory")
    @ResponseBody
    public Object cityExecutiveAuditHistory(){
        Page<Map<String,Object>> list = applicationService.cityExecutiveHistory();
        Page<Map<String,Object>>wrap=new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * lhd
     */
    @RequestMapping("/fillHistory")
    @ResponseBody
    public Object fillHistory(){
        ShiroUser user =  ShiroKit.getUser();
        Long userId = user.getId();
        Page<Map<String,Object>> list = applicationService.selectHistory(userId);
        Page<Map<String,Object>>wrap=new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * zjk
     */
    @RequestMapping("/cityAuditHistoryPage")
    public String cityAuditHistoryPage() {
        return PREFIX + "city_audit_history.html";
    }

    /**
     * zjk
     */
    @RequestMapping("/cityExecutiveAuditHistoryPage")
    public String cityExecutiveAuditHistoryPage() {
        return PREFIX + "city_executive_audit_history.html";
    }

    @ApiOperation("初审")
    @PostMapping("/firstAuditor")
    @ResponseBody
    public int firstAuditor(@RequestParam("id") int firstAuditorId,
                            @RequestParam("result") String firstAuditorResult,
                            @RequestParam("applicationId") int applicationId) {
        firstAuditorResult = firstAuditorResult.replaceAll("& #39;", "");
        System.out.println(firstAuditorResult);
        System.out.println(applicationId);
        System.out.println(firstAuditorId);
        return applicationService.firstAuditor(firstAuditorId, firstAuditorResult, applicationId);
    }

    @ApiOperation("区县审核")
    @PostMapping("/countyAuditor")
    @ResponseBody
    public int firstAuditor(@RequestParam("id") int auditorId,
                            @RequestParam("result") String auditorResult,
                            @RequestParam("applicationId") int applicationId,
                            @RequestParam("role") String role) {
        auditorResult = auditorResult.replaceAll("& #39;", "");
        role = role.replaceAll("& #39;", "");
        return applicationService.countyAuditor(role, auditorId, auditorResult, applicationId);
    }

    /***
     * 一个病害区县填报页面
     * List<Map<String,Object>>list = roadHazardImgService.selectByRoadHazardId(roadHazardId);
     */
    @RequestMapping("/findByRoadHazardId/{roadHazardId}")
    public String FindByRoadHarzardId(@PathVariable int roadHazardId, Model model) {
        Map roadHazard = this.road_hazardService.selectRoadHazardByroadHazardId(roadHazardId);
        model.addAttribute("roadHazard", roadHazard);
        String spSize= roadHazard.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        model.addAttribute("specific_size",spSize);

        String detect_time= roadHazard.get("detect_time").toString();
        detect_time= (String) detect_time.subSequence(0,19);
        model.addAttribute("detect_time",detect_time);

        List<Map<String, Object>> imgList = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        model.addAttribute("hazardImgs", imgList);
        ShiroUser user = ShiroKit.getUser();
        model.addAttribute("user",user);

        Map applyDate =  this.applicationService.getApplyDate(roadHazardId);
        if(applyDate!=null){
            model.addAttribute("applyDate",applyDate);
        }else {
            model.addAttribute("applyDate","未填报");
        }

        return PREFIX + "countyFill.html";
    }

    /***
     * 一个病害区县填报页面弹框
     * List<Map<String,Object>>list = roadHazardImgService.selectByRoadHazardId(roadHazardId);
     */
    @RequestMapping("/findByRoadHazardIdUpdate/{roadHazardId}")
    public String FindByRoadHarzardIdUpdate(@PathVariable int roadHazardId, Model model) {
        Map roadHazard = this.road_hazardService.selectRoadHazardByroadHazardId(roadHazardId);
        model.addAttribute("roadHazard", roadHazard);
        String spSize= roadHazard.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        model.addAttribute("specific_size",spSize);

        String detect_time= roadHazard.get("detect_time").toString();
        detect_time= (String) detect_time.subSequence(0,19);
        model.addAttribute("detect_time",detect_time);

        List<Map<String, Object>> imgList = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        model.addAttribute("hazardImgs", imgList);
        ShiroUser user = ShiroKit.getUser();
        model.addAttribute("user",user);

        Map applyDate =  this.applicationService.getApplyDate(roadHazardId);
        if(applyDate!=null){
            model.addAttribute("applyDate",applyDate);
        }else {
            model.addAttribute("applyDate","未填报");
        }

        return PREFIX + "updateapplicationfillmsg.html";
    }

    /***
     * 通过Aid获取Rid
     */
    @RequestMapping("/aGetR")
    @ResponseBody
    public Object aGetR(Integer updateId) {
        Integer a = updateId;
        return this.applicationService.selectOne(a);
    }

    /**
     * zjk
     * 市区参照审核人员待审核列表
     */
    @RequestMapping("/ReffeAuditerUnderList")
    @ResponseBody
    public Object ReffeAuditerUnderList() {
//        Long userId=ShiroKit.getUser().getId();
        Long userId = 1L;
        System.out.println("用户信息");
        System.out.println(ShiroKit.getUser());
        Page<Map<String, Object>> list = this.applicationService.ReffeAuditerUnderList(ShiroKit.getUser().getId());
        Page<Map<String, Object>> wrap = new ApplicationWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * zjk
     * 市区参照审核人员待审核页面
     */
    @RequestMapping("/ReffeAuditerUnderPage")
    public String ReffeAuditerUnderPage() {
        return PREFIX + "reffAuditerUnderPage.html";
    }

    /***
     * 一个病害区县审核页面
     * List<Map<String,Object>>list = roadHazardImgService.selectByRoadHazardId(roadHazardId);
     */
    @RequestMapping("/findByRoadHazardIdCountyFirstTrial/{roadHazardId}")
    public String FindByRoadHarzardIdCountyFirstTrial(@PathVariable int roadHazardId,Model model){
        System.out.println("道路编号"+roadHazardId);
        Map roadHazard = this.road_hazardService.selectCountyFirstTrialByroadHazardId(roadHazardId);
        model.addAttribute("roadHazard",roadHazard);
        System.out.println(roadHazard);
        List<Map<String,Object>>imgList = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        model.addAttribute("hazardImgs",imgList);
        ShiroUser user = ShiroKit.getUser();
        model.addAttribute("user",user);

        //通过灾害ID获取填报时间
        Map applyDate =  this.applicationService.getApplyDate(roadHazardId);
        model.addAttribute("applyDate",applyDate);

        return "/modular/system/countyFirstTrial/countyFirstTrialInfor.html";
    }

    @CrossOrigin
    @ApiOperation("删除申请")
    @ResponseBody
    @PostMapping("/deleteApplication")
    public Object deleteApplication(int id) {
        applicationService.deleteApplication(id);
        return 1;
    }

    @GetMapping("/getCostEvaluationDetail")
    public String getCostEvaluationDetail(int id ,Model model) {
        Map<String,Object> detail=   appraisalService.getCostEvaluationDetail(id);
        model.addAttribute("detail",detail);

        return "/modular/system/audit/city_audit_detail.html";
    }
}

