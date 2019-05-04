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
<style type="text/css" rel="stylesheet">
    td,th{
        vertical-align: middle;
    }
</style>
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-bars"></i> Pages</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                    <li><i class="fa fa-bars"></i>工作时间设置</li>
                    <li><i class="fa fa-square-o"></i>节假日配置</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <section class="panel">
                    <header class="panel-heading no-border">
                        今年(<span id="targetDate"></span>)节假日安排
                    </header>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>假期名</th>
                            <th>日期</th>
                            <th>星期</th>
                            <th>类型</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="tab_append" align="center" style= "margin: 0cm 0cm 0pt; text-align: left">
                        </tbody>
                    </table>
                    <div class="col-md-12">
                        总记录数：<span id="total_record"></span>&nbsp;
                        总页数：<span id="total_page"></span>&nbsp;
                        当前页：<span id="current_page"></span>&nbsp;
                    </div>
                    <div class="col-md-4 col-md-offset-4" style="position: relative;top:5px;" id="toPage">
                        <div class="col-md-4">
                            <a href="javascript:pageConfig()" id="beforePage" style="color: #007AFF">上一页</a>
                        </div>
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <a href="javascript:pageConfig()" id="nextPage" style="color: #007AFF">下一页</a>
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                    &nbsp;&nbsp;&nbsp;<a href="javascript:getHolidays()" style="color: #688A7E;line-height: 40px">刷新法定节假日</a>
                </section>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <section class="panel">
                    <header class="panel-heading">
                        新建放假安排
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:showHelp()">查看帮助</a>
                    </header>
                    <div class="panel-body">
                        <form class="form-horizontal" action="" id="addHolidayForm" role="form" method="post">
                            <div class="form-group">
                                <label for="holidayName" class="col-lg-2 control-label">假期名</label>
                                <div class="col-lg-10">
                                    <input type="text" required class="form-control" id="holidayName" placeholder="输入假期名" name="holidayName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="holidayDate" class="col-lg-2 control-label">日期</label>
                                <div class="col-lg-10">
                                    <input type="date" required class="form-control" id="holidayDate" placeholder="放假日期" name="holidayDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="holidayComment" class="col-lg-2 control-label">备注</label>
                                <div class="col-lg-10">
                                    <input type="text" required class="form-control" id="holidayComment" placeholder="备注" name="holidayComment">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <input type="button" onclick="addHoliday()" class="btn btn-danger" value="提交"></input>
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <%--怎么判断只输出一次--%>
    <script type="text/x-jquery-tmpl" id="holidaytmpl">
    {{each(i,e) holidayContent}}
        <tr>
            {{if i==0}}
            <td rowspan="${'${'}holidayContent.length}">${'${'}groupName}</td>
            {{/if}}
            <td class="holiday_date">${'${'}e.holidayDate}</td>
            <td class="holiday_weekday">${'${'}e.holidayWeekday}</td>
            {{if i==0}}
            <td rowspan="${'${'}holidayContent.length}">${'${'}groupType}</td>
            <td rowspan="${'${'}holidayContent.length}">${'${'}e.holidayComment}</td>
            {{/if}}
            <td><button class="btn button btn-danger" onclick="deleteHolidayById(${'${'}e.holidayId})">删除</button></td>
        </tr>
    {{/each}}
    </script>
    <script type="text/javascript">
        $(function () {
            var today = new Date();
            $("#targetDate").text(today.format("yyyy"));
            pageConfig(1);
        })
        function appendHoliday(url) {
            $.getJSON(url,function(data){
                var arr = data.list;
                $("#tab_append").empty();
                $("#holidaytmpl").tmpl(arr).appendTo($("#tab_append"));
                rependDate();
                rependPage(data.totalRecord,data.totalPages,data.currentPage,data.nextPage,data.prePage);
            })
        }
        function deleteHolidayById(id){
            $.ajax({
                type:"DELETE",
                url:"/holidayConfig/deleteHolidayConfiguration/"+id+".do",
                success:function(result){
                    if(result == 1){
                        pageConfig(1);
                    }else{
                        $("#common_msg").text("删除失败")
                        $("#common_modal").modal("show");
                    }
                }
            })
        }

        function showHelp() {
            $("#common_msg").text("如果想创建一个假期组，那么这个假期组中的所有假期名应当一致")
            $("#common_modal").modal("show");
        }

        function pageConfig(pageNo) {
            var url = "/holidayConfig/getAllHolidayConfiguration.do";
            if(pageNo!=null){
                url = url + "?pageNo="+pageNo;
            }
            appendHoliday(url);
        }

        function rependPage(totalRecord,totalPages,currentPage,nextPage,prePage){
            $("#total_page").text(totalPages);
            $("#total_record").text(totalRecord);
            $("#current_page").text(currentPage);
            $("#nextPage").attr("href","javascript:pageConfig("+nextPage+")");
            $("#beforePage").attr("href","javascript:pageConfig("+prePage+")");
        }

        function rependDate(){
            var repenDate = $(".holiday_date");
            var repenWeekDay = $(".holiday_weekday");
            $.each(repenDate,function(index,ele){
                var date = new Date(parseInt($(ele).text()));
                $(ele).text(date.format("yyyy-MM-dd"));
            })
            $.each(repenWeekDay,function (index,ele) {
                var day = parseInt($(ele).text());
                switch (day){
                    case 1:
                        $(ele).text("星期一");
                        break;
                    case 2:
                        $(ele).text("星期二");
                        break;
                    case 3:
                        $(ele).text("星期三");
                        break;
                    case 4:
                        $(ele).text("星期四");
                        break;
                    case 5:
                        $(ele).text("星期五");
                        break;
                    case 6:
                        $(ele).text("星期六");
                        break;
                    case 7:
                        $(ele).text("星期日");
                        break;
                }
            })
        }
        function addHoliday() {
            if($("#holidayDate").val()==null||$("#holidayName").val()==""||$("#holidayComment").val()==""){
                $("#common_msg").text("表单信息不完整，不可提交")
                $("#common_modal").modal("show");
                return;
            }
            $.ajax({
                type:"POST",
                url:"/holidayConfig/postNewHolidayConfiguration.do",
                data:$("#addHolidayForm").serialize(),
                success:function(result){
                    if(result == 1){
                      $("#common_msg").text("添加假期成功")
                      $("#common_modal").modal("show");
                      pageConfig(1);
                    }else{
                        $("#common_msg").text("当前请求的日期已经存在")
                        $("#common_modal").modal("show");
                    }
                }
            })

        }

    </script>
</section>
<!--main content end-->
<jsp:include page="commonBottom.jsp"/>