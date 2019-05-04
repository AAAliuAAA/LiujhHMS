<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="common_modal" style="z-index: 1052">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p id="common_msg">超过最大页数&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<%--加班组件--%>
<jsp:include page="${pageContext.request.contextPath}/work_overtime_assignment.jsp" flush="true"/>
<%--请假组件--%>
<jsp:include page="${pageContext.request.contextPath}/employee_leave_assignment.jsp" flush="true"/>
<script src="${pageContext.request.contextPath}/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pmsJS/common/common_util_js.js"></script>
<script type="text/javascript">
  function commonRependDate(rependateObj) {
    $.each(rependateObj, function (index, ele) {
      var date = new Date(parseInt($(ele).text()));
      $(ele).text(date.format("yyyy-MM-dd"));
    })
  }

  function messageAlert(msg) {
    $("#common_msg").text(msg);
    $("#common_modal").modal("show");
  }

  function leave_tab_appendDate(rependateObj) {
    $.each(rependateObj, function (index, ele) {
      var date = new Date(parseInt($(ele).text()));
      $(ele).text(date.format("yyyy-MM-dd hh:mm"));
    })
  }

  function workAttendanceCheckTimeRepend(rependateObj) {
    $.each(rependateObj, function (index, ele) {
      if($(ele).text() == null||$(ele).text() == ""){
        $(ele).text("未打卡");
      }else{
        var date = new Date(parseInt($(ele).text()));
        $(ele).text(date.format("hh:mm"));
      }
    })
  }
</script>


</body>
</html>