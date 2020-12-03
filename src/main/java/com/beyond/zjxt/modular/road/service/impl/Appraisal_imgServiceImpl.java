package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.modular.road.entity.Appraisal_img;
import com.beyond.zjxt.modular.road.mapper.Appraisal_imgMapper;
import com.beyond.zjxt.modular.road.service.Appraisal_imgService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Appraisal_imgServiceImpl extends ServiceImpl<Appraisal_imgMapper, Appraisal_img> implements Appraisal_imgService {
    @Override
    public List<Map<String, Object>> selectByAppraisalId(Integer appraisal_id) {
        return this.baseMapper.selectByAppraisalId(appraisal_id);
    }
}
