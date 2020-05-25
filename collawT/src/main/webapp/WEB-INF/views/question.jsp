<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 3 | Registration Page</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Font Awesome -->
<link rel="stylesheet" href="${contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet" href="${contextPath}/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${contextPath}/resources/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function checkAll() {
    if (!checkQ_Subject(form.q_Subject.value)) {
        return false;
    } else if (!checkQ_Content(form.q_Content.value)) {
        return false;
    } else if (!checkQ_Email(form.q_Email.value)) {
        return false;
    } 
    return true;
}
//공백확인 함수
function checkExistData(value, dataName) {
    if (value == "") {
        alert(dataName + " 입력해주세요!");
        return false;
    }
    return true;
}


function checkQ_Subject(q_Subject) {
    //제목이 입력되었는지 확인하기
    if (!checkExistData(q_Subject, "문의 제목을"))
        return false;

   
    return true; //확인이 완료되었을 때
}


function checkQ_Content(q_Content) {
    //내용이 입력되었는지 확인하기
    if (!checkExistData(q_Content, "문의 내용을")){
        return false;
    }else if (q_Content.length < 8||q_Content.length > 300){
    	alert("8 ~ 300자 내로 작성하세요. ");
    
    	return false;
    }

    return true; //확인이 완료되었을 때
}

function checkQ_Email(q_Email) {
    
    //이메일이 입력되었는지 확인하기
    if (!checkExistData(q_Email, "이메일을"))
        return false;
	
    
    
    return true; //확인이 완료되었을 때
    
}    

</script>

</head>
<body class="hold-transition register-page" style="background-color: white;">
	<div class="register-box">
		<div class="register-logo">
			<a href="/"><b>Collaw </b>T</a>
		</div>

		<div class="card">
			<div class="card-body register-card-body">
				

				<form action="/question/sendEmail" name="form" method="post" onsubmit="return checkAll()">

					<div class="card card-primary">
						<div class="card-header" style="background-color: #20c997;border-color:#20c997">
							<h3 class="card-title">문의하기</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
								
								</button>
							</div>
						</div>
						<div class="card-body">
							<div class="form-group">
								<label for="inputName">문의 제목</label> <input type="text" id="inputName" name="q_Subject" class="form-control">
							</div>
							<div class="form-group">
								<label for="inputStatus">문의 카테고리</label> 
								<select class="form-control custom-select" id="q_Kind" name="q_Kind">
									<option value="버그 관련">버그 관련</option>
									<option value="일반 문의">일반 문의</option>
									<option value="아이디어 제의">아이디어 제의</option>
									<option value="기타 문의">기타 문의</option>
								</select>
							</div>
							<div class="form-group">
								<label for="inputDescription">문의 내용</label>
								<textarea id="inputDescription" class="form-control" name="q_Content" rows="4"></textarea>
							</div>
							
							<p class="login-box-msg">회원님의 이메일을 입력해주세요.</p>
							<div class="input-group mb-3">
								<input type="email" class="form-control" name="q_Email" placeholder="Email">
								<div class="input-group-append">
									<div class="input-group-text">
										<span class="fas fa-envelope"></span>
									</div>
								</div>
							</div>
							
							
						
						</div>
						<!-- /.card-body -->
					</div>
					<div class="row">
						<div class="col-6">
							<a href="javascript:history.back()" class="btn btn-primary btn-block"style="background-color: #20c997;border-color:#20c997">뒤로가기</a>
						</div>
						<div class="col-6">
							<button type="submit" class="btn btn-primary btn-block"style="background-color: #20c997;border-color:#20c997">제출</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.form-box -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->

	<!-- jQuery -->
	<script src="${contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="${contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${contextPath}/resources/dist/js/adminlte.min.js"></script>
</body>
</html>
