package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.beyond.zjxt.modular.road.service.Construct_typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**

 * @author lhd
 * @since 2019-11-21
 */
@Controller
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/audit")
public class AuditController extends BaseController {

    @Autowired
    private Construct_typeService construct_typeService;

    private String PREFIX = "/modular/system/audit/";

    @RequestMapping("county")
    public String county(Model model) {
        model.addAttribute("construct_type", construct_typeService.getAll());
        return PREFIX + "county_audit.html";
    }

    /**
     * zjk
     * @param model
     * @return
     */
    @RequestMapping("/cityaudit")
    public String cityAudit(Model model){
        return PREFIX+"city_audit.html";
    }

    @RequestMapping("add")
    public String add(Model model) {
        return PREFIX + "application_add.html";
    }

    @RequestMapping("county_check")
    public String countyCheck(Model model) {
        return PREFIX + "county_check.html";
    }
}
