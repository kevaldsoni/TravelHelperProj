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

var fetchlyftEstimatedPrice = function(latitudeStart,longitudeStart,endlatitude,endlongitude){
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
	    		lyftDetailsJson.lyftDet.push({
	    			"display_name" : estimatePrice[i].display_name,
	    			"max_cost" : (estimatePrice[i].estimated_cost_cents_max)/100,
	    			"min_cost" : (estimatePrice[i].estimated_cost_cents_min)/100,
	    			"distance" : estimatePrice[i].estimated_distance_miles,
	    			"time_estimate" : (estimatePrice[i].estimated_duration_seconds)/60
	    		});
	    	}
	    	console.log(lyftDetailsJson);
	    	showLyftDetails(lyftDetailsJson);
	    },
	    error: function(response){
	    	console.log(response);
	    }	
	});
}


var fetchLyftTimeEstimate = function(latitudeStart,longitudeStart){

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
    },
    error: function(response){
    	console.log(response);
    }	
});


}