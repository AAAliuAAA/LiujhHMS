<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-bars"></i> 录入员工转正请求</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                    <li><i class="fa fa-bars"></i>人力资源管理</li>
                    <li><i class="fa fa-square-o"></i>转正申请录入</li>
                </ol>
            </div>
        </div>
        <!-- page start-->

        <div class="col-md-offset-1 col-md-8">
            <section class="panel">
                <header class="panel-heading">
                    录入员工转正请求
                </header>
                <div class="panel-group" id="application_list">
                </div>

                <!-- page end-->
            </section>
            <!--main content end-->
    </section>
    <script type="text/x-jquery-tmpl" id="application_template">
    <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              发起人:${'${'}employee.employeeName}
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
              负责人审核意见：
              ${'${'}leaderAcception}
              <hr/>
              <button onclick="pass(${'${'}regularWorkerApplicationId})" class="btn btn-info">通过</button>
              <button onclick="repulse(${'${'}regularWorkerApplicationId})" class="btn btn-danger">驳回</button>
            </div>
          </div>
        </div>

    </script>

    <script type="text/javascript">
        $(function () {
            getAllApplication();
        })

        function getAllApplication() {
            $("#application_list").empty();
            var url = "/RegularWorkerApplication/all_application.do";
            $.getJSON(url, function (data) {
                $("#application_template").tmpl(data).appendTo($("#application_list"));
            })
        }

        function pass(id) {
            var url = "${pageContext.request.contextPath}/RegularWorkerApplication/hr_application_pass/"+id+".do";
            $.ajax({
                type: "POST",
                async: true,
                url:url,
                success: function (data) {
                    console.log(data);
                    if(data == 1){
                        $("#common_msg").text("转正申请通过成功，当前员工已转正");
                        $("#common_modal").modal("show");
                    }else{
                        $("#common_msg").text("处理错误");
                        $("#common_modal").modal("show");
                    }
                    getAllApplication();
                }
            })
        }

        function repulse(id) {
            var url = "/RegularWorkerApplication/hr_application_reject/"+id+".do";
            $.ajax({
                type: "POST",
                async: true,
                url:url,
                success: function (data) {
                    if(data == 1){
                        $("#common_msg").text("转正驳回成功，请与此员工领导联系");
                        $("#common_modal").modal("show");
                    }else{
                        $("#common_msg").text("处理错误");
                        $("#common_modal").modal("show");
                    }
                    getAllApplication();
                }
            })
        }
    </script>
<jsp:include page="commonBottom.jsp"/>