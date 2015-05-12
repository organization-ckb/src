/**
 * javascript
 */

/*
 * 登陆控制脚本
 */
$(function(){
	//绑定一个登录事件
	$("input[name='addmanager']").click(function(){
		//登录之前需要验证是否输入
		if($.trim($("#loginname").val())==""){
			alert("登录名不能为空!");
			$("#loginname").focus();
			return ;
		}
		if($.trim($("#loginpwd").val())==""){
			alert("密码不能为空!");
			$("#loginpwd").focus();
			return ;
		}
		if($.trim($("#useremail").val())==""){
			alert("邮箱不能为空!");
			$("#useremail").focus();
			return ;
		}
		if($.trim($("#usermobile").val())==""){
			alert("联系方式不能为空!");
			$("#usermobile").focus();
			return ;
		}
		
		//提交表单
		$("#addmanager").submit();
		
	});
	
	//初始化页面的时候让用户名输入框得到焦点
	$("#loginname").focus();
});

