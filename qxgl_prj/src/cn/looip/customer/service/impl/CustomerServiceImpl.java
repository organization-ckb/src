package cn.looip.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.looip.core.utils.GeneralTools;
import cn.looip.customer.repository.dao.CustomerDAO;
import cn.looip.customer.repository.domain.Customer;
import cn.looip.customer.service.interfaces.CustomerService;

import cn.looip.jurisdiction.repository.domain.SysUser;

@Service
public class CustomerServiceImpl implements CustomerService {

	//持久化接口
	@Autowired
	private CustomerDAO customerDAO;
	
	//工具类
	@Autowired
	private GeneralTools iTools ;
	
	@Override
	public int insertCustomer(Customer customer) {
		return customerDAO.insertCustomer(customer);
		
	}

	/*@Override
	public int insertCustomerBySysUser(SysUser sysUser) {
		 return customerDAO.insertCustomerBySysUser(sysUser);

	}
*/
	//查询所有客户（分页）
	@Override
	public List<Customer> getCustomers(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return customerDAO.getCustomers(index, pagerNum);
	}

	@Override
	public List<Customer> searchcustomer(String beginIndex, int pagerNum,int status) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return customerDAO.searchcustomer(index, pagerNum,status);
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomer(id);
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDAO.updateCustomer(customer);
	}
	
	//所有的客户的总条数
	@Override
	public int getNum() {
		return customerDAO.getNum();
	}
	
	//查询的客户总条数
	public int getSearchNum(int status){
		return customerDAO.getSearchNum(status);
	}
}
