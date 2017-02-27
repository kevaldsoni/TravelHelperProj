/**
 * 
 */

var uberDetailsJson = { uberDet:[]};
var latitudeStart;
var longitudeStart;
var latitudeEnd;
var longitudeEnd;


var accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZXMiOlsicHJvZmlsZSJdLCJzdWIiOiIzODliMzM5YS0wY2JjLTQzYzktYjQyYS0xNTFiMmVhM2FjMjEiLCJpc3MiOiJ1YmVyLXVzMSIsImp0aSI6IjM5NGFjM2YwLWRjNWQtNDg4Ni1hYjQ3LTllMTQyNmRmMzM3YiIsImV4cCI6MTQ4ODc4MDgzMiwiaWF0IjoxNDg2MTg4ODMxLCJ1YWN0IjoiZnB3Zmw4RkhSRmV5NHBFNGY1REc5MkhzN2RsSzBrIiwibmJmIjoxNDg2MTg4NzQxLCJhdWQiOiJLcXRrelo3WUh3OG42UEo5NkNBMHdNSk5iZGVFTy1tbSJ9.ZRK0SIpsIAERtcEoMjMz694-5I5s6J4lha8-91LNmU9RGPb5h_EZAUXqSEBkKrFIMhzQl2m64FmCA7Cg5dqXXUT7fnzuaEP6lm7hADBgvjW8LTPdS8Te-J7md2r2HNYA3WruCAVlPH3xUHPIOUA5LgdA6lIpA9EJJKig8OF2BWrKx28IPrqwYmFhdYYPiAF-wVsJ-gJwUjRpCdCFZ2Xzmtz6o0CxitY20bdB4pwZdqj2R3woQdaOw4rDBj1kprmTlzjYEjw7aT_SVM-6RQ7lVfdhmgA69RomU08-ZblNHISZQ4xWXi3FCYVp4Hdqdggp_VzMrrVFe-jDixxrh6sNvw";

var fetchUberDetails = function (latitudeStart,longitudeStart,destLatitude,destLongitude,travelSearchDetailsJson){
		//getAccessToken();
	fetchUberProductDetails(latitudeStart,longitudeStart).then(fetchUberEstimatedPrice(latitudeStart,longitudeStart,destLatitude,destLongitude)).then(pushFinalUberData);
}
var pushFinalUberData = function(){
	console.log("in b");
	console.log(uberDetailsJson);
	var data = uberDetailsJson.uberDet;
	for(i in data){
		travelSearchDetailsJson.travelData.push(data[i]);
	}
	showUberDetail(travelSearchDetailsJson);
	
}

var fetchUberProductDetails = function(latitudeStart,longitudeStart,destLatitude,destLongitude,travelSearchDetailsJson){
	return new Promise(function(resolve,reject){
	$.ajax({
		type: "GET",
		url : "https://api.uber.com/v1.2/products",
		headers : {
			Authorization : "Token" + "qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN"
		},
		data: {
			server_token : 'qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN',
			latitude: latitudeStart,
			longitude: longitudeStart,
	        
	    },
	    success : function(result){
	    	console.log(result);
	    	var products = result.products;
	    	for(var i in products){
	    		travelSearchDetailsJson.travelData.push({
	    			"mode" : products[i].display_name,
	    			"product_id" : products[i].product_id,
	    			"capacity" : products[i].capacity
	    		});
	    		fetchRideEstimate(products[i].product_id,latitudeStart,longitudeStart,destLatitude,destLongitude);
	    		
	    	}
	    	/*var data = uberDetailsJson.uberDet;
	    	for(var i in data){
	    		var productId = data[i].product_id;
	    		var displayName = data[i].display_name;
	    		//fetchUberTimeEstimate(productId,latitudeStart,longitudeStart);
	    		//fetchRideEstimate(productId,latitudeStart,longitudeStart,destLatitude,destLongitude);
	    	}*/
	    	resolve();
	    },
	    error: function(response){
	    	console.log(response);
	    }	
	});
	});
}

var fetchUberEstimatedPrice = function(latitudeStart,longitudeStart,endlatitude,endlongitude,travelSearchDetailsJson){
	return new Promise(function(resolve,reject){
	//console.log(latitudeStart+" "+longitudeStart+" "+endlatitude+" "+endlongitude);
	var priceurl = "https://api.uber.com/v1.2/estimates/price";
	var sandBoxUrl = "https://sandbox-api.uber.com/v1/estimates/price";
	$.ajax({
		type: "GET",
		//url : "https://api.uber.com/v1/estimates/price",
		url : priceurl,
		headers : {
			Authorization : "Token" + "qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN"
		},
		data: {
			server_token : 'qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN',
			start_latitude: latitudeStart,
			start_longitude: longitudeStart,
			end_latitude: endlatitude,
			end_longitude : endlongitude
		},
	    success : function(result){
	    	
	    	var uberParsedData = travelSearchDetailsJson.travelData;
	    	var data = result.prices;
	    	for(var i in data){
	    		var driveType = data[i].display_name;
	    		var estimate = data[i].estimate;
	    		//console.log(driveType+" "+estimate);
	    		for(var i in uberParsedData){
					if(driveType == uberParsedData[i].mode){
						//uberParsedData[i].price_estimate = estimate;
						var money = estimate.split("-");
						var minCost = money[0].substring(1);
						var maxCost = money[1];
						//uberParsedData[i].max_cost = maxCost;
						//uberParsedData[i].min_cost = minCost;
						
						travelSearchDetailsJson.travelData[i].cost =Math.round((+minCost + +maxCost)/2);
						//alert(uberParsedData[i].avg_cost);
					}
				}
	    	}
	    	resolve();
	    },
	    error: function(response){
	    	console.log(response);
	    	
	    }	
	});
	});
}


var fetchUberTimeEstimate = function(completeData,latitudeStart,longitudeStart){
	return new Promise(function(resolve,reject){
	var count = 0;
	for(var i in completeData){
		var productId = completeData[i].product_id;
		if(productId != undefined){
			var estimate = ""
			$.ajax({
				type: "GET",
				url : "https://api.uber.com/v1.2/estimates/time",
				headers : {
					Authorization : "Token" + "qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN"
				},
				data: {
					server_token : 'qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN',
					start_latitude: latitudeStart,
					start_longitude: longitudeStart,
					product_id:productId	
				},
				success : function(result){
					var data = result.times;
					var uberParsedData = travelSearchDetailsJson.travelData;
					for(var i in data){
						var driveType = data[i].display_name;
						estimate = data[i].estimate;
						for(var i in uberParsedData){
							if(driveType == uberParsedData[i].mode){
								travelSearchDetailsJson.travelData[i].time_estimate = (estimate/60.0);
							}
						}
						showTravelDetails(travelSearchDetailsJson); 
					}
					
				},
				error: function(response){
					console.log(response);
				}	
			});	
		}
		}
		
	});
}

var fetchRideEstimate = function(completeData,latitudeStart,longitudeStart,destLatitude,destLongitude){
	
	return new Promise(function(resolve,reject){
		
		for(var i in completeData){
			var productId = completeData[i].product_id;
			if(productId != undefined){
				var estimate = ""
				var jsonData = {"product_id":productId,"start_latitude":latitudeStart,"start_longitude":longitudeStart,"end_latitude":destLatitude,"end_longitude":destLongitude};
				$.ajax({
					type: "POST",
					url : "https://sandbox-api.uber.com/v1.2/requests/estimate",
					headers : {
						"Authorization" : "Bearer " + accessToken,
						"Content-Type": "application/json",
						"Accept-Language": "en_US"
					},
					
					data: JSON.stringify(jsonData),
					success : function(result){
						//console.log( result.trip.distance_estimate);
						//console.log(result.trip.duration_estimate);
						var uberParsedData = travelSearchDetailsJson.travelData;
						for(var i in uberParsedData){
							var mode = uberParsedData[i].mode.toLowerCase();
							if(mode.indexOf("uber") >= 0 || mode.indexOf("pool") >= 0 || mode.indexOf("wav") >= 0){
								travelSearchDetailsJson.travelData[i].distance = result.trip.distance_estimate;
								travelSearchDetailsJson.travelData[i].duration = Math.round(+result.trip.duration_estimate / +60);
							}
						}
					},
					error: function(response){
						console.log(response);
					}	
				});	
				
			}
			}
			
		});
	
}
