<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="load-full-screen">

<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:46:00 GMT -->
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
	<!-- LIGHT -->
	<link rel="stylesheet" type="text/css" href="/TravelHelper/assets/css/color/blue.css" id="select-style">
	<link href="/TravelHelper/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	
	<link href="/TravelHelper/assets/css/light.css" rel="stylesheet" media="screen">
	<!-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/> -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600' rel='stylesheet' type='text/css'>
	<link rel="manifest" href="/TravelHelper/assets/pages/firebase/manifest.json">
	<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/pushMessaging.js"></script>
	<script type ="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/customNotify.js"></script>
	<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/parent.js"></script>
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
	.scheduledisabled {
  		opacity: 0.65; 
  		cursor: not-allowed;
  		
	}		
	</style>
</head>
<body class="load-full-screen">

<!-- BEGIN: SITE-WRAPPER -->
<div class="site-wrapper">
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
	<div class="clearfix"></div>
	<!-- BEGIN: SEARCH SECTION -->
	<div class="row">
		<div class="container">
			<div class="col-md-8 col-sm-6 text-center">
				<div>
					<div class="hotel-tagline text-center">
						<h3>Welcome To</h3>
						<h1>Travel Helper</h1>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="room-check">
					<h4 class="text-center">TAKE A DRIVE</h4>
					<div class="room-check-body">
						<div id="enablePush">
							<button class="js-push-button btn-primary btn-md" style="padding:5px;margin-left: 50px;margin-bottom: 20px;">Enable Push Messages</button>
							<!-- <button class="btn-primary" onclick="sendGCMPush();">Call NOtify</button> -->
						</div>
						
						<div id="confirmationBox" class="alert alert-info" style="display: none;font-weight: bold;">
						</div>
						
						<form id="scheduleTravelForm" style="display: none;">
							<label>Pick Up Location</label>
							<div class="input-group">
								<input type="text" name="source" id="autocomplete" class="form-control" placeholder="E.g Long beach" required>
								<span class="input-group-addon"><i class="fa fa-map-marker fa-fw"></i></span>
							</div>
							<div class="alert alert-danger sourceerrmsg" role="alert" style="display: none;">Enter valid source location</div>
							<label>Drop Location</label>
							<div class="input-group">
								<input type="text" name="destination" id="autocompleteDest" class="form-control" placeholder="E.g Los angeles" required>
								<span class="input-group-addon"><i class="fa fa-map-marker fa-fw"></i></span>
							</div>
							<div class="alert alert-danger destinationerrmsg" role="alert" style="display: none;">Enter valid destination location</div>
							<label>Select Drive</label>
							<select class="selectpicker" name="travel_type" id="travelDrive">
									<option selected="selected">DRIVING</option>
									<option>TRANSIT</option>
									<option>WALKING</option>
									<option>BICYCLING</option>
									<option>LYFT</option>
									<option>UBERX</option>
							</select>
							
							<label>Select Date</label>
					        <div class='input-group'>
					         <input type="date" class="form-control" name="date" id="scheduleDate">
					        </div>
							<div class="alert alert-danger dateerrmsg" role="alert" style="display: none;">Enter valid date</div>

					        <label>Select Time</label>
					        <div class='input-group'>
					         <input type="time" class="form-control" name="time" id="scheduleTime">
					        </div>
							<div class="alert alert-danger timeerrmsg" role="alert" style="display: none;">Enter valid time</div>
							
							<label>Notify Before (minutes)</label>
							<select class="selectpicker" name="notify_before" id="notifyBefore">
									<option selected="selected">5</option>
									<option>10</option>
									<option>15</option>
									<option>30</option>
							</select>
							
							<div class="text-center">
								<!-- <input type="submit" name="submit" id="submitAddr" class="submitAddr">Search Results</input> -->
								<button type="submit" id="scheduleTravelSubmit">Schedule</button>
								<img alt="" src="/TravelHelper/assets/images/loading2.gif" id="scheduleprogress" style="display: none;" ></img>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END: SEARCH SECTION -->
<!-- START: FOOTER -->
<section id="footer">
	<footer>
		<div class="row sm-footer" style="padding: 20px;">
			<div class="container">
				<div class="col-md-4 col-sm-4 footer-about-box">
					<h4>Travel Helper</h4>
					<p>Travel Application can be for deciding on best travel mode and scheduling travel.</p>
				</div>
				<div class="col-md-4 col-sm-4 contact-box">
					<h4>Contact Us</h4>
					<p><i class="fa fa-home"></i> Long Beach CA</p>
					<p><i class="fa fa-envelope-o"></i> email@travelhelper.com</p>
					<p><i class="fa fa-phone"></i> +1 123-456-7890</p>
				</div>
				<div class="clearfix visible-sm-block"></div>
				
				<div class="col-md-4 col-sm-4 footer-subscribe">
					<h4>Social Presence</h4>
					<p>Don't miss any update.</p>
					<p class="social-media">
						<a href="#"><i class="fa fa-facebook"></i></a>
						<a href="#"><i class="fa fa-twitter"></i></a>
						<a href="#"><i class="fa fa-google-plus"></i></a>
						<a href="#"><i class="fa fa-instagram"></i></a>
					</p>
				</div>
			</div>
		</div>
		<!-- <div class="clearfix"></div> -->
		<div class="row sm-footer-nav text-center">
			<!-- <p>
				<a href="#">HOME</a>
				<a href="#">CARS</a>
				<a href="#">SERVICE</a>
				<a href="#">GALLERY</a>
			</p> -->
			<p class="copyright">
				&copy;	2017 TRAVELHELPER ALL RIGHTS RESERVED
			</p>
			<div class="go-up">
				<a href="#"><i class="fa fa-arrow-up"></i></a>
			</div>
		</div>
	</footer>
</section>
<!-- END: FOOTER -->

</div>
<!-- END: SITE-WRAPPER -->
<!-- Load Scripts -->

<script src="/TravelHelper/assets/js/respond.js"></script>
<script src="/TravelHelper/assets/js/jquery.js"></script>
<script src="/TravelHelper/assets/plugins/owl.carousel.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/TravelHelper/assets/js/jquery-ui.min.js"></script>
<script src="/TravelHelper/assets/js/bootstrap-select.min.js"></script>
<script src="/TravelHelper/assets/plugins/wow.min.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/plugins/supersized.3.1.3.min.js"></script>
<script src="/TravelHelper/assets/js/js.js"></script>
<!-- <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"> </script> -->
<script type="text/javascript">
function initAutocomplete() {
	
	var options = {types: ['geocode']};
	var sourceinput = document.getElementById('autocomplete');
	autocomplete = new google.maps.places.Autocomplete(sourceinput,options);
    autocomplete.addListener('place_changed', fetchGeoCodes);
    
    var destinput = document.getElementById('autocompleteDest');
    destcomplete = new google.maps.places.Autocomplete(destinput,options);
    destcomplete.addListener('place_changed', fetchDestGeoCodes);
 }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCin3LHSQnBxk5b1uCGbnzC5dT8uL93JRw&libraries=places&callback=initAutocomplete"
        async defer></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/metro.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/uberFunctions.js"></script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/lyftFunctions.js"></script>
<!-- <script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/loginvalidation.js"></script>  -->   
<script type="text/javascript">  
			
			jQuery(function($){
				"use strict";
				$.supersized({
				
					//Functionality
					slideshow               :   1,		//Slideshow on/off
					autoplay				:	1,		//Slideshow starts playing automatically
					start_slide             :   1,		//Start slide (0 is random)
					random					: 	0,		//Randomize slide order (Ignores start slide)
					slide_interval          :   10000,	//Length between transitions
					transition              :   1, 		//0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
					transition_speed		:	500,	//Speed of transition
					new_window				:	1,		//Image links open in new window/tab
					pause_hover             :   0,		//Pause slideshow on hover
					keyboard_nav            :   0,		//Keyboard navigation on/off
					performance				:	1,		//0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)
					image_protect			:	1,		//Disables image dragging and right click with Javascript

					//Size & Position
					min_width		        :   0,		//Min width allowed (in pixels)
					min_height		        :   0,		//Min height allowed (in pixels)
					vertical_center         :   1,		//Vertically center background
					horizontal_center       :   1,		//Horizontally center background
					fit_portrait         	:   1,		//Portrait images will not exceed browser height
					fit_landscape			:   0,		//Landscape images will not exceed browser width
					
					//Components
					navigation              :   1,		//Slideshow controls on/off
					thumbnail_navigation    :   1,		//Thumbnail navigation
					slide_counter           :   1,		//Display slide numbers
					slide_captions          :   1,		//Slide caption (Pull from "title" in slides array)
					slides 					:  	[		//Slideshow Images 
														{image : '/TravelHelper/assets/images/car-slide1.jpg', title : 'Slide 2'},
														{image : '/TravelHelper/assets/images/car-slide2.jpg', title : 'Slide 1'}
												]
												
				}); 
		    });
		    
</script>
</body>

<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:48:43 GMT -->
</html>