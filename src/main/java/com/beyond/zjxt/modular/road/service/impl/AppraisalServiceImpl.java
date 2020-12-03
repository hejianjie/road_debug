package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Appraisal;
import com.beyond.zjxt.modular.road.mapper.AppraisalMapper;
import com.beyond.zjxt.modular.road.service.AppraisalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评估表 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class AppraisalServiceImpl extends ServiceImpl<AppraisalMapper, Appraisal> implements AppraisalService {
    @Autowired
    private AppraisalMapper appraisalMapper;

    @Override
    public Map<String, Object> getInfo(Integer appraisal_id) {
        return this.appraisalMapper.getInfo(appraisal_id);
    }

    @Override
    public Page<Map<String, Object>> getByApplicationId(int applicationId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.getByApplicationId(page,applicationId);
    }

    @Override
    public int changeStatus(int applicationId, Long userId) {
        return this.baseMapper.changeStatus(applicationId,userId);
    }

    @Override
    public Map<String, Object> getCostEvaluationDetail(Integer id) {
        return appraisalMapper.getCostEvaluationDetail(id);
    }
}
