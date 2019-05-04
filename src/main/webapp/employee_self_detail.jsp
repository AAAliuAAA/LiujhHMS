<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->

<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-user-md"></i> ${sessionScope.logEmployee.employeeName} 的员工资料</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                 <li><i class="fa fa-user-md"></i>查看我的信息</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <!-- profile-widget -->
            <div class="col-lg-12">
                <div class="profile-widget profile-widget-info">
                    <div class="panel-body">
                        <div class="col-lg-2 col-sm-2">
                            <h4>${logEmployee.employeeName}</h4>
                            <div class="follow-ava">
                                <a data-toggle="modal" data-target="#headChange" id="headPoint">
                                <img id="headSculpture" src="${logEmployee.employeeHeadSculpture}" onerror="javascript:this.src='${pageContext.request.contextPath}/img/profile-widget-avatar.jpg'" alt="">
                                </a>
                            </div>
                            <h6>${logEmployee.department.departmentName}</h6>
                        </div>
                        <div class="col-lg-4 col-sm-4 follow-info">
                            <p>${logEmployee.department.departmentName}&nbsp;&nbsp;${logEmployee.position.positionName}</p>
                            <p>${logEmployee.employeeEmail}</p>
                            <p><i class="fa fa-phone">${logEmployee.employeePhone}</i></p>
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
                            <!-- profile -->
                            <div id="profile" class="tab-pane active">
                                <section class="panel">
                                    <div class="bio-graph-heading">
                                        ${logEmployee.employeeIntroduction}
                                    </div>
                                    <div class="panel-body bio-graph-info">
                                        <h1>详细资料</h1>
                                        <div class="row">
                                            <div class="bio-row">
                                                <p><span>姓名 </span>: ${logEmployee.employeeName} </p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>在职状态 </span>:<span class="status"> ${logEmployee.statusId}</span></p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>联系电话</span>: ${logEmployee.employeePhone}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>电子邮箱 </span>: ${logEmployee.employeeEmail}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>部门 </span>: ${logEmployee.department.departmentName}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>职位 </span>: ${logEmployee.position.positionName}</p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>出生日期 </span>: <span class="time">${logEmployee.employeeBirthday}</span></p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>入职日期 </span>: <span class="time">${logEmployee.employeeCreateTime}</span></p>
                                            </div>
                                            <div class="bio-row">
                                                <p><span>工号 </span>:  ${logEmployee.employeeWorkId}</p>
                                            </div>
                                            <div class="bio-row">
                                               <p><span>直属上级 </span>:  <a href="#" id="toLeaderDetail">${logEmployee.employeeLeaderName}</p></a>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                                <section>
                                    <div class="row">
                                        <button class="btn btn-link btn-block" onclick="uptSelfInfo()">修改个人信息</button>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- page end-->
        <div class="modal fade" id="headChange">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">上传头像</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-group" action="${pageContext.request.contextPath}/Employee/headSulptureUpload.do" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="employeeUptId" value="${logEmployee.employeeId}"/>
                            <input type="hidden" name="uploadUser" value="self"/>
                            <div class="row">
                                <div class="col-lg-9">
                                    <input single class="btn btn-sm btn-default form-control" accept="image/jpg,image/png" type="file" value="" name="headSculpture" name="headSculpture"/>
                                </div>
                                <div class="col-lg-2">
                                    <button class="btn btn-primary">上传</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </section>
</section>
<!--main content end-->
</section>
<script>
    var statusData;
    $(function(){
        <shiro:hasAnyRoles name="hr_manager,hr_worker,hr_chargeman,hr_internship">
        if(${logEmployee.employeeLeader!=null}&&${logEmployee.employeeLeader!=""}){
            $("#toLeaderDetail").attr("href","${pageContext.request.contextPath}/Employee/${logEmployee.employeeLeader}.do?method=check");
        }
        </shiro:hasAnyRoles>
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
    function uptSelfInfo() {
        location.href="${pageContext.request.contextPath}/employee_update_selfInfo.jsp";
    }
</script>
<script src="${pageContext.request.contextPath}/js/pmsJS/employeeDetail.js"></script>
<jsp:include page="commonBottom.jsp"/>