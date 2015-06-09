package cn.looip.project.service.interfaces;

import java.util.Date;
import java.util.List;

import cn.looip.project.repository.domain.Customer;
import cn.looip.project.repository.domain.Log;
import cn.looip.project.repository.domain.Programmer;
import cn.looip.project.repository.domain.ProgrammerProject;
import cn.looip.project.repository.domain.Project;

public interface ProjectService
{
	public int getFinalid();

	public void saveProject(Project project);

	public void updateStatus();

	public List<Project> getProjects(String beginIndex, int pagerNum);

	public List<Project> getProjectes(String beginIndex, int pagerNum);

	public List<Project> getSeach(String beginIndex, int pagerNum,
			String proName);

	public int getNum();

	public int getNums();

	public int getNumes(String proName);

	public void updateProject(Project project);

	public Project getProject(int id);

	public void delectProject(int id);

	public List<Programmer> programmer();

	public void saveProgrammers(ProgrammerProject Pproject);

	public void deletePprogrammer(int id);

	public void updateState(int id, int State);

	public List<ProgrammerProject> getPproject(String beginIndex, int pagerNum,
			int id);

	public int getNumber(int id);

	public Log getLogs(int id, int pid);

	public List<ProgrammerProject> getRecord(String beginIndex, int pagerNum);

	public int getRecordCount();

	public List<Customer> getCustomer();

	public int getNumbers(int id);

	public List<Project> getCProject(String beginIndex, int pagerNum, int id);

	public void updateExtension(ProgrammerProject Pproject);

	public void updateContract(int id, int extensionTime, int RemainingTime,
			Date timeRecord);

	public int getNumberes(int id);

	public List<ProgrammerProject> getPprojects(String beginIndex,
			int pagerNum, int id);

	public void saveFiles(int id, String fileName);

	public String imgNumber(int id);

	public String getcustomerName(int id);

	public String getprogrammerName(int id);

	public void updateProjectState(int id, int State);

	public String getPprogrammer(int id);

	public List<ProgrammerProject> getPprogramm(int id);

	public void updatePprogrammerState(int id);

	public List<ProgrammerProject> endTimeprogrammer();

	public Integer getProgrammerID(int id);

	public Integer getProgrammersID(int id);

	public void updateProjectStates();

	public List<ProgrammerProject> getPprojectID(int id);

	public Integer determineProject(String ProName, String BargainNo);

	public Integer determinePproject(int ProgrammerID, int ProjectID);

}
