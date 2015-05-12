package cn.looip.project.service.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.looip.project.repository.domain.Customer;
import cn.looip.project.repository.domain.Log;
import cn.looip.project.repository.domain.Programmer;
import cn.looip.project.repository.domain.ProgrammerProject;
import cn.looip.project.repository.domain.Project;

/**
 * 项目service接口
 * @author gaoxiang
 *
 */
public interface ProjectService {
	/**
	 * 获取最后一个项目的ID
	 * @return
	 */
	public int   getFinalid();
	
	
   /**
    * 添加项目
    */
	public void saveProject(Project project);
	
	/**
	 * 修改项目时间结束的状态
	 */
	public void updateStatus();
	
	
	/**
	 * 查询当前项目
	 * @param beginIndex第几页开始
	 * @param pagerNum
	 * @return
	 */
	public List<Project> getProjects(String beginIndex, int pagerNum);
	/**
	 * 查询历史项目
	 * @param beginIndex
	 * @param pagerNum
	 * @return
	 */
	public List<Project> getProjectes(String beginIndex, int pagerNum);
	
	/**
	 * 模糊查询项目
	 * @param beginIndex
	 * @param pagerNum
	 * @param proName
	 * @return
	 */
	public List<Project> getSeach(String beginIndex, int pagerNum,String proName);
	/**
	 * 当前项目总条数
	 * @return
	 */
	public int getNum();
	/**历史项目
	 * 总条数
	 * @return
	 */
	public int getNums();
	/**
	 * 模糊查询的总条数
	 */
	public int getNumes(String proName);
	/**
	 * 修改项目
	 * @param project
	 */
	public void updateProject(Project project);
	
	/**
	 * 查询要修改的项目信息
	 * @param project
	 */
	public Project getProject(int id); 
     /**
      * 删除项目
      * @param id主键
      */
	public void delectProject(int id);
	
	/**
	 * 待分配程序员
	 * @return
	 */
	public List<Programmer> programmer();
	
	/**
	 * 项目分配程序员
	 * @param Pproject程序员信息
	 */
	public void saveProgrammers(ProgrammerProject Pproject);
	/**
	 * 踢出程序员
	 * @param id
	 */
	public void deletePprogrammer(int id);
	
	/**
	 * 修改程序员状态为：State  1忙碌、0闲置
	 * @param id
	 */
	public void updateState(int id,int State);
	
	/**
	 * 项目成员
	 * @param beginIndex
	 * @param pagerNum
	 * @param id项目ID
	 * @return
	 */
	public List<ProgrammerProject> getPproject(String beginIndex, int pagerNum,int id);
	/**
	 * 项目成员数
	 * @param id
	 * @return
	 */
    public int getNumber(int id);
    
    /**
     * 查询报告
     * @param id项目ID
     * @param pid程序员ID
     * @return
     */
    public Log getLogs(int id,int pid);
    
    /**
     * 查看续约记录
     * @return
     */
    public List<ProgrammerProject> getRecord(String beginIndex,int pagerNum);
   
    /**
     * 申请的记录条数
     * @return
     */
    public int getRecordCount();
    
    
/*------------------------客户模块---------------------------------------*/
    /**
	 * 所有客户
	 * @return
	 */
	public List<Customer> getCustomer();
    
    
    /**
     * 客户项目数
     * @param id
     * @return
     */
    public int getNumbers(int id);
    
    /**
     * 客户项目
     * @param beginIndex 起始页
     * @param pagerNum   结束页
     * @param id 客户ID
     * @return
     */
    public List<Project> getCProject(String beginIndex, int pagerNum,int id);
    
    
    /**
     * 申请续约
     * @param Pproject
     */
    public void updateExtension(ProgrammerProject Pproject);
    
    /**
     * 同意续约
     * @param id
     * @param extensionTime
     * @param RemainingTime
     */
    public void updateContract(int id,int extensionTime,int RemainingTime,Date timeRecord);
    
    /*------------------------程序员模块---------------------------------------*/  
     /**
      * 程序员项目
      * @param id
      * @return
      */
    public int getNumberes(int id);
    /**
     * 程序员的项目
     * @param beginIndex
     * @param pagerNum
     * @param id  程序员ID
     * @return
     */
    public List<ProgrammerProject> getPprojects(String beginIndex, int pagerNum,int id);
    /**
     * 上传合同
     * @param id
     */
    public void saveFiles(int id,String fileName);
    
    
    /**
     * 查询图片
     * @param id
     * @return
     */
    public String imgNumber(int id);
    /**
     * 查询客户的姓名
     * @param id
     * @return
     */
    public String getcustomerName(int id);
    /**
     * 查询程序员姓名
     * @param id
     * @return
     */
    public String getprogrammerName(int id);
    
    public void updateProjectState(int id,int State);
   
    /**
     * 查询程序员单个项目
     */
    public String getPprogrammer(int id);
    /**
     * 判断项目中有没有程序员
     * @param id
     * @return
     */
    public String getPprogramm(int id);
    
    
    public void updatePprogrammerState(int id);
    /**
     * 查询结束时间的程序员
     * @return
     */
    public List<ProgrammerProject> endTimeprogrammer();
    /**
     * 根据结束时间的程序员查询最后一次项目
     * @return
     */
    public Integer getProgrammerID(int id);
    
    /**
     * 修改项目状态
     */
    public void updateProjectStates();
    
    public int getPprojectID(int id);
    
    
}
