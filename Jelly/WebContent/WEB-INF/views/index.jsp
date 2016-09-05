<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/resource/jsLib/angularJS.js'/>"></script>
<script src="<c:url value='/resource/jsLib/jquery-1.9.0.js'/>"></script>
<script>
	var myAngular = angular.module("myAngular", []);
	myAngular.controller("myAdd", [ "$scope", function($scope) {
		$scope.name = "Hello world!";
	} ])
</script>
</head>
<body ng-app="myAngular">
  <div ng-controller="myAdd">{{name}}</div>
  <c:forEach items="${accountName }" var="m">
        ${m.value }
  </c:forEach>
</body>
</html>