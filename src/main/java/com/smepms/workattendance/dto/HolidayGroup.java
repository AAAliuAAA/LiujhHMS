package com.smepms.workattendance.dto;

import com.smepms.workattendance.pojo.Holiday;

import java.util.*;

public class HolidayGroup {
  private String GroupName;
  private String GroupType;
  private Set<Holiday> holidayContent;

  public HolidayGroup(){
    holidayContent = new TreeSet<Holiday>(new Comparator<Holiday>() {
      public int compare(Holiday o1, Holiday o2) {
        return o1.getHolidayId()>o2.getHolidayId()?1:-1;
      }
    });
  }


  public String getGroupName() {
    return GroupName;
  }

  public void setGroupName(String groupName) {
    GroupName = groupName;
  }

  public String getGroupType() {
    return GroupType;
  }

  public void setGroupType(String groupType) {
    GroupType = groupType;
  }

  public Set<Holiday> getHolidayContent() {
    return holidayContent;
  }

  public void setHolidayContent(Set<Holiday> holidayContent) {
    this.holidayContent = holidayContent;
  }
}
