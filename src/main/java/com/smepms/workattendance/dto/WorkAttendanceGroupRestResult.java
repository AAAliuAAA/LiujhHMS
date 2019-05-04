package com.smepms.workattendance.dto;

import com.github.pagehelper.PageInfo;
import com.smepms.common.util.PageUtil;

import java.util.Date;
import java.util.List;

/**
 * 页面展示 - 员工考勤信息 记录传输对象
 * WorkAttendanceRestResult
 *
 * @author liujh
 * @since 2018/5/9
 */
public class WorkAttendanceGroupRestResult {
  private PageInfo<WorkAttendanceRestResult> page;
  private Date workAttendanceDate;

  public PageInfo<WorkAttendanceRestResult> getPage() {
    return page;
  }

  public void setPage(PageInfo<WorkAttendanceRestResult> page) {
    this.page = page;
  }

  public Date getWorkAttendanceDate() {
    return workAttendanceDate;
  }

  public void setWorkAttendanceDate(Date workAttendanceDate) {
    this.workAttendanceDate = workAttendanceDate;
  }
}
