/**
 * 
 */

"use strict";

$(document).ready(function() {
	
	$("#submitAddr").bind('click',fetchInput);
	
});

var fetchInput = function(){
	console.log("Fetch input");
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