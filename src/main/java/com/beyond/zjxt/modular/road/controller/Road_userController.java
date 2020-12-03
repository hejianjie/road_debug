package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.service.Road_userService;
import com.beyond.zjxt.modular.road.service.Unit_price_typeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-22
 */
@Controller
@CrossOrigin
@RequestMapping("/sys/road_user")
public class Road_userController {
    @Autowired
    private Road_userService road_userService;

    private String PREFIX = "/modular/system/roadUser/";


    @ApiOperation("根据id获取用户")
    @GetMapping("/selectOne")
    @ResponseBody
    public Object selectOne(int id){
        return road_userService.selectOne(id);
    };



    @RequestMapping("")
    public String roadUser(){
        return PREFIX+"road_user.html";
    }
    @RequestMapping("/info/addInspector")
    public String addRoadUser(){
        return PREFIX+"road_user_add.html";
    }
    @RequestMapping("/info/editInspector")
    public String editRoadUser(){
        return PREFIX+"road_user_edit.html";
    }

    @GetMapping("/selectAllInspector")
    @ResponseBody
    public Object selectAllInspector()
    {
        return LayuiPageFactory.createPageInfo(road_userService.selectAllInspector());
    }

    @GetMapping("/selectInspector")
    @ResponseBody
    public Object selectInspector(Integer organizationId)
    {
        return LayuiPageFactory.createPageInfo(road_userService.selectInspectorByOrganizationId(organizationId));
    }

    @PostMapping("/freezeInspector")
    @ResponseBody
    public int freezeInspector(Integer roadUserId)
    {
        return road_userService.updateInspectorStatus(roadUserId);
    }

    @PostMapping("/thawInspector")
    @ResponseBody
    public int thawInspector(Integer roadUserId)
    {
        return road_userService.thawInspector(roadUserId);
    }

    @PostMapping("/deleteInspector")
    @ResponseBody
    public int deleteInspector(Integer roadUserId)
    {
        return road_userService.deleteInspector(roadUserId);
    }

    @PostMapping("/addInspector")
    @ResponseBody
    public int addInspector(String roadUserName,Integer gender,Integer age,String telephone,Integer organizationId,String account)
    {
        String password=telephone.substring(telephone.length()-6,telephone.length());
        return road_userService.addInspector(roadUserName,gender,age,telephone,organizationId,account,password);
    }

    @PostMapping("/updateInspector")
    @ResponseBody
    public int updateInspector(String roadUserName,Integer gender,Integer age,String telephone,Integer organizationId,String account,Integer inspectorId)
    {
        return road_userService.updateInspector(roadUserName,gender,age,telephone,organizationId,account,inspectorId);
    }

    @GetMapping("/selectOneInspector")
    @ResponseBody
    public Object selectOneInspector(Integer inspectorId)
    {
        return road_userService.selectInspectorById(inspectorId);
    }

    @PostMapping("/judgeAccount1")
    @ResponseBody
    public int judgeAccount1(String account)
    {
        System.out.println(1);
        int a=road_userService.selectInspectorByAccount(account);
        return a;
    }

    @PostMapping("/judgeAccount2")
    @ResponseBody
    public int judgeAccount2(String account,int inspectorId)
    {
        System.out.println(2);
        return road_userService.selectInspectorByAccountAndId(account,inspectorId);
    }

    @PostMapping("/resetInspectorPassword")
    @ResponseBody
    public int resetInspectorPassword(Integer roadUserId)
    {
        return road_userService.resetInspectorPassword(roadUserId);
    }





}

