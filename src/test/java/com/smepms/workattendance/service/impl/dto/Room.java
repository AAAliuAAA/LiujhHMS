package com.smepms.workattendance.service.impl.dto;

import com.smepms.workattendance.service.impl.dto.common.DutyRole;

/**
 * @author liujh
 * @since 2018/3/30
 */
public class Room {
  private DutyRole dutyRole;
  private Double totalArea;
  private String address;
  private YunXingDanWei YunXingDanWei;
  private String FangJianHao;
  private Integer LouCeng;
  private Double YiYongMianJi;
  private String MenJin;
  private String name;

  @Override
  public String toString() {
    return "Room{" +
        "dutyRole=" + dutyRole +
        ", totalArea=" + totalArea +
        ", address='" + address + '\'' +
        ", YunXingDanWei=" + YunXingDanWei +
        ", FangJianHao='" + FangJianHao + '\'' +
        ", LouCeng=" + LouCeng +
        ", YiYongMianJi=" + YiYongMianJi +
        ", MenJin='" + MenJin + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  public DutyRole getDutyRole() {
    return dutyRole;
  }

  public void setDutyRole(DutyRole dutyRole) {
    this.dutyRole = dutyRole;
  }

  public Double getTotalArea() {
    return totalArea;
  }

  public void setTotalArea(Double totalArea) {
    this.totalArea = totalArea;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public com.smepms.workattendance.service.impl.dto.YunXingDanWei getYunXingDanWei() {
    return YunXingDanWei;
  }

  public void setYunXingDanWei(com.smepms.workattendance.service.impl.dto.YunXingDanWei yunXingDanWei) {
    YunXingDanWei = yunXingDanWei;
  }

  public String getFangJianHao() {
    return FangJianHao;
  }

  public void setFangJianHao(String fangJianHao) {
    FangJianHao = fangJianHao;
  }

  public Integer getLouCeng() {
    return LouCeng;
  }

  public void setLouCeng(Integer louCeng) {
    LouCeng = louCeng;
  }

  public Double getYiYongMianJi() {
    return YiYongMianJi;
  }

  public void setYiYongMianJi(Double yiYongMianJi) {
    YiYongMianJi = yiYongMianJi;
  }

  public String getMenJin() {
    return MenJin;
  }

  public void setMenJin(String menJin) {
    MenJin = menJin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
