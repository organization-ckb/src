package com.looip.crm.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.looip.crm.R;
import com.looip.crm.base.CCBaseAdapter;
import com.looip.crm.bean.ProjectItemBean.projects;

/**
 * 我的项目列表 adapter
 * 
 * @author lxtao
 * 
 * @param <T>
 *            集合泛型
 * @param <Q>
 *            view类型
 */
public class MyProjectAdapter<T, Q> extends CCBaseAdapter<T, Q> {

	private TextView tv_projecTextView;
	private TextView tv_projectstate;
	private TextView tv_starttime;
	private TextView tv_endtime;

	public MyProjectAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyProjectAdapter(Context ct, List<T> lists) {
		super(ct, lists);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = View.inflate(ct, R.layout.myproject_lv_item, null);
		}
		tv_projecTextView = (TextView) convertView
				.findViewById(R.id.tv_projectname);
		tv_projectstate = (TextView) convertView
				.findViewById(R.id.tv_projectstate);
		tv_starttime = (TextView) convertView.findViewById(R.id.tv_starttime);
		tv_endtime = (TextView) convertView.findViewById(R.id.tv_endtime);

		projects projects = (projects) lists.get(position);
		tv_projecTextView.setText("项目名称：" + projects.proName);
		/*
		 * 状态 0 完成 1待分配 2进行中
		 */
		switch (projects.status) {
		case 0:
			tv_projectstate.setText("状态：" + "完成");
			break;
		case 1:
			tv_projectstate.setText("状态：" + "待分配");
			break;
		case 2:
			tv_projectstate.setText("状态：" + "进行中");
			break;

		default:
			break;
		}

		tv_starttime.setText("开始时间：" + projects.starttime);
		tv_endtime.setText("结束时间：" + projects.endtime);

		return convertView;
	}
}