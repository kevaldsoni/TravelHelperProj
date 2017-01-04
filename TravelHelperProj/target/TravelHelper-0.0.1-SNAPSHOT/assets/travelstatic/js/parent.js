/**
 * 
 */

"use strict";

$(document).ready(function() {
	
	$("#submitAddr").bind('click',fetchInput);
	
});

var fetchInput = function(event){
	
	
	console.log("Fetch input");
	
	// Prevent the form from submitting via the browser.
	event.preventDefault();
	distanceCalculationTransit();
	
	distanceCalculationDriving();
	distanceCalculationWalking();
	distanceCalculationBicycling();
    fetchUberDetails(sourceLatitude,sourceLongitude,destLatitude,destLongitude);
	//fetchLyftDetails(sourceLatitude,sourceLongitude);
	fetchlyftEstimatedPrice(sourceLatitude,sourceLongitude,destLatitude,destLongitude);
	fetchLyftTimeEstimate(sourceLatitude,sourceLongitude);
	return false;
}
function print(){
	console.log("wokrngg");
}

function showTransitDetails(results){
	
	var json = "<h4>Transit Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#publicTransitInfomation').html(json);
	
}

function showWalkingDetails(results){
	
	var json = "<h4>Walking Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#walkInfomation').html(json);
	
}

function showBicyclingDetails(results){
	
	var json = "<h4>Bicycling Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#bicyclingInfomation').html(json);
	
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