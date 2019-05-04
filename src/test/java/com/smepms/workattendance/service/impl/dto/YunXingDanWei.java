package com.smepms.workattendance.service.impl.dto;

/**
 * @author liujh
 * @since 2018/4/2
 */
public class YunXingDanWei{
  private Integer id;
  private Boolean deleted;
  private Boolean builtIn;
  private String parentId;
  private String comName;
  private String comCode;
  private String nodeType;
  private String depict;
  private String dispOrder;
  private String children;
  private String parentComCode;
  private String parentComName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Boolean getBuiltIn() {
    return builtIn;
  }

  public void setBuiltIn(Boolean builtIn) {
    this.builtIn = builtIn;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getComName() {
    return comName;
  }

  public void setComName(String comName) {
    this.comName = comName;
  }

  public String getComCode() {
    return comCode;
  }

  public void setComCode(String comCode) {
    this.comCode = comCode;
  }

  public String getNodeType() {
    return nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public String getDepict() {
    return depict;
  }

  public void setDepict(String depict) {
    this.depict = depict;
  }

  public String getDispOrder() {
    return dispOrder;
  }

  public void setDispOrder(String dispOrder) {
    this.dispOrder = dispOrder;
  }

  public String getChildren() {
    return children;
  }

  public void setChildren(String children) {
    this.children = children;
  }

  public String getParentComCode() {
    return parentComCode;
  }

  public void setParentComCode(String parentComCode) {
    this.parentComCode = parentComCode;
  }

  public String getParentComName() {
    return parentComName;
  }

  public void setParentComName(String parentComName) {
    this.parentComName = parentComName;
  }
}
