package com.beyond.zjxt.modular.road.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Stake;
import com.beyond.zjxt.modular.road.mapper.StakeMapper;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.service.StakeService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 桩 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@CrossOrigin
@RequestMapping("/stake")
public class StakeController {

    @Autowired
    private  StakeService stakeService;
    @Autowired
    private StakeMapper stakeMapper;

    @ApiOperation("获取路桩列表")
    @ResponseBody
    @GetMapping("/list")
    public Object getStakeList(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("rememberMe")) {
                    System.out.println(cookie.getValue());
                }
            }
        }
//        Stake stake = new Stake();
//        stake.setRoad_section_id(1);
//        stake.setName("十五号桩");
//        UpdateWrapper<Stake> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("name","一号桩");
//
//        return stakeService.saveOrUpdate(stake,updateWrapper);
        return 1;
    }

    @ApiOperation("获取路桩列表(带模糊查询)")
    @GetMapping("/selectAllStake")
    @ResponseBody
    public Object selectAllStake(@RequestParam(defaultValue = "0")Integer nationalHighwayId
                                ,@RequestParam(defaultValue = "0")Integer begin
                                ,@RequestParam(defaultValue = "99999")Integer end
                                ,@RequestParam(defaultValue = "0") Integer roadSectionId
                                ){
        return LayuiPageFactory.createPageInfo(stakeService.selectAllStake(nationalHighwayId,begin,end,roadSectionId));
    }

    @ApiOperation("获取国道列表")
    @GetMapping("/selectAllNationalHighway")
    @ResponseBody
    public Object selectAllNationalHighway(){
        return stakeService.selectAllNationalHighway();
    }

    @ApiOperation("获取相应国道的路段列表")
    @GetMapping("/selectAllRoadSection")
    @ResponseBody
    public Object selectAllRoadSection(@RequestParam(defaultValue = "") int nationalHighwayId){
        return stakeService.selectAllRoadSection(nationalHighwayId);
    }

    @ApiOperation("添加桩")
    @PostMapping("/addStake")
    @ResponseBody
    public int addStake(String stakeName,int roadSectionId,int location){
        return stakeService.addStake(stakeName,roadSectionId, location);
    };

    @ApiOperation("修改桩")
    @PostMapping("/updateStake")
    @ResponseBody
    public int updateStake(String stakeName,int roadSectionId,int stakeId,int location){
        return stakeService.updateStake(stakeName,roadSectionId,stakeId, location);
    };

    @ApiOperation("获取一个桩")
    @GetMapping("/slectStakeOne")
    @ResponseBody
    public Object slectStakeOne(int stakeId){
        return stakeService.slectStakeOne(stakeId);
    };

    @ApiOperation("删除一个桩")
    @PostMapping("/deleteStake")
    @ResponseBody
    public int deleteStake(int stakeId){
        return stakeService.deleteStake(stakeId);
    };


}

