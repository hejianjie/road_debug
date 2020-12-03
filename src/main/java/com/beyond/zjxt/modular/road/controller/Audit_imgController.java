package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.modular.road.service.Audit_imgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 审核照片 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin

@RequestMapping("/audit_img")
public class Audit_imgController {
    @Autowired
    private Audit_imgService audit_imgService;

    @ApiOperation("获取核量对应的图片地址")
    @GetMapping("/select")
    @ResponseBody
    public Object select(int acceptanceId){ return audit_imgService.select(acceptanceId);
    };
}

