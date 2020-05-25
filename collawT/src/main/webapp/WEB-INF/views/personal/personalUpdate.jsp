<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- <script type="text/javascript" src="/ckeditor/ckeditor.js"></script> -->

<script src = "${path}/resources/ckeditor/ckeditor.js"></script>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />


<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
		 $(document).ready(function(){
			 getFileList();
			 periodSetting();
			 var p_Num = $("#p_Num").val();
			 var mem_Id = $("#mem_Id").val();
			 
			 //취소버튼 클릭시 해당 게시글로 화면으로 이동
			$("#cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "/personal/read?mem_Id="+mem_Id&"+p_Num="+p_Num;
			})
			
			//글쓰기 빈 값 안되게 검사
			$("#submit").click(function(){
				var p_i_Name = $("#p_i_Name").val();
			
				if(p_i_Name==''){
					alert("이슈명을 입력하세요");
					document.updateForm.p_i_Name.focus();
					return false; 
				}
				
				
				document.insertForm.submit();
				
			});
			
			 
			//캘린더 기능
			 $('#p_i_Period').daterangepicker({
				locale: { format: 'YYYY-MM-DD'}
			 })
			 
			$("#periodNull").click(function(){
				
				periodModify();
			})
			
			$('#p_i_Period').change(function(){
				
				periodModify();
				
				
			})
			
			$('#p_i_Period').click(function(){
				
				periodModify();
				
				
			})
			
			 
			//파일첨부 ajax
			$('#p_a_File').change(function(e){
				var formData = new FormData();
				var inputFile = $("input[name='p_a_File']");
				var files = inputFile[0].files;
				var p_Num = $("#p_Num").val();
				
				console.log(files);
			
				
				for(var i=0; i<files.length; i++){
					formData.append("p_a_File", files[i]);
					
				}
				formData.append("p_Num", p_Num)
				console.log(formData);
				
				$.ajax({
					url:'/personal/appendix/fileUpload',
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
				url : "${path}/personal/appendix/fileread?p_Num=${personalUpdate.p_Num}",
						
				success:function(result){
					var str="";
					if(result!=0){
						str+='<table class="table">';
						
		                    
						for(var i in result){
							str+='<tr><td><span id="p_a_RealName" name="p_a_RealName">'+result[i].p_a_RealName+'</span></td>';
							str+='<td><span id="p_a_Size" name="p_a_Size">'+(result[i].p_a_Size/1000)+'kb</span></td>';
							str+='<td><input type="hidden" id="p_a_Num" name="p_a_Num" value="'+result[i].p_a_Num+'"/></td>';
							str+='<td class="text-right py-0 align-middle">';
							str+='<div class="btn-group btn-group-sm" ><a href="javascript:fileUpdate('+result[i].p_a_Num+')" class="btn btn-danger" ><i class="fas fa-trash" ></i></a></div>';
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
		 
			//첨부파일 삭제
		  	function fileUpdate(p_a_Num){
			
					$.ajax({
						url : "/personal/appendix/fileUpdate",
						data : {"p_a_Num" : p_a_Num},
						type : 'post',
						success:function(){
							getFileList();
							
						}
					})
					
				
					
			}
			
			
			//수정 취소 시 파일 DB에서 지우기
		  	function fileDelete(p_Num){
		  		
				$.ajax({
					url : "/personal/appendix/fileCancel",
					data : {
						"p_Num" : p_Num
						},
					type : 'post',
					success:function(){
					
						window.history.go(-2);
					}
						
				})
				
				
		}

			
		  //캘린더 시작일/마감일 초기 세팅
			function periodSetting(){
				
				var p_i_Period = $("#p_i_Start").val()+' - '+ $("#p_i_End").val();
				document.updateForm.p_i_Period.value=p_i_Period;
				
				if($("#p_i_Start").val()=="" && $("#p_i_End").val()==""){
					
					$("#p_i_Period").css('background-color','#e9ecef');
					$("#p_i_Period").css('color','#e9ecef');
					$("#p_i_Period").attr('disabled',true)
					$("#periodNull").attr('checked',true)
					
					var today = new Date();
					var dd = today.getDate();
					var mm = today.getMonth()+1; //January is 0!
					var yyyy = today.getFullYear();

					if(dd<10) {
					    dd='0'+dd
					} 

					if(mm<10) {
					    mm='0'+mm
					} 

					today = yyyy+'/'+mm+'/'+dd;
				
				
					var p_i_Period = today+' - '+ today;
					document.updateForm.p_i_Period.value=p_i_Period;
			 	}					
					
					
				}
				
			
		  
		  
			function periodModify(){
				if($("#periodNull").is(":checked")==true){
					
					var p_i_Start = "";
					var p_i_End="";
					document.updateForm.p_i_Start.value=p_i_Start;
					document.updateForm.p_i_End.value=p_i_End;
					
					var p_i_PeriodCheck = "";
					document.updateForm.p_i_PeriodCheck.value=p_i_PeriodCheck;
					
					$("#p_i_Period").css('background-color','#e9ecef');
					$("#p_i_Period").css('color','#e9ecef');
					$("#p_i_Period").attr('disabled',true);
				}else{
					
					 $("#p_i_Period").css('background-color','#fff');
					$("#p_i_Period").css('color','#495057');
					$("#p_i_Period").attr('disabled',false)
					
					var p_i_Period = $("#p_i_Period").val();
					
					
						var p_i_PeriodCheck = $("#p_i_Period").val();
						document.updateForm.p_i_PeriodCheck.value=p_i_PeriodCheck;
						
						var p_i_Start = $("#p_i_PeriodCheck").val().substring(0,10);
						var p_i_End= $("#p_i_PeriodCheck").val().substring(13,23);
						
						
						document.updateForm.p_i_Start.value=p_i_Start;
						document.updateForm.p_i_End.value=p_i_End;
					
					
				}
			}
			

	</script>
	
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    

    <!-- Main content -->
    <form action="/personal/update" method="post" encType="UTF-8" name="updateForm">
    <section class="content-header">
	 <div class="container-fluid">
	  <div class="row mb-2">
     <div class="col-md-6">
     </div>
     
     <div class="col-md-6">
     </div>
     </div>
     </div>
     </section>
     
     <section class="content">
     <div class="row" id="row">
     <div class="col-md-6">
       <div class="card card-primary">
			<div class="card-body">
          		<div class="form-group">
          		
          		<div class="form-group">
           
          <input type="hidden" id = "p_Num" name="p_Num" value="${personalUpdate.p_Num}"/>
              
                <label for="p_i_Name">이슈명</label>
                <input type="text" id="p_i_Name" name="p_i_Name" value="${personalUpdate.p_i_Name}" class="form-control"/>
              </div>
              
              <div class="form-group">
             
                <label for="p_i_Content">이슈 내용</label>
                
                <textarea id="p_i_Content" name="p_i_Content" class="form-control" rows="4">
                <c:out value="${personalUpdate.p_i_Content}" /></textarea>
                <script>
                CKEDITOR.replace( 'p_i_Content', {
                	allowedContent:true,
                	toolbar :[['NewPage','Preview','Bold','Italic','Underline','Strike','-','Subscript','Superscript','-','-',
                	'JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Outdent','Indent','HorizontalRule','Link','Unlink','-',
                	'Find','Replace','SelectAll','Image','Youtube','Table','SpecialChar'],
                	'/',['Styles','Format','Font','FontSize','Undo','Redo'],['TextColor','BGColor'],['Cut','Copy','Paste','PasteText'],['Source']],
                	filebrowserImageUploadUrl: '/issue/imageUpload',
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
					</script>
              </div>
              <small>드래그 앤 드롭으로 이미지를 쉽게 추가할 수 있습니다.</small>
              </div>
              </div>
              </div>
              </div>
              
              
              
              <!--  파일 첨부 -->

		<div class="col-md-6">
		
		<div class="card card-info" >
          
            <div class="card-body">
            <label for="inputName">첨부파일</label>
            
            <a href="#"><button type="button" class="btn btn-block btn-default btn-xs float-right" onclick="oncilck=document.all.p_a_File.click()" style="width:50px; margin:1px">추가</button></a>
            <input type="file" id="p_a_File" name="p_a_File" style="display:none" multiple="multiple"/>
           <br>
               
				<div id="fileList"></div>

              <br>
              
            </div>
            
          </div>
              
              <div class="card card-info" >
              <div class="card-body">
              
 
              
              
    
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
                    <input type="text" id = "p_i_Period" name="p_i_Period" class="form-control float-right">
                  </div>
                  
                    <input type="hidden" id = "p_i_PeriodCheck" name="p_i_PeriodCheck" value="">
                    <input type="hidden" id = "p_i_Start" name="p_i_Start" value="${personalUpdate.p_i_Start}">
                    <input type="hidden" id = "p_i_End" name="p_i_End" value="${personalUpdate.p_i_End}">
                  
              </div>
                <!-- /.캘린더 -->
           <br>
                
           <input type="submit" id = "submit" value="이슈 수정" class="btn btn-danger btn-sm float-right" style="margin:3px;">
           <input type="button" id = "cancel" value="수정 취소" class="btn btn-danger btn-sm float-right" onclick="javascript:fileDelete(${personalUpdate.p_Num})" style="margin:3px;">     
                    
                 </div>
              </div>
              </section>
             
        
    <!-- /.content -->
     </form>
 
              </div>
              
  
  <!-- /.content-wrapper -->