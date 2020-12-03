package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Cost_evaluation_company_road_hazard;
import com.beyond.zjxt.modular.road.service.Cost_evaluation_company_road_hazardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author YuJunMing
 * @Date 2019/12/3 17:47
 * DESCRIPTION:
 */
@Controller
@CrossOrigin

@RequestMapping("/CostEvaluationCompany")
public class CostEvaluationCompanyController extends BaseController  {
    private String PREFIX = "/modular/system/cost_evaluation_company/";

    @Autowired
    private Cost_evaluation_company_road_hazardService cost_evaluation_company_road_hazardService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "cost_evaluation_company.html";
    }

    @ApiOperation("getCompanyList")
    @GetMapping("/getCompanyList")
    @ResponseBody
    public Object getCompanyList(@RequestParam(defaultValue = "")String companyName){
        return  LayuiPageFactory.createPageInfo(cost_evaluation_company_road_hazardService.getCompanyList(companyName));
    }

    @ApiOperation("getCostEvaluationCompany")
    @GetMapping("/getCostEvaluationCompany")
    @ResponseBody
    public Object getCostEvaluationCompany(){
        return  cost_evaluation_company_road_hazardService.getCompanyListForSelector();
    }
    @ApiOperation("添加第三方和申请")
    @PostMapping("/addCostEvaluationCompany")
    @ResponseBody
    public Object addCostEvaluationCompany(@RequestParam(value = "third_party_id")Long third_party_id,@RequestParam(value = "application_id")Long application_id){
        QueryWrapper<Cost_evaluation_company_road_hazard> cost_evaluation_company_road_hazardQueryWrapper = new QueryWrapper<>();
        cost_evaluation_company_road_hazardQueryWrapper.eq("third_party_id",third_party_id).eq("application_id",application_id);
        int count = cost_evaluation_company_road_hazardService.count(cost_evaluation_company_road_hazardQueryWrapper);
        if(count<1){
            Cost_evaluation_company_road_hazard cost_evaluation_company_road_hazard = new Cost_evaluation_company_road_hazard();
            cost_evaluation_company_road_hazard.setApplication_id(application_id);
            cost_evaluation_company_road_hazard.setThird_party_id(third_party_id);
            cost_evaluation_company_road_hazard.setStatus(0);
            cost_evaluation_company_road_hazardService.insert(cost_evaluation_company_road_hazard);
        }

        return SUCCESS_TIP;
    }

    @ApiOperation("getByApplicationId")
    @GetMapping("/getByApplicationId")
    @ResponseBody
    public Object getByApplicationId(Integer applicationId){
        return cost_evaluation_company_road_hazardService.getByApplicationId(applicationId);
    }

    @ApiOperation("deleteByApplicationId")
    @PostMapping("/deleteByApplicationId")
    @ResponseBody
    public Object deleteByApplicationId(Integer id){
        return cost_evaluation_company_road_hazardService.removeById(id);
    }
}
