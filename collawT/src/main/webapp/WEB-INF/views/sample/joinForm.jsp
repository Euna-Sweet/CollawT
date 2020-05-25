<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MEMBER JOIN</h1>
	
	<form action="/sample/join" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="memId" /></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="memPw" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="memName" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Join" >
					<input type="reset" value="Cancel" >
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>