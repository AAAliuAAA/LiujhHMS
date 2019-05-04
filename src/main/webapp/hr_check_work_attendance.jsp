<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> 考勤信息总览</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
          <li><i class="fa fa-bars"></i>员工考勤信息总览</li>
          <li><i class="fa fa-square-o"></i>员工打卡/加班信息</li>
        </ol>
      </div>
    </div>
    <!-- page start-->
    <div class="row">
      <form class="form-inline" id="hr_work_attendance_search_form">
        <div class="col-lg-12 col-md-10 col-sm-10 col-xs-10">
          <div class="col-md-1">
            <label style="position: relative;top:6px" class="pull-right control-label">日期：</label>
          </div>
          <div class="col-md-3 form-group">
            <input type="date" class="form-control" name="workAttendanceDate" placeholder="开始时间"/>
          </div>
          <div class="col-md-1">
            <label style="position: relative;top:6px" class="pull-right">部门：</label>
          </div>
          <div class="form-group col-md-2">
            <select name="departmentId" class="form-control m-bot15" required>
              <option selected="selected" id="chooseDept">请选择...</option>
              <!--在这里插入部门数据-->
            </select>
          </div>
          <div class="col-md-1">
            <label style="position: relative;top:6px" class="pull-right">工号：</label>
          </div>
          <div class="form-group col-md-3">
            <input type="number" class="form-control" name="employeeWorkId" placeholder="输入员工工号"/>
          </div>
          <div class="form-group col-md-1">
            <input type="button" class="btn btn-info" value="查找" onclick="pageInit(1)"/>
          </div>
        </div>
      </form>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <section class="panel">
          <header class="panel-heading">
            员工考勤信息 &nbsp; <span id="workAttendanceTargetDate"></span> &nbsp;&nbsp;&nbsp;&nbsp;<a
                                                                         href="javascript:workAttendanceRigidReload();"
                                                                         style="color: #688A7E;">刷新考勤表记录(严格模式)</a>
            <a class="pull-right"
                                                                         href="javascript:messageReminder();"
                                                                         style="color: #688A7E;">操作提示</a>
          </header>
          <table class="table table-striped table-advance table-hover">
            <thead>
            <tr>
              <th><i class="icon_profile"></i> 员工名称</th>
              <th> 员工工号</th>
              <th> 直属审批人</th>
              <th> 审批人工号</th>
              <th><i class="icon_clock_alt"></i> 上班打卡时间</th>
              <th><i class="icon_clock_alt"></i> 下班打卡时间</th>
              <th><i class="icon_mail_alt"></i> 加班时间</th>
              <th> 是否迟到</th>
              <th> 是否早退</th>
              <th><i class="icon_cogs"></i> 操作</th>
            </tr>
            </thead>
            <tbody id="hr_check_work_attendance_tab">
            </tbody>
          </table>
        </section>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="col-xs2"></div>
          <div class="col-xs-10">总页数： <span id="pages"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总记录数：
            <span id="total"></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前页：
            <span id="pageNum"></span></div>
        </div>
      </div>
      <div class="row">
        <div class="btn-row">
          <div class="col-md-5">
          </div>
          <div class="btn-toolbar col-lg-5 col-md-5 col-sm-6 col-xs-9">
            <div class="btn-group">
              <button class="btn btn-primary" id="prePage" onclick="">上一页</button>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="btn btn-primary nextPage" id="nextPage" onclick="">下一页</button>
          </div>
        </div>
      </div>
    </div>
    <!-- page end-->
  </section>
</section>
<!--main content end-->
</section>

<div class="modal fade" id="work_attendance_operation_modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
        <h4 class="modal-title">操作</h4>
      </div>
      <div class="modal-body">
        <div class="btn-group">
          <a class="btn btn-warning" href="javascript:workAttendanceAddLate()"><i class="icon_bag_alt"></i>&nbsp;添加迟到考勤异常</a>
          <a class="btn btn-danger" href="javascript:workAttendanceAddLeaveEarly()"><i class="icon_wallet_alt"></i>&nbsp;添加早退考勤异常</a>
          <a class="btn btn-info" href="javascript:workAttendanceRemoveAllOperation()"><i class="icon_tag_alt"></i>&nbsp;删除迟到和早退异常</a>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<script type="text/x-jquery-tmpl" id="hr_check_work_attendance_template">
        <tr>
            <td>${'${'}employeeName}</td>
            <td>${'${'}employeeWorkId}</td>
            <td>${'${'}employeeLeaderName}</td>
            <td>${'${'}employeeLeaderWorkId}</td>
            <td  class="check_time_repend_date">${'${'}checkInTime}</td>
            <td  class="check_time_repend_date">${'${'}checkOutTime}</td>
            <td>${'${'}workOverTimeHour}</td>
            <td class="statusOpt">${'${'}late}</td>
            <td class="statusOpt">${'${'}leaveEarly}</td>
            <td>
                <div class="btn-group">
                    <a class="btn btn-primary" href="javascript:workAttendanceOperation(${'${'}workAttendanceId})"><i class="icon_plus_alt2"></i>&nbsp;考勤操作</a>
                </div>
            </td>
     </tr>


</script>
<script type="text/javascript">
  var workAttendanceOperationId = null;

  $(function () {
    departmentConfig();
    pageInit(1);
  })

  function departmentConfig() {
    $.getJSON("/Department.do", function (data) {
      $.each(data, function (index, ele) {
        var $opt = $("<option value=" + ele.departmentId + ">" + ele.departmentName + "</option>");
        $("select[name='departmentId']").append($opt);
      });
    })
  }

  function pageInit(pageNo) {
    var url = "/workAttendance/hr.do";
    var date = $("input[name='workAttendanceDate']").val();
    if (date == null || date == "") {
      date = new Date().format("yyyy-MM-dd");
    }
    var departmentId = $("select[name='departmentId']").val();
    if (departmentId == "请选择...") {
      departmentId = null;
    }
    var employeeWorkId = $("input[name='employeeWorkId']").val();
    var data = {
      workAttendanceDate: date,
      departmentId: departmentId,
      employeeWorkId: employeeWorkId,
      pageNo:pageNo
    }
    $.getJSON(url, data, function (data) {
      //如果存在没有处理的申请，则当前员工的考勤当天无法查看 TODO bug
      $("#workAttendanceTargetDate").text(date);
      $("#hr_check_work_attendance_tab").empty();
      $("#hr_check_work_attendance_template").tmpl(data.page.list).appendTo($("#hr_check_work_attendance_tab"));
      pageConfig(data.page);
      workAttendanceCheckTimeRepend($(".check_time_repend_date"));
      workAttendanceStatusConfig();
    })
  }

  function workAttendanceOperation(workAttendanceId) {
    workAttendanceOperationId = workAttendanceId;
    $("#work_attendance_operation_modal").modal("show");
  }

  function workAttendanceAddLate() {
    var url = "/workAttendance/update/late/" + this.workAttendanceOperationId + ".do";
    $.ajax({
      url:url,
      type:"POST",
      success:function (data) {
        if(data == true){
          messageAlert("操作成功");
          pageInit(parseInt($("#pageNum").text()));
        }
      }
    })
    pageInit(parseInt($("#pageNum").text()));
  }

  function workAttendanceAddLeaveEarly() {
    var url = "/workAttendance/update/leave_early/" + this.workAttendanceOperationId + ".do";
    $.ajax({
      url:url,
      type:"POST",
      success:function (data) {
        if(data == true){
          messageAlert("操作成功");
          pageInit(parseInt($("#pageNum").text()));
        }
      }
    })
    pageInit(parseInt($("#pageNum").text()));
  }

  function workAttendanceRemoveAllOperation() {
    var url = "/workAttendance/delete/status/" + this.workAttendanceOperationId + ".do";
    $.ajax({
      url:url,
      type:"POST",
      success:function (data) {
        if(data == true){
          messageAlert("操作成功");
          pageInit(parseInt($("#pageNum").text()));
        }
      }
    })
  }

  function messageReminder() {
    messageAlert("早退和迟到在硬性自动生成考勤信息中，是由上班是否打卡和下班是否打卡决定的，系统硬性自动生成考勤信息只会\n" +
        "针对没有迟到或者早退现象的指定考勤记录进行操作，如果当前员工当天请假则也不会对其考勤信息进行操作" +
        "，如果在装载后要进行手动修改迟到或者早退信息，比如一个员工当天下班未打卡，但是想将它的考勤记录设置为无考勤异常，" +
        "则需要在清理异常后再让员工发起请假申请,将当前划入请假范围内，那么硬性自动生成考勤信息则不会对其生成考勤异常");
  }


  function pageConfig(data) {
    $("#pageNum").text(data.pageNum);
    $("#pages").text(data.pages);
    $("#total").text(data.total);
    var nextPage = data.nextPage;
    if (data.nextPage == 0) {
      nextPage = data.lastPage;
    }
    var prePage = data.prePage;
    if (data.prePage == 0) {
      prePage = data.firstPage;
    }
    $("#prePage").attr("onclick", "pageInit(" + prePage + ")");
    $("#nextPage").attr("onclick", "pageInit(" + nextPage + ")");
  }

  function workAttendanceStatusConfig() {
    var status = null;
    var url = "/workAttendance/late_leave_early_table_status.do";
    $.ajax({
      type:"GET",
      url:url,
      async:false,
      success:function (data) {
        status = data;
      }
    })
    console.log(status);
    var statusOpt =  $(".statusOpt");
    $.each(statusOpt, function (index, ele) {
      if($(ele).text() == status){
        $(ele).text("");
        $(ele).append("<i class='fa fa-dot-circle-o'></i>");
      }
    })
  }

  function workAttendanceRigidReload() {
    //硬性操作考勤表，慎用
   messageAlert("开始执行严格刷新考勤表记录，可能需要一点时间");
   $.ajax({
     type:"GET",
     url:"/workAttendance/updateWorkAttendanceLateAndLeaveEarly.do",
     success:function (data) {
       if(data == true){
         $("#common_msg").text("更新完成");
       }else{
         $("#common_msg").text("更新失败");
       }
     }
   })
  }

</script>
<jsp:include page="commonBottom.jsp"/>