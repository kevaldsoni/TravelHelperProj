/**
 * 
 */

"use strict";
var sourceLatitude = 33.790811;
var sourceLongitude = -118.135536;
var destLatitude = 33.930390;
var destLongitude = -118.396492;
var travelSearchDetailsJson = { travelData:[]};
var finalResults=[];
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
			  showTravelDetails(travelSearchDetailsJson,sourceLatitude,sourceLongitude,destLatitude,destLongitude);
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
		
	// preprocess results

	console.log("Now displaying data");
	$('#showDetails').html("");
	var json = "<h4>Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#showDetails').html(json);
	
}

function preProcessResults(results){

	for(var i in results){
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
}

}


function showTravelDetails(results,sourceLatitude,sourceLongitude,destLatitude,destLongitude){
	results = results.travelData;
	preProcessResults(results);
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
	
	/*var items = [];
	$.each(results, function(key,val){
		
		items.push("<tr id='"+val.mode+"' onclick='passSelection(this);'>");
		items.push("<td>"+val.mode+"</td>");
		items.push("</tr>");
		
	});
	$("<tbody/>",{"class" : "mydata" , html: items.join("")}).appendTo("table");*/
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

/*function showUberDetail(results){
	var json = "<h4>Uber Details</h4><pre>"
		+ JSON.stringify(results, null, 4) + "</pre>";
	$('#uberDetails').html(json);
	}*/


function saveNotificationEndPoint(gcmUrl){
	
	alert(gcmUrl);
	var spData = gcmUrl.split("/");
	var gcmId = spData[5];
	console.log(gcmId);
	$.ajax({
		type : "POST",
		url : "/TravelHelper/saveGcmIdForUser",
		data : "id="+gcmId,
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