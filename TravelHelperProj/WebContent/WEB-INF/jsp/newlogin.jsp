<!DOCTYPE html>
<html class="load-full-screen">

<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:46:00 GMT -->
<head>
	
	<jsp:include page="header.jsp"></jsp:include>
</head>
<body class="load-full-screen">

<!-- BEGIN: SITE-WRAPPER -->
<div class="site-wrapper">
	<div class="clearfix"></div>
	<!-- BEGIN: SEARCH SECTION -->
	<div class="row">
		<div class="container">
			<div class="col-md-4 col-sm-4">
				<div>
					<div class="hotel-tagline text-center">
						<h3>Welcome To</h3>
						<h1>Travel Helper</h1>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-sm-8">
					
	<!-- BEGIN: SEARCH SECTION -->
			<div id="loginbox" class="col-md-6 col-sm-6" style="padding: 10px;float:right;">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Sign In</div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
                    </div>     

                    <div style="padding:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" method="post" action="<c:url value='j_spring_security_check' />" >
                           <%
								String message=(String)request.getAttribute("errormessage");
                           		if(message!=null && message.length()>0){
							%>
							<div class="alert alert-danger">
  							<strong>Authentication Failed</strong>
							</div>
							   <%
								}else if((String)request.getAttribute("successMessage") != null){
							   %>
							   	<div class="alert alert-success">
  								<strong>${successMessage}</strong>
								</div>
							   <% } %>
   		                   <div style="margin-bottom: 25px" class="form-group">
   		                   <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                               <input id="login-username" type="text" class="form-control" name='j_username'  placeholder="username" />  
        					</div>
        					<div class="messageContainer"></div>
                            </div>
        					                        
                            <div style="margin-bottom: 25px" class="form-group">
                            <div class="input-group">
                                 <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                               	 <input id="login-password" type="password" class="form-control" name='j_password' placeholder="password"/>
                            </div>
                            
                            </div>
 							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
                                    <div class="col-sm-12 controls">
                                      <button id="btn-login" type="submit" class="btn btn-success">Login</button>
                                    </div>
                            </div>
							<input type="hidden" name="formtype" value="login">
                            <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                            Don't have an account! 
                                        <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                            Sign Up Here
                                        </a>
                                        </div>
                                    </div>
                                </div>    
                            </form>     
					 </div>                     
                    </div>  
        </div>
        <div id="signupbox" style="display:none;margin-top: 10px;" class="mainbox col-md-8 col-sm-8 col-sm-offset-2 col-md-offset-3 ">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form" method="post" action="signupform">
                                
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">First Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="firstName" placeholder="Enter First Name">
                                    </div>
                                        <div class="signupMessageContainer"></div>
                                </div>
                                  
                                <div class="form-group">
                                    <label for="lastname" class="col-md-3 control-label">Last Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="lastName" placeholder="Enter Last Name">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Username</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="username" placeholder="Enter Username">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Password</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="password" placeholder="Password">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="confirmpassword" class="col-md-3 control-label">Confirm Password</label>
                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="confirmpassword" placeholder="Confirm Password">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Email</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="email" placeholder="Enter Email Address">
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="phone" class="col-md-3 control-label">Phone</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="contactNumber" placeholder="Phone Number">
                                    </div>
                                </div>
                                
                                <!-- <div class="form-group">
                                    <label for="dob" class="col-md-3 control-label">Date of Birth</label>
                                    <div class="col-md-9">
                                      <input type="date" class="form-control" name="dob" placeholder="DateOfBirth" maxlength="10">
                                    </div>
                                </div> -->
                                
                                <div class="form-group">
                                    <label for="address1" class="col-md-3 control-label">Street Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="addrStreetName" placeholder="Enter Street Name">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="address2" class="col-md-3 control-label">Apartment Details</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="addrAptNo" placeholder="Enter Apartment Details">
                                    </div>
                                </div>    
                                
                                <div class="form-group">
                                    <label for="city" class="col-md-3 control-label">City</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="addrCity" placeholder="Enter City">
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="state" class="col-md-3 control-label">State</label>
                                    <div class="col-md-9">
									<select class="form-control" name="addrState">
										<option value="AL">Alabama</option>
										<option value="AK">Alaska</option>
										<option value="AZ">Arizona</option>
										<option value="AR">Arkansas</option>
										<option value="CA">California</option>
										<option value="CO">Colorado</option>
										<option value="CT">Connecticut</option>
										<option value="DE">Delaware</option>
										<option value="DC">District Of Columbia</option>
										<option value="FL">Florida</option>
										<option value="GA">Georgia</option>
										<option value="HI">Hawaii</option>
										<option value="ID">Idaho</option>
										<option value="IL">Illinois</option>
										<option value="IN">Indiana</option>
										<option value="IA">Iowa</option>
										<option value="KS">Kansas</option>
										<option value="KY">Kentucky</option>
										<option value="LA">Louisiana</option>
										<option value="ME">Maine</option>
										<option value="MD">Maryland</option>
										<option value="MA">Massachusetts</option>
										<option value="MI">Michigan</option>
										<option value="MN">Minnesota</option>
										<option value="MS">Mississippi</option>
										<option value="MO">Missouri</option>
										<option value="MT">Montana</option>
										<option value="NE">Nebraska</option>
										<option value="NV">Nevada</option>
										<option value="NH">New Hampshire</option>
										<option value="NJ">New Jersey</option>
										<option value="NM">New Mexico</option>
										<option value="NY">New York</option>
										<option value="NC">North Carolina</option>
										<option value="ND">North Dakota</option>
										<option value="OH">Ohio</option>
										<option value="OK">Oklahoma</option>
										<option value="OR">Oregon</option>
										<option value="PA">Pennsylvania</option>
										<option value="RI">Rhode Island</option>
										<option value="SC">South Carolina</option>
										<option value="SD">South Dakota</option>
										<option value="TN">Tennessee</option>
										<option value="TX">Texas</option>
										<option value="UT">Utah</option>
										<option value="VT">Vermont</option>
										<option value="VA">Virginia</option>
										<option value="WA">Washington</option>
										<option value="WV">West Virginia</option>
										<option value="WI">Wisconsin</option>
										<option value="WY">Wyoming</option>
									</select>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="zipcode" class="col-md-3 control-label">Zip Code</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="addrZip" placeholder="Enter Zip Code">
                                    </div>
                                </div>
                                
                                
                                <input type="hidden" name="roleId" value="1">
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i>Sign Up</button>
                                         
                                    </div>
                                </div>
                              
                            </form>
                         </div>
                    </div>
			 </div>

			</div>
		</div>
	</div>
	<!-- END: SEARCH SECTION -->
<!-- START: HOW IT WORK -->
<section id="how-it-work">
		<div class="row work-row">
			<div class="container">
				<div class="section-title text-center">
					<h2>HOW IT WORKS?</h2>
					<h4>SEARCH - SELECT - BOOK</h4>
					<div class="space"></div>
					<p>
						Lorem Ipsum is simply dummy text. Lorem Ipsum is simply dummy text of the printing and typesetting industry.<br>
						Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
					</p>
				</div>
				<div class="work-step">
					<div class="col-md-4 col-sm-4 first-step text-center">
						<i class="fa fa-search"></i>
						<h5>SEARCH CAR</h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
					</div>
					<div class="col-md-4 col-sm-4 second-step text-center">
						<i class="fa fa-heart-o"></i>
						<h5>SELECT CAR</h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
					</div>
					<div class="col-md-4 col-sm-4 third-step text-center">
						<i class="fa fa-shopping-cart"></i>
						<h5>BOOK CAR</h5>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
					</div>
				</div>
			</div>
		</div>
</section>
<section id="footer">
	<hr>
	<footer>
		<div class="container clear-padding">
			
		</div>
		<div class="row sm-footer-nav text-center">
			<div class="container clear-padding">
			<div class="row sm-footer">
				<div class="col-md-3 col-sm-6 footer-about-box">
					<h4>TRAVEL HELPER</h4>
					<p>Travel Application for deciding on best travel mode and scheduling travel.</p>
				</div>
				<div class="col-md-3 col-sm-6 contact-box">
					<h4>CONTACT US</h4>
					<p><i class="fa fa-home"></i> Long Beach CA</p>
					<p><i class="fa fa-envelope-o"></i> email@travelhelper.com</p>
					<p><i class="fa fa-phone"></i> +1 123-456-7890</p>
				</div>
				<div class="col-md-3 col-sm-6 footer-subscribe">
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
			<p class="copyright">
				&copy;	2016 TRAVELHELPER ALL RIGHTS RESERVED
			</p>
			<div class="go-up">
				<a href="#"><i class="fa fa-arrow-up"></i></a>
			</div>
		</div>
	</footer>
</section>


</body>
<script src="assets/js/respond.js"></script>
<script src="assets/js/jquery.js"></script>
<script src="assets/plugins/owl.carousel.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="assets/js/jquery-ui.min.js"></script>
<script src="assets/js/bootstrap-select.min.js"></script>
<script src="assets/plugins/wow.min.js"></script>
<script type="text/javascript" src="assets/plugins/supersized.3.1.3.min.js"></script>
<script src="assets/js/js.js"></script>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/> 
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"> </script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/loginvalidation.js"></script>
<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:48:43 GMT -->
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
														{image : 'assets/images/car-slide1.jpg', title : 'Slide 2'},
														{image : 'assets/images/car-slide2.jpg', title : 'Slide 1'}
												]
												
				}); 
		    });
		    
</script>
</html>