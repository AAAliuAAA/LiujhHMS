<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> Pages</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
          <li><i class="fa fa-bars"></i>申请处理</li>
          <li><i class="fa fa-square-o"></i>请假申请处理</li>
        </ol>
      </div>
    </div>
    <!-- page start-->
    <div class="row">
      <div class="col-lg-12">
        <section class="panel">
          <header class="panel-heading">
            需要处理的 ${sessionScope.logEmployee.department.departmentName} 请假申请
          </header>

          <table class="table table-striped table-advance table-hover">
            <thead>
            <tr>
              <th><i class="icon_profile"></i> 申请人</th>
              <th> 申请人工号</th>
              <th><i class="icon_mail_alt"></i> 请假开始时间</th>
              <th><i class="icon_clock_alt"></i> 请假截止时间</th>
              <th><i class="icon_clock_alt"></i> 申请理由</th>
              <th><i class="icon_cogs"></i> 操作</th>
            </tr>
            </thead>
            <tbody id="leave_assignment_tab">
            </tbody>
          </table>
        </section>
      </div>
    </div>
    <!-- page end-->
  </section>
</section>
<!--main content end-->
<script type="text/x-jquery-tmpl" id="leave_assignment_tab_template">
        <tr>
            <td>${'${'}employee.employeeName}</td>
            <td>${'${'}employee.employeeWorkId}</td>
            <td class="manager_repend_leave_date">${'${'}leaveStartTime}</td>
            <td class="manager_repend_leave_date">${'${'}leaveEndTime}</td>
            <td>${'${'}leaveReason}</td>
            <td>
                <div class="btn-group">
                    <a class="btn btn-primary" href="javascript:managerPass(${'${'}leaveId})"><i class="icon_plus_alt2"></i>&nbsp;通过</a>
                    <a class="btn btn-danger" href="javascript:managerRejection(${'${'}leaveId})"><i class="icon_close_alt2"></i>&nbsp;打回</a>
                </div>
            </td>
     </tr>
</script>
</section>
<script type="text/javascript">
  $(function () {
    appendApplicationData();
  })

  function appendApplicationData() {
    var leaderId = ${logEmployee.employeeId};
    var url = "/leave/leader/" + leaderId + ".do";
    $.getJSON(url, function (data) {
      $("#leave_assignment_tab").empty();
      if (data.length == 0 || data == null) {
        $("#leave_assignment_tab").append("<tr><td colspan=\"6\">暂时没有需要处理的请假申请</td></tr>");
        return;
      }
      $("#leave_assignment_tab_template").tmpl(data).appendTo($("#leave_assignment_tab"));
      leave_tab_appendDate($(".manager_repend_leave_date"));
    })
  }
  function managerPass(leaveId) {
    var url = "/leave/leader/pass/"+leaveId+".do";
    $.ajax({
      type: "POST",
      async: true,
      url:url,
      success: function (data) {
        console.log(data);
        if (data == true) {
          messageAlert("员工请假信息通过成功");
        } else {
          messageAlert("员工请假信息通过失败");
        }
        appendApplicationData();
      }
    })
  }

  function managerRejection(leaveId) {
    var url = "/leave/leader/reject/"+leaveId+".do";
    $.ajax({
      type:"POST",
      async:true,
      url:url,
      success:function(data){
        if(data == true){
          messageAlert("员工请假信息驳回成功");
        }
        appendApplicationData();
      }
    })
  }


</script>
<jsp:include page="commonBottom.jsp"/>