package com.beyond.zjxt.modular.road.controller;


import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.beyond.zjxt.config.properties.GunsProperties;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.modular.road.entity.*;
import com.beyond.zjxt.modular.road.service.Appraisal_imgService;
import com.beyond.zjxt.modular.road.service.Audit_imgService;
import com.beyond.zjxt.modular.road.service.Road_hazard_imgService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.filters.CorsFilter;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * <p>
 * 巡检病害图片表 前端控制器
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Controller
@RequestMapping("/road_hazard_img")
@CrossOrigin
public class Road_hazard_imgController extends BaseController {
    @Autowired
    private Road_hazard_imgService road_hazard_imgService;
    @Autowired
    private Appraisal_imgService appraisal_imgService;
    @Autowired
    private Audit_imgService audit_imgService;

    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";


    @ApiOperation("巡查员上传照片")
    @RequestMapping(value = "/upload")
    //@RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile picture, @RequestParam("roadHazardId") int roadHazardId, HttpServletRequest request) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = "D:\\image\\";
            picture.transferTo(new File(fileSavePath + pictureName));

            Road_hazard_img road_hazard_img =new Road_hazard_img();
            road_hazard_img.setRoad_hazard_id(roadHazardId);
            road_hazard_img.setName(pictureName);
            this.road_hazard_imgService.save(road_hazard_img);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        return pictureName;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        // 构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
//        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
//        // 存放上传文件的文件夹
//        //   String format = sdf.format(new Date());
//        // 存放上传文件的文件夹
//        File file = new File(realPath);
//        if (!file.isDirectory()) {
//            // 递归生成文件夹
//            file.mkdirs();
//        }
//        System.out.println("文件的绝对路经" + file.getAbsolutePath());
//        // 获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
//        String fileName = picture.getOriginalFilename();
//
//        if (fileName != null && fileName != "") {
//            // 新的文件名
//            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileName;
//            Road_hazard_img road_hazard_img =new Road_hazard_img();
//            road_hazard_img.setRoad_hazard_id(roadHazardId);
//            road_hazard_img.setName(fileName);
//            this.road_hazard_imgService.save(road_hazard_img);
//        }
////        map.put("fileName", fileName);
//        try {
//            //构建真实的文件路径
//            File newFile = new File(file.getAbsolutePath() + File.separator + fileName);
//            // 转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
//            System.out.println("新文件的绝对路经" + request.getScheme() + newFile.getAbsolutePath());
//            picture.transferTo(newFile);
//            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + fileName;
////            map.put("path", filePath);
//            return filePath;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }
    @ApiOperation("评估上传照片")
    @RequestMapping(value = "/uploadAppraisal")
    //@RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String uploadAppraisal(@RequestParam("file") MultipartFile picture, @RequestParam("appraisalId") int appraisalId, HttpServletRequest request) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = "D:\\image\\";
            picture.transferTo(new File(fileSavePath + pictureName));

            Appraisal_img appraisal_img = new Appraisal_img();
            appraisal_img.setAppraisal_id(appraisalId);
            appraisal_img.setAppraisal_img_name(pictureName);
            this.appraisal_imgService.save(appraisal_img);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        return pictureName;
    }
    @ApiOperation("核量上传照片")
    @RequestMapping(value = "/uploadAcceptance")
    //@RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String uploadAcceptance(@RequestParam("file") MultipartFile picture, @RequestParam("acceptanceId") int acceptanceId, HttpServletRequest request) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = "D:\\image\\";
            picture.transferTo(new File(fileSavePath + pictureName));
            Audit_img audit_img = new Audit_img();
            audit_img.setAcceptance_id(acceptanceId);
            audit_img.setAudit_img_name(pictureName);
            this.audit_imgService.save(audit_img);

        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        return pictureName;
    }

}

