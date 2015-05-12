<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:8080/cccrm/resources/css/addprogrammer.css" type="text/css"></link>
<script type="text/javascript" src="http://localhost:8080/cccrm/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/cccrm/resources/js/addmanager.js"></script>
</head>
<body>
<form action="#">
	<center>
		<jsp:include page="/WEB-INF/views/layout/head.jsp" />
			<div class="bottom">
				<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
				<div class="main">
					<div class="one">登录名</div><div class="two"><input type="text" name="loginname" id="loginname"></div><br/>
					<div class="one">密&nbsp;&nbsp;码</div><div class="two"><input type="password" name="loginpwd" id="loginpwd"></div><br/>
					<div class="one">邮&nbsp;&nbsp;箱</div><div class="two"><input type="text" name="useremail" id="useremail"></div><br/>
					<div class="one">联系方式</div><div class="two"><input type="text" name="usermobile" id="usermobile"></div><br/>
					<div class="one"></div><div class="two"><input type="submit" name="addmanager" id="addmanager" value="确认新增"></div><br/>
				</div>
			</div>
	</center>
	</form>
</body>
</html>