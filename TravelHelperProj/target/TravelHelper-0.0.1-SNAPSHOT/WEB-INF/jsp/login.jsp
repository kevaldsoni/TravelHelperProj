<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="load-full-screen">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="LimpidThemes">
	
	<title>Travel Helper Application</title>
	<link rel="shortcut icon" href="assets/travelstatic/favicon/favicon.ico" type="image/x-icon">
    <!-- STYLES -->
	<link href="assets/css/animate.min.css" rel="stylesheet">
	<link href="assets/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="assets/css/owl.carousel.css" rel="stylesheet">
	<link href="assets/css/owl-carousel-theme.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="assets/css/flexslider.css" rel="stylesheet" media="screen">
	<link href="assets/css/style.css" rel="stylesheet" media="screen">
	<!-- LIGHT -->
	<link rel="stylesheet" type="text/css" href="assets/css/color/blue.css" id="select-style">
	<link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"/> 
	<link href="assets/css/light.css" rel="stylesheet" media="screen">
	
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600' rel='stylesheet' type='text/css'>
	
</head>
<body class="load-full-screen">

<!-- BEGIN: SITE-WRAPPER -->
<div class="site-wrapper">
	
		<div class="row">
			<div class="hotel-tagline text-center" style="margin-top: 1%;">
						<h3>Welcome To</h3>
						<h1>Travel Helper</h1>
					</div>
		</div>
 

	<div class="clearfix"></div>
	<!-- BEGIN: SEARCH SECTION -->
	<div class="row">
		<div class="container">
			<div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2" style="margin-top: 10px;">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title" >Sign In</div>
                        <!-- <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div> -->
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form" method="post" action="<c:url value='j_spring_security_check' />" >
                           <%
								String message=(String)request.getAttribute("errormessage");
                           		if(message!=null && message.length()>0){
							%>
							<div class="alert alert-danger">
  							<strong>${errormessage}</strong>
							</div>
							   <%
								}else if((String)request.getAttribute("successMessage") != null){
							   %>
							   	<div class="alert alert-success">
  								<strong>${successMessage}</strong>
								</div>
							   <% 
							   message = null;
								} %>
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
        <div id="signupbox" style="display:none;margin-top: 10px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>
                            <div class="panel-title" style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
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
	<!-- END: SEARCH SECTION -->
<!-- START: FOOTER -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- END: FOOTER -->

</div>
<!-- END: SITE-WRAPPER -->
<!-- Load Scripts -->
<script src="assets/js/respond.js"></script>
<script src="assets/js/jquery.js"></script>
<script src="assets/plugins/owl.carousel.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="assets/js/jquery-ui.min.js"></script>
<script src="assets/js/bootstrap-select.min.js"></script>
<script src="assets/plugins/wow.min.js"></script>
<script type="text/javascript" src="assets/plugins/supersized.3.1.3.min.js"></script>
<script src="assets/js/js.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"> </script>
<script type="text/javascript" src="/TravelHelper/assets/travelstatic/js/loginvalidation.js"></script>
</body>
</html>