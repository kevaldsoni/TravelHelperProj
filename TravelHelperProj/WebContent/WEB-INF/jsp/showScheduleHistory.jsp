<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
<div class="container">
<div class="row">
	<h3><span class="label label-info">Scheduled Travel Dashboard</span></h3>
</div>
<div class="row" style="margin-top: 20px;">

<span class="label label-primary" style="padding: 10px;">Drive Summary</span>
<table id="scheduleDataDriveSummary" class="col-md-12 table-bordered table-hover table-condensed table-striped table-responsive" style="margin-top: 20px;">
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
<div class="row" style="margin-top: 20px;">

<button type="button" class="btn btn-primary" style="padding: 10px;" data-toggle="collapse" data-target="#scheduleHistory">Click for Detailed Schedule History</button>
<table id="scheduleHistory" class="col-md-12 table-hover table-condensed table-striped table-responsive collapse" style="margin-top: 20px;">
			<thead>
			<tr style="color:#2F7AF8;">
			<th>Record ID</th>
			<th>Record Timestamp</th>
			<th>Drive Selected</th>
         	<th>Destination Reach Time</th>
         	<th>Notification Time</th>
         	<th>Travel Time Estimated</th>
         	<th>Start Location</th>
         	<th>Destination Location</th>
			</thead>
			<tbody id="travelsearchresults">
				<c:forEach var="i" items="${scheduleData}">
					<tr>
					<td><c:out value="${i.recordId}"></c:out></td>
					<td><c:out value="${i.requestSavetime}"></c:out></td>
					<td><c:out value="${i.travelDriveSelected}"></c:out></td>
					<td><c:out value="${i.destinationReachTime}"></c:out></td>
					<td><c:out value="${i.notificationTime}"></c:out></td>
					<td><c:out value="${i.traveltimeExpected}"></c:out></td>
					<td><c:out value="${i.startLatitude}"></c:out>,<c:out value="${i.startLongitude}"></c:out></td>
					<td><c:out value="${i.endLatitude}"></c:out>,<c:out value="${i.endLongitude}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
</table>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>