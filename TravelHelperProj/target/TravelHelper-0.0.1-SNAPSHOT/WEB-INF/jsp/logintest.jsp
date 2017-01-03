<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
		<div>
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	 <form name="f" method="post" action="<c:url value='j_spring_security_check' />" >
                user: <input  type="text" name='j_username' />  
        		pass: <input type="password"  name='j_password'/>
                <input type="submit" name="submit" value="submit" />
     </form>     
</body>
</html>