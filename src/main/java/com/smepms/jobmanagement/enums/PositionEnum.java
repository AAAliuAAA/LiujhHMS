package com.smepms.jobmanagement.enums;

/**
 * PositionEnum
 *  定义项目的权限基本骨架，和基础表position数据相同
 * @author liujh
 * @since 2018/5/8
 */
public enum PositionEnum {
  CLERK(1,"职员"),MANAGER(2,"部门经理"),INTERNSHIP(3,"实习生"),PROBATION_CLERK(4,"试用"),LEADER(5,"组长");

  private int positionId;
  private String positionName;

  PositionEnum(int positionId, String positionName) {
    this.positionId = positionId;
    this.positionName = positionName;
  }

  public int getPositionId() {
    return positionId;
  }

  public String getPositionName() {
    return positionName;
  }

  public PositionEnum fromPositionId(Integer positionId){
    if(positionId == null){
      return null;
    }
    PositionEnum position = null;
    for(PositionEnum positionEnum:PositionEnum.values()){
      if(positionEnum.positionId == positionId){
        position = positionEnum;
      }
    }
    return position;
  }
}
