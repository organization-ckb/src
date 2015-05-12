/**
 * javascript
 */


 //登陆控制脚本
 
$(function(){
	
	var type=$("#hidden-type").val();
	
	
	//初始化页面的时候让用户名输入框得到焦点
	$("#loginName").focus();
	
	//绑定一个登录事件
	$("input[name='addcustomer']").click(function(){
			
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
		//alert("pwd:"+pwd);
		if(pwd==""){
			alert("密码不能为空!");
			$("#loginPwd").focus();
			return;
		}
		var pwd1=$.trim($("#loginPwd1").val());
		//alert("pwd1:"+pwd1);
		if(pwd1!=pwd){
			alert("两次密码输入不一致！");
			return;
		}
		if($.trim($("#customerName").val())==""){
			alert("公司名称不能为空!");
			$("#customerName").focus();
			return ;
		}
		if($.trim($("#customerMan").val())==""){
			alert("联系人名称不能为空!");
			$("#customerMan").focus();
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
		if($.trim($("#customerQq").val())==""){
			alert("QQ不能为空!");
			$("#customerQq").focus();
			return ;
		}
		
		var type=$("#hidden-type").val();
		if(type=="2"){ //edit
			$("#addcustomer").attr("action", "editCustomerResult");
		}
		
		
		//提交表单
		$("#addcustomer").submit();
		
	});
	
	
});











