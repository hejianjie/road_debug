package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VAcceptanceRepository extends JpaRepository<VAcceptance, Integer> {
    List<VAcceptance> findAllByAcceptOrganizationId(Long acceptOrganizationId);
}
