<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>회원탈퇴</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../resources/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bbootstrap 4 -->
<link rel="stylesheet"
	href="../resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="../resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="../resources/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../resources/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="../resources/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet"
	href="../resources/plugins/summernote/summernote-bs4.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function fn_deleteMem(){
		if(!confirm("정말탈퇴 하시겠습니까?")){
			return false;
		}
	};
</script>
</head>
<body class="login-page">
	<div class="login-box">
		<div class="card-body login-card-body">
			<div class="login-logo">
				<a href="javascript:window.history.back();"> <b>Collaw T</b></a>
			</div>
			<div class="card">
				<div class="card-body login-card-body">
					<b class="login-box-msg" ><center>회원탈퇴</center></b>
						<p class="login-box-msg">
					<form id="outMemberForm" action="/member/delete_Member" method="post">
					<h1>${member.mem_Id}</h1>
						<input type="hidden" name="mem_Id" value="${member.mem_Id}">
						<p class="login-box-msg">
							<label>기존비밀번호</label> <input class="form-control" id="pwd"
								name="pwd" type="password" required>
						</p>
							<button type="submit" id="outBtn"
								class="btn btn-block btn-success" onclick="fn_deleteMem()">회원탈퇴</button>
							<button type="button" class="btn btn-block btn-success"
								onclick="history.back(-1);">뒤로가기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>