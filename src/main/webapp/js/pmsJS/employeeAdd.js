var statusData;
var departmentId;

$(function () {
    //查询在职状态列表
    statusConfig();
    //领导工号查询按钮绑定
    queryEmployeeWorkIdBind();
    //领导查询按钮绑定（先选择部门才可以查询）
    queryLeaderPageBind();
    //查询当前最合适工号
    workIdConfig();
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


function workIdConfig() {
    $.getJSON("/EmployeeAjaxController/queryMaxWorkId.do", function (maxId) {
        $("input[name='employeeWorkId']").val(maxId);
    });
}
function departmentConfig() {
    $.getJSON("/Department.do", function (data) {
        $.each(data, function (index, ele) {
            var $opt = $("<option value=" + ele.departmentId + ">" + ele.departmentName + "</option>");
            $("select[name='department.departmentId']").append($opt);
        });
    })
}

function  positionConfig() {
    $.getJSON("/Position.do", function (data) {
        $.each(data, function (index, ele) {
            var $opt = $("<option value=" + ele.positionId + ">" + ele.positionName + "</option>");
            $("select[name='position.positionId']").append($opt);
        });
    })
}
function statusConfig() {
    var $confi = $(".status");
    $.getJSON("/Status.do", function (data) {
        statusData = data;
        $.each(data, function (index, ele) {
            var $opt = $("<option value=" + ele.statusId + ">" + ele.statusName + "</option>");
            $("select[name='statusId']").append($opt);
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