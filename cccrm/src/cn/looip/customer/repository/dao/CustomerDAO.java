package cn.looip.customer.repository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.looip.customer.repository.domain.Customer;
import cn.looip.jurisdiction.repository.domain.SysUser;


@Repository
public interface CustomerDAO {

	//新增客户
	public int insertCustomer(Customer customer);
	
	//public int insertCustomerBySysUser(SysUser sysUser);
	
	//查询所有客户
	public List<Customer> getCustomers(@Param("pagerIndex") int pagerIndex, @Param("pagerNum")int pagerNum);
	
	//按条件查询客户
	public List<Customer> searchcustomer(@Param("pagerIndex") int pagerIndex, @Param("pagerNum")int pagerNum,@Param("status") int status);
	
	//根据ID查询客户
	public Customer getCustomer(@Param("id") int id);
	
	//更新客户
	public int updateCustomer(Customer customer);
	
	//当前客户的总条数
	public int getNum();
	
	//查询的客户总条数
	public int getSearchNum(@Param("status") int status);
	
}
