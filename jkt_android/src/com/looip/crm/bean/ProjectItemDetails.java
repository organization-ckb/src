package com.looip.crm.bean;

import java.util.List;

/**
 * 项目详情bean
 * 
 * @author 李兴涛
 * 
 */
public class ProjectItemDetails {
	public List<projetinfo> projetinfo;
	public int resultcode;

	public class projetinfo {
		public String agree;
		public String beginTime;
		public String day;
		public String department_name;
		public String endTime;
		public String extension_time;
		public String id;
		public String pro_status;
		public String programmerLevel;
		public String programmerName;
		public String programmer_id;
		public String project_id;
		public String status;
		public String timeRecord;
		public String totalDays;

		@Override
		public String toString() {
			return "projetinfo [agree=" + agree + ", beginTime=" + beginTime
					+ ", day=" + day + ", department_name=" + department_name
					+ ", endTime=" + endTime + ", extension_time="
					+ extension_time + ", id=" + id + ", pro_status="
					+ pro_status + ", programmerLevel=" + programmerLevel
					+ ", programmerName=" + programmerName + ", programmer_id="
					+ programmer_id + ", project_id=" + project_id
					+ ", status=" + status + ", timeRecord=" + timeRecord
					+ ", totalDays=" + totalDays + "]";
		}

	}

}
