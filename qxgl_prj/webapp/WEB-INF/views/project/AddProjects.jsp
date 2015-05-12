<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员信息</title>
<link type="text/css" rel="stylesheet" media="all"href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../resources/css/global_color.css" />
<script src="${path}/resources/laydate/laydate.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/static/css/ui2.css?2013032917">
<script type="text/javascript">
function over() {
	$("#first").css({"background":"#fff","color":"#697d84"});
}
function overes() {
	$("#first").css({"background":"#3CB371","color":"#fff"}); 
}
</script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<img src="../resources/img/logo.png" class="imglogo" alt="logo" />
	    <div>
		<img  class="headerimg" src="../resources/img/manager.png"> 
		<a>${sessionScope.nickname}</a>
		<img  class="headerimg" src="../resources/img/cancel.png"> 
		<a href="${path}/project/logout">注销</a>
       </div>
	</div>

	<!--主要区域开始-->
	<div id="main">
		<div id="mains">
			<div id="contents">
			<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
			</div>
			<div id="record">
				<div id="subfield">分栏</div>
				<div class="tables">
					<table id="tableslist">
						<tr>
							<th>项目名称</th>
							<td>${project.proName }</td>
							<th>合同编号</th>
							<td>${project.bargainNo}</td>
						</tr>
						<tr>
							<th>开始时间</th>
							<fmt:formatDate var="starttime" value="${project.starttime}"
								type="both" pattern="yyyy-MM-dd " />
							<td>${starttime}</td>
							<th>结束时间</th>
							<fmt:formatDate var="endtime" value="${project.endtime}"
								type="both" pattern="yyyy-MM-dd " />
							<td>${endtime}</td>
						</tr>
					</table>
				</div>
				<div id="two" class="columns">
					
					<div class="all">
						<c:forEach var="programmer" items="${programmers}"
							varStatus="index">
							<!-- 控制随机样式 list backc+数字-->
							<%int number =(int)(Math.random()*11+1);%>
							<c:set var="randomnum" value="<%=number%>" />
							<!-- 控制跳转到的DIV #signup-modal+循环数-->
							<div class="bll">	
							<a data-toggle="modal"  href="#signup-modal${index.count}">
							<div class="list backc${randomnum}" >${programmer.programmerName}</div>
							</a>
							</div>

							<div class="modal in" id="signup-modal${index.count}">
								<a class="close" data-dismiss="modal">×</a>
								<h1>设置工作时间</h1>
								<form class="signup-form clearfix" method="post" action="${path}/project/addProgrammers">
								        <input type="hidden" name="project.id" value="${project.id}">
								        <input type="hidden" name="status" value="${project.status}">
								        <input type="hidden" name="programmer.id" value="${programmer.id}">
								        <input type="hidden" name="programmer.programmerStatus" value="${programmer.programmerStatus}">
									    <input type="text"  readonly="readonly" onFocus="this.blur()" value="${programmer.programmerName}"> 
										<input name="beginTime" type="text" placeholder="开始时间：" onclick="laydate()" > 
										<input name="endTime" type="text" placeholder="结束时间：" onclick="laydate()" > 
									    <input type="submit" name="type" class="button-blue reg"
										    value="添加" data-category="UserAccount" data-action="regist">
								</form>
							</div>
						</c:forEach>
						<script src="${path}/resources/static/js/landing-min.js?2013032917"></script>
					</div>

				</div>
			</div>

		</div>
	</div>

</body>
</html>