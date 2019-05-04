
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->

<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-user-md"></i> ${emp.employeeName} 的员工资料</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                    <li><i class="icon_group"></i>人力资源管理</li>
                    <li><i class="fa fa-adjust"></i><a href="${pageContext.request.contextPath}/employee_check_allajax.jsp">员工信息管理</a></li>
                    <li><i class="fa fa-user-md"></i>查看员工信息</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <!-- profile-widget -->
            <div class="col-lg-12">
                <div class="profile-widget profile-widget-info">
                    <div class="panel-body">
                        <div class="col-lg-2 col-sm-2">
                            <h4>${emp.employeeName}</h4>
                            <div class="follow-ava">
                                <a data-toggle="modal" data-target="#headChange" id="headPoint">
                                <img id="headSculpture" src="${emp.employeeHeadSculpture}" onerror="javascript:this.src='${pageContext.request.contextPath}/img/profile-widget-avatar.jpg'" alt="">
                                </a>
                            </div>
                            <h6>${logEmployee.department.departmentName}</h6>
                        </div>
                        <div class="col-lg-4 col-sm-4 follow-info">
                            <p>${emp.department.departmentName}&nbsp;&nbsp;${emp.position.positionName}</p>
                            <p>${emp.employeeEmail}</p>
                            <p><i class="fa fa-phone">${emp.employeePhone}</i></p>
                            <h6>
                                <span><i class="icon_clock_alt"></i>11:05 AM</span>
                                <span><i class="icon_calendar"></i>25.10.13</span>
                                <span><i class="icon_pin_alt"></i>NY</span>
                            </h6>
                        </div>
                        <div class="col-lg-2 col-sm-6 follow-info weather-category">
                            <ul>
                                <li class="active">

                                    <i class="fa fa-comments fa-2x"> </i><br>
                                </li>

                            </ul>
                        </div>
                        <div class="col-lg-2 col-sm-6 follow-info weather-category">
                            <ul>
                                <li class="active">

                                    <i class="fa fa-bell fa-2x"> </i><br>
                                </li>

                            </ul>
                        </div>
                        <div class="col-lg-2 col-sm-6 follow-info weather-category">
                            <ul>
                                <li class="active">

                                    <i class="fa fa-tachometer fa-2x"> </i><br>
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- page start-->
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading tab-bg-info">
                        <ul class="nav nav-tabs">
                            <li class="active">

                                <a data-toggle="tab" href="#profile">
                                    <i class="icon-user"></i>
                                    <span style="position: relative; right: 20px;">详细信息</span>
                                </a>
                            </li>
                        </ul>
                    </header>

                    <div class="panel-body">
                        <div class="tab-content">
                            <!-- profile -->
                            <div id="profile" class="tab-pane active">
                                <section class="panel">
                                    <div class="bio-graph-heading">
                                        ${emp.employeeIntroduction}
                                    </div>
                                    <div class="panel-body bio-graph-info">
                                        <h1>详细资料</h1>
                                        <div class="row">
                                            <div class="bio-row">
                                                <p><span>姓名 </span>: ${emp.employeeName} </p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>在职状态 </span>: <span class="status">${emp.statusId}</span></p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>联系电话</span>: ${emp.employeePhone}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>电子邮箱 </span>: ${emp.employeeEmail}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>部门 </span>: ${emp.department.departmentName}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>职位 </span>: ${emp.position.positionName}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>出生日期 </span>: <span class="time">${emp.employeeBirthday}</span></p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>入职日期 </span>: <span class="time">${emp.employeeCreateTime}</span></p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>工号 </span>:  ${emp.employeeWorkId}</p>
                                            </div>
                                            <div class="bio-row">
                                               <p><span>直属上级 </span>:  <a href="#" id="toLeaderDetail">${emp.employeeLeaderName}</p></a>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                                <section>
                                    <div class="row">
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
</section>
<!--main content end-->
</section>
<script type="text/javascript">
    var statusData;
    $(function(){

        if(${emp.employeeLeader!=null}&&${emp.employeeLeader!=""}){
            $("#toLeaderDetail").attr("href","${pageContext.request.contextPath}/Employee/${emp.employeeLeader}.do?method=check");
        }
        dateConfig();
        queryStatusData();

    });


    function queryStatusData() {
        $.getJSON("/Status.do",function(data){
            statusData = data;
            statusConfigToText(statusData);
        });
    }

    function statusConfigToText(statusData){
        var $confi = $(".status");
        $.each(statusData,function(index,eleStatus){
            if($confi.text()==eleStatus.statusId){
                $confi.text(eleStatus.statusName);
            }
        });
    }

</script>
<script src="${pageContext.request.contextPath}/js/pmsJS/employeeDetail.js"></script>
<jsp:include page="commonBottom.jsp"/>