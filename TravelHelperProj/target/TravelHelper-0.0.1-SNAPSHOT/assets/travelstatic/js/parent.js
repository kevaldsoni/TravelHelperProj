/**
 * 
 */

"use strict";
/*var sourceLatitude = 33.790811;
var sourceLongitude = -118.135536;
var destLatitude = 33.930390;
var destLongitude = -118.396492;
*/


var result=[];
var travelSearchDetailsJson = { travelData:[]};
var finalResults=[];
$(document).ready(function() {
	
	$("#submitAddr").bind('click',fetchInput);
	$("#scheduleTravelSubmit").bind('click',handleScehduledTravel);
	$("#downloadTravelHistory").bind('click',downloadTravelHistory);

});

var fetchInput = function(event){
	
	console.log("Fetch input");
	travelSearchDetailsJson.travelData = [];
	// Prevent the form from submitting via the browser.
	event.preventDefault();
	//$.when(gatherData(travelSearchDetailsJson)).then(showDetails(travelSearchDetailsJson));
	$('#location').css({"visibility": "visible"});
	gatherData(travelSearchDetailsJson);
	return false;
}

var handleScehduledTravel = function(event){
	
	event.preventDefault();
	console.log("In scheduled travel");
	var drive = $(".selectpicker").val();
	console.log(drive);
	getScheduledTravelTime(drive).then(function(){
		return fetchScheduleTravelDetails();
	});
	
}

function getScheduledTravelTime(drive){
		console.log("getScheduledTravelTime func");
		return new Promise(function(resolve,reject){
		switch (drive){
		case 'DRIVING' :  
						  distanceCalculationDriving(travelSearchDetailsJson);
						  setTimeout(function(){
							  console.log("Waiting");
							  resolve();
						  },5000);
						  break;
		case 'TRANSIT' : distanceCalculationTransit(travelSearchDetailsJson);
						 setTimeout(function(){
						  console.log("Waiting");
						  resolve();
						 },5000);break;
		case 'WALKING' : distanceCalculationWalking(travelSearchDetailsJson);
						 setTimeout(function(){
							  console.log("Waiting");
							  resolve();
						 },5000);break;
						 
		case 'BICYCLING' : distanceCalculationBicycling(travelSearchDetailsJson);
						 setTimeout(function(){
							  console.log("Waiting");
							  resolve();
						 },5000);break;
						 
		case 'LYFT' :  fetchlyftEstimatedPrice(sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson);
						setTimeout(function(){
							  console.log("Waiting");
							  resolve();
						 },5000);break;
		case 'UBER' :  distanceCalculationDriving(travelSearchDetailsJson);
					  setTimeout(function(){
						  console.log("Waiting");
						  resolve();
					  },5000);
					  break;
		}
		});
}
	

var fetchScheduleTravelDetails = function(event){
	
	console.log("Fetch fetchScheduleTravelDetails");
	console.log(sourceLatitude+" "+sourceLongitude+" "+destLatitude+" "+destLongitude);
	
	var drive = $(".selectpicker").val();
	console.log(drive);
	var date = $("#scheduleDate").val();
	console.log(date);
	var time = $("#scheduleTime").val();
	console.log(time);
	var notifyBefore = $("#notifyBefore").val();
	console.log(notifyBefore);
	
	var expectedTravelTime;
	console.log(travelSearchDetailsJson.travelData);
	var data = travelSearchDetailsJson.travelData[0].duration;
	if(data != undefined){
		var isnum = /^[0-9.]+$/.test(data);
		if(!isnum){
			if(data.length >1){
				var durationInfo = data.split(" ");
				if(durationInfo.length > 2){
					console.log("duration has hours "+durationInfo);
					expectedTravelTime = +(durationInfo[0]*60) + +durationInfo[2];
				}else{
					console.log("duration has minutes "+durationInfo);
					expectedTravelTime = durationInfo[0];
				}
			}
		}else{
			expectedTravelTime = Math.round(data);
		}
	}

	var destReachTime = date+" "+time;
	var dataTobeSent = "userDrive="+drive+"&startLatitude="+sourceLatitude+"&startLongitude="+sourceLongitude+"&endLatitude="+destLatitude+"&endLongitude="+destLongitude+
	   "&preNotificationTime="+notifyBefore+"&travelDriveSelected="+drive+"&destReachTime="+destReachTime+"&expectedTravelTime="+expectedTravelTime;
	
	saveFutureTravelDetails(dataTobeSent);
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
	      fetchRideEstimate(completeData,sourceLatitude,sourceLongitude,destLatitude,destLongitude,travelSearchDetailsJson);
	  
	  }).then(function(){
		      var completeData = travelSearchDetailsJson.travelData;
			  fetchUberTimeEstimate(completeData,sourceLatitude,sourceLongitude,travelSearchDetailsJson);
		  
	  }).then(function(){
		  setTimeout(function(){
			  showTravelDetails(travelSearchDetailsJson,sourceLatitude,sourceLongitude,destLatitude,destLongitude);
		  },15000);
		  		
	  });
}

function showDetails(results){
		
	// preprocess results

	console.log("Now displaying data");
	$('#showDetails').html("");
	var json = "<h4>Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#showDetails').html(json);
	
}

function preProcessResults(results){
	results = results.travelData;
	var pplCount = $("#pplcount").val();
	var travelpref = $("#travelpref").val();
	console.log(pplCount+" "+travelpref);
	for(var i in results){
		
		if(results[i].capacity >= pplCount){
		var data = results[i].duration;
		if(data != undefined){
			var isnum = /^[0-9.]+$/.test(data);
			if(!isnum){
				if(data.length >1){
					var durationInfo = data.split(" ");
					if(durationInfo.length > 2){
						console.log("duration has hours "+durationInfo);
						results[i].duration = (durationInfo[0]*60)+durationInfo[2];
					}else{
						console.log("duration has minutes "+durationInfo);
						results[i].duration = durationInfo[0];
					}
				}
			}
		}else{
			console.log("Need to populate data");	
		}
		var distanceDuration = results[i].distance;
		if(distanceDuration != undefined){
			var isnum = /^[0-9.]+$/.test(distanceDuration);
			if(!isnum){
				var durationInfo = distanceDuration.split(" ");
				results[i].distance = durationInfo[0];
			}
		}
	  }else{
		  console.log("Not enough capacity");
		  results.splice(i,1);
	  }
	}// end for
	
		if(travelpref == "Ecomonical"){
			results.sort(function(a, b) {
			    return parseInt(a.cost, 10) - parseInt(b.cost, 10);
			});
		}
		for(var i in results){
			console.log(results[i].cost);
		}
	
}


function showTravelDetails(travelSearchDetailsJson,sourceLatitude,sourceLongitude,destLatitude,destLongitude){
	var results = travelSearchDetailsJson.travelData;
	//preProcessResults(results); 
	
	/* func start*/
	/*for(var i in results){
		console.log("Start :"+results[i].cost);
	}*/
	var pplCount = $("#pplcount").val();
	var travelpref = $("#travelpref").val();
	console.log(pplCount+" "+travelpref);
	for(var i in results){
		
		if(results[i].capacity >= pplCount){
		var data = results[i].duration;
		if(data != undefined){
			var isnum = /^[0-9.]+$/.test(data);
			if(!isnum){
				if(data.length >1){
					var durationInfo = data.split(" ");
					if(durationInfo.length > 2){
						console.log("duration has hours "+durationInfo);
						results[i].duration = (durationInfo[0]*60)+durationInfo[2];
					}else{
						console.log("duration has minutes "+durationInfo);
						results[i].duration = durationInfo[0];
					}
				}
			}
		}else{
			console.log("Need to populate data");	
		}
		var distanceDuration = results[i].distance;
		if(distanceDuration != undefined){
			var isnum = /^[0-9.]+$/.test(distanceDuration);
			if(!isnum){
				var durationInfo = distanceDuration.split(" ");
				results[i].distance = durationInfo[0];
			}
		}
	  }else{
		  console.log("Not enough capacity");
		  results.splice(i,1);
	  }
		
		if(results[i].mode == 'DRIVING'){
			results[i].cost =Math.round( +(results[i].distance) * 0.54 );
		}
		
	}// end for
	
	
	
	//results.sort( predicatBy("cost") );
	//alert(travelpref);
	//Usage
	if(travelpref == "Economical"){
		results.sort( predicatBy("cost") );
	}else{
		results.sort( predicatBy("duration") );
		
	}
	/*for(i in results){
		console.log(results[i].cost);
	}*/
	/* func end */
	
	$('#travelsearchresults').empty();
	var trHTML= "";
	for(var i in results){
		results[i].sourceLatitude=sourceLatitude;
		results[i].sourceLongitude=sourceLongitude;
		results[i].destLatitude=destLatitude;
		results[i].destLongitude=destLongitude;
		trHTML += '<tr scope="row" class="info" id='+results[i].mode+' onclick="passSelection(this);">'+ 
		'<td>'+ results[i].mode+ '</td><td>' + results[i].distance + '</td><td>' + results[i].duration + '</td><td>' + results[i].time_estimate + 
		'</td><td>'+ results[i].cost + '</td><td>' + results[i].capacity + '</td></tr>';
		
	}
	finalResults = results;
	$('#travelsearchresults').html(trHTML);
	//document.getElementById('traveloptions').reset();
	$(window).scrollTop($('#traveldatasection').position().top);

}

function downloadTravelHistory(){
	alert("Downloading file");

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/TravelHelper/handleTravelHistoryDownload",
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			alert("SUCCESS: ", data);
			
		},
		error : function(e) {
			alertg("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});

	}

function predicatBy(prop){
	   return function(a,b){
	      if( a[prop] > b[prop]){
	          return 1;
	      }else if( a[prop] < b[prop] ){
	          return -1;
	      }
	      return 0;
	   }
}

function passSelection(elem){
	var drive = $(elem).attr("id");
    alert(drive);
    var search = {}
	search["userDrive"] = drive;
	search["sourceLatitude"] = sourceLatitude;
	search["sourceLongitude"] = sourceLongitude;
	search["destLatitude"] = destLatitude;
	search["destLongitude"] = destLongitude;
	
	for(var i in finalResults){
		if(drive == finalResults[i].mode){
			search["distance"] = finalResults[i].distance;
			search["duration"] = finalResults[i].duration;
			search["cost"] = finalResults[i].cost;
			break;
		} 
	}
	
    $.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/TravelHelper/saveTravelSelection",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			//display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
    
}


function saveFutureTravelDetails(dataTobeSent){
	//alert(dataTobeSent);
    $.ajax({
		type : "POST",
		contentType : "application/x-www-form-urlencoded",
		url : "/TravelHelper/saveFutureTravelDetails",
		data : dataTobeSent,
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			$('#confirmationBox').css({"display": "block"});
			document.getElementById('scheduleTravelForm').reset();
			$(window).scrollTop($('#confirmationBox').position().top);
			travelSearchDetailsJson.travelData = [];
			//display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
    

}


function saveNotificationEndPoint(gcmUrl){
	
	var spData = gcmUrl.split("/");
	var gcmId = spData[5];
	console.log(gcmId);
	$.ajax({
		type : "POST",
		url : "/TravelHelper/saveGcmIdForUser",
		data : "id="+gcmId,
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ");
			$('#scheduleTravelForm').css({"display": "block"});
			//display(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			//display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
}