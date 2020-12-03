package com.beyond.zjxt.modular.road.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NationalHighwayRepositoryTest {

    @Autowired
    private NationalHighwayRepository nationalHighwayRepository;

    @Test
    public void testFindAll() {
        nationalHighwayRepository.findAll().forEach(System.out::println);
    }

}