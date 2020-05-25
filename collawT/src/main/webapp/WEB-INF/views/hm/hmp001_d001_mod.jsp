<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*"
    pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<jsp:useBean  id="hmp001_d001VO"  class="project.hm.hmp001_d001.vo.Hmp001_d001VO"  scope="request"/> 
<%
   request.setCharacterEncoding( "utf-8" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript">
	function frm_update() {
		alert('${p0001VO.id}');
		
		var frmPro = document.frm;
		frmPro.method = "post";
		frmPro.action = "${contextPath}/hm/hmp001_d001/updateMember.do"; 
		frmPro.submit();
	}
	
	function frm_add() {
		var frmPro = document.frm;
		frmPro.method = "post";
		frmPro.action = "${contextPath}/hm/hmp001_d001/insertMember.do"; 
		frmPro.submit();
	}	
	
	// number인 경우 maxlength가 안먹기 때문에 함수를 통해 자리수 제어를 추가해 주어야 한다 
    function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    }	
   </script>
</head>
<body>
	<form name="frm" method="post" encType="UTF-8">
	ID :<input type="text" name="id" value="${hmp001_d001VO.id}" readonly><br>
	비밀번호 :<input type="text" name="pwd" value="${hmp001_d001VO.pwd}"><br>
	이름 :<input type="text" name="name" value="${hmp001_d001VO.name}"><br>
	이메일:<input type="text" name="email" value="${hmp001_d001VO.email}"><br>
	DATE:<input type="text" name="joinDate" value="${hmp001_d001VO.joinDate}"><br>
	NUM:<input type="text" name="num" value="${hmp001_d001VO.num}" placeholder='5자리' oninput='numberMaxLength(this);' maxlength='5'><br>
	
<c:if test="${command=='modSearch'}" > 	
	<input type="submit" name='submit' value="수정" onclick = "frm_update()">
	<input type='hidden' name='command' value='modUpdate'   /> 
</c:if>

<c:if test="${command=='addSearch'}" > 	
	<input type="submit" name='submit' value="추가" onclick = "frm_add()"> 
	<input type='hidden' name='command' value='addUpdate'   />
</c:if>	
	</form>
</body>
</html>