package com.beyond.zjxt.modular.road.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Stake;
import com.beyond.zjxt.modular.road.mapper.StakeMapper;
import com.beyond.zjxt.modular.road.service.StakeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 桩 服务实现类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public class StakeServiceImpl extends ServiceImpl<StakeMapper, Stake> implements StakeService {

    @Autowired
    private StakeMapper stakeMapper;

    @Override
    public Object getStakeList() {
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String,Object>> s = stakeMapper.getStakeList(page).getRecords();
        System.out.println(s);
        return stakeMapper.getStakeList(page);
    }
    @Override
    public Page<Map<String, Object>> selectAllStake(Integer nationalHighwayId,Integer begin,Integer end,Integer roadSectionId){

        Page page = LayuiPageFactory.defaultPage();
        return stakeMapper.selectAllStake(page,nationalHighwayId,begin ,end ,roadSectionId);
    }

    @Override
    public List<Map<String, Object>> selectAllNationalHighway(){

        return stakeMapper.selectAllNationalHighway();
    }
    @Override
    public List<Map<String, Object>> selectAllRoadSection(int nationalHighwayId){
        return stakeMapper.selectAllRoadSection(nationalHighwayId);

    }
    @Override
    public int addStake(String stakeName,int roadSectionId,int location){
        return stakeMapper.addStake(stakeName,roadSectionId, location);
    };
    @Override
    public int updateStake(String stakeName,int roadSectionId,int stakeId,int location){
        return stakeMapper.updateStake(stakeName,roadSectionId,stakeId, location);
    };

    @Override
    public Object slectStakeOne(int stakeId){
        return stakeMapper.slectStakeOne(stakeId);
    };
    @Override
    public int deleteStake(int stakeId){
        return stakeMapper.deleteStake(stakeId);
    };



}
