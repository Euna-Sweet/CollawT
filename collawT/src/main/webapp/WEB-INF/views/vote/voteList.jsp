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
.nav-pills .nav-link.active {
	background-color: #dc3545;
}

</style>


<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

$('#applyform').ready(function() {
	$('#insertsubmit').click(function() {
		var listnum = $('#applyList').children().length;
			if (listnum == 0) { alert("초대 리스트가 비어있습니다");
				return false;}
				})
				
		var applyCount = 0;
		var check_Id = new Array();
				
	$('#item_mem_Id').keydown(function(event) {
		
		
		if (event.keyCode == '13') {
			var mem_Id = $('#item_mem_Id').val();
			var check_stat = true;
				if (mem_Id == '${member.mem_Id}') {
					$("#id_check").text("자신을 초대할수 없습니다 :p");
					$("#id_check").css("color","red");} 
				else {
					$.ajax({
						url : '${contextPath}/news/memberCheck?mem_Id='+ mem_Id,
						type : 'get',
						success : function(data) {console.log("1 = 중복o / 0 = 중복x : " + data);
							if (data == 1) {
								for (var i = 0; i < check_Id.length; i++) {
								if (mem_Id == check_Id[i]) {
									$("#id_check").text("동일한 아이디를 여러번 초대할수 없습니다. :p");
									$("#id_check").css("color","red");
									check_stat = false;
									} 
								}
								if (check_stat){
									$('#applyList').append("<span id= 'applyspan"+applyCount+"'>"+ mem_Id+ "<a id ='applydelete' onclick = 'delbtn("+applyCount+")' href='#'>X</a></span>");
									$('#applyform').append("<input type='hidden' id='hidden_Id"+applyCount+"' name='mem_Id' value='"+mem_Id+"'>");
									$('#item_mem_Id').val("");
									applyCount++;
									check_Id.push(mem_Id);
									}
							} else {
								$("#id_check").text("잘못된 아이디 입니다 다시 확인해주세요 :p");
								$("#id_check").css("color","red");}
							}
						})
					}
				} else {$("#id_check").text("이메일 주소를 입력하고 Enter키를 눌러 파트너들을 초대해 보세요.");
						$("#id_check").css("color","#a1a1a1");}
			});
		});

function delbtn(applyCount){
	$('#applyspan'+applyCount).remove();
	$('#hidden_Id'+applyCount).remove();
	
}

</script>
<!-- 정보변경 모달 팝업 내용 -->
<div id='update-popup' class="needpopup">
	<a href="#"></a>
	
	<form id="projectupdate" action="/project/update" method="post" style="display: inline;">
	<div class= "form-group">
		<div class="col-md-6" style="max-width: 100%;">
			<input type="hidden" id="c_Id" name="c_Id" value="${pjt.c_Id}">
			<h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">협업공간명</h4>
			<input class="form-control" type="text" id="c_Name" name="c_Name"
				required value="${pjt.c_Name}">
		</div>
		</div>
		<div class= "form-group">
		<div class="col-md-6" style="max-width: 100%;">
		<h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">협업공간 설명</h4>
			<textarea class="form-control" rows="3" id="c_Comment"
				name="c_Comment" required>${pjt.c_Comment}</textarea>
		</div>
		</div>
		<div class= "form-group">
		<div class="col-md-6" style="max-width: 100%;">
		<h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">관리자 변경</h4>
			 <select class="form-control custom-select" name="mem_Id">
				<option value="${member.mem_Id}">${member.mem_Name}(${member.mem_Id})</option>
				<c:forEach var="itemList" items="${memberList}">
					<c:if test="${itemList.mem_Id != pjt.mem_Id}">
						<option value="${itemList.mem_Id}">${itemList.mem_Name}(${itemList.mem_Id})</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		</div>
		<div class= "form-group">
		<div class="col-md-6" style="max-width: 100%;">
			<h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">카테고리</h4>
			<select class="form-control custom-select" name="c_Category">

				<option value="${pjt.c_Category}" hidden>
				<c:choose> 
				 <c:when test = "${pjt.c_Category == 01}"> 협업업무관련</c:when>
				 <c:when test = "${pjt.c_Category == 02}"> 개인업무관련</c:when>
				 <c:when test = "${pjt.c_Category == 03}"> 학업과제관련</c:when>
				 <c:when test = "${pjt.c_Category == 04}"> 기타</c:when>
				 </c:choose>
				</option>
				<option value="01">협업업무관련</option>
				<option value="02">개인업무관련</option>
				<option value="03">학업과제관련</option>
				<option value="04">기타</option>
			</select>
		</div>
		</div>
		  <span class="col-md-6">
		<button type="submit" class="btn btn-block btn-success" style="width: 220px; float:none; display: inline;">정보변경</button>
		</span>
	</form>
		<form id="projectdelete" action="/project/delete" method="post" style="display: contents; ">
		<span class="col-md-6">
			<input type="hidden" id="c_Id" name="c_Id" value="${pjt.c_Id}">
			<button style="background-color: #dc3545; width: 220px; display: inline; float: right; border-color: #dc3545;" type="submit"
				class="btn btn-block btn-success">협업공간삭제</button>
		</span>
		</form>
		</div>
<!-- 맴버초대 모달 팝업 내용 -->
<div id='add-popup' class="needpopup">
	<div style="padding-bottom: 25px;">
		<h4 class="m-0 text-dark"
			style="font-family: Recipekorea; padding-bottom: 5px;">파트너 초대</h4>
		<span style="font-size: 0.9em; line-height: 1.0; color: #a1a1a1;">
			많은 사람을 초대하여 원활한 의사소통으로 업무를 효율적으로 처리해보세요. 회사 동료뿐만 아니라 외부 협업자도 파트너로 초대할
			수 있습니다.</span>
	</div>

	<div style="padding-bottom: 25px;">
		<h4 class="m-0 text-dark"
			style="font-family: Recipekorea; padding-bottom: 5px;">파트너 아이디</h4>
		<input style="margin-bottom: 5px;" class="form-control" type="text"
			id="item_mem_Id" name="mem_Id" required> <span id="id_check"
			style="font-size: 0.9em; line-height: 1.0; color: #a1a1a1;">
			이메일 주소를 입력하고 Enter키를 눌러 파트너들을 초대해 보세요.</span>
	</div>


	<div style="padding-bottom: 25px">
		<h4 class="m-0 text-dark"
			style="font-family: Recipekorea; padding-bottom: 5px;">초대 리스트</h4>
		<div id="applyList" class="form-control"
			style="height: 152px; width: 490px; white-space: pre-line; margin-bottom: 5px;"></div>
		<span style="font-size: 0.9em; line-height: 1.0; color: #a1a1a1;">

			초대 메세지를 보낼 파트너 아이디를 여기서 확인할 수 있습니다.</span>
	</div>
	<div>
		<span style="float: left; padding-right: 50px;">
			<form action="/news/insert" method="post" id="applyform">
				<input type="hidden" name="c_Id" value="${pjt.c_Id}">
				<button type="submit" id="insertsubmit"
					class="btn btn-block btn-success" style="width: 220px;">초대하기</button>
			</form>
		</span> <span>
			<button type="reset" class="btn btn-block btn-success"
				onclick="history.go(0);" style="width: 220px; background-color: #dc3545; border-color: #dc3545;" >취소</button>
		</span>
	</div>
</div>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header" style="height: 100px">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<div>
						<h1 class="m-0 text-dark"
							style="font-family: Recipekorea; max-width: 80%; display: contents;">${pjt.c_Name}</h1>
						<c:choose>
							<c:when test="${member.mem_Id == pjt.mem_Id}">
								<a style="font-size: 20px; margin: 3px;" href="/#"
									data-needpopup-show="#update-popup"><i
									class="nav-icon fas fa-cogs"> </i>
									</a>
							</c:when>
							<c:otherwise>
							<a style="font-size: 20px; margin: 3px;" href="/pjtmember/delete?c_Id=${pjt.c_Id}&mem_Id=${member.mem_Id}"
									onclick="return withdrawal()"><i
									class="nav-icon fas fa-door-open"> </i>
									</a>
							</c:otherwise>
						</c:choose>

					</div>
					<div>
						<p class="breadcrumb float-sm-left"
							style="position: absolute; margin-top: 10px">${pjt.c_Comment}</p>
					</div>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-right">

						<li class="breadcrumb-item"><a style="font-size: 30px;"
							href="/#" data-needpopup-show="#add-popup"> <i
								style="width: 25px; height: 25px;" class="ion ion-person-add"></i></a></li>
						<li class="breadcrumb-item active">파트너 추가</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->
<div class="col-md-9" style="max-width: 100%;">
		<div class="card">
			<div class="card-header p-2">
			
				<ol class="nav nav-pills" style="font-family: Spoqa Han Sans; font-size:15px; margin:0px; border:none; padding-inline-start: 0px;  width:400px; float:left;">
					<li class="nav-item" ><a class="nav-link"
						href="/project/main?c_Id=${pjt.c_Id}"  id="activityMenu">홈</a></li>
						
					<li class="nav-item" id = "activityMenu"><a class="nav-link" href="/project/issue/list?c_Id=${pjt.c_Id}"
						 id= "issueMenu">이슈</a></li>
					<li class="nav-item" id = "activityMenu"><a class="nav-link" href="/project/kanban?c_Id=${pjt.c_Id}"
						 id= "issueMenu">칸반</a></li>
					<li class="nav-item" id = "activityMenu"><a class="nav-link" href="/project/calendar?c_Id=${pjt.c_Id}"
						 id= "issueMenu">캘린더</a></li>
					<li class="nav-item" id = "activityMenu"><a class="nav-link active" href="/project/vote/list?c_Id=${pjt.c_Id}"
						 id= "issueMenu"><b>투표</b></a></li>
						
				</ol>
				
				 <button type="button" class="btn btn-success float-right" onclick="location.href='/project/vote/insert?c_Id=${pjt.c_Id}'"style="text-align:center; float:right; background-color:#DC3545; border-color:#DC3545; font-family: Spoqa Han Sans; font-size:13px;">
								<i class="fas fa-edit"></i> <b>투표 작성</b></button>
				
			</div>
			
						<!-- Main content -->
	<section class="content">
	
			<!-- <input type="text" id="today" name="v_Today"> -->
			
			       <!-- 리스트 부분 -->
        <div class="card-body p-0">
           
          <table class="table table-striped projects">
              <thead>
              <tr>
              <td style="font-size:13px; text-align:left"><b>상태</b></td>
              <td style="font-size:13px; text-align:left"><b>제목</b></td>
              <td style="font-size:13px; text-align:left"><b>기간</b></td>
              </tr>
              </thead>
              
              <tbody>
			<c:choose>
			<c:when test="${fn:length(voteList)!=0}">
                 <c:forEach var="voteList" items="${voteList}" >	
                  <tr>
                  
                      <td  style="width:10%; vertical-align:top"> 
                             <span style= "background-color:${voteList.vs_Color}"
							id="vs_Name" name="vs_Name" class="badge badge-success"><c:out  value="${voteList.vs_Name}" /></span>
					
						</td>
					  <td style="width:60%">	
					  <a href="/project/vote/read?c_Id=${voteList.c_Id}&v_Num=${voteList.v_Num}"> <font size="3em"><b>${voteList.v_Name}</b></font></a>
					  <br>
					  <font size="1em" color="#6c757d">${voteList.mem_Id}</font>    
					  </td>
                      
                      <td style="width:30%; vertical-align:top"> <font size="2em" color="#6c757d"><i class="far fa-calendar-alt"></i>&nbsp;&nbsp;<c:if test="${voteList.v_Start == null}">기간 미설정</c:if>
                      <c:if test="${voteList.v_Start != null}">${voteList.v_Start} - ${voteList.v_End}</c:if> 
                        </font>  </td>
                     
                   </tr>
                   
                   </c:forEach>
                 </c:when>
                 <c:otherwise>
                 <p style="text-align:center;"><small><br><br>작성된 글이 없습니다.</small></p>
                 </c:otherwise>
                 </c:choose>
              </tbody>
          </table>
        </div>
        <!-- /리스트 부분 -->
        <br><br>
        <!-- 페이징 -->
 <div class="form-group" style="margin-left:20px">

   <ul class="pagination pagination-sm m-0">
    <c:if test="${pageMaker.prev}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    <c:choose>
    <c:when test="${pageMaker.page==idx}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(idx)}" style="background-color:#DC3545; color:white">${idx}</a></li>
    </c:when>
    <c:otherwise>
    <li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
    </c:otherwise>
    </c:choose>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="page-item" id="liStyle"><a class="page-link" href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
    </c:if> 
  </ul>
  <br>
  </div>
  
 
        <!--/페이징  -->
       

	</section>
	<!-- /.content -->
					
				
				</div>
			</div>
			<!-- /.card-body -->
		
		
<!-- /.content-wrapper -->

  <script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true,
      "autoWidth": false,

    })
  });
    
    
</script>



