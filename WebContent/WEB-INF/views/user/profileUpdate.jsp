<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
 
<!DOCTYPE html>
<html ng-app="jakdukApp">
<head>
	<jsp:include page="../include/html-header.jsp"></jsp:include>
	
	<link href="<%=request.getContextPath()%>/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container ng-controller="writeCtrl"">
<jsp:include page="../include/navigation-header.jsp"/>

<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<form:form commandName="userProfileWrite" name="userProfileWrite" action="${contextPath}/user/profile/update" method="POST" cssClass="form-horizontal"
ng-submit="onSubmit(userProfileWrite, $event)">
	<form:input path="usernameStatus" cssClass="hidden" size="0" ng-init="usernameStatus='${userProfileWrite.usernameStatus}'" ng-model="usernameStatus"/>
	<legend><spring:message code="user.profile.update"/> </legend>
  <div class="form-group">
			<label class="col-sm-2 control-label" for="email">
				<spring:message code="user.email"/>
			</label>
    <div class="col-sm-3">
    		<input type="email" name="email" class="form-control" size="50" placeholder="Email"
    		ng-init="email='${userProfileWrite.email}'" ng-model="email" disabled="disabled"/>
    </div>
  </div>
	<div class="form-group has-feedback" 
	ng-class="{'has-success':userProfileWrite.username.$valid, 
	'has-error':userProfileWrite.username.$invalid || usernameStatus == 'duplication'}">
		<label class="col-sm-2 control-label" for="username">
			<abbr title='<spring:message code="common.msg.required"/>'>*</abbr> <spring:message code="user.nickname"/>
		</label>
		<div class="col-sm-3">
			<form:input path="username" cssClass="form-control" size="50" placeholder="Nickname" 
				ng-model="username" ng-init="username='${userProfileWrite.username}'" ng-blur="onUsername(userProfileWrite)"
				ng-required="true" ng-minlength="2" ng-maxlength="20"/>
			<span class="glyphicon form-control-feedback" 
			ng-class="{'glyphicon-ok':userProfileWrite.username.$valid, 
			'glyphicon-remove':userProfileWrite.username.$invalid || usernameStatus == 'duplication'}"></span>
			<i class="fa fa-spinner fa-spin" ng-show="usernameConn == 1"></i>					
			<form:errors path="username" cssClass="text-danger" element="span" ng-hide="usernameAlert.msg"/>
			<span class="{{usernameAlert.classType}}" ng-show="usernameAlert.msg" ng-init="onUsername(userProfileWrite)">{{usernameAlert.msg}}</span>		
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="supportFC">
			<spring:message code="user.support.football.club"/>
		</label>
		<div class="col-sm-3">
			<form:select path="footballClub" cssClass="form-control">
				<form:option value=""><spring:message code="common.none"/></form:option>
				<c:forEach items="${footballClubs}" var="club">
					<c:forEach items="${club.names}" var="name">
						<form:option value="${club.id}" label="${name.fullName}"/>
					</c:forEach>
				</c:forEach>
			</form:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="about"> <spring:message code="user.comment"/></label>
		<div class="col-sm-4">
			<form:textarea path="about" cssClass="form-control" cols="40" rows="5" placeholder="About"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<input type="submit" value="<spring:message code="common.button.submit"/>" class="btn btn-default"/>
			<a class="btn btn-danger" href="<c:url value="/user/profile"/>"><spring:message code="common.button.cancel"/></a>
		</div> 
	</div>	
  	
</form:form>

<jsp:include page="../include/footer.jsp"/>
</div><!-- /.container -->

<!-- Bootstrap core JavaScript ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/resources/jquery/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
var jakdukApp = angular.module("jakdukApp", []);

jakdukApp.controller("writeCtrl", function($scope, $http) {
	$scope.usernameConn = 0;
	$scope.usernameAlert = {};
	
	$scope.onSubmit = function(userProfileWrite, event) {
		if (userProfileWrite.$valid && $scope.usernameStatus == "ok") {
		} else {			
			if (userProfileWrite.username.$invalid) {
				checkUsername(userProfileWrite);
			} else if ($scope.usernameStatus == "duplication") {
				$scope.usernameAlert = {"classType":"text-danger", "msg":'<spring:message code="user.msg.replicated.data"/>'};
			} else {
				$scope.usernameAlert = {"classType":"text-danger", "msg":'<spring:message code="common.msg.error.shoud.check.redudancy"/>'};
			}

			event.preventDefault();
		}
	};
	
	$scope.onUsername = function(userProfileWrite) {
		if (userProfileWrite.username.$valid) {
			var bUrl = '<c:url value="/check/user/update/username.json?username=' + $scope.username + '"/>';
			if ($scope.usernameConn == 0) {
				var reqPromise = $http.get(bUrl);
				$scope.usernameConn = 1;
				reqPromise.success(function(data, status, headers, config) {
					if (data.existUsername != null) {
						if (data.existUsername == false) {
							$scope.usernameStatus = "ok";
							$scope.usernameAlert = {"classType":"text-success", "msg":'<spring:message code="user.msg.avaliable.data"/>'};
						} else {
							$scope.usernameStatus = "duplication";
							$scope.usernameAlert = {"classType":"text-danger", "msg":'<spring:message code="user.msg.replicated.data"/>'};
						}
					}
					$scope.usernameConn = 0;
				});
				reqPromise.error(usernameError);
			}
		} else {
			checkUsername(userProfileWrite);
		}
	};
	
	function usernameError(data, status, headers, config) {
		$scope.usernameConn = 0;
		$scope.usernameAlert = {"classType":"text-danger", "msg":'<spring:message code="common.msg.error.network.unstable"/>'};
	}
	
	function checkUsername(userProfileWrite) {
		
		if (userProfileWrite.username.$error.required) {
			$scope.usernameAlert = {"classType":"text-danger", "msg":'<spring:message code="common.msg.required"/>'};
		} else if (userProfileWrite.username.$error.minlength || userProfileWrite.username.$error.maxlength) {
			$scope.usernameAlert = {"classType":"text-danger", "msg":'<spring:message code="Size.userWrite.username"/>'};
		}
	}
	
});
</script>

<jsp:include page="../include/body-footer.jsp"/>

</body>
</html>