package cn.looip.project.repository.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目类
 * @author gaoxiang
 *
 */
public class Project implements Serializable {

	private static final long serialVersionUID = -4678334155233644220L;
	private int id;              // 项目编号
	private String proName;      // 项目名称
	private String bargainNo;    // 合同编号
	private String proImage;     // 合同图文
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date starttime;      // 开始时间
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endtime;        // 结束时间
	private short status;        //状态（短整型）
    private Customer customer;   //关联客户
	/**
	 * 程序员与项目多对多关联
	 */
	private List<Programmer> programmers;
	/* 
	 * 构造器
	 */
	
	public Project() {
		super();
	}
	
	/*
	 * get/set方法
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public String getProImage() {
		return proImage;
	}
	public void setProImage(String proImage) {
		this.proImage = proImage;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public short getStatus() {
		return status;
	}


	public void setStatus(short status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Programmer> getProgrammers() {
		return programmers;
	}

	public void setProgrammers(List<Programmer> programmers) {
		this.programmers = programmers;
	}
	
	

}
