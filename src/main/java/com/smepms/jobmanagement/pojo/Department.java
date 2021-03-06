package com.smepms.jobmanagement.pojo;

import java.util.Date;

public class Department {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_department.department_id
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private Integer departmentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_department.department_name
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private String departmentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_department.department_creater
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private Integer departmentCreater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_department.department_sign
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private String departmentSign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_department.department_create_time
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private Date departmentCreateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_department.department_id
     *
     * @return the value of t_department.department_id
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_department.department_id
     *
     * @param departmentId the value for t_department.department_id
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_department.department_name
     *
     * @return the value of t_department.department_name
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_department.department_name
     *
     * @param departmentName the value for t_department.department_name
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_department.department_creater
     *
     * @return the value of t_department.department_creater
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public Integer getDepartmentCreater() {
        return departmentCreater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_department.department_creater
     *
     * @param departmentCreater the value for t_department.department_creater
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setDepartmentCreater(Integer departmentCreater) {
        this.departmentCreater = departmentCreater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_department.department_sign
     *
     * @return the value of t_department.department_sign
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public String getDepartmentSign() {
        return departmentSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_department.department_sign
     *
     * @param departmentSign the value for t_department.department_sign
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setDepartmentSign(String departmentSign) {
        this.departmentSign = departmentSign == null ? null : departmentSign.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_department.department_create_time
     *
     * @return the value of t_department.department_create_time
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public Date getDepartmentCreateTime() {
        return departmentCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_department.department_create_time
     *
     * @param departmentCreateTime the value for t_department.department_create_time
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setDepartmentCreateTime(Date departmentCreateTime) {
        this.departmentCreateTime = departmentCreateTime;
    }
}