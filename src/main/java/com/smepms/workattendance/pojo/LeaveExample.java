package com.smepms.workattendance.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LeaveExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andLeaveIdIsNull() {
            addCriterion("leave_id is null");
            return (Criteria) this;
        }

        public Criteria andLeaveIdIsNotNull() {
            addCriterion("leave_id is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveIdEqualTo(Integer value) {
            addCriterion("leave_id =", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotEqualTo(Integer value) {
            addCriterion("leave_id <>", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdGreaterThan(Integer value) {
            addCriterion("leave_id >", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_id >=", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdLessThan(Integer value) {
            addCriterion("leave_id <", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdLessThanOrEqualTo(Integer value) {
            addCriterion("leave_id <=", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdIn(List<Integer> values) {
            addCriterion("leave_id in", values, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotIn(List<Integer> values) {
            addCriterion("leave_id not in", values, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdBetween(Integer value1, Integer value2) {
            addCriterion("leave_id between", value1, value2, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_id not between", value1, value2, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeIsNull() {
            addCriterion("leave_start_time is null");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeIsNotNull() {
            addCriterion("leave_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeEqualTo(Date value) {
            addCriterion("leave_start_time =", value, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeNotEqualTo(Date value) {
            addCriterion("leave_start_time <>", value, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeGreaterThan(Date value) {
            addCriterion("leave_start_time >", value, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("leave_start_time >=", value, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeLessThan(Date value) {
            addCriterion("leave_start_time <", value, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("leave_start_time <=", value, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeIn(List<Date> values) {
            addCriterion("leave_start_time in", values, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeNotIn(List<Date> values) {
            addCriterion("leave_start_time not in", values, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeBetween(Date value1, Date value2) {
            addCriterion("leave_start_time between", value1, value2, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("leave_start_time not between", value1, value2, "leaveStartTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeIsNull() {
            addCriterion("leave_end_time is null");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeIsNotNull() {
            addCriterion("leave_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeEqualTo(Date value) {
            addCriterion("leave_end_time =", value, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeNotEqualTo(Date value) {
            addCriterion("leave_end_time <>", value, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeGreaterThan(Date value) {
            addCriterion("leave_end_time >", value, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("leave_end_time >=", value, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeLessThan(Date value) {
            addCriterion("leave_end_time <", value, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("leave_end_time <=", value, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeIn(List<Date> values) {
            addCriterion("leave_end_time in", values, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeNotIn(List<Date> values) {
            addCriterion("leave_end_time not in", values, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeBetween(Date value1, Date value2) {
            addCriterion("leave_end_time between", value1, value2, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("leave_end_time not between", value1, value2, "leaveEndTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysIsNull() {
            addCriterion("leave_total_days is null");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysIsNotNull() {
            addCriterion("leave_total_days is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysEqualTo(Float value) {
            addCriterion("leave_total_days =", value, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysNotEqualTo(Float value) {
            addCriterion("leave_total_days <>", value, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysGreaterThan(Float value) {
            addCriterion("leave_total_days >", value, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysGreaterThanOrEqualTo(Float value) {
            addCriterion("leave_total_days >=", value, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysLessThan(Float value) {
            addCriterion("leave_total_days <", value, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysLessThanOrEqualTo(Float value) {
            addCriterion("leave_total_days <=", value, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysIn(List<Float> values) {
            addCriterion("leave_total_days in", values, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysNotIn(List<Float> values) {
            addCriterion("leave_total_days not in", values, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysBetween(Float value1, Float value2) {
            addCriterion("leave_total_days between", value1, value2, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveTotalDaysNotBetween(Float value1, Float value2) {
            addCriterion("leave_total_days not between", value1, value2, "leaveTotalDays");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusIsNull() {
            addCriterion("leave_status is null");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusIsNotNull() {
            addCriterion("leave_status is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusEqualTo(Integer value) {
            addCriterion("leave_status =", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNotEqualTo(Integer value) {
            addCriterion("leave_status <>", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusGreaterThan(Integer value) {
            addCriterion("leave_status >", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_status >=", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusLessThan(Integer value) {
            addCriterion("leave_status <", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusLessThanOrEqualTo(Integer value) {
            addCriterion("leave_status <=", value, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusIn(List<Integer> values) {
            addCriterion("leave_status in", values, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNotIn(List<Integer> values) {
            addCriterion("leave_status not in", values, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusBetween(Integer value1, Integer value2) {
            addCriterion("leave_status between", value1, value2, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_status not between", value1, value2, "leaveStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Integer value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Integer value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Integer value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Integer value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Integer> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Integer> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameIsNull() {
            addCriterion("leave_status_name is null");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameIsNotNull() {
            addCriterion("leave_status_name is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameEqualTo(String value) {
            addCriterion("leave_status_name =", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameNotEqualTo(String value) {
            addCriterion("leave_status_name <>", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameGreaterThan(String value) {
            addCriterion("leave_status_name >", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("leave_status_name >=", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameLessThan(String value) {
            addCriterion("leave_status_name <", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameLessThanOrEqualTo(String value) {
            addCriterion("leave_status_name <=", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameLike(String value) {
            addCriterion("leave_status_name like", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameNotLike(String value) {
            addCriterion("leave_status_name not like", value, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameIn(List<String> values) {
            addCriterion("leave_status_name in", values, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameNotIn(List<String> values) {
            addCriterion("leave_status_name not in", values, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameBetween(String value1, String value2) {
            addCriterion("leave_status_name between", value1, value2, "leaveStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaveStatusNameNotBetween(String value1, String value2) {
            addCriterion("leave_status_name not between", value1, value2, "leaveStatusName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}