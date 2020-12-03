package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.modular.road.service.Construct_project_detailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 建设项目细目表 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Controller
@CrossOrigin

@RequestMapping("/construct_project_detail")
public class Construct_project_detailController {
    @Autowired
    private Construct_project_detailService construct_project_detailService;

    @ApiOperation("根据项目获取细目")
    @GetMapping("/selectByProjectId")
    @ResponseBody
    public Object selectByProjectId(int constructProjectId){
        return construct_project_detailService.selectByProjectId(constructProjectId);
    };





}

