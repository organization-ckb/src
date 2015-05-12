package cn.looip.resource.repository.domain;

import cn.looip.jurisdiction.repository.domain.SysUser;

public class Programmer extends SysUser{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String programmerName;

    private Integer department;
    
//    private Integer searchdepartment;
    
    private Short programmerSex;

    private String programmerQq;

    private Short programmerLevel;

    private Short programmerStatus;

//    private String loginName;
//    
//    private String userMobile;
    
    
//    public String getLoginName() {
//		return loginName;
//	}
//
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//
//	public String getUserMobile() {
//		return userMobile;
//	}
//
//	public void setUserMobile(String userMobile) {
//		this.userMobile = userMobile;
//	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgrammerName() {
        return programmerName;
    }

    public void setProgrammerName(String programmerName) {
        this.programmerName = programmerName;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Short getProgrammerSex() {
        return programmerSex;
    }

    public void setProgrammerSex(Short programmerSex) {
        this.programmerSex = programmerSex;
    }

    public String getProgrammerQq() {
        return programmerQq;
    }

    public void setProgrammerQq(String programmerQq) {
        this.programmerQq = programmerQq;
    }

    public Short getProgrammerLevel() {
        return programmerLevel;
    }

    public void setProgrammerLevel(Short programmerLevel) {
        this.programmerLevel = programmerLevel;
    }

    public Short getProgrammerStatus() {
        return programmerStatus;
    }

    public void setProgrammerStatus(Short programmerStatus) {
        this.programmerStatus = programmerStatus;
    }

//	public Integer getSearchdepartment() {
//		return searchdepartment;
//	}
//
//	public void setSearchdepartment(Integer searchdepartment) {
//		this.searchdepartment = searchdepartment;
//	}

    
    
}