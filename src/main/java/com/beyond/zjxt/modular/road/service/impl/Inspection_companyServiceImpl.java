package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Cost_evaluation_company_road_hazard;
import com.beyond.zjxt.modular.road.entity.Inspection_company;
import com.beyond.zjxt.modular.road.mapper.Cost_evaluation_company_road_hazardMapper;
import com.beyond.zjxt.modular.road.mapper.Inspection_companyMapper;
import com.beyond.zjxt.modular.road.service.Inspection_companyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方核量 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-12-06
 */
@Service
public class Inspection_companyServiceImpl extends ServiceImpl<Inspection_companyMapper, Inspection_company> implements Inspection_companyService {

    @Autowired
    private Inspection_companyMapper inspection_companyMapper ;

    @Override
    public Page<Map<String,Object>> getCompanyList(String companyName) {
        Page page = LayuiPageFactory.defaultPage();
        return inspection_companyMapper.getCompanyList( page, companyName);
    }

    @Override
    public List<Map<String, Object>> getCompanyListForSelector() {
        return inspection_companyMapper.getCompanyListForSelector();
    }

    @Override
    public List<Map<String, Object>> getByApplicationId(int applicationId) {
        return inspection_companyMapper.getByApplicationId(applicationId);
    }

    @Override
    public Integer count(Long applicationId, Long userId) {
        return this.baseMapper.count(applicationId,userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Inspection_company inspection_company){
        int res = 0 ;
        try{
            res = inspection_companyMapper.insert(inspection_company);
        }catch (Exception e){
            throw new ServiceException(BizExceptionEnum.INSERT_FAIL);
        }
        if(res <1){
            throw new ServiceException(BizExceptionEnum.INSERT_FAIL);
        }
    }

}
