/**
 * 
 */
var lyftDetailsJson = { lyftDet:[]};
var latitudeStart;
var longitudeStart;
var latitudeEnd;
var longitudeEnd;

var fetchLyftDetails = function(latitudeStart,longitudeStart){
	
	$.ajax({
		type: "GET",
		url : "https://api.lyft.com/v1/ridetypes",
		headers : {
			Authorization : "Bearer " + "gAAAAABX_BGK2BngTKxPmydHT6OML9-8flDomuKFPcN0xuNJmrqVBplvYqch13SXtmLPMdZCLxJnRwjO5XFkSKSa05fLTCJfxDUWSL0602e_hC2grt9xB3Zj_9Tv0s29_t3Sxm9V1GDseDgbKZkoqFSm603NmPZgeFae0SuqwrJPvOyF5KtlUp0="
		},
		data: {
			
			lat: latitudeStart,
			lng: longitudeStart,
	        
	    },
	    success : function(result){
	    	console.log(result);
	    },
	    error: function(response){
	    	console.log(response);
	    }	
	});
}

var fetchlyftEstimatedPrice = function(latitudeStart,longitudeStart,endlatitude,endlongitude,travelSearchDetailsJson){
	return new Promise(function(resolve,reject){
	$.ajax({
		type: "GET",
		url : "https://api.lyft.com/v1/cost",
		headers : {
			Authorization : "Bearer " + "gAAAAABX_BGK2BngTKxPmydHT6OML9-8flDomuKFPcN0xuNJmrqVBplvYqch13SXtmLPMdZCLxJnRwjO5XFkSKSa05fLTCJfxDUWSL0602e_hC2grt9xB3Zj_9Tv0s29_t3Sxm9V1GDseDgbKZkoqFSm603NmPZgeFae0SuqwrJPvOyF5KtlUp0="
		},
		data: {
			start_lat: latitudeStart,
			start_lng: longitudeStart,
			end_lat: endlatitude,
			end_lng : endlongitude
		},
	    success : function(result){
	    	
	    	estimatePrice = result.cost_estimates;
	    	for(var i in estimatePrice){
	    		/*lyftDetailsJson.lyftDet.push({
	    			"display_name" : estimatePrice[i].display_name,
	    			"max_cost" : (estimatePrice[i].estimated_cost_cents_max)/100,
	    			"min_cost" : (estimatePrice[i].estimated_cost_cents_min)/100,
	    			"distance" : estimatePrice[i].estimated_distance_miles,
	    			"time_estimate" : (estimatePrice[i].estimated_duration_seconds)/60
	    		});*/
	    		var capacity = 0;
	    		var name = estimatePrice[i].display_name;
	    		switch(name){
	    			case 'Lyft' : capacity=3; break;
	    			case 'Lyft Plus' : capacity=10; break;
	    		}
	    		if(estimatePrice[i].display_name){}
	    		travelSearchDetailsJson.travelData.push({
	        		  	"mode" : estimatePrice[i].display_name,
		    			"distance" : estimatePrice[i].estimated_distance_miles,
		    			"duration" : Math.round((estimatePrice[i].estimated_duration_seconds)/60),
		    			"cost" : Math.round((+(estimatePrice[i].estimated_cost_cents_max)+ +(estimatePrice[i].estimated_cost_cents_min))/200),
		    			"capacity" : capacity
		    	});
	    		
	    	}
	    	//console.log(lyftDetailsJson);
	    	//showLyftDetails(lyftDetailsJson);
	    	
	    	resolve();
	    },
	    error: function(response){
	    	console.log(response);
	    }	
	});
	});
}


var fetchLyftTimeEstimate = function(latitudeStart,longitudeStart,travelSearchDetailsJson){
	return new Promise(function(resolve,reject){
		$.ajax({
			type: "GET",
			url : "https://api.lyft.com/v1/eta",
			headers : {
				Authorization : "Bearer " + "gAAAAABX_BGK2BngTKxPmydHT6OML9-8flDomuKFPcN0xuNJmrqVBplvYqch13SXtmLPMdZCLxJnRwjO5XFkSKSa05fLTCJfxDUWSL0602e_hC2grt9xB3Zj_9Tv0s29_t3Sxm9V1GDseDgbKZkoqFSm603NmPZgeFae0SuqwrJPvOyF5KtlUp0="
			},
			data: {
				lat: latitudeStart,
				lng: longitudeStart,	
		    },
		    success : function(result){
		    	console.log(result);
		    	data = result.eta_estimates;
		    	//var parsedData = travelSearchDetailsJson.travelData;
		    	for(var i in data){
					var driveType = data[i].display_name;
					estimate = data[i].eta_seconds;
					for(var i in travelSearchDetailsJson.travelData){
						if(driveType == travelSearchDetailsJson.travelData[i].mode){
							travelSearchDetailsJson.travelData[i].time_estimate = (estimate/60.0);
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