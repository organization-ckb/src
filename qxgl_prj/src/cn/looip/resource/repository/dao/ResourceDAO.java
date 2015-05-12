package cn.looip.resource.repository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.looip.resource.repository.domain.Programmer;

/**
 * 资源功能模块持久化接口定义
 * @author hujian
 */
@Repository
public interface ResourceDAO {
	
	//新增程序员
	public int insertProgrammer(Programmer programmer);
	
//	public int insertProgrammer2(Manager manager);
	
	//程序员集合
	public List<Programmer> getprogrammers(int pagerIndex,int pagerNum);
	
	//按条件查询程序员
	public List<Programmer> searchprogrammer(@Param("department") int department,@Param("status") int status);
	
	//分页查询程序员
	public List<Programmer> getPageProgrammer(@Param("pagerIndex") int pagerIndex, @Param("pagerNum")int pagerNum);

	//程序员总条数
	public int getNum();

	//根据Id查询程序员
	public Programmer getProgrammer(@Param("id") int id);

	//更新程序员
	public int updateProgrammer(Programmer programmer);
	/*
	 *程序员集合总条数
	 */
	public int count();
}

