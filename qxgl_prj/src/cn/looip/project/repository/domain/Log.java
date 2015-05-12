package cn.looip.project.repository.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 记录
 * @author gaoxiang
 *
 */
public class Log implements Serializable {

	private static final long serialVersionUID = -7307090408179679806L;
	private int id;
	private Date logTime;      // 系统时间
	// private 程序员类 程序员             
	private Project project;   // 关联项目，对应项目的日志
	private String logContent; // 文本日志
	private String logVideo;   // 视频日志
	
	private int cnum;//程序员文本数
	private int vnum;//程序员视频数

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogVideo() {
		return logVideo;
	}

	public void setLogVideo(String logVideo) {
		this.logVideo = logVideo;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getVnum() {
		return vnum;
	}

	public void setVnum(int vnum) {
		this.vnum = vnum;
	}

}
