package com.looip.crm.pager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.looip.crm.R;
import com.looip.crm.activity.IndexActivity;
import com.looip.crm.adapter.MyProjectAdapter;
import com.looip.crm.base.BasePager;
import com.looip.crm.bean.ProjectItemBean;
import com.looip.crm.bean.ProjectItemBean.projects;
import com.looip.crm.projectinfo.Activity_ListView;
import com.looip.crm.refreshlistview.RefreshListView;
import com.looip.crm.utils.NetUtils;
import com.looip.crm.utils.PromptManager;
import com.looip.crm.utils.SharedPreferencesUtils;

/**
 * 我的项目界面
 * 
 * @author lixingtao
 * 
 */
public class MyProjectPager extends BasePager implements OnClickListener {
	// 项目列表集合
	private List<projects> projectItemList = new ArrayList<projects>();
	// 下拉刷新listview
	private RefreshListView mListView;
	// 项目 适配器
	private MyProjectAdapter adapter;
	// 开始时间
	private long startTime;
	// 用户Id
	private String userID;

	public MyProjectPager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		View view = View.inflate(ct, R.layout.activity_myproject, null);
		mListView = (RefreshListView) view.findViewById(R.id.lv_myproject);
		initTitleBar(view);
		if (!PromptManager.isConn(ct)) {
			/*
			 * 设置网络
			 */
			PromptManager.setNetworkMethod(ct);
		}
		return view;
	}

	@Override
	public void initData() {
		/*
		 * 获取到sp中的缓冲数据有就用 没用就加载
		 */
		String result = SharedPreferencesUtils.getString(ct,
				"projectItemBeanJson", "");
		if (!TextUtils.isEmpty(result)) {
			processData(result);
		}
		// 获取到用户的Id
		userID = SharedPreferencesUtils.getString(ct, "userID", null);
		initProjectItemList();
		// 设置标题
		txt_title.setText("我的项目");
		tv_left.setText("首页");
		imgleft.setOnClickListener(this);
		adapter = new MyProjectAdapter<>(ct, projectItemList);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			projects projects;

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mListView.getChildCount() > 0) {
					projects = projectItemList.get(position - 1);
					// Toast.makeText(ct, "" + (position - 1), 0).show();
					/**
					 * 跳转界面 得到项目ItemID 传递给activity
					 * 
					 * @author lixingtao
					 */
					int ItemId = projectItemList.get(position - 1).id;
					// 获取项目ID传给后一个页面
					// Toast.makeText(ct, ItemId + "ItemID", 0).show();
					Intent intent = new Intent(ct, Activity_ListView.class);
					intent.putExtra("ItemId", ItemId);
					ct.startActivity(intent);

				} else {
					projects = projectItemList.get(position);
					Toast.makeText(ct, "" + position, 0).show();
					/**
					 * 跳转界面
					 * 
					 * @author lixingtao
					 */
					int ItemID = projectItemList.get(position - 1).id;
					// Toast.makeText(ct, ItemID + "haha", 0).show();
					Intent intent = new Intent(ct, Activity_ListView.class);
					intent.putExtra("ItemID", ItemID);
					ct.startActivity(intent);
				}
			}
		});

		/**
		 * 设置下拉刷新
		 */
		mListView
				.setOnRefreshListener(new com.looip.crm.refreshlistview.OnRefreshListener() {

					@Override
					public void onPullDownRefresh() {

						// 刷新数据.
						new AsyncTask<Void, Void, Void>() {
							/**
							 * 在onPreExecute执行完之后, 执行, 运行在子线程中.
							 */
							@Override
							protected Void doInBackground(Void... params) {
								SystemClock.sleep(3000);
								// projectItemList.add(new
								// projects("231331","laomi","123131231",1));
								return null;
							}

							/**
							 * 在doInBackground方法执行之后, 调用. 运行在主线程中
							 */
							@Override
							protected void onPostExecute(Void result) {

								// 调用REfreshListView中的方法, 隐藏头布局
								mListView.hideHeaderView();
								adapter.notifyDataSetChanged();
							}
						}.execute(new Void[] {}); // 调用onPreExecute
					}

					/**
					 * 加载更多
					 * 
					 * @author lixingtao
					 */
					@Override
					public void onLoadingMore() {
						// new AsyncTask<Void, Void, Void>() {
						//
						// @Override
						// protected Void doInBackground(Void... params) {
						// SystemClock.sleep(5000);
						// return null;
						// }
						//
						// @Override
						// protected void onPostExecute(Void result) {
						// adapter.notifyDataSetChanged();
						//
						// 脚布局隐藏
						mListView.hideFooterView();
						// }
						//
						// }.execute(new Void[] {});
					}
				});
	}

	/**
	 * 初始化集合数据 Url userid再sp中获取
	 * 
	 * @author lixingtao
	 */
	private void initProjectItemList() {
		loadData(HttpMethod.GET, NetUtils.PROJECTITEMBEAN + "1", null,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						// 获取数据失败提示
						Toast.makeText(ct, "网络连接异常！", 0).show();

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						/**
						 * 成功后缓存 & 解析
						 */

						LogUtils.d("responseInfo---->" + arg0.result);

						SharedPreferencesUtils.saveString(ct,
								"projectItemBeanJson", arg0.result);
						processData(arg0.result);

					}
				});
	}

	/**
	 * 处理json数据
	 * 
	 * @author lixingtao
	 * @param result
	 *            json数据
	 */
	protected void processData(String result) {
		Gson gson = new Gson();// gson 对象
		// 得到集合对象
		ProjectItemBean json = gson.fromJson(result, ProjectItemBean.class);
		List<projects> projects = json.projects;
		// 如果集合不为空&&返回码为200 继续下步操作
		if (projects.size() > 0 && json.resultcode == 200) {
			projectItemList.clear();
			projectItemList.addAll(projects);
			/*
			 * 设置adapter的更新（MyProjectAdapter&mListView）
			 */
			if (adapter == null) {
				adapter = new MyProjectAdapter(ct, projectItemList);
				mListView.setAdapter(adapter);
			} else {
				adapter.notifyDataSetChanged();
			}
		} else {
			Toast.makeText(ct, "获取网络数据异常", 0).show();
		}

	}

	/**
	 * 处理事件
	 * 
	 * @author lixingtao
	 */
	@Override
	public void onClick(View v) {
		// 返回到首页
		ct.startActivity(new Intent(ct, IndexActivity.class));
	}

}
