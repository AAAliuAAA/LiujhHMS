/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/10 9:15:19                            */
/*==============================================================*/
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE
IF EXISTS t_department;

DROP TABLE
IF EXISTS t_employee;

DROP TABLE
IF EXISTS t_employee_leave;

DROP TABLE
IF EXISTS t_employee_salary;

DROP TABLE
IF EXISTS t_function;

DROP TABLE
IF EXISTS t_function_role;

DROP TABLE
IF EXISTS t_leave;

DROP TABLE
IF EXISTS t_performance;

DROP TABLE
IF EXISTS t_position;

DROP TABLE
IF EXISTS t_role;

DROP TABLE
IF EXISTS t_role_employee;

DROP TABLE
IF EXISTS t_salary;

DROP TABLE
IF EXISTS t_status;

DROP TABLE
IF EXISTS t_work_attendance;

DROP TABLE
IF EXISTS t_work_overtime;

/*==============================================================*/
/* Table: t_department                                          */
/*==============================================================*/
CREATE TABLE t_department (
	department_id INT NOT NULL auto_increment,
	department_name VARCHAR (40) NOT NULL,
	department_creater INT NOT NULL,
	department_sign VARCHAR (58),
	department_create_time datetime NOT NULL,
	PRIMARY KEY (department_id)
);

ALTER TABLE t_department COMMENT '部门表/角色表';

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
CREATE TABLE t_employee (
	employee_id INT NOT NULL auto_increment,
	status_id INT,
	employee_name VARCHAR (20) NOT NULL,
	employee_password VARCHAR (40) NOT NULL,
	employee_phone VARCHAR (50) NOT NULL,
	employee_email VARCHAR (50) NOT NULL,
	employee_introduction text NOT NULL,
	employee_head_sculpture LONGTEXT NOT NULL,
	employee_birthday date NOT NULL,
	employee_create_time datetime NOT NULL,
	employee_leader INT,
	PRIMARY KEY (employee_id)
);

ALTER TABLE t_employee COMMENT '员工表';

/*==============================================================*/
/* Table: t_employee_leave                                      */
/*==============================================================*/
CREATE TABLE t_employee_leave (leave_id INT, employee_id INT);

/*==============================================================*/
/* Table: t_employee_salary                                     */
/*==============================================================*/
CREATE TABLE t_employee_salary (
	employee_salary_id INT NOT NULL auto_increment,
	employee_id INT,
	employee_salary_total FLOAT NOT NULL,
	PRIMARY KEY (employee_salary_id)
);

ALTER TABLE t_employee_salary COMMENT '员工薪资表';

/*==============================================================*/
/* Table: t_function                                            */
/*==============================================================*/
CREATE TABLE t_function (
	function_id INT NOT NULL auto_increment,
	function_name VARCHAR (20) NOT NULL,
	function_icon_url VARCHAR (100),
	function_url VARCHAR (50) NOT NULL,
	PRIMARY KEY (function_id)
);

ALTER TABLE t_function COMMENT '指定当前权限能实现哪些功能';

/*==============================================================*/
/* Table: t_function_role                                       */
/*==============================================================*/
CREATE TABLE t_function_role (role_id INT, function_id INT);

ALTER TABLE t_function_role COMMENT '权限/角色连接表';

/*==============================================================*/
/* Table: t_leave                                               */
/*==============================================================*/
CREATE TABLE t_leave (
	leave_id INT NOT NULL auto_increment,
	leave_reason text NOT NULL,
	leave_start_time datetime NOT NULL,
	leave_end_time datetime NOT NULL,
	leave_total_days FLOAT NOT NULL,
	leave_pass INT NOT NULL,
	PRIMARY KEY (leave_id)
);

/*==============================================================*/
/* Table: t_performance                                         */
/*==============================================================*/
CREATE TABLE t_performance (
	performance_id INT NOT NULL auto_increment,
	performance_name VARCHAR (50) NOT NULL,
	performance_salary FLOAT NOT NULL,
	PRIMARY KEY (performance_id)
);

ALTER TABLE t_performance COMMENT '绩效配置表';

/*==============================================================*/
/* Table: t_position                                            */
/*==============================================================*/
CREATE TABLE t_position (
	position_id INT NOT NULL auto_increment,
	position_name VARCHAR (20) NOT NULL,
	PRIMARY KEY (position_id)
);

ALTER TABLE t_position COMMENT '职位表';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
CREATE TABLE t_role (
	role_id INT NOT NULL auto_increment,
	department_id INT,
	position_id INT,
	PRIMARY KEY (role_id)
);

ALTER TABLE t_role COMMENT '角色';

/*==============================================================*/
/* Table: t_role_employee                                       */
/*==============================================================*/
CREATE TABLE t_role_employee (employee_id INT, role_id INT);

ALTER TABLE t_role_employee COMMENT '连接用户 和角色';

/*==============================================================*/
/* Table: t_salary                                              */
/*==============================================================*/
CREATE TABLE t_salary (
	salary_id INT NOT NULL auto_increment,
	employee_id INT,
	salary_date date NOT NULL,
	salary_base FLOAT NOT NULL,
	PRIMARY KEY (salary_id)
);

ALTER TABLE t_salary COMMENT '薪资记录表';

/*==============================================================*/
/* Table: t_status                                              */
/*==============================================================*/
CREATE TABLE t_status (
	status_id INT NOT NULL auto_increment,
	status_name VARCHAR (20),
	PRIMARY KEY (status_id)
);

/*==============================================================*/
/* Table: t_work_attendance                                     */
/*==============================================================*/
CREATE TABLE t_work_attendance (
	work_attendance_id INT NOT NULL auto_increment,
	work_overtime_id INT,
	employee_id INT,
	work_attendance_date date NOT NULL,
	late INT NOT NULL,
	leave_early INT NOT NULL,
	PRIMARY KEY (work_attendance_id)
);

ALTER TABLE t_work_attendance COMMENT '考勤表';

/*==============================================================*/
/* Table: t_work_overtime                                      */
/*==============================================================*/
CREATE TABLE t_work_overtime (
	work_overtime_id INT NOT NULL auto_increment,
	work_overtime_date date NOT NULL,
	work_overtime_hour INT NOT NULL,
	PRIMARY KEY (work_overtime_id)
);

ALTER TABLE t_work_overtime COMMENT '加班信息表';

ALTER TABLE t_employee ADD CONSTRAINT FK_Relationship_9 FOREIGN KEY (status_id) REFERENCES t_status (status_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_employee_leave ADD CONSTRAINT FK_Relationship_12 FOREIGN KEY (leave_id) REFERENCES t_leave (leave_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_employee_leave ADD CONSTRAINT FK_Relationship_16 FOREIGN KEY (employee_id) REFERENCES t_employee (employee_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_employee_salary ADD CONSTRAINT FK_Relationship_14 FOREIGN KEY (employee_id) REFERENCES t_employee (employee_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_function_role ADD CONSTRAINT FK_Relationship_5 FOREIGN KEY (function_id) REFERENCES t_function (function_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_function_role ADD CONSTRAINT FK_Relationship_6 FOREIGN KEY (role_id) REFERENCES t_role (role_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_role ADD CONSTRAINT FK_Relationship_7 FOREIGN KEY (position_id) REFERENCES t_position (position_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_role ADD CONSTRAINT FK_Relationship_8 FOREIGN KEY (department_id) REFERENCES t_department (department_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_role_employee ADD CONSTRAINT FK_Relationship_10 FOREIGN KEY (employee_id) REFERENCES t_employee (employee_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_role_employee ADD CONSTRAINT FK_Relationship_4 FOREIGN KEY (role_id) REFERENCES t_role (role_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_salary ADD CONSTRAINT FK_Relationship_13 FOREIGN KEY (employee_id) REFERENCES t_employee (employee_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_work_attendance ADD CONSTRAINT FK_Relationship_11 FOREIGN KEY (employee_id) REFERENCES t_employee (employee_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_work_attendance ADD CONSTRAINT FK_Relationship_15 FOREIGN KEY (work_overtime_id) REFERENCES t_work_overtime (work_overtime_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

