<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<style type="text/css">
.user-panel img {
     height: 40px;
     width: 40px;
}


@font-face {
	font-family: 'Recipekorea';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}


.needpopup {
	border-radius: 6px;
	box-shadow: 0 1px 5px 1px rgba(0, 0, 0, 1);
}

.needpopup p {
	font-size: 1.2em;
	line-height: 1.4;
	color: #343638;
	margin: 15px 0;
	margin: 0;
}

.needpopup p+p {
	margin-top: 10px;
}

#applyList span {
	background-clip: padding-box;
    border: 1px solid #17a2b8;
    padding: 2px;
    margin: 2px;
    display: inline-block;
}
#applyList a {

	margin: 2px;
}


</style>

<script type="text/javascript">


	$(document).ready(function(){
		//검색
		$("#search").click(function(){
			if($("#keyword").val()!=''){
				var url = "${contextPath}/search/wholeresult";
				url = url + "?keyword="+$('#keyword').val();
				location.href=url;
			}

		})
		
		//엔터키 검색
		$("#keyword").keydown(function(){
			if($("#keyword").val()!=''){
			if (event.keyCode == '13') {
				var url = "${contextPath}/search/wholeresult";
				url = url + "?keyword="+$('#keyword').val();
				location.href=url;
			}
			}
		})
		
		//검색시 검색어 유지
	var keyword_1 = $("#keyword_1").val();
	 $("#keyword").val(keyword_1);
	
		
		//알림개수 가져오기
		var total = 0;		
		$.ajax({
			url : '${contextPath}/notify/viewReply',
			type : 'get',
			success : function(data){
				var itemcount = data.length;
				var str='개의 댓글이 달렸습니다'
				var no=itemcount+str;
				 if(itemcount != 0){				
					var div ='<div class="dropdown-divider"></div>'
				    div +='<a href="/main" class="dropdown-item"> <i class="fas fa-list" style="display: flex;">'
					div +='<p id = "test01" style="margin: 2px; margin-left: 10px; color: lightslategray;font-size: 12px;font-weight: normal"></p>'
					div +='</i> <span class="float-right text-muted text-sm"></span></a><div class="dropdown-divider"></div>'
          
					$('#app').append(div)
				} 
				var count =parseInt(itemcount);
				total += count;
				$("#test01").html(no);				
				
			}
		})
		$.ajax({
			url : '${contextPath}/notify/viewVote',
			type : 'get',
			success : function(data){
				var itemcount = data.length;
				var str='개의 투표가 있습니다'
					 if(itemcount != 0){				
							var div ='<div class="dropdown-divider"></div>'
						    div +='<a href="/main" class="dropdown-item"> <i class="fas fa-vote-yea" style="display: flex;">'
							div +='<p id = "test66" style="margin: 2px; margin-left: 10px; color: lightslategray;font-size: 12px;font-weight: normal"></p></i>'
							div +='<span class="float-right text-muted text-sm"></span></a><div class="dropdown-divider"></div>'
							
							$('#app').append(div)
						} 
				var no=itemcount+str;
				var count =parseInt(itemcount);
				total += count;
				$("#test66").html(no);
			}
		})
		$.ajax({
			url : '${contextPath}/notify/view',
			type : 'get',
			success : function(data){
				var itemcount = data.length;
				var str='개의 이슈가 있습니다'
				var no=itemcount+str;
				 if(itemcount != 0){				
						var div ='<div class="dropdown-divider"></div>'
					    div +='<a href="/main" class="dropdown-item"> <i class="fas fa-copy" style="display: flex;">'
						div +='<p id = "test03" style="margin: 2px; margin-left: 10px; color: lightslategray;font-size: 12px;font-weight: normal"></p></i>'
						div +='<span class="float-right text-muted text-sm"></span></a><div class="dropdown-divider"></div>'
						
						$('#app').append(div)
					} 
				var count =parseInt(itemcount);
				total += count;
				$("#test03").html(no);				
				
			}
		})
		
		$.ajax({
			url : '${contextPath}/news/view',
			type : 'get',
			success : function(data){
				var itemString =[];
				if(data != 0){
					for(var i in data){
						if(data[i].ap_Yn === 'waiting'){
							itemString.push(data[i].ap_Yn)
						}
					}
				}
				var itemcount1 = itemString.length;
				var str='개의 초대가 있습니다'
					  if(itemcount1 != 0){				
							var div ='<div class="dropdown-divider"></div>'
						    div +='<a href="/main" class="dropdown-item"> <i class="fas fa-envelope" style="display: flex;" >'
							div +='<p id = "test33" style="margin: 2px; margin-left: 10px; color: lightslategray;font-size: 12px;font-weight: normal"></p></i>'
							div +='<span class="float-right text-muted text-sm"></span></a><div class="dropdown-divider"></div>'
						
							$('#app').append(div)
						}  
				var no=itemcount1+str;
				var count1 =parseInt(itemcount1);
				total += count1;
				var str='개의 알림이 있습니다'
				var last=total+str;
				$("#test33").html(no);
				$("#test00").html(total);
				$("#test22").html(last);
			}
		})
		
		//채팅연결끊기 
		disconnect()
		//북마크 리스트 가져오기 ajax 
		$.ajax({
			url : '${contextPath}/bookmark/list',
			type : 'get',
			success : function(data) {
				var itemString = "";
				var itemcount = data.length;
				if (data != 0) {
					for ( var i in data) {
				itemString += '<tr><td style="width:30%;"><font size="3em" color="#6c757d"><b>'
				itemString +=  data[i].c_Name+'</b></font></td>'
				itemString += '<td style="width:50%"><a style="color: #20c997" href="/project/issue/read?i_Num='+data[i].i_Num+'&c_Id='+data[i].c_Id+'">';
				itemString += '<font size="3em"><b>'+data[i].i_Name+'</b></font></a>'
				itemString += '<a href="#" onclick="bookdelete('+data[i].i_Num+')" style="padding: 0px; margin-top: 3; float:right;">'
				itemString += '<i class="fas fa-trash-alt"></i></a></td>'
				itemString += '<td style="width:20%;"><font size="3em" color="#6c757d"><b>'
				itemString +=  data[i].mem_Name+'</b></font></td></tr>'
					}
				$("#bookmarkcount").html(itemcount);
				}else{
				itemString += '<tr><td colspan="3" align="center"><font size="3em"><b>등록된 북마크가 없습니다.</b></font></td></tr>'  
				}
				$("#bookmarkitem").html(itemString);
			}})
		
		var getmenu = getCookie('menu');
		var menuId = document.getElementById('menustat');
		if (getmenu != null) {
			menuId.className = getmenu;
		}
		//쿠키생성
		var getmenu = getCookie('apply');
		var menuId1 = document.getElementById('activity');
		var menuId2 = document.getElementById('timeline');
		var menuId3 = document.getElementById('activityMenu');
		var menuId4 = document.getElementById('timelineMenu');
		//var applysub = applymenus.siblings();
		if (getmenu != null) {
			menuId1.className = 'tab-pane';
			menuId2.className = 'tab-pane active';
			menuId3.className = 'nav-link';
			menuId4.className = 'nav-link active';
			deleteCookie('apply');
		}
		
	});
	//북마크 버튼클릭시 다시 ajax실행
		function  bookmarkA() {
		console.log("북마크 클릭");
		$.ajax({
			url : '${contextPath}/bookmark/list',
			type : 'get',
			success : function(data) {
				var itemString = "";
				var itemcount = data.length;
				if (data != 0) {
					for ( var i in data) {
						itemString += '<tr><td style="width:30%;"><font size="3em" color="#6c757d"><b>'
							itemString +=  data[i].c_Name+'</b></font></td>'
							itemString += '<td style="width:50%"><a style="color: #20c997" href="/project/issue/read?i_Num='+data[i].i_Num+'&c_Id='+data[i].c_Id+'">';
							itemString += '<font size="3em"><b>'+data[i].i_Name+'</b></font></a>'
							itemString += '<a href="#" onclick="bookdelete('+data[i].i_Num+')" style="padding: 0px; margin-top: 3; float:right;">'
							itemString += '<i class="fas fa-trash-alt"></i></a></td>'
							itemString += '<td style="width:20%;"><font size="3em" color="#6c757d"><b>'
							itemString +=  data[i].mem_Name+'</b></font></td></tr>'
					}
				$("#bookmarkcount").html(itemcount);
				}else{
				itemString += '<tr><td colspan="3" align="center"><font size="3em"><b>등록된 북마크가 없습니다.</b></font></td></tr>'  
				}
				$("#bookmarkitem").html(itemString);
			}})
}
	
	
	
	function menuclick() {
		deleteCookie('menu');
		var stat = document.getElementById('menustat').className;
		if (stat == 'nav-item has-treeview menu-open') {
			setCookie('menu', 'nav-item has-treeview', 1);
		} else if (stat == 'nav-item has-treeview') {
			setCookie('menu', 'nav-item has-treeview menu-open', 1);
		}
	}
	function setCookie(name, value, exp) {
		var date = new Date();
		date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString()
				+ ';path=/';
	}
	//쿠키가져오기
	function getCookie(name) {
		var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
		return value ? value[2] : null;
	}
	//쿠키삭제
	var deleteCookie = function(name) {
		var date = new Date();
		document.cookie = name + "= " + "; expires=" + date.toUTCString()
				+ "; path=/";
	}
	
	function bookdelete(itemdata) {
		var i_Num = itemdata;
		$.ajax({
			url : '${contextPath}/bookmark/deletelist?i_Num='+i_Num,
			type : 'get',
			success : function(data) {
				var itemString = "";
				var bookcount = $("#bookmarkcount").html();
				if (data != 0) {
					for ( var i in data) {
						itemString += '<tr><td style="width:30%;"><font size="3em" color="#6c757d"><b>'
							itemString +=  data[i].c_Name+'</b></font></td>'
							itemString += '<td style="width:50%"><a style="color: #20c997" href="/project/issue/read?i_Num='+data[i].i_Num+'&c_Id='+data[i].c_Id+'">';
							itemString += '<font size="3em"><b>'+data[i].i_Name+'</b></font></a>'
							itemString += '<a href="#" onclick="bookdelete('+data[i].i_Num+')" style="padding: 0px; margin-top: 3; float:right;">'
							itemString += '<i class="fas fa-trash-alt"></i></a></td>'
							itemString += '<td style="width:20%;"><font size="3em" color="#6c757d"><b>'
							itemString +=  data[i].mem_Name+'</b></font></td></tr>'
					}
				$("#bookmarkcount").html(bookcount);
				}else{
				itemString += '<tr><td colspan="3" align="center"><font size="3em"><b>등록된 북마크가 없습니다.</b></font></td></tr>'  
				$("#bookmarkcount").html('');
				}
				$("#bookmarkitem").html(itemString);
			}})
	}
	
</script>

<script>

 function move(url){

    $('#content').children().remove();

    $('#content').load(url);

 }

</script>




<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<link rel="stylesheet" href="${contextPath}/resources/modal/dist/needpopup.min.css">
<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="${contextPath}/resources/modal/dist/needpopup.min.js"></script>


<title>협업툴 Collaw T - 간단하고 쉬운협업</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bbootstrap 4 -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="${contextPath}/resources/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${contextPath}/resources/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/summernote/summernote-bs4.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
</head>

<body class="sidebar-mini layout-fixed accent-teal">
	<div class="wrapper" id="start">

		<!-- Navbar -->
		 <nav class="main-header navbar navbar-expand navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="/main" class="nav-link">Home</a>
      </li>
    </ul>
<!-- 검색 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    <!-- SEARCH FORM -->
    <div class="form-inline ml-3">
      <div class="input-group input-group-sm">
        <input class="form-control form-control-navbar" type="text" placeholder="검색" id="keyword" name="keyword">
        <div class="input-group-append">
          <button class="btn btn-navbar"  id = "search" name = "search">
            <i class="fas fa-search"></i>
          </button>
        </div>
      </div>
    </div>
     <input type="hidden" value="${keyword}" id="keyword_1" name="keyword_1">
    

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">

<!-- 알림~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
      <!-- Notifications Dropdown Menu -->
      <li class="nav-item dropdown" ><a class="nav-link"
					data-toggle="dropdown" href="#"><i class="fas fa-bell"></i> <span
						class="badge badge-warning navbar-badge" id ="test00"></span>
				</a>
					 <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right" id="app" >
						<span class="dropdown-item dropdown-header" id = "test22" ></span>
      </li>
      
      
      <!-- 채팅 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
         <i class="fas fa-comments"></i>
         <span id= "totalCountNum"class="badge badge-danger navbar-badge">${totalCount}</span>
        </a>
      </li>
    </ul>
  </nav>
 <!-- 채팅 끝 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
	<!-- Main Sidebar Container -->
	<aside class="main-sidebar elevation-4 sidebar-dark-danger">
		<!-- Brand Logo -->
		<a  href="/main" class="brand-link navbar-danger"> <img
			src="${contextPath}/resources/dist/img/logocollawT.png" alt="AdminLTE Logo"
			class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light" style="font-family:Tahoma;">&nbsp;Collaw T</span>
		</a>

		<!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar user panel (optional) -->
			<div class="user-panel mt-3 pb-3 mb-3 d-flex"style="padding-bottom: 0px !important;margin-bottom: 0px !important;margin-top: 10px !important;height: 65px;">
				<span class="image" style="padding-left: .6rem;">
					<c:if test="${member.mem_File != null }">
						<div class="profile">
							<img alt="User Image" style="width:40px; height:40px" class="img-circle elevation-2"
								src="/member/getByteImage?mem_Id=${member.mem_Id}" />&nbsp;&nbsp;
						</div>
					</c:if>
					
					<c:if test="${member.mem_File == null }">
							<img src="${contextPath}/resources/dist/img/profile.jpg" style="width:40px; height:40px"
								class="img-circle elevation-2" alt="User Image">
					</c:if>
				</span>
				
					<span class="info" style="width: 170px;">
						<a href="/member/mypage"  style="padding: 0px; margin-right: 0px;">${member.mem_Name} </a>
						<a href="/logout"  style="padding: 0px; margin-top: 3; float:right;"><i class="fas fa-sign-out-alt"></i></a><br>
						<p style="padding: 0px; margin-right: 0px; color:#C2C7D1"><font size="2">(${member.mem_Id})</font></p>
					</span>
		
			</div>
			
			<!-- Sidebar Menu -->
			<nav class="mt-2">
				<ul class="nav nav-pills nav-sidebar flex-column"
					data-widget="treeview" role="menu" data-accordion="false">

					
												
					<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
					<li class="nav-item has-treeview" id="menustat" style="border-bottom: 1px solid #4f5962;"><a href="#"
						class="nav-link active" onclick="menuclick()"> <i
							class="nav-icon far fa-handshake"></i>
							<p>
								등록된 협업공간 <i class="right fas fa-angle-left"></i>
							</p>
					</a>
						<ul class="nav nav-treeview" >


							<c:forEach var="coworklist" items="${coworklist}">
								<li class="nav-item"><a
									href="/project/main?c_Id=${coworklist.c_Id}" class="nav-link">
										<i class="nav-icon fas fa-sitemap"></i>
										<p>${coworklist.c_Name}</p>
								</a></li>
							</c:forEach>
						<li class="nav-item"><a href="/#" data-needpopup-show="#createpjt-popup"
								class="nav-link" > <i class="nav-icon fas fa-folder-plus"></i>
									<p>협업공간 생성</p>
							</a></li>
						</ul></li>
						<li class="nav-item"><a href="/main" class="nav-link">
									<i class="nav-icon fas fa-user-secret"></i>
									<p>내 공간</p>
							</a></li>

					<li class="nav-item"><a href="/#" data-needpopup-show="#bookmark-popup"
						class="nav-link" onclick="bookmarkA()"> <i class="nav-icon fas fa-bookmark"></i>
							<p>
								북마크 <span class="badge badge-info right" id="bookmarkcount" style="background-color: #FFC108"></span>
								<!--  <span class="right badge badge-danger">New</span>-->
							</p>
					</a></li>
					
					
					
					<li class="nav-item" ><a href="/calendar/main"
						class="nav-link"> <i class="nav-icon fas fa-calendar-check"></i>
							<p>
								전체 캘린더 <span class="badge badge-info right"></span>
								<!--  <span class="right badge badge-danger">New</span>-->
							</p>
					</a></li>
					
					<li class="nav-item" ><a href="/personal/search/myFile"
						class="nav-link"> <i class="nav-icon fas fa-archive"></i>
							<p>
								전체 파일함 <span class="badge badge-info right"></span>
								<!--  <span class="right badge badge-danger">New</span>-->
							</p>
					</a></li>
					
					
					
						
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar -->
	</aside>

<!-- 공간생성 모달 팝업 내용 -->
<div id='createpjt-popup' class="needpopup">

<form action="/project/insert" method="post" id="createpjtform">
              <div class="form-group">
              <div class="col-md-6" style="max-width: 100%;">
                <h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">협업공간명</h4>
                <input type="text" name="c_Name" id="create_Name" class="form-control">
              </div>
              </div>
              <div class="form-group">
              <div class="col-md-6" style="max-width: 100%;">
                <h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">협업공간 설명</h4>
                <textarea name="c_Comment" id="create_Comment" class="form-control" rows="4"></textarea>
              </div>
              </div>
              <div class="form-group">
              <div class="col-md-6" style="max-width: 100%;">
                <h4 class="m-0 text-dark" style="font-family: Recipekorea; padding-bottom: 5px;">카테고리</h4>
               <select class="form-control custom-select" name= "c_Category" id="create_Category">
                  <option value="01" hidden="" >선택해주세요</option>
                  <option value="01">협업관련 업무관리</option>
                  <option value="02">개인 업무관리</option>
                  <option value="03">학업관련 과제관리</option>
                  <option value="04">기타</option>
                </select>
              </div>
              </div>
              <input type="hidden"  name = mem_Id value= ${member.mem_Id }>
              <div class="col-md-6" style="max-width: 100%;">
          <input type="submit" value="생성" class="btn btn-success" id="projectInsert"  style="width: 220px; float:none;">
          <input type="reset" class="btn btn-block btn-success" onclick="history.go(0);" value = "취소" style="background-color: #dc3545; 
          float: right;background-color: #dc3545;width: 220px;border-color: #dc3545; ">
          </div>
      </form>
</div>


<div id='bookmark-popup' class="needpopup">
<div class="col-md-6" style="max-width: 100%;">
<table class="table table-striped projects" style="white-space: nowrap; overflow: hidden;"><div style="margin-bottom: 10px;">
<h4 class="m-0 text-dark" style="font-family: Recipekorea; max-width: 80%; display: contents;">북마크</h4></div>
		<tr style="background-color: #dc3545; color: white;">
			<td style="width:30%; vertical-align:top"><font size="3em"><b>협업공간명</b></font></td>
			<td style="width:50%"><font size="3em"><b>글제목</b></font></a>
			<td style="width:20%; vertical-align:top"><font size="3em"><b>작성자</b></font></td></tr>
 		<tbody id = 'bookmarkitem'>
	
                 
 </tbody>
</table>
        </div>
</div>