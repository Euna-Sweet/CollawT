<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<link rel="stylesheet" href="${contextPath}/resources/modal/dist/needpopup.min.css">
<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="${contextPath}/resources/modal/dist/needpopup.min.js"></script>


<title>협업툴 Collaw T - 관리자 페이지</title>
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
        <a href="/manager/main" class="nav-link">Home</a>
      </li>
    </ul>
    </nav>
	<!-- Main Sidebar Container -->
	<aside class="main-sidebar elevation-4 sidebar-dark-info">
		<!-- Brand Logo -->
		<a  href="/manager/main" class="brand-link navbar-info"> <img
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
						<a href="#"  style="padding: 0px; margin-right: 0px;">${member.mem_Name} </a>
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
                    <li class="nav-item"><a href="/manager/main" 
						class="nav-link" > <i class="nav-icon fas fa-users-cog"></i>
							<p>관리자 홈</p>
					</a></li>
               		<li class="nav-item"><a href="/manager/question/main" 
						class="nav-link" > <i class="nav-icon fas fa-question-circle"></i>
							<p>문의 및 답변</p>
					</a></li>
					
               		<li class="nav-item has-treeview" id="menustat" style="border-bottom: 1px solid #4f5962;"><a href="#"
						class="nav-link active"> <i
							class="nav-icon fas fa-database"></i>
							<p>
								DB 관리 <i class="right fas fa-angle-left"></i>
							</p>
					</a>
						<ul class="nav nav-treeview" >
						<li class="nav-item"><a href="/manager/member/main" class="nav-link">
									<i class="nav-icon fas fa-user"></i>
									<p>회원 관리</p>
							</a></li>
						<li class="nav-item"><a href="/manager/project/main" class="nav-link">
									<i class="nav-icon fas fa-handshake"></i>
									<p>공간 관리</p>
							</a></li>

					<li class="nav-item"><a href="/manager/issue/main" 
						class="nav-link" > <i class="nav-icon fas fa-edit"></i>
							<p>이슈 관리</p>
					</a></li>
					<li class="nav-item"><a href="/manager/vote/main" 
						class="nav-link" > <i class="nav-icon fas fa-vote-yea"></i>
							<p>투표 관리</p>
					</a></li>
					<li class="nav-item"><a href="/manager/personal/main" 
						class="nav-link" > <i class="nav-icon fas fa-user-lock"></i>
							<p>개인 이슈 관리</p>
					</a></li>
					
					<li class="nav-item"><a href="/manager/chat/main" 
						class="nav-link" > <i class="nav-icon fas fa-comments"></i>
							<p>채팅 관리</p>
					</a></li>
					

					</ul>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar -->
	</aside>



