<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
  $(function() {
    $("#checkJson").click(function() {
       // ajax로 JSON Data를 전달할 때는 객체 Data Type으로 전달해야 한다
	   var _jsonInfo ='{"name":[[1,2,3],[4,5,6],[7,8,9]]}';
	   
	   $.ajax({
         type:"post",
         async:true, 
         url:"${contextPath}/hm/hmp001_d002/jsonTest.do",
         data : _jsonInfo,
         contentType: 'application/json; charset=utf-8',
         success:function (data,textStatus){
        	 console.log(data.name[0][0]);
         },
         error:function(data,textStatus){
            alert("에러가 발생했습니다.");
         },
         complete:function(data,textStatus){
         }
       });  //end ajax	
   });
  });

 function fn_process(p_command){
	var v_url = '';
    var vp_id = $("#p_id").val();    
    
    if(p_command == 'before'){
    	p_command = 'search';
    	var before_id = $("#before_id").val();
    	$("#p_id").val(before_id);  
    	vp_id = $("#p_id").val(); 
    }
    
    if(p_command == 'after'){
    	p_command = 'search';
    	var after_id = $("#after_id").val();
    	$("#p_id").val(after_id); 
    	vp_id = $("#p_id").val(); 
    }    
    
	// command 설정
    $("#command").val(p_command);      
    
	var params = $("#frm").serialize();	
	
	// json Test Data
	//params = '{data:[[1,2,3], [4,5,6], [7,8,9]]}';
	
    // 추가 시 값 초기화 후 종료
    if(p_command == 'add'){
    	$('#frm')[0].reset();
    	return;
    }	    
    
    // 조회 시 validation
    if(p_command == 'search' && vp_id == ''){
    	alert("ID를 입력하세요");
    	return;
    }
    
    // 저장 시 validation
    var v_id = $("#id").val();
    if(p_command == 'save' && v_id == ''){
       alert("조회나 추가 후 저장을 선택해 주세요");
       return;
    }    
    
    if(p_command == 'search'){
    	v_url = "${contextPath}/hm/hmp001_d002/searchMember.do";
    }else if(p_command == 'save'){
    	v_url = "${contextPath}/hm/hmp001_d002/updateMember.do";
    }
    
    console.log(params);
    $.ajax({
       type:"post",
       async:true,  
       url:v_url,
       //contentType: 'application/json', /* 전달하는 Data가 json이 아니므로 필요없음 */
       data: params,
       //dataType:"json",
       success:function (data,textStatus){
    	   //var jsonInfo = JSON.parse(data);
           var jsonInfo = data;
           
           if(p_command == 'search'){
	           if(jsonInfo.error_yn == 'Y'){
	        	   alert(jsonInfo.error_text);
	        	   $("#frm")[0].reset();
	        	   return;
	           }
	           
	           $('#id').val(jsonInfo.id);  
	           $('#before_id').val(jsonInfo.before_id);
	           $('#after_id').val(jsonInfo.after_id);
	           $('#idCopy').val(jsonInfo.id);  
	           $('#pwd').val(jsonInfo.pwd);    
	           $('#name').val(jsonInfo.name);  
           }else if(p_command == 'save'){
	           if(jsonInfo.error_yn == 'Y'){
	        	   alert(jsonInfo.error_text);
	           }else{
        	       alert('저장되었습니다');
	           }
           }
       },
       error:function(request,textStatus,error){
          alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax
 }		
</script>
</head>
<body>
	<form name="frm" id="frm">
		<input type="text" name="p_id" id="p_id" /> 
		<input type="hidden" name="command" id="command" /> 
		<input type="button" id="btn_search" value="조회" onClick="fn_process('search')" /> 
		<input type="button" id="btn_add" value="추가" onClick="fn_process('add')" /> 
		<input type="button" id="btn_save" value="저장" onClick="fn_process('save')" />
		<input type="button" id="btn_before" value="이전" onClick="fn_process('before')" />
		<input type="button" id="btn_after" value="이후" onClick="fn_process('after')" />
		<table>
			<tr>
				<td>고객ID</td>
				<td><input type="text" name="id" id="id" maxlength=10></td>
				<input type="hidden" name="idCopy" id="idCopy">
				<input type="hidden" name="before_id" id="before_id">
				<input type="hidden" name="after_id" id="after_id">
			</tr>
			<tr>
				<td>고객PW</td>
				<td><input type="text" name="pwd" id="pwd" maxlength=10></td>
			</tr>
			<tr>
				<td>고객이름</td>
				<td><input type="text" name="name" id="name" maxlength=20></td>
			</tr>
		</table>
	</form>
	
	<a id="checkJson" style="cursor:pointer">JSON TEST</a><br><br>
</body>
</html>
