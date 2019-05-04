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
                <h3 class="page-header"><i class="fa fa-bars"></i> 转正申请提交</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
                    <li><i class="fa fa-bars"></i>转正申请</li>
                </ol>
            </div>
        </div>
        <!-- page start-->
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        提交申请后将交由您的领导进行审批，领导审核通过后交由人事部门审批
                        <span class="pull-right">审核状态：<span id="status"></span></span>
                    </header>
                    <div class="panel-body">
                        <div class="form">
                            <form class="form-validate form-horizontal" id="regularWorkerAssignmentForm" method="post">
                                <div class="form-group ">
                                    <label for="comment" class="control-label col-lg-2">申请内容</label>
                                    <div class="col-lg-10">
                                        <textarea rows="15" class="form-control " id="comment" name="employeeApplication" required></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <input type="button" class="btn btn-primary" id="formSub" value="提交"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <!-- page end-->
    </section>
</section>
<!--main content end-->
</section>
<script>
    $(function() {
        queryApplicationStatusAndAppendData();

        $("#formSub").click(function () {
            submitRegularWorkerAssignment();
        });
    })
    function submitRegularWorkerAssignment(){
        var url = "${pageContext.request.contextPath}/RegularWorkerApplication/employeeAssignment.do"
        $.ajax({
            type:"POST",
            url:url,
            data:$("#regularWorkerAssignmentForm").serialize(),
            async:true,
            success:function(result){
                if(result == 1){
                    $("#common_msg").text("申请提交成功，请等待审批")
                    $("#common_modal").modal("show");
                  queryApplicationStatusAndAppendData();
                }else{
                    $("#common_msg").text("申请提交失败")
                    $("#common_modal").modal("show");
                  queryApplicationStatusAndAppendData();
                }
            }
        })
    }
    function queryApplicationStatusAndAppendData() {
        //查询当前申请状态并将 textarea置为disable
      $.getJSON("${pageContext.request.contextPath}/RegularWorkerApplication/employeeApplication.do",function (data) {
        console.log(data);
        if(data == null){
          return;
        }
        $("#comment").attr("disabled","disabled");
        $("#comment").val(data.employeeApplication);
        $("#formSub").attr("disabled","disabled");
        $("#status").text(data.applicationStatus);
      })
    }
</script>
<jsp:include page="commonBottom.jsp"/>