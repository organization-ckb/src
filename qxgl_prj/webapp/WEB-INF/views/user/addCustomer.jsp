<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../resources/js/addcustomer.js"></script>
<script type="text/javascript" src="../resources/js/global.js"></script>
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
	<div id="main">
		<div id="mains">
			<div id="contents">
				<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
			</div>
			<div id="record">
				<div id="subfield"></div>

				<input type="hidden" id="hidden-type" value="${type}" />

				<form action="addCustomerResult" method="post" id="addcustomer"
					class="white-pink">
					<input type="hidden" id="hidden-id" name="id" value="${id}" /> <label><span>邮箱(用户名):</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="loginName" id="loginName"
								value="${customer.userEmail}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="loginName" id="loginName"
								value="${customer.userEmail}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="loginName" id="loginName" />
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->

						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->

							<label><span>密码:</span></label>
							<input type="password" name="loginPwd" id="loginPwd"
								value="${customer.loginPwd}" />
							<label><span>确认密码:</span></label>
							<input type="password" id="loginPwd1"
								value="${customer.loginPwd}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<label><span>密码:</span></label>
							<input type="password" name="loginPwd" id="loginPwd" />

							<label><span>确认密码:</span></label>
							<input type="password" id="loginPwd1" />

						</c:otherwise>
					</c:choose>


					<label><span>公司名称:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="customerName" id="customerName"
								value="${customer.customerName}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="customerName" id="customerName"
								value="${customer.customerName}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="customerName" id="customerName" />
						</c:otherwise>
					</c:choose>

					<label><span>联系人:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="customerMan" id="customerMan"
								value="${customer.customerMan}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="customerMan" id="customerMan"
								value="${customer.customerMan}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="customerMan" id="customerMan" />
						</c:otherwise>
					</c:choose>

					<label><span>性别:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input disabled type="radio" class="radios" value="0" name="customerSex"
								<c:if test="${customer.customerSex == 0}">checked="checked"</c:if> />男
                            <input disabled type="radio" class="radios" value="1"
								name="customerSex"
								<c:if test="${customer.customerSex == 1}">checked="checked"</c:if> />女
                        </c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="radio" class="radios" value="0" name="customerSex"
								<c:if test="${customer.customerSex == 0}">checked="checked"</c:if> />男
                            <input type="radio" class="radios" value="1"
								name="customerSex"
								<c:if test="${customer.customerSex == 1}">checked="checked"</c:if> />女
                        </c:when>
						<c:otherwise>
							<!--add-->
							<input type="radio" class="radios" value="0" name="customerSex"
								checked="checked" />男<input class="radios"  type="radio" value="1"
								name="customerSex" />女
                        </c:otherwise>
					</c:choose>


					<label><span>手机:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="userMobile" id="userMobile"
								value="${customer.userMobile}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="userMobile" id="userMobile"
								value="${customer.userMobile}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="userMobile" id="userMobile" />
						</c:otherwise>
					</c:choose>

					<label><span>QQ:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="customerQq" id="customerQq"
								value="${customer.customerQq}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="customerQq" id="customerQq"
								value="${customer.customerQq}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="customerQq" id="customerQq" />
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->

						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="button" style="margin-left: 100px;"  class="button" name="addcustomer" value="编辑" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="button" style="margin-left: 100px;"  class="button" name="addcustomer" value="确认新增" />
						</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
</body>
</html>