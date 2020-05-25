<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<style type="text/css">
    .fc-day-top.fc-sat { color:#0000FF; }     /* 토요일 */
    .fc-day-top.fc-sun { color:#FF0000; }    /* 일요일 */
</style>

 <!-- fullCalendar -->
  <link rel="stylesheet" href="${contextPath}/resources/plugins/fullcalendar/main.min.css">
  <link rel="stylesheet" href="${contextPath}/resources/plugins/fullcalendar-daygrid/main.min.css">
  <link rel="stylesheet" href="${contextPath}/resources/plugins/fullcalendar-timegrid/main.min.css">
  <link rel="stylesheet" href="${contextPath}/resources/plugins/fullcalendar-bootstrap/main.min.css">
  <!-- /fullCalendar -->
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header" style="height: 100px">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark"
							style="font-family: Recipekorea; max-width: 80%;">
							전체 캘린더</h1>
          <p class="breadcrumb float-sm-left">일정을 관리해보세요</p>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
        
          <div class="col-md-9">
            <div class="card card-primary">
              <div class="card-body p-0">
                <!-- THE CALENDAR -->
                <div id="calendar"></div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
            <div class="col-md-3">
            <div class="sticky-top mb-3" style="z-index : 1;">
              <div class="card">
                <div class="card-header">
                  <h5 class="card-title">해당 협업공간</h5>
                </div>
                <div class="card-body">
                  <!-- the events -->
							
                  <div id="external-events">
                  <c:forEach var="coitemlist" items="${coworklist}">
                    <div class="external-event" style="background-color: ${coitemlist.color}; color: white; cursor: auto;">${coitemlist.c_Name}</div>
                    </c:forEach>
                  </div>
                </div>
                <!-- /.card-body -->
              </div>
              <!-- /.card -->
            </div>
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  
  
<!-- fullCalendar 2.2.5 -->
<script src="${contextPath}/resources/plugins/moment/moment.min.js"></script>
<script src="${contextPath}/resources/plugins/fullcalendar/main.min.js"></script>
<script src="${contextPath}/resources/plugins/fullcalendar-daygrid/main.min.js"></script>
<script src="${contextPath}/resources/plugins/fullcalendar-timegrid/main.min.js"></script>
<script src="${contextPath}/resources/plugins/fullcalendar-interaction/main.min.js"></script>
<script src="${contextPath}/resources/plugins/fullcalendar-bootstrap/main.min.js"></script>
<!-- Page specific script -->
<script>
  $(function () {

    /* initialize the calendar
     -----------------------------------------------------------------*/
    //Date for the calendar events (dummy data)
    var date = new Date()
    var d    = date.getDate(),
        m    = date.getMonth(),
        y    = date.getFullYear()

    var Calendar = FullCalendar.Calendar;
    var Draggable = FullCalendarInteraction.Draggable;

    var containerEl = document.getElementById('external-events');
    var checkbox = document.getElementById('drop-remove');
    var calendarEl = document.getElementById('calendar');

    // initialize the external events
    // -----------------------------------------------------------------
    
    var calendar = new Calendar(calendarEl, {
      plugins: [ 'bootstrap', 'interaction', 'dayGrid', 'timeGrid' ],
      header    : {
        left  : 'prev',
        center: 'title',
        right : 'next'
      },
      
      themeSystem: 'bootstrap',
      editable: true,
      //Random default events
     
      events: function(info,successCallback, failureCallback){
    	  $.ajax({
				url : '${contextPath}/calendar/list',
				type : 'get',
				success : function(data) {
					successCallback(data);
				}
       });  
       },
       eventClick:function(event) {
           if(event.url) {
               
        	   alert(event.url)
               return false;
           }
       } ,


       
      editable  : true,
      droppable : true, // this allows things to be dropped onto the calendar !!!
      drop      : function(info) {
        // is the "remove after drop" checkbox checked?
        if (checkbox.checked) {
          // if so, remove the element from the "Draggable Events" list
          info.draggedEl.parentNode.removeChild(info.draggedEl);
        }
      }    
    });

    calendar.render();
    // $('#calendar').fullCalendar()

    /* ADDING EVENTS */
    var currColor = '#3c8dbc' //Red by default
    //Color chooser button
    var colorChooser = $('#color-chooser-btn')
    $('#color-chooser > li > a').click(function (e) {
      e.preventDefault()
      //Save color
      currColor = $(this).css('color')
      //Add color effect to button
      $('#add-new-event').css({
        'background-color': currColor,
        'border-color'    : currColor
      })
    })
    $('#add-new-event').click(function (e) {
      e.preventDefault()
      //Get value and make sure it is not null
      var val = $('#new-event').val()
      if (val.length == 0) {
        return
      }

      //Create events
      var event = $('<div />')
      event.css({
        'background-color': currColor,
        'border-color'    : currColor,
        'color'           : '#fff'
      }).addClass('external-event')
      event.html(val)
      $('#external-events').prepend(event)

      //Add draggable funtionality
      ini_events(event)

      //Remove event from text input
      $('#new-event').val('')
    })
  })
</script>
