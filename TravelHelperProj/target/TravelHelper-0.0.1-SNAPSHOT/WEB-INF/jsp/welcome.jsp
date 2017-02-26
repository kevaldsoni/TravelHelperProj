<!DOCTYPE html>
<html class="load-full-screen">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:46:00 GMT -->
<head>
	
	<jsp:include page="bootstrapheader.jsp"></jsp:include>

</head>
<body class="load-full-screen">

<!-- BEGIN: SITE-WRAPPER -->
<div class="site-wrapper">
	<b>Welcome to kevals try to get proj running</b>
	<sec:authentication property="name"></sec:authentication>

	
	
	<!-- BEGIN: SEARCH SECTION -->
<div class="container" style="padding-top:40px;padding-bottom: 40px;">
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 style="text-align: center;"><a href="/TravelHelper/travelsearch">Travel Search</a></h3>
                </div>
                <div class="panel-body">Look for the best way to travel right now. Travel Helper presents comprehensive analysis of distance, duration and cost to 
                	decide which mode will be best to travel now. Uber and Lyft drive details present.
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 style="text-align: center;"><a href="/TravelHelper/assets/pages/firebase/scheduleTravel.jsp">Schedule Travel</a></h3>
                </div>
                <div class="panel-body">Want to travel later and reach destination on time. Schedule future travel storing time to reach and travel mode. 
                	Travel Helper will notify when to start travel to reach destination on time.
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 style="text-align: center;"><a href="/TravelHelper/dashboard">Dashboard</a></h3>
                </div>
                <div class="panel-body">Presents Historical Analysis of Travel Preferences and Travel Scheduling. View complete travel information. Data can be sorted on date range and
                 	report can be downloaded for offline use.
                 </div>
            </div>
        </div>
    </div>
</div>
	<!-- END: SEARCH SECTION -->


<!-- START: FOOTER -->
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:48:43 GMT -->
</html>