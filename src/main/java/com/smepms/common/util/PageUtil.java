package com.smepms.common.util;

import java.util.List;

public class PageUtil<T> {
  private List<T> list;
  private Integer startRecord;
  private Integer endRecord;
  private Integer pageSize;
  private Integer currentPage;
  private Integer nextPage;
  private Integer prePage;
  private Integer totalPages;
  private Integer totalRecord;

  public Integer getTotalRecord() {
    return totalRecord;
  }

  public void setTotalRecord(Integer totalRecord) {
    this.totalRecord = totalRecord;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public Integer getStartRecord() {
    return startRecord;
  }

  public void setStartRecord(Integer startRecord) {
    this.startRecord = startRecord;
  }

  public Integer getEndRecord() {
    return endRecord;
  }

  public void setEndRecord(Integer endRecord) {
    this.endRecord = endRecord;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getNextPage() {
    return nextPage;
  }

  public void setNextPage(Integer nextPage) {
    this.nextPage = nextPage;
  }

  public Integer getPrePage() {
    return prePage;
  }

  public void setPrePage(Integer prePage) {
    this.prePage = prePage;
  }

  public void startPage(Integer pageSize,Integer pageNo,Integer totalRecord){
    this.pageSize = pageSize;
    this.currentPage = pageNo;
    this.totalRecord = totalRecord;
    this.startRecord = pageSize * (pageNo - 1);
    this.endRecord = pageSize* pageNo;
    this.totalPages = totalRecord % pageSize == 0 ? totalRecord/pageSize:totalRecord/pageSize+1;
    if(currentPage == totalPages){
      this.nextPage = totalPages;
    }else{
      this.nextPage = currentPage+1;
    }
    if(currentPage == 1){
      this.prePage = 1;
    }else{
      this.prePage = currentPage - 1;
    }
  }

}
