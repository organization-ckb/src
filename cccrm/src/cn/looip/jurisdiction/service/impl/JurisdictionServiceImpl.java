package cn.looip.jurisdiction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.looip.core.utils.GeneralTools;
import cn.looip.customer.repository.domain.Customer;
import cn.looip.jurisdiction.repository.dao.JurisdictionDAO;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.jurisdiction.service.interfaces.JurisdictionService;
import cn.looip.resource.repository.domain.Programmer;



@Service
public class JurisdictionServiceImpl implements JurisdictionService {
	

	@Autowired
	private JurisdictionDAO jurisdictionDAO;
	

	@Autowired
	private GeneralTools iTools ;
	


	public boolean checkLogin(String loginName, String loginPwd) {
		boolean isSuccess = false;
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
		return jurisdictionDAO.updateSysUserByProgrammer(user);
	}
	
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


