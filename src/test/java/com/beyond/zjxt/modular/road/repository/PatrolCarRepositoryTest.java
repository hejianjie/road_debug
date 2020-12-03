package com.beyond.zjxt.modular.road.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PatrolCarRepositoryTest {

    @Autowired
    private PatrolCarRepository patrolCarRepository;

    @Test
    public void findAllByDeptId() {
        Long l =(long) 4;
        patrolCarRepository.findAllByDeptId(l).forEach(System.out::println);
    }
}