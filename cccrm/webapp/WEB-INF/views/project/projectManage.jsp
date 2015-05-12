<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员项目管理</title>
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
	function over() {
		$("#first").css({"background":"#fff","color":"#697d84"});
	}
	function overes() {
		$("#first").css({"background":"#3CB371","color":"#fff"}); 
	}
	$(function () {
        $("#big").mouseover(function () {
        	$("#smalls").show();//表示display:block, 
    		
        });
        $("#big").mouseleave(function () {
        	$("#smalls").hide();//表示display:none; 
        });
    });
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

				<div id="subfield"><!-- 分栏 --></div>
				<c:if test="${sessionScope.loginUser.userType==0}">
					<div class="search_add">

						<form action="${path}/project/seachPros" method="post">
							<input type="text" placeholder="请输入项目名称" name="proName"
								class="search"> <input type="submit" 
								value="搜索">
						</form>

					</div>
				</c:if>
				<!--数据区域：用表格展示数据-->
				<div id="data">
					<div class="news">
						<c:if test="${new1 == 'new1'}">
							<div class="olds">
								<a id="old" href="${path}/project/projectManage">当前项目</a>
							</div>
							<div class="oldes">
								<a href="${path}/project/historyProjects">历史项目</a>
							</div>
						</c:if>
						<c:if test="${old1 == 'old1'}">
							<div class="oldes">
								<a id="old" href="${path}/project/projectManage">当前项目</a>
							</div>
							<div class="olds">
								<a href="${path}/project/historyProjects">历史项目</a>
							</div>
						</c:if>

					</div>
					<table id="datalist">
						<tr id="tres">
							<th width="150px">项目名称</th>
							<th width="130px">开发时间</th>
							<th width="130px">结束时间</th>
							<th width="100px">状&nbsp;&nbsp;态</th>
							<th width="150px">操&nbsp;作</th>
						</tr>
						<c:forEach var="project" items="${projects}">
							<fmt:formatDate var="starttime" value="${project.starttime}"
								type="both" pattern="yyyy-MM-dd " />
							<fmt:formatDate var="endtime" value="${project.endtime}"
								type="both" pattern="yyyy-MM-dd " />
							<tr id="tres">
								<td>
								<a href="${path}/project/ProjectInfo?id=${project.id}"
								   class="btn_delete">${project.proName}</a>
								</td>
								<td>${starttime}</td>
								<td>${endtime}</td>
								<td><c:choose>
										<c:when test="${project.status == 2}">
				                                                                                进行中
			                           </c:when>
			                           <c:when test="${project.status == 0}">
				                                                                               完成
			                           </c:when>
										<c:otherwise>
				                                                                               待分配
			                           </c:otherwise>
									</c:choose></td>
								<td class="td_modi">
								<c:if test="${new1 == 'new1'||seach=='seach'}">
								 <a class="btn_delete"
											href="${path}/project/addProgrammer?id=${project.id}">分配人员</a>&nbsp;&nbsp;
								  <a class="btn_delete" id="Arch"
											href="${path}/project/updateProject?id=${project.id}">修改</a>&nbsp;&nbsp;
								</c:if> 
								   <c:if test="${old1 == 'old1'||seach=='seach'}">
										<a class="btn_delete" onclick="return deleteInfo();"
											href="${path}/project/delectPros?id=${project.id}">删除</a>
									</c:if>
									 <c:if test="${loginUser.userType==2}">
										<a class="btn_delete"
											href="${path}/project/ProjectInfo?id=${project.id}">项目详情</a>
									 </c:if></td>
							</tr>
						</c:forEach>
					</table>

				</div>



				<!--分页-->
				<div id="pages">
				
					<c:if test="${new1 == 'new1'}">
						<c:set var="a" value="${path}/project/projectManage" />
					</c:if>
					<c:if test="${old1 == 'old1'}">
						<c:set var="a" value="${path}/project/historyProjects" />
					</c:if>
					<c:if test="${seach == 'seach'}">
						<c:set var="a" value="${path}/project/seachPros" />
					</c:if>
					<pg:pager items="${count}" export="currentPageNumber=pageNumber"
						maxPageItems="${initParam.pagerNum}" maxIndexPages="5"
						isOffset="true" url="${a}">
						<pg:prev>
							<a class="current_page" href="${pageUrl}"> &lt;上一页  </a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber}">
									<a >${pageNumber}</a>
								</c:when>
								<c:otherwise>
									<a class="current_page" href="${pageUrl}">${pageNumber}</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a class="current_page" href="${pageUrl}">下一页&gt;</a>
						</pg:next>
					</pg:pager>
				
				</div>

			</div>
		</div>
	</div>
</body>
</html>