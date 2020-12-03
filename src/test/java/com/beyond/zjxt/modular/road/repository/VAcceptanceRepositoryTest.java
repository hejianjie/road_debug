package com.beyond.zjxt.modular.road.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VAcceptanceRepositoryTest {

    @Autowired
    private VAcceptanceRepository acceptanceRepository;

    @Test
    public void findAllByAcceptOrganizationIdAndAudited() {
        acceptanceRepository.findAllByAcceptOrganizationId(1200000929026998274L).forEach(System.out::println);
    }
}