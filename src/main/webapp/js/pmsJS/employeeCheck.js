var departmentOptionArr;
var statusData;
var departmentData;

$(function () {
    //将全局的异步数据查询出来
    queryDepartmentData();
    queryStatusData();
    $("#hideLeave").addClass("active");
//  隐藏选择控件
    $("#values1").hide();
    $("#values2").hide();

//  使用ajax发送请求获取页面数据
    query(1);

});

function queryDepartmentData(){
    $.getJSON("/Department.do",function(data){
        departmentData = data;
        queryDepartment(departmentData);
    });
}

function queryStatusData() {
    $.getJSON("/Status.do",function(data){
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
    $.getJSON("/EmployeeAjaxController/queryEmployees.do", data, function (data) {
        if(data.pages<pageNo&&data.pages!=0){
            $('#alertModal').modal('show');
        }else{
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
/**
 *
 */
function queryDepartment(departmentData){
    departmentOptionArr = new Array();
    $seltmp = $("<option class='input-sm' selected>请选择部门...</option>");
    departmentOptionArr.push($seltmp);
    $.each(departmentData,function(index,ele){
        departmentOptionArr.push($("<option class='input-sm' value='"+ele.departmentId+"'>"+ele.departmentName+"</option>"));
    });
}

function statusConfigToText(statusData){
    var $confi = $(".status");
    $.each(statusData,function(index,eleStatus){
        $.each($confi,function(index,ele){
            if($(ele).text()==eleStatus.statusId){
                $(ele).text(eleStatus.statusName);
            }
        });
    });
}

function pageConfig(data) {
    //配置当前分页信息
    $(".prePage").attr("onclick", "query(" + data.prePage + ")");
    $(".nextPage").attr("onclick", "query(" + data.nextPage + ")");
    $(".totalPage").text(data.pages);
    $(".totalRecord").text(data.total);
    if (data.pages > 10) {
        for (var i = 1; i <= 3; i++) {
            if (data.pageNum == i) {
                $("<button class='btn btn-primary active' type='button' onclick='query(" + i + ")'>" + i + "</button>").appendTo($(".pageGroup"));
            } else {
                $("<button class='btn btn-primary' type='button' onclick='query(" + i + ")'>" + i + "</button>").appendTo($(".pageGroup"));
            }
        }
        ;
        $("<button class='btn' type='button'>...</button>").appendTo($(".pageGroup"));

        for (var i = (data.pages - 2); i <= data.pages; i++) {
            if (data.pageNum == i) {
                $("<button class='btn btn-primary active' type='button' onclick='query(" + i + ")'>" + i + "</button>").appendTo($(".pageGroup"));
            } else {
                $("<button class='btn btn-primary' type='button' onclick='query(" + i + ")'>" + i + "</button>").appendTo($(".pageGroup"));
            }
        }
        ;
    } else {
        for (var i = 1; i <= data.pages; i++) {
            if (data.pageNum == i) {
                $("<button class='btn btn-primary active' type='button' onclick='query(" + i + ")'>" + i + "</button>").appendTo($(".pageGroup"));
            } else {
                $("<button class='btn btn-primary' type='button' onclick='query(" + i + ")'>" + i + "</button>").appendTo($(".pageGroup"));
            }
        }
        ;
    }
}

function changeStatusCondition(statusId){
    if( $("input[name='statusId']").val()==0||$("input[name='statusId']").val()=="0"){
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
        case "checkByDepartment":
            $inputV.hide();
            $inputV2.hide();
            $inputV2.attr("name", "departmentId");
            $.each(departmentOptionArr,function (index,ele) {
                $inputV2.append($(ele));
            })
            $inputV2.show();
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

function jump(){
    var pageNo =  $("#jumpInput").val();
    if(pageNo!=null){
        query(pageNo);
    }
}