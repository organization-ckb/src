package cn.looip.project.repository.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.looip.project.repository.domain.Customer;
import cn.looip.project.repository.domain.Log;
import cn.looip.project.repository.domain.Programmer;
import cn.looip.project.repository.domain.ProgrammerProject;
import cn.looip.project.repository.domain.Project;

@Repository
public interface ProjectDao {
	/**
	 * 项目最后ID
	 * 
	 * @return
	 */
	public Map<String,Object> getFinalid();

	/**
	 * 添加项目
	 * 
	 * @param project
	 */
	public void saveProject(Project project);

	/**
	 * 
	 * @return 客户集合
	 */
	public List<Customer> getCustomer();

	/**
	 * 分页查询项目
	 * @param pagerIndex
	 * @param pagerNum
	 * @return
	 */
	public List<Project> getProjects(@Param("pagerIndex") int pagerIndex,
			@Param("pagerNum") int pagerNum);

	/**
	 * 修改项目时间结束的状态
	 */
	public void updateStatus();

	/**
	 * 查询历史项目
	 * 
	 * @param pagerIndex
	 * @param pagerNum
	 * @return
	 */
	public List<Project> getProjectes(@Param("pagerIndex") int pagerIndex,
			@Param("pagerNum") int pagerNum);

	/**
	 * 模糊查询项目
	 * 
	 * @param beginIndex
	 * @param pagerNum
	 * @param proName
	 * @return
	 */
	public List<Project> getSeach(@Param("pagerIndex") int pagerIndex,
			@Param("pagerNum") int pagerNum, @Param("proName") String proName);

	/**
	 * 当前项目总条数
	 */
	public int getNum();

	/**
	 * 历史项目总条数
	 * 
	 * @return
	 */
	public int getNums();

	/**
	 * 条件查询总数
	 * 
	 * @return
	 */
	public int getNumes(@Param("proName") String proName);

	/**
	 * 修改项目
	 * 
	 * @param project
	 */
	public void updateProject(Project project);

	/**
	 * 查询项目
	 * 
	 * @param 主键id
	 * @return
	 */
	public Project getProject(int id);

	/**
	 * 删除项目
	 * 
	 * @param 根据id主键
	 */
	public void delectProject(int id);

	/**
	 * 查询闲置程序员
	 * 
	 * @return
	 */
	public List<Programmer> getProgrammer();

	/**
	 * 项目分配程序员
	 * 
	 * @param Pproject
	 * @param ids
	 */
	public void saveProgrammers(ProgrammerProject Pproject);

	/**
	 * 添加进项目后修改状态
	 * 
	 * @param id
	 */
	public void updateState(int id, int State);

	/**
	 * 项目成员
	 * 
	 * @param beginIndex
	 * @param pagerNum
	 * @param id
	 * @return
	 */
	public List<ProgrammerProject> getPproject(
			@Param("pagerIndex") int pagerIndex,
			@Param("pagerNum") int pagerNum, @Param("id") int id);

	/**
	 * 项目成员数
	 * 
	 * @param id
	 * @return
	 */
	public int getNumber(int id);

	/**
	 * 查询程序员的项目报告
	 * 
	 * @param id项目ID
	 * @param pid程序员ID
	 * @return
	 */
	public Log getLogs(int id, int pid);

	/**
	 * 申请续约
	 * 
	 * @param Pproject
	 */
	public void updateExtension(ProgrammerProject Pproject);

	/**
	 * 查看续约申请记录
	 * 
	 * @return
	 */
	public List<ProgrammerProject> getRecord(int pagerIndex,int pagerNum);
	
	/**
	 * 申请续约的记录条数
	 *
	 */
	public int getRecordCount();
	
	/**
	 * 同意续约：结束时间到期
	 * @param id
	 * @param begindate
	 * @param enddate
	 */
	public void updateContract(int id, Date begindate,Date enddate);
	/**
	 * 同意续约：在结束时间有余
	 * @param id
	 * @param extensionTime
	 */
	public void updateContracts(int id,int extensionTime);

	/**
	 * 踢出程序员
	 * 
	 * @param id
	 */
	public void deletePprogrammer(int id);

	/*------------------------客户模块---------------------------------------*/

	/**
	 * 客户项目总数
	 * 
	 * @param id
	 * @return
	 */
	public int getNumbers(int id);

	/**
	 * 客户项目
	 * 
	 * @param pagerIndex
	 * @param pagerNum
	 * @param id
	 * @return
	 */
	public List<Project> getCProject(int pagerIndex, int pagerNum, int id);

	/**
	 * 程序员项目数
	 * 
	 * @param id
	 * @return
	 */
	public int getNumberes(int id);

	/**
	 * 程序员的项目
	 * 
	 * @param pagerIndex
	 * @param pagerNum
	 * @param id
	 * @return
	 */
	public List<ProgrammerProject> getPprojects(int pagerIndex, int pagerNum,
			int id);
	
	public void saveFiles(Map<String, Object> file);
	
	/**
	 * 查询图片
	 * @param id
	 * @return
	 */
	public String imgNumber(int id);
	/**
	 * 查询姓名
	 * @param id类型外键
	 * @return
	 */
	public String getcustomerName(int id);
	public String getprogrammerName(int id);
	/**
	 * 修改项目状态
	 * @param State
	 */
	public void updateProjectState(int id,int State);
	/**
	 * 查询程序员的项目
	 * @param id
	 * @return
	 */
	public String getPprogrammer(int id);
	/**
	 * 判断项目中有没有程序员
	 * @param id
	 * @return
	 */
	public String getPprogramm(int id);
	/**
	 * 修改结束的程序员状态
	 */
	public void updatePprogrammerState(int id);
	/**
	 * 修改结束的项目程序员状态
	 */
	 public void updateProjectStates();
	/**
	 * 查询结束时间的程序员
	 * @return
	 */
	 public List<ProgrammerProject> endTimeprogrammer();
	 /**
	  * 根据结束时间的程序员查询最后一次项目
	  * @param id
	  * @return
	  */
	 public Integer getProgrammerID(int id);
	 /**
	  * 根据项目ID查询程序员项目表ID
	  * @param id
	  * @return
	  */
	 public int getPprojectID(int id);
}
