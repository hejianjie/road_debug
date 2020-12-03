package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.entity.supremoLimitmoney;
import com.beyond.zjxt.modular.road.service.ApplicationService;
import com.beyond.zjxt.modular.road.service.supremoLimitmoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @Author :zjk
 * @Date :Create in 20:46 2019-12-06
 * @Description
 **/
@Controller
@CrossOrigin
@RequestMapping("/supremoLimitMoney")
public class supremoLimitMoney extends BaseController {
    @Autowired
    private supremoLimitmoneyService supremoLimitmoneyService;
    @Autowired ApplicationService applicationService;

    private String PREFIX = "/modular/system/audit/";


    @RequestMapping("/updateOrInsert")
    @ResponseBody
    public ResponseData updateOrInsert(BigDecimal money){
        Long userId= ShiroKit.getUser().getId();
        Map<String,Object>map=supremoLimitmoneyService.selectTopOne();
        System.out.println("---------------------------------------");
        System.out.println(map);
        if (map==null){
            supremoLimitmoney supremoLimitmoney = new supremoLimitmoney();
            supremoLimitmoney.setCity_supremo_id(userId);
            supremoLimitmoney.setLimit_money(money);
            supremoLimitmoneyService.save(supremoLimitmoney);
            applicationService.supremoAutoAudit(userId,new Date(),money);
        }
        else {
            supremoLimitmoneyService.updateLimitMoney(money,userId);
            applicationService.supremoAutoAudit(userId,new Date(),money);
        }
        return SUCCESS_TIP;
    }

    /**
     * zjk
     * 设置自动审核金额页面
     */

    @RequestMapping("autoAuditPage")
    public String autoAuditPage(Model model){
        Map<String,Object>map=supremoLimitmoneyService.selectTopOne();
        BigDecimal money=(BigDecimal) map.get("limit_money");
        super.setAttr("money",money.toString());
        System.out.println(money);
        return PREFIX+"supremoSetMoneyLimit.html";
    }
}
