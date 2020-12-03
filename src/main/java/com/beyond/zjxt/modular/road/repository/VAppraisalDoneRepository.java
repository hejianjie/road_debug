package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VAppraisalDone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VAppraisalDoneRepository extends JpaRepository<VAppraisalDone, Integer> {
    List<VAppraisalDone> findAllByThirdPartyId(Long thirdPartyId);
}
