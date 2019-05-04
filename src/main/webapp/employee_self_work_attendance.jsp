<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-bars"></i> 我的考勤信息</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
                    <li><i class="fa fa-bars"></i>我的考勤信息</li>
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
                        员工考勤信息 &nbsp; <span id="workAttendanceTargetDate"></span>
                    </header>
                    <table class="table table-striped table-advance table-hover">
                        <thead>
                        <tr>
                            <th><i class="icon_profile"></i> 员工名称</th>
                            <th>日期</th>
                            <th> 直属审批人</th>
                            <th> 审批人工号</th>
                            <th><i class="icon_clock_alt"></i> 上班打卡时间</th>
                            <th><i class="icon_clock_alt"></i> 下班打卡时间</th>
                            <th><i class="icon_mail_alt"></i> 加班时间</th>
                            <th> 迟到</th>
                            <th> 早退</th>
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


<script type="text/x-jquery-tmpl" id="hr_check_work_attendance_template">
        <tr>
            <td>${'${'}employeeName}</td>
            <td class="self_work_attendance_repend_date">${'${'}workAttendanceDate}</td>
            <td>${'${'}employeeLeaderName}</td>
            <td>${'${'}employeeLeaderWorkId}</td>
            <td  class="check_time_repend_date">${'${'}checkInTime}</td>
            <td  class="check_time_repend_date">${'${'}checkOutTime}</td>
            <td>${'${'}workOverTimeHour}</td>
            <td class="statusOpt">${'${'}late}</td>
            <td class="statusOpt">${'${'}leaveEarly}</td>
     </tr>
</script>
<script type="text/javascript">
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
    var url = "/workAttendance/self.do";
    var date = $("input[name='workAttendanceDate']").val();
    var employeeWorkId = $("input[name='employeeWorkId']").val();
    var data = {
      workAttendanceDate: date,
      departmentId: null,
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
      commonRependDate($(".self_work_attendance_repend_date"));
      workAttendanceStatusConfig();
    })
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
    var statusOpt =  $(".statusOpt");
    $.each(statusOpt, function (index, ele) {
      if($(ele).text() == status){
        $(ele).text("");
        $(ele).append("<i class='fa fa-dot-circle-o'></i>");
      }
    })
  }
</script>
<jsp:include page="commonBottom.jsp"/>