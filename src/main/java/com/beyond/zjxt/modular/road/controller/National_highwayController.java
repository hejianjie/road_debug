package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.modular.system.entity.NationalHighWay;
import com.beyond.zjxt.modular.system.service.NationalHighWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 国道 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/national_highway")
public class National_highwayController {
    @Autowired
    NationalHighWayService nationalHighWayService;

    @RequestMapping("/list")
    @ResponseBody
    public List<NationalHighWay> getList() {
        List<NationalHighWay> list = nationalHighWayService.list();
        return list;
    };
}

