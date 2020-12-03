package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.modular.road.service.Unit_price_typeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 单价种类表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/unit_price_type")
public class Unit_price_typeController {
    @Autowired
    private Unit_price_typeService unit_price_typeService;

    @ApiOperation("获取单价种类")
    @GetMapping("/getAll")
    @ResponseBody
    public Object getAll(){
        return unit_price_typeService.getAll();
    };
}

