package com.smepms.jobmanagement.service.impl;

import com.smepms.jobmanagement.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * EmployeeServiceImplTest
 *
 * @author liujh
 * @since 2018/4/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-common.xml")
public class EmployeeServiceImplTest {
  @Autowired
  private EmployeeMapper employeeMapper;

  @Test
  public void queryEmployeeDepartmentLeader() {
    try {

      employeeMapper.queryEmployeeWithDepartmentAndPosition(18);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}