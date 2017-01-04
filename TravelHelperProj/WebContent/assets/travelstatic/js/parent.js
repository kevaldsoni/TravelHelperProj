/**
 * 
 */

"use strict";

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
		
	/*Promise.all([distanceCalculationTransit(travelSearchDetailsJson),
	             distanceCalculationDriving(travelSearchDetailsJson),
	             distanceCalculationWalking(travelSearchDetailsJson),
	             distanceCalculationBicycling(travelSearchDetailsJson),
	             fetchUberDetails(sourceLatitude,sourceLongitude,destLatitude,destLongitude),
	             fetchlyftEstimatedPrice(sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson),
	             fetchLyftTimeEstimate(sourceLatitude,sourceLongitude,travelSearchDetailsJson)
	  ]).then(function(){
	  showDetails(travelSearchDetailsJson);          	 
	});*/
	getAccessToken();
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

function showLyftDetails(results){
	
	var json = "<h4>Lyft Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#lyftDetails').html(json);
	
}

function showUberDetail(results){
	var json = "<h4>Uber Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#uberDetails').html(json);
	}