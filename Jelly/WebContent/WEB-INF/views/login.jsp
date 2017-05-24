<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/resource/jsLib/angularJS.js'/>"></script>
<script src="<c:url value='/resource/jsLib/jquery-1.9.0.js'/>"></script>
</head>
<body>
		<form action="index/result.do" method="post">
			<label>用户名：</label><input type="text" ng-model="username">
			<label>密    码：</label><input type="password" ng-model="password">
			<input type="submit" value="submit">
		</form>

</body>
</html>