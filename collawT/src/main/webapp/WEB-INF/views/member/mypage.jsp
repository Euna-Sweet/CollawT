<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#holder {
	overflow: hidden;
	height: auto;
}

.filebox label {
	display: inline-block;
	padding: .375rem .75rem;
	color: #fff;
	line-height: normal;
	vertical-align: middle;
	background-color: #28a745;
	cursor: pointer;
	border: 1px solid #4cae4c;
	border-radius: .25em;
	-webkit-transition: background-color 0.2s;
	transition: background-color 0.2s;
}

.filebox label:hover {
	background-color: #6ed36e;
}

.filebox label:active {
	background-color: #367c36;
}

.filebox input[type="file"] {
	position: absolute;
	padding: 0;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.user-img {
	margin: 0 auto;
	width: 100px;
	height: 95px;
}
</style>
<!-- 
<script>
if ('${msg}' == 'success') {
	alert(" 회원정보 변경 되었습니다.");
	console.log("after:" + '${msg}');
	}else if(result != null){
	console.log("dd");
	} 

</script> -->

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	/* 	if(${msg != null}){
	 alert('${msg}');
	 };  */
	 
	 
	 function checkAll() {
	      if (!checkUserId(form.mem_Id.value)) {
	          return false;
	      } else if (!checkPassword(form.mem_Id.value, form.mem_Pwd.value, form.mem_Pwd1.value)) {
	          return false;
	      } else if (!checkName(form.mem_Name.value)) {
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


	  function checkPassword(id, password1, password2) {
	      //비밀번호가 입력되었는지 확인하기
	      if (!checkExistData(password1, "비밀번호를"))
	          return false;
	      //비밀번호 확인이 입력되었는지 확인하기
	      if (!checkExistData(password2, "비밀번호 확인을"))
	          return false;
		
	      
	      var password1RegExp = /^[a-zA-z0-9]{10,12}$/;
	      if (!password1RegExp.test(password1)) {
	          alert("비밀번호는 영문 대소문자와 숫자 10~12자리로 입력해야합니다!");
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
	  
	 
	$(function() {


		//비밀번호같지 확인하는 자바스크립트 (서브밋할때 작동됨)
		/* if ($("#pwForm").submit(function() {
			console.log("11");
			if ($("#pw").val() !== $("#pw2").val()) {
				alert("변경하실 비밀번호가 일치하지 않습니다.");
				$("#pw").val("").focus();
				$("#pw2").val("");
				return false;
			} else if ($("#pw").val().length < 8) {
				alert("비밀번호는 8자 이상으로 설정해야 합니다.");
				$("#pw").val("").focus();
				return false;
			} else {
				alert("비밀번호가 수정되었습니다.");
			}
		}))
			; */

		//파일 비어잇는거 못들어가게함 서브밋 탈때 작동됨
		if ($("#fileupload").submit(function() {
			console.log("fileUpload");
			var fileCheck = $("#imageFile").val();
			if (!fileCheck) {
				alert("프로필 사진을 첨부해 주세요");
				return false;
			} else {
				alert("프로필사진이 변경 되었습니다.");
			}
		}))
			;

	});

	function fn_deleteMem() {
		if (!confirm("정말탈퇴 하시겠습니까?")) {
			return false;
		} else {
			alert(" 탈퇴 되었습니다. 그동안 콜라우티를 이용해주셔서 감사합니다.");
		}
	};
</script>
<!-- Site wrapper -->
<div class="wrapper">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>개인정보 수정</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="/main">Home</a></li>
							<li class="breadcrumb-item active">개인정보 수정</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-6">
					<div class="card card-secondary">
						<div class="card-header">
							<h3 class="card-title">이름</h3>
							<!-- 이름바꾸는 폼  -->
							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse" data-toggle="tooltip"
									title="Collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="card-body">
							<div class="form-group">
								<form class="form-horizontal" action="/member/update_mypage"
									method="post">
									<label for="inputEstimatedBudget">ID</label> <input
										class="form-control" type="email" id="mem_Id" name="mem_Id"
										readonly value="${member.mem_Id}">
									<div class="form-group">

										<label for="inputSpentBudget">이름</label> <input
											class="form-control" type="text" id="mem_Name"
											name="mem_Name" value="${member.mem_Name}" required>
									</div>
									
									<div class="row">
										<div class="col-12">
											<input type="submit" value="이름변경"
												class="btn btn-success float-right">
											<!--이름바꾸는 폼 끝  -->
										</div>
									</div>
								</form>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
				</div>
				<!--프로필사진 변경 -->
				<div class="col-md-6">
					<div class="card card-secondary">
						<div class="card-header">
							<h3 class="card-title">프로필사진</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse" data-toggle="tooltip"
									title="Collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>

						</div>
						<div class="card-body" style="padding-top: 0px;padding-bottom: 0px;">
							<div id="left" style="padding: 10px;margin-right: 10px;height: 194px;">
								<div class="card card-primary card-outline"
									style="border-top: white">
									<div class="card-body box-profile"
										style="padding-top: 5px; padding-bottom: 0px;">
										<!-- 변경된이미지 -->
										<div id="holder" class="user-img img-fluid img-circle">
											<!-- 아이디 있으면.. -->
											<c:if test="${member.mem_File != null }">
												<img src="/member/getByteImage?mem_Id=${member.mem_Id}"
													class="user-img img-fluid img-circle" alt="User Image" />
											</c:if>
											<c:if test="${member.mem_File == null }">
												<img src="../resources/dist/img/profile.jpg"
													class="user-img img-fluid img-circle" alt="User Image">
											</c:if>
										</div>
										<h3 class="profile-username text-center">${member.mem_Name}</h3>
										<p class="text-muted text-center">${member.mem_Id}</p>
									</div>
									<!-- /.card-body -->
								</div>
							</div>
							<form action="/member/saveImage" id="fileupload"
								enctype="multipart/form-data" method="post"
								class="form-horizontal">
								<input type="hidden" name="mem_Id" value="${member.mem_Id}">
								<div class="row">
									<div class="col-12" style="padding: 5px;height: 48px;">
										<div class="filebox">
											<label for="imageFile" style="font-weight: 400; height: 38px"
												class="btn btn-success float-right">프로필 사진 선택</label> <input
												type="file" name="mem_File" id="imageFile"
												accept="image/gif, image/jpeg, image/png" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12" style="padding: 5px;">
										<input type="submit" value="프로필 사진 변경"
											class="btn btn-success float-right">
									</div>
								</div>
							</form>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<c:if test="${member.mem_LoginApi == null }">
					<div class="col-md-6">
						<div class="card card-secondary">
							<div class="card-header">
								<h3 class="card-title">비밀번호 변경</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool"
										data-card-widget="collapse" data-toggle="tooltip"
										title="Collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
							<!-- 비밀번호 변경 -->

							<div class="card-body"
								style="padding-top: 20px;padding-bottom: 20px;">
								<div class="form-group">
									<form id="pwForm" class="form-horizontal" action="/member/update_pw" method="post" name="form" onsubmit="return checkAll()">
										<div class="form-group">
										<input type="hidden" name="mem_Id" value="${member.mem_Id}">
										<label for="inputEstimatedDuration">현재 비밀번호</label> <input
											class="form-control" id="old_pw" name="old_pw"
											type="password" required>
										</div>
										<div class="form-group">
											<label for="inputEstimatedDuration">새로운 비밀번호</label> <input
												class="form-control" id="pw" name="mem_Pwd" type="password"
												required>
										</div>
										<div class="form-group">
											<label for="inputEstimatedDuration">새로운 비밀번호 확인</label> <input
												class="form-control" id="pw2" name="mem_Pwd1" type="password"
												type="password" required>
										</div>
										<div class="row">
											<div class="col-12">
												<input type="submit" value="비밀번호 변경"
													class="btn btn-success float-right">
											</div>
										</div>
									</form>
								</div>
							</div>


							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!--  회원탈퇴 변경 -->
					<div class="col-md-6">
						<div class="card card card-secondary collapsed-card">
							<div class="card-header">
								<h3 class="card-title">회원탈퇴</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool"
										data-card-widget="collapse" data-toggle="tooltip"
										title="Collapse">
										<i class="fas fa-plus"></i>
									</button>
								</div>
							</div>
							<div class="card-body">
								<div class="form-group">
									<form id="outMemberForm" action="/member/delete_Member"
										method="post">
										<input type="hidden" name="mem_Id" value="${member.mem_Id}" />
										<p class="login-box-msg">이용해주셔서 감사합니다.</p>
										<label for="inputEstimatedBudget">현재 비밀번호</label> 
										<div class="form-group">
										<input
											class="form-control" id="pwd" name="mem_Pwd" type="password"
											required></div>
										<div class="row">
											<div class="col-12">
											<div class="form-group">
												<input type="submit" value="회원탈퇴"
													class="btn btn-success float-right"
													onclick="fn_deleteMem()" />
											</div></div>
										</div>
										<!-- 변경폼 -->

									</form>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
				</c:if>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<script type="text/javascript">
	//upload변수의 인풋의 순서 header의 인풋까지 포함.. 미포함시 6번째 //15
	var upload = document.getElementsByTagName('input')[10];
	var holder = document.getElementById('holder');
	var state = document.getElementById('status');
	upload.onchange = function(e) {
		e.preventDefault();

		var file = upload.files[0], reader = new FileReader();

		reader.onload = function(event) {
			var img = new Image();
			img.src = event.target.result;

			img.width = 100;
			img.height = 95;

			holder.innerHTML = '';
			holder.appendChild(img);
			holder.width = 100;
			holder.height = 95;

		};
		reader.readAsDataURL(file);
		return false;

	};
</script>