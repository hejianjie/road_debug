package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Construct_type;
import com.beyond.zjxt.modular.road.mapper.Construct_typeMapper;
import com.beyond.zjxt.modular.road.service.Construct_typeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhd
 * @since 2019-11-21
 */
@Service
public class Construct_typeServiceImpl extends ServiceImpl<Construct_typeMapper, Construct_type> implements Construct_typeService {

    @Autowired
    private Construct_typeMapper construct_typeMapper;

    @Override
    public Object getAll() {
        return construct_typeMapper.getAll();
    }

    /**
     * 获取所有建设性质列表,并且可以根据建设性质名查询获取列表
     */
    public Page<Map<String, Object>> ConstructList( String constructTypeNameCondition){
        Page  page = LayuiPageFactory.defaultPage();
        return this.baseMapper.ConstructList(page,constructTypeNameCondition);
    }

    @Override
    public void addConstructType(Construct_type constructType) {

        if (ToolUtil.isOneEmpty(constructType, constructType.getConstructTypeName())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //判断是否即将要添加的建设性质名称是否已经存在
        if(this.baseMapper.selectByConstructName(constructType.getConstructTypeName())!=null)
        {
            throw new ServiceException(BizExceptionEnum.CONSTRUCT_NAME);
        }
        this.save(constructType);
    }

    @Override
    public void editConstructType(Construct_type constructType) {
        if (ToolUtil.isOneEmpty(constructType, constructType.getConstructTypeName())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //判断之前的建设性质表里面是否已经存在这个建设性质名称，原值和旧值比较
      Construct_type old = this.baseMapper.selectById(constructType.getConstructTypeId());
       if(!old.getConstructTypeName().equals(constructType.getConstructTypeName())){
           if(this.baseMapper.selectByConstructName(constructType.getConstructTypeName())!=null)
           {
               throw new ServiceException(BizExceptionEnum.CONSTRUCT_NAME);
           }
       }
        this.updateById(constructType);
    }



}
