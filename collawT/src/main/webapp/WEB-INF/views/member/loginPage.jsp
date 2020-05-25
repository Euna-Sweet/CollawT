<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- 컨트롤러에서 로그인 실패할때 가져오는 값. -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>CollawT Login</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="${contextPath}/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../resources/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<script type="text/javascript">
	var result = '${msg}';
	var mem_Name = '${mem_Name}';
	if (result == 'success') {
		alert(mem_Name + " 님 콜라우티 회원가입을 축하 합니다. 이메일 인증 후 사용 가능합니다!!!");
<%
		//msg 세션 지우기.
		session.removeAttribute("msg");
%>
	}
</script>




<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		/*  */
		var userInputId = getCookie("userInputId");//저장된 쿠기값 가져오기
		$("input[name='mem_Id']").val(userInputId);

		if ($("input[name='mem_Id']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩
			// 아이디 저장하기 체크되어있을 시,
			$("#remember").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		}

		$("#remember").change(function() { // 체크박스에 변화가 발생시
			console.log("아이디 기억 체크");
			if ($("#remember").is(":checked")) { // ID 저장하기 체크했을 때,
				var userInputId = $("input[name='mem_Id']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			} else { // ID 저장하기 체크 해제 시,
				deleteCookie("userInputId");
			}
		});

		// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		$("input[name='mem_Id']").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
			if ($("#remember").is(":checked")) { // ID 저장하기를 체크한 상태라면,
				var userInputId = $("input[name='mem_Id']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			}
		});
	});

	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>

</head>
<body class="hold-transition login-page" style="background: white">




	<div class="login-box">
		<div class="login-logo">
			<a href="/"><b>Collaw</b>T</a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Login</p>

				<form action="/member/login" method="post">
					<div class="input-group mb-3">
						<input type="email" name="mem_Id" class="form-control"
							placeholder="아이디 이메일 형식">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" name="mem_Pwd" class="form-control"
							placeholder="비밀번호">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" id="remember"> <label
									for="remember"> 아이디 기억 </label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-4">
							<button type="submit" class="btn btn-primary btn-block" style="white-space: nowrap; overflow: hidden; text-align: center">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<div class="social-auth-links text-center mb-3">
					<p></p>
					<a href="${naverLoginUrl}" class="btn btn-block btn-danger"
						style="background-color: #29c23b; border-color: #29c23b;"> <i
						class="fab fa-neos mr-2"></i>네이버 로그인
					</a>
					<button type="button" id="loginBtn"
						class="btn btn-block btn-danger">
						<i class="fab fa-google-plus mr-2"></i>구글 로그인
					</button> 
				</div>
				<!-- /.social-auth-links -->

				<p class="mb-1">
					<a href="/member/forgotPwd">비밀번호를 잊어버리셨나요?</a>
				</p>
				<p class="mb-0">
					<a href="/member/signup" class="text-center">회원가입</a>
				</p>
				<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
				<script>
					$("#loginBtn")
							.click(
									function() {
										location.href = "https://accounts.google.com/o/oauth2/auth?client_id="
												+ "332997436138-3g0cj5k952gddaro03grkth547udnh41.apps.googleusercontent.com"
												+ "&redirect_uri="
												+ "http://collawt.site/member/googleLogin"
												+ "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email&approval_prompt=force&access_type=offline";
									});
				</script>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="${contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="${contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${contextPath}/resources/dist/js/adminlte.min.js"></script>

</body>
</html>
