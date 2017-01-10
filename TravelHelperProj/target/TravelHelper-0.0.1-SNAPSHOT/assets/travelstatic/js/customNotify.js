/**
 * 
 */

var GCM_REGISTERATION_ID = "ejaU9aATa1E:APA91bHbKnXHVurS1ShHmjhf3CJc1GcrCOBlRCjlBVsfFKO4uXmLyOffsPwi87fGQkC-bZgcRR-ISXgU-DwJ49zPxWosvVZE549-JDzHzekav18GkaOFSoC9jPqZhZTIHrUN7Wh-A5tX";

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