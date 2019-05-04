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
                <h3 class="page-header"><i class="fa fa-bars"></i> 员工信息录入</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="index.jsp">主页</a></li>
                    <li><i class="icon_group"></i>人力资源管理</li>
                    <li><i class="fa fa-android"></i>新增员工</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-11">
                <section class="panel">
                    <header class="panel-heading">
                        员工录入
                    </header>
                    <div class="panel-body">
                        <form class="form-horizontal " method="post" action="/Employee/addEmployee.do">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工号</label>
                                <div class="col-sm-10">
                                    <input type="text" name="employeeWorkId" class="form-control" value="1111"
                                           readonly="readonly" required>
                                    <span class="help-block">系统默认为您生成当前插入员工的最合适的工号，如果想自由更改，请联系管理员</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-10">
                                    <input type="text" name="employeeName" class="form-control" required>
                                    <!--<span class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span>-->
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">出生日期</label>
                                <div class="col-sm-5">
                                    <input required name="employeeBirthday" class="form-control" type="date"
                                           placeholder="请选择...">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">在职状态</label>
                                <div class="col-sm-10">
                                    <select name="statusId" class="form-control m-bot15" required>
                                        <option selected="selected">请选择...</option>

                                        <!--在这里插入在职状态数据-->
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">初始密码</label>
                                <div class="col-sm-10">
                                    <input name="employeePassword" class="form-control" type="text" readonly
                                           value="123456" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-10">
                                    <input name="employeePhone" class="form-control" type="text" required>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">电子邮箱</label>
                                <div class="col-sm-10">
                                    <input name="employeeEmail" class="form-control" type="text" required>
                                </div>
                            </div>


                            <div class="form-group ">
                                <label for="comment" class="control-label col-lg-2">员工简介</label>
                                <div class="col-lg-10">
                                    <textarea name="employeeIntroduction" id="comment" class="form-control"
                                              style="height: 100px;"></textarea>
                                    <span class="help-block">可选</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">员工所在部门</label>
                                <div class="col-sm-10">
                                        <select name="department.departmentId" class="form-control m-bot15" required>
                                            <option selected="selected" id="chooseDept">请选择...</option>

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
                                    <input type="text" value="" readonly id="leaderName" class="form-control"
                                           placeholder="请选择..." required>
                                    <input type="hidden" name="employeeLeader"/>
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
<!--main content end-->
</section>
<script type="text/x-jquery-tmpl" id="tmplLeader">
    <tr>
        <td><input type="radio" name="leader" class="form-control" style="position:relative;bottom:3px" value="${'${'}employeeId}"/></td>
        <td>${'${'}employeeWorkId}</td>
        <td class="leaderName">${'${'}employeeName}</td>
        <td>${'${'}position.positionName}</td>
        <td class='status'>${'${'}statusId}</td>
     </tr>

</script>
<script src="${pageContext.request.contextPath}/js/pmsJS/employeeAdd.js"></script>
<jsp:include page="commonBottom.jsp"/>