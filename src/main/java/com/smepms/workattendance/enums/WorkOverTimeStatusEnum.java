package com.smepms.workattendance.enums;

public enum WorkOverTimeStatusEnum {

    LEADER_PASS("部门主管通过"),HR_PASS("人事部门通过"),REJECT("打回"),CREATED("等待部门主管审批");

    private String name;

    WorkOverTimeStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
