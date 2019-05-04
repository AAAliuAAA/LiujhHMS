<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> 人力资源 请假处理</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
          <li><i class="fa fa-bars"></i>考勤管理</li>
          <li><i class="fa fa-square-o"></i>请假申请录入</li>
        </ol>
      </div>
    </div>
    <!-- page start-->
    <div class="row">
      <form class="form-inline" id="hr_leave_search_form">
        <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
          <div class="col-md-3">
            <label style="position: relative;top:6px" class="pull-right control-label">根据请假开始日期搜索：</label>
          </div>
          <div class="col-md-3 form-group">
            <input type="datetime-local" class="form-control" name="leaveQueryStartDate" placeholder="开始时间"/>
          </div>
          <div class="col-md-1">
            <label style="position: relative;top:6px" class="pull-right">至</label>
          </div>
          <div class="form-group col-md-3">
            <input type="datetime-local" class="form-control" name="leaveQueryEndDate" placeholder="截止时间"/>
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
            需要人事部门录入的请假申请
          </header>
          <table class="table table-striped table-advance table-hover">
            <thead>
            <tr>
              <th><i class="icon_profile"></i> 申请人</th>
              <th> 申请人工号</th>
              <th> 审批人</th>
              <th> 审批人工号</th>
              <th><i class="icon_mail_alt"></i> 请假开始时间</th>
              <th><i class="icon_clock_alt"></i> 请假截止时间</th>
              <th><i class="icon_clock_alt"></i>请假理由</th>
            </tr>
            </thead>
            <tbody id="leave_hr_tab">
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
<script type="text/x-jquery-tmpl" id="hr_leave_tab_template">
        <tr>
            <td>${'${'}employee.employeeName}</td>
            <td>${'${'}employee.employeeWorkId}</td>
            <td>${'${'}employee.employeeLeaderName}</td>
            <td>${'${'}employee.employeeLeaderWorkId}</td>
            <td  class="hr_repend_leave_date">${'${'}leaveStartTime}</td>
            <td  class="hr_repend_leave_date">${'${'}leaveEndTime}</td>
            <td onmouseover='showLeaveReason(this);' onmouseout='hideLeaveReason(this);' id="label${'${'}leaveId}">鼠标悬浮查看详细
            <div id='tip${'${'}leaveId}' style='display:none;border:1px solid gray;border-radius: 5px;background-color:#F9F9F9;color:black;font-size:16px;line-height:16px;text-align:center;position: absolute;z-index:20;opacity:0.8;'><p>${'${'}leaveReason}</p></div>
            </td>
     </tr>
</script>
<script type="text/javascript">
  $(function () {
    pageInit(1);
  })

  function pageInit(pageNo) {
    var startDate = $("input[name='leaveQueryStartDate']").val();
    var endDate = $("input[name='leaveQueryEndDate']").val();
    var data = {
      startDate: startDate,
      endDate: endDate,
      pageNo: pageNo
    }
    var url = "/leave/hr/check.do";
    $.getJSON(url, data, function (data) {
      $("#leave_hr_tab").empty();
      if (data.list.length == 0 || data.list == null) {
        $("#leave_hr_tab").append("<tr><td colspan=\"8\">暂时没有请假申请</td></tr>");
        return;
      }
      $("#hr_leave_tab_template").tmpl(data.list).appendTo($("#leave_hr_tab"));
      leave_tab_appendDate($(".hr_repend_leave_date"));
      pageConfig(data);
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


  //请假理由 悬停显示与隐藏
  function showLeaveReason(target) {
    var idStr = target.id
    var divIdStr = idStr.replace(/^label/, 'tip');
    $("#" + divIdStr + "").css("display", "block");

  }

  function hideLeaveReason(target) {
    var idStr = target.id
    var divIdStr = idStr.replace(/^label/, 'tip');
    $("#" + divIdStr + "").css("display", "none");
  }


</script>

<jsp:include page="commonBottom.jsp"/>