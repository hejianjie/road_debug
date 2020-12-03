package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Hazard_base_info;
import com.beyond.zjxt.modular.road.service.Construct_typeService;
import com.beyond.zjxt.modular.road.service.Hazard_base_infoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 病害基础信息 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Controller
@CrossOrigin
@RequestMapping("/hazard_base_info")
public class Hazard_base_infoController {

    private String PREFIX = "/modular/system/hazardBaseInfo/";

    @Autowired
    private Hazard_base_infoService hazard_base_infoService;

    @RequestMapping("")
    public String index() {
        return PREFIX+"hazardBaseInfo.html";
    }

    @RequestMapping("/hazardBaseInfo_add")
    public String add() {
        return PREFIX + "hazardBaseInfo_add.html";
    }

    @RequestMapping("/hazardBaseInfo_edit")
    public String edit() {
        return PREFIX + "hazardBaseInfo_edit.html";
    }

    @ApiOperation("获取病害名称")
    @GetMapping("/getOne")
    @ResponseBody
    public Object getOne(int id){
        return hazard_base_infoService.getById(id);
    };

    @ApiOperation("获取病害名称")
    @GetMapping("/getAll")
    @ResponseBody
    public Object getAll(){
        Map<String,Object> map = new HashMap<>();
        Object list = hazard_base_infoService.getAll();
        map.put("data",list);
        return map;
    }

    /**
     * 返回一个select框的数据
     * @param
     * @return
     */
    @RequestMapping("selectCascaderAll")
    @ResponseBody
    public Object selectCascaderAll(){
        Object List = this.hazard_base_infoService.CountyList();
        ResponseData res = new ResponseData();
        res.setData(List);
        res.setCode(0);
        res.setSuccess(true);
        res.setMessage("成功");
        return res;
    }

    @ApiOperation("获取病害名称")
    @GetMapping("/hazardBaseInfoList")
    @ResponseBody
    public Object hazardBaseInfoList(@RequestParam(defaultValue = "")String name){
        return LayuiPageFactory.createPageInfo(hazard_base_infoService.hazardBaseInfoList(name));
    };
    @ApiOperation("修改病害名称")
    @PostMapping("/update")
    @ResponseBody
    public Object update(@RequestParam(value = "id")Integer id,@RequestParam Map<String, String> params){
        String name=params.get("name");
        Hazard_base_info hazard_base_info=new Hazard_base_info();
        hazard_base_info.setInfo_id(id);
        hazard_base_info.setName(name);
       return hazard_base_infoService.updateById(hazard_base_info);
    };
    @ApiOperation("添加病害名称")
    @PostMapping("/add")
    @ResponseBody
    public Object add(@RequestParam Map<String, String> params){
        String name=params.get("constructTypeName");
        Hazard_base_info hazard_base_info=new Hazard_base_info();
        hazard_base_info.setName(name);
        return hazard_base_infoService.save(hazard_base_info);
    };
    @ApiOperation("删除病害名称")
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Map<String, String> params){
        Integer id=Integer.parseInt(params.get("constructTypeId"));
        return hazard_base_infoService.removeById(id);
    };


}

