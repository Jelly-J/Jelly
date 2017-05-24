<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/resource/jsLib/angularJS.js'/>"></script>
<script src="<c:url value='/resource/jsLib/jquery-1.9.0.js'/>"></script>
<script src="<c:url value='/resource/jsLib/jquery.qrcode.min.js'/>"></script>
<script>
	var myAngular = angular.module("myAngular", []);
	myAngular.controller("myAdd", function($scope, $http, $window) {
		$scope.name = "Hello world!";
		$scope.clickes = function() {

		}
	})

	myAngular
			.controller(
					"requestAuton",
					function($scope, $http, $window) {
						$scope.anniu = function() {
							var data = {
								'name' : $scope.name,
								'password' : $scope.password
							}, transFn = function(data) {
								return $.param(data);
							}, postCfg = {
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
								},
								transformRequest : transFn
							};

							// 							$http.get("index/login?name="+$scope.name+"&password="+$scope.password).success(
							//                                     function(req, stauts, headers, config) {
							//                                         $window.alert(req);
							//                                         $scope.sku = req;
							//                                     });

							$http.post("index/login", data, postCfg).success(
									function(req) {
										$window.alert(req);
										$scope.sku = req;
									});

							//                          .then(
							//                                  function(response) {
							//                                      $window.alert(response.data);
							//                                      alert("${modelMap['accountName']}")
							//                                      $scope.sku = response.data;
							//                                  });
						}
						// $scope.anniu();
					})
</script>
</head>
<body ng-app="myAngular">
  <%--     <a href="<c:url value='views/index.jsp'/>" target="_blank">button</a> --%>

  <form action="index/login" method="post">
    <label>用户名：</label><input type="text" ng-model="name" value=""> <label>密 码：</label><input type="password"
      ng-model="password" value=""> <input type="submit" value="button">
  </form>


  <div ng-controller="requestAuton">
    <button ng-click="anniu()">按钮</button>
    <input type="text" ng-model="sku">
  </div>

  <div ng-controller="myAdd">
    {{name}} {{sku}} <input type="submit" ng-click="clickes()" value="button">

  </div>

  <input type="button" onclick="aaa()" value="barcode">

  <div id="qrcode"></div>
  <script>
			function utf16to8(str) {
				var out, i, len, c;
				out = "";
				len = str.length;
				for (i = 0; i < len; i++) {
					c = str.charCodeAt(i);
					if ((c >= 0x0001) && (c <= 0x007F)) {
						out += str.charAt(i);
					} else if (c > 0x07FF) {
						out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
						out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
						out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
					} else {
						out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
						out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
					}
				}
				return out;
			}

			$('#qrcode').qrcode({
				width : 300,
				height : 300,
				text : utf16to8("艹你妈那个逼！！！")
			});
		</script>

  <form action="index/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" /> <input type="submit" value="Submit" />
  </form>

</body>
</html>