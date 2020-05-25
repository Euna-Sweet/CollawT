<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src = "${path}/resources/ckeditor/ckeditor.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
    <script type = "text/javascript">
        window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}','${filePath}', '업로드완료');
    </script>

<style type="text/css">
span[name="chargerspan"] {
	background-clip: padding-box;
	border: 1px solid #17a2b8;
	padding: 2px;
	margin: 2px;
	display: inline-block;
}

#row {
	margin-left: 0px !important;
	margin-right: 0px !important
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		periodSetting();
		
		//글쓰기 빈 값 안되게 검사
		$("#submit").click(function(){
			var v_Name = $("#v_Name").val();
		
			
			if(v_Name==''){
				alert("제목을 입력하세요");
				document.insertForm.v_Name.focus();
				return false; 
			}  else if (CKEDITOR.instances.v_Content.getData() == '') {
				console.log("sdaklfj"+v_Content);
				alert("내용을 입력해주세요");
				return false;
			}  else if (v_Subject == '') {
				alert("투표주제를 입력해주세요");
				document.insertForm.v_Subject.focus();
				return false;
			} else if (vd_Content == '') {
				alert("투표항목을 입력해주세요");
				return false;
			} else if (v_Name.length > 12) {
				alert("제목은 12자리 이내로 입력해주세요");
				return false;
			} else if (v_Content.length > 12) {
				alert("내용은 12자리 이내로 입력해주세요.");
				return false;
			} else if (v_Subject.length > 12) {
				alert("투표 주제는 12자리 이내로 입력해주세요.");
				return false;
			} else if (vd_Content.length > 12) {
				alert("투표 항목은 12자리 이내로 입력해주세요.");
				return false;
			}
			console.log("??");
			
			periodSetting();
			document.insertForm.submit();
			
		});
		
		
		//캘린더 시작일/마감일 구하기/기간 미설정 시
		
		$("#periodNull").click(function(){
			periodSetting();
		})
		
		
		//캘린더 기능
		 $('#v_Period').daterangepicker({
			locale: { format: 'YYYY-MM-DD'}
		 })
	
	function periodSetting(){
		if($("#periodNull").is(":checked")==true){
			var v_Start = "";
			var v_End="";
			document.insertForm.v_Start.value=v_Start;
			document.insertForm.v_End.value=v_End;
			
			var v_PeriodCheck = "";
			document.insertForm.v_PeriodCheck.value=v_PeriodCheck;
			
			$("#v_Period").css('background-color','#e9ecef');
			$("#v_Period").css('color','#e9ecef');
			$("#v_Period").attr('disabled',true)
		}else{
			$("#v_Period").css('background-color','#fff');
			$("#v_Period").css('color','#495057');
			$("#v_Period").attr('disabled',false)
			
			var v_PeriodCheck = $("#v_Period").val();
			document.insertForm.v_PeriodCheck.value=v_PeriodCheck;
			
			var v_Start = $("#v_PeriodCheck").val().substring(0,10);
			var v_End= $("#v_PeriodCheck").val().substring(13,23);
			document.insertForm.v_Start.value=v_Start;
			document.insertForm.v_End.value=v_End;
		}
	}
		/* 
		 $('.btnAdd').click (function () {                                        
			var lim = 0;
			console.log(lim);
             $('.buttons').append (                        
                 '<input type="text" name="txt"> <input type="button" class="btnRemove" value="Remove"><br>'                    
             );
             lim++;
             if(lim<5){
            	 alert("5개이상은 많아요.");
            	return false;
             }
             // end append                            
             $('.btnRemove').on('click', function () { 
                 $(this).prev().remove (); // remove the textbox
                 $(this).next ().remove (); // remove the <br>
                 $(this).remove (); // remove the button
             });
         }); // end click       */

	 }); // end ready    
  	
</script>
<script language="javascript">
//항목 추가 텍스트 박스
var flen = 3;
function Insert_poll()
{    
 var vd_Num_length = document.insertForm.vd_Num.length;
 if (vd_Num_length > 4)
 {
  alert("더이상 추가할수 없습니다.")
  return false;
 }

 var table;
 var objRow;
 var objCell;         
 
 table = document.getElementById("poll_item");
 tableRow = table.insertRow(table.rows.length);
 tableCell = tableRow.insertCell(0);

 tableCell.innerHTML = "<label for='inputName'>투표 항목"+flen +"</label>"
 					+  "<input type='hidden' id='vd_Num' name='vd_Num' class='form-control' value="+flen+">"
 					+  "<input type='text' id='vd_Content' name='vd_Content' class='form-control'>"
 					;
 flen++;
}

 

function Del_poll()
{
 // file_length 이하로는 필드가 삭제되지 않게
 var file_length = 0;
 var table = document.getElementById("poll_item");
 if (table.rows.length - 1 > file_length)
 {
  table.deleteRow(table.rows.length - 1);
  flen--;
 }
}
</script>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<form name=insertForm action="/project/vote/update" method="post" encType="UTF-8">
		<input type="hidden" id="v_Num" name="v_Num" value="${voteRead.v_Num}">
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6"></div>
					<div class="col-sm-6"></div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</section>

		<%-- <input id="c_Id" name="c_Id" type="hidden" value="${c_Id}" /> --%>
		<!-- Main content -->
		<section class="content">
			<div class="row" id="row">
				<div class="col-md-6">
					<div class="card card-primary">

						<div class="card-body">
							<div class="form-group">
								<div class="form-group">
									<label for="inputName">제목</label> 
									<input type="text" id="v_Name" name="v_Name" class="form-control" value="${voteRead.v_Subject}">
								</div>
								<div class="form-group">
									<label for="inputDescription">내용</label>
									<textarea name="v_Content" id="v_Content" class="form-control" style="width: 100%;">${voteRead.v_Subject}</textarea>
				<script>
           
                //CK에디터 적용
					CKEDITOR.replace( 'v_Content', {
						allowedContent:true,
	
					toolbar :[['NewPage','Preview','Bold','Italic','Underline','Strike','-','Subscript','Superscript','-','-',
					'JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Outdent','Indent','HorizontalRule','Link','Unlink','-',
					'Find','Replace','SelectAll','Image','Youtube','Table','SpecialChar'],
					'/',['Styles','Format','Font','FontSize','Undo','Redo'],['TextColor','BGColor'],['Cut','Copy','Paste','PasteText'],['Source']],
					
					});
				
			 	 CKEDITOR.on('dialogDefinition', function( ev ){
			        var dialogName = ev.data.name;
			         var dialogDefinition = ev.data.definition;
			      
			         switch (dialogName) {
			             case 'image': //Image Properties dialog
			                 //dialogDefinition.removeContents('info');
			                 //dialogDefinition.removeContents('Link'); // 링크탭 제거
			                 dialogDefinition.removeContents('advanced'); // 자세히탭 제거
			                 break;
         }
     });
			 	 
			 	CKFinder.setupCKEditor( v_Content, '/ckfinder/' );
			 	 
	
 				       
					</script>
								</div>
       
								<div class="form-group">
								</div>

								<small></small>
							</div>
						</div>

						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>

				
				<!--협업공간ID -->
				<input id="c_Id" name="c_Id" type="hidden" value="${c_Id}" />
				
				<!-- 투표상태는 디폴트로 '1'으로 설정 (진행중)-->
				<input name="vs_Num" id="vs_Num" type="hidden" value="1"><br>
				<!--  여기부터 투표기능 만드세여~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~` -->

				<div class="col-md-6">

					<div class="card card-info">

						<div class="card-body">


							<!-- 투표 주제 -->
							<div class="form-group">
								<label for="inputName">투표 주제</label> <input type="text" id="v_Subject" name="v_Subject" class="form-control"value="${voteRead.v_Subject }">
							</div>

							<!-- 투표 항목 -->
							<div class="form-group">
								
								<c:forEach var="votedReadItem" items="${votedRead}" varStatus="status">
								<label for="inputName">투표 항목${status.index+1} </label>
								<input type="hidden" id="vd_Num" name="vd_Num" class="form-control" value="${status.index+1}"> 
								<input type="text" id="vd_Content" name="vd_Content" class="form-control" value="${ votedReadItem.vd_Content}">
								
								<!--추가되는. -->
								</c:forEach>
								<table width="100%" cellpadding="0" cellspacing="0" id="poll_item"></table>
							</div>



							<!-- 답변 선택 수 -->
							<div class="form-group">
								<label for="inputStatus">답변 선택 수</label> <select class="form-control custom-select" id="v_Count" name="v_Count">
									<option value="1">1개 선택 가능</option>
									<option value="2">2개 선택 가능</option>
									<option value="3">3개 선택 가능</option>
								</select>
							</div>



							<!-- 기간 설정 -->
							<div class="form-group">
								<div class="row">
									<div class="col-8">
										<label for="inputStatus">기간 설정</label>
									</div>

									<div class="col-4">
										<div class="icheck-danger" style="text-align: right;">

											<input type="checkbox" value="" id="periodNull" name="periodNull"> <label for="periodNull"><small>기간 미설정</small></label>
										</div>
									</div>
								</div>
								<tr>
												<td><ion-icon name="calendar-outline" style="font-size:24"></ion-icon>&nbsp; <span style="font-size: 0.8em;"><b>기간</b></span><br> 
													<c:if test="${not empty voteRead.v_Start&& not empty voteRead.v_End}">
														<span id="v_Start" class="username "> 
														<small><c:out value="${voteRead.v_Start}~" /></small>
														</span>
														<span id="v_End" class="username"> 
														<small><c:out value="${voteRead.v_End}" /></small>
														</span>
													</c:if> <c:if test="${empty voteRead.v_Start&& empty voteRead.v_End}">
														<span class="username"> <small>기간 미설정</small></span>
													</c:if></td>
												<td></td>
											</tr>
							
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="far fa-calendar-alt"></i>
										</span>
									</div>

									<input type="text" id="v_Period" name="v_Period" class="form-control float-right" value="${voteRead.v_Start}">




								</div>
								<input type="hidden" id="v_PeriodCheck" name="v_PeriodCheck" value=""> 
								<input type="hidden" id="v_Start" name="v_Start" value=""> 
								<input type="hidden" id="v_End" name="v_End" value="">

							</div>

							<br> 
							<input type="submit" id="submit" value="항목 수정" class="btn btn-danger btn-sm float-right" style="margin: 3px;"> 
							<input type="button" id="cancel" value="수정 취소" class="btn btn-danger btn-sm float-right" style="margin: 3px;" onclick="history.back(-1)">

						</div>
						</div>
						</div>
						</div>
						</form>
		</section>
		<!-- /.content -->
</div>
<!-- /.content-wrapper -->

