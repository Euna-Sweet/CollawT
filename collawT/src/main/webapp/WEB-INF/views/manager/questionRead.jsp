<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css"> 
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<style>
.ib_product{
width: 100%;
	}
#content{

    margin-left: 0px;
}

</style>


<script type="text/javascript">

$(document).ready(function() {
	$("#submit").click(function(){
		var as_Content = $("#as_Content").val();
		
		if(as_Content.length < 8||as_Content.length > 300){
			alert("8 ~ 300자 내로 작성하세요. ");
			return false;
		}else{
			if(confirm("문의 답변 이메일을 보내시겠습니까?")){
				document.insertForm.submit();
			}else{
				return false;
			}
		}
	})
	
})

	
	
</script>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					
				</div>
				<div class="col-sm-6">
			
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->

	<section class="content">

		<div class="row">
			<div class="col-md-6">

				<!-- Box Comment -->
<!-- 본문 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->				
			

					<div class="card card-widget">
						<div class="card-header">
						
						<span style= "vertical-align:text-top;
								<c:if test="${questionRead.q_Answer == '미확인'}">background-color:#ffc107;</c:if>
								<c:if test="${questionRead.q_Answer == '확인중'}">background-color:#007bff;</c:if>
								<c:if test="${questionRead.q_Answer == '답변완료'}">background-color:#28a745;</c:if>
						"
							id="q_Answer" name="q_Answer" class="badge badge-success"><c:out  value="${questionRead.q_Answer}" /></span>&nbsp;&nbsp;
							<span id="q_Subject" name="q_Subject"><b><c:out	value="${questionRead.q_Subject}" /></b></span>

														<div class="btn-group float-right">
                          <button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false" style="border:white;background-color:white;height:20px;padding-top: 0px;color:gray"><small>답변상태 변경 ▼</small>
                          </button>
                          <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; right: 0px; transform: translate3d(0px, 38px, 0px);">
                            
                        
                            <li><small><a class="dropdown-item" href="/manager/question/update?q_Num=${questionRead.q_Num}&q_Answer=확인중">확인중</a></small></li>
                            <li><small><a class="dropdown-item" href="/manager/question/update?q_Num=${questionRead.q_Num}&q_Answer=답변완료">답변완료</a></small></li>
                          </ul>
                       
                        </div>

							


						</div>
						

						
						<!-- /.card-header -->

						<div class="card-body">
						<span class="text-muted float-right"id="q_Date" name="q_Date" style="font-size:small">
								<c:out	value="${questionRead.q_Date}" /></span>
							<div class="user-block">
								
								<span id="q_Email" name="q_Email" style="font-size:10pt"> 
								<c:out value="${questionRead.q_Email}" /></span>

							</div>
							<br><br><br>
							
							
				
								<br>
							
							<!-- 내용 -->
								<p id="q_Content" name="q_Content"><c:out value="${questionRead.q_Content}" escapeXml="false"/></p>
						
							
							
							<br>
							<br>
							<br>
 <br>
							<br>

			

							

						
						<a id="list_btn" class="btn btn-default btn-sm" href='/manager/question/main' style="color:#444; margin:3px;"> <i class="fas fa-list"></i>&nbsp;목록</a>

								
								<br><br>
						</div>

						<!-- /.card-body -->

						<!-- /.card -->
					</div>



					<!-- /.card -->
					
			</div>

<!-- /.card -->




<!-- 답변하기 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->				

			<div class="col-md-6">

			
			<div class="card card-widget">
						<div class="card-header">
							<b>답변하기</b>
						</div>
						<div class="card-body">
				<form name = insertForm action="/manager/question/insert" method="post" encType="UTF-8">
               <div class="form-group">
               <textarea name="as_Content" id="as_Content" class="form-control" style="height:263px;" placeholder=""></textarea>
               
				 <input type="hidden" id="q_Num" name="q_Num" value="${questionRead.q_Num}" />
				 <input type="hidden" id="q_Email" name="q_Email" value="${questionRead.q_Email}" />
					
              </div>
              
			<input type="submit" id = "submit" value="메일 보내기" class="btn btn-danger btn-sm float-right" style="margin:3px;">
              
			
			</form>		
			</div>
			</div>
			
			
			
</div>
			</div>
<!--  이전 답변 내역 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
		<div class="row">
			<div class="col-md-12">		
				<div class="card card-widget">
						<div class="card-header">
							<b>이전 답변내역</b>
						</div>
						<div class="card-body">
				<table class="table table-striped table-bordered table-hover dataTable">
			<c:choose>
			<c:when test="${fn:length(beforeAnswerList)!=0}">
			
				
				 <thead style="font-size:13px;">
				 <tr role="row">
				<th>답변날짜</th>
               	<th>답변내용</th>
				</tr>
				
              <tbody>
			
                 <c:forEach var="answeritem" items="${beforeAnswerList}" >	
                  <tr>
                  
					<td style="width:15%; vertical-align:top;"><font size="2em" color="#6c757d">${answeritem.as_Date}</font></td>
					<td style="width:85%; vertical-align:top;"><font size="2em" color="#6c757d">${answeritem.as_Content}</font></td>
	          	 </tr>
                   
                   </c:forEach>
                 </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br>답변내역이 없습니다.</small></p><br><br>
                 </c:otherwise>
                 </c:choose>
              </tbody>
          </table>
						</div>
			</div>
			</div>
		</div>
					
					
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

