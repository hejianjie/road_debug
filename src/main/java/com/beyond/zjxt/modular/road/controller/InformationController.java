package com.beyond.zjxt.modular.road.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YuJunMing
 * @Date 2019/12/6 16:46
 * DESCRIPTION:
 */
@Controller
@CrossOrigin
@RequestMapping("/information")
public class InformationController {
    private String PREFIX = "/modular/system/information/";
    /**
     * @Author YuJunMing
     * @Date 2019/11/25 12:38
     * DESCRIPTION: 路桩信息管理首页路径
     */
    @RequestMapping("/stake")
    public String stake() {
        return PREFIX + "stake.html";
    }

    @RequestMapping("/stake_add")
    public String stake_add() {
        return PREFIX + "stake_add.html";
    }

    @RequestMapping("/stake_edit")
    public String stake_edit() {
        return PREFIX + "stake_edit.html";
    }


    @RequestMapping("/roadSection")
    public String roadSection() {
        return PREFIX + "road_section.html";
    }
    @RequestMapping("/addRoadSection")
    public String addRoadSection() {
        return PREFIX + "road_section_add.html";
    }

    @RequestMapping("/editRoadSection")
    public String editRoadSection() {
        return PREFIX + "road_section_edit.html";
    }
}
