<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/10
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<section id="main-content">
  <section class="wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h3 class="page-header"><i class="fa fa-bars"></i> LJH 职员主页</h3>
        <ol class="breadcrumb">
          <li><i class="fa fa-home"></i><a href="index.html">主页</a></li>
          <!--<li><i class="fa fa-bars"></i>Pages</li>
          <li><i class="fa fa-square-o"></i>Pages</li>-->
        </ol>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <a href="${pageContext.request.contextPath}/work_date_check.jsp">
          <div class="info-box green-bg ">
            <i class="fa fa-bullhorn"></i>
            <div class="count">入职的第<span id="days"></span>天</div>
            <div class="title" id="welcome">2018年1月10日也要元气满满哦</div>
          </div><!--/.info-box-->
        </a>
      </div><!--/.col-->

      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <a href="${pageContext.request.contextPath}/employee_self_detail.jsp">
          <div class="info-box blue-bg" id="myprofile">
            <i class="fa fa-user"></i>
            <div class="count">我的资料</div>
            <div class="title">查看或修改</div>
          </div><!--/.info-box-->
        </a>
      </div><!--/.col-->
    </div>
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <a href="${pageContext.request.contextPath}/employee_self_leave.jsp">
          <div class="info-box twitter-bg" id="mysalary">
            <i class="fa fa-table"></i>
            <div class="count">我的请假信息</div>
            <div class="title">查看</div>
          </div><!--/.info-box-->
        </a>
      </div><!--/.col-->

      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <a href="${pageContext.request.contextPath}/employee_self_work_attendance.jsp">
          <div class="info-box twitter-bg" id="myattendance">
            <i class="fa fa-table"></i>
            <div class="count">我的考勤表</div>
            <div class="title">查看</div>
          </div><!--/.info-box-->
        </a>
      </div><!--/.col-->
    </div>
    <div class="row">
      <div class="col-md-2"></div>
      <div class="col-md-4">
        <a id="checkInBar" href="javascript:checkIn()">
          <div class="info-box twitter-bg" id="checkIn">
            <i class="fa fa-thumb-tack"></i>
            <div class="count">上班打卡</div>
            <div class="title">最晚打卡时间：<span id="checkInTime"></span></div>
          </div><!--/.info-box-->
        </a>
      </div><!--/.col-->
      <div class="col-md-4">
        <a id="checkOutBar" href="javascript:checkOut()">
          <div class="info-box twitter-bg" id="checkOut">
            <i class="fa fa-exchange"></i>
            <div class="count">下班签退</div>
            <div class="title">下班时间：<span id="checkOutTime"></span></div>
          </div><!--/.info-box-->
        </a>
      </div><!--/.col-->
    </div>
    <div class="row">
      <div class="col-md-8 col-md-offset-2 portlets">
        <!-- Widget -->
        <div class="panel panel-default">
          <div class="panel-heading">
            <div class="pull-left">LJHPMS公共聊天室</div>
            <div class="widget-icons pull-right">
              <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
              <a href="#" class="wclose"><i class="fa fa-times"></i></a>
            </div>
            <div class="clearfix"></div>
          </div>
          <div class="panel-body">
            <div class="pad scroll" id="scroll_bar" style="height: 400px;overflow: auto;overflow-x: hidden">
              <ul class="chats" id="chatsPanel">
              </ul>
            </div>
            <!-- Widget footer -->
            <div class="widget-foot">
              <div class="form-inline">
                <div class="form-group">
                  <input type="text" style="width: 800px" class="form-control" placeholder="Type your message here..." id="inputMsg">
                </div>
                <button onclick="doSend()" class="btn btn-info">Send</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


  </section>
</section>
<%--添加sockJS依赖--%>
<script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
<script type="text/x-jquery-tmpl" id="chatsTemplateByOther">
    <li class="by-other">
        <div class="avatar pull-right">
            <img style="height: 44px; width: 44px" src="${'${'}headSculpture}" alt=""/>
        </div>
        <div class="chat-content">
            <div class="chat-meta">${'${'}now} <span class="pull-right">${'${'}name}</span></div>
            <div class="clearfix">${'${'}msg}</div>
        </div>
    </li>

</script>
<script type="text/x-jquery-tmpl" id="chatsTemplateByMe">
                <li class="by-me">
                  <div class="avatar pull-left">
                     <img style="height: 44px; width: 44px" src="${'${'}headSculpture}" alt=""/>
                  </div>
                  <div class="chat-content">
                   <div class="chat-meta">${'${'}now} <span class="pull-right">${'${'}name}</span></div>
            <div class="clearfix">${'${'}msg}</div>
                  </div>
                </li>
</script>
<script type="text/x-jquery-tmpl" id="sysInfoLog">
                <li class="by-me">
                  <div class="chat-content">
                   <div class="chat-meta">${'${'}now} <span class="pull-right">系统通知</span></div>
            <div class="clearfix">${'${'}msg}</div>
                  </div>
                </li>
</script>
<script type="text/javascript">
  var logEmployeeWorkId = ${sessionScope.logEmployee.employeeWorkId};
  $(function () {
      //初始化页面框体内容
    initPage();
    //异步查询员工在职信息
    appendWorkTime();
    //异步查询员工打卡信息
    queryCheckStatusAppend();
    //绑定键盘点击事件
    keyPressbind();
  });

  function appendWorkTime() {
    $.getJSON("${pageContext.request.contextPath}/WorkAttendanceConfig/queryWorkAttendanceConfigToday.do", function (result) {
      $("#checkInTime").text(result.lastCheckInTime);
      $("#checkOutTime").text(result.earlyCheckOutTime)
    })
  }

  function keyPressbind() {
    $("#inputMsg").keypress(function (e) {
      var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
      if (eCode == 13) {
        doSend();
      }
    })
  }


  function initPage() {
    var createDate = new Date("${sessionScope.logEmployee.employeeCreateTime}");
    var today = new Date();
    var between = today.getTime() - createDate.getTime();
    var days = Math.floor(between / (24 * 3600 * 1000)) + 1;
    $("#days").text(days);
    var todayText = today.format("yyyy年MM月dd日");
    $("#welcome").text(todayText + "也要元气满满哦(点击查看工作日历)");
    $("#scroll_bar").scrollTop($("#scroll_bar")[0].scrollHeight);
  }


  function queryCheckStatusAppend() {
    $.getJSON("${pageContext.request.contextPath}/workAttendanceCheck/queryCheckStatus.do", function (result) {
      console.log(result)
      var checkStatus = result.checkStatus;
      switch (checkStatus) {
          //上班打卡没下班打卡
        case 0:
          $("#checkInBar").removeAttr("href");
          $("#checkIn").removeClass("twitter-bg");
          $("#checkIn").addClass("green-bg");
          $("#checkIn .count").text("上班已打卡");
          var checkInTime = new Date(result.workAttendanceCheck.checkIn);
          $("#checkInBar .title").text("打卡时间：" + checkInTime.format("hh:mm:ss"));
          break;
          //没打卡
        case 2:
          //不做操作
          break;
          //全打卡
        case 3:
          $("#checkInBar").removeAttr("href");
          $("#checkIn").removeClass("twitter-bg");
          $("#checkIn").addClass("green-bg");
          $("#checkIn .count").text("上班已打卡");
          $("#checkOutBar").removeAttr("href");
          $("#checkOut").removeClass("twitter-bg");
          $("#checkOut").addClass("green-bg");
          $("#checkOut .count").text("下班已签退");
          var checkInTime = new Date(result.workAttendanceCheck.checkIn);
          $("#checkInBar .title").text("打卡时间：" + checkInTime.format("hh:mm:ss"));
          var checkOutTime = new Date(result.workAttendanceCheck.checkOut);
          $("#checkOutBar .title").text("签退时间：" + checkOutTime.format("hh:mm:ss"));
          break;
      }
    })
  }

  function checkIn() {
    var checkInUrl = "${pageContext.request.contextPath}/workAttendanceCheck/checkIn.do";
    $.getJSON(checkInUrl, function (result) {
      if (result == 0) {
        $("#common_msg").text("打卡失败");
      } else {
        $("#common_msg").text("打卡成功");
      }
      $("#common_modal").modal("show");
      queryCheckStatusAppend();
    })
  }

  function checkOut() {
    var checkOutUrl = "${pageContext.request.contextPath}/workAttendanceCheck/checkOut.do";
    $.getJSON(checkOutUrl, function (result) {
      if (result == 0) {
        $("#common_msg").text("签退失败");
      } else {
        $("#common_msg").text("签退成功");
      }
      $("#common_modal").modal("show");
      queryCheckStatusAppend();
    })
  }
</script>
<script type="text/javascript" id="socketChats">
  var websocket = null;
  if ('WebSocket' in window) {
    //Websocket的连接
    websocket = new WebSocket("ws://localhost:80/websocket/chats.do");//WebSocket对应的地址
  } else {
    alert("当前浏览器不支持websocket")
  }
  websocket.onopen = onOpen;
  websocket.onmessage = onMessage;
  websocket.onerror = onError;
  websocket.onclose = onClose;

  function onOpen(openEvt) {
    //连接建立时执行
  }

  function onMessage(evt) {
    var data = evt.data;
    if (data.indexOf('sysInfo') != -1) {
      var links = { msg: evt.data.split(":")[1] };
      $("#sysInfoLog").tmpl(links).appendTo($("#chatsPanel"));
    } else {
      //系统通知
      var jsonObj = JSON.parse(data);
      messageLogPage(jsonObj);
    }
  }

  function onError() {
  }

  function onClose() {
  }

  function doSend() {
    if (websocket.readyState == websocket.OPEN) {
      var msg = document.getElementById("inputMsg").value;
      websocket.send(msg);//调用后台handleTextMessage方法
      $("#inputMsg").val("");
    } else {
      $("#common_msg").text("发送失败");
      $("#common_modal").modal("show")
    }
  }

  window.close = function () {
    websocket.close();
  }

  function messageLogPage(data) {
    var employeeWorkId = data.employeeWorkId;
    var time = data.now;
    var str = time.hours+":"+ time.minutes + ":" + time.seconds;
    data.now = str;
    if (employeeWorkId == logEmployeeWorkId) {
      //自己发送的消息
      $("#chatsTemplateByMe").tmpl(data).appendTo($("#chatsPanel"));
    } else {
      //别人发送的消息
      $("#chatsTemplateByOther").tmpl(data).appendTo($("#chatsPanel"));
    }
    $("#scroll_bar").scrollTo('100%');
  }

</script>


<jsp:include page="commonBottom.jsp"/>