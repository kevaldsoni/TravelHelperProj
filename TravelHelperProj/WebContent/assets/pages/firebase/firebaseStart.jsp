<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://www.gstatic.com/firebasejs/3.6.4/firebase.js"></script>
<script type="text/javascript" src="../../travelstatic/js/pushMessaging.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyDFET5hkDKo-wc1LmDLxR7Mc1-WbgUkK-A",
    authDomain: "travelhelper-141318.firebaseapp.com",
    databaseURL: "https://travelhelper-141318.firebaseio.com",
    storageBucket: "travelhelper-141318.appspot.com",
    messagingSenderId: "408223607451"
  };
  firebase.initializeApp(config);
</script>
<script>
	const messaging = firebase.messaging();
	messaging.requestPermission()
	.then(function() {
	  console.log('Notification permission granted.');
	  // TODO(developer): Retrieve an Instance ID token for use with FCM.
	  // ...
	})
	.catch(function(err) {
	  console.log('Unable to get permission to notify.', err);
	});
	
	// Get Instance ID token. Initially this makes a network call, once retrieved
	  // subsequent calls to getToken will return from cache.
	  messaging.getToken()
	  .then(function(currentToken) {
	    if (currentToken) {
	      sendTokenToServer(currentToken);
	      updateUIForPushEnabled(currentToken);
	    } else {
	      // Show permission request.
	      console.log('No Instance ID token available. Request permission to generate one.');
	      // Show permission UI.
	      updateUIForPushPermissionRequired();
	      setTokenSentToServer(false);
	    }
	  })
	  .catch(function(err) {
	    console.log('An error occurred while retrieving token. ', err);
	   // showToken('Error retrieving Instance ID token. ', err);
	    setTokenSentToServer(false);
	  });
	
	
	// Callback fired if Instance ID token is updated.
	messaging.onTokenRefresh(function() {
	  messaging.getToken()
	  .then(function(refreshedToken) {
	    console.log('Token refreshed.');
	    // Indicate that the new Instance ID token has not yet been sent to the
	    // app server.
	    setTokenSentToServer(false);
	    // Send Instance ID token to app server.
	    sendTokenToServer(refreshedToken);
	    // ...
	  })
	  .catch(function(err) {
	    console.log('Unable to retrieve refreshed token ', err);
	    showToken('Unable to retrieve refreshed token ', err);
	  });
	});
</script>
</head>
<body>

<button class="js-push-button" disabled>
  Enable Push Messages  
</button>
</body>
</html>