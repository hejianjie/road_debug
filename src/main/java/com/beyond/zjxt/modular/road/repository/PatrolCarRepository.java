package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.PatrolCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatrolCarRepository extends JpaRepository<PatrolCar, String> {
    List<PatrolCar> findAllByDeptId(Long deptId);
}
