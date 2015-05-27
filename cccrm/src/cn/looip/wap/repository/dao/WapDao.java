package cn.looip.wap.repository.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import cn.looip.wap.repository.domain.ProgrammerProject;
import cn.looip.wap.repository.domain.Project;



@Repository
public interface WapDao {
	
	public Integer getUserName(String username);
	public Integer getLogin(String username,String password);
	public List<Project> getProjects(int id);
	public List<ProgrammerProject> getProjectInfo(int id);
}
