package cn.looip.wap.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.looip.wap.repository.domain.SysUser;
import cn.looip.wap.service.interfaces.WapService;

@Controller
@RequestMapping("/wap")
public class WapController {
	
  @Autowired
  private WapService wapservice;
  
 
  @RequestMapping("/login")
  @ResponseBody
  public Map<String, String> getjson(SysUser user) throws Exception
  {
	  String username=user.getLoginName();
	  String password=user.getLoginPwd();
	  Map<String, String> map = new HashMap<String, String>();

	  if("".equals(username)||"".equals(password)){
		  map.put("resultcode", "0");
	  }
	  else{
	      if(wapservice.getLogin(user))
	      {
	          map.put("results", "success");
	      }
	      else
	      {
	          map.put("results", "error");
	          
	      } 
	      map.put("resultcode", "200"); 
	      
	  }
     
	  return map;
     
  }
  
}
