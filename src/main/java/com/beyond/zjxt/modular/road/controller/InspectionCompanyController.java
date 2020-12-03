package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Inspection_company;
import com.beyond.zjxt.modular.road.service.Inspection_companyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 第三方核量 前端控制器
 * </p>
 *
 * @author yujm
 * @since 2019-12-07
 */
@Controller
@CrossOrigin
@RequestMapping("/inspectionCompany")
public class InspectionCompanyController extends BaseController {

    private String PREFIX = "/modular/system/inspection_company/";

    @Autowired
    private Inspection_companyService inspectionCompanyService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "inspection_company.html";
    }

    @ApiOperation("getCompanyList")
    @GetMapping("/getCompanyList")
    @ResponseBody
    public Object getCompanyList(@RequestParam(defaultValue = "")String companyName){
        return  LayuiPageFactory.createPageInfo(inspectionCompanyService.getCompanyList(companyName));
    }

    @ApiOperation("getInspectionCompany")
    @GetMapping("/getInspectionCompany")
    @ResponseBody
    public Object getInspectionCompany(){
        return  inspectionCompanyService.getCompanyListForSelector();
    }
    @ApiOperation("添加第三方和申请")
    @PostMapping("/addInspectionCompany")
    @ResponseBody
    public Object addInspectionCompany(@RequestParam(value = "third_party_id")Long third_party_id,@RequestParam(value = "application_id")Long application_id){

        if(inspectionCompanyService.count(application_id,third_party_id)>0){
            return 0;
        }
        Inspection_company inspectionCompany = new Inspection_company();
        inspectionCompany.setApplication_id(application_id);
        inspectionCompany.setThird_party_id(third_party_id);
        inspectionCompany.setStatus(0);
        inspectionCompanyService.insert(inspectionCompany);

        return SUCCESS_TIP;
    }

    @ApiOperation("getByApplicationId")
    @GetMapping("/getByApplicationId")
    @ResponseBody
    public Object getByApplicationId(int applicationId){
        return inspectionCompanyService.getByApplicationId(applicationId);
    }

}

