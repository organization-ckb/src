package com.looip.crm.base;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.looip.crm.R;

/**
 * pager的抽象类
 * 
 * @author lixingtao
 * 
 */
public abstract class BasePager {
	public Context ct;
	public View view;
	public boolean HAS_SUCESS_DATA = false;
	public TextView txt_title;
	public TextView tv_left;
	public ImageButton imgleft;

	/**
	 * 再构造函数中得到slidingMenu 并且初始化view
	 * 
	 * @author lixingtao
	 * @param context
	 */
	public BasePager(Context context) {
		this.ct = context;
		view = initView();
	}

	/**
	 * 得到初始化的view
	 * 
	 * @author lixingtao
	 * @return
	 */
	public View getRootView() {
		return view;
	}

	/**
	 * 初始化pager的标题
	 * 
	 * @author lixingtao
	 * @param view
	 */
	public void initTitleBar(View view) {
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		imgleft = (ImageButton) view.findViewById(R.id.img_left);
		tv_left = (TextView) view.findViewById(R.id.tv_left);
	}

	/**
	 * 通过网络加载数据的方法
	 * 
	 * @author lixingtao
	 * @param method
	 * @param url
	 * @param params
	 * @param callBack
	 */
	public void loadData(HttpMethod method, String url, RequestParams params,
			RequestCallBack<String> callBack) {
		HttpUtils httpUtils = new HttpUtils();
		if (params == null) {
			params = new RequestParams();
		}
		httpUtils.send(method, url, params, callBack);
	}

	/**
	 * 初始化view的 抽象方法
	 * 
	 * @author lixingtao
	 * @return
	 */
	public abstract View initView();

	/**
	 * 初始化数据的 抽象方法
	 * @author lixingtao
	 * @return
	 */
	public abstract void initData();
}
