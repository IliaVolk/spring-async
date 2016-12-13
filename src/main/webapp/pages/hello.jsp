<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<html>

<head>
	<script src="<s:url value="/resources/js/angular.min.js"/>"></script>
</head>
<body ng-app="main">
	<h1>Messages</h1>
	<div ng-controller="MainController as ctrl">
		<blockquote ng-repeat="m in ctrl.messages">
			Message: {{m}}
		</blockquote>
	</div>
	<button ng-click="ctrl.doRequest()">Do Request</button>

	<h1>${message}</h1>
</body>
</html>