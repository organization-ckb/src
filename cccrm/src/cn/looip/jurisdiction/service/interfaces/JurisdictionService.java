package cn.looip.jurisdiction.service.interfaces;

import cn.looip.customer.repository.domain.Customer;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.resource.repository.domain.Programmer;


/**
 * 权限管理功能模块业务接口定义
 * @author xuzhidong
 */
public interface JurisdictionService {
	//================系统功能=======================
	
	public boolean checkLogin(String loginName,String loginPwd);
	
	//获取登录用户
	public SysUser getLoginUser(String loginName,String loginPwd);
	
	//增加用户
	public int insertSysUser(SysUser user);
	
	//更新用户
	public int updateSysUserByProgrammer(Programmer prm);
		
//	//增加程序员
//	public int insertProgrammer1(Programmer programmer);
//	
//	public void insertProgrammer2(Manager manager);
//
//
//	//查询程序员
//	
//	public List<Programmer> getprogrammer();
//	
//	//按条件查询程序员
//	public List<Programmer> searchprogrammer();
//
//	//beginIndex第几页开始 			pagerNum
//	
//	public List<Programmer> getProgrammer(String beginIndex, int pagerNum);
//	
//	//总条数
//	
//	public int getNum();

	//更新客户
	public int updateSysUserByCustomer(Customer customer);

	public int insertSysUserByCustomer(SysUser sysUser);
	
}
