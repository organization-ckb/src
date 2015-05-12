<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/head.jsp" />

	<div id="main">
		<div id="mains">
			<div id="contents">
				<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
			</div>
			<form action="#">
				<center>

					<div class="success">客户新增成功!</div>
					<div>
						<a href="addcustomer">继续新增</a>
					</div>
					<div>
						<a href="customermanage">查看客户管理</a>
					</div>
				</center>
			</form>
		</div>
	</div>
</body>
</html>