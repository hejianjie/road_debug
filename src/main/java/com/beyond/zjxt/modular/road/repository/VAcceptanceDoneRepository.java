package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VAcceptanceDone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VAcceptanceDoneRepository extends JpaRepository<VAcceptanceDone, Integer> {
    List<VAcceptanceDone> findAllByAcceptOrganizationId(Long acceptOrganizationId);
}
