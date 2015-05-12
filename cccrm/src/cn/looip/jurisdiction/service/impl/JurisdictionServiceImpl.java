package cn.looip.jurisdiction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.looip.core.utils.GeneralTools;
import cn.looip.customer.repository.domain.Customer;
import cn.looip.jurisdiction.repository.dao.JurisdictionDAO;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.jurisdiction.service.interfaces.JurisdictionService;
import cn.looip.resource.repository.domain.Programmer;


/**
 * 权限管理功能模块业务接口实现
 * @author xuzhidong
 */
@Service
public class JurisdictionServiceImpl implements JurisdictionService {
	
	//持久化接口
	@Autowired
	private JurisdictionDAO jurisdictionDAO;
	
	//工具类
	@Autowired
	private GeneralTools iTools ;
	
	//================测试=======================
	
	//用户登陆
	public boolean checkLogin(String loginName, String loginPwd) {
		boolean isSuccess = false;
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("loginName", loginName);
//		map.put("loginPwd", loginPwd);
		SysUser sysUser = jurisdictionDAO.selectUserByLogin(loginName,loginPwd);
		if(sysUser!=null)
		{
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public SysUser getLoginUser(String loginName,String loginPwd){
		SysUser sysUser = jurisdictionDAO.selectUserByLogin(loginName,loginPwd);
		return sysUser;
	}

	@Override
	public int insertSysUser(SysUser user)
	{
	
		return jurisdictionDAO.insertSysUser(user);
	}

	@Override
	public int updateSysUserByProgrammer(Programmer programmer)
	{
		SysUser user=(SysUser)programmer;
		
		user.setUserId(programmer.getId());
		user.setUserType((short)1);
		user.setUserEmail(programmer.getLoginName());
//		user.setUserRoleId(userRoleId);
		return jurisdictionDAO.updateSysUserByProgrammer(user);
	}
	
//	/*新增程序员信息至程序员表*/
//	@Override
//	public int insertProgrammer1(Programmer programmer) {
//		
//		return jurisdictionDAO.insertProgrammer1(programmer);
//		
//		/*	boolean isSuccess = false;
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("department", department);
//		map.put("programmerName", programmerName);
//		map.put("programmerSex", programmerSex);
//		map.put("programmerQq", programmerQq);
//		map.put("programmerLevel", programmerLevel);
//		map.put("programmerStatus",programmerStatus);
//		int programmer1 = jurisdictionDAO.insertProgrammer1(map);
//		return true;*/
//	
//	}
//		
//	/*增加程序员信息到管理员表sys_users*/
//	@Override
//	public void insertProgrammer2(Manager manager){
//		
//		/*manager.setLoginName(loginName);
//		manager.setLoginPwd(loginPwd);
//		manager.setUserMobile(userMobile);*/
//		jurisdictionDAO.insertProgrammer2(manager);
//		
//	}
//	/*@Override
//	public boolean insertProgrammer2(String loginName, String loginPwd,
//			String userMobile,String programmerName,String programmerQq) {
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("loginName", loginName);
//		map.put("loginPwd", loginPwd);
//		map.put("userMobile", userMobile);
//		map.put("programmerName", programmerName);
//		map.put("programmerQq", programmerQq);
//		int programmer2 = jurisdictionDAO.insertProgrammer2(map);
//		return true;
//		
//	}*/
//
//	
//
//	@Override
//	public List<Programmer> getProgrammer(String beginIndex, int pagerNum) {
//		int pagerIndex = 0;
//		if (beginIndex != null) {
//			pagerIndex = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
//		}
//		return jurisdictionDAO.getPageProgrammer(pagerIndex, pagerNum);
//	}
//
//	@Override
//	public int getNum() {
//		// TODO Auto-generated method stub
//		return jurisdictionDAO.getNum();
//	}
////查询程序员
//	@Override
//	public List<Programmer> getprogrammer() {
//		// TODO Auto-generated method stub
//		return jurisdictionDAO.getprogrammer();
//	}
//
//	//按条件查询程序员
//	@Override
//	public List<Programmer> searchprogrammer() {
//		// TODO Auto-generated method stub
//		return jurisdictionDAO.searchprogrammer();
//	}


	@Override
	public int updateSysUserByCustomer(Customer customer) {
		
		SysUser sysUser = (SysUser)customer;
		sysUser.setUserId(customer.getId());
		sysUser.setUserType((short)2);
		sysUser.setUserEmail(customer.getLoginName());
		return jurisdictionDAO.updateSysUserByCustomer(sysUser);
	}

	@Override
	public int insertSysUserByCustomer(SysUser sysUser) {
		// TODO Auto-generated method stub
		return jurisdictionDAO.insertSysUserByCustomer(sysUser);
	}
	
	}


