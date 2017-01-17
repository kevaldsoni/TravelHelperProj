console.log('Started', self);
self.addEventListener('install', function(event) {
  self.skipWaiting();
  console.log('Installed', event);
});
self.addEventListener('activate', function(event) {
  console.log('Activated', event);
});
self.addEventListener('push', function(event) {
  console.log('Push message received', event);
  
  var title = 'Time to start Travel !!';  
  var body = 'Travel Helper has something to say. Kindly check.';  
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