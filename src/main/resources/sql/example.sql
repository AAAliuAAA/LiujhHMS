-- 员工在职状态表插入
INSERT INTO t_status (status_id, status_name)
VALUES
	(NULL, "在职");

INSERT INTO t_status (status_id, status_name)
VALUES
	(NULL, "离职");

INSERT INTO t_status (status_id, status_name)
VALUES
	(NULL, "实习");

INSERT INTO t_status (status_id, status_name)
VALUES
	(NULL, "试用");

-- 部门表插入
INSERT INTO t_department (
	department_id,
	department_name,
	department_creater,
	department_sign,
	department_create_time
)
VALUES
	(
		NULL,
		"人事部",
		"1",
		NULL,
		now()
	);

INSERT INTO t_department (
	department_id,
	department_name,
	department_creater,
	department_sign,
	department_create_time
)
VALUES
	(
		NULL,
		"销售部",
		"2",
		NULL,
		now()
	);

INSERT INTO t_department (
	department_id,
	department_name,
	department_creater,
	department_sign,
	department_create_time
)
VALUES
	(
		NULL,
		"渠道部",
		"1",
		NULL,
		now()
	);

INSERT INTO t_department (
	department_id,
	department_name,
	department_creater,
	department_sign,
	department_create_time
)
VALUES
	(
		NULL,
		"开发部",
		"2",
		NULL,
		now()
	);

INSERT INTO t_department (
	department_id,
	department_name,
	department_creater,
	department_sign,
	department_create_time
)
VALUES
	(
		NULL,
		"采购部",
		"1",
		NULL,
		now()
	);

-- 员工表插入
INSERT INTO t_employee (
	employee_id,
	employee_name,
	employee_password,
	employee_phone,
	employee_email,
	employee_introduction,
	employee_head_sculpture,
	employee_birthday,
	employee_create_time,
	employee_leader,
	status_id
)
VALUES
	(
		NULL,
		"小刘",
		"123",
		"132228888888",
		"liujiahe123456789@163.com",
		"很帅",
		"暂时置空",
		"1996-03-11",
		NOW(),
		NULL,
		1
	);

INSERT INTO t_employee (
	employee_id,
	employee_name,
	employee_password,
	employee_phone,
	employee_email,
	employee_introduction,
	employee_head_sculpture,
	employee_birthday,
	employee_create_time,
	employee_leader,
	status_id
)
VALUES
	(
		NULL,
		"小王",
		"123456",
		"13222888778",
		"liujiahe12345@163.com",
		"不是很帅",
		"暂时置空",
		"1996-03-19",
		NOW(),
		1,
		1
	);

INSERT INTO t_employee (
	employee_id,
	employee_name,
	employee_password,
	employee_phone,
	employee_email,
	employee_introduction,
	employee_head_sculpture,
	employee_birthday,
	employee_create_time,
	employee_leader,
	status_id
)
VALUES
	(
		NULL,
		"小杨",
		"123456789",
		"132244588",
		"liujiahe123456@126.com",
		"确实不是很帅",
		"暂时置空",
		"1996-03-19",
		NOW(),
		1,
		1
	);

INSERT INTO t_employee (
	employee_id,
	employee_name,
	employee_password,
	employee_phone,
	employee_email,
	employee_introduction,
	employee_head_sculpture,
	employee_birthday,
	employee_create_time,
	employee_leader,
	status_id
)
VALUES
	(
		NULL,
		"小李",
		"1234888779",
		"132244897",
		"liujiahe123456@gmail.com",
		"一般帅",
		"暂时置空",
		"1996-08-19",
		NOW(),
		1,
		1
	);

-- 功能/权限表 插入
INSERT INTO t_function (
	function_id,
	function_name,
	function_icon_url,
	function_url
)
VALUES
	(
		NULL,
		"查看我的信息",
		"暂时置空",
		"employee_self:view"
	);

INSERT INTO t_function (
	function_id,
	function_name,
	function_icon_url,
	function_url
)
VALUES
	(
		NULL,
		"录入员工信息",
		"暂时置空",
		"employee_others:insert"
	);

INSERT INTO t_function (
	function_id,
	function_name,
	function_icon_url,
	function_url
)
VALUES
	(
		NULL,
		"修改自己的员工信息",
		"暂时置空",
		"employee_self:update"
	);

INSERT INTO t_function (
	function_id,
	function_name,
	function_icon_url,
	function_url
)
VALUES
	(
		NULL,
		"修改其他员工信息",
		"暂时置空",
		"employee_others:update"
	);

INSERT INTO t_function (
	function_id,
	function_name,
	function_icon_url,
	function_url
)
VALUES
	(
		NULL,
		"删除指定员工",
		"暂时置空",
		"employee_others:delete"
	);

INSERT INTO t_function (
	function_id,
	function_name,
	function_icon_url,
	function_url
)
VALUES
	(
		NULL,
		"查看其他员工",
		"暂时置空",
		"employee_others:view"
	);

-- 职位表插入
INSERT INTO t_position (position_id, position_name)
VALUES
	(NULL, "职员");

INSERT INTO t_position (position_id, position_name)
VALUES
	(NULL, "部门经理");

INSERT INTO t_position (position_id, position_name)
VALUES
	(NULL, "实习生");

INSERT INTO t_position (position_id, position_name)
VALUES
	(NULL, "未入职");

INSERT INTO t_position (position_id, position_name)
VALUES
	(NULL, "组长");

-- 薪资记录表插入
INSERT INTO t_employee_salary (
	employee_salary_id,
	employee_id,
	employee_salary_total
)
VALUES
	(NULL, 1, 6600.0);

INSERT INTO t_employee_salary (
	employee_salary_id,
	employee_id,
	employee_salary_total
)
VALUES
	(NULL, 2, 5780.0);

INSERT INTO t_employee_salary (
	employee_salary_id,
	employee_id,
	employee_salary_total
)
VALUES
	(NULL, 1, 8701.0);

INSERT INTO t_employee_salary (
	employee_salary_id,
	employee_id,
	employee_salary_total
)
VALUES
	(NULL, 1, 5784.0);

-- 角色表数据插入 
INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 1, 1);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 1, 2);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 1, 3);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 1, 4);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 1, 5);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 2, 1);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 2, 2);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 2, 3);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 2, 4);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 2, 5);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 3, 1);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 3, 2);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 3, 3);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 3, 4);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 3, 5);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 4, 1);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 4, 2);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 4, 3);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 4, 4);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 4, 5);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 5, 1);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 5, 2);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 5, 3);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 5, 4);

INSERT INTO t_role (
	role_id,
	department_id,
	position_id
)
VALUES
	(NULL, 5, 5);

-- 角色权限连接表插入
-- 考勤表插入
-- 绩效表插入
-- 用户角色连接表插入
-- 工资记录表插入
-- 考勤表插入
-- 加班表插入
COMMIT;

