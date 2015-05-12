<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>项目续约记录</title>
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global_color.css" />
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript">
	function deleteInfo() {
		if (confirm("确定要删除此商品信息吗？\r\n删除后将不能恢复!")) {
			return true;
		} else {
			return false;
		}
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

				<div id="subfield"></div>

				<div class="search_add"></div>

				<!--数据区域：用表格展示数据-->
				<div id="data">
					<table id="datalist">
						<tr id="tres">
							<th width="100px">项目名称</th>
							<th width="100px">程序员</th>
							<th width="100px">申请时间</th>
							<th width="100px">续约时间</th>
							<th width="150px">操&nbsp;作</th>
						</tr>
						<c:forEach var="Record" items="${ProjectRecord}">

							<tr id="tres">
								<td><a class="btn_delete">${Record.project.proName}</a></td>
								<td>${Record.programmer.programmerName}</td>
								<c:set var="day"
									value="${Record.endTime.getTime()-date.getTime()}" />
								<fmt:formatNumber var="days" value="${day/(1000*60*60*24)}"
									pattern="#" type="number" />
								<c:choose>
									<c:when test="${day<0}">
										<c:set var="oneday" value="0"></c:set>
									</c:when>
									<c:when test="${(day/(1000*60*60*24))>days}">
										<c:set var="oneday" value="${days+1}"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="oneday" value="${days}"></c:set>
									</c:otherwise>
								</c:choose>
								<fmt:formatDate var="timeRecord" value="${Record.timeRecord}"
									type="both" pattern="yyyy-MM-dd " />
								<td>${timeRecord}</td>
								<td>${Record.extension_time}天</td>
								<td class="td_modi"><a class="btn_delete"
									href="${path}/project/updateRecord?id=${Record.id}
									&extensionTime=${Record.extension_time}
									&RemainingTime=${oneday}
									&timeRecord=${timeRecord}">允许续约</a>&nbsp;&nbsp;
									<a class="btn_delete" onclick="return deleteInfo();" href="#">驳回续约</a>
								</td>
							</tr>

						</c:forEach>
					</table>

				</div>



				<!--分页-->
				<div id="pages">
					<pg:pager items="${count}" export="currentPageNumber=pageNumber"
						maxPageItems="${initParam.pagerNum}" maxIndexPages="5"
						isOffset="true" url="${path}/getExtensionInfo">
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
					</pg:pager>
				</div>

			</div>
		</div>
	</div>
</body>
</html>