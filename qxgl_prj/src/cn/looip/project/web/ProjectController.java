package cn.looip.project.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
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

	/**
	 * 1、SpringMVC不能直接接收user.username需要加前缀支持 如果不需要带前缀就不许用加
	 * 
	 * @param binder
	 */

	// @InitBinder
	// public void initBinder(WebDataBinder binder) {

	// binder.setFieldDefaultPrefix("projec.");
	// }

	/*
	 * 定义日期格式
	 */
	@InitBinder
	public void InitBinder(ServletRequestDataBinder bin) {
		bin.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * addPro get请求访问下面方法 向页面传集合customer
	 * 
	 * @return 添加页面
	 */
	@RequestMapping(value = "/addPro", method = RequestMethod.GET)
	public String addProject(Model model,HttpSession session) {
		session.setAttribute("mark", "project");
		List<Customer> customer = proservice.getCustomer();
		long i=  proservice.getFinalid();
		model.addAttribute("customer", customer);
		model.addAttribute("serialNo", SerialNo.serialNo(i));
		return "/project/AddProject";
	}

	/**
	 * 方法一：Sturts2一样，jsp用前缀。 首先第一步@InitBinder，定义前缀 name 前缀.属性名
	 * 也可以不用前缀，方法名（@ModelAttribute A a ,BindingResult result） jsp name与属性名相同
	 * 添加项目，form表单提交Post请求访问下面方法
	 * 
	 * @param project
	 * @return 项目页面
	 */
	// @RequestMapping(value = "/addPros", method = RequestMethod.POST)
	// public String addProjects(@ModelAttribute Project projec,
	// BindingResult result) {//2、 BindingResult result 接收带前缀的参数
	// System.out.println(projec.getProName());
	// System.out.println(projec.getBargainNo());
	// return "/project/PM"; // 前面加redirect:重定向 默认forward:
	//
	// }
	/**
	 * 方法二：普通类 直接用对象接收参数
	 * 
	 * @param projec页面参数传到对象
	 * @return 项目页面
	 */
	@RequestMapping("/addPros")
	public String addPros(Project projec) {
		proservice.saveProject(projec);
		return "redirect:/project/projectManage";

	}

	/**
	 * 查询当前项目
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/projectManage", method = RequestMethod.GET)
	public String projectManage(Model model, HttpServletRequest request,HttpSession session,String mark) {
	    /*
		 * 修改已过期的项目状态
		 */
		proservice.updateStatus();
		proservice.updateProjectStates();
		List<ProgrammerProject> Pgprojecr=proservice.endTimeprogrammer(); 
		if(Pgprojecr!=null){
			 for (int i = 0; i < Pgprojecr.size(); i++) {
				 int id=Pgprojecr.get(i).getProgrammer().getId();			 
				 Integer ids=proservice.getProgrammerID(id);
			      if(ids!=null){
			    	 proservice.updatePprogrammerState(ids);
			      }
			 }
		}
		if(mark!=null){
			session.setAttribute("mark", mark);
		}else{
			session.setAttribute("mark", "project");
		}
		SysUser user=(SysUser) session.getAttribute("loginUser");
		int type=user.getUserType();
			String beginIndex = request.getParameter("pager.offset");
			int pagerNum = Integer.parseInt(request.getServletContext()
					.getInitParameter("pagerNum"));
		  if(type==0){
				/*
				 * 管理员登录
			     */
            String Name=user.getLoginName();
            session.setAttribute("nickname", Name);
			int count = proservice.getNum();
			List<Project> projects = proservice.getProjects(beginIndex, pagerNum);
			model.addAttribute("projects", projects);
			model.addAttribute("count", count);
			model.addAttribute("new1", "new1");
			return "project/projectManage";
		}else if(type==1){
			/*
			 * 程序员登录后
			*/
			int pgid=user.getUserId();//程序员ID
			 String Name=proservice.getprogrammerName(pgid);
	            session.setAttribute("nickname", Name);
			List<ProgrammerProject> Pprojects = proservice.getPprojects(beginIndex,
					pagerNum, pgid); // 程序员项目信息
			int count = proservice.getNumberes(pgid);
			model.addAttribute("Pprojects", Pprojects);
			Calendar c = Calendar.getInstance();
			Date date=c.getTime();
			model.addAttribute("date", date);
			model.addAttribute("count", count);
			return "/project/PMs";
		}else{
			     /*客户登录后跳转*/
			    //根据用户session编号查出，客户ID
				int cid=user.getUserId();//客户ID
				String Name=proservice.getcustomerName(cid);
				session.setAttribute("nickname", Name);
				int count = proservice.getNumbers(cid);
				List<Project> projects = proservice.getCProject(beginIndex, pagerNum,cid);
				model.addAttribute("projects", projects);
				model.addAttribute("count", count);
				return "/project/projectManage";	
		}
	}

	/**
	 * 查询历史项目
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
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

	/**
	 * 查询要修改的原有信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateProject", method = RequestMethod.GET)
	public String getProject(Model model, int id) {
		List<Customer> customer = proservice.getCustomer();
		Project project = proservice.getProject(id);
		model.addAttribute("customer", customer);
		model.addAttribute("project", project);
		return "/project/updateProject";

	}

	/**
	 * 修改项目
	 * 
	 * @param projec
	 * @return
	 */
	@RequestMapping("/updatePros")
	public String updatePros(Project projec) {
		proservice.updateProject(projec);
		return "redirect:/project/projectManage";// 重定向跳转到另一个方法

	}

	/**
	 * 删除项目
	 * 
	 * @param projec
	 * @return
	 */
	@RequestMapping(value = "/delectPros", method = RequestMethod.GET)
	public String delectPros(int id) {
		System.out.println(proservice.getPprogramm(id));
		
		  if(proservice.getPprogramm(id)!=null){
			  
			  int ppid=proservice.getPprojectID(id);
			  proservice.deletePprogrammer(ppid);
		  }
		  proservice.delectProject(id);
		
		
		return "redirect:/project/projectManage";// 重定向跳转到另一个方法

	}

	/**
	 * 根据项目名查询
	 * 
	 * @param proName
	 * @return
	 */
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

	/**
	 * 项目详情
	 * 
	 * @param id项目ID
	 * @return
	 */
	@RequestMapping(value = "/ProjectInfo", method = RequestMethod.GET)
	public String ProjectInfo(int id, Model model, HttpSession session,HttpServletRequest request) {
		SysUser user=(SysUser) session.getAttribute("loginUser");
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNums"));

		Project project = proservice.getProject(id);// 项目信息
		List<ProgrammerProject> Pprojects = proservice.getPproject(beginIndex,
				pagerNum, id); // 项目程序员信息
		int count = proservice.getNumber(id);// 条数
		String imgnum=proservice.imgNumber(id);//图片字符
		String[] strarray=null;
		int imgnums=0;
		if(imgnum!=null){
			strarray= imgnum.split("\\*"); //把每个图片字符串按*分隔
			imgnums=strarray.length;//图片个数
		}
		Calendar c = Calendar.getInstance();
		Date date=c.getTime();
		model.addAttribute("project", project);
		model.addAttribute("count", count);
		model.addAttribute("Pprojects", Pprojects);
		model.addAttribute("id", id);
		model.addAttribute("date", date);
		model.addAttribute("imgnums", imgnums);
		if(user.getUserType()==2){
		  return "/project/ProjectInfoes";
		}
		return "/project/ProjectInfo";

	}

	/**
	 * 显示可被添加的项目人员
	 * @param id    项目ID
	 * @param model 人员集合
	 * @return 项目分配人员
	 */
	@RequestMapping(value = "/addProgrammer", method = RequestMethod.GET)
	public String addProgrammer(int id, Model model) {
		/*  待开发
		 * 1查询所有结束工作的人员
		 * 2根据人员ID查询最后一个项目结束时间与系统时间比较
		 * 3最后时间确实结束则修改状态为‘闲置’
		 */
		
		

		/*
		 * 项目信息
		 */
		Project project = proservice.getProject(id);
		/*
		 * 人员集合
		 */
		List<Programmer> programmers = proservice.programmer();
		model.addAttribute("project", project);

		if (programmers.size() > 0) {
			model.addAttribute("programmers", programmers);
		} else {
			model.addAttribute("message", "没有闲置的人员！请手动踢出在工作人员后再来添加！");
		}

		return "/project/AddProjects";
	}

	/**
	 * 添加项目人员
	 * @param Pprojec程序员项目类
	 * @return
	 */
	@RequestMapping("/addProgrammers")
	public String addProgrammers(ProgrammerProject Pprojec,int status) {
		/*
		 * 添加
		 */
		proservice.saveProgrammers(Pprojec);
		int State=Pprojec.getProgrammer().getProgrammerStatus();
		/*
		 * 修改项目状态
		 */
		int projectState=status;
		proservice.updateProjectState(Pprojec.getProject().getId(),projectState);
		/*
		 * 修改程序员状态
		 */
		
		proservice.updateState(Pprojec.getProgrammer().getId(),State);
		
		/*
		 * 传递项目ID返回
		 */
		return "redirect:/project/addProgrammer?id=" + Pprojec.getProject().getId()
				+ "";

	}
	
	/**
	 * 踢出程序员
	 * @param id 程序员项目ID
	 * @param State  程序员状态
	 * @param pgid 程序员ID
	 * @param pid  项目ID
	 * @return
	 */
	@RequestMapping("/deleteProgrammers")
	public String deleteProgrammers(ProgrammerProject Pprojec) {
		int id=Pprojec.getId();
		int pgid=Pprojec.getProgrammer().getId();
		int pid=Pprojec.getProject().getId();
		short Status= Pprojec.getProgrammer().getProgrammerStatus();  
	    proservice.deletePprogrammer(id);
	    
	    if( proservice.getPprogrammer(id)==null){
	       proservice.updateState(pgid,Status);
	       if(proservice.getPprogramm(pid)==null){
	    	   proservice.updateProjectState(pid,3);
	       }
	    }
	  
		return "redirect:/project/ProjectInfo?id=" + pid
				+ "";
	}
	
	/**
	 * 申请续约
	 * @param Pproject
	 * @return
	 */
	@RequestMapping("/getExtensionTime")
	public String  getExtensionTime(ProgrammerProject Pproject,String details){
		proservice.updateExtension(Pproject);
		return "redirect:/project/ProjectInfo?id="+Pproject.getProject().getId()+"&details="+details+"";
		
	}
	
	/**
	 * 续约记录
	 * @param Pproject
	 * @return
	 */
	@RequestMapping(value = "/getExtensionInfo", method = RequestMethod.GET)
	public String  getExtensionInfo(Model model, HttpServletRequest request,HttpSession session){
		session.setAttribute("mark", "project");
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		
		List<ProgrammerProject> ProjectRecord=proservice.getRecord(beginIndex,pagerNum);
		int count =proservice.getRecordCount();
		model.addAttribute("count", count);
		model.addAttribute("ProjectRecord", ProjectRecord);
		model.addAttribute("date", new Date());
		return "/project/ExtensionInfo";
		
	}
	/**
	 * 同意续约
	 * @param id 项目续约的ID
	 * @param extensionTime续约的天数
	 * @param RemainingTime剩余的天数
	 * @return
	 */
	@RequestMapping(value = "/updateRecord", method = RequestMethod.GET)
	public String updateRecord(int id,int extensionTime,int RemainingTime,Date timeRecord){
		proservice.updateContract(id, extensionTime, RemainingTime,timeRecord);
		return "redirect:/project/getExtensionInfo";
		
	}
	
	
	@RequestMapping("/upload") 
	public String addFile(HttpServletRequest request,HttpServletResponse response,String FileName, int id) throws IllegalStateException, IOException{
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                       // System.out.println(myFileName);  
                        //重命名上传后的文件名 (原文件名+上传时间)
                        String fileName =file.getOriginalFilename();
                        String fileExt = fileName.substring(
    							fileName.lastIndexOf(".") + 1).toLowerCase();
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    					String newFileName = df.format(new Date());
                        String fileNames=newFileName+pre+"."+fileExt;
                        //定义上传路径  
                        //String path = "F:/" + fileNames; 
                        String path=request.getSession().getServletContext().getRealPath("/resources/contractImgs") + "/" +  fileNames;
                        File localFile = new File(path); 
                        if(!localFile.exists()){//如果文件夹不存在，自动创建
                        	localFile.mkdirs();
                           }
                        file.transferTo(localFile); 
                        //组成一个字符
                        if(FileName!=null){
                        	FileName=FileName+fileNames+"*";
                        }
                        else{
                        	FileName=fileNames+"*";
                        	 
                        }
                    }  
                }  
                //记录上传该文件后的时间  
//                int finaltime = (int) System.currentTimeMillis();  
//                System.out.println(finaltime - pre);
               
            }  
            proservice.saveFiles(id,FileName);
        }  

		  return "redirect:/project/ProjectInfo?id=" + id
					+ "";
	 }	
	
/*************************客户项目模块结束*****************************/
	@RequestMapping("/logout")
	public String logout( HttpSession session){
		session.removeAttribute("nickname");
		session.removeAttribute("loginUser");
		return "redirect:/user/login";
		
	}

	
	

}
