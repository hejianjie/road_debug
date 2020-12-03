package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Construct_type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
@Mapper
public interface Construct_typeMapper extends BaseMapper<Construct_type> {
    List<Map<String,Object>> getAll();
    /**
     * 获取所有建设性质列表,并且可以根据建设性质名查询获取列表
     */
    Page<Map<String, Object>> ConstructList(@Param("page") Page page, @Param("constructTypeNameCondition") String constructTypeNameCondition);

   public Construct_type selectByConstructName(@Param("constructTypeName") String constructTypeName);
}
