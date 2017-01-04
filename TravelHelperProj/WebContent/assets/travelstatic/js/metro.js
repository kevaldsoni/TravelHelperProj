	/**
	 * 
	 */
	"use strict";
	
	var sourceLatitude = 0;
	var sourceLongitude = 0;
	var destLatitude = 0;
	var destLongitude = 0;
	
	var fetchMetroAllRoutes = function(latitude,longitude){
		
		
		$.ajax({
			
			type : "GET",
			url : "http://api.metro.net/agencies/lametro/routes/",
			success : function(result){
				console.log("Metro All Routes ");
				$.each(result.items, function(idx, obj) {
					fetchRouteOnParticularStop(obj.id,latitude,longitude);
				});
			},
			error : function(response){
				console.log(response);
			}
		});
		
	}
	
	var fetchRouteOnParticularStop = function(routeId,sourceLat,sourceLong){
		
		$.ajax({
			
			type : "GET",
			url : "http://api.metro.net/agencies/lametro/routes/"+routeId+"/sequence",
			success : function(result){
				console.log("RouteOnParticularStop"+routeId+" : ");
				$.each(result.items, function(idx, obj) {
					console.log(obj.latitude);
					console.log(obj.longitude);
				});
			},
			error : function(response){
				console.log(response);
			}
			
		});
	}
	
	var fetchAllVehiclesOnRoute = function (){
		
		$.ajax({
			
			type : "GET",
			url : "http://api.metro.net/agencies/lametro/routes/704/vehicles/",
			success : function(result){
				console.log("All Vehicle on route : 704 ");
				console.log(result);
			},
			error : function(response){
				console.log(response);
			}
		});
		
	}
	
	var fetchRealtimePrediction = function (){
		
		$.ajax({
			
			type : "GET",
			url : "http://api.metro.net/agencies/ lametro/stops/6033/predictions/",
			success : function(result){
				console.log("RealtimePrediction : 6033 ");
				console.log(result);
			},
			error : function(response){
				console.log(response);
			}
			
		});
		
	}
	
	var fetchGeoLocation = function (addr){
		
		var API_KEY = "AIzaSyCin3LHSQnBxk5b1uCGbnzC5dT8uL93JRw";
		
		$.ajax({
			
			type : "GET",
			url : "https://maps.googleapis.com/maps/api/geocode/json?address="+addr+"&key="+API_KEY,
			success : function(result){
				destLatitude = result.results[0].geometry.location.lat;
				destLongitude = result.results[0].geometry.location.lng;
				console.log("Destination : "+destLatitude+ " " +destLongitude);
			},
			error : function(response){
				console.log(response);
			}
			
		});
		
	}
	
	
	 function fetchGeoCodes() {
	    var place = autocomplete.getPlace();
	  	sourceLatitude = place.geometry.location.lat();
	  	sourceLongitude = place.geometry.location.lng();
	    console.log("Source : "+sourceLatitude+ " " +sourceLongitude);
	    //fetchMetroAllRoutes(sourceLatitude,sourceLongitude);
	 }
	 function fetchDestGeoCodes() {
		  var address = document.getElementById('autocompleteDest').value;
	      fetchGeoLocation(address);
	 }
	
	var distanceCalculation = function(){
		console.log("Calculating dist : "+sourceLatitude+" "+sourceLongitude+" & "+destLatitude+" "+destLongitude);
		var API_KEY = "AIzaSyCin3LHSQnBxk5b1uCGbnzC5dT8uL93JRw";
		var httpurl = "http://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&mode=driving&origins="+sourceLatitude+","+sourceLongitude+"&destinations="+destLatitude+","+destLongitude;
		var httpsurl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&mode=driving&origins="+sourceLatitude+","+sourceLongitude+"&destinations="+destLatitude+","+destLongitude+"&key="+API_KEY;
		$.ajax({
			
			type : "GET",
			url : httpsurl,
			success : function(result){
				console.log(result);
			},
			error : function(errorresponse){
				console.log(errorresponse);
			}
			
		});
		return false;
	}
	
	var distanceCalculationTransit = function(travelSearchDetailsJson){
		return new Promise(function(resolve,reject){
				console.log("Calculating Transit dist : "+sourceLatitude+" "+sourceLongitude+" & "+destLatitude+" "+destLongitude);
				var origin = new google.maps.LatLng(sourceLatitude,sourceLongitude);
				var destination = new google.maps.LatLng(destLatitude, destLongitude);
				//var travelModes = ["driving","walking","transit","bicycling"];
				var service = new google.maps.DistanceMatrixService;
				service.getDistanceMatrix(
				  {
				    origins: [origin],
				    destinations: [destination],
				    travelMode: 'TRANSIT',
				    unitSystem: google.maps.UnitSystem.IMPERIAL,
				    avoidHighways: false,
				    avoidTolls: false,
				  }, callback);
		
				function callback(response, status) {
				  var originList = response.originAddresses;
		          var destinationList = response.destinationAddresses;
		          var results = response.rows[0].elements;
		          //console.log("Transit Distance:"+results[0].distance.text);
		          //console.log("Transit Time : "+results[0].duration.text);
		          for(var i in results){
		        	  travelSearchDetailsJson.travelData.push({
		        		  	"mode" : "transit",
			    			"distance" : results[i].distance.text,
			    			"duration" : results[i].duration.text,
			    			"capacity" : 20,
			    		});
			      }
		          resolve();
		          //showTransitDetails(results);
				}
		});

	}
	
	var distanceCalculationDriving = function(travelSearchDetailsJson){
		return new Promise(function(resolve,reject){
			console.log("Calculating Driving dist : "+sourceLatitude+" "+sourceLongitude+" & "+destLatitude+" "+destLongitude);
			var origin = new google.maps.LatLng(sourceLatitude,sourceLongitude);
			var destination = new google.maps.LatLng(destLatitude, destLongitude);
			var service = new google.maps.DistanceMatrixService;
			service.getDistanceMatrix(
			  {
			    origins: [origin],
			    destinations: [destination],
			    travelMode: 'DRIVING',
			    unitSystem: google.maps.UnitSystem.IMPERIAL,
			    avoidHighways: false,
			    avoidTolls: false,
			  }, callback);
			function callback(response, status) {
			  var originList = response.originAddresses;
	          var destinationList = response.destinationAddresses;
	          var results = response.rows[0].elements;
	          //console.log("Driving Distance:"+results[0].distance.text);
	          //console.log("Driving Time : "+results[0].duration.text);
	          for(var i in results){
	        	  travelSearchDetailsJson.travelData.push({
	        		  	"mode" : "driving",
		    			"distance" : results[i].distance.text,
		    			"duration" : results[i].duration.text,
		    			"capacity" : 4
		    		});
		      }
	          //showDrivingDetails(results);
	         resolve();
	        }
		});
	}
	
	var distanceCalculationWalking = function(travelSearchDetailsJson){
		return new Promise(function(resolve,reject){
			console.log("Calculating Walking dist : "+sourceLatitude+" "+sourceLongitude+" & "+destLatitude+" "+destLongitude);
			var origin = new google.maps.LatLng(sourceLatitude,sourceLongitude);
			var destination = new google.maps.LatLng(destLatitude, destLongitude);
			var service = new google.maps.DistanceMatrixService;
			service.getDistanceMatrix(
			  {
			    origins: [origin],
			    destinations: [destination],
			    travelMode: 'WALKING',
			    unitSystem: google.maps.UnitSystem.IMPERIAL,
			    avoidHighways: false,
			    avoidTolls: false,
			  }, callback);
	
			function callback(response, status) {
			  
			  var originList = response.originAddresses;
	          var destinationList = response.destinationAddresses;
	          var results = response.rows[0].elements;
	          //console.log("Walking Distance:"+results[0].distance.text);
	          //console.log("Walking Time : "+results[0].duration.text);
	          for(var i in results){
	        	  travelSearchDetailsJson.travelData.push({
	        		  	"mode" : "walking",
		    			"distance" : results[i].distance.text,
		    			"duration" : results[i].duration.text,
		    			"cost" : 0,
		    			"capacity" : 100
		    		});
		      }
	          //showWalkingDetails(results);
	          resolve();
			}
		});
	}
	
	var distanceCalculationBicycling = function(travelSearchDetailsJson){
		return new Promise(function(resolve,reject){
			console.log("Calculating Bicycling dist : "+sourceLatitude+" "+sourceLongitude+" & "+destLatitude+" "+destLongitude);
			var origin = new google.maps.LatLng(sourceLatitude,sourceLongitude);
			var destination = new google.maps.LatLng(destLatitude, destLongitude);
			var service = new google.maps.DistanceMatrixService;
			service.getDistanceMatrix(
			  {
			    origins: [origin],
			    destinations: [destination],
			    travelMode: 'BICYCLING',
			    unitSystem: google.maps.UnitSystem.IMPERIAL,
			    avoidHighways: false,
			    avoidTolls: false,
			  }, callback);
	
			function callback(response, status) {
			  
			  var originList = response.originAddresses;
	          var destinationList = response.destinationAddresses;
	          var results = response.rows[0].elements;
	          //console.log("Bicycling Distance:"+results[0].distance.text);
	          //console.log("Bicycling Time : "+results[0].duration.text);
	          for(var i in results){
	        	  travelSearchDetailsJson.travelData.push({
	        		  	"mode" : "bicycling",
		    			"distance" : results[i].distance.text,
		    			"duration" : results[i].duration.text,
		    			"cost" : 0,
		    			"capacity" : 1,
		    		});
		      }
	          //showBicyclingDetails(results);
	          resolve();
			}
		});
	}