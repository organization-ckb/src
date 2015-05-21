package cn.looip.jurisdiction.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.looip.core.utils.Utils;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.jurisdiction.service.interfaces.JurisdictionService;
import cn.looip.project.repository.domain.ProgrammerProject;
import cn.looip.project.service.interfaces.ProjectService;

@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private JurisdictionService jurisdictionService;
	@Autowired
	private ProjectService proservice;

	
	@RequestMapping("/checkEmail")
	@ResponseBody
	public Map<String, String> getjson(SysUser user) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			System.out.println(jurisdictionService.getUserEmail(user));
			if (jurisdictionService.getUserEmail(user)) {
				map.put("results", "1");
			} else {
				map.put("results", "0");
			}
			map.put("resultcode", "200");
		} catch (Exception e) {
			map.put("resultcode", "0");
		}
		
		return map;

	}


	  
	
	@RequestMapping(value = "/home")
	public ModelAndView home()
	{
		Map<String, Object> map = new HashMap<String, Object>();

		return new ModelAndView("home", map);
	}

	@RequestMapping("/login")
	public ModelAndView doLogin(HttpSession session)
	{  
		if(session.getAttribute("nickname")!=null){
			session.removeAttribute("nickname");
		}
		return new ModelAndView("user/login");
	}
	
	@RequestMapping("/logout")
	public ModelAndView doLogOut(HttpServletRequest request)
	{
		return new ModelAndView("redirect:login");
	}

	@RequestMapping("/doLoginResult")
	public ModelAndView doLoginResult(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("loginName") String loginName,
			@RequestParam("loginPwd") String loginPwd)
	{
		SysUser user = jurisdictionService.getLoginUser(loginName, loginPwd);
		if (user != null)
		{
			Cookie cookie = Utils.getCookieByName(request,"remember_flag");
			if(cookie!=null && cookie.getValue().equals("true")){
				Utils.addCookie(response, "loginName", user.getLoginName(), 3600*24*7);
				Utils.addCookie(response, "loginPwd", user.getLoginPwd(), 3600*24*7);
			}
			HttpSession session = request.getSession(); 
			session.setAttribute("loginUser", user);
			/**
			 * 登录成功：修改已结束的项目状态，查询已结束的程序员ID，
			 * 查出最后一次项目都已经结束了的ID，修改程序员状态;
			 */
			proservice.updateStatus();
			List<ProgrammerProject> Pgprojecr = proservice.endTimeprogrammer();
			if (Pgprojecr != null) {
				for (int i = 0; i < Pgprojecr.size(); i++) {
					int id = Pgprojecr.get(i).getProgrammer().getId();
					Integer ids = proservice.getProgrammerID(id);
					if (ids != null) {
						proservice.updatePprogrammerState(ids);
					}
				}
			}
			
			return new ModelAndView("redirect:../project/projectManage");
		}
		else
		{
			model.addAttribute("loginName", loginName);
			model.addAttribute("loginPwd", loginPwd);
			model.addAttribute("msg", "用户名/密码错误");
			return new ModelAndView("user/login");
		}
	}
	
}