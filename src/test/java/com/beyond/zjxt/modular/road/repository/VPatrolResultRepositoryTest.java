package com.beyond.zjxt.modular.road.repository;

import com.beyond.zjxt.modular.road.entity.VPatrolResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VPatrolResultRepositoryTest {

    @Autowired
    private VPatrolResultRepository vPatrolResultRepository;

    @Test
    public void testFindAll() {
        PageRequest request = PageRequest.of(0,20);
        vPatrolResultRepository.findAll(request).getContent().forEach(System.out::println);
    }

    @Test
    public void findByParams() {
        List<VPatrolResult> list = vPatrolResultRepository.findAllByParams(102,null,1,null,null,null, null);
        list.forEach(System.out::println);
    }
}