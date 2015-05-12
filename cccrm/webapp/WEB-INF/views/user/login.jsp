<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" href="${path}/resources/css/login.css"
	type="text/css" />
<!-- <link rel="stylesheet" href="<c:url value="/resources/css/resourcemanage.css" />"
	type="text/css" /> -->
<link rel="stylesheet" href="../resources/css/global.css"
	type="text/css" />
	
<!-- <link rel="stylesheet" href="../resources/css/global_color.css" type="text/css"/> -->
<script type="text/javascript" src="${path}/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/cookie.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/login.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/layout/head.jsp" />
	<div id="main">
		
		    <!-- toolbar -->
    <div style=" text-align:center; position:relative; font-size:24px;margin-top: 100px; height:400px; width:80%;">
    	<form action="${path}/user/doLoginResult" method="post" id="form-login" >            
                <!-- 搜索工种/部门 -->
                <div>
                    <label>用户名：</label>
                    <c:choose>
                        <c:when test="${loginName != null}">
                            <input type="text" id="loginName" name="loginName" placeholder="输入邮箱（用户名）" 
                            	style=" font-size:24px;" value="${loginName}"/>
                        </c:when>
                        
                        <c:otherwise>
                            <input type="text" id="loginName" name="loginName" placeholder="输入邮箱（用户名）" style=" font-size:24px;"/>
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                <br/>
                
                <!-- 状态 -->
                <div>
                    <label>密&nbsp;&nbsp;&nbsp;码：</label> 
                    <c:choose>
                        <c:when test="${loginPwd != null}">
                            <input type="password" id="loginPwd" name="loginPwd" placeholder="输入密码" 
                            style=" font-size:24px;" value="${loginPwd}"/>
                        </c:when>
                        
                        <c:otherwise>
                            <input type="password" id="loginPwd" name="loginPwd" placeholder="输入密码" style=" font-size:24px;" />
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                
                <!--错误提示-->
                <div>
                	<c:choose>
                        <c:when test="${msg != null}">
                            <label style="font-size:18px; color:#F00;">${msg}</label>
                        </c:when>
                        
                        <c:otherwise>
                           
                        </c:otherwise>
                    </c:choose>
                </div>
                <div style="margin-top:20px; ">
                    <label style=" font-size:14px;"><input id="chk_remember_pwd" type="checkbox"/>7天内记住账号密码</label>
                    <a id="a_get_pwd" style="cursor:pointer;margin-left:30px;font-size:14px; color:#00F">忘记密码</a>
                </div>
                <div style="margin-top:30px;">
                    <input id="btn_login" type="button" style="width: 250px;height: 50px;" class="button" value="确认登录" onClick="login();" />
                    <!--<button id="btn_login" style=" font-size:24px;" onClick="login();">确认登录</button>-->
                </div>
           
        </form>
    </div>
	
	<div style="float:left; margin-left:200px; margin-top:20px; font-size:14px;">
    	友情链接：
        <a style="cursor:pointer; margin-left:30px;">持创研发中心官网</a>
        <a style="cursor:pointer; margin-left:30px;">极客邦官网</a>
    </div>
    
    <script type="text/javascript">
		/*
		function test(){
			$.cookie('remember_flag','true',{expires:7});

		}*/
	
	</script>
		
		</div>
</body>

</html>