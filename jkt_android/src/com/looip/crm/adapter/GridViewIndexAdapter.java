package com.looip.crm.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.looip.crm.R;

/**
 * 首页 GridView的 适配器
 * 
 * @author lixingtao
 * 
 */
public class GridViewIndexAdapter extends BaseAdapter {
	private String[] names = new String[] { "机器人", "我的项目", "视频会议", "客服热线" };
	private int[] images = new int[] { R.drawable.robot, R.drawable.project,
			R.drawable.videosession, R.drawable.servicetel };

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return convertView;
	}
}
