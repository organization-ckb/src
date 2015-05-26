package com.looip.crm.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.looip.crm.R;
import com.looip.crm.base.CCBaseAdapter;
import com.looip.crm.bean.RobotInfoBean;

/**
 * MyProjectpager 的适配器
 * 
 * @author lixingtao
 * 
 * @param <T>
 * @param <Q>
 */
public class RobotAdapter<T, Q> extends CCBaseAdapter<T, Q> {

	private TextView tv_projecTextView;
	private TextView robotPosition;

	public RobotAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RobotAdapter(Context ct, List<T> lists) {
		super(ct, lists);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = View.inflate(ct, R.layout.robot_lv_item, null);
		}
		tv_projecTextView = (TextView) convertView
				.findViewById(R.id.tv_robot_projectname);
		robotPosition = (TextView) convertView
				.findViewById(R.id.tv_robotposition);
		RobotInfoBean robotInfoBean = (RobotInfoBean) lists.get(position);

		tv_projecTextView.setText(robotInfoBean.robotProjectName);
		robotPosition.setText(robotInfoBean.robotProjectPositon);
		return convertView;
	}
}