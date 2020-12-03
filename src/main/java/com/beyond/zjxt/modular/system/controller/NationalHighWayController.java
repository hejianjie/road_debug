package com.beyond.zjxt.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.annotion.BussinessLog;

import com.beyond.zjxt.core.common.constant.dictmap.NationalHighWayDict;

import com.beyond.zjxt.core.common.constant.dictmap.NationalHighWayDictt;
import com.beyond.zjxt.core.common.constant.factory.ConstantFactory;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;

import com.beyond.zjxt.core.log.LogObjectHolder;
import com.beyond.zjxt.modular.road.entity.CascaderDTO;
import com.beyond.zjxt.modular.system.entity.NationalHighWay;


import com.beyond.zjxt.modular.system.entity.NationalHighWayDTO;
import com.beyond.zjxt.modular.system.service.NationalHighWayService;
import com.beyond.zjxt.modular.system.warpper.NationalHighWayWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/***
 * 国道控制器
 */
@Controller
@CrossOrigin
@RequestMapping("/nationalHighWay")
public class NationalHighWayController extends BaseController {

    private String PREFIX = "/modular/system/nationalHighWay/";

    @Autowired
    private NationalHighWayService nationalHighWayService;

    /***
     * 跳转到国道页面
     * @return
     */
    @RequestMapping("")
    public String nationalHighWayIndex(){
        return PREFIX+"nationalHighWay.html";
    }

    /**
     * 跳转到添加国道页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/nationalHighWay_add")
    public String nationalHighWayAdd() {
        return PREFIX + "nationalHighWay_add.html";
    }
    /**
     * 跳转到添加国道页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/nationalHighWay_edit/{highwayId}")
    public String nationalHighWayUpdate(@PathVariable int highwayId, Model model) {
        NationalHighWay national = this.nationalHighWayService.getById(highwayId);
        model.addAllAttributes(BeanUtil.beanToMap(national));
        LogObjectHolder.me().set(national);
        return PREFIX + "nationalHighWay_edit.html";
    }

    @RequestMapping(value = "/nationalHighWayList")
    @ResponseBody
    public Object nationalList(String highwayNameCondition){
        Page<Map<String,Object>> list = this.nationalHighWayService.HighWayList(highwayNameCondition);
        Page<Map<String,Object>> wrap = new NationalHighWayWrapper(list).wrap();
        return  LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增添加国道信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/nationalHighWayAdd")
    @ResponseBody
    public Object add(NationalHighWay nationalHighWay) {
     //   NationalHighWay nationalHighWay = new NationalHighWay();
     //   nationalHighWay.setHighwayName(nationalHighWayDto.getHighwayName());
     //   nationalHighWay.setHighwayName(nationalHighWayDto.getOverallLength());
        System.out.println(nationalHighWay.getHighwayName());
        System.out.println(nationalHighWay.getOverallLength());
       this.nationalHighWayService.addNationalHighWay(nationalHighWay);
        return SUCCESS_TIP;
    }

    /**
     * 修改国道
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResponseData update(NationalHighWay nationalHighWay) {
        nationalHighWayService.editNationalHighWay(nationalHighWay);
        return SUCCESS_TIP;
    }

    /**
     * 删除国道
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam int highwayId) {

        //缓存通知名称
     //   LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

        this.nationalHighWayService.deleteNationalHighway(highwayId);

        return SUCCESS_TIP;
    }

    /**
     * 国道，路段，桩级联
     */
    @RequestMapping(value = "/highwayCascader")
    @ResponseBody
    public Object getHighwayAndStakeAnaRoadCascader(){
        List<CascaderDTO> result =  this.nationalHighWayService.HighwayAndStakeANdRoadCascader();
        ResponseData res = new ResponseData();

        res.setCode(0);
        res.setData(result);
        res.setMessage("返回成功");

        return  res;

    }

}
