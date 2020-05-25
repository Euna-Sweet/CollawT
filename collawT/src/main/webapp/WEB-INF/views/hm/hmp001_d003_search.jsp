<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품 정보 출력창</title>
<style>
h1 {
text-align: center;
}
</style>

</head>
<body>
<h1>물품 정보 출력</h1>
<%
   request.setCharacterEncoding( "utf-8" );
%>
 <table border='1' width='800' align='center'>
   <tr align='center' bgcolor='#FFFF66'> 
     <td>제품ID</td>
     <td>제품명</td>
     <td>제품가격</td>
     <td>제품설명</td>
     <td>공급업체명</td>
     <td>수정</td>
</tr>

  <c:forEach var="prod" items="${searchList}" >	
	 <c:url var="url"  value="searchMod.do"  >
	   <c:param  name="p_mod_id" value="${prod.prod_id}" />
	 </c:url>     

     <tr align=center>
       <td>${prod.prod_id}</td>
       <td>${prod.prod_name}</td>
       <td>${prod.prod_price}</td>
       <td>${prod.prod_desc}</td>
       <td>${prod.vend_name}</td>
       <td><a href='${url}'> 수정 </a></td>
     </tr>
  </c:forEach>
</table>
</body>
</html>