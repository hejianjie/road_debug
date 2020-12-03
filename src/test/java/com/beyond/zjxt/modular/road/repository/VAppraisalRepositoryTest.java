package com.beyond.zjxt.modular.road.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VAppraisalRepositoryTest {

    @Autowired
    private VAppraisalRepository vAppraisalRepository;

    @Test
    public void findAllByThirdPartyIdAndEvaluated() {
        vAppraisalRepository.findAllByThirdPartyIdAndEvaluated(38L, 1).forEach(System.out::println);
    }
}