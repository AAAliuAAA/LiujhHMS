package com.smepms.jobmanagement.pojo;

public class Position {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_position.position_id
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private Integer positionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_position.position_name
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    private String positionName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_position.position_id
     *
     * @return the value of t_position.position_id
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_position.position_id
     *
     * @param positionId the value for t_position.position_id
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_position.position_name
     *
     * @return the value of t_position.position_name
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_position.position_name
     *
     * @param positionName the value for t_position.position_name
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }
}