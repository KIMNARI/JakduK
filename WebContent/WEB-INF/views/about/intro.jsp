<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    

<!DOCTYPE html>
<html ng-app="jakdukApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="../include/html-header.jsp"></jsp:include>
</head>
<body>
<div class="container">
<jsp:include page="../include/navigation-header.jsp"/>

<h4>K리그 작두왕</h4>
<p>K리그 작두왕은 'K리그', '작두', '왕' 세단어의 조합어이며 K리그의 경기결과를 신들린 것처럼 잘 맞추는 사람을 뜻합니다.</p>
<p>작두타기는 무당의 신내림 과정에서 나온 말입니다. 무당은 신통력을 사람들에게 과시하기 위해 맨발로 작두를 타며, 사람들은 그것을 보고 놀라 <strong>"신 들렸다."</strong>라고 표현합니다.</p>
<p>반대로 무아지경 상태에 빠진 신들린 어떤 사람을 보고 <strong>"저 사람이 작두를 탄다."</strong>라고 합니다.</p> 
<hr/>
<h4>K리그 커뮤니티</h4>
<p>K리그 작두왕은 K리그 중심의 국내 축구 커뮤니티입니다.</p> 
<p>K리그 작두왕은 K리그 컨텐츠를 활용하여 게시판, 경기일정, 통계, 그래프, 갤러리 등의 기능을 앞으로 제공할 예정입니다.</p>
<p>또한 사용자가 직접 참여하여 만드는 승무패, 백과사전 등 사용자 참여형 컨텐츠를 구상중입니다.</p>
<hr/>
<h4>오픈 소스 프로젝트</h4>
<p>K리그 작두왕은 Spring Framework, Bootstrap, AngularJS 등 각종 오픈 소스를 활용하여 개발하였습니다.</p>
<p>마찬가지로 K리그 작두왕도 오픈소스이며 github를 통해 누구든지 개발에 참여할 수 있습니다. </p>
<p><a href="https://github.com/Pyohwan/JakduK">https://github.com/Pyohwan/JakduK</a></p>

<jsp:include page="../include/footer.jsp"/>
</div><!-- /.container -->

<!-- Bootstrap core JavaScript
  ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/resources/jquery/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>    

<script type="text/javascript">
var jakdukApp = angular.module("jakdukApp", []);
</script>

<jsp:include page="../include/body-footer.jsp"/>

</body>
</html>