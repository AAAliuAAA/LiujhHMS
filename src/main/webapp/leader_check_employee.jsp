<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="commonTop.jsp"/>
<<!--main content start-->
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> 员工信息管理</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="index.jsp">主页</a></li>
          <li><i class="icon_group"></i>我的部门</li>
          <li><i class="fa fa fa-adjust"></i>下属员工信息查看</li>
        </ol>
      </div>
    </div>
    <div class="row">
      <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-10 col-xs-10">
          <div class="col-lg-12">
            <form class="form-inline" id="searchForm" role="form">
              <input type="hidden" name="employeeLeaderId" value="${logEmployee.employeeId}">
              <input type="hidden" name="statusId" value="0"/>
              <div class="form-group">
                <select class="form-control" name="condition" onchange="changeCondition()">
                  <option class="input-sm" value="" selected>请选择查询方式...</option>
                  <option class="input-sm" value="checkByEmployeeWorkId">工号查询</option>
                  <option class="input-sm" value="checkByEmployeeName">姓名查询</option>
                </select>
              </div>
              <div class="form-group">
                <input type="text" id="values1" class="form-control" name="" value="">
              </div>
              <div class="form-group">
                <select class="form-control" name="" id="values2">

                </select>
              </div>
              <div class="form-group">
                <input type="number" class="form-control" name="pageSize" placeholder="每页显示条数"/>
              </div>
              <input type="button" onclick="query(1)" class="btn btn-primary" value="查找">
            </form>
          </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-2 col-xs-10">
          <div class="col-lg-7">
            <div class="input-group">
              <input type="number" class="form-control" id="jumpInput" placeholder="请输入页码">
              <span class="input-group-btn">
                            <button class="btn btn-info" type="button" onclick="jump()">前往</button>
                          </span>
            </div><!-- /input-group -->
          </div><!-- /.col-lg-6 -->
          <div class="col-lg-5">
            <div class="btn-group">
              <button type="button" id="showLeave" class="btn btn-default" onclick="changeStatusCondition(1)">显示离职
              </button>
            </div>
          </div>
        </div><!-- /.row -->
      </div>
    </div>

    <div style="height: 10px;" class="row"></div>
    <div class="row">
      <div class="col-lg-12">
        <section class="panel">
          <header class="panel-heading">
            LJH 员工信息 总览/查询
          </header>
          <table class="table table-striped table-advance table-hover">
            <tbody>
            <tr>
              <th><i class="icon_profile"></i> 工号</th>
              <th><i class="icon_calendar"></i> 姓名</th>
              <th><i class="icon_mail_alt"></i> 部门</th>
              <th><i class="icon_pin_alt"></i> 职位</th>
              <th><i class="icon_mobile"></i> 联系电话</th>
              <th><i class="icon_comment_alt"></i>在职状态</th>
              <th><i class="icon_cogs"></i> 操作</th>
            </tr>
            </tbody>
            <tbody id="employeeList">

            </tbody>
          </table>
        </section>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="col-xs2"></div>
          <div class="col-xs-10">总页数： <span class="totalPage"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总记录数：
            <span class="totalRecord"></span>
            当前页： <span id="pageNum"></span>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="btn-row">
          <div class="col-md-5">
          </div>
          <div class="btn-toolbar col-lg-5 col-md-5 col-sm-6 col-xs-9">
            <div class="btn-group">
              <button class="btn btn-primary prePage" id="prePage" onclick="">上一页</button>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="btn btn-primary nextPage" id="nextPage" onclick="">下一页</button>
          </div>
        </div>
      </div>

      <div class="modal fade" id="alertModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                      class="sr-only">Close</span></button>
              <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
              <p>超过最大页数&hellip;</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->

    </div>
    </div>
  </section>
</section>
<!--main content end-->
</section>
<script type="text/x-jquery-tmpl" id="tmpl1">
    <tr>
        <td>${'${'}employeeWorkId}</td>
        <td>${'${'}employeeName}</td>
        <td>${'${'}department.departmentName}</td>
        <td>${'${'}position.positionName}</td>
        <td>${'${'}employeePhone}</td>
        <td class='status'>${'${'}statusId}</td>
        <td>
            <div class="btn-group">
                 <a class="btn btn-info" href="${pageContext.request.contextPath}/Employee/${'${'}employeeId}.do?method=check"><i class="icon_check_alt">&nbsp;&nbsp;查看</i> </a>
            </div>
        </td>
     </tr>

</script>
<script type="text/javascript">
  var statusData;

  $(function () {
    //将全局的异步数据查询出来
    queryStatusData();
    $("#hideLeave").addClass("active");
//  隐藏选择控件
    $("#values1").hide();
    $("#values2").hide();

//  使用ajax发送请求获取页面数据
    query(1);

  });

  function queryStatusData() {
    $.getJSON("/Status.do", function (data) {
      statusData = data;
    });
  }


  function query(pageNo) {
    //清空当前数据
    $form = $("#searchForm");
    //表单序列化并添加参数
    var data;
    if (pageNo != null) {
      data = $.param({"pageNo": pageNo}) + '&' + $form.serialize();
    } else {
      data = $.param({"pageNo": 1}) + '&' + $form.serialize();
    }
    var url = "/EmployeeAjaxController/queryEmployees.do";
    $.getJSON(url,data, function (data) {
      if (data.pages < pageNo && data.pages != 0) {
        $('#alertModal').modal('show');
      } else {
        $("#employeeList").empty();
        $("#tmpl1").tmpl(data.list).appendTo($("#employeeList"));
        $(".pageGroup").empty();
        //如果没有数据了，直接跳到第一页
        if (data.pageNum == 0) {
          query(data.firstPage);
        }
        queryStatusData();
        pageConfig(data);
        statusConfigToText(statusData);
      }
    });
    //
  }

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

  function pageConfig(data) {
    //配置当前分页信息
    $(".totalPage").text(data.pages);
    $(".totalRecord").text(data.total);
    $("#pageNum").text(data.pageNum);
    var nextPage = data.nextPage;
    if (data.nextPage == 0) {
      nextPage = data.lastPage;
    }
    var prePage = data.prePage;
    if (data.prePage == 0) {
      prePage = data.firstPage;
    }
    $("#prePage").attr("onclick", "query(" + prePage + ")");
    $("#nextPage").attr("onclick", "query(" + nextPage + ")");
  }

  function changeStatusCondition(statusId) {
    if ($("input[name='statusId']").val() == 0 || $("input[name='statusId']").val() == "0") {
      $("#showLeave").addClass("active");
      $("input[name='statusId']").val(statusId);
      query(1);
    }
  }

  function changeCondition() {
    $("#values1").hide();
    $("#values2").hide();
    var selectTest = $("select[name='condition']").val();
    var $inputV = $("#values1");
    var $inputV2 = $("#values2");
    $inputV.val("");
    $inputV2.val("");
    switch (selectTest) {
      case "checkByEmployeeWorkId":
        $inputV.hide();
        $inputV2.hide();
        $inputV.attr("name", "employeeWorkId");
        $inputV.attr("placeholder", "请输入工号");
        $inputV.show();
        break;
      case "checkByEmployeeName":
        $inputV.hide();
        $inputV2.hide();
        $inputV.attr("name", "employeeName");
        $inputV.attr("placeholder", "请输入姓名");
        $inputV.show();
        break;
    }
  }

  function jump() {
    var pageNo = $("#jumpInput").val();
    if (pageNo != null) {
      query(pageNo);
    }
  }
</script>
<jsp:include page="commonBottom.jsp"/>