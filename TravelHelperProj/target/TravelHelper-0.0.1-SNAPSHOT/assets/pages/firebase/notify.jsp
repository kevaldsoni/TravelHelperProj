<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>trying notification</title>
<link rel="manifest" href="manifest.json">
<script type="text/javascript" src="../../travelstatic/js/pushMessaging.js"></script>
<script type ="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../../travelstatic/js/customNotify.js"></script>
</head>
<body>
<button class="js-push-button">
  Enable Push Messages  
</button>

<button onclick="sendGCMPush();">
Call Notification
</button>
</body>
</html>