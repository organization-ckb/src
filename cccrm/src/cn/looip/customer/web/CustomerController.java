package cn.looip.customer.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.looip.customer.repository.domain.Customer;
import cn.looip.customer.service.interfaces.CustomerService;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.jurisdiction.service.interfaces.JurisdictionService;



@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private JurisdictionService jurisdictionService;
	
	@RequestMapping("addCustomer" )
	public ModelAndView addCustomer()
	{
		Map<String, Object> map = new HashMap<String , Object>();
			
		return new ModelAndView("user/addCustomer",map);
	}
	
	//新增客户
	@RequestMapping("addCustomerResult")
	public ModelAndView addcustomers(Customer customer,SysUser sysUser)
	{
		int count = customerService.insertCustomer(customer);
		SysUser sysuser=(SysUser)customer;
		sysuser.setUserEmail(customer.getLoginName());
		sysuser.setUserId(customer.getId());
		customer.setUserEmail(customer.getLoginName());
		sysuser.setUserType((short)2);//1:程序员
		int count1=jurisdictionService.insertSysUser(sysuser);
		//boolean f=jurisdictionService.checkLogin("autumnhu@looip.cn", "123456");
		return new ModelAndView("user/customeraddsuccess");
	}
	
	//查询所有客户
	@RequestMapping(value="customerManage" )
	public ModelAndView customerManage(Model model, HttpServletRequest request,HttpSession session,String mark)
	{  
		if(mark!=null){
		 session.setAttribute("mark", mark);
		}
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		
		int count = customerService.getNum();
		List<Customer> customer = customerService.getCustomers(beginIndex, pagerNum);
			//System.out.println("客户总条数="+count);
			model.addAttribute("customers", customer);
			model.addAttribute("count", count);
			model.addAttribute("allcustomers", "allcustomers");
		return new ModelAndView("user/customerManage");//customerManage
	}
	
	//按条件查询客户
	@RequestMapping(value="searchcustomer" ,method = RequestMethod.GET)
	public ModelAndView searchcustomer(Model model,
			@RequestParam("status") int status, 
			HttpServletRequest request)
	{
		String beginIndex = request.getParameter("pager.offset");
		int pagerNum = Integer.parseInt(request.getServletContext()
				.getInitParameter("pagerNum"));
		int count = customerService.getSearchNum(status);
		List<Customer> customer = customerService.searchcustomer(beginIndex, pagerNum,status);
			model.addAttribute("customers", customer);
			model.addAttribute("count", count);
			model.addAttribute("status", status);
			model.addAttribute("searchcustomers", "searchcustomers");
			return new ModelAndView("user/customerManage");
	}
	
	
	
	
	//查看客户
	@RequestMapping("viewCustomer")
	public ModelAndView viewCustomer(Model model,int id)
	{
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		model.addAttribute("type", 1);
		return new ModelAndView("user/addCustomer");
	}
	
	//编辑客户
	@RequestMapping("editCustomer")
	public ModelAndView editCustomer(Model model,int id)
	{
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		model.addAttribute("type","2"); //编辑
		model.addAttribute("id",id); 
		
		return new ModelAndView("user/addCustomer");
		
	}
	
	@RequestMapping("editCustomerResult")
	public String editCustomerResult(Model model,Customer customer)
	{	
		int count=customerService.updateCustomer(customer);
		int count1=jurisdictionService.updateSysUserByCustomer(customer);
		
		return "redirect:customermanage";
//		return response.sendredirect("http://www.foo.com/path/error.html");
//		return doResourceManage(model);
	
	}
}
