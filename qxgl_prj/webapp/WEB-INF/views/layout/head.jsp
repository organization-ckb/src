<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>苏州持创crm系统</title>
<link type="text/css" rel="stylesheet" media="all"
	href="${path }/resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="${path}/resources/css/global_color.css" />
<script type="text/javascript" src="${path}/resources/js/global.js"></script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<img src="${path}/resources/img/logo.png" class="imglogo" alt="logo" />
		<c:if test="${sessionScope.nickname!=null}">
		<div>
			<img class="headerimg" src="../resources/img/manager.png"> <a>${sessionScope.nickname}</a>
			<img class="headerimg" src="../resources/img/cancel.png"> <a
				href="${path}/project/logout">注销</a>
		</div>
		</c:if>
		
	</div>
</body>
</html>