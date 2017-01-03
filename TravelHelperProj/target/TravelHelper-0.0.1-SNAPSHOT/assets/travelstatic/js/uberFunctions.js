/**
 * 
 */

var uberDetailsJson = { uberDet:[]};
var latitudeStart;
var longitudeStart;
var latitudeEnd;
var longitudeEnd;

var fetchUberDetails = function (latitudeStart,longitudeStart,destLatitude,destLongitude){
	
	getAccessToken();
	//fetchUberProductDetails(latitudeStart,longitudeStart).then(fetchUberEstimatedPrice(latitudeStart,longitudeStart,destLatitude,destLongitude)).then(b);
	//return uberDetailsJson;
}
var b = function(){
	console.log("in b");
	console.log(uberDetailsJson);
}

var fetchUberProductDetails = function(latitudeStart,longitudeStart,destLatitude,destLongitude){
	var defer = $.Deferred();
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
	    	
	    	var products = result.products;
	    	for(var i in products){
	    		uberDetailsJson.uberDet.push({
	    			"display_name" : products[i].display_name,
	    			"product_id" : products[i].product_id
	    		});
	    		
	    	}
	    	var data = uberDetailsJson.uberDet;
	    	for(var i in data){
	    		var productId = data[i].product_id;
	    		var displayName = data[i].display_name;
	    		//console.log(displayName+" "+productId);
	    		fetchUberTimeEstimate(productId,latitudeStart,longitudeStart);
	    		fetchRideEstimate(productId,latitudeStart,longitudeStart,destLatitude,destLongitude);
	    	}
	    	
	    },
	    error: function(response){
	    	console.log(response);
	    }	
	});
	setTimeout(function() {
        defer.resolve(); // When this fires, the code in a().then(/..../); is executed.
    }, 5000);

    return defer;
}

var fetchUberEstimatedPrice = function(latitudeStart,longitudeStart,endlatitude,endlongitude){
	var defer = $.Deferred();
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
	    	
	    	var uberParsedData = uberDetailsJson.uberDet;
	    	var data = result.prices;
	    	for(var i in data){
	    		var driveType = data[i].display_name;
	    		var estimate = data[i].estimate;
	    		//console.log(driveType+" "+estimate);
	    		for(var i in uberParsedData){
					if(driveType == uberParsedData[i].display_name){
						uberParsedData[i].price_estimate = estimate;
						var money = estimate.split("-");
						uberParsedData[i].max_cost = money[1];
						uberParsedData[i].min_cost = money[0].charAt(1);
						uberParsedData[i].avg_cost = (+money[1] + +money[0].charAt(1))/2;
						//alert(uberParsedData[i].avg_cost);
					}
				}
	    	}
	    
	    },
	    error: function(response){
	    	console.log(response);
	    	
	    }	
	});
	setTimeout(function() {
        defer.resolve(); // When this fires, the code in a().then(/..../); is executed.
    }, 5000);
}


var fetchUberTimeEstimate = function(productId,latitudeStart,longitudeStart){
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
			var uberParsedData = uberDetailsJson.uberDet;
			for(var i in data){
				var driveType = data[i].display_name;
				estimate = data[i].estimate;
				for(var i in uberParsedData){
					if(driveType == uberParsedData[i].display_name){
						uberParsedData[i].time_estimate = (estimate/60.0);
					}
				}
			}
			
		},
		error: function(response){
			console.log(response);
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