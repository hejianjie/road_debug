package com.beyond.zjxt.modular.road.service;

import com.beyond.zjxt.modular.road.entity.Appraisal;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * 评估表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
public interface AppraisalService extends IService<Appraisal> {
    Map<String,Object> getInfo(Integer appraisal_id);

    Page<Map<String,Object>> getByApplicationId(int applicationId);

    int changeStatus(int applicationId,Long userId);

    Map<String,Object> getCostEvaluationDetail(Integer id);
}
