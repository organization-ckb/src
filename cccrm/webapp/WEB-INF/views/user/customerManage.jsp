<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户管理</title>
<link rel="Shortcut Icon" href="../resources/img/icoimg.ico">
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global_color.css" />
<script type="text/javascript" src="../resources/js/customermanage.js"></script>
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
   <form action="searchcustomer" method="get" id="customermanage">
	<!--主要区域开始-->
	<div id="main">
		<div id="mains">
			<div id="contents">
				<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
			</div>

			<div id="record">

				<div id="subfield"></div>
				<div class="search_add">
				<div class="left">
					<select name="status" id="sel_status" style="margin-left: 30px;">
					<c:if test="${searchcustomers == 'searchcustomers'}">
					<c:if test="${status=='-1'}">
					<c:set var="a" value="selected"></c:set>	
					</c:if>
					<c:if test="${status=='0'}">
					<c:set var="b" value="selected"></c:set>	
					</c:if>
					<c:if test="${status=='1'}">
					<c:set var="c" value="selected"></c:set>	
					</c:if>
					</c:if>
					<option value="-1" ${a}>全部</option>
					<option value="0" ${b}>空闲</option>
					<option value="1" ${c}>项目中</option>
					</select>
					<input type="hidden" value="${status}" id="hidden-status"/>
					<button id="btn_search"  class="find">搜索</button>
				</div>
					<div class="rights">
				
					 <a href="${path}/customer/addCustomer">新增资源</a>
					</div>
				</div>
				<!--数据区域：用表格展示数据-->
					<div id="data">
					<table id="datalist">
						<tr id="tres">
							<th >公司名称</th>
							<th >联系人</th>
							<th >性别</th>
							<th >邮箱</th>
							<th >手机</th>
							<th >QQ</th>
							<th >状&nbsp;&nbsp;态</th>
							<th >操&nbsp;作</th>
						</tr>
						<c:forEach var="customer" items="${customers}">
					<tr id="tres"><td>${customer.customerName}</td>
						<td>${customer.customerMan}</td>
						<td>
							<c:choose>
									  <c:when test="${customer.customerSex == 0}">男</c:when>
								  	  <c:when test="${customer.customerSex == 1}">女</c:when>
						    </c:choose>
						</td>
						<td>${customer.userEmail}</td>
						<td>${customer.userMobile}</td>
						<td>${customer.customerQq}</td>
						<td>
							<c:choose>
									  <c:when test="${customer.customerStatus == 0}">空闲</c:when>
								  	  <c:when test="${customer.customerStatus == 1}">项目中</c:when>
						    </c:choose>
						</td>
						<td><a onclick="" class="btn_delete"  href="<c:url value="/customer/viewCustomer?id=${customer.id}" />">查看</a>
							<a onclick=""  class="btn_delete" href="<c:url value="/customer/editCustomer?id=${customer.id}" />">编辑</a>
						</td>
						</tr>
						
					</c:forEach>
					</table>

				</div>
				
				<div id="pages">
				
				 	<c:if test="${allcustomers == 'allcustomers'}">
						<c:set var="a" value="customerManage" />
					</c:if>
					<c:if test="${searchcustomers == 'searchcustomers'}">
						<c:set var="a" value="searchcustomer" />
					</c:if> 
					
					<pg:pager items="${count}" export="currentPageNumber=pageNumber"
						maxPageItems="${initParam.pagerNum}" maxIndexPages="5"
						isOffset="true" url="${a}">
						<c:if test="${searchcustomers == 'searchcustomers'}">
						<pg:param name="status" value="${status}" />
						</c:if>
						<pg:first>
							<a href="${pageUrl}">首页</a>
						</pg:first>
						<pg:prev>
							<a href="${pageUrl}">前页</a> 
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
								 <a href="${pageUrl}">后页</a> 
						</pg:next>
						<pg:last>
								 <a href="${pageUrl}">尾页</a> 
						</pg:last>
					</pg:pager>
				</div>
			
			</div>
		</div>
	</div>
	</form>
</body>
</html>