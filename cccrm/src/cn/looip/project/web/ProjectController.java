package cn.looip.project.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.looip.core.utils.SerialNo;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.project.repository.domain.Customer;
import cn.looip.project.repository.domain.Programmer;
import cn.looip.project.repository.domain.ProgrammerProject;
import cn.looip.project.repository.domain.Project;
import cn.looip.project.service.interfaces.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService proservice;

	@InitBinder
	public void InitBinder(ServletRequestDataBinder bin) {
		bin.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping(value = "/addPro", method = RequestMethod.GET)
	public String addProject(Model model, HttpSession session, String massage,
			String massages) throws UnsupportedEncodingException {
		session.setAttribute("mark", "project");
		List<Customer> customer = proservice.getCustomer();
		long i = proservice.getFinalid();
		if (massage != null) {
			model.addAttribute("massage",
					new String(massage.getBytes("ISO8859-1"), "UTF-8"));
		}
		if (massages != null) {
			model.addAttribute("massages",
					new String(massages.getBytes("ISO8859-1"), "UTF-8"));
		}
		model.addAttribute("customer", customer);
		model.addAttribute("serialNo", SerialNo.serialNo(i));
		return "/project/AddProject";
	}

	@RequestMapping("/addPros")
	public String addPros(Project projec, Model model) {
		String BargainNo = projec.getBargainNo();
		String ProName = projec.getProName();
		if (proservice.determineProject(ProName, BargainNo) == null) {
			proservice.saveProject(projec);
			if (proservice.determineProject(ProName, BargainNo) != null) {
				model.addAttribute("massage", "添加项目成功，是否继续添加？");
			}
		} else {
			model.addAttribute("massages", "项目已添加，请勿重复提交");
		}

		return "redirect:/project/addPro";

	}

	@RequestMapping(value = "/projectManage", method = RequestMethod.GET)
	public String projectManage(Model model, HttpServletRequest request,
			HttpSession session, String mark) {
		proservice.updateProjectStates();
		if (mark != null) {
			session.setAttribute("mark", mark);
		} else {
			session.setAttribute("mark", "project");
		}
		SysUser user = (SysUser) session.getAttribute("loginUser");
		int type = user.getUserType();
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		if (type == 0) {
			String Name = user.getLoginName();
			session.setAttribute("nickname", Name);
			int count = proservice.getNum();
			List<Project> projects = proservice.getProjects(beginIndex,
					pagerNum);
			model.addAttribute("projects", projects);
			model.addAttribute("count", count);
			model.addAttribute("new1", "new1");
			return "project/projectManage";
		} else if (type == 1) {
			int pgid = user.getUserId();
			String Name = proservice.getprogrammerName(pgid);
			session.setAttribute("nickname", Name);
			List<ProgrammerProject> Pprojects = proservice.getPprojects(
					beginIndex, pagerNum, pgid);
			int count = proservice.getNumberes(pgid);
			model.addAttribute("Pprojects", Pprojects);
			Calendar c = Calendar.getInstance();
			Date date = c.getTime();
			model.addAttribute("date", date);
			model.addAttribute("count", count);
			return "/project/PMs";
		} else {
			int cid = user.getUserId();
			String Name = proservice.getcustomerName(cid);
			session.setAttribute("nickname", Name);
			int count = proservice.getNumbers(cid);
			List<Project> projects = proservice.getCProject(beginIndex,
					pagerNum, cid);
			model.addAttribute("projects", projects);
			model.addAttribute("count", count);
			return "/project/projectManage";
		}
	}

	@RequestMapping(value = "/historyProjects", method = RequestMethod.GET)
	public String getHistoryProjects(Model model, HttpServletRequest request) {
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		int count = proservice.getNums();
		List<Project> projects = proservice.getProjectes(beginIndex, pagerNum);
		model.addAttribute("projects", projects);
		model.addAttribute("count", count);
		model.addAttribute("old1", "old1");
		return "project/projectManage";
	}

	@RequestMapping(value = "/updateProject", method = RequestMethod.GET)
	public String getProject(Model model, int id) {
		List<Customer> customer = proservice.getCustomer();
		Project project = proservice.getProject(id);
		model.addAttribute("customer", customer);
		model.addAttribute("project", project);
		return "/project/updateProject";

	}

	@RequestMapping("/updatePros")
	public String updatePros(Project projec) {
		proservice.updateProject(projec);
		return "redirect:/project/projectManage";
	}

	@RequestMapping(value = "/delectPros", method = RequestMethod.GET)
	public String delectPros(int id) {
		if (proservice.getPprogramm(id) != null) {
			List<ProgrammerProject> ProgrammerProjects = proservice
					.getPprojectID(id);
			for (int i = 0; i < ProgrammerProjects.size(); i++) {
				proservice.deletePprogrammer(ProgrammerProjects.get(i).getId());
			}
		}
		proservice.delectProject(id);
		return "redirect:/project/projectManage";

	}

	@RequestMapping("/seachPros")
	public String seach(Model model, HttpServletRequest request, String proName) {
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		int count = proservice.getNumes(proName);
		List<Project> projects = proservice.getSeach(beginIndex, pagerNum,
				proName);
		model.addAttribute("projects", projects);
		model.addAttribute("count", count);
		model.addAttribute("seach", "seach");
		return "/project/projectManage";
	}

	@RequestMapping(value = "/ProjectInfo", method = RequestMethod.GET)
	public String ProjectInfo(int id, Model model, HttpSession session,
			HttpServletRequest request) {
		SysUser user = (SysUser) session.getAttribute("loginUser");
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNums"));

		Project project = proservice.getProject(id);
		List<ProgrammerProject> Pprojects = proservice.getPproject(beginIndex,
				pagerNum, id);

		int count = proservice.getNumber(id);
		String imgnum = proservice.imgNumber(id);
		String[] strarray = null;
		int imgnums = 0;
		if (imgnum != null) {
			strarray = imgnum.split("\\*");
			imgnums = strarray.length;
		}
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		model.addAttribute("project", project);
		model.addAttribute("count", count);
		model.addAttribute("Pprojects", Pprojects);
		model.addAttribute("id", id);
		model.addAttribute("date", date);
		model.addAttribute("imgnums", imgnums);
		if (user.getUserType() == 2) {
			return "/project/ProjectInfoes";
		}
		return "/project/ProjectInfo";

	}

	@RequestMapping(value = "/addProgrammer", method = RequestMethod.GET)
	public String addProgrammer(int id, Model model) {
		Project project = proservice.getProject(id);
		List<Programmer> programmers = proservice.programmer();
		model.addAttribute("project", project);

		if (programmers.size() > 0) {
			model.addAttribute("programmers", programmers);
		} else {
			model.addAttribute("message", "没有闲置的人员！请手动踢出在工作人员后再来添加！");
		}

		return "/project/AddProjects";
	}

	@RequestMapping("/addProgrammers")
	public String addProgrammers(ProgrammerProject Pprojec, int status) {
		int ProjectID = Pprojec.getProject().getId();
		int ProgrammerID = Pprojec.getProgrammer().getId();

		if (proservice.determinePproject(ProgrammerID, ProjectID) == null) {// 防止表单重复提交
			proservice.saveProgrammers(Pprojec);
			int State = Pprojec.getProgrammer().getProgrammerStatus();
			int projectState = status;
			proservice.updateProjectState(Pprojec.getProject().getId(),
					projectState);
			proservice.updateState(Pprojec.getProgrammer().getId(), State);
		}

		return "redirect:/project/addProgrammer?id="
				+ Pprojec.getProject().getId() + "";

	}

	@RequestMapping("/deleteProgrammers")
	public String deleteProgrammers(ProgrammerProject Pprojec) {
		int id = Pprojec.getId();
		int pgid = Pprojec.getProgrammer().getId();
		int pid = Pprojec.getProject().getId();
		Integer pgids = proservice.getProgrammersID(pgid);// 查询程序员最后一次项目，是否已结束，没结束返回ID
		proservice.deletePprogrammer(id);
		if (proservice.getPprogrammer(id) == null) {// 判断项目还存不存在
			if (pgids != null) {// 判断是不是最后一个项目，是则修改为闲置
				proservice.updatePprogrammerState(pgids);
			}
			if (proservice.getPprogramm(pid).size() == 0) {// 判断项目中有没有程序员
				proservice.updateProjectState(pid, 3);
			}
		}

		return "redirect:/project/ProjectInfo?id=" + pid + "";
	}

	@RequestMapping("/getExtensionTime")
	public String getExtensionTime(ProgrammerProject Pproject, String details) {
		proservice.updateExtension(Pproject);
		return "redirect:/project/ProjectInfo?id="
				+ Pproject.getProject().getId() + "&details=" + details + "";

	}

	@RequestMapping(value = "/getExtensionInfo", method = RequestMethod.GET)
	public String getExtensionInfo(Model model, HttpServletRequest request,
			HttpSession session) {
		session.setAttribute("mark", "project");
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		List<ProgrammerProject> ProjectRecord = proservice.getRecord(
				beginIndex, pagerNum);
		int count = proservice.getRecordCount();
		model.addAttribute("count", count);
		model.addAttribute("ProjectRecord", ProjectRecord);
		model.addAttribute("date", new Date());
		return "/project/ExtensionInfo";

	}

	@RequestMapping(value = "/updateRecord", method = RequestMethod.GET)
	public String updateRecord(int id, int extensionTime, int RemainingTime,
			Date timeRecord) {
		proservice.updateContract(id, extensionTime, RemainingTime, timeRecord);
		return "redirect:/project/getExtensionInfo";

	}

	@RequestMapping("/upload")
	public String addFile(HttpServletRequest request,
			HttpServletResponse response, String FileName, int id)
			throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
//				int pre = (int) System.currentTimeMillis();//开始时时间
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String myFileName = file.getOriginalFilename();
					if (myFileName.trim() != "") {
						String fileName = file.getOriginalFilename();
						String fileExt = fileName.substring(
								fileName.lastIndexOf(".") + 1).toLowerCase();
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyyMMddHHmmss");
						String newFileName = df.format(new Date());	 
						String fileNames = newFileName +  new Random().nextInt(1000) + "." + fileExt;
						String path = "/usr/local/tomcat/webapps/crm/contractImgs/"
								+ fileNames;
//						String path = "F:/" + fileNames;
						// String path =
						// request.getSession().getServletContext()
						// .getRealPath("/resources/contractImgs")
						// + "/" + fileNames;
						File localFile = new File(path);
						if (!localFile.exists()) {// 如果文件夹不存在，自动创建
							localFile.mkdirs();
						}
						file.transferTo(localFile);
						if (FileName != null) {
							FileName = FileName + fileNames + "*";
						} else {
							FileName = fileNames + "*";

						}
					}
				}
			}
			proservice.saveFiles(id, FileName);
		}
		return "redirect:/project/ProjectInfo?id=" + id + "";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("nickname");
		session.removeAttribute("loginUser");
		session.removeAttribute("mark");
		return "redirect:/user/login";
	}

	@RequestMapping("/download")
	public void download(String proImage,int id, HttpServletResponse response)
			throws IOException {
		if (proImage != "") {
			String strArray = null;
			strArray = proImage;
			// 接收参数时，默认将编码转换为ISO-8859-1,将其重新转码为UTF-8
			strArray = new String(strArray.getBytes("ISO-8859-1"), "utf-8");
			String[] fileNameArray = strArray.split("\\*");
			String zipFileName = "Image.zip";
			response.setContentType("application/x-msdownload"); // 通知客户文件的MIME类型：
			response.setHeader("Content-disposition", "attachment;filename="
					+ zipFileName);
			// 要下载的文件目录
			ZipOutputStream zos = new ZipOutputStream(
					response.getOutputStream());
			for (String fileName : fileNameArray) {
				String path="/usr/local/tomcat/webapps/crm/contractImgs/"+fileName;//文件路径
				File file = new File(path);
				doZip(file, zos);
			}
			zos.close();
		}
		
	}

	private void doZip(File file, ZipOutputStream zos) throws IOException {
		if (file.exists()) {
			if (file.isFile()) {
				// 如果是文件，写入到 zip 流中
				zos.putNextEntry(new ZipEntry(file.getName()));
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int r = 0;
				while ((r = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, r);
				}
				zos.flush();
				fis.close();
			} else {
				// 如果是目录。递归查找里面的文件
				String dirName = file.getName() + "/";
				zos.putNextEntry(new ZipEntry(dirName));
				File[] subs = file.listFiles();
				for (File f : subs) {
					doZip(f, zos);
				}
			}
		}
	}

}
