/**
 * 
 */

"use strict";
var sourceLatitude = 33.790811;
var sourceLongitude = -118.135536;
var destLatitude = 33.930390;
var destLongitude = -118.396492;
var travelSearchDetailsJson = { travelData:[]};
$(document).ready(function() {
	
	$("#submitAddr").bind('click',fetchInput);

});

var fetchInput = function(event){
	
	console.log("Fetch input");
	
	// Prevent the form from submitting via the browser.
	event.preventDefault();
	//$.when(gatherData(travelSearchDetailsJson)).then(showDetails(travelSearchDetailsJson));
	gatherData(travelSearchDetailsJson);
	return false;
}

function gatherData(travelSearchDetailsJson){
		
	Promise.all([distanceCalculationTransit(travelSearchDetailsJson),
	             distanceCalculationDriving(travelSearchDetailsJson),
	             distanceCalculationWalking(travelSearchDetailsJson),
	             distanceCalculationBicycling(travelSearchDetailsJson),
	             fetchlyftEstimatedPrice(sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson),
	             fetchLyftTimeEstimate(sourceLatitude,sourceLongitude,travelSearchDetailsJson),
	             //fetchUberDetails(sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson),
	             fetchUberProductDetails(sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson),
	             fetchUberEstimatedPrice(sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson)
	            
	  ]).then(function(){
		      var completeData = travelSearchDetailsJson.travelData;
			  fetchUberTimeEstimate(completeData,sourceLatitude,sourceLongitude,travelSearchDetailsJson);
		  
	  }).then(function(){
		  setTimeout(function(){
			  showTravelDetails(travelSearchDetailsJson);
			  document.getElementById('travelSearchResults').scrollIntoView();
		  },7000);
		  		
	  });
	//getAccessToken();
	//fetchUberDetails(sourceLatitude,sourceLongitude,destLatitude,destLongitude);
	//fetchLyftDetails(sourceLatitude,sourceLongitude);
	//fetchlyftEstimatedPrice(sourceLatitude,sourceLongitude,destLatitude,destLongitude);
	//fetchLyftTimeEstimate(sourceLatitude,sourceLongitude);
}

function showDetails(results){
	
	console.log("Now displaying data");
	$('#showDetails').html("");
	var json = "<h4>Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#showDetails').html(json);
	
}




function showTravelDetails(results){
	results = results.travelData;
	$('#location tr').empty();
	var trHTML='<tr style="color:#2F7AF8;"><th>Travel Mode</th><th>Distance</th>'+
         		'<th>Duration (min)</th>'+
         		'<th>Time estimate (min)</th>'+
         		'<th>Cost $</th>'+
         		'<th>Capacity</th></tr>';
	for(var i in results){
		trHTML += '<tr scope="row" class="info" id='+results[i].mode+' onclick="passSelection("this");">'+ 
		'<td>'+ results[i].mode+ '</td><td>' + results[i].distance + '</td><td>' + results[i].duration + '</td><td>' + results[i].time_estimate + 
		'</td><td>'+ results[i].cost + '</td><td>' + results[i].capacity + '</td></tr>';
	}
	$('#location').html(trHTML);
	
}

function passSelection(){
	alert("yes sir");
	 alert($(this).attr('id')); 
}

function showUberDetail(results){
	var json = "<h4>Uber Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#uberDetails').html(json);
	}