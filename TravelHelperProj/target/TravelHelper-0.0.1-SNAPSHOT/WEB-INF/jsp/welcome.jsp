<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Antic+Slab);

html,body {
  height:100%;
}

h1 {
  font-family: 'Antic Slab', serif;
  font-size:80px;
  color:#DDCCEE;
}

.lead {
	color:#DDCCEE;
}


/* Custom container */
.container-full {
  margin: 0 auto;
  width: 100%;
  min-height:100%;
  background-color:#110022;
  color:#eee;
  overflow:hidden;
}

.container-full a {
  color:#efefef;
  text-decoration:none;
}

.v-center {
  margin-top:7%;
}
  

</style>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<h3>Home Page</h3>
	<b>Welcome to kevals try to get proj running</b>
	<a href="/TravelHelper/travelsearch">Travel Search</a>
	<!-- <a href="/TravelHelper/scheduletravel">Schedule Travel</a> -->
	<a href="/TravelHelper/assets/pages/firebase/scheduleTravel.jsp">Schedule Travel</a>
	<a href="/TravelHelper/dashboard">Dash board</a>
	<a href="<c:url value="j_spring_security_logout" />" >Logout</a>
	<%
	String access_token = request.getParameter("access_token");
	%>
	Code : <%=access_token%>
	<c:url var="logoutUrl" value="j_spring_security_logout"/>
	<form action="${logoutUrl}" method="post">
 	<input type="submit" value="Log out" /><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
<!-- 	<div class="container-full">
    <div class="row">
        <div class="col-lg-12 text-center v-center">
             <h1 class="">Travel Helper</h1>

            <p class="lead">A sign-up page example for Bootstrap 3</p>
            <br class="">
            <br class="">
            <br class="">
            <form class="col-lg-12">
                <div class="input-group" style="width:340px;text-align:center;margin:0 auto;">
                    <input type="text" class="form-control input-lg" title="Don't worry. We hate spam, and will not share your email with anyone." placeholder="Enter your email address"> <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="button">OK</button></span>

                </div>
            </form>
        </div>
    </div>
    /row
    <div class="row">
        <div class="col-lg-12 text-center v-center" style="font-size:39pt;"> <a href="#" class=""><i class="icon-google-plus"></i></a>  <a href="#" class=""><i class="icon-facebook"></i></a> 
            <a href="#" class=""><i class="icon-twitter"></i>
                </a> <a href="#" class=""><i class="icon-github"></i></a>  <a href="#" class=""><i class="icon-pinterest"></i></a>

        </div>
    </div>
    <br class="">
    <br class="">
    <br class="">
    <br class="">
    <br class="">
</div> -->
<!-- /container full -->
<div class="container">
    <hr class="">
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class=""><a href="/TravelHelper/travelsearch">Travel Search</a></h3>
                </div>
                <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra
                    varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt
                    condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis
                    nisi. Sed ac orci quis tortor imperdiet venenatis. Duis elementum auctor
                    accumsan. Aliquam in felis sit amet augue.</div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class=""><a href="/TravelHelper/assets/pages/firebase/scheduleTravel.jsp">Schedule Travel</a></h3>
                </div>
                <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra
                    varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt
                    condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis
                    nisi. Sed ac orci quis tortor imperdiet venenatis. Duis elementum auctor
                    accumsan. Aliquam in felis sit amet augue.</div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class=""><a href="/TravelHelper/dashboard">Dash board</a></h3>
                </div>
                <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra
                    varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt
                    condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis
                    nisi. Sed ac orci quis tortor imperdiet venenatis. Duis elementum auctor
                    accumsan. Aliquam in felis sit amet augue.</div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>

</body>
</html>