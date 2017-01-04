<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
<h3>Home Page</h3>
	<b>Welcome to kevals try to get proj running</b>
	<a href="/TravelHelper/travelsearch">Travel Search</a>
	<a href="/TravelHelper/scheduletravel">Schedule Travel</a>
	<a href="/TravelHelper/dashboard">Dash board</a>
	<a href="<c:url value="j_spring_security_logout" />" >Logout</a>
	
	<c:url var="logoutUrl" value="j_spring_security_logout"/>
	<form action="${logoutUrl}" method="post">
 	<input type="submit" value="Log out" />
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>