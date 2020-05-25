<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Collaw T 회원가입</title>
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
      if (!checkUserId(form.mem_Id.value)) {
          return false;
      } else if (!checkPassword(form.mem_Id.value, form.mem_Pwd.value, form.mem_Pwd1.value)) {
          return false;
      } else if (!checkName(form.mem_Name.value)) {
          return false;
      } else if (!form.terms.checked){
    	  alert("회원가입 동의를 해주세요.");
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

  
  function checkUserId(id) {
      //Id가 입력되었는지 확인하기
      if (!checkExistData(id, "아이디를"))
          return false;

      var idRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
      if (!idRegExp.test(id)) {
          alert("아이디는 이메일 형식으로 옳바르지 않습니다.");
          form.mem_Id.value = "";
          form.mem_Id.focus();
          return false;
      }
      return true; //확인이 완료되었을 때
  }

  
  function checkName(name) {
      //Id가 입력되었는지 확인하기
      if (!checkExistData(name, "이름 또는 닉네임"))
          return false;

      return true; //확인이 완료되었을 때
  }

  function checkPassword(id, password1, password2) {
      //비밀번호가 입력되었는지 확인하기
      if (!checkExistData(password1, "비밀번호를"))
          return false;
      //비밀번호 확인이 입력되었는지 확인하기
      if (!checkExistData(password2, "비밀번호 확인을"))
          return false;
	
      
      var password1RegExp = /^[a-zA-z0-9]{6,12}$/;
      if (!password1RegExp.test(password1)) {
          alert("비밀번호는 영문 소문자와 숫자 6~12자리로 입력해야합니다!");
          form.mem_Pwd.value = "";
          form.mem_Pwd.focus();
          return false;
      } 
      //비밀번호와 비밀번호 확인이 맞지 않다면..
      if (password1 != password2) {
          alert("두 비밀번호가 맞지 않습니다.");
          form.mem_Pwd.value = "";
          form.mem_Pwd1.value = "";
          form.mem_Pwd1.focus();
          return false;
      }
  		var num = password1.search(/[0-9]/g);
		var eng = password1.search(/[a-z]/ig);
      if(num < 0 || eng < 0){
    	  alert("비밀번호에 영문,숫자, 특수문자를 혼합하여 입력해주세요.");
			return false;
      }

      //아이디와 비밀번호가 같을 때..
      if (id == password1) {
          alert("아이디와 비밀번호는 같을 수 없습니다!");
          form.mem_Pwd.value = "";
          form.mem_Pwd1.value = "";
          form.mem_Pwd1.focus();
          return false;
      }
      return true; //확인이 완료되었을 때
  }    
  
  
  
  $(function(){
	  
		 /* $("#joinForm").submit(function(){
			//console.log("checked: "+$("agreeTerms").is(":checked"));
			var mem_Pwd0 = $("#mem_Pwd0").val();
			var mem_Pwd1 = $("#mem_Pwd1").val();
			var num = mem_Pwd0.search(/[0-9]/g);
			var eng = mem_Pwd0.search(/[a-z]/ig);
			
			if ($("#agreeTerms").is(":checked")==false){
				alert("회원가입 동의를 해주세요.");	
				return false;
			} else if(mem_Pwd0 !== mem_Pwd1){
				alert("비밀번호가 다릅니다.");
				$("#mem_Pwd0").val("").focus();
				$("#mem_Pwd1").val("");
				return false;
			}else if ($("#mem_Id").val()==""){
				alert("아이디를 입력해주세요.");
				return false;
			}else if ($("#mem_Name".val()=="")){
				alert("이름을 입력해주세요.");
				return false;
			}else if (mem_Pwd0.length < 8 || mem_Pwd0.length > 12 ) {
				alert("비밀번호는 8자리 ~ 12자리 이내로 입력해주세요");
				$("#mem_Pwd0").val("").focus();
				return false; 
			} else if (num < 0 || eng < 0){
				alert("비밀번호에 영문,숫자, 특수문자를 혼합하여 입력해주세요.");
				return false;
			} 
		});   */
	// 아이디 유효성 검사(1 = 중복 / 0 != 중복)
		$("#mem_Id").blur(function() {
			var idJ = /^[a-z0-9]{4,12}$/;
			var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z]?)*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			var _kor = /[ㄱ-ㅎ가-힣]/g;
			var mem_Id = $("#mem_Id").val();
			console.log("블러");
			$.ajax({
				url : '${contextPath}/member/check_id?mem_Id='+ mem_Id,
				type : 'get',
				success : function(data) {

					if (data == 1) {
					console.log("1 = 중복o / 0 = 중복x : "+ data);							
					// 1 : 아이디가 중복되는 문구
					$("#id_check").text("사용중인 아이디입니다.");
					$("#id_check").css("color", "red");
					$("#joinBtn").attr("disabled", true);
				} else {
					// 0 : 아이디 사용가능 문구
					$("#id_check").text("사용가능한 아이디입니다.");
					$("#id_check").css("color", "green");
					$("#joinBtn").attr("disabled", false);
					if(mem_Id == ""){
						$('#id_check').text('이메일을 입력해주세요.');
						$('#id_check').css('color', 'red');
						$("#joinBtn").attr("disabled", false);	
					}
					
					
					//아이디 유효성 검사
					else if(idJ.test(mem_Id) ||_kor.test(mem_Id)){
						console.log("유효성체크검사");
						if(mem_Id.length == 0){
						$('#id_check').text('이메일을 입력해주세요.');
						$('#id_check').css('color', 'red');
						$("#joinBtn").attr("disabled", false);				
						
					} else {
						console.log("잘못된아이디");
						$('#id_check').text("잘못된 아이디 형식입니다. 이메일 주소를 입력해 주세요 :)");
						$('#id_check').css('color', 'red');
						$("#joinBtn").attr("disabled", true);
							}
							}
						}/*  else if(mem_Id == ""){
							$('#id_check').text('아이디를 입력해주세요 :)');
							$('#id_check').css('color', 'red');
							$("#joinBtn").attr("disabled", false);				
							
						}  */
					}, error : function() {
							console.log("실패");
					}
				});
			});
	});
</script>
</head>
<body class="register-page" style="background:white">
<div class="register-box">
  <div class="register-logo">
    <a href="/"><b>Collaw</b>T</a>
  </div>

  <div class="card">
    <div class="card-body register-card-body">
      <p class="login-box-msg">회원가입</p>

      <form action="/member/join" name="form" method="post" id="joinForm"onsubmit="return checkAll()">
        <div class="input-group mb-3">
          <input type="text" name="mem_Name" class="form-control" placeholder="콜라우티에서 사용할 이름 또는 닉네임">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>
        </div>
        
        <div class="input-group mb-3">
          <input type="email" name="mem_Id" id="mem_Id" class="form-control" placeholder="아이디는 이메일 형식입니다">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
             </div>
          </div>
        </div>
        <!-- <input type="button" id="checkBtn" onClick="" value="중복체크하기"> -->
        <!-- 아이디 중복값  -->
        <div class="input-group mb-3">
          <input type="password" id="mem_Pwd0" name="mem_Pwd" class="form-control" placeholder="비밀번호">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" id="mem_Pwd1" class="form-control" name="mem_Pwd1" placeholder="비밀번호 확인">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div> 
        </div>
        <div class="nput-group mb-3" id="id_check"style="font-size: small; float:top;"></div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="agreeTerms" name="terms" >
              <label for="agreeTerms">
              회원가입 <a href="#">동의</a>
              <!-- <script type="text/javascript">
              $(document).ready(function(){
            	    $("#agreeTerms").change(function(){
            	        if($("#agreeTerms").is(":checked")){
            	            alert("회원가입에 동의해주셔서 감사합니다.");
            	        }else{
            	            alert("회원가입에 동의하시지 않으셔서 회원가입이 불가능 합니다. 회원가입 동의 체크를 해주세요.");
            	        }
            	    });
            	});
              
              </script> -->
              </label>
            </div>
          </div>
          <!-- /.col -->
        <div class="col-4">
            <button type="submit" id="joinBtn" class="btn btn-primary btn-block" style="float:right" >회원가입</button>
          </div>
        </div>
      </form>

      <div class="social-auth-links text-center">
        <a href="${naverLoginUrl}" class="btn btn-block btn-danger" style="background-color: #29c23b;  border-color:#29c23b;" > <i class="fab fa-neos mr-2" ></i>네이버 로그인</a>
					<button type="button" id="loginBtn" class="btn btn-block btn-danger"><i class="fab fa-google-plus mr-2"></i>구글 로그인</button>
					<!-- 구글 로그인 -->
					<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
					<script>
						$("#loginBtn").click(function(){
											location.href = "https://accounts.google.com/o/oauth2/auth?client_id="
													+ "332997436138-3g0cj5k952gddaro03grkth547udnh41.apps.googleusercontent.com"
													+ "&redirect_uri="
													+ "http://collawt.site/member/googleLogin"
													+ "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email&approval_prompt=force&access_type=offline";
										});
					</script>
      </div>

      <a href="/member/loginPage" class="text-center">이미 회원가입을 하셨나요?</a>
    </div>
    
    <!-- /.form-box -->
  </div><!-- /.card -->
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