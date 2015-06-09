package cn.looip.wap.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.looip.jurisdiction.repository.domain.Mail;
import cn.looip.project.service.interfaces.ProjectService;
import cn.looip.wap.repository.domain.ProgrammerProject;
import cn.looip.wap.repository.domain.Project;
import cn.looip.wap.repository.domain.SysUser;
import cn.looip.wap.service.interfaces.WapService;

@Controller
@RequestMapping("/wap")
public class WapController
{

	@Autowired
	private WapService wapservice;
	@Autowired
	private ProjectService proservice;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> getlogin(SysUser user)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			if (wapservice.getLogin(user))
			{
				map.put("results", "1");
				Integer id = wapservice.getUserName(user.getLoginName());
				String ids = id.toString();
				map.put("id", ids);
			}
			else
			{
				map.put("results", "0");
			}
			map.put("resultcode", "200");
		}
		catch (Exception e)
		{
			map.put("resultcode", "0");
		}

		return map;

	}

	@RequestMapping("/getprojet")
	@ResponseBody
	public Map<String, Object> getProjects(int id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			List<Project> Projects = wapservice.getProjects(id);
			map.put("projects", Projects);
			map.put("resultcode", "200");
		}
		catch (Exception e)
		{
			map.put("resultcode", "0");
		}

		return map;
	}

	@RequestMapping("/projectinfo")
	@ResponseBody
	public Map<String, Object> getProjectInfo(int id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			List<ProgrammerProject> projectinfo = wapservice.getProjectInfo(id);
			map.put("projetinfo", projectinfo);
			map.put("resultcode", "200");
		}
		catch (Exception e)
		{
			map.put("resultcode", "0");
		}
		return map;

	}

	@RequestMapping("/checkEmail")
	@ResponseBody
	public Map<String, String> getjson(SysUser user)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			// System.out.println(jurisdictionService.getUserEmail(user));
			if (wapservice.getUserEmail(user))
			{
				int loginPwd = (int) (Math.random() * 900000 + 100000);
				Mail sendmail = new Mail();
				sendmail.setHost("smtp.qq.com");
				sendmail.setUserName("956894327@qq.com");
				sendmail.setPassWord("7852152.xzw");
				sendmail.setTo(user.getUserEmail());
				// System.out.println(user.getUserEmail());
				sendmail.setFrom("956894327@qq.com");
				sendmail.setSubject("密码取回");
				sendmail.setContent("你的密码是：" + loginPwd);// 随机产生六位数的密码
				sendmail.sendMail();
				// 根据产生的随机六位数重新设置密码
				wapservice.updatePasswordByEmail(loginPwd, user.getUserEmail());

				map.put("results", "1");
			}
			else
			{
				map.put("results", "0");
			}
			map.put("resultcode", "200");
		}
		catch (Exception e)
		{
			map.put("resultcode", "0");
		}

		return map;

	}

	// 查看项目合同照片
	@RequestMapping("/getImgNumber")
	@ResponseBody
	public Map<String, Object> getImgNumber(int id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			String imgnum = wapservice.imgNumber(id);
			String[] strarray = null;
			int imgnums = 0;
			if (imgnum != null)
			{
				strarray = imgnum.split("\\*");
				imgnums = strarray.length;
				map.put("imgnums", imgnums);
				map.put("resultcode", "200");
			}
		}
		catch (Exception e)
		{
			map.put("resultcode", "0");
		}
		return map;
	}
	
	// 续约
	
}
