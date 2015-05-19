package cn.looip.jurisdiction.service.interfaces;

import cn.looip.customer.repository.domain.Customer;
import cn.looip.jurisdiction.repository.domain.SysUser;
import cn.looip.resource.repository.domain.Programmer;



public interface JurisdictionService {
	
	
	public boolean checkLogin(String loginName,String loginPwd);
	

	public SysUser getLoginUser(String loginName,String loginPwd);

	public int insertSysUser(SysUser user);

	public int updateSysUserByProgrammer(Programmer prm);
	
	public int updateSysUserByCustomer(Customer customer);

	public int insertSysUserByCustomer(SysUser sysUser);
	
}
