/**
 * 
 */
"use strict";

var isPushEnabled = false;

window.addEventListener('load', function() {  
  var pushButton = document.querySelector('.js-push-button');
  
  pushButton.addEventListener('click', function() {
	console.log("Push clicked : isPushEnabled - "+isPushEnabled);
    if (isPushEnabled) {  
      unsubscribe();  
    } else {  
      subscribe();  
    }  
  });

  // Check that service workers are supported, if so, progressively  
  // enhance and add push messaging support, otherwise continue without it.  
  if ('serviceWorker' in navigator) {  
    navigator.serviceWorker.register('/TravelHelper/assets/travelhelperserviceworker.js')  
    .then(initialiseState);  
   
  } else {  
    console.warn('Service workers aren\'t supported in this browser.');  
  }  
});

  
function initialiseState() {  
  // Are Notifications supported in the service worker?  
	console.log("Initializing state now");
  if (!('showNotification' in ServiceWorkerRegistration.prototype)) {  
    console.warn('Notifications aren\'t supported.');  
    return;  
  }
  if (Notification.permission === 'denied') {  
    console.warn('The user has blocked notifications.');  
    return;  
  }
  
//Check if push messaging is supported  
  if (!('PushManager' in window)) {  
    console.warn('Push messaging isn\'t supported.');  
    return;  
  }
  
  navigator.serviceWorker.ready.then(function(serviceWorkerRegistration) {
	  
	  serviceWorkerRegistration.pushManager.getSubscription()  
      .then(function(subscription) { 
    	 
    	  if(subscription != undefined){ 
    		  console.log(subscription);
    		  $('#scheduleTravelForm').css({"display": "block"});
    		  //saveNotificationEndPoint(subscription.endpoint);
    	  }
    	  var pushButton = document.querySelector('.js-push-button');  
          pushButton.disabled = false;
          if (!subscription) {  
            // We aren't subscribed to push, so set UI  
            // to allow the user to enable push  
            return;  
          }
          pushButton.textContent = 'Disable Notification';  
          isPushEnabled = true;  
    	}).catch(function(err) {  
        console.warn('Error during getSubscription()', err);  
      });
	});
  
}


function subscribe() {  
	  var pushButton = document.querySelector('.js-push-button');  
	  //pushButton.disabled = true;
	  navigator.serviceWorker.ready.then(function(serviceWorkerRegistration) {  
	    serviceWorkerRegistration.pushManager.subscribe({userVisibleOnly:true})  
	      .then(function(subscription) {  
	        // The subscription was successful  
	        isPushEnabled = true;  
	        pushButton.textContent = 'Disable Notification';  
	        pushButton.disabled = false;
	        if(subscription != undefined){ 
	    		  console.log(subscription);
	    		  saveNotificationEndPoint(subscription.endpoint);
	    	  }
	        
	      })  
	      .catch(function(e) {  
	        if (Notification.permission === 'denied') {  
	          console.warn('Permission for Notifications was denied');  
	          pushButton.disabled = true;  
	        } else {  
	          console.error('Unable to subscribe to push.', e);  
	          pushButton.disabled = false;  
	          pushButton.textContent = 'Enable Notification';  
	        }  
	      });  
	  });  
	}

function unsubscribe() {  
	  var pushButton = document.querySelector('.js-push-button');  
	  pushButton.disabled = true;
	  navigator.serviceWorker.ready.then(function(serviceWorkerRegistration) {  
      serviceWorkerRegistration.pushManager.getSubscription().then(  
	      function(pushSubscription) {  
	          
	        if (!pushSubscription) {  
	          isPushEnabled = false;  
	          pushButton.disabled = false;  
	          pushButton.textContent = 'Enable Notification'; 
	          $('#scheduleTravelForm').css({"display": "none"});
	          return;  
	        }  
	        var subscriptionId = pushSubscription.subscriptionId;  
	        pushSubscription.unsubscribe().then(function(successful) {  
	          pushButton.disabled = false;  
	          pushButton.textContent = 'Enable Notification';  
	          isPushEnabled = false;  
	          $('#scheduleTravelForm').css({"display": "none"});
	        }).catch(function(e) {  
	         console.log('Unsubscription error: ', e);  
	          pushButton.disabled = false;
	          pushButton.textContent = 'Enable Notification';
	        });  
	      }).catch(function(e) {  
	        console.error('Error thrown while unsubscribing from push messaging.', e);  
	      });  
	  });  
	}

