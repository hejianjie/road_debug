package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VHazard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: RaymondZhao
 * @Datetime: 2019/12/12 15:13
 * @Description:
 */
public interface VHazardRepository extends JpaRepository<VHazard, Integer> {
    Page<VHazard> findAllByPatrolResultIdAndUserId(Integer patrolResultId, Integer userId, Pageable pageable);
    Page<VHazard> findAllByUserId(Integer userId, Pageable pageable);
}
