package com.beyond.zjxt.modular.road.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.core.common.page.LayuiPageFactory;
import com.beyond.zjxt.modular.road.entity.Stake;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beyond.zjxt.modular.road.mapper.StakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 桩 服务类
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Service
public interface StakeService extends IService<Stake> {

    Object getStakeList();

    Page<Map<String, Object>> selectAllStake(Integer nationalHighwayId,Integer begin,Integer end,Integer roadSectionId);

    Object selectAllNationalHighway();

    Object selectAllRoadSection(int nationalHighwayId);

    int addStake(String stakeName,int roadSectionId,int location);

    int updateStake(String stakeName,int roadSectionId,int stakeId,int location);

    Object  slectStakeOne(int stakeId);

    int deleteStake(int stakeId);


}
