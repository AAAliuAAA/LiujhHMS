<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/10
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="commonTop.jsp"/>
<!-- full calendar css-->
<link href="${pageContext.request.contextPath}/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/widgets.css" rel="stylesheet">
<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-calendar"></i> 工作日历</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-home"></i><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
                    <li><i class="fa fa-calendar"></i>工作日历</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1"></div><div class="col-md-10 portlets">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2><strong>工作日历</strong></h2>
                    <div class="panel-actions">
                        <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a>
                        <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                    </div>
                </div><br><br><br>
                <div class="panel-body">
                    <div id="calendar"></div>
                </div>
            </div>
        </div>
            <div class="col-md-1"></div>
        </div>
    </section>
</section>
<!--main content end-->
</section>

<!-- jQuery full calendar -->
<script src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="${pageContext.request.contextPath}/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
<!--script for this page only-->
<script src="${pageContext.request.contextPath}/js/jquery.rateit.min.js"></script>
<script>
    $(function () {
        attendanceCalendarConfig();
    })
    //配置日历的方法
    //传入当前的事件参数，然后在里面进行调用
    //var variable = new Date(year, month, day, hours, minutes, seconds, milliseconds);
    function attendanceCalendarConfig(eventList) {
        /* initialize the external events
         -----------------------------------------------------------------*/
        $('#external-events div.external-event').each(function() {
            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            // it doesn't need to have a start or end
            var eventObject = {
                title: $.trim($(this).text()) // use the element's text as the event title
            };

            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);
            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });
        });
        /* initialize the calendar
         -----------------------------------------------------------------*/
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            editable: true,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function(date, allDay) { // this function is called when something is dropped
                // retrieve the dropped element's stored Event Object
                var originalEventObject = $(this).data('eventObject');
                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);
                // assign it the date that was reported
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDay;
                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }
            },
            //new Date()的参数有哪些
            //整数类型的：当前年，当前月，日，
            events: [
                {
                    title: 'All Day Event',
                    start: new Date(y, m, 1)
                },
                {
                    title: 'Long Event',
                    start: new Date(y, m, d-5),
                    end: new Date(y, m, d-2)
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: new Date(y, m, d-3, 16, 0),
                    allDay: false
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: new Date(y, m, d+4, 16, 0),
                    allDay: false
                },
                {
                    title: 'Meeting',
                    start: new Date(y, m, d, 10, 30),
                    allDay: false
                },
                {
                    title: 'Lunch',
                    start: new Date(y, m, d, 12, 0),
                    end: new Date(y, m, d, 14, 0),
                    allDay: false
                },
                {
                    title: 'Birthday Party',
                    start: new Date(y, m, d+1, 19, 0),
                    end: new Date(y, m, d+1, 22, 30),
                    allDay: false
                },
                {
                    title: 'Click for Google',
                    start: new Date(y, m, 28),
                    end: new Date(y, m, 29),
                    url: 'http://google.com/'
                }
            ]
        });
    }
</script>

<jsp:include page="commonBottom.jsp"/>