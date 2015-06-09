package cn.looip.wap.service.interfaces;

import java.util.List;

import cn.looip.wap.repository.domain.ProgrammerProject;
import cn.looip.wap.repository.domain.Project;
import cn.looip.wap.repository.domain.SysUser;

public interface WapService {
	public Integer getUserName(String username);
	public boolean getLogin(SysUser user);
	public List<Project>  getProjects(int id);
	
	public List<ProgrammerProject> getProjectInfo(int id);
	
	
	//验证邮箱接口
	public boolean getUserEmail(SysUser user);
	
	//查看项目合同照片
	public String imgNumber(int id);
	
	public void updatePasswordByEmail(int loginPwd,String userEmail);
}
