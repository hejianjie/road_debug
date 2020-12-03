package com.beyond.zjxt.modular.road.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.Cost_evaluation_company_road_hazard;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方巡检机构负责的病害表 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-12-03
 */
public interface Cost_evaluation_company_road_hazardService extends IService<Cost_evaluation_company_road_hazard> {

    Page<Map<String,Object>> getCompanyList(String companyName);

    List<Map<String,Object>> getCompanyListForSelector();

    void insert(Cost_evaluation_company_road_hazard cost_evaluation_company_road_hazard);

    List<Map<String,Object>> getByApplicationId(Integer applicationId);
}
