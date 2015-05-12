/*
 * javascript
 */
$(function(){
	//var type = '<%= request.getAttribute("type") %>';
	//$("#hidden-type").val(type);
	var type=$("#hidden-type").val();
	//alert("type:"+type);
		
	//初始化页面的时候让用户名输入框得到焦点
	$("#username").focus();
	
	
	$("input[name='addprogrammer']").click(function(){
				
		//登录之前需要验证是否输入
		var loginName=$.trim($("#loginName").val());
		if(loginName==""){
			alert("用户名不能为空!");
			$("#loginName").focus();
			return ;
		}else if(!emailCheck(loginName)){
			alert("用户名格式不正确，请输入正确的邮箱作为用户名!");
			$("#loginName").focus();
			return ;
		}
		
		var pwd=$.trim($("#loginPwd").val());
		if(pwd==""){
			alert("密码不能为空!");
			$("#loginPwd").focus();
			return ;
		}
		var pwd1=$.trim($("#loginPwd1").val());
		if(pwd1!=pwd){
			alert("两次密码输入不一致！");
			return;
		}
		if($.trim($("#programmerName").val())==""){
			alert("姓名不能为空!");
			$("#programmerName").focus();
			return ;
		}
		var mobile=$.trim($("#userMobile").val());
		if(mobile==""){
			alert("手机号码不能为空!");
			$("#userMobile").focus();
			return ;
		}else if(!isMobil(mobile)){
			alert("手机号码输入有误!");
			$("#userMobile").focus();
			return ;
		}
		if($.trim($("#programmerQq").val())==""){
			alert("QQ不能为空!");
			$("#programmerQq").focus();
			return ;
		}
		
		var type=$("#hidden-type").val();
		if(type=="2"){ //edit
			$("#addprogrammer").attr("action", "editProgrammerResult");
		}
		
		
		//提交表单
		$("#addprogrammer").submit();
		
	});
	
	
});

