<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="bootstrapheader.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<body>
<div class="container">
<div class="row">
	<h3><span class="label label-info">Scheduled Travel Dashboard</span></h3>
</div>
<div class="row"  style="margin-top: 5px;">
<div class="col-md-2">
<h4><span class="label label-primary">Drive Summary</span></h4>
</div>
</div>
<div class="row">
<table id="scheduleDataDriveSummary" class="col-md-12 table-bordered table-hover table-condensed table-striped table-responsive"  style="margin-top:5px;margin-left:15px;">
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
<div class="row" style="margin-top: 5px;">
<div class="col-md-3 col-sm-3 col-xs-8">
<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#scheduleHistory">Click for Detailed Schedule History</button>
</div>
<div class="col-md-2 col-sm-2 col-xs-2">
<form enctype="mutipart/form-data" method="post" action="handleScheduleHistoryDownload">
	<button type="submit" class="btn btn-primary">Download Report</button>
</form>
</div>
<div class="col-md-1 col-sm-1 col-xs-1">
<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#filterdate">Filter results</button>
</div>
<div class="col-md-4 col-sm-4 col-xs-4" style="margin-left: 15px;">
<form action="filterschedulehistory" method="post" id="filterdate" class="collapse">
<input type="text" name="daterange" value="01/01/2017 - 01/31/2017" />
<input class="btn btn-primary" type="Submit" name="filter"/>
</form>
</div>
</div>
<div class="row" style="margin-top: 5px;">

<table id="scheduleHistory" class="col-md-12 table-hover table-condensed table-striped table-responsive collapse" style="margin-top: 5px;">
			<thead>
			<tr style="background-color:#2F7AF8;color: #fff;">
			<th>ID</th>
			<th>Timestamp</th>
			<th>Drive Selected</th>
         	<th>Destination Reach Time</th>
         	<th>Notification Time</th>
         	<th>Estimated Travel Time (seconds)</th>
         	<!-- <th>Start Location</th>
         	<th>Destination Location</th> -->
			</thead>
			<tbody id="travelsearchresults">
				<c:forEach var="i" items="${scheduleData}">
					<tr>
					<td><c:out value="${i.recordId}"></c:out></td>
					<td><c:out value="${i.requestSavetime}"></c:out></td>
					<td><c:out value="${i.travelDriveSelected}"></c:out></td>
					<td><c:out value="${i.destinationReachTime}"></c:out></td>
					<td><c:out value="${i.notificationTime}"></c:out></td>
					<td style="text-align: center;"><c:out value="${i.traveltimeExpected}"></c:out></td>
					<%-- <td><c:out value="${i.startLatitude}"></c:out>,<c:out value="${i.startLongitude}"></c:out></td>
					<td><c:out value="${i.endLatitude}"></c:out>,<c:out value="${i.endLongitude}"></c:out></td> --%>
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