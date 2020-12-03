package com.beyond.zjxt.modular.road.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beyond.zjxt.core.common.exception.BizExceptionEnum;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Cost_evaluation_company_road_hazard;
import com.beyond.zjxt.modular.road.mapper.Cost_evaluation_company_road_hazardMapper;
import com.beyond.zjxt.modular.road.service.Cost_evaluation_company_road_hazardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方巡检机构负责的病害表 服务实现类
 * </p>
 *
 * @author yu
 * @since 2019-12-03
 */
@Service
public class  Cost_evaluation_company_road_hazardServiceImpl extends ServiceImpl<Cost_evaluation_company_road_hazardMapper, Cost_evaluation_company_road_hazard> implements Cost_evaluation_company_road_hazardService {

    @Autowired
    private Cost_evaluation_company_road_hazardMapper cost_evaluation_company_road_hazardMapper;

    @Override
    public Page<Map<String,Object>> getCompanyList(String companyName) {

        Page page = LayuiPageFactory.defaultPage();
        return cost_evaluation_company_road_hazardMapper.getCompanyList( page, companyName);
    }

    @Override
    public List<Map<String, Object>> getCompanyListForSelector() {
        return cost_evaluation_company_road_hazardMapper.getCompanyListForSelector();
    }

    @Override
    public List<Map<String, Object>> getByApplicationId(Integer applicationId) {
        return cost_evaluation_company_road_hazardMapper.getByApplicationId(applicationId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Cost_evaluation_company_road_hazard cost_evaluation_company_road_hazard){

        int res = 0 ;
        try{
            res = cost_evaluation_company_road_hazardMapper.insert(cost_evaluation_company_road_hazard);
        }catch (Exception e){
            throw new ServiceException(BizExceptionEnum.INSERT_FAIL);
        }
        if(res <1){
            throw new ServiceException(BizExceptionEnum.INSERT_FAIL);
        }
    }
}
