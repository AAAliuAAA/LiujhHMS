<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> 处理转正申请</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
          <li><i class="fa fa-bars"></i>申请处理</li>
          <li><i class="fa fa-square-o"></i>员工转正申请处理</li>
        </ol>
      </div>
    </div>

    <div class="col-md-offset-1 col-md-8">
      <section class="panel">
        <header class="panel-heading">
          需要处理的员工转正请求
        </header>
    <div class="panel-group" id="application_list">
    </div>
    </section>
  </section>
</section>


<div class="modal fade" id="acception_modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p>请填写转正通过信息提交给人事部门</p>
        <textarea style="width: 100%" id="leaderAcception">
        </textarea>
      </div>
      <div class="modal-footer">
        <button onclick="pass()" type="button" class="btn btn-info" data-dismiss="modal">提交</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!--main content end-->
<script type="text/x-jquery-tmpl" id="application_template">
  <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
              发起人: ${'${'}employee.employeeName}
            <a data-toggle="collapse" data-parent="#accordion"
               href="#collapse${'${'}regularWorkerApplicationId}" class="pull-right" style="font-size: 14px;color: #86C5F5;">
              展开
            </a>
          </h4>
        </div>
        <div id="collapse${'${'}regularWorkerApplicationId}" class="panel-collapse collapse">
          <div class="panel-body">
            ${'${'}employeeApplication}
            <hr/>
            <button onclick="passAlert(${'${'}regularWorkerApplicationId})" class="btn btn-info pass-bind">通过</button>
            <button  onclick="dismiss(${'${'}regularWorkerApplicationId})" class="btn btn-danger dismiss-bind">驳回</button>
          </div>
        </div>
      </div>
</script>
<script type="text/javascript">
  $(function () {
    queryRegularWorkerApplications();
  })
  var passId = null;

  function queryRegularWorkerApplications() {
    $("#application_list").empty();
    var url = '${pageContext.request.contextPath}/RegularWorkerApplication/all_employee_application.do';
    $.getJSON(url,function (data) {
      $("#application_list").empty();
      if (data.length == 0 || data == null) {
        $("#application_list").append("<tr><td colspan=\"5\">暂时没有需要处理的转正申请</td></tr>");
        return;
      }
      $("#application_template").tmpl(data).appendTo($("#application_list"))
    })
  }

  function passAlert(id) {
    passId = id;
    $("#acception_modal").modal("show");
  }


  function pass() {
    var url = "${pageContext.request.contextPath}/RegularWorkerApplication/pass_employee_application.do";
    var leaderAcception = $("#leaderAcception").val();
    var postData = {id:passId,leaderAcception:leaderAcception};
    $.ajax({
      type:"POST",
      url:url,
      data:postData,
      async:true,
      success:function (data) {
        if(data == 1){
          $("#common_msg").text("提交信息成功，请等待人事部处理申请");
          $("#common_modal").modal("show");
        }else{
          $("#common_msg").text("提交失败");
          $("#common_modal").modal("show");
        }
        queryRegularWorkerApplications();
      }
    })
    passId = null;
    $("#acception_modal").modal("hide");
  }

  function dismiss(id) {
    var url = "${pageContext.request.contextPath}/RegularWorkerApplication/dismiss_employee_application/"+id+".do";
    $.ajax({
      type:"POST",
      async:true,
      url:url,
      success:function (data) {
        if(data == 1){
          $("#common_msg").text("处理成功，申请已驳回");
          $("#common_modal").modal("show");
        }else{
          $("#common_msg").text("提交失败");
          $("#common_modal").modal("show");
        }
        queryRegularWorkerApplications();
      }
    });
  }
</script>
<jsp:include page="commonBottom.jsp"/>