<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRM系统</title>
<link rel="stylesheet" href="../resources/css/global.css"
	type="text/css" />
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../resources/js/cookie.min.js"></script>
<script type="text/javascript" src="../resources/js/resourcemanage.js"></script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<img src="../resources/img/logo.png" class="imglogo" alt="logo" />
		<div>
			<img class="headerimg" src="../resources/img/manager.png"> <a>${sessionScope.nickname}</a>
			<img class="headerimg" src="../resources/img/cancel.png"> <a
				href="${path}/project/logout">注销</a>
		</div>
	</div>
	<input type="hidden" value="${loginUser.loginName}"
		id="hidden-username" />
	<input type="hidden" value="${loginUser.userType}" id="hidden-usertype" />

	<div id="main">
		<div id="mains">
			<div id="contents">
				<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
			</div>
			<div id="record">
				<div id="subfield"></div>
				<div class="search_add">
					<div class="left">
						<form action="programmerSearch" method="post" id="programmersearch">
							<label>工种：</label>
							<select id="sel_department" name="department">
								<option value="-1">全部</option>
								<option value="1">JAVA</option>
								<option value="2">PHP</option>
								<option value="3">IOS</option>
								<option value="4">Android</option>
							</select>
							<input type="hidden" value="${department}"
								id="hidden-department" />
							<label>状态：</label>
							<select id="sel_status" name="status">
								<option value="-1">全部</option>
								<option value="0">空闲</option>
								<option value="1">项目中</option>
							</select>
							<input type="hidden" value="${status}" id="hidden-status" />
							<button id="btn_search">搜索</button>
						</form>
					</div>
					<div class="rights">
						<button onClick="addResouce();">新增资源</button>
					</div>
				</div>
				<div id="data">
					<table id="datalist">
						<tr id="tres">
							<th >工种</th>
							<th >姓名</th>
							<th >性别</th>
							<th >邮箱</th>
							<th >手机</th>
							<th>QQ</th>
							<th >状&nbsp;&nbsp;态</th>
							<th >操&nbsp;作</th>
						</tr>
						<c:forEach var="programmer" items="${programmers}">
							<tr id="tres">
								<td><c:choose>
										<c:when test="${programmer.department == 1}">JAVA</c:when>
										<c:when test="${programmer.department == 2}">PHP</c:when>
										<c:when test="${programmer.department == 3}">IOS</c:when>
										<c:when test="${programmer.department == 4}">Android</c:when>
									</c:choose></td>
								<td>${programmer.programmerName}</td>
								<td><c:choose>
										<c:when test="${programmer.programmerSex == 0}">男</c:when>
										<c:when test="${programmer.programmerSex == 1}">女</c:when>
									</c:choose></td>
								<td>${programmer.loginName}</td>
								<td>${programmer.userMobile}</td>
								<td>${programmer.programmerQq}</td>
								<td><c:choose>
										<c:when test="${programmer.programmerStatus == 0}">空闲</c:when>
										<c:when test="${programmer.programmerStatus == 1}">项目中</c:when>
									</c:choose></td>
								<td><a class="btn_delete" onclick="" id="Arch"
									href="<c:url value="/resource/viewProgrammer?id=${programmer.id}" />">查看</a>&nbsp;&nbsp;
									<a class="btn_delete" onclick=""
									href="<c:url value="/resource/editProgrammer?id=${programmer.id}" />">编辑</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				   <div id="pages">
				   	<pg:pager items="${count}" export="currentPageNumber=pageNumber"
						maxPageItems="${initParam.pagerNumes}" maxIndexPages="5"
						isOffset="true" url="${path}/resource/resourceManage">	
						<pg:prev>
							<a class="current_page" href="${pageUrl}">前页</a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber}">
									<a >${pageNumber}</a>
								</c:when>
								<c:otherwise>
									<a class="current_page" href="${pageUrl}">${pageNumber }</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a class="current_page" href="${pageUrl}">后页</a>
						</pg:next>
					</pg:pager>
				    </div>
				
			</div>
		</div>
	</div>

</body>

</html>