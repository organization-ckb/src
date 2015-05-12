package cn.looip.customer.repository.domain;

import java.io.Serializable;

import cn.looip.jurisdiction.repository.domain.SysUser;

public class Customer extends SysUser{
	
	
    private Integer id;
    
    //公司名字
    private String customerName;
    
    //联系人名称
    private String customerMan;
    
    //客户性别
    private Short customerSex;

    //客户QQ
    private String customerQq;

    //客户状态
    private Short customerStatus;

    //客户邮箱
    private String userEmail;
    
    //客户手机
    private String userMobile;
    
    //客户密码
    private String loginPwd;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMan() {
        return customerMan;
    }

    public void setCustomerMan(String customerMan) {
        this.customerMan = customerMan;
    }

    public Short getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(Short customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerQq() {
        return customerQq;
    }

    public void setCustomerQq(String customerQq) {
        this.customerQq = customerQq;
    }

    public Short getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Short customerStatus) {
        this.customerStatus = customerStatus;
    }

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
    
    
}