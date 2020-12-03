package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Inspection_company;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方核量 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-12-06
 */
public interface Inspection_companyMapper extends BaseMapper<Inspection_company> {

    Page<Map<String,Object>> getCompanyList(@Param("Page") Page page, @Param("companyName") String companyName);

    List<Map<String,Object>> getCompanyListForSelector();

    List<Map<String,Object>> getByApplicationId(int applicationId);

    Integer count(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

}
