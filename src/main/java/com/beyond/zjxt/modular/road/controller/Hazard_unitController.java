package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.modular.road.service.Hazard_base_infoService;
import com.beyond.zjxt.modular.road.service.Hazard_unitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 病害单位表 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Controller
@CrossOrigin
@RequestMapping("/hazard_unit")
public class Hazard_unitController {
    @Autowired
    private Hazard_unitService hazard_unitService;

    @ApiOperation("获取单位列表")
    @GetMapping("/getAll")
    @ResponseBody
    public Object getAll(){
        return hazard_unitService.getAll();
    };
    @ApiOperation("app获取单位")
    @GetMapping("/getList")
    @ResponseBody
    public Object getList(){
        Map<String,Object> map = new HashMap<>();
        Object list = hazard_unitService.getAll();
        map.put("data",list);
        return map;
    }
}

