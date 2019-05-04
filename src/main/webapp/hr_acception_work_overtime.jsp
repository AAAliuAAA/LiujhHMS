<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> Pages</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="/index.jsp">主页</a></li>
          <li><i class="fa fa-bars"></i>员工考勤</li>
          <li><i class="fa fa-square-o"></i>加班申请录入</li>
        </ol>
      </div>
    </div>
    <div class="row">
      <form class="form-inline" id="work_overtime_search_form">
        <div class="col-lg-8 col-md-8 col-sm-10 col-xs-10">
          <div class="col-md-2">
            <label style="position: relative;top:6px" class="pull-right control-label">开始日期：</label>
          </div>
          <div class="col-md-3 form-group">
            <input type="date" class="form-control" name="startDate" placeholder="开始时间"/>
          </div>
          <div class="col-md-2">
            <label style="position: relative;top:6px" class="pull-right">截止日期：</label>
          </div>
          <div class="form-group col-md-3">
            <input type="date" class="form-control" name="endDate" placeholder="截止时间"/>
          </div>
          <div class="form-group col-md-1">
            <input type="button" class="btn btn-info" value="查找" onclick="pageInit(1)"/>
          </div>
        </div>
      </form>
    </div>
    <div class="row" style="height: 15px;"></div>
    <!-- page start-->
    <div class="row">
      <div class="col-lg-12">
        <section class="panel">
          <header class="panel-heading">
            需要人事部门录入的加班申请
          </header>
          <table class="table table-striped table-advance table-hover">
            <thead>
            <tr>
              <th><i class="icon_profile"></i> 申请人</th>
              <th> 申请人工号</th>
              <th> 审批人</th>
              <th> 审批人工号</th>
              <th><i class="icon_mail_alt"></i> 加班日期</th>
              <th><i class="icon_clock_alt"></i> 加班时长</th>
              <th><i class="icon_cogs"></i> 操作</th>
            </tr>
            </thead>
            <tbody id="work_overtime_hr_tab">
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
<script type="text/x-jquery-tmpl" id="hr_over_time_template">
        <tr>
            <td>${'${'}employee.employeeName}</td>
            <td>${'${'}employee.employeeWorkId}</td>
            <td>${'${'}employee.employeeLeaderName}</td>
            <td>${'${'}employee.employeeLeaderWorkId}</td>
            <td  class="hr_repend_overtime_date">${'${'}workOvertimeDate}</td>
            <td>${'${'}workOvertimeHour}</td>
            <td>
                <div class="btn-group">
                    <a class="btn btn-primary" href="javascript:hrPass(${'${'}workOvertimeId})"><i class="icon_plus_alt2"></i>&nbsp;通过</a>
                    <a class="btn btn-danger" href="javascript:hrRejection(${'${'}workOvertimeId})"><i class="icon_close_alt2"></i>&nbsp;打回</a>
                </div>
            </td>
     </tr>




</script>
<script type="text/javascript">
  $(function () {
    pageInit(1);
  })

  function pageInit(pageNo) {
    var url = "/work_overtime/hr/date.do";
    // var  data = $("work_overtime_search_form").serialize();
    var startDate = $("input[name='startDate']").val();
    var endDate = $("input[name='endDate']").val();
    //两个变量在没有填入的时候为空字符串
    var data = {
      pageNo: pageNo,
      startDate: startDate,
      endDate: endDate
    }
    $.ajax({
      type: "GET",
      data: data,
      url: url,
      async: true,
      success: function (data) {
        $("#work_overtime_hr_tab").empty();
        if (data.list.length == 0 || data.list == null) {
          $("#work_overtime_hr_tab").append("<tr><td colspan=\"7\">暂时没有需要处理的加班申请</td></tr>");
          return;
        }
        $("#hr_over_time_template").tmpl(data.list).appendTo($("#work_overtime_hr_tab"));
        pageConfig(data);
        commonRependDate($(".hr_repend_overtime_date"));
      }
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

  function hrPass(workOvertimeId) {
    var url = "/work_overtime/hr/pass/"+workOvertimeId+".do";
    $.ajax({
      type:"POST",
      url:url,
      async:true,
      success:function (data) {
        if(data == true){
          $("#common_msg").text("加班申请通过成功");
          $("#common_modal").modal("show");
        }else{
          $("#common_msg").text("加班申请通过失败");
          $("#common_modal").modal("show");
        }
        pageInit(1);
      }
    })
  }

  function hrRejection(workOvertimeId) {
    var url = "/work_overtime/hr/reject/"+workOvertimeId+".do";
    $.ajax({
      type:"POST",
      url:url,
      async:true,
      success:function (data) {
        if(data == true){
          $("#common_msg").text("加班申请驳回成功");
          $("#common_modal").modal("show");
        }else{
          $("#common_msg").text("加班申请驳回失败");
          $("#common_modal").modal("show");
        }
        pageInit(1);
      }
    })
  }

</script>
<jsp:include page="commonBottom.jsp"/>