package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.common.page.LayuiPageInfo;
import com.beyond.zjxt.modular.road.entity.Construct_project;
import com.beyond.zjxt.modular.road.entity.Construct_project_detail;
import com.beyond.zjxt.modular.road.service.Construct_projectService;
import com.beyond.zjxt.modular.road.service.Construct_project_detailService;
import com.beyond.zjxt.modular.road.service.Construct_typeService;
import com.beyond.zjxt.modular.system.warpper.ConstructProjectWrapper;
import com.beyond.zjxt.modular.system.warpper.SupervisorWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建设项目表 前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-11-22
 */
@Controller
@CrossOrigin

@RequestMapping("/construct_project")
public class Construct_projectController extends BaseController {

    private String PREFIX = "/modular/system/constructProject/";

    @Autowired
    private Construct_projectService construct_projectService;
    @Autowired
    private Construct_project_detailService construct_project_detailService;

    @ApiOperation("获取项目列表")
    @GetMapping("/getAll")
    @ResponseBody
    public Object getAll(){
        return construct_projectService.getAll();
    };

    @RequestMapping("")
    public String index(){
      return PREFIX+"constructProject.html";
    };

    @RequestMapping("/constructProjectAdd")
    public String add(){
        return PREFIX+"constructProject_add.html";
    };
    @RequestMapping("/constructProjectAddChild")
    public String addChild(){
        return PREFIX+"constructProject_addChild.html";
    };

    @RequestMapping("/constructProjectEdit")
    public String edit(){
        return PREFIX+"constructProject_edit.html";
    };

    @ApiOperation("getConstructProjectList")
    @GetMapping("/getConstructProjectList")
    @ResponseBody
    public Object getList(){
        List<Map<String,Object>> menus = construct_projectService.getList();

        List<Map<String, Object>> menusWrap = new ConstructProjectWrapper(menus).wrap();
        LayuiPageInfo result = new LayuiPageInfo();
        result.setData(menusWrap);
        return result;
    }

    @ApiOperation("增加一个父级")
    @PostMapping("/addFather")
    @ResponseBody
    public Object addFather(@RequestParam(value = "name")String name ,@RequestParam(defaultValue = "0") int pid){
        Construct_project_detail construct_project_detail = new Construct_project_detail();
        construct_project_detail.setConstruct_project_detail_name(name);
        construct_project_detail.setConstruct_project_id(pid);
        construct_project_detailService.save(construct_project_detail);
        return SUCCESS_TIP;
    }

//    @ApiOperation("添加子级")
//    @PostMapping("/addChild")
//    @ResponseBody
//    public Object addChild(@RequestParam(value = "name") String  name ,@RequestParam(value = "pid") int pid) {
//        Construct_project_detail construct_project_detail = new Construct_project_detail();
//        construct_project_detail.setConstruct_project_detail_name(name);
//        construct_project_detail.setConstruct_project_id(pid);
//
//        construct_project_detailService.save(construct_project_detail);
//        return SUCCESS_TIP;
//    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam(value = "id") int id) {

        return construct_project_detailService.removeById(id);
    }

}

