
function dateConfig(){
    $.each($(".time"),function(index,ele){
        var str = $(ele).text();
        var date = new Date(str);
        var localDate = date.format("yyyy-MM-dd").toString();
        $(ele).text(localDate);
    })
}



