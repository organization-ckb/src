// JavaScript Document
function emailCheck(s) {  
   
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
    if (!pattern.test(s)) {  
       
        return false;  
    }  
    return true;  
}

//手机号码验证信息 
function isMobil(s) { 
	var patrn = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/; 
	if (!patrn.exec(s)) 
	{ 
		return false; 
	} 
	return true; 
} 