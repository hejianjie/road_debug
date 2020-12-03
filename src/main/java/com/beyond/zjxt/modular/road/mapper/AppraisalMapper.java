package com.beyond.zjxt.modular.road.mapper;

import com.beyond.zjxt.modular.road.entity.Appraisal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * 评估表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Mapper
public interface AppraisalMapper extends BaseMapper<Appraisal> {
    Map<String,Object> getInfo(@Param("appraisal_id")Integer appraisal_id);

    Page<Map<String,Object>> getByApplicationId(@Param("page")Page page,@Param("applicationId")int applicationId);

    int changeStatus(@Param("applicationId")int applicationId,@Param("userId")Long userId);

    Map<String, Object> getCostEvaluationDetail(@Param("id")Integer id);
}
