<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1> MEMBER JOIN OK </h1>
	
	ID : ${memberVO.memId} <br />
	PW : ${memberVO.memPw} <br />
	Mail : ${memberVO.memName} <br />
	
	<P>  The time on the server is ${serverTime}. </P>
	
</body>
</html>
