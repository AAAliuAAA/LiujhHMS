<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="commonTop.jsp"/>
<<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-bars"></i> Pages</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
                    <li><i class="fa fa-bars"></i>Pages</li>
                    <li><i class="fa fa-square-o"></i>Pages</li>
                </ol>
            </div>
        </div>


        <div class="row">
            <div class="row">
                <div class="col-lg-6 col-md-8 col-sm-10 col-xs-10">
                    <div class="col-lg-12">
                        <form class="form-inline" action="/Employee.do" role="form">
                            <div class="form-group">
                                <input type="text" placeholder="输入员工工号" class="form-control" name="employeeWorkId" value="${employeeWorkId}">
                            </div>
                            <button type="submit" class="btn btn-primary">查找</button>
                        </form>
                    </div>
                </div>
            </div>
            <div style="height: 10px;" class="row"></div>
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        LJH 员工信息 总览/查询
                    </header>



                    <table class="table table-striped table-advance table-hover">
                        <tbody>
                        <tr>
                            <th><i class="icon_profile"></i> 工号 </th>
                            <th><i class="icon_calendar"></i> 姓名 </th>
                            <th><i class="icon_mail_alt"></i> 部门 </th>
                            <th><i class="icon_pin_alt"></i> 职位 </th>
                            <th><i class="icon_mobile"></i> 联系电话 </th>
                            <th><i class="icon_cogs"></i> 操作 </th>
                        </tr>
                        </tbody>
                        <tbody>
                        <c:forEach items="${page.list}" var="emp">
                        <tr>
                            <td>${emp.employeeWorkId}</td>
                            <td>${emp.employeeName}</td>
                            <td>开发部</td>
                            <td>职员</td>
                            <td>${emp.employeePhone}</td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-info" href="${pageContext.request.contextPath}/Employee/${emp.employeeId}.do?method=check"><i class="icon_check_alt">&nbsp;&nbsp;查看</i> </a>
                                    <a class="btn btn-warning" href=${pageContext.request.contextPath}/Employee/${emp.employeeId}.do??method=update"><i class="icon_pencil-edit_alt"></i>&nbsp;&nbsp;修改</a>
                                </div>
                            </td>
                        </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

            <jsp:include page="common_nav_pageInfo.jsp">
                <jsp:param name="pageNum" value="${page.pageNum}"/>
                <jsp:param name="pages" value="${page.pages}"/>
                <jsp:param name="prePage" value="${page.prePage}"/>
                <jsp:param name="nextPage" value="${page.nextPage}"/>
                <jsp:param name="requestUri" value="${pageContext.request.contextPath}Employee.do"/>
                <jsp:param name="conditions" value="&employeeWorkId=${employeeWorkId}"/>
            </jsp:include>

            </div>
        </div>
    </section>
</section>
<!--main content end-->
</section>
<script>
    $(function () {
        $("#values1").hide();
        $("#values2").hide();
    })

    function changeCondition() {
        $("#values1").hide();
        $("#values2").hide();

        var selectTest = $("select[name='condition']").val();
        var $inputV = $("#values1");
        var $inputV2  = $("#values2");
        var $seltmp = $("<option selected>请选择部门...</option>");
        switch (selectTest){
            case "checkByEmployeeWorkId":
                $("#values1").hide();
                $("#values2").hide();
                $inputV.attr("name","employeeId");
                $inputV.attr("placeholder","请输入工号");
                $inputV.show();
                break;
            case "checkByDepartment":
                $("#values1").hide();
                $("#values2").hide();
                $inputV2.attr("name","deparmentId");
                $inputV2.append($seltmp);
                $inputV2.show();
                break;
            case "checkByEmployeeName":
                $("#values1").hide();
                $("#values2").hide();
                $inputV.attr("name","employeeName");
                $inputV.attr("placeholder","请输入工号");
                $inputV.show();
                break;
        }
    }
</script>
<jsp:include page="commonBottom.jsp"/>