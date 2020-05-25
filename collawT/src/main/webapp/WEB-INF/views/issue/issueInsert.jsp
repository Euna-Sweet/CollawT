<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src = "${path}/resources/ckeditor/ckeditor.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
#row {margin-left:0px !important; margin-right:0px !important}

</style>

<script type="text/javascript">
	$(document).ready(function() {
		getFileList();
		periodSetting();
		var i_Num = $("#i_Num").val();
		var c_Id = $("#c_Id").val();
		

		
		//글쓰기 빈 값 안되게 검사
		$("#submit").click(function(){
			var i_Name = $("#i_Name").val();
		
			
			if(i_Name==''){
				alert("이슈명을 입력하세요");
				document.insertForm.i_Name.focus();
				return false; 
			}	else if (CKEDITOR.instances.i_Content.getData() == '') {
				alert("내용을 입력해주세요");
				return false;
			}
			
			periodSetting();

			document.insertForm.submit();
			
		});
		

		
		//캘린더 시작일/마감일 구하기/기간 미설정 시
		
		$("#periodNull").click(function(){
			periodSetting();
		})
		
		
		//캘린더 기능
		 $('#i_Period').daterangepicker({
			locale: { format: 'YYYY-MM-DD'}
		 })
		 
		//파일첨부 ajax
		$('#a_File').change(function(e){

			var formData = new FormData();
			var inputFile = $("input[name='a_File']");
			var files = inputFile[0].files;
	
			for(var i=0; i<files.length; i++){
				formData.append("a_File", files[i]);
				console.log(files[i].size);
				console.log(files[i].name);
				if(files[i].size > 52000000){
					alert("한 파일당 52MB까지만 첨부 가능합니다. 해당 파일의 용량을 확인해주세요. (파일 이름 : "+files[i].name+")");
					return false;
				}
			}
			
			formData.append("i_Num", i_Num)
			
			$.ajax({
				url:'/appendix/fileUpload',
				processData:false,
				contentType:false,
				data:formData,
				type:'POST',
				success:function(result){
					getFileList();
				}
			});
		});
	})


	//첨부된 파일 리스트
		 function getFileList(){
			$.ajax({
				type:"get",
				url : "${path}/appendix/fileread?i_Num=${i_Num.NEXTVAL}",
						
				success:function(result){
					var str="";
					if(result!=0){
						str+='<table class="table">';
						
		                    
						for(var i in result){
							str+='<tr><td><span id="a_RealName" name="a_RealName">'+result[i].a_RealName+'</span></td>';
							str+='<td><span id="a_Size" name="a_Size">'+(result[i].a_Size/1000)+'kb</span></td>';
							str+='<td><input type="hidden" id="a_Num" name="a_Num" value="'+result[i].a_Num+'"/></td>';
							str+='<td class="text-right py-0 align-middle">';
							str+='<div class="btn-group btn-group-sm" ><a href="javascript:fileUpdate('+result[i].a_Num+')" class="btn btn-danger" ><i class="fas fa-trash" ></i></a></div>';
							str+='</td></tr>'
						}
						
						str+='</tbody></table>'
						
					}else{
						str+='<p style="text-align:center;"><small><br><br>첨부된 파일이 없습니다.</small></p>'
						
					}
					$("#fileList").html(str);
				}
			})
			
			
			
			
		}
	
	//첨부파일 리스트에서 삭제
  	function fileUpdate(a_Num){
	
			$.ajax({
				url : "/appendix/fileUpdate",
				data : {"a_Num" : a_Num},
				type : 'post',
				success:function(){
					getFileList();
					
				}
			})
			
	}
	
	//작성 취소 시 파일 DB에서 지우기
  	function fileDelete(i_Num){
  		
		$.ajax({
			url : "/appendix/fileDelete",
			data : {
				"i_Num" : i_Num
				},
			type : 'post',
			success:function(){
			
				window.history.go(-2);
			}
				
		})
		
		
}
	
	
	function periodSetting(){
		if($("#periodNull").is(":checked")==true){
			var i_Start = "";
			var i_End="";
			document.insertForm.i_Start.value=i_Start;
			document.insertForm.i_End.value=i_End;
			
			var i_PeriodCheck = "";
			document.insertForm.i_PeriodCheck.value=i_PeriodCheck;
			
			$("#i_Period").css('background-color','#e9ecef');
			$("#i_Period").css('color','#e9ecef');
			$("#i_Period").attr('disabled',true)
		}else{
			$("#i_Period").css('background-color','#fff');
			$("#i_Period").css('color','#495057');
			$("#i_Period").attr('disabled',false)
			
			var i_PeriodCheck = $("#i_Period").val();
			document.insertForm.i_PeriodCheck.value=i_PeriodCheck;
			
			var i_Start = $("#i_PeriodCheck").val().substring(0,10);
			var i_End= $("#i_PeriodCheck").val().substring(13,23);
			document.insertForm.i_Start.value=i_Start;
			document.insertForm.i_End.value=i_End;
		}
	}
	
  	
</script>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <form name = insertForm action="/project/issue/insert" method="post" encType="UTF-8">
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
      <div class="card card-primary">
        
        <div class="card-body">
        	<div class="form-group">
          
              <div class="form-group">
             	<input type="hidden" id = "i_Num" name="i_Num" value="${i_Num.NEXTVAL}">
                <label for="inputName">이슈명</label>
                <input type="text" id = "i_Name" name="i_Name" class="form-control">
              </div>
              
              <div class="form-group">
             
                <label for="inputDescription">이슈 내용</label>
                
                <textarea name="i_Content" id="i_Content" class="form-control" style="width:100px;">              </textarea>
                <script>
           
                //CK에디터 적용
					CKEDITOR.replace( 'i_Content', {
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
			 	 
			 	CKFinder.setupCKEditor( i_Content, '/ckfinder/' );
			 	 
	
 				       
					</script>
					
              </div>
              <small>드래그 앤 드롭으로 이미지를 쉽게 추가할 수 있습니다.</small>
             
              </div>
        
        </div>
        
        <!-- /.card-body -->
      </div>
      <!-- /.card -->
</div>

<!--  파일 첨부 -->

<div class="col-md-6">

<div class="card card-info" >
          
            <div class="card-body">
            <label for="inputName">첨부파일</label>
            
            <a href="#"><button type="button" class="btn btn-block btn-default btn-xs float-right" onclick="oncilck=document.all.a_File.click()" style="width:50px; margin:1px">추가</button></a>
            <input type="file" id="a_File" name="a_File" style="display:none" multiple="multiple"/>
           <br>
               
				<div id="fileList"></div>

              <br>
              
              
          
          
            </div>
            
          </div>
         <div class="card card-info" >
          
            <div class="card-body">
             <div class="form-group">
              
                <label for="inputStatus">담당자</label>
                
               <select class="form-control custom-select" id="chargerSelect" name="chargerSelect">
               	<option selected disabled>담당자를 지정하세요</option>
                  <c:forEach var="comemList" items="${comemList}" >
                  <option >${comemList.MEM_ID}(${comemList.MEM_NAME})</option>
                  </c:forEach>
                </select>
                
                
              </div>
              <div class="form-group">
              <span id="id_check" name="id_check"
				style="font-size: 0.9em; line-height: 1.0; color: #a1a1a1; ">
			
				담당자 목록을  확인하세요.</span>
              <div id="chargerList" class = "form-control" style="height: 100px; width: 100%; white-space: pre-line; margin-bottom: 5px; overflow:auto;"></div>
             
				</div>
				<div id="chargerForm"></div>
			<!-- 이슈 담당자 관련 스크립트 따로 놨어요 -->
			<script type="text/javascript">
			//이슈 담당자 부분
			var chargedArray = new Array();
			var chargedCount = 0;
			
			 $('#chargerSelect').change(function(event) {
				 var mem_Id = $('#chargerSelect').val();
				 var idSearch = mem_Id.split("(");
				 var realmem_Id = idSearch[0];
						 
				 chargedArray.push(mem_Id);
				 console.log("chargedArray : "+chargedArray);
				 
				 var ok = true;
					 for(var i=0; i<chargedArray.length-1; i++){
						 if(mem_Id == chargedArray[i]){
							 alert("동일한 아이디를 여러 번 초대할 수 없습니다.")
							 chargedArray.pop();
							 console.log(chargedArray);
						  	ok=false;
						 }
			
				 		}
					 if(ok==true){
						 
						 chargedCount++;
						 
						$('#chargerList').append("<span id= 'chargerspan"+chargedCount+"' name='chargerspan'>"+mem_Id+"<a id ='chargerdelete["+chargedCount+"]' name = 'chargerdelete' onclick='deleteCharger("+chargedCount+")'>X</a></span>");
						$('#chargerForm').append("<input type='hidden' id='hidden_Id"+chargedCount+"' name='comem_Id' value='"+realmem_Id+"'>");
					 }
			 })
			
			
			//이슈 담당자 x버튼 누르면 삭제
			function deleteCharger(chargedCount){
			
			 var chargerspan = $('#chargerspan'+chargedCount).text();
			 var splitResult = chargerspan.split("X");
			 var Realchargerspan = splitResult[0];
			 
			 
			 $('#chargerspan'+chargedCount).remove();
			 $('#hidden_Id'+chargedCount).remove();
			 
			 var index = chargedArray.indexOf(Realchargerspan);
			 
			 if (index > -1) {
				 chargedArray.splice(index,1);
				}
			 
			 console.log("changed : "+ chargedArray);
			 
			
			}
					
			</script>
				

				
             
            
             
                <%-- <label for="inputStatus">협업공간명</label>
               <select class="form-control custom-select">
               	<option selected value="${c_Id}" label="${c_Id}"></option>
                 <c:forEach var="coList" items="${coList}" >
                 <option id="c_Id" value="${coList.C_ID}" label="${coList.C_NAME}"></option>
               </c:forEach> --%>
                  
                
        <!--협업공간ID -->
             <input id="c_Id" name="c_Id" type="hidden" value="${c_Id}"/>
		<!-- 이슈그룹번호는 디폴트로 '1'으로 설정 -->
			<input name="ig_Num" id="ig_Num" type="hidden" value="1"><br>     
               
               
               
             
               
                
               <!-- 캘린더 -->
                <div class="form-group">
                <div class="row">
                	<div class="col-8">
                   <label for="inputStatus">기간</label>
                   </div>
             
                   <div class="col-4" >
                   <div class="icheck-danger" style="text-align:right; ">
                    
                        <input type="checkbox" value="" id="periodNull" name="periodNull">
                       <label for="periodNull"><small>기간 미설정</small></label>
                      </div>
                      </div>
                      </div>
                      
                      
                      <div class="input-group">
                    <div class="input-group-prepend">
                      <span class="input-group-text">
                        <i class="far fa-calendar-alt"></i>
                      </span>
                    </div>
                    <input type="text" id = "i_Period" name="i_Period" class="form-control float-right">
                    
              </div>

                 
              
                    
                    <input type="hidden" id = "i_PeriodCheck" name="i_PeriodCheck" value="">
                    <input type="hidden" id = "i_Start" name="i_Start" value="">
                    <input type="hidden" id = "i_End" name="i_End" value="">
                  
                </div>
                <!-- /.캘린더 -->
                
                

				<input type="submit" id = "submit" value="이슈 작성" class="btn btn-danger btn-sm float-right" style="margin:3px;">
          
         		<input type="button" id = "cancel" value="작성 취소" class="btn btn-danger btn-sm float-right" onclick="javascript:fileDelete(${i_Num.NEXTVAL})" style="margin:3px;">
          
          </form>
              </div>
              
    </section>
    <!-- /.content -->
   
    <form action = "/project/issue/insert" method="post" id="cancelForm">
    <input type="hidden" name="c_Id" value="${c_Id}">
    <input type="hidden" id = "i_Num" name="i_Num" value="${i_Num.NEXTVAL}">
    </form>
    
    
    <input type="hidden" name="c_Id" value="${c_Id}">
    
   

  </div>
  <!-- /.content-wrapper -->


