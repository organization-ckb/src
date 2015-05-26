package com.looip.crm.bean;

/**
 * 机器人信息 bean
 * 
 * @author lixingtao
 * 
 */
public class RobotInfoBean {
	public String robotProjectName;
	public String robotProjectPositon;

	public RobotInfoBean(String robotProjectName, String robotProjectPositon) {
		super();
		this.robotProjectName = robotProjectName;
		this.robotProjectPositon = robotProjectPositon;
	}

	@Override
	public String toString() {
		return "RobotInfoBean [robotProjectName=" + robotProjectName
				+ ", robotProjectPositon=" + robotProjectPositon + "]";
	}

}
