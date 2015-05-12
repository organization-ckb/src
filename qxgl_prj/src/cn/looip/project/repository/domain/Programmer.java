package cn.looip.project.repository.domain;

import java.io.Serializable;
import java.util.List;
/**
 * 程序员
 * @author gaoxiang
 *
 */
public class Programmer implements Serializable{

	private static final long serialVersionUID = 1395479656774261733L;
	private int id;
	private String programmerName;//员工姓名
	private Department department;//部门
	private short sex;//性别
	private String qq;//QQ账号
	private short level;//等级
	private short programmerStatus;//状态
	private String position;//职位描述
	/**
	 * 程序员与项目多对多关联
	 */
	private List<Project> Projects;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProgrammerName() {
		return programmerName;
	}

	public void setProgrammerName(String programmerName) {
		this.programmerName = programmerName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public short getProgrammerStatus() {
		return programmerStatus;
	}

	public void setProgrammerStatus(short programmerStatus) {
		this.programmerStatus = programmerStatus;
	}

	public List<Project> getProjects() {
		return Projects;
	}

	public void setProjects(List<Project> projects) {
		Projects = projects;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
    
	
    
}
