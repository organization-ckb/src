<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://localhost:8080/cccrm/resources/css/resourcemanage.css" type="text/css"></link>
<link type="text/css" rel="stylesheet" media="all" href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../resources/css/global_color.css" />
<script type="text/javascript" src="http://localhost:8080/cccrm/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/cccrm/resources/js/resourcemanage.js"></script>
</head>
<body>
<form action="http://localhost:8080/cccrm/test/programmersearch" method="post" id="programmersearch">
	<center>
		<jsp:include page="/WEB-INF/views/layout/head.jsp" />
			<div class="bottom">
			<!-- <jsp:include page="/WEB-INF/views/layout/left.jsp" /> -->	
				<div class="main">
				<div class="gz">
					<select name="department">	
					<option value="4">全部</option>
					<option value="0">JAVA</option>
					<option value="1">Android</option>
					<option value="2">IOS</option>
					<option value="3">PHP</option>
					</select>
				</div>
				<div class="zt">
					<select name="department">	
					<option value="4">全部</option>
					<option value="0">空闲</option>
					<option value="1">项目中</option>
					</select>
					<div class="sousuo"><input type="button" name="sousuo" value="搜索"/></div>
				</div>
				
				<div class="xz"><a href="http://localhost:8080/cccrm/test/addprogrammer">新增资源</a></div>
				<table width="700px" >
					<tr class="t0"><th class="t1">工种</th><th class="t2">姓名</th><th class="t3">性别</th><th class="t4">邮箱</th><th class="t5">手机</th><th class="t6">QQ</th><th class="t7">状态</th><th class="t8">操作</th></tr>
					<c:forEach var="programmersearch" items="${programmersearch}">
					<tr><th><c:choose><c:when test="${programmersearch.department == 0}">JAVA</c:when>
									  <c:when test="${programmersearch.department == 1}">Android</c:when>
									  <c:when test="${programmersearch.department == 2}">IOS</c:when>
									  <c:when test="${programmersearch.department == 3}">PHP</c:when></c:choose></th>
					<th>${programmersearch.programmerName}</th>
					<th><c:choose><c:when test="${programmersearch.programmerSex == 0}">男</c:when>
								  <c:when test="${programmersearch.programmerSex == 1}">女</c:when></c:choose></th>
					<th>${programmersearch.loginName}</th>
					<th>${programmersearch.userMobile}</th>
					<th>${programmersearch.programmerQq}</th>
					<th><c:choose><c:when test="${programmersearch.programmerStatus == null}">空闲</c:when>
								  <c:when test="${programmersearch.programmerStatus != null}">项目中</c:when></c:choose></th>
					<th><a class="btn_delete" onclick="changA()" id="Arch"
						href="#">查看</a>
						<a class="btn_delete" onclick="return deleteInfo();"
						href="#">编辑</a></th></tr>
					</c:forEach>
				</table>
				</div>
				
				<%-- <!--分页-->
				<div id="pages">
				<pg:pager items="${count}" export="currentPageNumber=pageNumber"
					maxPageItems="${initParam.pagerNum}" maxIndexPages="3"
					isOffset="true" url="/cccrm/PM">
					<pg:first>
						<a href="${pageUrl}">首页</a>
					</pg:first>
					<pg:prev>
						<a href="${pageUrl}">上一页</a>
					</pg:prev>
					<pg:pages>
						<c:choose>
							<c:when test="${currentPageNumber eq pageNumber}">
								<a class="current_page">${pageNumber }</a>
							</c:when>
							<c:otherwise>
								<a href="${pageUrl}">${pageNumber }</a>
							</c:otherwise>
						</c:choose>
					</pg:pages>
					<pg:next>
						<a href="${pageUrl}">下一页</a>
					</pg:next>
					<pg:last>
						<a href="${pageUrl}">尾页</a>
					</pg:last>
				</pg:pager>
			</div> --%>
		
		</div>	
	</center>
	</form>
</body>
</html>