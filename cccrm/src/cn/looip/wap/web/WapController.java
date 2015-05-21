package cn.looip.wap.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.looip.wap.repository.domain.Project;
import cn.looip.wap.repository.domain.SysUser;
import cn.looip.wap.service.interfaces.WapService;

@Controller
@RequestMapping("/wap")
public class WapController {

	@Autowired
	private WapService wapservice;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> getlogin(SysUser user) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (wapservice.getLogin(user)) {
				map.put("results", "1");
				Integer id = wapservice.getUserName(user.getLoginName());
				String ids = id.toString();
				map.put("id", ids);
			} else {
				map.put("results", "0");
			}
			map.put("resultcode", "200");
		} catch (Exception e) {
			map.put("resultcode", "0");
		}

		return map;

	}

	@RequestMapping("/getprojet") 
	@ResponseBody
	public Map<String, Object> getProjects(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		 try {
				List<Project> Projects = wapservice.getProjects(id);
				map.put("projects", Projects);
				map.put("resultcode", "200");
		} catch (Exception e) {
			map.put("resultcode", "0");
		}
	
		return map;
	}


}
