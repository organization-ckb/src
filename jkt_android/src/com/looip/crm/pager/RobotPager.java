package com.looip.crm.pager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.looip.crm.R;
import com.looip.crm.activity.IndexActivity;
import com.looip.crm.adapter.RobotAdapter;
import com.looip.crm.base.BasePager;
import com.looip.crm.bean.RobotInfoBean;
import com.looip.crm.refreshlistview.RefreshListView;
import com.looip.crm.utils.NetUtils;
import com.looip.crm.utils.PromptManager;

/**
 * 机器人 界面
 * 
 * @author lixingtao
 * 
 */
public class RobotPager extends BasePager implements OnClickListener {
	private List<RobotInfoBean> robotList = new ArrayList<RobotInfoBean>();
	private RefreshListView mListView;
	private RobotAdapter robotAdapter;
	private PackageManager packageManager;
	private boolean installed;
	private String apkFile = Environment.getExternalStorageDirectory()
			.getAbsolutePath().toString()
			+ "/padbot.apk";

	// private File file = new File(ct.getFilesDir(), "padbot.apk");
	public File file = new File(Environment.getExternalStorageDirectory()
			.getAbsolutePath().toString(), "padbot.apk");
	private ProgressDialog progressDialog;

	public RobotPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {

		progressDialog = new ProgressDialog(ct);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		View view = View.inflate(ct, R.layout.activity_robot, null);
		mListView = (RefreshListView) view.findViewById(R.id.lv_robot);
		initTitleBar(view);
		// 设置标题
		txt_title.setText("机器人");
		tv_left.setText("首页");
		imgleft.setOnClickListener(this);
		installed = isInstalled("com.inbot.module.padbot", "派宝");

		return view;
	}

	/**
	 * 判断是否下载完成
	 * 
	 */
	/**
	 * 获取程序列表
	 * 
	 * @author lixingtao
	 */
	private boolean isInstalled(String packageName, String appSoftName) {
		ApplicationInfo appInfo;
		String packname = null;
		String appName = null;
		// 获取一个包管理器
		packageManager = ct.getPackageManager();
		List list = new ArrayList<>();
		// 获取到所有安装了的应用程序的信息，包括那些卸载了的，但没有清除数据的应用程序
		List<PackageInfo> packageInfos = packageManager
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		// 遍历集合
		for (PackageInfo info : packageInfos) {
			// 拿到包名
			packname = info.packageName;
			appInfo = info.applicationInfo;
			appName = appInfo.loadLabel(packageManager).toString();
			// //判断是否安装了某应用
			if (packname.equals(packageName) && appName.equals(appSoftName)) {
				return true;
			}

		}

		return false;
	}

	/**
	 * 启动程序
	 * 
	 * @author lixingtao
	 */
	private void startApp(String packageName) {
		// TODO Auto-generated method stub
		PackageManager pm = ct.getPackageManager();
		Intent intent = pm.getLaunchIntentForPackage(packageName);// 包名
		if (intent == null) {
			Toast.makeText(ct, "应用程序无法启动", 0).show();
		} else {
			ct.startActivity(intent);
		}
	}

	/**
	 * 安装程序
	 * 
	 * @author lixingtao
	 * @param v
	 * @throws IOException
	 */
	public void installApp(File file) {
		/*
		 * <intent-filter> <action android:name="android.intent.action.VIEW" />
		 * <category android:name="android.intent.category.DEFAULT" /> <data
		 * android:scheme="content" /> <data android:scheme="file" /> <data
		 * android:mimeType="application/vnd.android.package-archive" />
		 * </intent-filter>
		 */
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		// 文件 /sdcard
		// File file = new File("file:///android_assets/padbot.apk");
		// File file = new File(Environment.getExternalStorageDirectory(),
		// "padbot.apk");
		Uri data = Uri.fromFile(file);
		// 如果一个activity中有scheme和mimtype使用setDateAndType
		intent.setDataAndType(data, "application/vnd.android.package-archive");
		ct.startActivity(intent);
	}

	@Override
	public void initData() {
		robotList.clear();
		robotList.add(new RobotInfoBean("吹米项目机器人", "持创研发中心201"));
		robotAdapter = new RobotAdapter(ct, robotList);
		mListView.setAdapter(robotAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mListView.getChildCount() > 0) {
					Toast.makeText(ct, "" + (position - 1), 0).show();
					if (installed) {
						startApp("com.inbot.module.padbot");
					} else {
						/**
						 * 获取网络状态
						 */
						if (PromptManager.isConn(ct)) {
							showDownDialog();
							installed = isInstalled("com.inbot.module.padbot",
									"派宝");
						} else {
							PromptManager.setNetworkMethod(ct);
						}

					}

				} else {
					Toast.makeText(ct, "" + position, 0).show();
					if (installed) {
						startApp("com.inbot.module.padbot");
					} else {
						/**
						 * 获取网络状态
						 */
						if (PromptManager.isConn(ct)) {
							showDownDialog();
							installed = isInstalled("com.inbot.module.padbot",
									"派宝");
						} else {
							PromptManager.setNetworkMethod(ct);
						}
					}
				}
			}

		});
		/**
		 * 设置下拉刷新
		 * 
		 * @author lixingtao
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
								SystemClock.sleep(1000);
								robotList.add(new RobotInfoBean("454545",
										"dsfdsafasd"));
								return null;
							}

							/**
							 * 在doInBackground方法执行之后, 调用. 运行在主线程中
							 */
							@Override
							protected void onPostExecute(Void result) {

								// 调用REfreshListView中的方法, 隐藏头布局
								mListView.hideHeaderView();
								robotAdapter.notifyDataSetChanged();
							}
						}.execute(new Void[] {}); // 调用onPreExecute
					}

					/**
					 * 加载更多
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
	 * 链接互联网
	 */
	@Override
	public void loadData(HttpMethod method, String url, RequestParams params,
			RequestCallBack<String> callBack) {
		// TODO Auto-generated method stub
		super.loadData(method, url, params, callBack);
	}

	/**
	 * 显示下载提示对话框
	 * 
	 * @author lixingtao
	 */
	protected void showDownDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ct);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("是否下载派宝");
		builder.setMessage("是否立即下载派宝，控制机器人！");
		builder.setCancelable(false);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			private HttpHandler handler;

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HttpUtils http = new HttpUtils();
				handler = http.download(NetUtils.DOWNLOADAPPURI, apkFile, true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
						true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
						new RequestCallBack<File>() {

							@Override
							public void onStart() {
								progressDialog.setTitle("准备下载");
								progressDialog.setMessage("正在连接......");
								progressDialog.show();
								// testTextView.setText("conn...");
							}

							@Override
							public void onLoading(long total, long current,
									boolean isUploading) {

								// testTextView.setText(current + "/" + total);

								progressDialog.setTitle("下载中");
								progressDialog.setMax((int) total);
								progressDialog.setProgress((int) current);
								progressDialog.setMessage("总大小："
										+ Formatter.formatFileSize(ct, total)
										+ " " + "已下载："
										+ Formatter.formatFileSize(ct, current));

								progressDialog.setCancelable(false);
								progressDialog
										.setOnKeyListener(new DialogInterface.OnKeyListener() {

											public boolean onKey(
													DialogInterface dialog,
													int keyCode, KeyEvent event) {
												// TODO Auto-generated method
												// stub
												// Cancel task.
												if (keyCode == KeyEvent.KEYCODE_BACK) {
													progressDialog.dismiss();
													handler.cancel();
												}
												return true;
											}
										});
							}

							@Override
							public void onSuccess(
									ResponseInfo<File> responseInfo) {
								// testTextView.setText("downloaded:" +
								// responseInfo.result.getPath());
								progressDialog.setTitle("下载完成");
								progressDialog.setMessage("downloaded:"
										+ responseInfo.result.getPath());
								progressDialog.dismiss();
								AlertDialog.Builder builder = new AlertDialog.Builder(
										ct)
										.setTitle("安装应用")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("您要立即安装应用？")
										.setPositiveButton(
												"是",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														installApp(file);

													}
												})
										.setNegativeButton(
												"否",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub
													}
												});
								builder.create();
								builder.show();
							}

							@Override
							public void onFailure(HttpException error,
									String msg) {
								// testTextView.setText(msg);
								progressDialog.setTitle("下载进度");
								progressDialog.setMessage("downloaded:" + msg);
								progressDialog.setMessage("下载执行完成请安装！");
								SystemClock.sleep(2000);
								progressDialog.dismiss();
								AlertDialog.Builder builder = new AlertDialog.Builder(
										ct)
										.setTitle("安装应用")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("您要立即安装应用？")
										.setPositiveButton(
												"是",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														installApp(file);

													}
												})
										.setNegativeButton(
												"否",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub
													}
												});
								builder.create();
								builder.show();
							}
						});
			}
		});

		builder.create();
		builder.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ct.startActivity(new Intent(ct, IndexActivity.class));
	}

}
