package cn.looip.wap.repository.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 程序员和项目关联关系
 * @author gaoxiang
 *
 */
public class ProgrammerProject  implements Serializable{
	private static final long serialVersionUID = -3905342877239644954L;
	private int id;
	private int programmer_id;//程序员ID
	private String ProgrammerName;//程序员姓名
	private short programmerLevel;//程序员等级
	private String department_name;//程序员部门
	private short pro_status;//项目状态
	
	private int project_id;//项目ID
	private Date beginTime;//开始时间
	private Date endTime;//结束时间
	private int Day;//天数
	private int TotalDays; 
	private int status;//续约状态（是否有续约 0/1）
	private int extension_time;//续约天数
	private int agree;//续约是否同意
	private Date timeRecord;//记录开始续约的时间
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getExtension_time() {
		return extension_time;
	}

	public void setExtension_time(int extension_time) {
		this.extension_time = extension_time;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public Date getTimeRecord() {
		return timeRecord;
	}

	public void setTimeRecord(Date timeRecord) {
		this.timeRecord = timeRecord;
	}

	public int getProgrammer_id() {
		return programmer_id;
	}

	public void setProgrammer_id(int programmer_id) {
		this.programmer_id = programmer_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProgrammerName() {
		return ProgrammerName;
	}

	public void setProgrammerName(String programmerName) {
		ProgrammerName = programmerName;
	}

	public short getProgrammerLevel() {
		return programmerLevel;
	}

	public void setProgrammerLevel(short programmerLevel) {
		this.programmerLevel = programmerLevel;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public int getDay() {
		return Day;
	}

	public void setDay(int day) {
		Day = day;
	}

	public int getTotalDays() {
		return TotalDays;
	}

	public void setTotalDays(int totalDays) {
		TotalDays = totalDays;
	}

	public short getPro_status() {
		return pro_status;
	}

	public void setPro_status(short pro_status) {
		this.pro_status = pro_status;
	}

	


}
