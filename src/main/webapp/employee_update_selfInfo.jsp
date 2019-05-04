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
                <h3 class="page-header"><i class="fa fa-user-md"></i> 修改资料</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                    <li><i class="icon_documents_alt"></i><a
                            href="${pageContext.request.contextPath}/employee_self_detail.jsp">我的资料</a></li>
                    <li><i class="fa fa-user-md"></i>修改我的资料</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-11">
                <section class="panel">
                    <header class="panel-heading">
                        修改我的基本资料
                    </header>
                    <div class="panel-body">
                        <form class="form-horizontal " method="post" action="${pageContext.request.contextPath}/Employee/updateEmployeeSelf.do">
                            <input type="hidden" name="_method" value="PUT"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工号</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" value="${logEmployee.employeeWorkId}"
                                           readonly="readonly" required>
                                    <span class="help-block">工号入职即确定，不可更改</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-10">
                                    <input type="text" name="employeeName" class="form-control" required value="${logEmployee.employeeName}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">出生日期</label>
                                <div class="col-sm-5">
                                    <input required name="employeeBirthday" class="form-control" type="date"
                                           placeholder="请选择..." value="">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-10">
                                    <input name="employeePhone" class="form-control" type="text" value="${logEmployee.employeePhone}" required>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">电子邮箱</label>
                                <div class="col-sm-10">
                                    <input name="employeeEmail" class="form-control" type="text" value="${logEmployee.employeeEmail}" required>
                                </div>
                            </div>


                            <div class="form-group ">
                                <label for="comment" class="control-label col-lg-2">员工简介</label>
                                <div class="col-lg-10">
                                    <textarea name="employeeIntroduction" id="comment" class="form-control"
                                              style="height: 100px;">${logEmployee.employeeIntroduction}</textarea>
                                    <span class="help-block"></span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-xs-5"></div>
                                <div class="col-xs-3 col-sm-offset-1">
                                    <button class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section>
</section>
</section>
</section>
<!--main content end-->
</section>

<script>

    $(function () {
        //格式化时间并回填到表单
        birthdayFormat();
    })

    function birthdayFormat(){
        var birthday = new Date("${logEmployee.employeeBirthday}");
        var dateStr = birthday.format("yyyy-MM-dd");
        $("input[name='employeeBirthday']").val(dateStr);
    }
</script>
<jsp:include page="commonBottom.jsp"/>