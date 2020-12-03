package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.AddAppraisalDTO;
import com.beyond.zjxt.modular.road.entity.Appraisal;
import com.beyond.zjxt.modular.road.service.AppraisalService;
import com.beyond.zjxt.modular.road.service.Appraisal_imgService;
import com.beyond.zjxt.modular.road.warpper.PatrolInfoWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评估表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin

@RequestMapping("/appraisal")
public class AppraisalController extends BaseController {
    @Autowired
    private AppraisalService appraisalService;
    @Autowired
    private Appraisal_imgService appraisal_imgService;

    /**
     * 新增评估
     *
     * @author wdz
     * @Date 2019/12/5 14：30 PM
     */
    @RequestMapping(value = "/addassessinfo")
    @ResponseBody
    public Integer add(AddAppraisalDTO addAppraisalDTO) {
        LocalDateTime nowtime = LocalDateTime.now();
        Appraisal appraisal = new Appraisal();
        appraisal.setAppraisal_time(nowtime);
        appraisal.setUnit(addAppraisalDTO.getSizeType());
        appraisal.setSpecific_size(addAppraisalDTO.getSpecificSize());
        appraisal.setCost_price(addAppraisalDTO.getCostPrice());
        appraisal.setDescription(addAppraisalDTO.getDescription());
        appraisal.setLatitude(addAppraisalDTO.getLatitude());
        appraisal.setLongitude(addAppraisalDTO.getLongitude());
        appraisal.setPosition(addAppraisalDTO.getPosition());
        appraisal.setHazardStatus(addAppraisalDTO.getHazardStatus());
        appraisal.setPatrolResultId(addAppraisalDTO.getPatrolResultId());
        appraisal.setApplication_id(addAppraisalDTO.getApplicationId());
        appraisal.setAppraisal_organisation_id(Long.parseLong(addAppraisalDTO.getAppraisalOrganisationId()));
        this.appraisalService.save(appraisal);
        Integer num= appraisal.getAppraisal_id();
        appraisalService.changeStatus(addAppraisalDTO.getApplicationId(), Long.parseLong(addAppraisalDTO.getAppraisalOrganisationId()));
        return num;
    }
    /**
     * 查看评估
     *
     * @author wdz
     * @Date 2019/12/5 14：30 PM
     */
    @GetMapping("/getlist")
    @ResponseBody
    public Object getList(@RequestParam("appraisal_id")Integer appraisal_id){
        Map map = this.appraisalService.getInfo(appraisal_id);
        String spSize=map.get("specificSize").toString();
        spSize=spSize.replace(',','*');
        map.put("specificSize", spSize);
        List<Map<String,Object>> list = appraisal_imgService.selectByAppraisalId(appraisal_id);
        Map<String,Object> map23 =new HashMap<>();
        map23.put("data",map);
        map23.put("AssessImgs",list);
        return map23;
    }

    /**
     *
     * @author lhd
     * @Date 2019/12/5 14：30 PM
     */
    @GetMapping("/getByApplicationId")
    @ResponseBody
    public Object getByApplicationId(@RequestParam("applicationId")int applicationId){
        Page<Map<String, Object>> list = this.appraisalService.getByApplicationId(applicationId);
        Page<Map<String, Object>> wrap = new PatrolInfoWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @ApiOperation("更改状态")
    @GetMapping("/changeStatus")
    @ResponseBody
    public Object changeStatus(@RequestParam("applicationId")int applicationId,@RequestParam("userId")Long userId){
        return appraisalService.changeStatus(applicationId,userId);
    }

    @GetMapping("/getCostEvaluationDetail")
    @ResponseBody
    public Object getCostEvaluationDetail(Integer id){
        return appraisalService.getCostEvaluationDetail(id);
//        SELECT sys_user.name as name,c.*,a.id
//        from cost_evaluation_company_road_hazard as a
//        left join sys_user on sys_user.user_id = a.third_party_id
//        left join appraisal as c on a.application_id = c.application_id and sys_user.user_id = c.appraisal_organisation_id
//        where  a.id = 24
    }
}