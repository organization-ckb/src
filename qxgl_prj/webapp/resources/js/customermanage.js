/*function gotoPage(url){
	alert(url);
	var status=$("#sel_status").val();
	alert(url+"&status="+status);
	window.location="http://localhost:8080/cccrm/customer/"+url+"&status="+status;
	
	
}*/


$(function(){
	var status = $("#hidden-status").val();
	
	if(status!=null){		
		$("#sel_status").find("option[value='"+status+"']").attr("selected",true); 
	}
	
	$("btn_search").click(function(){
		$("#btn_search").submit();
	})	
}) ;