<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}" />





<style>
.accent-teal .btn-link, .accent-teal a:not(.dropdown-item):not(.btn-app):not(.nav-link):not(.brand-link) {
    color: #343a40;
}
@font-face {
	font-family: 'Recipekorea';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
.nav-pills .nav-link.active{
		    background-color: #dc3545;
}


</style>

<script type="text/javascript">
$(function () {
    //-------------
    //- DONUT CHART -
    //-------------
    // Get context with jQuery - using jQuery's .get() method.
    var myissue1 = $('#myissue1').val();
    var myissue2 = $('#myissue2').val();
    var myissue3 = $('#myissue3').val();
    var myissue4 = $('#myissue4').val();
    var myissuename1 = $('#myissuename1').val();
    var myissuename2 = $('#myissuename2').val();
    var myissuename3 = $('#myissuename3').val();
    var myissuename4 = $('#myissuename4').val();
    var myissuecolor1 = $('#myissuecolor1').val();
    var myissuecolor2 = $('#myissuecolor2').val();
    var myissuecolor3 = $('#myissuecolor3').val();
    var myissuecolor4 = $('#myissuecolor4').val();
    var total = parseInt(myissue1)+parseInt(myissue2)+parseInt(myissue3)+parseInt(myissue4)
    var donutChartCanvas1 = $('#donutChart1').get(0).getContext('2d')
    var donutData1        = {
      labels: [
    	  myissuename1, 
    	  myissuename2,
    	  myissuename3, 
    	  myissuename4, 
           
      ],
      datasets: [
        {
          data: [myissue1,myissue2,myissue3,myissue4],
          backgroundColor : [myissuecolor1, myissuecolor2, myissuecolor3, myissuecolor4],
        }
      ],

    }
    var donutOptions1     = {
      maintainAspectRatio : false,
      responsive : true,
      cutoutPercentage: 65,
      legend: {
          display: true,
          position : 'left',
          align : 'start',
        labels: {pointStyle:'circle', usePointStyle:true}
      },
  	elements: {
  			center: {
  				text: '총 '+total+'건',
		        color: 'gray-dark', // Default is #000000
		        fontStyle: 'Arial', // Default is Arial
		        sidePadding: 20 // Defualt is 20 (as a percentage)
  			}
  		}
  	}     
    
    
    

    //Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    var donutChart1 = new Chart(donutChartCanvas1, {
      type: 'doughnut',
      data: donutData1,
      options: donutOptions1      
    })
	
	
	
	
	Chart.pluginService.register({
		beforeDraw: function (chart) {
			if (chart.config.options.elements.center) {
        //Get ctx from string
        var ctx = chart.chart.ctx;
        
				//Get options from the center object in options
        var centerConfig = chart.config.options.elements.center;
      	var fontStyle = centerConfig.fontStyle || 'Arial';
				var txt = centerConfig.text;
        var color = centerConfig.color || '#000';
        var sidePadding = centerConfig.sidePadding || 20;
        var sidePaddingCalculated = (sidePadding/100) * (chart.innerRadius * 2)
        //Start with a base font of 30px
        ctx.font = "30px " + fontStyle;
        
				//Get the width of the string and also the width of the element minus 10 to give it 5px side padding
        var stringWidth = ctx.measureText(txt).width;
        var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;

        // Find out how much the font can grow in width.
        var widthRatio = elementWidth / stringWidth;
        var newFontSize = 30;
        var elementHeight = (chart.innerRadius * 2);

        // Pick a new font size so it will not be larger than the height of label.
        var fontSizeToUse = Math.min(newFontSize, elementHeight);

				//Set font settings to draw it correctly.
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
        var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
        ctx.font = fontSizeToUse+"px " + fontStyle;
        ctx.fillStyle = color;
        
        //Draw text in center
        ctx.fillText(txt, centerX, centerY);
			}
		}
	});
	
    
	$('#myform').click(function(event) {
		
		var button_Id = event.target.getAttribute('id');
		var mem_Id = $('#mem_Id').val();
		var c_Id = $('#c_Id').val();
		console.log(button_Id)
		//초대 수락을 위한 ajax
		if (button_Id == 'acceptsubmit') {
			$.ajax({
			url : '${contextPath}/news/accept?mem_Id='+mem_Id+'&c_Id='+c_Id,
			type : 'get',
			success : function(data) {
				if (data == 1) {
				alert('수락하셨습니다.');
				setCookie('apply', 'active', 1)
				location.reload();
						}
					}
				})
		
		}else if(button_Id == 'rejectsubmit'){//초대 거절을 위한 ajax
			$.ajax({
				url : '${contextPath}/news/reject?mem_Id='+mem_Id+'&c_Id='+c_Id,
				type : 'get',
				success : function(data) {
					if (data == 1) {
					alert('거절하셨습니다.');
					setCookie('apply', 'active', 1)
					location.reload();
						}
					}
				}) 
		}
	
	})
    
    
})
  

		
</script>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<div class="content-header" style="height: 80px">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<div>
						<h1 class="m-0 text-dark"
							style="font-family: Recipekorea; max-width: 80%; display: contents;">내 공간</h1>
					</div>

					</div>
			</div>
		</div>
	</div>
	<div class="col-md-9" style="max-width: 100%;">
		<div class="card">
			<div class="card-header p-2">
				<ol class="nav nav-pills" style="font-family: Spoqa Han Sans; font-size:15px; margin:0px; border:none; padding-inline-start: 0px;  float:left;  ">
					<li class="nav-item" ><a class="nav-link active" style="background-color:#DC3545;"
						href="/main"  id="activityMenu" ><b>홈</b></a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/list?mem_Id=${member.mem_Id}" >내 이슈</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myChargerlist?mem_Id=${member.mem_Id}" id="activityMenu">내 담당 이슈</a></li>
					<li class="nav-item" ><a class="nav-link " 
						href="/personal/search/myBoardlist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 글</a></li>
					<li class="nav-item" ><a class="nav-link"
						href="/personal/search/myReplylist?mem_Id=${member.mem_Id}" id="activityMenu">내가 쓴 댓글</a></li>
					
				</ol>
				
								
			</div>

			
						<!-- Main content -->
						<section class="content" >
							<div class="container-fluid">
							<br>
								
<!--  새로운 소식 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
			<div class="row">
			<div class="col-md-7">
				<div class="card" style="min-height:695px; max-height:695px">
              <div class="card-header">
                <h3 class="card-title"><b>새로운 소식</b></h3>

              </div>
              <!-- /.card-header -->
              
              <div class="card-body" style="overflow:auto">
              
            <c:if test="${fn:length(newspeed)==0}">
            <p style="text-align:center;">
            <br><br><br><br><br><br><br><br><br><br><i class="far fa-newspaper" style="font-size:5em; color:gray;"></i>
            <br>새로운 소식이 없습니다.<br>:D</p>
           
           </c:if>

			<c:if  test= "${fn:length(newspeed)>0}">
                <div class="timeline timeline-inverse">
                	<c:forEach var="newspeeditem" items="${newspeed}">
							 <c:choose>
							 <c:when  test= "${newspeeditem.type eq '1'}">
							<div>
								
								<i class="fas fa-envelope bg-primary"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> ${newspeeditem.ap_Date}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

									<h3 class="timeline-header">
									<b>초대 │ </b>
									from <b>${newspeeditem.c_Name}</b>
									</h3>
									<div class="timeline-body">

									
									${newspeeditem.wr_mem_Name}(${newspeeditem.wr_mem_Id})님께서 회원님을&nbsp;<b>${newspeeditem.c_Name}</b>에 초대하셨습니다. </div>
									<div class="timeline-footer" id="timeline-footer">
									<form  id = "myform" action="/news/accept" method="post" style="display: inline;  margin: 5;">
									<input type="hidden" name="c_Id" id="c_Id" value="${newspeeditem.c_Id}">
									<input type="hidden" name="mem_Id" id="mem_Id" value="${newspeeditem.arm_mem_Id}">
										<a id = "acceptsubmit" href="#" class="btn btn-primary btn-sm" style="color: white;">수락</a>
										<a id = "rejectsubmit" href="#" class="btn btn-danger btn-sm" style="color: white;">거절</a>
										</form>
									</div>
								</div>
							</div>
							</c:when>
							
							 <c:when  test= "${newspeeditem.type eq '2'}">
							 
							<div>
								<i class="fas fa-clipboard-list bg-info"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> ${newspeeditem.ap_Date}&nbsp;&nbsp;&nbsp;
									<a href="/notify/update?c_Id=${newspeeditem.c_Id}&i_Num=${newspeeditem.i_Num}&status=main" style="color:red"><i class="fas fa-times"></i></a>
									</span>

									<h3 class="timeline-header">
									<b>이슈 │ </b>
										from <a href="/project/main?c_Id=${newspeeditem.c_Id}">${newspeeditem.c_Name}</a>
									</h3>
									<div class="timeline-body">

									${newspeeditem.wr_mem_Name}(${newspeeditem.wr_mem_Id})님이 &nbsp;<a href="/notify/update?c_Id=${newspeeditem.c_Id}&i_Num=${newspeeditem.i_Num}&status=read"><b>${newspeeditem.i_Name}</b></a>&nbsp;이슈를 등록하셨습니다.</div>
									<div class="timeline-footer" id="timeline-footer">
									</div>
								</div>
							</div>
							</c:when>
							
							<c:when  test= "${newspeeditem.type eq '3'}">
							<div>
								<i class="fas fa-comments bg-warning"></i>

								<div class="timeline-item">

									<span class="time"><i class="far fa-clock"></i> ${newspeeditem.ap_Date}&nbsp;&nbsp;&nbsp;
									<a href="/notify/replyupdate?c_Id=${newspeeditem.c_Id}&i_Num=${newspeeditem.i_Num}&r_Num=${newspeeditem.r_Num}&status=main" style="color:red"><i class="fas fa-times"></i></a></span>

									<h3 class="timeline-header">
									<b>댓글 │ </b>
									from <a href="/project/main?c_Id=${newspeeditem.c_Id}">${newspeeditem.c_Name}</a>
										
									</h3>
									<div class="timeline-body">
									
									${newspeeditem.wr_mem_Name}(${newspeeditem.wr_mem_Id})님이&nbsp;<a href="/notify/replyupdate?c_Id=${newspeeditem.c_Id}&i_Num=${newspeeditem.i_Num}&r_Num=${newspeeditem.r_Num}&status=read"><b>${newspeeditem.i_Name}</b></a>글에 댓글을 남기셨습니다.
									<div class="row" style="margin-left:32px; margin-right:32px;"><small>${newspeeditem.i_Content}</small></div></div>
									<div class="timeline-footer" id="timeline-footer">
									</div>
								</div>
							</div>
							</c:when>
							
							<c:when  test= "${newspeeditem.type eq '4'}">
							 
							<div>
								<i class="fas fa-vote-yea bg-purple"></i>

								<div class="timeline-item">
									<span class="time"><i class="far fa-clock"></i> ${newspeeditem.ap_Date}&nbsp;&nbsp;&nbsp;
									<a href="/notify/voteUpdate?c_Id=${newspeeditem.c_Id}&v_Num=${newspeeditem.i_Num}&status=main" style="color:red"><i class="fas fa-times"></i></a>
									</span>

									<h3 class="timeline-header">
									<b>투표 │ </b>
										from <a href="/project/main?c_Id=${newspeeditem.c_Id}">${newspeeditem.c_Name}</a>
									</h3>
									<div class="timeline-body">

									${newspeeditem.wr_mem_Name}(${newspeeditem.wr_mem_Id})님이 &nbsp;<a href="/notify/voteUpdate?c_Id=${newspeeditem.c_Id}&v_Num=${newspeeditem.i_Num}&status=read"><b>${newspeeditem.i_Name}</b></a>&nbsp;투표를 등록하셨습니다.
									</div>
									<div class="timeline-footer" id="timeline-footer">
									</div>
								</div>
							</div>
							</c:when>
							
							<c:when  test= "${newspeeditem.type eq '5'}">
							<c:if test="${newspeeditem.mem_Id != member.mem_Id}">
							<div>
								<i class="fas fa-comments bg-warning"></i>

								<div class="timeline-item">

									<span class="time"><i class="far fa-clock"></i> ${newspeeditem.ap_Date}&nbsp;&nbsp;&nbsp;
									<a href="/notify/votereplyupdate?c_Id=${newspeeditem.c_Id}&v_Num=${newspeeditem.i_Num}&vr_Num=${newspeeditem.r_Num}&status=main" style="color:red"><i class="fas fa-times"></i></a></span>

									<h3 class="timeline-header">
									<b>댓글 │ </b>
									from <a href="/project/main?c_Id=${newspeeditem.c_Id}">${newspeeditem.c_Name}</a>
										
									</h3>
									<div class="timeline-body">

									${newspeeditem.wr_mem_Name}(${newspeeditem.wr_mem_Id})님이&nbsp;<a href="/notify/votereplyupdate?c_Id=${newspeeditem.c_Id}&v_Num=${newspeeditem.i_Num}&vr_Num=${newspeeditem.r_Num}&status=read"><b>${newspeeditem.i_Name}</b></a>글에 댓글을 남기셨습니다.
									<div class="row" style="margin-left:32px; margin-right:32px;"><small>${newspeeditem.i_Content}</small></div></div>
									<div class="timeline-footer" id="timeline-footer">
									</div>
								</div>
							</div>
							</c:if>
							</c:when>

							</c:choose>
							  </c:forEach>
							  
 

					<div>
                        <i class="far fa-clock bg-gray"></i>
                      </div>	
                </div>
                </c:if> 


                
                <!-- /.table-responsive -->
              </div>
              <!-- /.card-body -->

            </div>
            
            
            

            </div>
            
            		
    <!--  이슈/투표 등 정보 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->        

			<div class="col-md-5">
			
			<div class="card">
              <div class="card-header">
                <h3 class="card-title" ><b>내 담당 이슈 진행 현황</b></h3>

              </div>
              <div class="card-body">
				<div class="chart-responsive">
                <canvas id="donutChart1" style="min-height: 252px; height: 252px; max-height: 252px; max-width: 100%; display: block; width: 406px;" width="812" height="502" class="chartjs-render-monitor"></canvas>
             	 </div>
             	 
             	 <c:forEach var="myissueitem" items="${myissue}">
             	 <input type="hidden" value="${myissueitem.COUNT}" id="myissue${myissueitem.IG_NUM}">
             	 <input type="hidden" value="${myissueitem.IG_NAME}" id="myissuename${myissueitem.IG_NUM}">
             	 <input type="hidden" value="${myissueitem.IG_COLOR}" id="myissuecolor${myissueitem.IG_NUM}">
             	 </c:forEach>

              </div>
              <!-- /.card-body -->
            </div>
			



				<div class="card" style="max-height:337.800px; min-height:337.800px;">
              <div class="card-header">
                <h3 class="card-title"><b>참여 가능한 투표</b></h3>
              </div>
              <div class="card-body" style="overflow:auto">
				<div class="table-responsive">
                  <table class="table m-0">

                    <tbody>
                    <c:forEach var="vaitem" items="${voteAvailable}">
                    <tr>
                      <td style="width:60%"><a href="/project/vote/read?c_Id=${vaitem.c_Id}&v_Num=${vaitem.v_Num}"><b>${vaitem.v_Name}</b></a> </td>
                      <td style="width:40%"><small>from <a href="/project/main?c_Id=${vaitem.c_Id}"><b>${vaitem.c_Name}</b></a></small></td>
                    </tr>
                    </c:forEach>

               
                    </tbody>
                  </table>
                </div>


              </div>
              <!-- /.card-body -->
            </div>
			

			
			</div>

					</div>
							</div>
							<!-- /.container-fluid -->
						</section>
						<!-- /.content -->
</div>
</div>
</div>
		
<!-- /.content-wrapper -->

  