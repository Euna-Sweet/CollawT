<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>

<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="/SRC2/modal/dist/needpopup.min.css">

<script type="text/javascript">
	var result = '${msg}';
	if (result == 'success') {
		if(confirm("복사가 완료되었습니다. 복사한 글로 이동하시겠습니까?")){
		
		}else{
			window.history.back();
		}
	
	
<%
		//msg 세션 지우기.
		session.removeAttribute("msg");
%>
}
</script>


<script type="text/javascript">



$(document).ready(function() {
	
	
	
	//댓글 목록 항상 호출
	getReplyList();
	
	var formObj = $("form[name='readForm']");
	var formReply = $("form[name='replyForm']");

	
	
	
		//댓글 입력 버튼 클릭 시 이벤트
	$("#replyInsert_btn").on("click", function() {
		var p_m_Content = $("#p_m_Content").val();
		//댓글 입력 비어있으면 아무 이벤트도 일어나지 않게 하기
		if(p_m_Content==''){
			return false;
		
	//
		 }else{
			$.ajax({
				url:"/personal/memo/insert",
				type:'post',
				data:{
					p_m_Content: $("#p_m_Content").val(),
					p_Num : $("#p_Num").val(),
				},
				success:function(data){
					
                        $("#p_m_Content").val("");
                        getReplyList();
					
				}
			})
		}
	})
})
	
	


	//댓글 목록
	function getReplyList(){
		$.ajax({
			type:"get",
			url : "${path}/personal/memo/list?p_Num=${personalRead.p_Num}",
			
			success:function(result){
				
				var str="";
				if(result!=0){
					for(var i in result){
						str+='<div class="card-footer card-comments">';
						str+='<div class="card-comment">';
						

						str+='<img width="50" height="50" src="/member/getByteImage?mem_Id='+result[i].mem_Id+'" class="img-circle elevation-1" onError="this.src=\'${contextPath}/resources/dist/img/profile.jpg\';"/>';

							
						str+='<div class="comment-text">';
						str+='<span class="username">'+result[i].mem_Name;
						str+='<span class="text-muted float-right">'+result[i].p_m_Date+'</span>';
						str+='<small>'+('${member.mem_Id}'==result[i].mem_Id ? "&nbsp;&nbsp;&nbsp;<a href='javascript:replyModifyForm("+result[i].p_m_Num+",\""+result[i].p_m_Content+"\")'>수정</a>" : "")+'</small>';
						str+='<small>'+('${member.mem_Id}'==result[i].mem_Id ? "&nbsp;&nbsp;&nbsp;<a href='javascript:replyDelete("+result[i].p_m_Num+")'>삭제</a>" : "")+'</small></span>';
						
						str+='<p id="replyContent'+result[i].p_m_Num+'" name="replyContent">'+result[i].p_m_Content+'</p>';
						str+='</div></div></div>';
						str+='<input type="hidden" id="r_Num" name="r_Num" value="'+result[i].p_m_Num+'" />';
					}
					
				}else{
					str+='<div class="card-footer card-comments">';
					
					str+='<div class="card-comment">';
					str+='<div class="comment-text">';
					str+='<p style="text-align:center;"><small><br><br>작성된 메모가 없습니다.<br>메모를 남겨보세요 :D</small></p>'
					str+='</div></div></div>';
				}
				$("#replyList").html(str);
			}
				
		})
		
	}


	//댓글 삭제
  	function replyDelete(p_m_Num){
	
		if(confirm("삭제하시겠습니까?")){
			
			$.ajax({
				url : "/personal/memo/delete",
				data : {"p_m_Num" : p_m_Num},
				type : 'post',
				success:function(){
					alert("삭제되었습니다.");
					getReplyList();
					
				}
			})
			
		}
			
	}
	
	
	
	//댓글 수정창 열기
  	function replyModifyForm(p_m_Num, p_m_Content){
		
		var str="";
		
		str+='<div><textarea id="p_m_Content'+p_m_Num+'" name="p_m_Content'+p_m_Num+'" class="form-control">'+p_m_Content+'</textarea></div>';
		str+='<small><a href="javascript:replyUpdate('+p_m_Num+')" id="replyInsert_btn" name="replyInsert_btn">입력</span></small>&nbsp;&nbsp;';
		str+='<small><a href="javascript:replyCancle('+p_m_Num+',\''+p_m_Content+'\')" id="replyCancel_btn" name="replyCancel_btn">취소</span></small>';
	
		
		$('#replyContent'+p_m_Num).html(str);	
			
	} 

  
	
	//댓글 수정 db
	function replyUpdate(p_m_Num){
		var updateContent = $('[name=p_m_Content'+p_m_Num+']').val();
	    
	    $.ajax({
	        url : '/personal/memo/update',
	        type : 'post',
	        data : {'p_m_Content' : updateContent, 'p_m_Num' : p_m_Num},
	        success : function(data){
	           
	        	getReplyList();
	        }
	    });
	}
	
	//댓글 입력 취소 버튼 클릭시
	function replyCancle(p_m_Num, p_m_Content){
		var str="";
		str+='<p id="replyContent'+p_m_Num+'" name="replyContent">'+p_m_Content+'</p>';
			
		$('#replyContent'+p_m_Num).html(str);	
	}
	
	//클립보드로 url 복사
function urlClipCopy() {
		var tempElem = document.createElement('textarea');
		  tempElem.value = window.document.location.href; 
		  document.body.appendChild(tempElem);

		  tempElem.select();
		  document.execCommand("copy");
		  document.body.removeChild(tempElem);
		  alert("클립보드에 URL이 복사되었습니다.");

	}
</script>

	<style>
	.accent-teal .btn-link, .accent-teal a:not(.dropdown-item):not(.btn-app):not(.nav-link):not(.brand-link) {
	    color: #343a40;
	}
	@font-face {
		font-family: 'Recipekorea';
		src:
			url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff')
			format('woff');
		font-weight: normal;
		font-style: normal;
	}
	.nav-pills .nav-link.active{
			    background-color: #dc3545;
	 
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
	#applyspan {
		background-clip: padding-box;
	    border: 1px solid #17a2b8;
	    padding: 2px;
	    margin: 2px;
	    display: inline-block;
	}
	#applydelete {
		margin: 2px;
	}
	</style>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					
				</div>
				<div class="col-sm-6">
			
				</div>
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
			
				<form name="readForm" role="form">

					<div class="card card-widget">
						<div class="card-header">
						
							<span id="p_i_Name" name="p_i_Name"><b><c:out	value="${personalRead.p_i_Name}" /></b></span>
							
							
							<div class="btn-group float-right">
                          <button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false" style="border:white;background-color:white;height:20px;padding-top: 0px;color:gray"><i class="fas fa-ellipsis-h"></i>
                          </button>
                          <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; right: 0px; transform: translate3d(0px, 38px, 0px);">
                            
                        
                            <li><a class="dropdown-item" data-needpopup-show="#add-popup"><small>다른 협업공간으로 글 복사</small></a></li>
                            <li><a class="dropdown-item" href="javascript:urlClipCopy()"><small>URL 복사</small></a></li>
                          </ul>
                        </div>
                        <span class="text-muted float-right"><small><a href="/personal/update?mem_Id=${personalRead.mem_Id}&p_Num=${personalRead.p_Num}">수정</a></small>
						<small><a onclick="if(confirm('삭제하시겠습니까?')){alert('삭제되었습니다.');}else{return false;};" href='/personal/delete?mem_Id=${member.mem_Id}&p_Num=${personalRead.p_Num}'>삭제</a></small></span>
					
						</div>
						

						
						<!-- /.card-header -->
						<div class="card-body">
						<span class="text-muted float-right"id="p_i_Date" name="p_i_Date" style="font-size:small">
								<c:out	value="${personalRead.p_i_Date}" /></span>
							<div class="user-block">
							
							


								<!-- 멤버이미지 넣기 -->
								
								<c:if test="${personalRead.mem_File != null }">
								<img alt="프로필사진" width="50" height="50"
								src="/member/getByteImage?mem_Id=${personalRead.mem_Id}" class="img-circle elevation-2"/>
								</c:if>
								<c:if test="${personalRead.mem_File == null }">
								<img src="${contextPath}/resources/dist/img/profile.jpg" width="50" height="50"
								class="img-circle elevation-2" alt="프로필사진">
								</c:if>
								<span class="username" id="mem_Name" name="mem_Name">
								<c:out value="${personalRead.mem_Name}" /></span>
								<span class="description"id="mem_Id" name="mem_Id">
								<c:out	value="${personalRead.mem_Id}" /></span>  
								
								
							</div>
							<br><br><br>
							
							
				
								<br>
							
							<!-- 내용 -->
								<p id="p_i_Content" name="p_i_Content"><c:out value="${personalRead.p_i_Content}" escapeXml="false"/></p>
						
							
							
							<br>
							<br>
							<br>
							 <input type="hidden" id="p_Num" name="p_Num"
								value="${personalRead.p_Num}" /> <br>
							<br>

<!-- 이슈정보~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<div class="card card-primary ">
            <div class="card-header" style="height:30px; padding-left:13px; padding-right:16px; padding-top:4px; background-color:#e87c87;">
             <label><small><b>이슈 정보</b></small></label>
                <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                  <i class="fas fa-minus"></i></button>
              </div>
            </div>
            <div class="card-body p-0">
            
             <table class="table">
              <tbody>
		           <tr>
		           <td>
		           <div class="row">
		           <ion-icon name="calendar-outline" style="font-size:24"></ion-icon>&nbsp;
					<span style="font-size: 0.8em;"><b>기간</b></span>
					</div>
		           		<!-- 시작일/종료일-->
		           		<div class="row" style="margin-left:9px">
							<c:if test="${not empty personalRead.p_i_Start&& not empty personalRead.p_i_End}">
							
								<span id="p_i_Start" name="p_i_Start" class="username ">
								<small><c:out value="${personalRead.p_i_Start}~"/></small></span>
								<span id="p_i_End" name="p_i_End" class="username">
								<small><c:out value="${personalRead.p_i_End}"/></small></span>
							</c:if>
							<c:if test="${empty personalRead.p_i_Start&& empty personalRead.p_i_End}">
							
								<span class="username"><small>기간 미설정</small></span>
							</c:if>
							</div>
		           </td>
		           </tr>
		        

							</tbody>
              </table>
            </div></div>				
<!-- 첨부파일 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->				


<!-- 첨부된 파일 없으면 창 아예 안보이게  -->		
<c:if test="${not empty file}">
					<div class="card card-info">
                        <div class="card-header" style="height:30px; padding-left:13px; padding-right:16px; padding-top:4px; background-color:#e87c87;">
             <label><small><b>첨부파일</b></small></label>
                <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                  <i class="fas fa-minus"></i></button>
              </div>
            </div>
            <div class="card-body p-0">
              <table class="table">

                <tbody>

				  <c:forEach var="file" items="${file}" >
                  <tr>
                    <td><small><span id="p_a_RealName" name="p_a_RealName">${file.p_a_RealName}</span></small></td>
                    <td><span id="p_a_Size" name="p_a_Size"><small>${file.p_a_Size/1000}kb</small></span></td>
                    <td class="text-right py-0 align-middle">
                      <a href="/personal/appendix/download?p_a_Num=${file.p_a_Num}"><i class="fas fa-download"></i></a>
                    </td>
                 </tr>
                 </c:forEach>
                </tbody>
              </table>
            </div>
           <!--  /.card-body -->
          </div>
          <br>
          </c:if>
          <!-- /.card -->
          							
						
						<a id="list_btn" class="btn btn-default btn-sm" href='list?mem_Id=${member.mem_Id}' style="color:#444; margin:3px;"> <i class="fas fa-list"></i>&nbsp;목록</a>

								
								<br><br>
						</div>

						<!-- /.card-body -->

						<!-- /.card -->
					</div>

					<!-- /.col -->
					<div class="col-md-6">
						<!-- Box Comment -->

					</div>

					<!-- /.card -->
					
			</div>
			</form>

<!-- /.card -->




<!-- 댓글 부분~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->				

			 <div class="col-md-6">
				<div class="card">

					
					<div id="replyList" style="max-height:631px; overflow:auto"></div>
					
					<!-- 댓글  입력-->
					<div class="card-footer" id="replyInput" name="replyInput">
					
			
						
							<div class="img-push">
								<textarea id="p_m_Content" name="p_m_Content" class="form-control"	placeholder="메모를 남겨주세요"></textarea>
									
									
									<small><a href="#" id="replyInsert_btn" name="replyInsert_btn">입력</a></small>
																		
									<input type="hidden" id="p_Num" name="p_Num" value="${personalRead.p_Num}" />
									
								
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


<!-- 다른협업공간으로 글 복제 모달 팝업 내용 -->
<div id='add-popup' class="needpopup">
	<p>
	<div style="padding-bottom: 25px;">
		<h6 class="m-0 text-dark"
			style="font-family: Spoqa Han Sans; padding-bottom: 5px;">협업공간 선택</h6>
		<span style="font-size: 0.9em; line-height: 1.0; color: #a1a1a1;">
			복사한 글을 게시할 협업공간을 선택하세요.</span>
	</div>
	
	<form action="/personal/copy" method="post" id="copyform">
      <select class="form-control custom-select" id="coworkSelect" name="coworkSelect">
      	<option selected disabled>협업공간을 선택하세요</option>
         <c:forEach var="coworkList" items="${coworkList}" >
         
         <option id="copyc_Id" name="copyc_Id" value="${coworkList.C_ID}">${coworkList.C_NAME}</option>

        </c:forEach>
       </select>	
		
		<input type="hidden" value="${personalRead.p_i_Name}" id="p_i_Name" name="p_i_Name">
		<input type="hidden" value="${personalRead.p_i_Content}" id="p_i_Content" name="p_i_Content">
		<input type="hidden" value="${personalRead.p_i_Start}" id="p_i_Start" name="p_i_Start">
		<input type="hidden" value="${personalRead.p_i_End}" id="p_i_End" name="p_i_End">
		<input type="hidden" value="${personalRead.p_Num}" id="p_Num" name="p_Num">
 		<input type="hidden" value="" id="changedc_Id" name="c_Id">
		
	
		<br><br>
		<span style="float: left; padding-right: 50px;">
			
		<button type="submit" id="copysubmit"
					class="btn btn-block btn-success" style="width: 220px;">복사</button>
			
		</span> <span>
			<button type="reset" class="btn btn-block btn-success"
				onclick="history.go(0);" style="width: 220px">취소</button>
		</span>
		</form>
	</div>
	<script>
 	$("#coworkSelect").change(function(event) {
 		var copyc_Id = $("#coworkSelect option:selected").val();
		$("#changedc_Id").val(copyc_Id);
	
	})
	
	

	</script>

