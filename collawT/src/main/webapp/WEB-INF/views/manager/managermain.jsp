<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${contextPath}/resources/css/style.css"> 

<style>
.ib_product{
width: 100%;
	}
#content{

    margin-left: 0px;
}

</style>

<script>
  $(function () {
	  	var todayitem = new Date();
	  	var tomonth = todayitem.getMonth() + 1;  // 월
	  	var today = todayitem.getDate();  // 날짜
	    var ttcountitem = '${member_Count}'-1;
	    console.log(ttcountitem);
	  //bar 차트데이터
	    var barChartData = {
	    	      labels  : [tomonth + '/' + (today-6), tomonth + '/' + (today-5), tomonth + '/' + (today-4), tomonth 
	    	    	  + '/' + (today-3), tomonth + '/' + (today-2), tomonth + '/' + (today-1), tomonth + '/' + today],
	    	      datasets: [
	    	        {
	    	          label               : '기존 회원',
	    	          backgroundColor     : 'rgba(60,141,188,0.9)',
	    	          borderColor         : 'rgba(60,141,188,0.8)',
	    	          pointRadius          : false,
	    	          pointColor          : '#3b8bba',
	    	          pointStrokeColor    : 'rgba(60,141,188,1)',
	    	          pointHighlightFill  : '#fff',
	    	          pointHighlightStroke: 'rgba(60,141,188,1)',
	    	          data                : [
	    	        	  //육일전
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}'-'${chartList.get(5).get("cnt")}'
	    	        	  -'${chartList.get(4).get("cnt")}'-'${chartList.get(3).get("cnt")}'-'${chartList.get(2).get("cnt")}'
	    	        	  -'${chartList.get(1).get("cnt")}'-'${chartList.get(0).get("cnt")}',
	    	        	  //오일전
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}'-'${chartList.get(5).get("cnt")}'
	    	        	  -'${chartList.get(4).get("cnt")}'-'${chartList.get(3).get("cnt")}'-'${chartList.get(2).get("cnt")}'
	    	        	  -'${chartList.get(1).get("cnt")}',
	    	        	  //사일전
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}'-'${chartList.get(5).get("cnt")}'
	    	        	  -'${chartList.get(4).get("cnt")}'-'${chartList.get(3).get("cnt")}'-'${chartList.get(2).get("cnt")}',
	    	        	  //삼일전
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}'-'${chartList.get(5).get("cnt")}'
	    	        	  -'${chartList.get(4).get("cnt")}'-'${chartList.get(3).get("cnt")}',
	    	        	  //이틀전
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}'-'${chartList.get(5).get("cnt")}'
	    	        	  -'${chartList.get(4).get("cnt")}',
	    	        	  //하루전
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}'-'${chartList.get(5).get("cnt")}',
	    	        	  //오늘
	    	        	  '${member_Count}'-'${chartList.get(6).get("cnt")}']
	    	        },
	    	        {
	    	          label               : '신규 회원',
	    	          backgroundColor     : 'rgba(256, 192, 40, 1)',
	    	          borderColor         : 'rgba(256, 192, 40, 1)',
	    	          pointRadius         : false,
	    	          pointColor          : 'rgba(256, 192, 40, 1)',
	    	          pointStrokeColor    : '#c1c7d1',
	    	          pointHighlightFill  : '#fff',
	    	          pointHighlightStroke: 'rgba(256, 192, 40, 1)',
	    	          data                : ['${chartList.get(0).get("cnt")}','${chartList.get(1).get("cnt")}',
	    	        	  '${chartList.get(2).get("cnt")}','${chartList.get(3).get("cnt")}','${chartList.get(4).get("cnt")}',
	    	        	  '${chartList.get(5).get("cnt")}','${chartList.get(6).get("cnt")}']
	    	        },
	    	      ]
	    	    }

	  //bar 차트
	  var stackedBarChartCanvas = $('#stackedBarChart').get(0).getContext('2d')
	    var stackedBarChartData = jQuery.extend(true, {}, barChartData)

	    var stackedBarChartOptions = {
	      responsive              : true,
	      maintainAspectRatio     : false,
	      scales: {
	        xAxes: [{
	          stacked: true,
	        }],
	        yAxes: [{
	          stacked: true
	        }]
	      }
	    }

	    var stackedBarChart = new Chart(stackedBarChartCanvas, {
	      type: 'bar', 
	      data: stackedBarChartData,
	      options: stackedBarChartOptions
	    })
	  
	  
	  console.log('${donutList}')
	   //도넛 차트
	    var donutChartCanvas = $('#donutChart').get(0).getContext('2d')
	    var donutData        = {
	      labels: [
	          '협업업무관련', 
	          '개인업무관련',
	          '학업과제관련', 
	          '기타', 
	      ],
	      datasets: [
	        {
	          data: ['${donutList.get(0).get("cnt")}','${donutList.get(1).get("cnt")}',
	        	  '${donutList.get(2).get("cnt")}','${donutList.get(3).get("cnt")}'],
	          backgroundColor : ['#f56954', '#00a65a', '#f39c12', '#3c8dbc'],
	        }
	      ]
	    }
	    var donutOptions     = {
	      maintainAspectRatio : false,
	      responsive : true,
	    }
	    var donutChart = new Chart(donutChartCanvas, {
	      type: 'doughnut',
	      data: donutData,
	      options: donutOptions      
	    })
	  
	  
	  //라인차트 
	    var lineChartCanvas = $('#lineChart').get(0).getContext('2d')
	    var lineChartOptions = {
	        maintainAspectRatio : false,
	        responsive : true,
	        legend: {
	          display: false
	        },
	        scales: {
	          xAxes: [{
	            gridLines : {
	              display : false,
	            }
	          }],
	          yAxes: [{
	            gridLines : {
	              display : false,
	            }
	          }]
	        }
	      }
	    var lineChartData = {
	    	      labels  : [tomonth + '/' + (today-6), tomonth + '/' + (today-5), tomonth + '/' + (today-4), tomonth 
	    	    	  + '/' + (today-3), tomonth + '/' + (today-2), tomonth + '/' + (today-1), tomonth + '/' + today],
	    	      datasets: [
	    	    	  {
	    	              label               : '주간 이슈 등록수',
	    	              backgroundColor     : 'rgba(210, 214, 222, 1)',
	    	              borderColor         : 'rgba(210, 214, 222, 1)',
	    	              pointRadius         : true,
	    	              pointColor          : 'rgba(210, 214, 222, 1)',
	    	              pointStrokeColor    : '#c1c7d1',
	    	              pointHighlightFill  : '#fff',
	    	              pointHighlightStroke: 'rgba(220,220,220,1)',
	    	              data                : ['${lineList.get(0).get("cnt")}', '${lineList.get(1).get("cnt")}',
	    	            	  '${lineList.get(2).get("cnt")}', '${lineList.get(3).get("cnt")}', '${lineList.get(4).get("cnt")}', 
	    	            	  '${lineList.get(6).get("cnt")}', '${lineList.get(5).get("cnt")}']
	    	            },
	    	      ]
	    	    }
	    lineChartData.datasets[0].fill = false;
	    lineChartOptions.datasetFill = false;
	  
	    var lineChart = new Chart(lineChartCanvas, { 
	      type: 'line',
	      data: lineChartData, 
	      options: lineChartOptions
	    })
	  
	  })
    </script>
    
    <div class="content-wrapper" >
  <div class="main_content">
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-6">
    
      <div class="card card-warning">
              <div class="card-header">
                <h3 class="card-title" style="font-family: fantasy; color: white">미답변 문의내역</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                </div>
              </div>
              <div class="card-body" style="min-height:290px; max-height:290px; overflow:auto">
                <div class="table-responsive">
                  <table class="table m-0">

                    <tbody>
              <c:choose>
			<c:when test="${fn:length(recentBoard)!=0}">
       <c:forEach var="recentBoarditem" items="${recentBoard}" >
                    <tr>
                      <td name="boarditem" style="width:70%">
							<a style="color:black" href="/manager/question/read?q_Num=${recentBoarditem.q_Num}">${recentBoarditem.q_Subject}</a>                      
                      </td>
                        <td name="boarditem" style="width:30%">
							${recentBoarditem.q_Date}                    
                      </td>
                    </tr>
         </c:forEach>
            </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br><br><br>최근 등록된 문의가 없습니다.</small></p>
                 </c:otherwise>
                 </c:choose>
                                      
               
                    </tbody>
                  </table>
                </div>


              </div>
              <!-- /.card-body -->
            </div>

            
            <!-- DONUT CHART -->
            <div class="card card-danger">
              <div class="card-header">
                <h3 class="card-title" style="font-family: fantasy;">협업공간 카테고리 비율</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                </div>
              </div>
              <div class="card-body">
                <canvas id="donutChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
            </div>
             <!-- /.col (LEFT) -->
          <div class="col-md-6">
                 <!-- BAR CHART -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title" style="font-family: fantasy;">누적 회원</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                </div>
              </div>
              <div class="card-body">
                <div class="chart">
                  <canvas id="stackedBarChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
            <!-- LINE CHART -->
            <div class="card card-info">
              <div class="card-header">
                <h3 class="card-title" style="font-family: fantasy;">일별 이슈 등록차트</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                </div>
              </div>
              <div class="card-body">
                <div class="chart">
                  <canvas id="lineChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
            
           
            </div>
            

            
            
            </div>
            </div>
            </section>
            </div>
            </div>