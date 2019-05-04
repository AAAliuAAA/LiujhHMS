package com.smepms.workattendance.service;

import com.smepms.workattendance.dto.WorkAttendanceCheckRestResult;
import com.smepms.workattendance.pojo.WorkAttendanceCheck;

import java.util.Date;
import java.util.List;

public interface WorkAttendanceCheckService {
  public boolean checkIn();

  public boolean checkOut();

  public List<WorkAttendanceCheck> queryWorkAttendanceCheckByTargetDate(Date date);

  public WorkAttendanceCheckRestResult queryCheckStatus();
}
