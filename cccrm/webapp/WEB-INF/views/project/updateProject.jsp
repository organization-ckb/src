<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri= "http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员信息</title>
<link type="text/css" rel="stylesheet" media="all" href="${path}/resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="${path}/resources/css/global_color.css" />
<script src="${path}/resources/laydate/laydate.js"></script> 
<script type="text/javascript">

$(function(){
	$('.date_picker').date_input();
	})
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
				<form action="${path}/project/updatePros" method="post"  class="white-pink">
				    <input type="hidden" name="id" value="${project.id}">
					<label> <span>项目名称 :</span> 
					<input id="name" type="text" name="proName" value="${project.proName }" />
					</label> 
					<label> <span>合同编号 :</span>
					 <input name="bargainNo" type="text" value="${project.bargainNo}"  />
					</label>
					<label> <span>状态:</span>
					<c:if test="${project.status == 1}">
				       <c:set var="m" value="checked" />
			         </c:if>
					<c:if test="${project.status == 2}">
				       <c:set var="f" value="checked"/>
			         </c:if>
					<input name="status" type="radio" class="radios" value="1" ${m}>未完成
		            <input name="status" type="radio"  class="radios" value="0" ${f}>完成
					</label>
					
					<label> <span>客户 :</span>
					  <select name="customer.id">
					   <c:forEach var="cus" items="${customer}">
					   <c:if test="${cus.id == project.customer.id}">
				       <option value="${cus.id}" selected="selected">${cus.name}</option>
			           </c:if>
			           <option value="${cus.id}">${cus.name}</option>
					   </c:forEach>
					  </select>
					
					</label>
					
					<fmt:formatDate var="starttime" value="${project.starttime}" type="both" pattern="yyyy-MM-dd "/>
					 <label> <span>开始时间 :</span>
					  <input name="starttime"  placeholder="请输入日期" onclick="laydate()" type="text"  value="${starttime}"   />
					</label>
					 <fmt:formatDate var="endtime" value="${project.endtime}" type="both" pattern="yyyy-MM-dd " />
					<label> <span>结束时间 :</span>
					
					<input name="endtime"	type="text" value="${endtime}" onclick="laydate()" />
					</label>	  
					<label> <span>&nbsp;</span> 
					<input type="submit" class="button" value="确认修改" />
					</label>		
				</form>
			</div>
		</div>
	</div>
</body>
</html>