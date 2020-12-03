package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.entity.Inspection_company;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方核量 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-12-07
 */
public interface Inspection_companyService extends IService<Inspection_company> {

    Page<Map<String,Object>> getCompanyList(String companyName);

    List<Map<String,Object>> getCompanyListForSelector();

    void insert(Inspection_company inspection_company);

    List<Map<String,Object>> getByApplicationId(int applicationId);

    Integer count(Long applicationId, Long userId);
}
