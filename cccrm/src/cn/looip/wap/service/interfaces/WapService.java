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
}
