package com.smepms.workattendance.service;

import com.github.pagehelper.PageInfo;
import com.smepms.common.util.PageUtil;
import com.smepms.workattendance.dto.HolidayGroup;
import com.smepms.workattendance.pojo.Holiday;

import java.util.Date;

public interface HolidayService {
  public void queryHolidayTillTargetDate(Date date);

  public PageUtil<HolidayGroup> queryAllHolidaySelective(Date startDate, Date endDate, Integer pageNo, Integer pageSize);
  boolean createNewHolidayConfig(Holiday holiday);

  boolean deleteHolidayById(Integer holidayId);

}
