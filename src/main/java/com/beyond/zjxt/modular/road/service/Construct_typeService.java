package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Construct_type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
public interface Construct_typeService extends IService<Construct_type> {
    Object getAll();
    /**
     * 获取所有建设性质列表,并且可以根据建设性质名查询获取列表
     */
    Page<Map<String, Object>> ConstructList(String constructTypeNameCondition);

    /***
     * 添加建设性质
     * @param constructType
     */
    public void addConstructType(Construct_type constructType);

    /***
     * 编辑建设性质
     * @param constructType
     */
    public void editConstructType(Construct_type constructType);

    /***
     * 注意建设性质名称的唯一性的判断
     */



}
