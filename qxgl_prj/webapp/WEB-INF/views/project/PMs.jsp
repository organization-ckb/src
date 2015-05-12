<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的项目</title>
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global_color.css" />
<link rel="stylesheet" type="text/css"
	href="../resources/static/css/ui2.css?2013032917">
<script type="text/javascript" src="${path}/resources/js/jquery.min.js"></script>
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
				<!--数据区域：用表格展示数据-->
				<div id="data">
					<div class="news">
					
					</div>
					<table id="datalist">
						<tr id="tres">
							<th width="150px">项目名称</th>
							<th width="130px">开发时间</th>
							<th width="130px">结束时间</th>
							<th width="100px">状&nbsp;&nbsp;态</th>
							<th width="150px">操&nbsp;作</th>
						</tr>
						<c:forEach var="Pproject" items="${Pprojects}" varStatus="index">
							<fmt:formatDate var="starttime" value="${Pproject.beginTime}"
								type="both" pattern="yyyy-MM-dd " />
							<fmt:formatDate var="endtime" value="${Pproject.endTime}"
								type="both" pattern="yyyy-MM-dd " />
							<tr id="tres">
								<td>${Pproject.project.proName}</td>
								<td>${starttime}</td>
								<td>${endtime}</td>
								<td><c:choose>
										<c:when test="${Pproject.project.status == 1}">
				                                                                                进行中
			                           </c:when>
										<c:otherwise>
				                                                                                   完成
			                           </c:otherwise>
									</c:choose>
								</td>
								<td class="td_modi"><a class="btn_delete" data-toggle="modal"  href="#signup-modal${index.count}">详情</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
					<c:forEach var="Pproject" items="${Pprojects}" varStatus="index">
				   <div class="modal in" id="signup-modal${index.count}">
								<a class="close" data-dismiss="modal">×</a>
								<h1>项目详情</h1>
										<form  class="white-pink">
											<label><span>项目名称 :</span>
											<input readonly="readonly" onFocus="this.blur()" type="text"
												name="proName" value="${Pproject.project.proName }" />
										    </label> 
										    <c:set var="day" value="${Pproject.endTime.getTime()-Pproject.beginTime.getTime()}"/>
			                                <fmt:formatNumber var="days"  value="${day/(1000*60*60*24)}"  pattern="#" type="number"/>
										    <label><span>全部时间 :</span>
											<input readonly="readonly" onFocus="this.blur()" type="text"
												name="proName" value="${days+1}/天" />
										    </label> 
										    <c:set var="dateTime" value="${date.getTime()-Pproject.beginTime.getTime()}"/>
			                                <fmt:formatNumber var="UsedTime"  value="${dateTime/(1000*60*60*24)}"  pattern="#" type="number"/>
										    <label><span>已用时间 :</span>
											<input readonly="readonly" onFocus="this.blur()" type="text"
												name="proName" value="${UsedTime}/天" />
										    </label>
										    <label><span>剩余时间 :</span>
										      <c:set var="residueTime" value="${days-UsedTime+1}"/>
			                              
											<input readonly="readonly" onFocus="this.blur()" type="text"
												name="proName" value="${residueTime}/天" />
										    </label>  
										</form>
									</div>
								</c:forEach>
				 <script src="${path}/resources/static/js/landing-min.js?2013032917"></script>
				<!--分页-->
				<div id="pages">
					<pg:pager items="${count}" export="currentPageNumber=pageNumber"
						maxPageItems="${initParam.pagerNum}" maxIndexPages="5"
						isOffset="true" url="${path}/project/projectManage">
						<pg:first>
							<a class="current_page" href="${pageUrl}">首页</a>
						</pg:first>
						<pg:prev>
							<a class="current_page" href="${pageUrl}">前页</a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber}">
									<a>${pageNumber }</a>
								</c:when>
								<c:otherwise>
									<a class="current_page" href="${pageUrl}">${pageNumber }</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a class="current_page" href="${pageUrl}">后页</a>
						</pg:next>
						<pg:last>
							<a class="current_page" href="${pageUrl}">尾页</a>
						</pg:last>
					</pg:pager>
				</div>

			</div>
		</div>
	</div>
</body>
</html>