package cn.looip.wap.repository.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.looip.wap.repository.domain.ProgrammerProject;
import cn.looip.wap.repository.domain.Project;



@Repository
public interface WapDao {
	
	public Integer getUserName(String username);
	public Integer getLogin(String username,String password);
	public List<Project> getProjects(int id);
	public List<ProgrammerProject> getProjectInfo(int id);
	
	//验证邮箱接口
	public Integer getUserEmail(String userEmail);
	
	public void updatePasswordByEmail(Map<String, Object> map);
	//查看项目合同照片
	public String imgNumber(int id);
	
		
	//续约状态
	
}
