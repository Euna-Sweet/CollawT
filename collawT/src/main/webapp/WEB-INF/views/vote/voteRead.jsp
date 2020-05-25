<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.btn-app {
	border-radius: 3px;
	color: #6c757d;
	font-size: 12px;
	height: 60px;
	margin: 0 0 0px 0px;
	min-width: 80px;
	padding: 0px 5px;
	position: relative;
	text-align: center;
}

#modal {
	display: none;
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1;
}

#modal h2 {
	margin: 0;
}

#modal button {
	display: inline-block;
	width: 100px;
	margin-left: calc(100% - 100px - 10px);
}

#modal .modal_content {
	width: 300px;
	margin: 100px auto;
	padding: 20px 10px;
	background: #fff;
	border: 2px solid #666;
}

#modal .modal_layer {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	z-index: -1;
}

progress {
	display: block; /* default: inline-block */
	width: 200px;
	padding: 4px;
	border: 0 none;
	background: #444;
	border-radius: 14px;
}

progress::-moz-progress-bar {
	border-radius: 12px;
	background: blue;
}

progress::-webkit-progress-value {
	border-radius: 12px;
	background: #fff;
}
</style>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>

<script type="text/javascript">
function checkAll() {
    //투표 선택안하면 ...
	var vd_Num = document.getElementsByName('vd_Num');
	var sel_type = null;
	for(var i=0;i< vd_Num.length;i++){
		if(vd_Num[i].checked == true){ 
			sel_type = vd_Num[i].value;
			console.log(sel_type);
		}
	}

	if(sel_type == null){
            alert("투표유형을 선택해주세요. "); 
		return false;

	}
	 return true;
}

$(document).ready(function() {
	
	
	
	//댓글 목록 항상 호출
	getReplyList();
	
	var formObj = $("form[name='readForm']");
	var formReply = $("form[name='replyForm']");

	
	
	//북마크 체크 변수
	var checkbook = 0
	
	//북마크 체크 확인
	bookcheck();
	
	
	//console.log("22222222222"+checkbook);



	
	
	//댓글 입력 버튼 클릭 시 이벤트
	$("#replyInsert_btn").on("click", function() {
		var vr_Content = $("#vr_Content").val();
		//댓글 입력 비어있으면 아무 이벤트도 일어나지 않게 하기
		if(vr_Content==''){
			return false;
		
	//
		 }else{
			 console.log("its been a longday")
			$.ajax({
				url:"/voteReply/insert",
				type:'post',
				data:{
					vr_Content: $("#vr_Content").val(),
					v_Num : $("#v_Num").val(),
					c_Id : $("#c_Id").val()
				},
				success:function(data){
					
                        $("#vr_Content").val("");
                        getReplyList();
					
				}
			})
		}
	})
	
	
	//북마크 버튼 이벤트
	$("#bookmarkinsert").on("click", function() {
		console.log("1121212"+checkbook);
		if(checkbook == 0){
		$.ajax({
			url:"/bookmark/insert",
			type:'post',
			data:{
				i_Num : $("#i_Num").val(),
			},
			success:function(data){
				var bookcount = $("#bookmarkcount").html();
				console.log(bookcount);
				if(data == 1){
                    alert("북마크에 등록하셨습니다.");
				$("#bookmarkinsert").css('color','#f8f9fa');
				$("#bookmarkinsert").css('background-color', '#dc3545');
				checkbook = 1;
				bookcount++;
				console.log(bookcount);
				$("#bookmarkcount").html(bookcount);
				}
			}
		})
		}else if(checkbook == 1){
			$.ajax({
				url:"/bookmark/delete",
				type:'post',
				data:{
					i_Num : $("#i_Num").val(),
				},
				success:function(data){
					var bookcount = $("#bookmarkcount").html();
					if(data == 1){
	                    alert("북마크를 해제하셨습니다.");
					$("#bookmarkinsert").css('color','#444');
					$("#bookmarkinsert").css('background-color', '#f8f9fa');
					checkbook = 0;
					bookcount--;
					console.log(bookcount);
					$("#bookmarkcount").html(bookcount);
				}
					}
				})
			}
		})
	
		
		//북마크 추가 상태 확인 메소드
	function bookcheck() {
		$.ajax({
			url:"/bookmark/bookmarkCheck",
			type:'get',
			data:{
				i_Num : $("#i_Num").val(),
			},
			success:function(data){
				if(data == 1){
				$("#bookmarkinsert").css('color','#f8f9fa');
				$("#bookmarkinsert").css('background-color', '#dc3545');
				console.log(checkbook);
				checkbook = 1;
				console.log(checkbook);
				}
				
			}
		})
	}
	
})

	//댓글 목록
	function getReplyList(){
		$.ajax({
			type:"get",
			url : "${path}/voteReply/list?v_Num=${voteRead.v_Num}",
			
			success:function(result){
				
				var str="";
				if(result!=0){
					for(var i in result){
						str+='<div class="card-footer card-comments">';
						str+='<div class="card-comment">';
						
						str+='<img width="50" height="50" src="/member/getByteImage?mem_Id='+result[i].mem_Id+'" class="img-circle elevation-1" onError="this.src=\'${contextPath}/resources/dist/img/profile.jpg\';"/>';
						
						str+='<div class="comment-text">';
						str+='<span class="username">'+result[i].mem_Name;
						str+='<span class="text-muted float-right">'+result[i].vr_Date+'</span>';
						str+='<small>'+('${member.mem_Id}'==result[i].mem_Id ? "&nbsp;&nbsp;&nbsp;<a href='javascript:replyModifyForm("+result[i].vr_Num+",\""+result[i].vr_Content+"\")'>수정</a>" : "")+'</small>';
						str+='<small>'+('${member.mem_Id}'==result[i].mem_Id ? "&nbsp;&nbsp;&nbsp;<a href='javascript:replyDelete("+result[i].vr_Num+")'>삭제</a>" : "")+'</small></span>';
						
						str+='<p id="replyContent'+result[i].vr_Num+'" name="replyContent">'+result[i].vr_Content+'</p>';
						str+='</div></div></div>';
						str+='<input type="hidden" id="vr_Num" name="vr_Num" value="'+result[i].vr_Num+'" />';
					}
					
				}else{
					str+='<div class="card-footer card-comments">';
					
					str+='<div class="card-comment">';
					str+='<div class="comment-text">';
					str+='<p style="text-align:center;"><small><br><br>작성된 댓글이 없습니다.<br>이 글의 첫 번째 댓글을 작성해주세요 :D</small></p>'
					str+='</div></div></div>';
				}
				$("#replyList").html(str);
			}
				
		})
		
	}


	//댓글 삭제
  	function replyDelete(vr_Num){
	
		if(confirm("삭제하시겠습니까?")){
			$.ajax({
				url : "/voteReply/delete",
				data : {"vr_Num" : vr_Num},
				type : 'post',
				success:function(){
					alert("삭제되었습니다.");
					getReplyList();
					
				}
			})
			
		}
			
	}
	
	
	
	//댓글 수정창 열기
  	function replyModifyForm(vr_Num, vr_Content){
		
		var str="";
		
		str+='<div><textarea id="vr_Content'+vr_Num+'" name="vr_Content'+vr_Num+'" class="form-control">'+vr_Content+'</textarea></div>';
		str+='<small><a href="javascript:replyUpdate('+vr_Num+')" id="replyInsert_btn" name="replyInsert_btn">입력</span></small>&nbsp;&nbsp;';
		str+='<small><a href="javascript:replyCancle('+vr_Num+',\''+vr_Content+'\')" id="replyCancel_btn" name="replyCancel_btn">취소</span></small>';
	
		
		$('#replyContent'+vr_Num).html(str);	
			
	} 

  
	
	//댓글 수정 db
	function replyUpdate(vr_Num){
		var updateContent = $('[name=vr_Content'+vr_Num+']').val();
	    console.log("gesg");
	    $.ajax({
	        url : '/voteReply/update',
	        type : 'post',
	        data : {'vr_Content' : updateContent, 'vr_Num' : vr_Num},
	        success : function(data){
	           
	        	getReplyList();
	        }
	    });
	}
	
	//댓글 입력 취소 버튼 클릭시
	function replyCancle(vr_Num, vr_Content){
		var str="";
		str+='<p id="replyContent'+vr_Num+'" name="replyContent">'+vr_Content+'</p>';
			
		$('#replyContent'+vr_Num).html(str);	
	}
	
	//클립보드로 url 복사


	function urlClipCopy() {
		var tempElem = document.createElement('textarea');
		  tempElem.value = window.document.location.href; 
		  document.body.appendChild(tempElem);

		  tempElem.select();
		  document.execCommand("copy");
		  document.body.removeChild(tempElem);
		  alert("URL이 클립보드에 복사되었습니다. CTRL+V하시면 URL이 입력됩니다");

	}

	
	
	
</script>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6"></div>
				<div class="col-sm-6"></div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->

	<section class="content">

		<div class="row" id="row">
			<div class="col-md-6">

				<!-- Box Comment -->
				<!-- 본문 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

				<form name="insertVoterForm" action="/project/vote/insertVoter" onsubmit="return checkAll()" method="get" encType="UTF-8">
					<!--  완료 처리하기 위한 오늘 날짜가져오 -->
				<!-- 	<input type="text" id="today" name="v_Today"> -->
					<div class="card card-widget">


						<div class="card-header">
						<span style= "vertical-align:text-top; background-color:${voteRead.vs_Color};"
							id="vs_Name" name="vs_Name" class="badge badge-success"><c:out  value="${voteRead.vs_Name}" /></span>&nbsp;&nbsp;
							<span id="v_Name" name="v_Name"><b><c:out	value="${voteRead.v_Name}" /></b></span>

							<div class="btn-group float-right">
								<button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false" style="border: white; background-color: white; height: 20px; padding-top: 0px; color: gray">
									<i class="fas fa-ellipsis-h"></i>
								</button>
								<ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; right: 0px; transform: translate3d(0px, 38px, 0px);">

							 <li><a class="dropdown-item" href="javascript:urlClipCopy()"><small>URL 복사</small></a></li>
								</ul>
							</div>
                        <!-- 본인만 글 수정, 삭제 가능-->
                        <span class="text-muted float-right">
                        <c:if test="${member.mem_Id == voteRead.mem_Id}">
                        <small><a href="/project/vote/update?c_Id=${voteRead.c_Id}&v_Num=${voteRead.v_Num}">수정</a>｜</small>
                        </c:if>
                        <!-- 협업공간 관리자는 모든 글 삭제 가능-->
                        <c:if test="${member.mem_Id == voteRead.mem_Id || member.mem_Id == pjt.mem_Id}">
						<small><a onclick="if(confirm('삭제하시겠습니까?')){alert('삭제되었습니다.');}else{return false;};" href='/project/vote/delete?c_Id=${voteRead.c_Id}&v_Num=${voteRead.v_Num}'>삭제</a></small>
						</c:if>
						</span>
						</div>



						<!-- /.card-header -->
						<div class="card-body">
							<span class="text-muted float-right" id="v_Date" style="font-size: small"> <c:out value="${voteRead.v_Date}" /></span>
							<div class="user-block">




								<!-- 멤버이미지 넣기 -->

								<c:if test="${voteRead.mem_File != null }">
									<img alt="프로필사진" width="50" height="50" src="/member/getByteImage?mem_Id=${voteRead.mem_Id}" class="img-circle elevation-2" />
								</c:if>
								<c:if test="${voteRead.mem_File == null }">
									<img src="${contextPath}/resources/dist/img/profile.jpg" width="50" height="50" class="img-circle elevation-2" alt="프로필사진">
								</c:if>
								<!-- 이름가죠오기 -->
								<span class="username" id="mem_Name"> <c:out value="${voteRead.mem_Name}" />
								</span> <span class="description" id="mem_Id"> <c:out value="${voteRead.mem_Id}" /></span>


							</div>
							<br> <br> <br> <br>


							<p id="v_Content">
								<c:out value="${voteRead.v_Content}" escapeXml="false" />
							</p>



							<br> <br> <br> <input type="hidden" id="v_Num" name="v_Num" value="${voteRead.v_Num}" /> <br> <br>
						
							<!-- 투표 view~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
							<div class="card card-primary collapsed-card">
								<div class="card-header" style="height: 30px; padding-left: 13px; padding-right: 16px; padding-top: 4px; background-color: #e87c87;">
									<label><small><b>${voteRead.v_Subject}</b></small></label>
									<div class="card-tools">
										<button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
											<i class="fas fa-minus"></i>
										</button>
									</div>
								</div>
								<input type="hidden" id="c_Id" name="c_Id" value="${voteRead.c_Id}" />
								<div class="card-body p-0" style="display: block;">
									<table class="table">
										<tbody>
											<!-- 투표 내용 -->
											<!--  상태 index -->
											<!-- for each -->
											<c:forEach var="votedReadItem" items="${votedRead}" varStatus="status">
												<tr>
													<td style="padding-left: 0px; padding-right: 0px; width: 315px;">
														<!-- 투표 리스트 --> <input type="hidden" name="getVsNum" value="${status.index+1 }"> 
														<c:if test="${ voteRead.vs_Num==1}"><input class="form-check-input" type="radio" name="vd_Num" value="${votedReadItem.vd_Num}" style="margin-left: 10px;"></c:if>
														<label class="form-check-label" style="margin-left: 40px;">${votedReadItem.vd_Num}.&nbsp;&nbsp;&nbsp;${votedReadItem.vd_Content}</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

													</td>
													<td><i class="fas fa-user-alt"></i><span class="badge bg-gray"><c:if test="${votedRead[status.index].v_Count ==null }">0</c:if>${votedReadItem.v_Count}</span> <br></td>
												</tr>
											</c:forEach>
											<tr>
											</tr>
											<tr>
												<td><ion-icon name="calendar-outline" style="font-size:24"></ion-icon>&nbsp; <span style="font-size: 0.8em;"><b>기간</b></span><br> <c:if test="${not empty voteRead.v_Start&& not empty voteRead.v_End}">
														<span id="v_Start" class="username "> <small><c:out value="${voteRead.v_Start}~" /></small>
														</span>
														<span id="v_End" class="username"> <small><c:out value="${voteRead.v_End}" /></small>
														</span>
													</c:if> <c:if test="${empty voteRead.v_Start&& empty voteRead.v_End}">
														<span class="username"> <small>기간 미설정</small></span>
													</c:if></td>
												<td></td>
											</tr>

										</tbody>
									</table>
								</div>
							</div>
							<div class="row float-right">
								<c:if test="${ voteRead.vs_Num==1}">
								<input type="submit" value="투표하기" class="btn btn-block btn-default btn-xs float-right" style="width: 80px; margin: 1px; padding-bottom: 1px; border-bottom-width: 1px; margin-bottom: 30px;">
								</c:if>
							</div>



							<!-- 북마크 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
							<!-- <button type="button" class="btn btn-default btn-sm" id="bookmarkinsert" style="color: #444; margin: 3px;">
								<i class="fas fa-bookmark"></i> 북마크
							</button> -->
							<input type="hidden" name="mem_Id" value="${voteRead.mem_Id}"> <a id="list_btn" class="btn btn-default btn-sm" href='list?c_Id=${voteRead.c_Id}' style="color: #444; margin: 3px;"> <i class="fas fa-list"></i>&nbsp;목록
							</a> <br> <br>
							<div></div>
						</div>
						<!-- /.card-body -->
						<!-- /.card -->
					</div>
					<!-- /.col -->
					<div class="col-md-6">
						<!-- Box Comment -->

					</div>
					<!-- /.card -->
				</form>
			</div>

			<!-- /.card -->




			<!-- 그래프 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

			<div class="col-md-6">
				<div class="card">


					<div class="card-body">
						<table>
							<tr>
								<td style="padding-left: 0px; padding-right: 0px; width: 315px;"><c:forEach var="voteTotalItem" items="${voteTotal}" varStatus="i">
										<span class="users-list-date">${voteTotalItem.vd_Num}번&nbsp;&nbsp;</span>
										<!-- 그래프 -->
										<progress value="${voteTotalItem.v_Count}" max="${voteRead.co_Mem_Total }" style="margin-left: 0px; margin-top: 0px; margin-bottom: 0px; margin-right: 0px;"></progress>
										&nbsp;&nbsp;&nbsp;&nbsp;<span class="users-list-date float-right" style="font-size: small; margin-right: 70px;">${voteTotalItem.v_Count *100 / voteRead.co_Mem_Total *100 /100} % &nbsp;&nbsp;&nbsp;&nbsp<%-- ; ${voteTotalItem.v_Count}명 --%></span>
										<br>
									</c:forEach></td>
							</tr>
						</table>



						<!-- 차트 넣을곳-->

						<%-- <c:forEach var="voterItem" items="${voterList}">
						
							
							<div class="user-block">
								<!-- 이름가죠오기 -->
							<p>${voterItem.vd_Num}번투표자</p>
								<span class="description" id="mem_Name"> <c:out value="${voterItem.mem_Name}" />
								<c:out value="(${voterItem.mem_Id})" /></span>

							</div>
						</c:forEach> --%>

					</div>
					<c:if test="${voteRead.v_Show==0 }">
					<p  class="nav-link" >
						 <i class="fas fa-vote-yea"></i> 비공개 투표입니다. <span class="badge badge-info right" id="bookmarkcount" style="background-color: #FFC108"></span>
					</p>
					</c:if>
					<c:if test="${voteRead.v_Show==1 }">
						<p>
							<a href="/#" data-needpopup-show="#voterList-popup" class="nav-link" id="voterlist"> <i class="fas fa-vote-yea"></i> 투표자 현황 <span class="badge badge-info right" id="bookmarkcount" style="background-color: #FFC108"></span>
							</a>
						</p>
					</c:if>

					<div id="replyList"></div>
					
					<!-- 댓글  입력-->
					<div class="card-footer" id="replyInput" name="replyInput">

						
							<%-- <img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${member.mem_Id}" alt="Alt Text" width="50" height="50" > --%>
							
								<c:if test="${member.mem_File != null }">
								<img class="img-fluid img-circle img-sm"
								src="/member/getByteImage?mem_Id=${member.mem_Id}" alt="Alt Text" width="50" height="50" >
								</c:if>
								<c:if test="${member.mem_File == null }">
								<img class="img-fluid img-circle img-sm"
								src="${contextPath}/resources/dist/img/profile.jpg" alt="Alt Text" width="50" height="50">
								</c:if>
						
							<div class="img-push">
								<textarea id="vr_Content" name="vr_Content" class="form-control"	placeholder="댓글을 입력하세요"></textarea>
									
									
									<small><a href="#" id="replyInsert_btn" name="replyInsert_btn">입력</a></small>
																		
									<input type="hidden" id="v_Num" name="v_Num" value="${voteRead.v_Num}" />
									<input type="hidden" id="c_Id" name="c_Id" value="${voteRead.c_Id}" />
									
								
							</div>
					
					</div>
					<!-- /댓글 입력 -->
				</div>
			</div>
			<div class="row">
				<div class="col-12"></div>
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<div id='voterList-popup' class="needpopup">
	<div class="col-md-6" style="max-width: 100%;">
		<table class="table table-striped projects" style="white-space: nowrap; overflow: hidden;">
			<div style="margin-bottom: 10px;">
				<h4 class="m-0 text-dark" style="font-family: Recipekorea; max-width: 80%; display: contents;">투표자 현황</h4>
			</div>
			<tr style="background-color: #dc3545; color: white;">
				<!-- <td style="width: 30%; vertical-align: top"><font size="3em"><b>보기</b></font></td> -->
				<td style="width: 50%"><font size="3em"><b>투표자</b></font></a>
			</tr>
			<tr>
				<%-- <td>
				 	<c:forEach var="votedRead" items="${votedRead}" varStatus="status">
						<c:out value="${votedRead.vd_Num} ."></c:out>
						<label class="form-check-label">&nbsp;${votedRead.vd_Content}</label>
						<br>

					</c:forEach>
				 </td>
		 --%>
				<td><c:if test="${empty voteTotal}">투표자가 없습니다.</c:if> <c:forEach var="voteTotalItem" items="${voteTotal}" varStatus="i">
						<span class="users-list-date">&nbsp;${voteTotalItem.vd_Num}번투표자</span>
						<%-- 
				<c:forEach var="voteTotalItem" items="${voteTotal}" varStatus="i">
						<span class="users-list-date">&nbsp;${voteTotalItem.vd_Num}번투표자</span> --%>
						<c:forEach var="voterItem" items="${voterList}" varStatus="j">
							<c:if test="${voteTotal[i.index].vd_Num eq voterList[j.index].vd_Num}">
								<%-- <c:out value="${voterItem.mem_Name}"/> --%>
								<span class="badge bg-gray"> <a class="users-list-name">${voterList[j.index].mem_Name}</a></span>
							</c:if>
						</c:forEach>
					</c:forEach></td>
			</tr>
			<tbody id='bookmarkitem'>
			</tbody>
		</table>
	</div>
</div>
