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
                <h3 class="page-header"><i class="fa fa-user-md"></i> 修改员工资料</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                    <li><i class="icon_documents_alt"></i>人力资源管理</li>
                    <li><i class="fa fa-adjust"></i><a
                            href="${pageContext.request.contextPath}/employee_check_allajax.jsp">员工信息管理</a></li>
                    <li><i class="fa fa-user-md"></i>修改员工资料</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-11">
                <section class="panel">
                    <header class="panel-heading">
                        修改资料
                    </header>
                    <div class="panel-body">
                        <form class="form-horizontal " method="post" action="/Employee/updateEmployee.do">
                            <input type="hidden" name="employeeId" value="${emp.employeeId}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工号</label>
                                <div class="col-sm-10">
                                    <input type="text" name="employeeWorkId" class="form-control" value="${emp.employeeWorkId}"
                                           readonly="readonly" required>
                                    <span class="help-block">工号入职即确定，不可更改</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-10">
                                    <input type="text" name="employeeName" class="form-control" required value="${emp.employeeName}">
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
                                <label class="col-sm-2 control-label">在职状态</label>
                                <div class="col-sm-10">
                                    <select name="statusId" class="form-control m-bot15" required>
                                        <option>请选择...</option>

                                        <!--在这里插入在职状态数据-->
                                    </select>
                                    <span class="help-block">只可以将当前状态修改为离职，其它在职状态修改请通过申请提交</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-10">
                                    <input name="employeePhone" class="form-control" type="text" value="${emp.employeePhone}" required>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">电子邮箱</label>
                                <div class="col-sm-10">
                                    <input name="employeeEmail" class="form-control" type="text" value="${emp.employeeEmail}" required>
                                </div>
                            </div>


                            <div class="form-group ">
                                <label for="comment" class="control-label col-lg-2">员工简介</label>
                                <div class="col-lg-10">
                                    <textarea name="employeeIntroduction" id="comment" class="form-control"
                                              style="height: 100px;">${emp.employeeIntroduction}</textarea>
                                    <span class="help-block">可选</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">员工所在部门</label>
                                <div class="col-sm-10">
                                    <select name="department.departmentId" class="form-control m-bot15" required>
                                        <option id="chooseDept">请选择...</option>

                                        <!--在这里插入部门数据-->
                                    </select>

                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">员工职位</label>
                                <div class="col-sm-10">
                                    <select name="position.positionId" class="form-control m-bot15" required>
                                        <option selected="selected">请选择...</option>

                                        <!--在这里插入职位数据-->
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">直属上级</label>
                                <div class="col-sm-10">
                                    <input type="text"  readonly id="leaderName" class="form-control"
                                           placeholder="请选择..." value="${emp.employeeLeaderName}" required>
                                    <input type="hidden" name="employeeLeader" value="${emp.employeeLeader}"/>
                                    <span class="help-block">可选</span>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-xs-5"></div>
                                <div class="col-xs-3 col-sm-offset-1">
                                    <button class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </form>
                        <!-- page end-->
                        <div class="modal fade" id="chooseLeader">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span
                                                aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                        </button>
                                        <h4 class="modal-title">选择上级员工</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <input type="number" name="employeeLeaderWorkId"
                                                       class="form-control input-sm" placeholder="输入上级员工工号查找">
                                            </div>
                                            <div class="col-lg-6">
                                                <button type="button" id="checkByEmployeeWorkId"
                                                        class="btn btn-sm btn-primary">查找
                                                </button>
                                            </div>
                                        </div>
                                        <div class="row" style="height: 20px"></div>
                                        <table class="table table-striped table-advance table-hover">
                                            <tbody>
                                            <tr>
                                                <th></th>
                                                <th><i class="icon_profile"></i> 工号</th>
                                                <th><i class="icon_calendar"></i> 姓名</th>
                                                <th><i class="icon_pin_alt"></i> 职位</th>
                                                <th><i class="icon_comment_alt"></i>在职状态</th>
                                            </tr>
                                            </tbody>
                                            <tbody id="employeeList">

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="confirmLeader">确定</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->

                        <!-- page end-->
                        <div class="modal fade" id="confirmModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span
                                                aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                        </button>
                                        <h4 class="modal-title">提示</h4>
                                    </div>
                                    <div class="modal-body">
                                        请先选择部门，再选择上级领导
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->

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
<script type="text/x-jquery-tmpl" id="tmplLeader">
    <tr>
        {{if ${emp.employeeLeader}==employeeId }}
            <td><input checked type="radio" name="leader" class="form-control" style="position:relative;bottom:3px" value="${'${'}employeeId}"/></td>
            {{else}}
              <td><input type="radio" name="leader" class="form-control" style="position:relative;bottom:3px" value="${'${'}employeeId}"/></td>

        {{/if}}
        <td>${'${'}employeeWorkId}</td>
        <td class="leaderName">${'${'}employeeName}</td>
        <td>${'${'}position.positionName}</td>
        <td class='status'>${'${'}statusId}</td>
     </tr>

</script>

<script>
    var statusData;
    var departmentId;

    $(function () {
        //格式化时间并回填到表单
        birthdayFormat();
        //查询在职状态列表
        statusConfig();
        //领导工号查询按钮绑定
        queryEmployeeWorkIdBind();
        //领导查询按钮绑定（先选择部门才可以查询）
        queryLeaderPageBind();
        //查询职位信息列表
        positionConfig();
        //查询部门列表
        departmentConfig();
        //领导查询确认按钮绑定
        confirmLeaderClickBind();
    })

    function confirmLeaderClickBind(){
        $("#confirmLeader").click(function(){
            var  $selRadio = $("#employeeList input[type='radio']:checked");
            if($selRadio!=null){
                $("input[name='employeeLeader']").val($selRadio.val());
                var $selLeaderName = $selRadio.parent().parent().children(".leaderName").text();
                $("#leaderName").val($selLeaderName);
                $("#chooseLeader").modal('hide');
            }
        });
    }


    function departmentConfig() {
        $.getJSON("/Department.do", function (data) {
            $.each(data, function (index, ele) {
                if(ele.departmentId==${emp.department.departmentId}+""){
                    var $opt = $("<option value=" + ele.departmentId + " selected='selected'>" + ele.departmentName + "</option>");
                    $("select[name='department.departmentId']").append($opt);
                }else{
                    var $opt = $("<option value=" + ele.departmentId + ">" + ele.departmentName + "</option>");
                    $("select[name='department.departmentId']").append($opt);
                }

            });
        })
    }

    function positionConfig() {
        $.getJSON("/Position.do", function (data) {
            $.each(data, function (index, ele) {
                if(ele.positionId==${emp.position.positionId}+""){
                    var $opt = $("<option value=" + ele.positionId + " selected='selected'>" + ele.positionName + "</option>");
                    $("select[name='position.positionId']").append($opt);
                }else{
                    var $opt = $("<option value=" + ele.positionId + ">" + ele.positionName + "</option>");
                    $("select[name='position.positionId']").append($opt);
                }
            });
        })
    }

    function statusConfig() {

        var $confi = $(".status");
        $.getJSON("/Status.do", function (data) {
            statusData = data;
            $.each(data, function (index, ele) {
                if(ele.statusId==${emp.statusId}){
                var $opt = $("<option value=" + ele.statusId + " selected='selected'>" + ele.statusName + "</option>");
                $("select[name='statusId']").append($opt);
                }else{
                    if(ele.statusId==2){
                        var $opt = $("<option value=" + ele.statusId + ">" + ele.statusName + "</option>");
                        $("select[name='statusId']").append($opt);
                    }
                }
            });
        });
    }


    function queryEmployeeWorkIdBind() {
        $("#checkByEmployeeWorkId").click(function () {
            var employeeLeaderWorkId = $("input[name='employeeLeaderWorkId']").val();
            queryLeaders(departmentId, employeeLeaderWorkId);
        });
    }


    function queryLeaderPageBind() {
        $("#leaderName").click(function () {
            if ($("#chooseDept").prop("selected") == true) {
                $("#confirmModal").modal("show");
            } else {
                //已选择部门，则选择当前部门领导
                departmentId = $("select[name='department.departmentId']").val();
                queryLeaders(departmentId, null);
                $("#chooseLeader").modal('show');
            }
        })
    }

    function queryLeaders(departmentId, employeeLeaderWorkId) {
        $("#employeeList").empty();
        if (employeeLeaderWorkId == null) {
            $.getJSON("/EmployeeAjaxController/queryEmployeeLeaders.do", "departmentId=" + departmentId, function (data) {
                $("#tmplLeader").tmpl(data).appendTo($("#employeeList"));
                statusConfigToText(statusData);
            });
        } else {
            $.getJSON("/EmployeeAjaxController/queryEmployeeLeaders.do", "employeeWorkId=" + employeeLeaderWorkId+"&departmentId="+ departmentId, function (data) {
                $("#tmplLeader").tmpl(data).appendTo($("#employeeList"));
                statusConfigToText(statusData);
                $("#chooseLeader").modal('show');
            });
        }
    }

    /*将职位ID转化为文字*/
    function statusConfigToText(statusData) {
        var $confi = $(".status");
        $.each(statusData, function (index, eleStatus) {
            $.each($confi, function (index, ele) {
                if ($(ele).text() == eleStatus.statusId) {
                    $(ele).text(eleStatus.statusName);
                }
            });
        });
    }

    function birthdayFormat(){
        var birthday = new Date("${emp.employeeBirthday}");
        var dateStr = birthday.format("yyyy-MM-dd");
        $("input[name='employeeBirthday']").val(dateStr);
    }
</script>
<jsp:include page="commonBottom.jsp"/>