package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.Appraisal_img;

import java.util.List;
import java.util.Map;

public interface Appraisal_imgService extends IService<Appraisal_img> {
    List<Map<String,Object>> selectByAppraisalId(Integer appraisal_id);
}
