<!DOCTYPE html>
<html class="load-full-screen">

<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:46:00 GMT -->
<head>
	
	<jsp:include page="bootstrapheader.jsp"></jsp:include>
	
</head>
<body class="load-full-screen">

<!-- BEGIN: SITE-WRAPPER -->
<div class="site-wrapper">
	
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
						<form id="traveloptions">
							<label>Source Location</label>
							<div class="input-group">
								<input type="text" name="source" id="autocomplete" class="form-control" placeholder="E.g Long beach" required>
								<span class="input-group-addon"><i class="fa fa-map-marker fa-fw"></i></span>
							</div>
							<div class="alert alert-danger sourceerrmsg" role="alert" style="display: none;">Enter valid source location</div>
							<label>Destination Location</label>
							<div class="input-group">
								<input type="text" name="destination" id="autocompleteDest" class="form-control" placeholder="E.g Los angeles" required>
								<span class="input-group-addon"><i class="fa fa-map-marker fa-fw"></i></span>
							</div>
							<div class="alert alert-danger destinationerrmsg" role="alert" style="display: none;">Enter valid destination location</div>
								<label>Travel Preference</label>
								<select class="selectpicker" name="money_type" id="travelpref">
									<option>Economical</option>
									<option>Fastest</option>

								</select>
							
							<label>Traveler Count</label>
							<select class="selectpicker" id="pplcount" name="travellercount_type">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
							</select>
							<div class="text-center">
								<!-- <input type="submit" name="submit" id="submitAddr" class="submitAddr">Search Results</input> -->
								<button type="submit" id="submitAddr">Search Results</button>
								<img alt="" src="/TravelHelper/assets/images/loading2.gif" id="scheduleprogress" style="display: none;" ></img>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- END: SEARCH SECTION -->
 	<section id="traveldatasection" >
 		
		<div class="row work-row">
			<table id="saveprefmessage" class="col-md-12 table-bordered table-hover table-condensed table-striped" style="margin-left: 50px;margin-right: 50px;width: 90%;visibility: hidden;">
			<tr><td><b>Select Travel Mode to save the decision</b></td></tr>
			</table>
			<table id="location" class="col-md-12 table-bordered table-hover table-condensed table-striped" style="margin-left: 50px;margin-right: 50px;width: 90%;visibility: hidden;">
			
			<thead>
			<tr style="background-color:#2F7AF8;color: #fff;">
			<th>Travel Mode</th>
			<th>Distance (miles)</th>
         	<th>Duration (minutes)</th>
         	<th>Time estimate (minutes)</th>
         	<th>Cost $</th>
         	<th>Capacity</th>
			</thead>
			<tbody id="travelsearchresults">
			
			</tbody>
			</table>
		</div>
	</section> 
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
  	<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" style="text-align: center;">Travel decision saved</h4>
      </div>
      <!-- <div class="modal-body">
        <p id="modalText">Travel decision saved</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div> -->
    </div>
	</div>
	</div>
<!-- START: FOOTER -->
	 
	<jsp:include page="footer.jsp"></jsp:include>
	

</body>

<!-- Mirrored from demo-limpidthemes.com/Themeforest/html/cruise-demo/light/car-index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 14 Jun 2016 22:48:43 GMT -->
</html>