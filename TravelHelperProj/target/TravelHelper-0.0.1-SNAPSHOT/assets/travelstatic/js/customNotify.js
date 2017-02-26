/**
 * 
 */

var GCM_REGISTERATION_ID = "c0AuKMQFjG8:APA91bGs367F5hnBSLf3UXJ-WatyKoMKdH14LzT1kQyRW_mj3VjHX2wgahgkEB5Hnoes63EolK93VHAHThJ_3HfG7j2k1ZY8r5Y2LpBeJXy3K6nYZ1zANFLxMWFnpa0cW5OH4GpXf-Me";

var sendGCMPush = function (){
	console.log("GCM Push");
	
	/*$http.defaults.useXDomain = true;
	var inputData = "registration_id="+GCM_REGISTERATION_ID;
	$http({
		method : 'POST',
		url: "https://gcm-http.googleapis.com/gcm/send",
		headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Authorization':'key=AIzaSyAV35mE_LvQQGypf7UjPJiJbf3v7bO6LU0'},
		data : inputData,
		xhrFields: {
		    withCredentials: true
		  }
	}).then(function successCallback(response) {
			console.log(response);
		},function errorCallback(response){
			console.log(response);
		});*/
	var inputData = "registration_id="+GCM_REGISTERATION_ID;
	$.ajax({
   		type 	 : "POST",
   		url 	 : "https://gcm-http.googleapis.com/gcm/send",
   		data	 : inputData,
   		headers: {'Content-Type': 'application/x-www-form-urlencoded', 'Authorization':'key=AIzaSyAAZwbKFn9it2GY9bZWJwwvzYzdVKZiEno'},
   		cache 	 : false,
   		async    : false,
   		complete : function(html){
   			var response = html.responseText;
   			response = response.replace(/^\s+|\s+$/g,'');
   			if(response!=null && response != ''){
   				//document.getElementById("barter_post_results").innerHTML = response;
   				
   			console.log(response);	
   					
	    	}
   			
   		}
		});
};