<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<body>
<div class="container">
<div class="row">
	<h3><span class="label label-info">Travel History Dashboard</span></h3>
</div>

<div class="row"  style="margin-top: 5px;">
<div class="col-md-2">
<h4><span class="label label-primary">Drive Summary</span></h4>
</div>
</div>

<div class="row">
<table id="scheduleDataDriveSummary" class="col-md-12 table-bordered table-hover table-condensed table-striped table-responsive" style="margin-top:5px;margin-left:15px;">
			<thead>
			<tr style="color:#2F7AF8;">
			<th>Drive</th>
			<th>Count</th>
			</thead>
			<tbody id="travelsearchresults">
			<c:forEach var="entry" items="${scheduleDataDriveSummary}">
  			<tr>
  			<td><c:out value="${entry.key}"/></td>
  			<td><c:out value="${entry.value}"/></td>
  			</tr>
			</c:forEach>
			</tbody>
</table>


</div> 
<section>
<!-- <div class="row">
<div class="col-md-4 col-sm-6 col-xs-6">
<button type="button" class="btn btn-primary" style="padding: 10px;" data-toggle="collapse" data-target="#scheduleHistory">Click for Detailed Travel History</button>
</div>
<div class="col-md-8 col-sm-6 col-xs-6">
<form enctype="mutipart/form-data" method="post" action="handleTravelHistoryDownload">
	<button type="submit" class="btn btn-primary" style="padding: 10px;">Download Report</button>
</form>
</div>
</div> -->
<div class="row" style="margin-top: 5px;">
<div class="col-md-3 col-sm-3 col-xs-8">
<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#scheduleHistory">Click for Detailed Schedule History</button>
</div>
<div class="col-md-2 col-sm-2 col-xs-2">
<form enctype="mutipart/form-data" method="post" action="handleTravelHistoryDownload">
	<button type="submit" class="btn btn-primary">Download Report</button>
</form>
</div>
<div class="col-md-1 col-sm-1 col-xs-1">
<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#filterdate">Filter results</button>
</div>
<div class="col-md-4 col-sm-4 col-xs-4" style="margin-left: 15px;">
<form action="filtertravelhistory" method="post" id="filterdate" class="collapse">
<input type="text" name="daterange" value="01/01/2017 - 01/31/2017" />
<input class="btn btn-primary" type="Submit" name="filter"/>
</form>
</div>
</div>
<div class="row" style="margin-top: 5px;">
<table id="scheduleHistory" class="col-md-12 table-hover table-condensed table-striped table-responsive collapse" style="margin-top: 5px;">
			<thead>
			<tr style="color:#2F7AF8;">
			<th>Record ID</th>
			<th>Record Timestamp</th>
			<th>Drive Selected</th>
         	<th>Mode Selected</th>
         	<th>Distance</th>
         	<th>Duration</th>
         	<th>Cost</th>
         	<th>Start Location</th>
         	<th>Destination Location</th>
			</thead>
			<tbody id="travelsearchresults">
				<c:forEach var="i" items="${scheduleData}">
					<tr>
					<td><c:out value="${i.travelRequestId}"></c:out></td>
					<td><c:out value="${i.requestTimeStamp}"></c:out></td>
					<td><c:out value="${i.userDrive}"></c:out></td>
					<td><c:out value="${i.travelMode}"></c:out></td>
					<td><c:out value="${i.distance}"></c:out></td>
					<td><c:out value="${i.duration}"></c:out></td>
					<td><c:out value="${i.cost}"></c:out></td>
					<td><c:out value="${i.sourceLatitude}"></c:out>,<c:out value="${i.sourceLongitude}"></c:out></td>
					<td><c:out value="${i.destLatitude}"></c:out>,<c:out value="${i.destLongitude}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
</table>
</div>
</section>
</div>
<jsp:include page="dashboardfooter.jsp"></jsp:include>
<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<!-- Include Date Range Picker -->
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<script type="text/javascript">
$(function() {
    $('input[name="daterange"]').daterangepicker();
});
</script>
</body>
</html>