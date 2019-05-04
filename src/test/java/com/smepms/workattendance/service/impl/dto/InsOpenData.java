package com.smepms.workattendance.service.impl.dto;

/**
 * @author liujh
 * @since 2018/4/2
 */
public class InsOpenData<T> {
  /**
   * 对象编码
   */
  private String dataCode;

  /**
   * 是否删除
   */
  private Boolean deleted;

  /**
   * 实例ID
   */
  private Long id;

  /**
   * 接入应用系统数据ID
   */
  private String appDataId;

  /**
   * 数据内容
   */
  private T data;

  public InsOpenData(){}

  public InsOpenData(Long id, String dataCode, Boolean deleted, T data, String appDataId) {
    this.id = id;
    this.dataCode = dataCode;
    this.deleted = deleted;
    this.data = data;
    this.appDataId = appDataId;
  }

  public String getDataCode() {
    return dataCode;
  }

  public void setDataCode(String dataCode) {
    this.dataCode = dataCode;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getAppDataId() {
    return appDataId;
  }

  public void setAppDataId(String appDataId) {
    this.appDataId = appDataId;
  }

  @Override
  public String toString() {
    return "InsOpenData{" +
        "dataCode='" + dataCode + '\'' +
        ", deleted=" + deleted +
        ", id=" + id +
        ", appDataId='" + appDataId + '\'' +
        ", data=" + data +
        '}';
  }
}
