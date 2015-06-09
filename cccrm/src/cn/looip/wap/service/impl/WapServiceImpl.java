package cn.looip.wap.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.looip.wap.repository.dao.WapDao;
import cn.looip.wap.repository.domain.ProgrammerProject;
import cn.looip.wap.repository.domain.Project;
import cn.looip.wap.repository.domain.SysUser;
import cn.looip.wap.service.interfaces.WapService;

@Service
public class WapServiceImpl implements WapService {
	@Autowired
	private WapDao wapdao;// 当他报错时候，代表找不到实现类

	@Override
	public boolean getLogin(SysUser user) {
		if (wapdao.getUserName(user.getLoginName()) != null) {
			if (wapdao.getLogin(user.getLoginName(), user.getLoginPwd()) != null) {

				return true;
			}
		}
		return false;
	}

	@Override
	public Integer getUserName(String username) {

		return wapdao.getUserName(username);
	}

	@Override
	public List<Project> getProjects(int id) {

		return wapdao.getProjects(id);
	}

	@Override
	public List<ProgrammerProject> getProjectInfo(int id) {
		List<ProgrammerProject> projectinfo = wapdao.getProjectInfo(id);
		for (int i = 0; i < projectinfo.size(); i++) {
			long sysTime = new Date().getTime();
			long BeginTime = projectinfo.get(i).getBeginTime().getTime();
			long EndTime = projectinfo.get(i).getEndTime().getTime();
			int totalDays = (int) (EndTime - BeginTime) / (1000 * 60 * 60 * 24)
					+ 1;// 算当天时间+1
			int day = (int) (EndTime - sysTime) / (1000 * 60 * 60 * 24) + 1;
			projectinfo.get(i).setDay(day);
			projectinfo.get(i).setTotalDays(totalDays);
		}
		return projectinfo;
	}

	//验证邮箱接口
	@Override
	public boolean getUserEmail(SysUser user) {
		if(wapdao.getUserEmail(user.getUserEmail()) != null){
				return true;
		}
		
		return false;
	}
	
	//查看项目合同照片
	public String imgNumber(int id) {
		// TODO Auto-generated method stub
		return wapdao.imgNumber(id);
	}

	@Override
	public void updatePasswordByEmail(int loginPwd, String userEmail) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("loginPwd", loginPwd);
		map.put("userEmail", userEmail);
		wapdao.updatePasswordByEmail(map);
		
	}

}
