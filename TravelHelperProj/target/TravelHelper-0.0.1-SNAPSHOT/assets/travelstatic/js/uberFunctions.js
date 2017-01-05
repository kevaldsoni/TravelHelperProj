/**
 * 
 */

var uberDetailsJson = { uberDet:[]};
var latitudeStart;
var longitudeStart;
var latitudeEnd;
var longitudeEnd;

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
						
						travelSearchDetailsJson.travelData[i].cost = (+minCost + +maxCost)/2;
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

var fetchRideEstimate = function(productId,latitudeStart,longitudeStart,destLatitude,destLongitude){
	alert(productId+" "+latitudeStart+" "+longitudeStart+" ");
	var estimate = ""
		$.ajax({
			type: "POST",
			url : "https://sandbox-api.uber.com/v1.2/requests/estimate",
			headers : {
				Authorization : "Token" + "qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN"
			},
			data: {
				server_token : 'qJxh0ZpirokeFfw-atLSxecxs83OuYgzDYcGHBkN',
				product_id:productId,
				start_latitude: latitudeStart,
				start_longitude: longitudeStart,
				end_latitude: destLatitude,
				end_longitude: destLongitude,
					
			},
			success : function(result){
	    	
				console.log(result);
				
			},
			error: function(response){
				console.log(response);
			}	
		});	
	
}

var getAccessToken = function(){
	jQuery.support.cors = true;
	$.ajax({
		type: "GET",
		url : "https://login.uber.com/oauth/v2/authorize",
		data: {
			client_id : 'KqtkzZ7YHw8n6PJ96CA0wMJNbdeEO-mm',
			response_type:'code'
		},
		success : function(result){
    	
			console.log(result);
			
		},
		error: function(response){
			console.log(response);
		}	
	});
	
}