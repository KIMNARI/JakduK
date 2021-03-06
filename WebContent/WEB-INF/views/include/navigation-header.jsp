<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h4><a class="text-muted" href="<c:url value="/home"/>"><spring:message code="common.jakduk"/></a></h4>

<div class="navbar navbar-default" role="navigation">
	<div class="container-fluid" ng-controller="headerCtrl">
		<nav>
			<ul class="nav nav-pills navbar-nav">
				<li ng-class="{active:isActive('<c:url value="/about"/>')}">
					<a href="<c:url value="/about"/>"><spring:message code="about"/></a>
				</li>
				<li ng-class="{active:isActive('<c:url value="/board"/>')}">
					<a href="<c:url value="/board"/>"><spring:message code="board"/></a>
				</li>
			</ul>
			<ul class="nav nav-pills navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span class="visible-sm visible-md visible-lg"><span class="glyphicon glyphicon-globe"></span> <spring:message code="common.language"/> <span class="caret"></span></span>
						<span class="visible-xs"><spring:message code="common.language"/> <span class="caret"></span></span>						
					</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?lang=ko"><spring:message code="common.language.korean"/></a></li>
						<li><a href="?lang=en"><spring:message code="common.language.english"/></a></li>
					</ul>
				</li>
				<sec:authorize access="isAnonymous()">
					<li>
						<a href="<c:url value="/login"/>">
							<span class="visible-sm visible-md visible-lg"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="common.login"/></span>
							<span class="visible-xs"><spring:message code="common.login"/></span>
						</a>
					</li>
	  		</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.username" var="userName"/>
					<sec:authentication property="principal.type" var="userType"/>
					<li class="dropdown" ng-class="{active:isActive('<c:url value="/user"/>') || isActive('<c:url value="/oauth"/>')}">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<span class="visible-sm visible-md visible-lg"><span class="glyphicon glyphicon-user"></span> ${userName} <span class="caret"></span></span>
							<span class="visible-xs">${userName} <span class="caret"></span></span>							
						</a>
						<ul class="dropdown-menu" role="menu">
							<c:choose>
								<c:when test="${userType == 'jakduk'}">
									<li>
										<a href="<c:url value="/user/profile"/>"><span class="glyphicon glyphicon-wrench"></span> <spring:message code="user.profile"/></a></li>
								</c:when>
								<c:when test="${userType == 'facebook' || userType == 'daum'}">
									<li><a href="<c:url value="/oauth/profile"/>"><span class="glyphicon glyphicon-wrench"></span> <spring:message code="user.profile"/></a></li>
								</c:when>
							</c:choose>
							<li><a href="<c:url value="/logout"/>"><span class="glyphicon glyphicon-log-out"></span> <spring:message code="common.logout"/></a></li>
						</ul>
					</li>           
    		</sec:authorize>
	    </ul>          
	  </nav>
	</div><!-- /.container -->
</div><!-- /.navbar -->

<script src="<%=request.getContextPath()%>/resources/angular/js/angular.min.js"></script>
<script type="text/javascript">

</script>