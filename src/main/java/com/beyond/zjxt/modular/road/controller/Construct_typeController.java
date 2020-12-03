package com.beyond.zjxt.modular.road.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.annotion.BussinessLog;
import com.beyond.zjxt.core.common.constant.dictmap.ConstructTypeDict;
import com.beyond.zjxt.core.common.constant.dictmap.ConstructTypeDictt;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.core.log.LogObjectHolder;
import com.beyond.zjxt.modular.road.entity.Construct_type;
import com.beyond.zjxt.modular.road.service.Construct_typeService;
import com.beyond.zjxt.modular.road.warpper.ConstructTypeWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
@Controller
@CrossOrigin

@RequestMapping("/construct_type")
public class Construct_typeController extends BaseController {

    @Autowired
    private Construct_typeService constructTypeService;

    @ApiOperation("获取建设性质")
    @GetMapping("/getAll")
    @ResponseBody
    public Object getAll(){
        return constructTypeService.getAll();
    };

    private String PREFIX = "/modular/system/constructType/";


    /***
     * 跳转到建设性质页面
     * @return
     */
    @RequestMapping("")
    public String constructTypeIndex(){
        return PREFIX+"constructType.html";
    }

    /**
     * 跳转到添加建设性质页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/constructType_add")
    public String constructTypeAdd() {
        return PREFIX + "constructType_add.html";
    }
    /**
     * 跳转到编辑建设性质页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/constructType_edit/{constructTypeId}")
    public String nationalHighWayUpdate(@PathVariable int constructTypeId, Model model) {
        Construct_type constructType = this.constructTypeService.getById(constructTypeId);
        model.addAllAttributes(BeanUtil.beanToMap(constructType));
        LogObjectHolder.me().set(constructType);
        return PREFIX + "constructType_edit.html";
    }

    @RequestMapping(value = "/constructTypeList")
    @ResponseBody
    public Object nationalList(String constructTypeCondition){
        Page<Map<String,Object>> list = this.constructTypeService.ConstructList(constructTypeCondition);
        Page<Map<String,Object>> wrap = new ConstructTypeWrapper(list).wrap();
        return  LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增添加建设性质信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping(value = "/constructTypeAdd")
    @ResponseBody
    @BussinessLog(value = "新增建设性质", key = "constructTypeName", dict = ConstructTypeDictt.class)
    public Object add(Construct_type constructType) {
        this.constructTypeService.addConstructType(constructType);
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
    @BussinessLog(value = "修改建设性质", key = "constructTypeName", dict = ConstructTypeDict.class)
    public ResponseData update(Construct_type constructType) {
        this.constructTypeService.editConstructType(constructType);
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
    @BussinessLog(value = "删除建设性质", key = "constructTypeId", dict = ConstructTypeDict.class)
    public Object delete(@RequestParam int constructTypeId) {

        //缓存通知名称
        //   LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));
        this.constructTypeService.removeById(constructTypeId);
        return SUCCESS_TIP;
    }





}

