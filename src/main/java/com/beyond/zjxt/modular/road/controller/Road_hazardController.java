package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.core.common.page.LayuiPageInfo;
import com.beyond.zjxt.core.shiro.ShiroUser;
import com.beyond.zjxt.modular.road.entity.PatrolResult;
import com.beyond.zjxt.modular.road.entity.Road_hazard;
import com.beyond.zjxt.modular.road.entity.VPatrolInfo;
import com.beyond.zjxt.modular.road.entity.addRoadProblemDTO;
import com.beyond.zjxt.modular.road.service.*;
import com.beyond.zjxt.modular.road.warpper.RoadHazardWrapper;
import com.beyond.zjxt.modular.system.warpper.SupervisorWrapper;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import io.swagger.annotations.ApiOperation;


/**
 * <p>
 * 巡检路段病害 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Controller
@CrossOrigin
@RequestMapping("/roadhazard")
public class Road_hazardController extends BaseController {
    private String PREFIX = "/modular/system/patrolResultIssueList/";
    @Autowired
    private Road_hazardService roadHazardService;
    @Autowired
    private Patrol_resultService patrolResultService;
    @Autowired
    private Road_hazard_imgService roadHazardImgService;
    @Autowired
    private VPatrolInfoService vPatrolInfoService;
    @Autowired
    private Hazard_base_infoService hazard_base_infoService;
    @Autowired
    private Hazard_unitService hazard_unitService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private V_ApplicationInfoService vApplicationInfoService;

    /**
     * 跳转到巡查信息首页
     *
     * @author zjk
     * @Date 2018/12/23 4:56 PM
     */
    @RequestMapping("")
    public String index(@RequestParam("patrolResultId") Integer patrolResultId, Model model ,@RequestParam(defaultValue = "0") String supervisor_Id) {
        String role ="";
        Map patrolResult = vPatrolInfoService.selectByPatrolResultId(patrolResultId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String patrolBeginTime = format.format(patrolResult.get("beginTime"));
        String patrolEndTime = format.format(patrolResult.get("endTime"));
        model.addAttribute("patrolResultInfo", patrolResult);
        model.addAttribute("patrolBeginTime", patrolBeginTime);
        model.addAttribute("patrolEndTime", patrolEndTime);
        System.out.println(patrolResultId);
        Long deptId ;

       if (0L==Long.parseLong(supervisor_Id)){
           deptId=ShiroKit.getUser().getDeptId();
           role = "养护站经理";
       }else {
           deptId=Long.parseLong(supervisor_Id);
       }
        List<Map<String, Object>> list = this.roadHazardService.list(patrolResultId,deptId);
        List imgs=new ArrayList();
        for (Map<String, Object> map : list) {
            String[] specificSize = map.get("specific_size").toString().split(",");
            String spSize= map.get("specific_size").toString();
            spSize=spSize.replace(',','*');
            map.put("specific_size", spSize);
            String decTime=format.format(map.get("detect_time"));
            map.put("detect_time", decTime);
            Integer roadHazardId=(Integer) map.get("road_hazard_id");
            List<Map<String,Object>>imgList = roadHazardImgService.selectByRoadHazardId(roadHazardId);
            map.put("hazardImgs", imgList);
            imgs.add(imgList);
        }
        System.out.println("------------------------------");
        System.out.println(imgs);
        super.setAttr("issueInfos",list);
        super.setAttr("hazardImgs",imgs);
        super.setAttr("role",role);
        System.out.println(list);
        System.out.println(list.size());
        super.setAttr("sizes",list.size());
        return PREFIX+"patrolResultIssueList.html";
    }

    /**
     * zjk
     * @param status
     * @param roadHazardId
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResponseData updateStatus(Integer status,Integer roadHazardId) {
        this.roadHazardService.updateStatus(status,roadHazardId);
        return SUCCESS_TIP;
    }

    /**
     * zjk
     */

    /**
     * zjk所有病害页面
     * @return
     */
    @RequestMapping("/allHazardPage")
    public String allHazardPage(){
        return PREFIX + "allRoadHazardList.html";
    }

    /**
     * zjk
     * 区县病害列表
     */
    @RequestMapping("/countyHazardList")
    @ResponseBody
    public Object countyHazardList(@RequestParam(value = "supervisorId" ,required = false) Integer supervisorId,
                                   @RequestParam(value = "beginTime",required = false) Date beginTime,
                                   @RequestParam(value = "endTime",required = false) Date endTime,
                                   @RequestParam(value = "roadSectionId",required = false) Integer roadSectionId,
                                   @RequestParam(value = "highwayId",required = false) Integer highwayId,
                                   @RequestParam(value = "stakeId",required = false) Integer stakeId,
                                   @RequestParam(value = "hazardStatusId",required = false)Integer hazardStatusId,
                                   @RequestParam(value = "pid",required = false)Long pid){

        Long deptId = ShiroKit.getUser().getDeptId();
        Page<Map<String, Object>> list = this.roadHazardService.selectCountyRoadHazard(supervisorId,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,hazardStatusId,pid);
        Page<Map<String, Object>> wrap = new RoadHazardWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * zjk
     * 区县病害列表
     */
    @RequestMapping("/countyHazardPage")
    public String countyHazardPage(){
        return "/modular/system/patrolResultIssueList/countyHazardPage.html";
    }

    /**
     * 单个病害详情
     * @return
     */
    @RequestMapping(value = "/singleissuedetail")
    public String singleIssueDetail(@RequestParam(value = "roadHazardId",required = true) Integer roadHazardId,Model model){
        Map<String, Object> map = roadHazardService.selectSingleHazard(roadHazardId);//病害详情
        List<Map<String, Object>> map1 = applicationService.selectByRoadHazardId(roadHazardId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] hazardSpecificSize = map.get("specific_size").toString().split(",");
        if (map.get("sizeType").equals("面积")) {
            super.setAttr("rlengths", hazardSpecificSize[0]);
            super.setAttr("rwidths", hazardSpecificSize[1]);
        }
        if (map.get("sizeType").equals("体积")) {
            super.setAttr("rlengths", hazardSpecificSize[0]);
            super.setAttr("rwidths", hazardSpecificSize[1]);
            super.setAttr("rheights", hazardSpecificSize[2]);

        }
        if (map.get("sizeType").equals("长度")) {
            super.setAttr("rmiles", hazardSpecificSize[0]);

        }
        if (map.get("sizeType").equals("个数")) {
            super.setAttr("rnumbers", hazardSpecificSize[0]);

        }
        if (map1.size()==0){
            super.setAttr("applicationId",0);
            super.setAttr("application",0);
        }
        else {
            super.setAttr("applicationId", map1.get(0).get("application_id"));
            Map<String, Object> application = vApplicationInfoService.selectByApplicationId((Integer) map1.get(0).get("application_id"));//申请表信息
            if (application.get("apply_time")!=null){
                String auditorOneTime = format.format(application.get("auditor_one_time"));
                application.put("auditor_one_time", auditorOneTime);

            }
            if (application.get("auditor_two_time")!=null){
                String auditorTwoTime = format.format(application.get("auditor_two_time"));
                application.put("auditor_two_time", auditorTwoTime);

            }
            if (application.get("apply_time")!=null){
                String applyTime = format.format(application.get("apply_time"));
                application.put("apply_time", applyTime);
            }
            super.setAttr("application",application);
            String[] specificSize = application.get("specific_size").toString().split(",");
            String spSize=map.get("specific_size").toString();
            spSize=spSize.replace(',','*');
            map.put("specific_size", spSize);
            if (map1.get(0).get("city_dept_head_status")==null){
                map1.get(0).put("city_dept_head_status", "未审核");
                map1.get(0).put("city_dept_head_fb", "无");
                map1.get(0).put("city_dept_head_time", "无");
            }
            else{
                String deptHeadTime = format.format(map1.get(0).get("city_dept_head_time"));
                map1.get(0).put("city_dept_head_time", deptHeadTime);
            }
            if (map1.get(0).get("city_executive_status")==null){
                map1.get(0).put("city_executive_status", "未审核");
                map1.get(0).put("city_executive_fb", "无");
                map1.get(0).put("city_audito_date", "无");
            }
            else {
                String city_audito_date = format.format(map1.get(0).get("city_audito_date"));
                map1.get(0).put("city_audito_date", city_audito_date);
            }
            if (map1.get(0).get("auditor_one_result")==null){
                map1.get(0).put("auditor_one_result", "未审核");
                map1.get(0).put("auditor_one_time", "无");
            }
            else{
                String auditor_one_time = format.format(map1.get(0).get("auditor_one_time"));
                map1.get(0).put("auditor_one_time", auditor_one_time);
            }
            if (map1.get(0).get("auditor_two_result")==null){
                map1.get(0).put("auditor_two_result", "未审核");
                map1.get(0).put("auditor_two_time", "无");
            }
            else {
                String auditor_two_time = format.format(map1.get(0).get("auditor_two_time"));
                map1.get(0).put("auditor_two_time", auditor_two_time);
            }
            if (map1.get(0).get("supremo_status")==null){
                map1.get(0).put("supremo_status", "未审核");
                map1.get(0).put("supremo_audit_date","无");;
            }
            else {
                String supremo_audit_date = format.format(map1.get(0).get("supremo_audit_date"));
                map1.get(0).put("supremo_audit_date", supremo_audit_date);
            }

            if (application.get("hazard_unit_name").equals("面积")) {
                super.setAttr("lengths", specificSize[0]);
                super.setAttr("widths", specificSize[1]);
            }
            if (application.get("hazard_unit_name").equals("体积")) {
                super.setAttr("lengths", specificSize[0]);
                super.setAttr("widths", specificSize[1]);
                super.setAttr("heights", specificSize[2]);

            }
            if (application.get("hazard_unit_name").equals("长度")) {
                super.setAttr("miles", specificSize[0]);

            }
            if (application.get("hazard_unit_name").equals("个数")) {
                super.setAttr("numbers", specificSize[0]);

            }
            super.setAttr("applicationInfo",map1.get(0));
        }
        System.out.println("***************************"+super.getPara("applicationId"));
        String spSize=map.get("specific_size").toString();
        spSize=spSize.replace(',','*');
        map.put("specific_size", spSize);
        List<Map<String,Object>>imgList = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        String decTime=format.format(map.get("detect_time"));
        map.put("detect_time", decTime);
        super.setAttr("issueInfo",map);
        super.setAttr("imgList",imgList);
        System.out.println("***************************");

        System.out.println(super.getPara("application"));
        System.out.println(super.getPara("applicationInfo"));
        return "/modular/system/patrolResultIssueList/roadHazardDetail.html";
    }


    @RequestMapping("/selectAllRoadHazard")
    @ResponseBody
    public Object selectAllRoadHazard(@RequestParam(value = "supervisorId" ,required = false) Integer supervisorId,
                                      @RequestParam(value = "beginTime",required = false) Date beginTime,
                                      @RequestParam(value = "endTime",required = false) Date endTime,
                                      @RequestParam(value = "roadSectionId",required = false) Integer roadSectionId,
                                      @RequestParam(value = "highwayId",required = false) Integer highwayId,
                                      @RequestParam(value = "stakeId",required = false) Integer stakeId,
                                      @RequestParam(value = "hazardStatusId",required = false)Integer hazardStatusId,
                                      @RequestParam(value = "pid",required = false)Long  pid){
        Long deptId = ShiroKit.getUser().getDeptId();
        Page<Map<String, Object>> list = this.roadHazardService.selectAllRoadHazard(supervisorId,beginTime,endTime,roadSectionId,highwayId,stakeId,deptId,hazardStatusId,pid);
        Page<Map<String, Object>> wrap = new RoadHazardWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 获取所有巡查信息列表
     *
     * @author zjk
     * @Date 2018/12/23 4:57 PM
     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(@RequestParam(value = "patrolResultId", required = true) Integer patrolResultId) {
//        Map patrolResult = vPatrolInfoService.selectByPatrolResultId(patrolResultId);
//        List<Map<String, Object>> list = this.roadHazardService.list(patrolResultId);
//        patrolResult.put("list", list);
//        return patrolResult;
//    }

    @RequestMapping(value = "/issuedetail")
    public String issueDetail(@RequestParam(value = "roadHazardId",required = true) Integer roadHazardId,Model model){
        System.out.println(roadHazardId);
        Map map = this.roadHazardService.selectById(roadHazardId);
        List<Map<String,Object>>list = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        System.out.println(map);
        System.out.println(list);
        Map patrolResult = vPatrolInfoService.selectByPatrolResultId((Integer) map.get("patrolResultId"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String patrolBeginTime = format.format(patrolResult.get("beginTime"));
        String patrolEndTime = format.format(patrolResult.get("endTime"));
        System.out.println("patrolBeginTime"+patrolBeginTime);
        System.out.println("patrolEndTime"+patrolEndTime);
        model.addAttribute("patrolEndTime", patrolEndTime);
        model.addAttribute("patrolResultInfo", patrolResult);
        model.addAttribute("patrolBeginTime", patrolBeginTime);
        model.addAttribute("issueInfo", map);
        super.setAttr("hazardImgs",list);
        return "/modular/system/issueDetailInfo/issueDetailInfo.html";
    }

    @ApiOperation("获取路面病害")
    @GetMapping("/selectOne")
    @ResponseBody
    public Object selectOne(int roadHazardId){
        return roadHazardService.getById(roadHazardId);
    };

    @RequestMapping("/selectByStatus")
    @ResponseBody
    public Object selectByStatus(){
        ShiroUser user =  ShiroKit.getUser();
        Long list1 = user.getDeptId();
        Page<Map<String, Object>> list = this.roadHazardService.selectByStatus(list1);
        Page<Map<String, Object>> wrap = new RoadHazardWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/roadHazardListPage")
    public String roadHazardListPage(){
        return "/modular/system/roadHazardList/roadHazardList.html";
    }

    @RequestMapping("/fillHistoryPage")
    public String fillHistoryPage(){
        return "/modular/system/roadHazardList/fillHistory.html";
    }

    @RequestMapping("/roadHazardPage")
    public String roadHazardPage(){
        return "/modular/system/roadHazardList/roadHazard.html";
    }


    @RequestMapping("/auditPage")
    public String auditPage(Integer roadHazardId){
        System.out.println(roadHazardId);
        super.setAttr("roadHazardId",roadHazardId);
        return  "/modular/system/patrolResultIssueList/audit.html";
    }
    /**
     * 区县部门领导审核页面
     */
    @RequestMapping("/countyFirstTrial")
    public String countyFirstTrialIndex(){
        return "/modular/system/countyFirstTrial/countyFirstTrial.html";
    }

    /**
     * 区县部门领导审核病害接口,该登录账号属于哪个部门
     */
    @RequestMapping(value = "/countyFirstTrialRoadHazardList")
    @ResponseBody
    public Object countyFirstTrialRoadHazardList(){
       ShiroUser user =  ShiroKit.getUser();
       List<Long> list1 = user.getRoleList();
        System.out.println(list1.get(0)+"-------");
       if(list1.get(0) == 13){
           Page<Map<String, Object>> list = this.roadHazardService.countyFirstTrial(user.getDeptId());
           Page<Map<String, Object>> wrap = new RoadHazardWrapper(list).wrap();
           return LayuiPageFactory.createPageInfo(wrap);
       }else if(list1.get(0) == 14){
           Page<Map<String, Object>>   list = this.roadHazardService.countySecondTrial(user.getDeptId());
           Page<Map<String, Object>> wrap = new RoadHazardWrapper(list).wrap();
           return LayuiPageFactory.createPageInfo(wrap);
       }
       return null;

    }

    /**
     * lhd
     */
    @ApiOperation("已审核列表")
    @RequestMapping(value = "/countyRoadHazard")
    @ResponseBody
    public Object countyRoadHazard(){
        ShiroUser user =  ShiroKit.getUser();
        Long list1 = user.getDeptId();
        System.out.println("sadfsafsafs"+list1);
            Page<Map<String, Object>> list = this.roadHazardService.selectRoadHazard(list1);
            Page<Map<String, Object>> wrap = new RoadHazardWrapper(list).wrap();
            return LayuiPageFactory.createPageInfo(wrap);
    }
    @ResponseBody
    @GetMapping("/checkRoadProblem")
    @CrossOrigin
    public Map checkRoadProblem(@RequestParam(value = "roadHazardId")Integer roadHazardId){
        Map map = this.roadHazardService.selectById(roadHazardId);
        String spSize=map.get("specificSize").toString();
        spSize=spSize.replace(',','*');
        map.put("specificSize", spSize);
        Object hazardName = this.hazard_base_infoService.getById((Integer) map.get("hazardStatus"));
        Object sizeTypeName = this.hazard_unitService.getById((int)map.get("sizeType"));
        List<Map<String,Object>> list = roadHazardImgService.selectByRoadHazardId(roadHazardId);
        Map<String,Object> map23 =new HashMap<>();
        map23.put("data",map);
        map23.put("hazardName",hazardName);
        map23.put("sizeTypeName",sizeTypeName);
        map23.put("hazardImgs",list);
        return map23;
    }
    /**
     * 新增路害问题
     *
     * @author wdz
     * @Date 2019/12/2 14：30 PM
     */
    @RequestMapping(value = "/addRoadProblem")
    @ResponseBody
    public Integer add(addRoadProblemDTO addRoadProblem) {
        LocalDateTime nowtime = LocalDateTime.now();
        Road_hazard roadHazard = new Road_hazard();

        roadHazard.setDescription(addRoadProblem.getDescription());
        roadHazard.setPosition(addRoadProblem.getPosition());
        roadHazard.setHazardStatus(addRoadProblem.getHazardStatus());
        roadHazard.setPosition(addRoadProblem.getPosition());
        roadHazard.setSizeType(addRoadProblem.getSizeType());
        roadHazard.setSpecificSize(addRoadProblem.getSpecificSize());
        roadHazard.setDetectTime(nowtime);
        roadHazard.setPatrolResultId(addRoadProblem.getPatrolResultId());
        roadHazard.setLongitude(addRoadProblem.getLongitude());
        roadHazard.setLatitude(addRoadProblem.getLatitude());
        roadHazard.setPotentialHazard(addRoadProblem.getPotentialHazard());
        roadHazard.setUser_id(addRoadProblem.getUserId());
        this.roadHazardService.save(roadHazard);
        Integer num= roadHazard.getRoadHazardId();
        return num;
    }
    /**
     * 删除病害信息
     * wdz
     */
    @RequestMapping(value = "/delectRoadProblem")
    @ResponseBody
    public Map<String,Object> delect(@RequestParam(value = "roadHazardId")Integer roadHazardId,@RequestParam(value = "patrolResultId")Integer patrolResultId){
        Map<String, Object> map = new HashMap<>();
        PatrolResult patrolResult = this.patrolResultService.selectById(patrolResultId);
        if(patrolResult.getEndTime()!=null){
            map.put("statusCode",201);
            map.put("msg","已结束巡查，无法删除巡检问题！");
        }else {
            this.roadHazardService.removeById(roadHazardId);
            map.put("statusCode", 200);
            map.put("msg", "删除成功");
        }

        return map;
    }
}

