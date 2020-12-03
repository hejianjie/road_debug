package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.datascope.DataScope;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.entity.Road_hazard;
import com.beyond.zjxt.modular.road.service.Road_hazardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@Controller
@CrossOrigin
@RequestMapping("/flow")
public class FlowController {

    private String PREFIX = "/modular/system/flow/";
    @Autowired
    private Road_hazardService road_hazardService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "flow.html";
    }

    @ApiOperation("getStatus")
    @GetMapping("/getStatus")
    @ResponseBody
    public int getStatus(@RequestParam("roadHazardId") Integer roadHazardId ){
        Map<String,Object> status = road_hazardService.getStatus(roadHazardId);
        return Integer.parseInt(String.valueOf(status.get("status")))+Integer.parseInt(String.valueOf(status.get("state")));
    };




}
