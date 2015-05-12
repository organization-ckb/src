package cn.looip.jurisdiction.web;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import cn.looip.core.utils.Utils;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.jurisdiction.service.interfaces.JurisdictionService;

@Controller
@RequestMapping("/user")
public class UserController
{
	// 权限业务接口
	@Autowired
	private JurisdictionService jurisdictionService;

	@RequestMapping(value = "/home")
	public ModelAndView home()
	{
		Map<String, Object> map = new HashMap<String, Object>();

		return new ModelAndView("home", map);
	}

	@RequestMapping("/login")
	public ModelAndView doLogin()
	{
		return new ModelAndView("user/login");
	}
	
	@RequestMapping("/logout")
	public ModelAndView doLogOut(HttpServletRequest request)
	{
//		HttpSession session = request.getSession(); 
//		session.invalidate();
		return new ModelAndView("redirect:login");
	}

	@RequestMapping("/doLoginResult")
	public ModelAndView doLoginResult(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("loginName") String loginName,
			@RequestParam("loginPwd") String loginPwd)
	{
		// Map<String, Object> map = new HashMap<String, Object>();
		// boolean result = jurisdictionService.checkLogin(loginName, loginPwd);
		SysUser user = jurisdictionService.getLoginUser(loginName, loginPwd);
		if (user != null)
		{
			Cookie cookie = Utils.getCookieByName(request,"remember_flag");
			if(cookie!=null && cookie.getValue().equals("true")){
				Utils.addCookie(response, "loginName", user.getLoginName(), 3600*24*7);
				Utils.addCookie(response, "loginPwd", user.getLoginPwd(), 3600*24*7);
			}else{
//				Utils.addCookie(response, "loginName", null,0);
//				Utils.addCookie(response, "loginPwd", null,0);
			}
			HttpSession session = request.getSession(); 
			session.setAttribute("loginUser", user);
			//return new ModelAndView("redirect:../resource/resourceManage");
			return new ModelAndView("redirect:../project/projectManage");
		}
		else
		{
			// map.put("mes", "用户名/密码错误");
			model.addAttribute("loginName", loginName);
			model.addAttribute("loginPwd", loginPwd);
			model.addAttribute("msg", "用户名/密码错误");
			return new ModelAndView("user/login");
		}

	}

	// 新增程序员
	// @RequestMapping("addprogrammer")
	// public ModelAndView createprogrammer()
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// return new ModelAndView("addprogrammer", map);
	// }

	// @RequestMapping("addProgrammerResult")
	// public ModelAndView doAddprogrammer(Programmer programmer, Manager
	// manager)
	// {
	// int count = jurisdictionService.insertProgrammer1(programmer);
	// Integer id = programmer.getId();
	// manager.setUserID(id);
	// manager.setUserEmail(manager.getLoginName());
	// // System.out.println("id="+id);
	// jurisdictionService.insertProgrammer2(manager);
	// return new ModelAndView("programmeraddsuccess");
	// }
	//
	// // 查询程序员
	// @RequestMapping("resourceManage")
	// public ModelAndView doResourceManage(Model model)
	// {
	// // Map<String,Object> map = new HashMap<String, Object>();
	// List<Programmer> programmer = jurisdictionService.getprogrammer();
	// // List<Manager> programmers=jurisdictionService.getprogrammers();
	// // map.put("programmer", programmer);
	// model.addAttribute("programmer", programmer);
	// // model.addAttribute("programmer", programmers);
	// // System.out.println(programmer.get(0).getUserMobile());
	// return new ModelAndView("resourceManage");
	// }

	/*
	 * @RequestMapping(value = "/resourceManage", method = RequestMethod.GET)
	 * public ModelAndView addProgrammer(HttpServletRequest request) {
	 * Map<String,Object> map = new HashMap<String, Object>(); String
	 * beginIndex= request.getParameter("pager.offset"); int pagerNum =
	 * Integer.parseInt
	 * (request.getServletContext().getInitParameter("pagerNum")); int
	 * count=jurisdictionService.getNum(); List<Programmer> programmer=
	 * jurisdictionService.getProgrammer(beginIndex, pagerNum); // for(int
	 * i=0;i<projects.size();i++){ //
	 * System.out.println(projects.get(i).getProName()); // } //
	 * System.out.println(count);
	 * 
	 * map.put("programmer", programmer); map.put("count", count); return new
	 * ModelAndView("resourceManage"); }
	 */

	// 按条件查询程序员
	// @RequestMapping("programmersearch")
	// public ModelAndView doprogrammersearch(Model model,
	// @RequestParam(value = "searchdepartment") Integer searchdepartment)
	// {
	// List<Programmer> programmersearch =
	// jurisdictionService.searchprogrammer();
	// model.addAttribute("programmersearch", programmersearch);
	// return new ModelAndView("programmersearch");
	// }

	// 新增客户
	// @RequestMapping("addcustomer")
	// public ModelAndView createcustomer()
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// return new ModelAndView("addcustomer", map);
	// }

	// 新增管理员
	// @RequestMapping("addmanager")
	// public ModelAndView createmanager()
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// return new ModelAndView("addmanager", map);
	// }

	// 客户管理
	// @RequestMapping("customermanage")
	// public ModelAndView creatrecustomermanage()
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// return new ModelAndView("customermanage", map);
	// }

}