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
<link rel="stylesheet" type="text/css" href="../resources/static/css/ui2.css?2013032917">
<link href="../resources/css/fullscreenstyle.css" type="text/css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" media="all" href="../resources/css/Tipswindown.css" />
<script type="text/javascript" src="../resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../resources/js/jquery.fullscreenslides.js"></script>
<script type="text/javascript" src="../resources/js/Tipswindown.js"></script>

<script type="text/javascript">
	function shows() {
		$("#two").show();//表示display:block, 
		$("#one").hide();//表示display:none; 
	};

	function showed() {
		$("#one").show();//表示display:block, 
		$("#two").hide();//表示display:none; 
	};
	$(function() {
		$('.image img').fullscreenslides();
		var $container = $('#fullscreenSlideshowContainer');
		$container.bind("init",function() {
		$container
		.append('<div class="ui" id="fs-close">&times;</div>')
		.append('<div class="ui" id="fs-loader">Loading...</div>')
		.append('<div class="ui" id="fs-prev">&lt;</div>')
		.append('<div class="ui" id="fs-next">&gt;</div>')
		.append('<div class="ui" id="fs-caption"><span></span></div>');
			$('#fs-prev').click(function() {
				$container.trigger("prevSlide");
			});
			$('#fs-next').click(function() {
			    $container.trigger("nextSlide");
			});
			$('#fs-close').click(function() {
				$container.trigger("close");
			});
			}).bind("startLoading", function() {
				$('#fs-loader').show();
			}).bind("stopLoading", function() {
				$('#fs-loader').hide();
			}).bind("startOfSlide", function(event, slide) {
				$('#fs-caption span').text(slide.title);
				$('#fs-caption').show();
			}).bind("endOfSlide", function(event, slide) {
				$('#fs-caption').hide();
			});
	});
	$(function() {
		var imgnum = $("#imgnums");//数据库已有的文件数
		$("#submit").click(function() {
			var filenum = $("input[type='file']");
			var nums = filenum.length;//上传的文件总个数
			var num = 0;//上传的文件个数
			$(filenum).each(function() {
				if ($(this).val() != "") {
					num = num + 1
				}
				//   alert($(this).val())

			});
			// alert(num);//1
			var imgnummass = 8 - imgnum.val();
			if (imgnum.val() >= 8) {
				alert("图片总数不能大于8张");
				return false;
			} else if (8 - imgnum.val() < num) {
				alert("您最多还可以上传" + imgnummass + "张");
				return false;
			} else {
				return true;
			}
		})
	});
</script>
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
               </div>
				<div id="record">
					<div id="subfield">分栏</div>
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
								<fmt:formatDate var="starttime" value="${project.starttime}"
									type="both" pattern="yyyy-MM-dd " />
								<td>${starttime}</td>
								<th>结束时间</th>
								<fmt:formatDate var="endtime" value="${project.endtime}"
									type="both" pattern="yyyy-MM-dd " />
								<td>${endtime}</td>
							</tr>
						</table>

					</div>

					<!--数据区域：用表格展示数据-->
					<div id="data">
						<div id="one" class="column" style="display: block;">
							<div id="parts">
								<div class="parts1">
									<a>项目成员</a>
								</div>
								<div class="parts2" onmouseover="shows()">
									<a>项目合同</a>
								</div>
							</div>
							<div id="part">
								<div class="border">
									<div class="border1">程序员</div>
									<c:if test="${project.status!=0}">
										<div class="border2">剩余工作时间（人/天）</div>
									</c:if>
									<c:if test="${project.status==0}">
										<div class="border2">总消耗时间（人/天）</div>
									</c:if>
								</div>
								<div id="cents">

									<c:forEach var="pp" items="${Pprojects}" varStatus="index">
										<form action="${path}/project/deleteProgrammers" method="post">
											<div class="centes">
												<div class="sort">
													<img src="../resources/img/userImage.png" />
												</div>

												<div class="sorts">
													<div class="sortes1">
														<a href="#">${pp.programmer.programmerName}</a>
													</div>
													<c:if test="${pp.programmer.level==0}">
														<c:set var="level" value="初级工程师" />
													</c:if>
													<c:if test="${pp.programmer.level==1}">
														<c:set var="level" value="中级工程师" />
													</c:if>
													<c:if test="${pp.programmer.level==2}">
														<c:set var="level" value="高级工程师" />
													</c:if>
													<div class="sortes2">${pp.programmer.position}${level}</div>
												</div>
												<div class="sortes">
													<c:if test="${project.status!=0}">

														<c:set var="day"
															value="${pp.endTime.getTime()-date.getTime()}" />
														<fmt:formatNumber var="days"
															value="${day/(1000*60*60*24)}" pattern="#" type="number" />
														<c:choose>
															<c:when test="${day<0}">
																<div class="sortes3">
																	<a>0</a>（天）
																</div>
															</c:when>
															<c:when test="${(day/(1000*60*60*24))>days}">
																<div class="sortes3">
																	<a>${days+1}</a>（天）
																</div>
															</c:when>
															<c:otherwise>
																<div class="sortes3">
																	<a>${days}</a>（天）
																</div>
															</c:otherwise>
														</c:choose>
														<div class="sortes4">
															<!-- Status程序员当前状态 -->
															<input type="hidden" name="id" value="${pp.id}">
															<input type="hidden" name="programmer.id"
																value="${pp.programmer.id}"> <input
																type="hidden" name="project.id" value="${project.id}">
															<input type="hidden" name="programmer.programmerStatus"
																value="${pp.programmer.programmerStatus}"> <input
																type="submit" value="移出项目">
														</div>
													</c:if>
													<c:if test="${project.status==0}">
														<c:set var="day"
															value="${pp.endTime.getTime()-pp.beginTime.getTime()}" />
														<fmt:formatNumber var="days"
															value="${day/(1000*60*60*24)}" pattern="#" type="number" />
														<div class="sortes3">
															<a>${days}</a>（天）
														</div>
														<div class="sortes4">
															<input type="button" value="项目已结束">
														</div>
													</c:if>
												</div>
											</div>
										</form>
									</c:forEach>
								</div>


								<div id="pages">
									<pg:pager items="${count}"
										export="currentPageNumber=pageNumber"
										maxPageItems="${initParam.pagerNums}" maxIndexPages="5"
										isOffset="true" url="${path}/project/ProjectInfo">
										<pg:param name="id" value="${id}" />
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
						<div id="two" class="columns" style="display: none;">
							<div id="parts">
								<div class="parts3" onmouseover="showed()">
									<a>项目成员</a>
								</div>
								<div class="parts4">
									<a>项目合同</a>
								</div>
							</div>
							<div id="partes">
								<c:forTokens var="Pprojectes" items="${project.proImage}"
									delims="*">
									<div class="image">
										<a href="../resources/contractImgs/${Pprojectes}"> <img
											src="../resources/contractImgs/${Pprojectes}" />
										</a>
									</div>
								</c:forTokens>
								<c:if test="${imgnums!=8 }">
									<div class="images">
										<img id="play"  src="../resources/img/imgs.png" />
									</div>
								</c:if>
								<div id="overlay"></div>
	                            <div id="win">
		                        <h2><span id="close">×</span></h2>
		                        <form class="signup-form clearfix" method="post"
										action="${path}/project/upload" enctype="multipart/form-data">
										<input type="hidden" name="id" value="${project.id}">
										<input type="hidden" name="FileName" value="${project.proImage}">
										 <input type="hidden" id="imgnums" value="${imgnums}"> 
										 <input type="file" name="file" accept="image/*">
										 <input type="file" name="file1" accept="image/*">
										 <input type="file" name="file2" accept="image/*"> 
										 <input type="submit" id="submit" name="type" class="buttones" value="上传">
								</form>
	                            </div>
							</div>

							<div id="bottomes">
								<input type="button" value="下载全部">
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>
</body>
</html>