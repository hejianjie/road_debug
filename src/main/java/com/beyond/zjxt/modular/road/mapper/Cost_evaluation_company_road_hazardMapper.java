package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Cost_evaluation_company_road_hazard;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方巡检机构负责的病害表 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-12-03
 */
public interface Cost_evaluation_company_road_hazardMapper extends BaseMapper<Cost_evaluation_company_road_hazard> {

    Page<Map<String,Object>> getCompanyList(@Param("Page") Page page,@Param("companyName") String companyName);

    List<Map<String,Object>> getCompanyListForSelector();

    List<Map<String,Object>> getByApplicationId(Integer applicationId);
}
