package com.smepms.jobmanagement.enums;

import com.smepms.common.util.handler.CodeBaseEnum;

/**
 * WorkerApplicationStatus
 *
 * @author liujh
 * @since 2018/4/25
 */
public enum WorkerApplicationStatus implements CodeBaseEnum{
  CREATED("CREATED", "创建等待部门经理审核"),
  LEADERPASS("LEADERPASS", "部门经理审核通过，等待人事审核"),
  ALLPASS("ALLPASS", "审核通过，等待人事处理");

  private String code;
  private String name;

  WorkerApplicationStatus(String code, String name) {
    this.code = code;
    this.name = name;
  }


  public int code(){
    //返回enum自定义编号
    return this.ordinal();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static WorkerApplicationStatus fromCode(String code) {
    if (code == null) {
      return null;
    }
    WorkerApplicationStatus status = null;
    for(WorkerApplicationStatus workerApplicationStatus: WorkerApplicationStatus.values()){
      if(workerApplicationStatus.getCode().equals(code)){
        status = workerApplicationStatus;
      }
    }
    return status;
  }
}
