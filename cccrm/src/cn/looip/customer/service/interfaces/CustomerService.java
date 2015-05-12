package cn.looip.customer.service.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.looip.customer.repository.domain.Customer;
import cn.looip.jurisdiction.repository.domain.SysUser;

public interface CustomerService {

	
	//增加客户
	public int insertCustomer(Customer customer);
	
	//public int insertCustomerBySysUser(SysUser sysUser);
	
	//查询所有客户
	public List<Customer> getCustomers(String beginIndex, int pagerNum);
	
	//按条件查询客户
	public List<Customer> searchcustomer(String beginIndex, int pagerNum,int status);
	
	//根据ID查询客户
	public Customer getCustomer(int id);
	
	//更新客户
	public int updateCustomer(Customer customer);

	//所有的客户的总条数
	public int getNum();
	
	//查询的客户总条数
	public int getSearchNum(int status);
}
