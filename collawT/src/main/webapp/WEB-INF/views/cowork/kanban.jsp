<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
.accent-teal a:not(.dropdown-item):not(.btn-app):not(.nav-link):not(.brand-link) {
    color: #343a40;
}
.nav-pills .nav-link.active {
	background-color: #dc3545;
}

.dropitem{
    cursor: grab;
    }

.kanbandiv{
    min-height: 380px;
    padding: 0px;

}    
    
#kanbandiv1 div{
box-shadow: 0 0 1px #ffc107;
margin: 10px;
}
#kanbandiv2 div{
box-shadow: 0 0 1px #007bff;
margin: 10px;
}
#kanbandiv3 div{
box-shadow: 0 0 1px #e83e8c;
margin: 10px;
}
#kanbandiv4 div{
box-shadow: 0 0 1px #28a745;
margin: 10px;
}

</style>

<script type="text/javascript">
function withdrawal() {
	var a = confirm("정말 협업공간을 나가시겠습니까?")
	if (a == true) {
		alert("협업공간을 나왔습니다.")
	}else {
		return false;
	}
	
}

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

	
	    function allowDrop(ev) {
	        ev.preventDefault();
	    }
	 
	    //드래그 했을시, 드래그 당하고 있는 해당 객체의 id를 가져와서 dataTransfer.setData로 담아줍니다. 
	    //해당 객체의 별명을 text로 지정해주게 됩니다. 그러면 다음 함수인 drop()에서 별명을 통해 데이터를 전달받게 됩니다.
	    function drag(ev) {
	        ev.dataTransfer.setData("text", ev.target.id);
	    }
	 
	    //위에서 text로 별명을 주었던 것을 getData()해서 가져옵니다. 그 데이터르 drop되는 위치에 append 시켜줌으로써 드래그 앤 드롭은 끝 입니다.
	    function drop(ev) {
	        ev.preventDefault();
	        var data = ev.dataTransfer.getData("text");
	        var updateNum;
	       if (ev.target.className == 'dropitem') {
	    	   //div 클래스가 dropitem 일경우 부모div에게 어팬드
			ev.target.parentNode.appendChild(document.getElementById(data));
	        updateNum = ev.target.parentNode.id;
			}else{
	        ev.target.appendChild(document.getElementById(data));
	        updateNum = ev.target.id;
	        }
	       	console.log(updateNum);
	       	console.log("data="+data)
	       	
	    	$.ajax({
				url : '${contextPath}/project/kanbanUpdate?i_Num='+data+'&ig_Num='+updateNum,
				type : 'get',
				success : function(data) {
					console.log("1 = 완료 / 0 = 실패 : "+data)}});
	       
	    }
	    
</script>
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
					<li class="nav-item" ><a
						href="/project/main?c_Id=${pjt.c_Id}"  id="activityMenu" class="nav-link" ><b>홈</b></a></li>
						
					<li class="nav-item" id = "activityMenu"><a class="nav-link " href="/project/issue/list?c_Id=${pjt.c_Id}"
						 id= "issueMenu">이슈</a></li>
					<li class="nav-item" id = "activityMenu"><a class="nav-link active" href="/project/kanban?c_Id=${pjt.c_Id}"
						 id= "issueMenu">칸반</a></li>
					<li class="nav-item" id = "activityMenu"><a class="nav-link" href="/project/calendar?c_Id=${pjt.c_Id}"
						 id= "issueMenu">캘린더</a></li>
					<li class="nav-item" id = "activityMenu"><a class="nav-link" href="/project/vote/list?c_Id=${pjt.c_Id}"
						 id= "issueMenu">투표</a></li>
						
				</ol>
				
				
			</div>
	<!-- Main content -->
		<div class="row">
		
		<!-- 첫번째  -->
		<div class="col-md-3">
		<div class="card" style="margin: 10px; box-shadow: 0 0 1px #ffc107, 0 1px 3px #ffc107;">
		<div class="card-header"><b>발의됨</b></div>
		<div class="card-body kanbandiv"  id="kanbandiv1" ondrop="drop(event)" ondragover="allowDrop(event)">
		<c:forEach var="kanbanitem1" items="${kanbanlist}">
			<c:if test="${kanbanitem1.ig_Num == 1 }">
			<div id="${kanbanitem1.i_Num}" class="dropitem" draggable="true" ondragstart="drag(event)">${kanbanitem1.i_Name}</div>
			</c:if>
		</c:forEach>		
		</div> 
		</div>
		</div>
		<!-- 첫번째 끝  -->
		<!-- 두번째  -->
		<div class="col-md-3">
		<div class="card" style="margin: 10px; box-shadow: 0 0 1px #007bff, 0 1px 3px #007bff;">
		<div class="card-header"><b>진행 중</b></div>
		<div class="card-body kanbandiv" id="kanbandiv2" ondrop="drop(event)" ondragover="allowDrop(event)">
		<c:forEach var="kanbanitem1" items="${kanbanlist}">
			<c:if test="${kanbanitem1.ig_Num == 2 }">
			<div id="${kanbanitem1.i_Num}" class="dropitem" draggable="true" ondragstart="drag(event)">${kanbanitem1.i_Name}</div>
			</c:if>
		</c:forEach>		
		</div>
		</div>
		</div>
		<!-- 두번째 끝  -->
		<!-- 세번째  -->
		<div class="col-md-3">
		<div class="card" style="margin: 10px;  box-shadow: 0 0 1px #e83e8c, 0 1px 3px #e83e8c;">
		<div class="card-header"><b>일시중지</b></div>
		<div class="card-body kanbandiv" id="kanbandiv3" ondrop="drop(event)" ondragover="allowDrop(event)">
		<c:forEach var="kanbanitem1" items="${kanbanlist}">
			<c:if test="${kanbanitem1.ig_Num == 3 }">
			<div id="${kanbanitem1.i_Num}" class="dropitem" draggable="true" ondragstart="drag(event)">${kanbanitem1.i_Name}</div>
			</c:if>
		</c:forEach>		
		</div>
		</div>
		</div>
		<!-- 세번째 끝  -->
		<!-- 네번째  -->
		<div class="col-md-3" >
		<div class="card" style="margin: 10px; box-shadow: 0 0 1px #28a745, 0 1px 3px #28a745;">
		<div class="card-header"><b>완료</b></div>
		<div class="card-body kanbandiv" id="kanbandiv4" ondrop="drop(event)" ondragover="allowDrop(event)">
		<c:forEach var="kanbanitem1" items="${kanbanlist}">
			<c:if test="${kanbanitem1.ig_Num == 4 }">
			<div id="${kanbanitem1.i_Num}" class="dropitem" draggable="true" ondragstart="drag(event)">${kanbanitem1.i_Name}</div>
			</c:if>
		</c:forEach>		
		</div>
		</div>
		</div>
		 <!-- 네번째 끝  -->
		</div>
		


    <!-- /.content -->
  </div>
  </div>
  </div>
  
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






