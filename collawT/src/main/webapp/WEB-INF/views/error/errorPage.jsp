<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
<div style="text-align:center; margin-top:100px">
	<img src="${contextPath}/resources/img/error_txt1.png">
</head>
<body>
	
	<%-- <c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
	<c:out value="${error.STATUS_CODE }"> </c:out> --%>
	<p><a href = "${contextPath}/main">메인페이지로 이동합니다</a></p><!-- </p> -->
	 <%-- </c:if>
	<c:if test="${requestScope['javax.servlet.error.stauts_code'] == 500}">
	<p>요청을 수행할 수 없습니다. 
	<a href = "${contextPath}/main">메인이동</a></p>
	</c:if>  --%>
</body>
</html>