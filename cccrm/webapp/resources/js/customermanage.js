

$(function(){
	var status = $("#hidden-status").val();
	
	if(status!=null){		
		$("#sel_status").find("option[value='"+status+"']").attr("selected",true); 
	}
	
	$("btn_search").click(function(){
		$("#btn_search").submit();
	})	
}) ;