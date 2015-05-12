package cn.looip.resource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.looip.core.utils.GeneralTools;
import cn.looip.resource.repository.dao.ResourceDAO;
import cn.looip.resource.repository.domain.Programmer;
import cn.looip.resource.service.interfaces.ResourceService;

/**
 * 资源模块业务接口实现
 * 
 * @author hujian
 */
@Service
public class ResourceServiceImpl implements ResourceService
{

	// 持久化接口
	@Autowired
	private ResourceDAO resourceDAO;

	// 工具类
	@Autowired
	private GeneralTools iTools;

	/* 新增程序员信息至程序员表 */
	@Override
	public int insertProgrammer(Programmer programmer)
	{

		return resourceDAO.insertProgrammer(programmer);

		/*
		 * boolean isSuccess = false; Map<String,Object> map = new
		 * HashMap<String, Object>(); map.put("department", department);
		 * map.put("programmerName", programmerName); map.put("programmerSex",
		 * programmerSex); map.put("programmerQq", programmerQq);
		 * map.put("programmerLevel", programmerLevel);
		 * map.put("programmerStatus",programmerStatus); int programmer1 =
		 * resourceDAO.insertProgrammer1(map); return true;
		 */

	}

	/* 增加程序员信息到管理员表sys_users */
	// @Override
	// public void insertProgrammer2(Manager manager){
	//
	// /*manager.setLoginName(loginName);
	// manager.setLoginPwd(loginPwd);
	// manager.setUserMobile(userMobile);*/
	// resourceDAO.insertProgrammer2(manager);
	//
	// }
	/*
	 * @Override public boolean insertProgrammer2(String loginName, String
	 * loginPwd, String userMobile,String programmerName,String programmerQq) {
	 * Map<String,Object> map = new HashMap<String, Object>();
	 * map.put("loginName", loginName); map.put("loginPwd", loginPwd);
	 * map.put("userMobile", userMobile); map.put("programmerName",
	 * programmerName); map.put("programmerQq", programmerQq); int programmer2 =
	 * resourceDAO.insertProgrammer2(map); return true;
	 * 
	 * }
	 */

	@Override
	public List<Programmer> getProgrammers(String beginIndex, int pagerNum)
	{
		int pagerIndex = 0;
		if (beginIndex != null)
		{
			pagerIndex = Integer.parseInt(beginIndex);// 将字符串转换为int型（整型）
		}
		return resourceDAO.getPageProgrammer(pagerIndex, pagerNum);
	}

	@Override
	public int getNum()
	{
		// TODO Auto-generated method stub
		return resourceDAO.getNum();
	}

	// 查询程序员
	@Override
	public List<Programmer> getprogrammers(String pagerIndex,int pagerNum)
	{
		int Index = 0;
		if (pagerIndex != null)
		{
			Index = Integer.parseInt(pagerIndex);// 将字符串转换为int型（整型）
		}
		return resourceDAO.getprogrammers(Index,pagerNum);
	}

	// 按条件查询程序员
	@Override
	public List<Programmer> searchprogrammer(int department,int status)
	{
		return resourceDAO.searchprogrammer(department,status);
	}

	@Override
	public Programmer getProgrammer(int id)
	{
		// TODO Auto-generated method stub
		return resourceDAO.getProgrammer(id);
	}

	@Override
	public int updateProgrammer(Programmer programmer)
	{
		return resourceDAO.updateProgrammer(programmer);
	}

	@Override
	public int count() {
		return resourceDAO.count();
	}

}
