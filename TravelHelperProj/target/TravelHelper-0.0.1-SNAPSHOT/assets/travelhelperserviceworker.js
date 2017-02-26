console.log('Started', self);
self.addEventListener('install', function(event) {
  self.skipWaiting();
  console.log('Installed', event);
});
self.addEventListener('activate', function(event) {
  console.log('Activated', event);
});

self.addEventListener('notificationclick', function(event) {
	  console.log('[Service Worker] Notification click Received.');

	  event.notification.close();

	  event.waitUntil(
	    clients.openWindow('http://localhost:8080/TravelHelper/schedulehistory')
	  );
});


self.addEventListener('push', function(event) {
  console.log('Push message received', event);
  
  var title = 'Notification from Travel Helper';  
  var body = 'Time to start your scheduled travel to reach destination on time.';  
  var icon = 'travelstatic/img/travelhelperpush.png';  
  var tag = 'simple-push-demo-notification-tag';

  event.waitUntil(  
    self.registration.showNotification(title, {  
      body: body,  
      icon: icon,  
      tag: tag  
    })  
  ); 
  // TODO
});