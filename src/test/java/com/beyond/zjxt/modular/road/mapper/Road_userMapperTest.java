package com.beyond.zjxt.modular.road.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Road_userMapperTest {
    @Autowired
    private Road_userMapper road_userMapper;
    @Test
    public void selectOne() {
        System.out.println(road_userMapper.selectOne(1));
    }
}