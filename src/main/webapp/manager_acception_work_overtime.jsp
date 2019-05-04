<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> 加班申请处理</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
          <li><i class="fa fa-bars"></i>申请处理</li>
          <li><i class="fa fa-square-o"></i>加班申请处理</li>
        </ol>
      </div>
    </div>
    <!-- page start-->
    <div class="row">
      <div class="col-lg-12">
        <section class="panel">
          <header class="panel-heading">
            需要处理的 ${sessionScope.logEmployee.department.departmentName} 加班申请
          </header>

          <table class="table table-striped table-advance table-hover">
            <thead>
            <tr>
              <th><i class="icon_profile"></i> 申请人</th>
              <th> 申请人工号</th>
              <th><i class="icon_mail_alt"></i> 加班日期</th>
              <th><i class="icon_clock_alt"></i> 加班时长</th>
              <th><i class="icon_cogs"></i> 操作</th>
            </tr>
            </thead>
            <tbody id="work_overtime_leader_process">

            </tbody>
          </table>
        </section>
      </div>
    </div>
    <!-- page end-->
  </section>
</section>
<!--main content end-->
<script type="text/x-jquery-tmpl" id="manager_over_time_template">
        <tr>
            <td>${'${'}employee.employeeName}</td>
            <td>${'${'}employee.employeeWorkId}</td>
            <td class="manager_repend_overtime_date">${'${'}workOvertimeDate}</td>
            <td>${'${'}workOvertimeHour}</td>
            <td>
                <div class="btn-group">
                    <a class="btn btn-primary" href="javascript:managerPass(${'${'}workOvertimeId})"><i class="icon_plus_alt2"></i>&nbsp;通过</a>
                    <a class="btn btn-danger" href="javascript:managerRejection(${'${'}workOvertimeId})"><i class="icon_close_alt2"></i>&nbsp;打回</a>
                </div>
            </td>
     </tr>

</script>
</section>
<script type="text/javascript">
  $(function () {
    managerOverTimeAppendData();
  })

  function managerPass(workOvertimeId) {
    var url = "/work_overtime/managerPass/" + workOvertimeId + ".do";
    $.ajax({
      type: "POST",
      url: url,
      success: function (data) {
        managerOverTimeAppendData();
        if (data == true) {
          $("#common_msg").text("加班申请通过成功");
        } else {
          $("#common_msg").text("加班申请通过失败");
        }
        $("#common_modal").modal("show");
      }
    })
  }

  function managerRejection(workOvertimeId) {
    var url = "/work_overtime/managerReject/" + workOvertimeId + ".do";
    $.ajax({
      type: "POST",
      url: url,
      success: function (data) {
        managerOverTimeAppendData();
        if (data == true) {
          $("#common_msg").text("加班申请打回成功，员工现在已经可以创建当日新的加班申请");
        } else {
          $("#common_msg").text("加班申请打回失败");
        }
        $("#common_modal").modal("show");
      }
    })
  }

  function managerOverTimeAppendData() {
    $("#work_overtime_leader_process").empty();
    var leaderId = ${sessionScope.logEmployee.employeeId};
    var url = "/work_overtime/leaderId/" + leaderId + ".do";
    $.getJSON(url, function (data) {
      $("#work_overtime_leader_process").empty();
      if (data.length == 0 || data == null) {
        $("#work_overtime_leader_process").append("<tr><td colspan=\"5\">暂时没有需要处理的加班申请</td></tr>");
        return;
      }
      $("#manager_over_time_template").tmpl(data).appendTo($("#work_overtime_leader_process"));
      commonRependDate($(".manager_repend_overtime_date"));
    })
  }

</script>
<jsp:include page="commonBottom.jsp"/>