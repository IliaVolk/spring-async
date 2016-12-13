<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<html>

<head>
	<script src="<s:url value="/resources/js/angular.min.js"/>"></script>
	<script src="<s:url value="/resources/js/main_angular_app.js"/>"></script>
</head>
<body ng-app="main">
	<h1>Messages</h1>
	<div ng-controller="MainController as ctrl">
		<blockquote ng-repeat="m in ctrl.messages">
			Message: {{m}}
		</blockquote>
		<button ng-click="ctrl.doRequest()">Do Request</button>
		<button ng-click="ctrl.doAnotherRequest()">Do Other Request</button>
		<p>Answer for other request : {{ctrl.anotherRequestAnswer}}</p>
	</div>

</body>
</html>