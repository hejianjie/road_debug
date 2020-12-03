package com.beyond.zjxt.modular.road.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beyond.zjxt.modular.road.entity.Road_section;
import com.beyond.zjxt.modular.road.entity.Stake;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 桩 Mapper 接口
 * </p>
 *
 * @author beyond
 * @since 2019-11-19
 */
@Mapper
@Repository
public interface StakeMapper extends BaseMapper<Stake> {

    Page<Map<String, Object>> getStakeList(@Param(("Page")) Page page);

    Page<Map<String, Object>> selectAllStake(@Param("page") Page page,@Param("nationalHighwayId")int nationalHighwayId,@Param("begin")Integer begin ,@Param("end") Integer end,@Param("roadSectionId") Integer roadSectionId);

    List<Map<String, Object>> selectAllNationalHighway();

    List<Map<String, Object>> selectAllRoadSection(@Param("nationalHighwayId") int nationalHighwayId);

    int addStake(@Param("stakeName") String stakeName,@Param("roadSectionId") int roadSectionId,@Param("location") int location);

    int updateStake(@Param("stakeName") String stakeName,@Param("roadSectionId") int roadSectionId,@Param("stakeId") int stakeId,@Param("location") int location);

    List<Map<String, Object>> slectStakeOne(@Param("stakeId") int stakeId);

    int deleteStake(@Param("stakeId") int stakeId);
}

