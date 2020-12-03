package com.beyond.zjxt.modular.road.controller;


import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.shiro.ShiroKit;
import com.beyond.zjxt.modular.road.service.Road_sectionService;
import com.beyond.zjxt.modular.road.service.Sys_deptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 路段表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/road_section")
public class Road_sectionController {

    @Autowired
    private Road_sectionService road_sectionService;
    @Autowired
    private Sys_deptService sys_deptService;


    @GetMapping("/selectAll")
    @ResponseBody
    public Object selectAll()
    {
        return LayuiPageFactory.createPageInfo(road_sectionService.selectAll());
    }

    @GetMapping("/select")
    @ResponseBody
    public Object selectByHighwayName(@RequestParam("highwayId") int highwayId){
        System.out.println(highwayId);
        System.out.println(road_sectionService.selectByHighwayId(highwayId));
        return LayuiPageFactory.createPageInfo(road_sectionService.selectByHighwayId(highwayId));
    }

    @PostMapping("/add")
    @ResponseBody
    public int addRoadSection(@RequestParam("name")String name,@RequestParam("overallLength")Double overallLength,@RequestParam("beginAt")Double beginAt,@RequestParam("endAt")Double endAt,@RequestParam("supervisorId")int supervisorId,@RequestParam("nationalHighwayId")int nationalHighwayId){
        System.out.println(name);
        return road_sectionService.addRoadSection(name,overallLength,beginAt,endAt,supervisorId,nationalHighwayId);
    }

    @PostMapping("/update")
    @ResponseBody
    public int update(@RequestParam("name")String name,@RequestParam("overallLength")Double overallLength,@RequestParam("beginAt")Double beginAt,@RequestParam("endAt")Double endAt,@RequestParam("supervisorId")int supervisorId,@RequestParam("nationalHighwayId")int nationalHighwayId,@RequestParam("roadSectionId")int roadSectionId){
        System.out.println(name);
        return road_sectionService.update(name,overallLength,beginAt,endAt,supervisorId,nationalHighwayId,roadSectionId);
    }

    @PostMapping("/delete")
    @ResponseBody
    public int deleteByRoadSectionId(@RequestParam("roadSectionId")int roadSectionId){
        System.out.println(roadSectionId);
        return road_sectionService.deleteByRoadSectionId(roadSectionId);
    }

    @GetMapping("/getsupervisor1")
    @ResponseBody
    public List<Map<String, Object>> selectCountyByUserId() {
        return sys_deptService.selectCounty();
    }

    @GetMapping("/getsupervisor2")
    @ResponseBody
    public List<Map<String, Object>> selectDepartByCountyId(int countyId) {
        return sys_deptService.selectDepartByCountyId(countyId);
    }

    @GetMapping("/getOneSection")
    @ResponseBody
    public List<Map<String, Object>> getOneSection(int roadSectionId) {
        return road_sectionService.selectOneSection(roadSectionId);
    }


}

