/**
 * 
 * 按条件查询程序员
 */

function addResouce(){
//	alert("addResouce");
	window.location='http://localhost:8080/cccrm/resource/addProgrammer';
	//document.location.href='http://www.baidu.com';
	//window.open('http://www.baidu.com','_self')
}


$(function(){
	//alert("loginName:"+$.cookie('loginName'));
	//alert("loginPwd:"+$.cookie('loginPwd'));
	//$.cookie('loginName',$.cookie('loginName'),{expires:7});
	//$.cookie('loginPwd',$.cookie('loginPwd'),{expires:7});
	//var department = '<%= request.getAttribute("department") %>';
	//var status = '<%= request.getAttribute("status") %>';
	//alert("department,status:"+department+","+status);
	var department = $("#hidden-department").val();
	var status = $("#hidden-status").val();
	var userName = $("#hidden-username").val();
	var userType = $("#hidden-usertype").val();
	//alert("department,status:"+department+","+status);
	//alert("userName,userType:"+userName+","+userType);
	if(department!=null){
		$("#sel_department").find("option[value='"+department+"']").attr("selected",true); 
		
	}
	if(status!=null){		
		$("#sel_status").find("option[value='"+status+"']").attr("selected",true); 
	}
	if(userName!=null){
		$("#lbl-user").html(userName);
	}
	if(userType!=null){
		if(userType=="0"){ //管理员
			$("#div-zy").css("display","block");
			$("#div-xm").css("display","block");
			$("#div-kh").css("display","block");
		}else if(userType=="1"){ //程序员
			$("#div-zy").css("display","none");
			$("#div-xm").css("display","block");
			$("#div-kh").css("display","none");
		}else if(userType=="2"){ //客户
			$("#div-zy").css("display","none");
			$("#div-xm").css("display","block");
			$("#div-kh").css("display","none");
		}
		
	}
	
	/*
	$("input[name='sousuo']").click(function(){
		
		$("#programmersearch").submit();
		
	})*/
	$("#btn_search").click(function(){
		/*if(1==1){
			alert(111);
			return false;
		}*/
		$("#programmersearch").submit();
		
	})
});