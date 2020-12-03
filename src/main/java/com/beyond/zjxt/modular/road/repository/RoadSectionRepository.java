package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.RoadSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadSectionRepository extends JpaRepository<RoadSection, Integer> {
    List<RoadSection> findAllByNationalHighwayId(Integer nationalHighwayId);
}
