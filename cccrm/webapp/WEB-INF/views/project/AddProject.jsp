<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri= "http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员信息</title>
<link type="text/css" rel="stylesheet" media="all" href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all" href="../resources/css/global_color.css" />
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>
<script src="../resources/laydate/laydate.js"></script> 
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
				<form action="${path}/project/addPros" method="post"  class="white-pink">
					<label> 
					<span>项目名称 :</span> 
					<input id="name" type="text" name="proName" />
					</label> 
					<label> <span>合同编号 :</span>
					 <input name="bargainNo" type="text" readonly="readonly" onFocus="this.blur()" value="${serialNo}"  />
					</label>
					<label> <span>状态:</span>				
					<input name="status" class="radios" type="radio" value="1" checked="checked" >未完成
		            <input name="status"  class="radios" type="radio" value="0">完成					
					</label>
				
					<label> <span>客户 :</span>
					  <select name="customer.id">
					   <c:forEach var="cus" items="${customer}">
					   <option value="${cus.id}">${cus.name}</option>
					   </c:forEach>
					  </select>
					
					</label>
					
					
					 <label> <span>开始时间 :</span>
					  <input name="starttime" type="text" onclick="laydate()"  />
					</label>
					<label> <span>结束时间 :</span> 
					<input name="endtime"	type="text" onclick="laydate()"  />
					</label>
					  
					<label> <span>&nbsp;</span> 
					<input type="submit" class="button" value="确认添加" />
					</label>
				</form>
			</div>

		</div>
	</div>
	
</body>
</html>