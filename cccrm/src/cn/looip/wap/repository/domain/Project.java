package cn.looip.wap.repository.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目类
 * @author gaoxiang
 *
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 6670823971264506051L;
	private int id;              // 项目编号
	private String proName;      // 项目名称
	private String bargainNo;    // 合同编号
	private String proImage;     // 合同图文
	private Date starttime;      // 开始时间
	private Date endtime;        // 结束时间
	private short status;        //状态（短整型）


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

}
