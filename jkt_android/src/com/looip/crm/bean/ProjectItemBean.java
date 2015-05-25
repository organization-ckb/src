package com.looip.crm.bean;

import java.util.List;

/**
 * 项目列表 Bean
 * 
 * @author lixingtao
 * 
 */
public class ProjectItemBean {
	// 集合
	public List<projects> projects;
	// 返回码
	public int resultcode;

	/*
	 * 项目列表
	 */
	public class projects {
		public String endtime;// 结束时间
		public int id;// 项目id
		public String proName;// 项目名
		public String starttime;// 开始时间
		public int status;// 状态

		@Override
		public String toString() {
			return "projects [ endtime=" + endtime + ", id=" + id
					+ ", proName=" + proName + ", starttime=" + starttime
					+ ", status=" + status + "]";
		}

		public projects(String endtime, String proName, String starttime,
				int status) {
			super();
			this.endtime = endtime;
			this.proName = proName;
			this.starttime = starttime;
			this.status = status;
		}

	}
}
