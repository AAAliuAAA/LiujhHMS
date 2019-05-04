package com.smepms.salaryconfig.mapper;

import com.smepms.salaryconfig.pojo.Performance;
import com.smepms.salaryconfig.pojo.PerformanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PerformanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int countByExample(PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int deleteByExample(PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int deleteByPrimaryKey(Integer performanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int insert(Performance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int insertSelective(Performance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    List<Performance> selectByExample(PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    Performance selectByPrimaryKey(Integer performanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByExampleSelective(@Param("record") Performance record, @Param("example") PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByExample(@Param("record") Performance record, @Param("example") PerformanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByPrimaryKeySelective(Performance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_performance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByPrimaryKey(Performance record);
}