package com.smepms.workattendance.mapper;

import com.smepms.workattendance.pojo.Holiday;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HolidayMapper {
  int addHoliday(Holiday holiday);

  List<Holiday> queryAllHolidays(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("startRecord")Integer startRecord,@Param("pageSize")Integer pageSize);

  Integer queryCountHolidays(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

  Integer queryHolidayExists(@Param("holidayDate")Date holidayDate);

  Integer deleteHolidayById(@Param("holidayId")Integer holidayId);
}
