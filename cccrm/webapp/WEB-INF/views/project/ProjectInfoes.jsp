<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>项目详情</title>
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../resources/css/global_color.css" />
<link rel="stylesheet" type="text/css"
	href="../resources/static/css/ui2.css?2013032917">
<script type="text/javascript" src="${path}/resources/js/jquery-1.8.2.min.js"></script>
<script src="../resources/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function shows() {
		$(".presents").show();//表示display:block, 
		$(".present").hide();//表示display:none; 
		
	}
	$(function(){
		$("#btn1").click(function(){
			var txt=  "当日已申请过续约，请明天后再次申请吧！";
			window.wxc.xcConfirm(txt,window.wxc.xcConfirm.typeEnum.info);
		});
		$("#btn2").click(function(){
			var txt=  "当日已申请过续约，请明天后再次申请吧！";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
		});
		$(".btn3").click(function(){
			var txt=  "当日成功续约，请明天后再次续约！";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
		});
		$("#btn4").click(function(){
			var txt=  "错误";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
		});
		$("#btn5").click(function(){
			var txt=  "成功";
			window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
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
				<div class="sysmenua" onMouseOut="overes()">
					<ul>
						<c:if test="${sessionScope.loginUser.userType==0}">
							<li id="big"><a id="first" class="current"
								href="${path}/project/projectManage">项目管理</a>
								<ul id="smalls" style="display: block;">
									<li><a class="small" href="${path}/project/addPro">添加项目</a></li>
									<li><a class="small"
										href="${path}/project/getExtensionInfo">续约记录</a></li>
								</ul></li>
							<li><a href="" onmouseover="over()" class="big">客户管理</a></li>
							<li><a href="" onmouseover="over()" class="big">资源管理</a></li>
						</c:if>
						<c:if test="${sessionScope.loginUser.userType!=0}">
							<li><a class="current" href="${path}/project/projectManage">我的项目</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<img src="../resources/img/video.png" class="imgs"> <a
						href="#">视频会议</a> <img src="../resources/img/phone.png"
						class="imges"> <a href="#">在线客服</a>
				</div>
			</div>

			<div id="record">

				<div id="subfield">分栏
				
				</div>
				<div class="tables">
					<table id="tableslist">
						<tr>
							<th>项目名称</th>
							<td>${project.proName }</td>
							<th>合同编号</th>
							<td>${project.bargainNo}</td>
						</tr>
						<tr>
							<th>开始时间</th>
							<fmt:formatDate var="starttime" value="${project.starttime}" type="both" pattern="yyyy-MM-dd "/>
							<td>${starttime}</td>
							<th>结束时间</th>
							 <fmt:formatDate var="endtime" value="${project.endtime}" type="both" pattern="yyyy-MM-dd " />
							<td>${endtime}</td>
						</tr>
					</table>

				</div>

				<!--数据区域：用表格展示数据-->
				<div id="data">
		           <div id="one" class="column">
		             <div id="part">  
		             <div class="border">
		             <div class="border1">程序员</div>
		             <div class="border2">剩余工作时间（人/天）</div>
		             </div>
			        <div id="cents">
			        
			          <c:forEach var="pp" items="${Pprojects}" varStatus="index" >
			           <div class="centes"  onmouseover="shows()">
			              <div class="sort"><img  src="${path}/resources/img/userImage.png"/></div>
			               <div class="sorts">
			                  <div class="sortes1"><a href="#">${pp.programmer.programmerName}</a></div>
			                  <c:if test="${pp.programmer.level==0}">
			                  <c:set var="level" value="初级工程师"/>
			                  </c:if>
			                  <c:if test="${pp.programmer.level==1}">
			                  <c:set var="level" value="中级工程师"/>
			                  </c:if>
			                  <c:if test="${pp.programmer.level==2}">
			                  <c:set var="level" value="高级工程师"/>
			                  </c:if>
			                  <div class="sortes2">${pp.programmer.position}${level}</div>
			               </div>
			               <div class="sortes">
			                 <c:set var="day" value="${pp.endTime.getTime()-date.getTime()}"/>
			                 <fmt:formatNumber var="days"  value="${day/(1000*60*60*24)}"  pattern="#" type="number"/>
			                  <c:choose>
								<c:when test="${day<0}">
									<div class="sortes3"><a>0</a>（天）</div>
								</c:when>
							   <c:when test="${(day/(1000*60*60*24))>days}">
                                <div class="sortes3"><a>${days+1}</a>（天）</div>
								</c:when>
								<c:otherwise>
							  <div class="sortes3"><a>${days}</a>（天）</div>
								</c:otherwise>
							</c:choose>
			                  <div class="sortes4">
			                  <!-- 判断方式，当续约被管理员处理agree=1,提示已续约，当前时间>=客户续约的时间(是从0点算起)+24小时 （续约1天后） -->
			                  <c:choose>
								<c:when test="${pp.status==1}">
									 <a id="btn2">已申请</a>
								</c:when>
								<c:when test="${pp.agree==1}">
								<c:choose>
								     <c:when test="${date.getTime() >=pp.timeRecord.getTime()+(1000*60*60*18)}">
							          <a  class="present"  style="display: block;" >已续约</a>
								      <a  class="presents" style="display: none;" data-toggle="modal"  href="#signup-modal${index.count}">申请续约</a>
								     </c:when>
								     <c:otherwise>
								       <a  class="btn3" >已续约</a>
								    </c:otherwise>
							   </c:choose>
								</c:when>
								<c:otherwise>
									 <a data-toggle="modal"  href="#signup-modal${index.count}" >申请续约</a>
								</c:otherwise>
							</c:choose>
			                  </div>
			               </div>
			           </div>
			           
			           
			           <div class="modal in" id="signup-modal${index.count}">
								<a class="close" data-dismiss="modal">×</a>
								<h1>申请续约</h1>
										<form action="${path}/project/getExtensionTime" method="post"
											class="white-pink">
											<label><span>项目名称 :</span>
											 <input type="hidden" name="project.id" value="${project.id}">
											 <input type="hidden" name="status" value="${pp.status}">  
											 <input type="hidden" name="programmer.id"
												value="${pp.programmer.id}">
											<input readonly="readonly" onFocus="this.blur()" type="text"
												name="proName" value="${project.proName }" /> </label> 
											<label>
												<span>程序员 :</span>
											 <input readonly="readonly" onFocus="this.blur()" name="programmer.programmerName"
												type="text" value="${pp.programmer.programmerName}" />
											</label>
											 <label> <span>续约天数 :</span>
											  <input name="extension_time" placeholder="请输入数字" type="text" />
											</label> 
											<label> <span>&nbsp;</span> 
											<input type="submit" class="button" value="确认申请" />
											</label>
										</form>
									</div>
			          </c:forEach>
			           <script src="${path}/resources/static/js/landing-min.js?2013032917"></script>
			        </div>

		            <div id="pages">
				   	<pg:pager items="${count}" export="currentPageNumber=pageNumber"
						maxPageItems="${initParam.pagerNums}" maxIndexPages="5"
						isOffset="true" url="${path}/project/ProjectInfo">
						<pg:param name="id" value="${id}" />
						<pg:param name="details" value="details" />
						<pg:prev>
							<a class="current_page" href="${pageUrl}">前页</a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber}">
									<a >${pageNumber}</a>
								</c:when>
								<c:otherwise>
									<a class="current_page" href="${pageUrl}">${pageNumber }</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a class="current_page" href="${pageUrl}">后页</a>
						</pg:next>
					</pg:pager>
				    </div>
				    
				    </div>  
		         </div>
			 </div>
		</div>
	</div>
</div>
</body>
</html>