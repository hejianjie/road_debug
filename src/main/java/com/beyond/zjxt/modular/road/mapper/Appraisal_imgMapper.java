package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beyond.zjxt.modular.road.entity.Appraisal_img;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Appraisal_imgMapper extends BaseMapper<Appraisal_img> {
    List<Map<String,Object>> selectByAppraisalId(@Param("appraisal_id") Integer appraisal_id);
}
