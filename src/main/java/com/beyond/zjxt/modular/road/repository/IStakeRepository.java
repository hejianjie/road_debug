package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.IStake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStakeRepository extends JpaRepository<IStake, Integer> {
    List<IStake> findAllByRoadSectionId(Integer roadSectionId);
}
