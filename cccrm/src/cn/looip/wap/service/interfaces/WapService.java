package cn.looip.wap.service.interfaces;

import java.util.List;

import cn.looip.wap.repository.domain.Project;
import cn.looip.wap.repository.domain.SysUser;

public interface WapService {
	public Integer getUserName(String username);
	public boolean getLogin(SysUser user);
	public List<Project>  getProjects(int id);
}
