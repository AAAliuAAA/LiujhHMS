<%--
  Created by IntelliJ IDEA.
  User: liujh
  Date: 2018/5/7
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="leave_modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
        <h4 class="modal-title">提交请假申请</h4>
      </div>
      <div class="modal-body">
        <!-- Inline form-->
        <div class="row">
          <div class="col-lg-12">
            <form id="employee_leave_form" class="form-inline" role="form">
              <div class="form-group col-md-6">
                请假开始时间:
                <input type="datetime-local" required class="form-control" name="leaveStartTime">
              </div>
              <div class="form-group col-md-6">
                请假截止时间:
                <input type="datetime-local" required class="form-control" name="leaveEndTime">
                <div style="height: 20px"></div>
              </div>
              <div class="form-group col-md-10">
                <textarea name="leaveReason" required class="form-control" rows="4" placeholder="输入请假理由..."></textarea>
              </div>
              <div class="col-md-2">
                <input type="button" onclick="submitLeaveApplication()" style="position: relative;top: 18px"
                       class="btn btn-primary" value="提交"/>
              </div>
            </form>

            <hr/>
            <h5 class="pull-left">最近的请假申请状态</h5>
            <table class="table table-striped table-advance table-hover">
              <thead>
              <tr>
                <th><i class="icon_calendar"></i>请假开始时间</th>
                <th><i class="icon_calendar"></i>请假截止时间</th>
                <th><i class="icon_comment_alt"></i>申请状态</th>
              </tr>
              </thead>
              <tbody id="leave_tab">

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
</div>
<!-- /.modal -->
<script type="text/x-jquery-tmpl" id="leave_tab_template">
        <tr>
        <td class="assignment_leave_date">${'${'}leaveStartTime}</td>
        <td class = "assignment_leave_date">${'${'}leaveEndTime}</td>
        <td>${'${'}leaveStatusName}</td>
     </tr>
</script>
<script type="text/javascript">
  function leaveModalShow() {
    $("#leave_modal").modal("show");
    appendEmployeeLeaveApplication();
  }

  function submitLeaveApplication() {
    var url = "/leave.do";
    var leaveStartTime = $("input[name='leaveStartTime']").val();
    var leaveEndTime = $("input[name='leaveEndTime']").val();
    var leaveReason = $("textarea[name='leaveReason']").val();
    if(leaveStartTime == ""||leaveEndTime==""||leaveReason==""){
      messageAlert("表单信息不完整，不可提交");
      return false;
    }
    var data = {
      leaveStartTime:leaveStartTime,
      leaveEndTime:leaveEndTime,
      leaveReason:leaveReason
    }
    $.ajax({
      type:"POST",
      url:url,
      data:data,
      async:true,
      success:function (data) {
        if(data == true){
          messageAlert("提交请假申请成功，等待当前直属上级审批")
        }else{
          messageAlert("提交请假申请失败")
        }
        $("#leave_modal").modal("hide");
        appendEmployeeLeaveApplication();
      },
      error:function (data) {
        console.log(data);
      }
    })
  }
  function appendEmployeeLeaveApplication() {
    var selfEmployeeId = ${logEmployee.employeeId};
    var url = "/leave/self/"+selfEmployeeId+".do";
    $.getJSON(url,function (data) {
      $("#leave_tab").empty();
      $("#leave_tab_template").tmpl(data).appendTo($("#leave_tab"));
      leave_tab_appendDate($(".assignment_leave_date"));
    })
  }
</script>
