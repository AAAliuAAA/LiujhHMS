<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--加班组件--%>
<div class="modal fade" id="work_overtime">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">提交加班申请</h4>
            </div>
            <div class="modal-body">
                <!-- Inline form-->
                <div class="row">
                    <div class="col-lg-12">
                        <form id="work_over_time_form" class="form-inline" role="form">
                            <div class="form-group col-md-5">
                                加班日期:
                                <input type="date" class="form-control" name="workOvertimeDate">
                            </div>
                            <div class="form-group col-md-5">
                                加班时长:
                                <input type="number" class="form-control" name="workOvertimeHour">
                            </div>
                          <div class="col-md-2">
                          <input type="button" onclick="submitWorkOvertime()" style="position: relative;top: 18px" class="btn btn-primary" value="提交"/>
                          </div>
                        </form>
                        <hr/>
                        <h5 class="pull-left">最近的加班申请状态</h5>
                        <table class="table table-striped table-advance table-hover">
                            <thead>
                            <tr>
                                <th><i class="icon_profile"></i>日期</th>
                                <th><i class="icon_calendar"></i>加班时长</th>
                                <th><i class="icon_comment_alt"></i>申请状态</th>
                            </tr>
                            </thead>
                            <tbody id="overtime_tab">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/x-jquery-tmpl" id="overtime_template">
        <tr>
        <td class="work_overtime_application_repend_date">${'${'}workOvertimeDate}</td>
        <td>${'${'}workOvertimeHour}</td>
        <td>${'${'}workOverTimeStatus.statusName}</td>
     </tr>
</script>

<script type="text/javascript">
    function overtimeModal() {
        initOvertimeTable();
        $("#work_overtime").modal("show");
    }

    function submitWorkOvertime() {
        var data = $("#work_over_time_form").serialize();
        $.ajax({
            type:"POST",
            url:"/work_overtime.do",
            data:data,
            success:function (data) {
                if(data == 1){
                    $("#work_overtime").modal("hide");
                    $("#common_msg").text("加班申请发起成功");
                    $("#common_modal").modal("show");
                }
                if(data == 2){
                    $("#work_overtime").modal("hide");
                    $("#common_msg").text("加班申请发起失败，当天已存在加班申请");
                    $("#common_modal").modal("show");
                }
                if(data == 3){
                    $("#work_overtime").modal("hide");
                    $("#common_msg").text("加班申请发起失败，当天不存在出勤信息");
                    $("#common_modal").modal("show");
                }
            }
        })
    }
    function initOvertimeTable() {
        //初始化加班信息列表
        $.ajax({
            type:"GET",
            url:"/work_overtime/employeeId/${sessionScope.logEmployee.employeeId}.do",
            success:function (data) {
                $("#overtime_tab").empty();
                $("#overtime_template").tmpl(data).appendTo($("#overtime_tab"));
                commonRependDate($(".work_overtime_application_repend_date"));
            }
        })
    }


</script>