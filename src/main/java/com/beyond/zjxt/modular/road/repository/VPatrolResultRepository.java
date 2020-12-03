package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VPatrolResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VPatrolResultRepository extends JpaRepository<VPatrolResult, Integer> {

    Page<VPatrolResult> findAll(Pageable pageable);
    Page<VPatrolResult> findAllByInspectorId(Integer inspectorId, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from v_patrol_result where inspector_id = ?1 " +
            "and if(IFNULL(?2,'')!='',highway_id=?2,1=1) " +
            "and if(IFNULL(?3,'')!='',road_section_id=?3,1=1) " +
            "and if(IFNULL(?4,'')!='',stake_begin_id>=?4,1=1) " +
            "and if(IFNULL(?5,'')!='',stake_end_id<=?5,1=1) " +
            "and if(?6 !='',begin_time>=?6,1=1) " +
            "and if(?7 !='',end_time<=?7,1=1)")
    List<VPatrolResult> findAllByParams(Integer inspectorId,Integer highwayId, Integer roadSectionId, Integer stakeBeginId, Integer stakeEndId, String beginTime, String endTime);
}
