<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加项目</title>
<link rel="Shortcut Icon" href="../resources/img/icoimg.ico">
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global_color.css" />
<link rel="stylesheet" href="../resources/css/style.css">
<!-- Resource style -->
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>
<script src="../resources/laydate/laydate.js"></script>
<script src="../resources/js/main.js"></script>
<!-- Resource jQuery -->
<script type="text/javascript">
	function over() {
		$("#first").css({
			"background" : "#fff",
			"color" : "#697d84"
		});
	}
	function overes() {
		$("#first").css({
			"background" : "#3CB371",
			"color" : "#fff"
		});
	}
	$(function() {
		var endTimeflag = true;
		var beginTimeflag = true;
		var nameflag = true;
		$("#submit").click(function() {
			var endTime = $("#endtime").val();
			var beginTime = $("#starttime").val();
			var name = $("#name").val();
			if (endTime == "") {
				$("#errores").text("结束时间不可为空");
				endTimeflag = false;
			} else {
				$("#errores").text("");
				endTimeflag = true;
			}
			if (beginTime == "") {
				$("#error").text("开始时间不可为空");
				beginTimeflag = false;
			} else {
				$("#error").text("");
				beginTimeflag = true;
			}
			if (name == "") {
				$("#nameerror").text("项目名不可为空");
				nameflag = false;
			} else {
				$("#nameerror").text("");
				nameflag = true;
			}
			return endTimeflag && beginTimeflag && nameflag;
		});

	});
</script>
</head>
<body>
	<!--Logo区域开始-->
	<div id="header">
		<div class="headlogo">
			<img src="${path}/resources/img/logo.png"  alt="logo" />
		</div>
		<div class="headerinfo">
			<c:if test="${sessionScope.nickname!=null}">
				<img class="headerimg" src="../resources/img/manager.png">
				<a>${sessionScope.nickname}</a>
				<img class="headerimg" src="../resources/img/cancel.png">
				<a href="${path}/project/logout">注销</a>
			</c:if>
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
				<form action="${path}/project/addPros" method="post"
					class="white-pink">
					<label> <span>项目名称 :</span> <input id="name" type="text"
						name="proName" />
					</label>
					<h1>
						<span id="nameerror"></span>
					</h1>
					<label> <span>合同编号 :</span> <input name="bargainNo"
						type="text" readonly="readonly" onFocus="this.blur()"
						value="${serialNo}" />
					</label> <label> <span>状态:</span> <input name="status"
						class="radios" type="radio" value="1" checked="checked">未完成
						<input name="status" class="radios" type="radio" value="0">完成
					</label> <label> <span>客户 :</span> <select name="customer.id">
							<c:forEach var="cus" items="${customer}">
								<option value="${cus.id}">${cus.name}</option>
							</c:forEach>
					</select>

					</label> <label> <span>开始时间 :</span> <input name="starttime"
						id="starttime" type="text" onclick="laydate()" />
					</label>

					<h1>
						<span id="error"></span>
					</h1>

					<label> <span>结束时间 :</span> <input name="endtime"
						id="endtime" type="text" onclick="laydate()" />
					</label>
					<h1>
						<span id="errores"></span>
					</h1>


					<label> <span>&nbsp;</span> <input type="submit"
						id="submit" class="button" value="确认添加" />
					</label>
				</form>
			</div>
			<c:choose>
				<c:when test="${massage != null && massages==null }">
					<div class="cd-popup">
						<div class="cd-popup-container">
							<p>${massage}</p>
								<ul class="cd-buttons">
									<li><a href="${path}/project/addPro">是</a></li>
									<li><a href="${path}/project/projectManage">否</a></li>
								</ul>
							<a href="#0" class="cd-popup-close img-replace">Close</a>
						</div>
					</div>
				</c:when>
				<c:when test="${massages != null}">
					<div class="cd-popup">
						<div class="cd-popup-container">
							<p>${massages}</p>
							<a href="#0" class="cd-popup-close img-replace">Close</a>
						</div>
					</div>
				</c:when>
			</c:choose>

		</div>
	</div>

</body>
</html>