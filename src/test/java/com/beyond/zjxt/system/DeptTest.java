package com.beyond.zjxt.system;

import com.beyond.zjxt.base.BaseJunit;
import com.beyond.zjxt.modular.system.entity.Dept;
import com.beyond.zjxt.modular.system.mapper.DeptMapper;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class DeptTest extends BaseJunit {

    @Resource
    DeptMapper deptMapper;

    @Test
    public void addDeptTest() {
        Dept dept = new Dept();
        dept.setFullName("测试fullname");
        dept.setSort(5);
        dept.setPid(1L);
        dept.setSimpleName("测试");
        dept.setDescription("测试tips");
        dept.setVersion(1);
        Integer insert = deptMapper.insert(dept);
        assertEquals(insert, new Integer(1));
    }

    @Test
    public void updateTest() {
        Dept dept = this.deptMapper.selectById(24);
        dept.setDescription("哈哈");
        deptMapper.updateById(dept);
    }

    @Test
    public void deleteTest() {
        Dept dept = this.deptMapper.selectById(24);
        Integer integer = deptMapper.deleteById(dept);
        assertTrue(integer > 0);
    }
}
