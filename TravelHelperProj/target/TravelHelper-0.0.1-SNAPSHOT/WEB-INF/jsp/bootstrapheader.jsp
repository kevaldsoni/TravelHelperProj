<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="LimpidThemes">
	
	<title>Travel Helper Application</title>
	<link rel="shortcut icon" href="/TravelHelper/assets/travelstatic/favicon/favicon.ico" type="image/x-icon">
    <!-- STYLES -->
	<link href="/TravelHelper/assets/css/animate.min.css" rel="stylesheet">
	<link href="/TravelHelper/assets/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="/TravelHelper/assets/css/owl.carousel.css" rel="stylesheet">
	<link href="/TravelHelper/assets/css/owl-carousel-theme.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/TravelHelper/assets/css/flexslider.css" rel="stylesheet" media="screen">
	<link href="/TravelHelper/assets/css/style.css" rel="stylesheet" media="screen">
	<link href="/TravelHelper/assets/css/color/blue.css" rel="stylesheet" type="text/css"  id="select-style">
	<link href="/TravelHelper/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="/TravelHelper/assets/css/light.css" rel="stylesheet" media="screen">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600' rel='stylesheet' type='text/css'>
	<style type="text/css">
	
	.navbar-inverse .navbar-nav > li > a {
  		color: #fff;
	}
	.navbar-inverse .navbar-text {
  		color: #9d9d9d;
	}
	.navbar-inverse .navbar-brand {
  		color: #fff;
	}
	.navbar-inverse .navbar-nav > li > a:hover,
	.navbar-inverse .navbar-nav > li > a:focus {
  		color: #9d9d9d;
  		background-color: transparent;
	}
	
	.navbar-inverse .navbar-nav>.active>a, .navbar-inverse .navbar-nav>.active>a:focus, .navbar-inverse .navbar-nav>.active>a:hover{
		background-color: inherit;
	}
			
	</style>
</head>
<nav class="navbar navbar-inverse" style="background-color: #2F7AF8;border: none;">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#"><span style="color:#fff;">Travel Helper</span></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/TravelHelper/welcome">Home</a></li>
        <li><a href="/TravelHelper/travelsearch">Travel Search</a></li>
        <li><a href="/TravelHelper/assets/pages/firebase/scheduleTravel.jsp">Schedule Travel</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="/TravelHelper/dashboard">Dashboard<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/TravelHelper/travelhistory">Travel Search Dashboard</a></li>
            <li><a href="/TravelHelper/schedulehistory">Schedule History Dashboard</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="#"><span class="glyphicon glyphicon-user" style="margin-right:5px;"></span>Welcome <sec:authentication property="name"></sec:authentication></a></li>
      	<li><a href="<c:url value="/logout"/> "><span class="glyphicon glyphicon-log-out" style="margin-right:5px;"></span>Logout</a></li>
      </ul>
     <%--  <c:url var="logoutUrl" value="j_spring_security_logout"></c:url>
      <c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>
      <form action="${logoutUrl}" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="submit" value="Log out">
	      <ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	      </ul>
      </input>
      </form> --%>
    </div>
  </div>
</nav>