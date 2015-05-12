<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="../resources/static/css/ui2.css?2013032917">
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global_color.css" />
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	function over() {
		$("#first").css({
			"background" : "#fff",
			"color" : "#697d84"
		});
	}

	function overes() {
		$("#first").css({
			"background" : "#3CB371",
			"color" : "#fff"
		});
	}
	$(function() {
		$("#big").mouseover(function() {
			$("#smalls").show();//表示display:block, 
			$("#seconds").css({
				"background" : "#3CB371",
				"color" : "#fff"
			});

		});
		$("#big").mouseleave(function() {
			$("#smalls").hide();//表示display:none; 
			$("#seconds").css({
				"background" : "#fff",
				"color" : "#697d84"
			});
		});
	});
</script>
</head>
<body>
	<div class="sysmenua" onMouseOut="overes()">
		<ul>
			<c:if test="${sessionScope.loginUser.userType==0}">
			<c:if test="${sessionScope.mark=='project' }">
			   <!-- 项目管理 -->
				<li id="big">
				<a id="first" class="current" href="${path}/project/projectManage?mark=project">项目管理</a>
				<ul id="smalls" style="display: none;">
					<li><a class="small" href="${path}/project/addPro">添加项目</a></li>
					<li><a class="small" href="${path}/project/getExtensionInfo">续约记录</a></li>
				</ul>
				</li>
				<li><a href="${path}/customer/customerManage?mark=client" onmouseover="over()" class="big">客户管理</a></li>
				<li><a href="${path}/resource/resourceManage?mark=resource" onmouseover="over()" class="big">资源管理</a></li>
				</c:if>
				<c:if test="${sessionScope.mark=='client'}">
			   <!--  客户管理-->
				<li id="big" onmouseover="over()">
				<a id="seconds" class="big"   href="${path}/project/projectManage?mark=project" >项目管理</a>
				<ul id="smalls" style="display: none;"  >
					<li><a class="small" href="${path}/project/addPro" >添加项目</a></li>
					<li><a class="small" href="${path}/project/getExtensionInfo">续约记录</a></li>
				</ul>
				</li>
				<li><a  id="first"  href="${path}/customer/customerManage?mark=client" class="current" >客户管理</a></li>
				<li><a href="${path}/resource/resourceManage?mark=resource" onmouseover="over()" class="big">资源管理</a></li>
				</c:if>
				<c:if test="${sessionScope.mark=='resource'}">
				<!-- 资源管理 -->
				<li id="big" onmouseover="over()">
				<a id="seconds" class="big"   href="${path}/project/projectManage?mark=project" >项目管理</a>
				<ul id="smalls" style="display: none;"  >
					<li><a class="small" href="${path}/project/addPro" >添加项目</a></li>
					<li><a class="small" href="${path}/project/getExtensionInfo">续约记录</a></li>
				</ul>
				</li>
				<li><a   href="${path}/customer/customerManage?mark=client" onmouseover="over()" class="big">客户管理</a></li>
				<li><a  id="first" href="${path}/resource/resourceManage?mark=resource"class="current"  >资源管理</a></li>
				</c:if>
			</c:if>
			
			<c:if test="${sessionScope.loginUser.userType!=0}">
				<li><a class="current" href="${path}/project/projectManage">我的项目</a></li>
			</c:if>
		</ul>
	</div>
	<div class="bottom">
		<img src="../resources/img/video.png" class="imgs"> <a href="#">视频会议</a>
		<img src="../resources/img/phone.png" class="imges"> <a href="#">在线客服</a>
	</div>
</body>
</html>