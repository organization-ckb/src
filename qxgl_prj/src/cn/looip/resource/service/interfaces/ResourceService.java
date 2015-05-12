package cn.looip.resource.service.interfaces;

import java.util.List;

import cn.looip.resource.repository.domain.Programmer;

/**
 * 资源业务接口定义
 * @author hujian
 */
public interface ResourceService {

	//增加程序员
	public int insertProgrammer(Programmer programmer);
	
//	public void insertProgrammer2(Manager manager);
	
	public int updateProgrammer(Programmer programmer);

	//查询程序员
	public Programmer getProgrammer(int id);
		
	//查询程序员list
	public List<Programmer> getprogrammers(String pagerIndex,int pagerNum);
	
	public int count();
	
	//按条件查询程序员
	public List<Programmer> searchprogrammer(int department,int status);
	
	//beginIndex第几页开始 			pagerNum
	
	public List<Programmer> getProgrammers(String beginIndex, int pagerNum);
	
	//总条数
	
	public int getNum();
}
