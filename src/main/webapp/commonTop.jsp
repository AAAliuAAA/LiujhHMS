<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    <title>LJHPMS</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${pageContext.request.contextPath}/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lte-ie7.js"></script>
    <script src="${pageContext.request.contextPath}/js/commonjs.js"></script>
    <![endif]-->
    <style>
        #myattendance{
            background-color: #394A59;
        }
        #myprofile:hover{
            background-color: #05526F ;
        }
        #mysalary{
            background-color: #394A59;
        }
        #mysalary:hover{
            background-color: #242E36;
        }
        #myattendance:hover{
            background-color: #242E36;
        }
        a{
            color: #1A2732;
        }
        #toPage a:hover{
            text-decoration:underline;
        }
        th{
            text-align: center;
        }
        td{
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>

<body>
<!-- container section start -->
<section id="container" class="">
    <!--header start-->

    <header class="header dark-bg">
        <div class="toggle-nav">
            <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
        </div>

        <!--logo start-->
        <a href="${pageContext.request.contextPath}/index.jsp" class="logo">LJH <span class="lite">PMS</span></a>
        <!--logo end-->

        <div class="top-nav notification-row">
            <!-- notificatoin dropdown start-->
            <ul class="nav pull-right top-menu">
                <!-- task notificatoin start -->
                <!-- inbox notificatoin start-->
                <li id="alert_notificatoin_bar" class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                        <i class="icon-bell-l"></i>
                        <span class="badge bg-important">7</span>
                    </a>
                    <ul class="dropdown-menu extended notification">
                        <div class="notify-arrow notify-arrow-blue"></div>
                        <li>
                            <p class="blue">You have 4 new notifications</p>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-primary"><i class="icon_profile"></i></span>
                                Friend Request
                                <span class="small italic pull-right">5 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-warning"><i class="icon_pin"></i></span>
                                John location.
                                <span class="small italic pull-right">50 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-danger"><i class="icon_book_alt"></i></span>
                                Project 3 Completed.
                                <span class="small italic pull-right">1 hr</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-success"><i class="icon_like"></i></span>
                                Mick appreciated your work.
                                <span class="small italic pull-right"> Today</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">See all notifications</a>
                        </li>
                    </ul>
                </li>
                <!-- alert notification end-->
                <!-- user login dropdown start-->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" style="height: 40px; width: 40px"  src="${sessionScope.logEmployee.employeeHeadSculpture}">
                            </span>
                        <span class="username" style="font-size: 16px;">&nbsp;${sessionScope.logEmployee.employeeName}&nbsp;</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu extended logout">
                        <div class="log-arrow-up"></div>
                        <li class="eborder-top">
                            <a href="${pageContext.request.contextPath}/employee_self_detail.jsp"><i class="icon_profile"></i>我的资料</a>
                        </li>
                        <li>
                            <a href="#"><i class="icon_mail_alt"></i>我的邮箱</a>
                        </li>
                        <li>
                            <a href="#"><i class="icon_contacts_alt"></i>我在xx公司</a>
                        </li>

                        <li>
                            <a href="#"><i class="icon_chat_alt"></i>通知和提醒</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/employee_updatePassword.jsp"><i class="icon_lock-open_alt"></i>修改密码</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/Employee/logout.do"><i class="icon_key_alt"></i> 退出登录</a>
                        </li>
                    </ul>
                </li>
                <!-- user login dropdown end -->
            </ul>
            <!-- notificatoin dropdown end-->
        </div>
    </header>
    <!--header end-->

    <!--sidebar start-->
    <aside>
        <div id="sidebar"  class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li class="">
                    <a class="" href="${pageContext.request.contextPath}/index.jsp">
                        <i class="icon_house_alt"></i>
                        <span>我的主页</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:leaveModalShow();" class="">
                        <i class="icon_document_alt"></i>
                        <span>请假申请</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:overtimeModal();" class="">
                        <i class="icon_desktop"></i>
                        <span>加班申请</span>
                    </a>
                    <!--<ul class="sub">
                        <li><a class="" href="general.html">Components</a></li>
                        <li><a class="" href="buttons.html">Buttons</a></li>
                        <li><a class="" href="grids.html">Grids</a></li>
                    </ul>-->
                </li>
                <shiro:hasAnyRoles name="cm_probation,hr_probation">
                <li>
                    <a href="${pageContext.request.contextPath}/regular_worker_assignment.jsp" class="">
                        <i class="icon_pencil-edit"></i>
                        <span>转正申请</span>
                    </a>
                </li>
                </shiro:hasAnyRoles>

                <shiro:hasAnyRoles name="hr_manager,hr_internship,hr_worker,hr_chargeman,hr_probation">
                <li class="sub-menu">
                    <a class="" href="javascript:;">
                        <i class="icon_group"></i>
                        <span>人力资源管理</span>
                        <span class="menu-arrow arrow_carrot-right"></span>
                    </a>
                    <ul class="sub">
                        <shiro:hasPermission name="hr:add_employee">
                        <li><a class="active" href="${pageContext.request.contextPath}/employee_add.jsp">新增员工</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="hr:view_employee">
                        <li><a class="" href="${pageContext.request.contextPath}/employee_check_allajax.jsp">员工信息管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="hr:full_member_record">
                        <li><a class="" href="${pageContext.request.contextPath}/employee_regular_worker_application_process.jsp">转正申请录入</a></li>
                        </shiro:hasPermission>
                    </ul>
                </li>
                <shiro:hasPermission name="hr:work_attendance_record">
                <li class="sub-menu">
                    <a class="" href="javascript:;">
                        <i class="icon_piechart"></i>
                        <span>员工考勤</span>
                        <span class="menu-arrow arrow_carrot-right"></span>
                    </a>
                    <ul class="sub">
                        <li><a class="active" href="${pageContext.request.contextPath}/hr_check_work_attendance.jsp">员工考勤信息总览</a></li>
                        <li><a class="" href="${pageContext.request.contextPath}/hr_check_employee_leave.jsp">员工请假统计</a></li>
                        <li><a class="" href="${pageContext.request.contextPath}/hr_acception_employee_leave.jsp">请假申请录入</a></li>
                        <li><a class="" href="${pageContext.request.contextPath}/hr_acception_work_overtime.jsp">加班申请录入</a></li>
                    </ul>
                </li>
                </shiro:hasPermission>
                    <%--<shiro:hasPermission name="hr:salary_record">--%>
                <%--<li class="sub-menu">--%>
                    <%--<a href="javascript:;" class="">--%>
                        <%--<i class="icon_table"></i>--%>
                        <%--<span>薪资测算</span>--%>
                        <%--<span class="menu-arrow arrow_carrot-right"></span>--%>
                    <%--</a>--%>
                    <%--<ul class="sub">--%>
                        <%--<shiro:hasPermission name="hr:performance_management">--%>
                        <%--<li><a class="" href="">绩效配置</a></li>--%>
                        <%--</shiro:hasPermission>--%>
                        <%--<li><a class="" href="">工资表总览</a></li>--%>
                        <%--<li><a class="" href="">当月工资录入</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                    <%--</shiro:hasPermission>--%>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="hr_manager,hr_chargeman,cm_manager,cm_chargeman">
                <li class="sub-menu ">
                    <a href="javascript:;" class="">
                        <i class="icon_documents_alt"></i>
                        <span>申请处理</span>
                        <span class="menu-arrow arrow_carrot-right"></span>
                    </a>
                    <ul class="sub">
                        <li><a class="" href="${pageContext.request.contextPath}/manager_acception_leave.jsp">处理请假申请</a></li>
                        <li><a class="" href="${pageContext.request.contextPath}/manager_acception_work_overtime.jsp"><span>处理加班申请</span></a></li>
                        <shiro:hasAnyRoles name="hr_manager,cm_manager">
                        <li><a class="" href="${pageContext.request.contextPath}/manager_regular_worker_conduct.jsp"><span>处理转正申请</span></a></li>
                        </shiro:hasAnyRoles>
                    </ul>
                </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="hr_manager,cm_manager,hr_chargeman,cm_chargeman">
                <li class="sub-menu ">
                    <a href="javascript:;" class="">
                        <i class="icon_cogs"></i>
                        <span>我的部门</span>
                        <span class="menu-arrow arrow_carrot-right"></span>
                    </a>
                    <ul class="sub">
                        <li><a class="" href="${pageContext.request.contextPath}/leader_check_employee.jsp">下属员工信息</a></li>
                        <%--<shiro:hasPermission name="hr:salary_join_config">--%>
                        <%--<li><a class="" href="">员工基础薪资配置</a></li>--%>
                        <%--</shiro:hasPermission>--%>
                    </ul>
                </li>
                </shiro:hasAnyRoles>
                <shiro:hasPermission name="hr:update_work_attendance">
                <li class="sub-menu ">
                    <a href="javascript:;" class="">
                        <i class="icon_calendar"></i>
                        <span>工作时间设置</span>
                        <span class="menu-arrow arrow_carrot-right"></span>
                    </a>
                    <ul class="sub">
                        <li><a class="" href="/WorkAttendanceConfig/queryAllWithCreator.do">上下班时间配置</a></li>
                        <li><a class="" href="/holiday_config.jsp">节假日配置</a></li>
                    </ul>
                </li>
                </shiro:hasPermission>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.scrollTo.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.nicescroll.js" type="text/javascript"></script><!--custome script for all page-->
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.min.js"></script>