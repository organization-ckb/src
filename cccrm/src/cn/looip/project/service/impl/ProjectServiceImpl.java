package cn.looip.project.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

	@Autowired
	private ProjectDao projectdao;

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

	@Override
	public List<Project> getProjects(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);
		}
		return projectdao.getProjects(index, pagerNum);
	}

	@Override
	public List<Project> getProjectes(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);
		}
		return projectdao.getProjectes(index, pagerNum);
	}

	@Override
	public int getNum() {
		return projectdao.getNum();
	}

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

	@Override
	public void delectProject(int id) {
		projectdao.delectProject(id);
	}

	@Override
	public List<Project> getSeach(String beginIndex, int pagerNum,
			String proName) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);
		}
		return projectdao.getSeach(index, pagerNum, proName);
	}

	@Override
	public int getNumes(String proName) {

		return projectdao.getNumes(proName);
	}

	@Override
	public List<Programmer> programmer() {

		return projectdao.getProgrammer();
	}

	@Override
	public void saveProgrammers(ProgrammerProject Pproject) {
		projectdao.saveProgrammers(Pproject);
	}

	/*
	 * 根据当前状态改为相反状态
	 * 
	 */
	@Override
	public void updateState(int id, int State) {
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
			index = Integer.parseInt(beginIndex);
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
			index = Integer.parseInt(beginIndex);
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
			index = Integer.parseInt(beginIndex);
		}
		return projectdao.getPprojects(index, pagerNum, id);
	}

	@Override
	public int getFinalid() {
		if (projectdao.getFinalid() == null) {
			return 1;
		} else {
			int id = (int) projectdao.getFinalid().get("id");
			return id + 1;
		}

	}

	@Override
	public List<ProgrammerProject> getRecord(String beginIndex, int pagerNum) {
		int index = 0;
		if (beginIndex != null) {
			index = Integer.parseInt(beginIndex);
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
		if (RemainingTime == 0) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			String date = sf.format(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, extensionTime);
			String dates = sf.format(c.getTime());
			Date begindate = Date.valueOf(date);
			Date enddate = Date.valueOf(dates);
			projectdao.updateContract(id, begindate, enddate);
		} else {

			projectdao.updateContracts(id, extensionTime);
		}

	}

	@Override
	public void saveFiles(int id, String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
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
	public void updateProjectState(int id, int State) {
		if (State == 1 || State == 2) {
			State = 2;
		}
		if (State == 3) {
			State = 1;
		}

		projectdao.updateProjectState(id, State);
	}

	@Override
	public String getPprogrammer(int id) {

		return projectdao.getPprogrammer(id);
	}

	@Override
	public  List<ProgrammerProject> getPprogramm(int id) {
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
	public List<ProgrammerProject> getPprojectID(int id) {
		return projectdao.getPprojectID(id);
	}

	@Override
	public Integer getProgrammersID(int id) {
		
		return projectdao.getProgrammersID(id);
	}

	@Override
	public Integer determineProject(String ProName, String BargainNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProName", ProName);
		map.put("BargainNo", BargainNo);
		return projectdao.determineProject(map);
		
	}

	@Override
	public Integer determinePproject(int ProgrammerID, int ProjectID) {
		
		return projectdao.determinePproject(ProgrammerID, ProjectID);
	}

}
