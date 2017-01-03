/**
 * 
 */

var GCM_REGISTERATION_ID = "fj7vk2Z9ga4:APA91bHS8Vx_Idr3O3NHUPvpS_3B7MXDndRotUalC6UvKE7orafpLFsIWbO7HbW8AVLwD5KJ9QpY1F4Bfxsd4Sm-f3pLR0glNfMkCXhwaO4ZByUDAM-k1MUVXrf9yn223GW3h05V0QKW";

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
   		url 	 : "https://fcm.googleapis.com/fcm/send",
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