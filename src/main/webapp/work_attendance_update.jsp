<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/10
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-bars"></i> 上下班时间设置</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
                    <li><i class="fa fa-bars"></i>工作时间设置</li>
                    <li><i class="fa fa-check-square-o"></i>上下班时间设置</li>
                </ol>
            </div>
        </div>
        <!-- page start-->
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <section class="panel">
                    <div class="panel-body progress-panel">
                        <div class="row">
                            <div class="col-lg-8 task-progress pull-left">
                                <h1>上下班考勤时间配置</h1>
                            </div>
                            <div class="col-lg-4">
                                <span class="profile-ava pull-right">
                                        <img alt="" class="simple" style="height: 40px;width: 40px" src="${logEmployee.employeeHeadSculpture}">
                                        ${logEmployee.employeeName}
                                </span>
                            </div>
                        </div>
                    </div>
                    <table class="table table-hover personal-task">
                        <tbody>
                        <tr>
                            <td>上班时间</td>
                            <td>
                                下班时间
                            </td>
                            <td>
                                开始日期
                            </td>
                            <td>
                                结束日期
                            </td>
                            <td>
                                备注
                            </td>
                            <td>
                                创建人（工号）
                            </td>
                            <td style="position: relative;right: 100px">
                                当前使用
                            </td>
                            <%--<td>--%>
                                <%--操作--%>
                            <%--</td>--%>
                        </tr>
                        <c:forEach var="workAttendanceConfig" items="${workAttendanceConfigPages.list}">
                        <tr>
                            <td>${workAttendanceConfig.lastCheckInTime}</td>
                            <td>
                                    ${workAttendanceConfig.earlyCheckOutTime}
                            </td>
                            <td class="dateConfi">
                                ${workAttendanceConfig.workAttendanceConfigStartDate}
                            </td>
                            <td class="dateConfi">
                                ${workAttendanceConfig.workAttendanceConfigEndDate}
                            </td>
                            <td>
                                <span class="badge bg-success">${workAttendanceConfig.configComment}</span>
                            </td>
                            <td>
                                ${workAttendanceConfig.employeeCreator.employeeWorkId}
                            </td>
                            <td>
                                <div class="col-md-3"></div>
                                <span>
                                    <c:if test="${workAttendanceConfig.isUsing==1}">
                                      <i class="fa fa-check pull-left"></i>
                                    </c:if>
                                    <c:if test="${workAttendanceConfig.isUsing==0}">
                                        <i class="fa fa-times pull-left"></i>
                                    </c:if>
                                  </span>
                                <div class="col-md-3"></div>
                            </td>
                            <%--<td>--%>
                                <%--<span>--%>
                                    <%--<c:if test="${workAttendanceConfig.isUsing==0}">--%>
                                    <%--<button class="btn btn-danger">使用</button>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${workAttendanceConfig.isUsing==1}">--%>
                                        <%--<button class="btn btn-danger" style="visibility: hidden">使用</button>--%>
                                    <%--</c:if>--%>
                                <%--</span>--%>
                            <%--</td>--%>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
                <div class="row">
                    <div class="col-md-3">
                        <span>总页数：&nbsp;&nbsp;${workAttendanceConfigPages.pages}</span>
                        <span>总记录数：&nbsp;&nbsp;${workAttendanceConfigPages.total}</span>
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-2"></div>
                        <div class="col-md-4"><button class="btn btn-info" id="prePage">上一页</button></div>
                        <div class="col-md-4"><button class="btn btn-info" id="nextPage">下一页</button></div>
                    </div>
                </div>
                <div class="row" style="height: 50px"></div>
                <div class="row">
                    <div class="col-lg-12">
                        <section class="panel">
                            <header class="panel-heading">
                                创建上下班时间配置&nbsp;&nbsp;<a href="javascript:showHelp()" style="color: #00aced">查看帮助</a>
                                <a href="javascript:updateConfig()" class="pull-right" style="color: #00aced">手动更新上下班时间配置</a>
                            </header>
                            <div class="panel-body">
                                <form class="form-inline" method="post" action="/WorkAttendanceConfig/addWorkAttendanceConfig.do" role="form">
                                    <input type="hidden" name="creator" value="${sessionScope.logEmployee.employeeId}"/>
                                    <div class="form-group"><label>上班时间：</label></div>
                                    <div class="form-group">
                                        <input type="time" class="form-control" placeholder="上班时间" name="lastCheckInTime" required>
                                    </div>
                                    <div class="form-group"><label>下班时间：</label></div>
                                    <div class="form-group">
                                        <input type="time" class="form-control" placeholder="下班时间" name="earlyCheckOutTime" required>
                                    </div>
                                    <div class="form-group"><label>开始日期</label></div>
                                    <div class="form-group">
                                        <input type="date" class="form-control" placeholder="开始日期" name="workAttendanceConfigStartDate" required>
                                    </div>
                                    <div class="form-group"><label>备注：</label></div>
                                    <div class="form-group">
                                        <textarea width="30px" id="workAttendanceConfigComment" class="form-control" style="width: 600px;height: 36px;" name="configComment"></textarea>
                                    </div>
                                    <input type="submit" id="formSub" class="btn btn-primary pull-right" value="确定"/>
                                </form>

                            </div>
                        </section>
                    </div>
                </div>
        </div>
        </div>
        <!-- page end-->
    </section>
</section>
<!--main content end-->

<div class="modal fade" id="alertModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">帮助</h4>
            </div>
            <div class="modal-body" id="msg">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" style="visibility: hidden" id="confirm">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="dismiss">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</section>

<script type="text/javascript">
    $(function () {
        $("#prePage").click(function () {
            window.location.href ="/WorkAttendanceConfig/queryAllWithCreator.do?pageNum=${workAttendanceConfigPages.prePage}"
        });
        $("#nextPage").click(function () {
            window.location.href ="/WorkAttendanceConfig/queryAllWithCreator.do?pageNum=${workAttendanceConfigPages.nextPage}"
        });
        formValidate();
        dateConfig();
    })

    function formValidate() {
        $("form").submit(function () {
            var checkIn = $("input[name='lastCheckInTime']").val();
            var checkOut = $("input[name='earlyCheckOutTime']").val();
            var startDate = new Date($("input[name='workAttendanceConfigStartDate']").val());
            var today = new Date((new Date()).format("yyyy-MM-dd"));
            if(checkIn>=checkOut){
                $("#msg").html("<p>上班时间不能大于下班时间</p>");
                $("#alertModal").modal("show");
                return false;
            }else if(today.getTime()>startDate.getTime()||today.getTime()==startDate.getTime()){
                $("#msg").html("<p>开始日期必须大于今天</p>");
                $("#alertModal").modal("show");
                return false;
            }else{
                var flag = dateValidation();
                console.log(flag);
                if(flag){
                    return true;
                }else{
                    return false;
                }
            }
        })
    }

    var queryFlag;
    //查询当前插入的开始日期是否合理（即是否在当前所有配置中最晚的开始日期之后）
    function dateValidation(){
        var startDate = $("input[name='workAttendanceConfigStartDate']").val();
        $.ajax({
            type:"get",
            url:"/WorkAttendanceConfig/addWorkAttendanceConfigValidation.do",
            async:false,
            data:"newStartDate="+startDate,
            success:function(data){
                if(data == 0){
                    $("#msg").html("<p>插入的开始日期必须大于当前最晚配置的开始日期</p>");
                    $("#alertModal").modal("show");
                    queryFlag = false;
                }else{
                    queryFlag =  true;
                }
            }
        });
        return queryFlag;
    }


    function dateConfig() {
        var $dateStrArray=$(".dateConfi");
        $.each($dateStrArray,function(index,ele){
           var $tempDay = new Date($(ele).text());
           var a = $(ele).text();
           if(ele.innerText==""){
               $(ele).text("...");
           }else{
               $(ele).text($tempDay.format("yyyy-MM-dd"));
           }
        });
    }

    function showHelp(){
        $("#confirm").css("visibility","hidden");
        $("#dismiss").text("关闭");
        $("#msg").html("<p>只可以设置开始时间，不可以设置截止时间，设置开始时间并创建配置后，系统将自动将当前使用的上下班时间配置的截止时间设置为当前指定的开始时间的前一天</p>" +
            "<p>比如当前使用的配置到2018年2月6日止，然后新创建了一个配置设置开始时间为2018年5月5日</p>" +
            "<p>那么旧的配置的结束时间就会被设置为2018年5月4日</p>")
        $("#alertModal").modal("show");
    }
    function updateConfig() {
        $("#msg").text("手动处理开始时间可能会导致薪资表和考勤配置错误，确认执行？");
        $("#dismiss").text("取消");
        $("#confirm").click(function () {
            window.location.href = "/WorkAttendanceConfig/updateWorkAttendanceConfig.do";
        });
        $("#confirm").css("visibility","visible");
        $("#alertModal").modal("show");
    }

</script>
<jsp:include page="commonBottom.jsp"/>