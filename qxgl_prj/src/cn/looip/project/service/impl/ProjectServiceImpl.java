package cn.looip.project.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.looip.core.utils.GeneralTools;
import cn.looip.project.repository.dao.ProjectDao;
import cn.looip.project.repository.domain.Customer;
import cn.looip.project.repository.domain.Log;
import cn.looip.project.repository.domain.Programmer;
import cn.looip.project.repository.domain.ProgrammerProject;
import cn.looip.project.repository.domain.Project;
import cn.looip.project.service.interfaces.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	// 持久化接口
	@Autowired
	private ProjectDao projectdao;
	// 工具类
	@Autowired
	private GeneralTools iTools;

	@Override
	public void saveProject(Project project) {
		projectdao.saveProject(project);
	}

	@Override
	public List<Customer> getCustomer() {
		return projectdao.getCustomer();
	}

	/**
	 * 查询当前项目
	 */
	@Override
	public List<Project> getProjects(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getProjects(index, pagerNum);
	}

	/**
	 * 查询历史项目
	 */
	@Override
	public List<Project> getProjectes(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getProjectes(index, pagerNum);
	}

	/**
	 * 当前项目总条数
	 */
	@Override
	public int getNum() {
		return projectdao.getNum();
	}

	/**
	 * 历史项目总条数
	 */
	@Override
	public int getNums() {

		return projectdao.getNums();
	}

	@Override
	public void updateProject(Project project) {
		projectdao.updateProject(project);
	}

	@Override
	public Project getProject(int id) {

		return projectdao.getProject(id);
	}

	/**
	 * 删除某项目
	 */
	@Override
	public void delectProject(int id) {
		projectdao.delectProject(id);
	}

	@Override
	public List<Project> getSeach(String beginIndex, int pagerNum,
			String proName) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getSeach(index, pagerNum, proName);
	}

	@Override
	public int getNumes(String proName) {

		return projectdao.getNumes(proName);
	}

	/**
	 * 查询闲置程序员
	 */
	@Override
	public List<Programmer> programmer() {

		return projectdao.getProgrammer();
	}

	@Override
	public void saveProgrammers(ProgrammerProject Pproject) {
		projectdao.saveProgrammers(Pproject);
	}
    
	@Override
	public void updateState(int id, int State) {
		/*
		 * 修改状态
		 */
		if (State == 0) {
			State = 1;
		} else {
			State = 0;
		}
		projectdao.updateState(id, State);
	}

	@Override
	public List<ProgrammerProject> getPproject(String beginIndex, int pagerNum,
			int id) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getPproject(index, pagerNum, id);
	}

	@Override
	public int getNumber(int id) {
		
		return projectdao.getNumber(id);
	}

	@Override
	public Log getLogs(int id, int pid) {
		return projectdao.getLogs(id, pid);
	}

	@Override
	public void updateExtension(ProgrammerProject Pproject) {

		if (Pproject.getStatus() == 0) {
			Pproject.setStatus(1);
		} else {
			Pproject.setStatus(0);
		}
		projectdao.updateExtension(Pproject);
	}

	@Override
	public void updateStatus() {
		projectdao.updateStatus();
	}

	@Override
	public void deletePprogrammer(int id) {

		projectdao.deletePprogrammer(id);
	}

	@Override
	public int getNumbers(int id) {
		return projectdao.getNumbers(id);
	}

	@Override
	public List<Project> getCProject(String beginIndex, int pagerNum, int id) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getCProject(index, pagerNum, id);
	}

	@Override
	public int getNumberes(int id) {

		return projectdao.getNumberes(id);
	}

	@Override
	public List<ProgrammerProject> getPprojects(String beginIndex,
			int pagerNum, int id) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getPprojects(index, pagerNum, id);
	}

	/**
	 * 返回ID+1
	 */
	@Override
	public int getFinalid() {
		if(projectdao.getFinalid()==null){
			return 1;
		}
		else{
			int id= (int) projectdao.getFinalid().get("id");
			return id+1;
		}
		
	}

	@Override
	public List<ProgrammerProject> getRecord(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return projectdao.getRecord(index, pagerNum);
	}

	@Override
	public int getRecordCount() {

		return projectdao.getRecordCount();
	}

	@Override
	public void updateContract(int id, int extensionTime, int RemainingTime,
			java.util.Date timeRecord) {

		/*
		 * 当用户工作结束，续约天数目前是，
		 * 已当日同意的系统时间为准相加；
		 */

		if (RemainingTime == 0) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			//c.setTime(timeRecord);// 加上这句则已续约时间为准
			String date = sf.format(c.getTime());// 开始时间
			// System.out.println(date);
			c.add(Calendar.DAY_OF_MONTH, extensionTime);
			String dates = sf.format(c.getTime());// 加过天数的结束时间
			// System.out.println(dates);
			Date begindate = Date.valueOf(date);
			Date enddate = Date.valueOf(dates);
			projectdao.updateContract(id, begindate, enddate);
		} else {

			projectdao.updateContracts(id, extensionTime);
		}

	}

	@Override
	public void saveFiles(int id,String fileName) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("fileName", fileName);
		projectdao.saveFiles(map);
	}

	@Override
	public String imgNumber(int id) {
		
		return projectdao.imgNumber(id);
	}

	@Override
	public String getcustomerName(int id) {
	
		return projectdao.getcustomerName(id);
	}

	@Override
	public String getprogrammerName(int id) {
		
		return projectdao.getprogrammerName(id);
	}

	@Override
	public void updateProjectState(int id,int State) {
		if(State==1||State==2){
			State=2;
		}
		if(State==3){
			State=1;
		}
		
		projectdao.updateProjectState(id,State);
	}

	@Override
	public String getPprogrammer(int id) {
	
		return projectdao.getPprogrammer(id);
	}

	@Override
	public String getPprogramm(int id) {
		return projectdao.getPprogramm(id);
	}

	@Override
	public void updatePprogrammerState(int id) {
		projectdao.updatePprogrammerState(id);
	}

	@Override
	public void updateProjectStates() {
		projectdao.updateProjectStates();
	}

	@Override
	public List<ProgrammerProject> endTimeprogrammer() {
		return projectdao.endTimeprogrammer();
	}

	@Override
	public Integer getProgrammerID(int id) {
		return projectdao.getProgrammerID(id);
	}

	@Override
	public int getPprojectID(int id) {
		return projectdao.getPprojectID(id);
	}

}
