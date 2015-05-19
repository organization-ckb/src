package cn.looip.resource.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.jurisdiction.service.interfaces.JurisdictionService;
import cn.looip.resource.repository.domain.Programmer;
import cn.looip.resource.service.interfaces.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	// 权限业务接口
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private JurisdictionService jurisdictionService;

	// 新增程序员
	@RequestMapping("addProgrammer")
	public ModelAndView addProgrammer() {
		Map<String, Object> map = new HashMap<String, Object>();

		return new ModelAndView("user/addProgrammer", map);
	}

	// 查看程序员
	@RequestMapping("viewProgrammer")
	public ModelAndView viewProgrammer(Model model, int id) {
		Programmer programmer = resourceService.getProgrammer(id);
		model.addAttribute("programmer", programmer);
		model.addAttribute("type", 1); // 查看
		return new ModelAndView("user/addProgrammer");
	}

	// 编辑程序员
	@RequestMapping("editProgrammer")
	public ModelAndView editProgrammer(Model model, int id) {
		Programmer programmer = resourceService.getProgrammer(id);
		model.addAttribute("programmer", programmer);
		model.addAttribute("type", "2"); // 编辑
		model.addAttribute("id", id); // 编辑
		return new ModelAndView("user/addProgrammer");
	}

	@RequestMapping("editProgrammerResult")
	public String doEditProgrammerResult(Model model, Programmer programmer) {
		int count = resourceService.updateProgrammer(programmer);
		int count1 = jurisdictionService.updateSysUserByProgrammer(programmer);
		return "redirect:resourceManage";
	}

	@RequestMapping("addProgrammerResult")
	public ModelAndView doAddProgrammerResult(Programmer programmer) {

		int count = resourceService.insertProgrammer(programmer);
		SysUser sysuser = (SysUser) programmer;
		sysuser.setUserEmail(programmer.getLoginName());
		sysuser.setUserId(programmer.getId());
		sysuser.setUserType((short) 1);// 1:程序员
		int count1 = jurisdictionService.insertSysUser(sysuser);
		return new ModelAndView("user/programmerAddSuccess");
	}

	// 查询程序员列表
	@RequestMapping("resourceManage")
	public ModelAndView doResourceManage(Model model,
			HttpServletRequest request, HttpSession session, String mark) {
		if (mark != null) {
			session.setAttribute("mark", mark);
		}

		String pagerIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNumes"));
		SysUser user = (SysUser) session.getAttribute("loginUser");

		List<Programmer> programmers = resourceService.getprogrammers(
				pagerIndex, pagerNum);
		int count = resourceService.count();
		model.addAttribute("loginUser", user);
		model.addAttribute("programmers", programmers);
		model.addAttribute("department", "-1");
		model.addAttribute("status", "-1");
		model.addAttribute("count", count);
		return new ModelAndView("user/resourceManage");
	}

	// 按条件查询程序员
	@RequestMapping("programmerSearch")
	public ModelAndView doProgrammerSearch(Model model,
			@RequestParam(value = "department") int department,
			@RequestParam(value = "status") int status,HttpServletRequest request) {
		String pagerIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNumes"));	
		List<Programmer> programmers = resourceService.searchprogrammer(
				department, status,pagerIndex,pagerNum);
		int count = resourceService.Searchcount(department,status);
		model.addAttribute("programmers", programmers);
		model.addAttribute("department", department);
		model.addAttribute("status", status);
		model.addAttribute("search", "search");
		model.addAttribute("count", count);
		return new ModelAndView("user/resourceManage");

	}

}