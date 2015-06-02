package com.looip.crm.projectinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.looip.crm.R;
import com.looip.crm.bean.ProjectItemBean.projects;
import com.looip.crm.bean.ProjectItemDetails;
import com.looip.crm.bean.ProjectItemDetails.projetinfo;

public class Activity_ListView extends Activity implements OnPageChangeListener {
	SharedPreferences shrPreferences;
	SharedPreferences preferences;
	private static final int DIALOG_TEXT_ENTRY = 1;
	View myview;
	View myviewpager;
	private int selectPosition;
	private ViewPager viewPager; // 图片列表的 “父控件”
	private TextView vDescription; // 文字描述列表的 “父控件”
	private LinearLayout vPoints; // 圆点列表的 “父控件”
	private String[] imageDescriptionArrays; // 用来存储文字描述的 “字符数组”
	private List<ImageView> imageViews; // List 集合用来存储 ImageView 图片来显示到界面上
	private int previousPosition = 0; // 始化的第一个圆点 “0”
	private boolean isLooper = true;
	private ListView lv_userinfo;
	private List<Map<String, Object>> data;
	/**
	 * @author 李兴涛
	 */
	private List<projetinfo> myList = new ArrayList<projetinfo>();
	// 接到 “空消息” 就切换到下一页 ，实现滚动播放
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 每两秒钟执行一下此方法, 切换一下view

			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/**
		 * 去除标题
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_listview);
		lv_userinfo = (ListView) findViewById(R.id.lv_userinfo);
		//	data = getData();
		shrPreferences=getSharedPreferences("dialoginfo",MODE_WORLD_READABLE);
		preferences=getSharedPreferences("dialoginfo", MODE_WORLD_READABLE);

		myviewpager = View.inflate(Activity_ListView.this,
				R.layout.activity_viewpager, null);
		lv_userinfo.addHeaderView(myviewpager);
		lv_userinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				/**
				 * 显示续约提示框
				 */
				//showDialog(DIALOG_TEXT_ENTRY);
			}
		});
		loadProjectDate();
		init();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isLooper) {

					SystemClock.sleep(3000);
					// 切换ViewPager
					handler.sendEmptyMessage(0);

				}

			}
		});
		thread.start();

		lv_userinfo.setAdapter(new MyAdapter1(this, myList));
	}

	/**
	 * 联网获取数据
	 * 
	 * @author 李兴涛
	 */
	private void loadProjectDate() {
		// TODO Auto-generated method stub
		//获得前一个页面的项目id
		Intent intent=getIntent();
		String ItemId=intent.getIntExtra("ItemId", 0)+"";
		//Toast.makeText(Activity_ListView.this, ItemId+"+++++", Toast.LENGTH_SHORT).show();
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET,
				"http://crm.test.looip.com/wap/projectinfo?id="+ItemId,
				new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity_ListView.this, "网络数据异常", 0)
				.show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				SharedPreferences sharedPreferences=getSharedPreferences("projectinfo",MODE_WORLD_READABLE);
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putString("projectinfo", result);
				System.out.println("1111111111111111111" + result);
				//解析数据
				processDate(result);
			}
		});
	}

	/**
	 * 解析json
	 * 
	 * @author 李兴涛
	 * @param result
	 */
	protected void processDate(String result) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		ProjectItemDetails details = gson.fromJson(result,
				ProjectItemDetails.class);
		List<projetinfo> projetinfo = details.projetinfo;
		if (details.resultcode == 200) {
			myList.clear();
			for (projetinfo projet : projetinfo) {
				System.out.println(projet.toString());
				myList.add(projet);
			}
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_TEXT_ENTRY:
			// This example shows how to add a custom layout to an AlertDialog
			LayoutInflater factory = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			// LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(R.layout.dialog_renewal,
					null);
			// AlertDialog ad=new AlertDialog.Builder(AlertDialogSamples.this)
			final Dialog ad = new Dialog(this, R.style.Theme_CustomDialog3);
			ad.setContentView(textEntryView);
			TextView tv_renewal_sure = (TextView) textEntryView
					.findViewById(R.id.tv_renewal_sure);
			TextView tv_renewal_cancel = (TextView) textEntryView
					.findViewById(R.id.tv_renewal_cancel);
			TextView tv_dialog_name=(TextView) textEntryView.findViewById(R.id.tv_dialog_name);

			TextView tv_dialog_level=(TextView) textEntryView.findViewById(R.id.tv_dialog_level);

			TextView tv_dialog_lastday=(TextView) textEntryView.findViewById(R.id.tv_dialog_lastday);

			EditText et_dialog_lastday=(EditText) textEntryView.findViewById(R.id.et_dialog_lastday);

			String programmerName=preferences.getString("dialogName", null);
			String programmerLevel=preferences.getString("dialogLevel", null);
			String ProgrammerDay=preferences.getString("dialogLastday", null);
			tv_dialog_name.setText(programmerName);
			tv_dialog_level.setText(programmerLevel);
			tv_dialog_lastday.setText(ProgrammerDay);
			String programmerId=preferences.getString("dialogId", null);

			System.out.println(programmerId+"|"+programmerName+"|"+programmerLevel+"|"+ProgrammerDay);

			tv_renewal_cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ad.dismiss();
					removeDialog(DIALOG_TEXT_ENTRY);
				}
			});
			tv_renewal_sure.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			ad.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					ad.dismiss();
					removeDialog(DIALOG_TEXT_ENTRY);

				}
			});

			return ad;
		}
		return null;
	}

	private void init() {
		// 初始化布局
		viewPager = (ViewPager) myviewpager.findViewById(R.id.viewpager);
		vDescription = (TextView) myviewpager.findViewById(R.id.tv_description);
		vPoints = (LinearLayout) myviewpager.findViewById(R.id.ll_points);
		int[] imageResIDs = getImageResIDs(); // 用来存储图片的 id
		imageDescriptionArrays = getImageDescriptionArray(); // 用来存储文字描述的 “字符数组”
		// 我们需要定义一个 List 集合用来存储 ImageView 图片来显示到界面上
		imageViews = new ArrayList<ImageView>();

		ImageView iv;
		LayoutParams lp;

		for (int i = 0; i < imageDescriptionArrays.length; i++) {
			// 创建一个ImageView, 并且添加到集合中
			iv = new ImageView(myviewpager.getContext());
			iv.setBackgroundResource(imageResIDs[i]); // 将图片 id 数组中对应的图片作为 iv
			// 的背景资源
			imageViews.add(iv); // 将 iv 添加到 imageViews 集合中

			// 把对应的圆点 ， 添加到线性布局中
			myviewpager = new View(myviewpager.getContext());
			lp = new LayoutParams(14, 14); // 把 LayoutParams 属性设置给 lp
			// Ctrl+Shift+o 导入：import android.widget.LinearLayout.LayoutParams;
			lp.leftMargin = 5; // 设置 “圆点相隔的间距” 为 5dip
			lp.rightMargin = 5;
			myviewpager.setLayoutParams(lp); // 将 view 对象的宽高都设置为 10 ，这样圆角半径为 5
			// 的四个角拼起来正好是一个圆

			myviewpager.setBackgroundResource(R.drawable.point_background); // 设置圆点
			// view
			// 的背景布局

			myviewpager.setEnabled(false); // 将 view 设置为 false 普通圆点 ，“灰暗”
			vPoints.addView(myviewpager);
		}

		// 将数据填充到 viewPager 中
		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener((OnPageChangeListener) myviewpager
				.getContext());

		// 将 int 最大值除以2 ，然后再 “模” 图片数组长度 ，这样得到的余数就是循环播放的页码
		int m = (Integer.MAX_VALUE / 2) % imageViews.size();
		int item = (Integer.MAX_VALUE / 2) - m;
		viewPager.setCurrentItem(item);

		// 初始化要显示在界面的 “图片” ，“文字” ，“圆点”
		vDescription.setText(imageDescriptionArrays[0]); // 第一串文字
		vPoints.getChildAt(0).setEnabled(true); // 获取圆点集合 vPoints 中的第一个圆点为 “亮起”
	}

	// 创建一个 PagerAdapter 来对数据进行筛选 .
	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * ViewPager 有一个 “预加载” 功能 ，会预加载 “左右两边” 的图片 当前显示的是 arg0 ，预加载的是下一个 view
		 * 对象： arg1 ，如果arg0滑动到一半又重新跑回原来的位置 ， 那么 arg0=arg1 ，那么我们就 “复用”这个 view 对象
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 重写 PagerAdapter 中的 destroyItem 销毁方法 和 instantiateItem 初始化方法
		/**
		 * 根据 isViewFromObject 加载完 “左，中，右” 三个 view 对象后 ，将用过的 view 的 position 传过来
		 * ， 销毁掉该 view ☆注意：destroyItem 方法的父类是抛出一个异常 ，所以我们应该不适用被注释的那句 super
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			// 获取传递过来的 position ，使用 container 对象去销毁 ViewGroup 中的 ImageView.
			container.removeView(imageViews.get(position % imageViews.size()));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 将 “预加载” 的 view 对象添加到 ViewGroup 中 .
			container.addView(imageViews.get(position % imageViews.size()));
			return imageViews.get(position % imageViews.size()); // 添加之后还需要返回
		}

	}

	// 定义 “滚动图片” 的数组 ，来存储图片的 id
	private int[] getImageResIDs() {
		return new int[] { R.drawable.mm, R.drawable.nn, R.drawable.mm,
				R.drawable.nn

		};
	}

	// 定义 “图片说明” 的数组 ，来存储描述的 字符串
	private String[] getImageDescriptionArray() {
		return new String[] { "", "", "", ""

		};
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	/**
	 * 当 “图片切换选择” 的时候回调 ，图片切换时同时改变下面的文字描述跟圆点
	 * 
	 */
	@Override
	public void onPageSelected(int position) {
		// 取得在集合中真正的位置.
		int newPosition = position % imageViews.size();
		selectPosition = position % imageViews.size();
		View view = imageViews.get(newPosition);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Toast.makeText(Activity_ProjectInfo.this, selectPosition +
				// "", 0).show();
				Intent intent = new Intent();
				intent.setClass(Activity_ListView.this, Activity_Photo.class);
				startActivity(intent);

			}
		});
		vPoints.getChildAt(previousPosition).setEnabled(false); // 把前一个点置为未选中
		vPoints.getChildAt(newPosition).setEnabled(true);// 把当前的点置为选中

		previousPosition = newPosition; // 把当前的点用前一个点的变量记录下来
	}


	/**
	 * BaseAdapter适配器.
	 * 
	 */
	public class MyAdapter1 extends BaseAdapter {
		private LayoutInflater mInflater = null;
		private List<projetinfo> list;

		private MyAdapter1(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		public MyAdapter1(Activity_ListView activity_ListView,
				List<projetinfo> myList) {
			// TODO Auto-generated constructor stub
			this.list = myList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 如果缓存convertView为空，则需要创建View
			if (convertView == null) {
				// 根据自定义的item加载布局
				convertView = View.inflate(Activity_ListView.this,
						R.layout.userinfo_item, null);

			}
			//头像
			ImageView iv_userinfo_p = (ImageView) convertView
					.findViewById(R.id.iv_userinfo_p);
			//用户名
			TextView tv_userinfo_name = (TextView) convertView
					.findViewById(R.id.tv_userinfo_name);
			//等级
			TextView tv_userinfo_level = (TextView) convertView
					.findViewById(R.id.tv_userinfo_level);
			//剩余
			TextView tv_userinfo_lastday = (TextView) convertView
					.findViewById(R.id.tv_userinfo_lastday);
			//剩余天数
			TextView tv_userinfo_day = (TextView) convertView
					.findViewById(R.id.tv_userinfo_day);
			//续约
			TextView tv_renewal = (TextView) convertView
					.findViewById(R.id.tv_renewal);


			//iv_userinfo_p.setBackgroundResource((Integer) data.get(position).get("img"));

			//设置程序员姓名
			tv_userinfo_name.setText(myList.get(position).programmerName);
			String level=myList.get(position).programmerLevel;
			if(level.equals("0")){
				level="初级工程师";
			}else if(level.equals("1")){
				level="中级工程师";
			}else if(level.equals("2")){
				level="高级工程师";
			}else if(level.equals("3")){
				level="资深工程师";
			}
			String department=myList.get(position).department_name;
			//设置程序员等级
			tv_userinfo_level.setText(department+level);
			//设置剩余工作日
			String finish=myList.get(position).status;
			if(finish.equals("0")){
				tv_userinfo_lastday.setText("剩余工作日:");
				tv_userinfo_day.setText(myList.get(position).day);
				tv_renewal.setText("续约");
				tv_renewal.setEnabled(true);
			}else {
				tv_userinfo_lastday.setText("总工作日:");
				tv_userinfo_day.setText(myList.get(position).totalDays);
				tv_renewal.setText("项目已完成");
				tv_renewal.setEnabled(false);
			}
			tv_renewal.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Toast.makeText(Activity_ListView.this,"success", Toast.LENGTH_SHORT).show();

					String dialogName=myList.get(position).programmerName;
					String dialogId=myList.get(position).programmer_id;
					String level=myList.get(position).programmerLevel;
					if(level.equals("0")){
						level="初级工程师";
					}else if(level.equals("1")){
						level="中级工程师";
					}else if(level.equals("2")){
						level="高级工程师";
					}else if(level.equals("3")){
						level="资深工程师";
					}

					//缓存点选的程序员信息
					String dialogLevel=myList.get(position).department_name+level;
					String dialogLastday=myList.get(position).day;
					Editor editor=shrPreferences.edit();
					editor.putString("dialogName", dialogName);
					editor.putString("dialogId", dialogId);
					editor.putString("dialogLevel", dialogLevel);
					editor.putString("dialogLastday", dialogLastday);
					editor.commit();
					System.out.println(dialogName+"   "+dialogId+"   "+dialogLevel+"   "+dialogLastday);
					showDialog(DIALOG_TEXT_ENTRY);

				}
			});

			return convertView;
		}

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) { 		
			System.out.println("++++++++++++++++++++++++++++");
			Activity_ListView.this.finish();
		}

		return super.onKeyDown(keyCode, event);
	}

}
