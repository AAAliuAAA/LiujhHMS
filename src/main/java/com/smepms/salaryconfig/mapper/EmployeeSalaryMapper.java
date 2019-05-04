package com.smepms.salaryconfig.mapper;

import com.smepms.salaryconfig.pojo.EmployeeSalary;
import com.smepms.salaryconfig.pojo.EmployeeSalaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeSalaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int countByExample(EmployeeSalaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int deleteByExample(EmployeeSalaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int deleteByPrimaryKey(Integer employeeSalaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int insert(EmployeeSalary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int insertSelective(EmployeeSalary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    List<EmployeeSalary> selectByExample(EmployeeSalaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    EmployeeSalary selectByPrimaryKey(Integer employeeSalaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByExampleSelective(@Param("record") EmployeeSalary record, @Param("example") EmployeeSalaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByExample(@Param("record") EmployeeSalary record, @Param("example") EmployeeSalaryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByPrimaryKeySelective(EmployeeSalary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee_salary
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByPrimaryKey(EmployeeSalary record);
}