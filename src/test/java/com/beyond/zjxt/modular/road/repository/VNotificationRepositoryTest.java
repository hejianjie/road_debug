package com.beyond.zjxt.modular.road.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VNotificationRepositoryTest {

    @Autowired
    private VNotificationRepository vNotificationRepository;

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 20);
        vNotificationRepository.findAll(request).getContent().forEach(System.out::println);
    }
}