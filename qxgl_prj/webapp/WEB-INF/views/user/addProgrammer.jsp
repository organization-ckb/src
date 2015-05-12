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
<script type="text/javascript" src="../resources/js/addprogrammer.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/layout/head.jsp" />
	<div id="main">
		<div id="mains">
			<div id="contents">
				<jsp:include page="/WEB-INF/views/layout/sysmenua.jsp" />
			</div>
			<div id="record">
				<div id="subfield"></div>

				<input type="hidden" id="hidden-type" value="${type}" />
				<form action="addProgrammerResult" method="post" id="addprogrammer"
					class="white-pink">
					<input type="hidden" id="hidden-id" name="id" value="${id}" />
					 <label> <span>邮箱(用户名):</span> <c:choose>
							<c:when test="${type == 1}">
								<!--view-->
								<input type="text" name="loginName" id="loginName"
									value="${programmer.loginName}" readonly />
							</c:when>
							<c:when test="${type == 2}">
								<!--edit-->
								<input type="text" name="loginName" id="loginName"
									value="${programmer.loginName}" />
							</c:when>
							<c:otherwise>
								<!--add-->
								<input type="text" name="loginName" id="loginName" />
							</c:otherwise>
						</c:choose>
					</label>

					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
						</c:when>
						<c:when test="${type == 2}">

							<label><span>密码:</span></label>
							<input type="password" name="loginPwd" id="loginPwd"
								value="${programmer.loginPwd}" />
							<label><span>确认密码:</span></label>
							<input type="password" id="loginPwd1"
								value="${programmer.loginPwd}" />
						</c:when>
						<c:otherwise>

							<label><span>密码:</span></label>
							<input type="password" name="loginPwd" id="loginPwd" />

							<label><span>确认密码:</span></label>
							<input type="password" id="loginPwd1" />

						</c:otherwise>
					</c:choose>

					<label><span>部门:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<select name="department" disabled>
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<select name="department">
						</c:when>
						<c:otherwise>
							<!--add-->
							<select name="department">
						</c:otherwise>
					</c:choose>
					<!--<select name="department">-->
					<c:choose>
						<c:when test="${programmer.department == 1}">
							<option value="1" selected>JAVA</option>
							<option value="2">PHP</option>
							<option value="3">IOS</option>
							<option value="4">Android</option>
						</c:when>
						<c:when test="${programmer.department == 2}">
						    <option value="1" >JAVA</option>
							<option value="2" selected>PHP</option>
							<option value="3">IOS</option>
							<option value="4">Android</option>
							
						</c:when>
						<c:when test="${programmer.department == 3}">
						    <option value="1" >JAVA</option>
							<option value="2" >PHP</option>
							<option value="3" selected>IOS</option>
							<option value="4">Android</option>
						</c:when>
						<c:when test="${programmer.department == 4}">
							<option value="1" >JAVA</option>
							<option value="2" >PHP</option>
							<option value="3" >IOS</option>
							<option value="4" selected>Android</option>
						</c:when>
						<c:otherwise>
							<option value="0" selected>JAVA</option>
							<option value="1">Android</option>
							<option value="2">IOS</option>
							<option value="3">PHP</option>
						</c:otherwise>
					</c:choose>
					</select>
					<label><span>姓名:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="programmerName" id="programmerName"
								value="${programmer.programmerName}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="programmerName" id="programmerName"
								value="${programmer.programmerName}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="programmerName" id="programmerName" />
						</c:otherwise>
					</c:choose>
					<label><span>性别:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input disabled type="radio" class="radios" value="0" name="programmerSex"
								<c:if test="${programmer.programmerSex == 0}">checked="checked"</c:if> />男
                            <input disabled type="radio" class="radios" value="1"
								name="programmerSex"
								<c:if test="${programmer.programmerSex == 1}">checked="checked"</c:if> />女
                        </c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="radio" class="radios" value="0" name="programmerSex"
								<c:if test="${programmer.programmerSex == 0}">checked="checked"</c:if> />男
                            <input type="radio" class="radios" value="1"
								name="programmerSex"
								<c:if test="${programmer.programmerSex == 1}">checked="checked"</c:if> />女
                        </c:when>
						<c:otherwise>
							<!--add-->
							<input type="radio" class="radios" value="0" name="programmerSex"
								checked="checked" />男<input type="radio" class="radios" value="1"
								name="programmerSex" />女
                        </c:otherwise>
					</c:choose>

					<label><span>手机:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<input type="text" name="userMobile" id="userMobile"
								value="${programmer.userMobile}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="userMobile" id="userMobile"
								value="${programmer.userMobile}" />
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
							<input type="text" name="programmerQq" id="programmerQq"
								value="${programmer.programmerQq}" readonly />
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="text" name="programmerQq" id="programmerQq"
								value="${programmer.programmerQq}" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="text" name="programmerQq" id="programmerQq" />
						</c:otherwise>
					</c:choose>
					<label><span>级别:</span></label>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
							<select name="programmerLevel" disabled>
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<select name="programmerLevel">
						</c:when>
						<c:otherwise>
							<!--add-->
							<select name="programmerLevel">
						</c:otherwise>
					</c:choose>
					<!--<select name="programmerLevel">-->
					<c:choose>
						<c:when test="${programmer.programmerLevel == 0}">
							<option value="0" selected>初级</option>
							<option value="1">中级</option>
							<option value="2">高级</option>
							<option value="3">资深</option>
						</c:when>
						<c:when test="${programmer.programmerLevel == 1}">
							<option value="0">初级</option>
							<option value="1" selected>中级</option>
							<option value="2">高级</option>
							<option value="3">资深</option>
						</c:when>
						<c:when test="${programmer.programmerLevel == 2}">
							<option value="0">初级</option>
							<option value="1">中级</option>
							<option value="2" selected>高级</option>
							<option value="3">资深</option>
						</c:when>
						<c:when test="${programmer.programmerLevel == 3}">
							<option value="0">初级</option>
							<option value="1">中级</option>
							<option value="2">高级</option>
							<option value="3" selected>资深</option>
						</c:when>
						<c:otherwise>
							<option value="0" selected>初级</option>
							<option value="1">中级</option>
							<option value="2">高级</option>
							<option value="3">资深</option>
						</c:otherwise>
					</c:choose>

					</select>
					<c:choose>
						<c:when test="${type == 1}">
							<!--view-->
						</c:when>
						<c:when test="${type == 2}">
							<!--edit-->
							<input type="button" class="button" style="margin-left: 100px;margin-top: 13px;" name="addprogrammer"
								value="编辑" />
						</c:when>
						<c:otherwise>
							<!--add-->
							<input type="button" style="margin-left: 100px;margin-top: 13px;""  class="button" name="addprogrammer"
								value="确认新增" />
						</c:otherwise>
					</c:choose>
				</form>
			</div>

		</div>
	</div>
</body>
</html>