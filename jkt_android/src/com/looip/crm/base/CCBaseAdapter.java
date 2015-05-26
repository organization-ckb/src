package com.looip.crm.base;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Adapter 抽取出来的 方法
 * 
 * @author lixingtao
 * 
 * @param <T>
 *            集合类型
 * @param <Q>
 *            返回的view类型
 */
public abstract class CCBaseAdapter<T, Q> extends BaseAdapter {

	public Context ct;

	public List<T> lists;

	public View Q;

	public CCBaseAdapter(Context ct, List<T> lists) {
		super();
		this.ct = ct;
		this.lists = lists;
	}

	public CCBaseAdapter(Context ct, List<T> lists, View q) {
		super();
		this.ct = ct;
		this.lists = lists;
		Q = q;
	}

	public CCBaseAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
