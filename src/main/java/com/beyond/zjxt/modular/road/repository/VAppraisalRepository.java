package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VAppraisal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VAppraisalRepository extends JpaRepository<VAppraisal, Integer> {
    List<VAppraisal> findAllByThirdPartyIdAndEvaluated(Long thirdPartyId, Integer evaluated);
}
