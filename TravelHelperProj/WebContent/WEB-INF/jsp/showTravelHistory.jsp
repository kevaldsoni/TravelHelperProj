<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
<div class="container">
<div class="row">
	<h3><span class="label label-info">Travel History Dashboard</span></h3>
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
<button type="button" class="btn btn-primary" style="padding: 10px;" data-toggle="collapse" data-target="#scheduleHistory">Click for Detailed Travel History</button>
<table id="scheduleHistory" class="col-md-12 table-hover table-condensed table-striped table-responsive collapse" style="margin-top: 20px;">
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
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>