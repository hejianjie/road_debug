package com.beyond.zjxt.modular.road.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.entity.PatrolCar;
import com.beyond.zjxt.modular.road.service.PatrolCarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/patrolcar")
public class PatrolCarController extends BaseController {
    @Autowired
    private PatrolCarService patrolCarService;
    private String PREFIX = "/modular/system/patrolCar";

    @RequestMapping("")
    public String notification() {
        return PREFIX + "/patrolCar.html";
    }

    @RequestMapping("patrolCar_add")
    public String patrolCar_add() {
        return PREFIX + "/patrolCar_add.html";
    }

    @ApiOperation("获取车辆")
    @ResponseBody
    @GetMapping("/getCarList")
    public Object getCarList(@RequestParam(defaultValue = "") String number){
        Long did= ShiroKit.getUser().getDeptId();
        return  LayuiPageFactory.createPageInfo(patrolCarService.getCarList(did,number));
    }

    @ApiOperation("插入车辆")
    @ResponseBody
    @PostMapping("/insertCar")
    public Object insertCar(@RequestParam(value = "number") String number ){
        Long did= ShiroKit.getUser().getDeptId();

        PatrolCar patrolCar=new PatrolCar();
        patrolCar.setCarNumber(number);
        patrolCar.setDeptId(did);
        patrolCarService.insertCar(patrolCar);
        return SUCCESS_TIP;
    }


    @ApiOperation("删除车辆")
    @ResponseBody
    @PostMapping("/deleteCar")
    public Object deleteCar(@RequestParam("number") String number){
          patrolCarService.deleteCar(number);
        return SUCCESS_TIP;
    }
}
