package com.looip.crm.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.looip.crm.R;
import com.looip.crm.utils.PromptManager;
import com.looip.crm.utils.SharedPreferencesUtils;

/**
 * 
 * 
 * 
 * 
 * 程序 首页
 * 
 * @author lixingtao
 * 
 */
public class IndexActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	private ImageButton img_right;// 退出按钮
	private ImageButton img_left; // 返回按钮
	// gridview
	private GridView gv_index;
	// adapter
	private GVAdapter adapter;
	private int[] imageResIDs; // 图片的id数据
	private List<ImageView> imageViewList; // viewpager的数据
	private ViewPager mViewPager; // 展示广告条的对象
	private LinearLayout llPointGroup; // 点的组
	private TextView tvImageDescription; // 图片描述
	private int previousPointPosition = 0; // 前一个被选中的点的索引
	private boolean isStop = false; // 是否停止循环的线程: 默认为, 不停止
	private int selectPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 设置无标题显示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

		/**
		 * 设置网络
		 * 
		 * @author lixingtao
		 */
		if (!PromptManager.isConn(this)) {
			PromptManager.setNetworkMethod(this);
		}

		initView();
		/**
		 * 开启一个子线程, 每隔3秒钟切换一个页面
		 * 
		 * @author lixingtao
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					SystemClock.sleep(5000); // 睡一秒

					if (isStop) {
						break;
					}

					// 接收的runnable对象中的run方法, 将要运行在主线程中.
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// 得到一个新的item的索引
							int newCurrentItem = mViewPager.getCurrentItem() + 1;
							mViewPager.setCurrentItem(newCurrentItem);
						}
					});
				}
			}
		}).start();
	}

	/**
	 * 初始化View
	 * 
	 * @author lixingtao
	 */
	private void initView() {
		img_right = (ImageButton) findViewById(R.id.imgbtn_right);
		findViewById(R.id.img_left).setVisibility(View.INVISIBLE);// 隐藏返回按钮
		img_right.setImageResource(R.drawable.logoff);
		img_right.setOnClickListener(this);
		// GridView
		gv_index = (GridView) findViewById(R.id.gv_index);
		adapter = new GVAdapter();
		// viewpager
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		// 点的容器
		llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);

		initDate();
		mViewPager.setAdapter(new MyPagerAdapter()); // 设置适配器时, 需要去找适配器要数据
		// 设置当页面改变的监听事件
		mViewPager.setOnPageChangeListener(this);
		llPointGroup.getChildAt(0).setEnabled(true); // 把第0个点选中
		// 刚开始显示时, 把当前的页面切换到MAX_VALUE/2的位置上.
		int currentItem = Integer.MAX_VALUE / 2;
		// 把currentItem减去余数, 可以切换到第一张图片
		currentItem = currentItem
				- ((Integer.MAX_VALUE / 2) % imageViewList.size());

		mViewPager.setCurrentItem(currentItem); // 设置当前选中的页面
	}

	/**
	 * 初始化 viewpager 数据
	 * 
	 * @author lixingtao
	 */
	private void initDate() {
		/**
		 * Viewpager 图片源
		 */
		imageResIDs = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d };

		imageViewList = new ArrayList<ImageView>();

		ImageView iv;
		View view;

		for (int i = 0; i < imageResIDs.length; i++) {
			iv = new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]); // 把图片设置给imageview控件
			imageViewList.add(iv);

			// 向LinearLayout中添加一个view对象, 设置背景为点的背景
			view = new View(this);
			LayoutParams params = new LayoutParams(10, 10);
			params.leftMargin = 6;
			params.rightMargin = 6;
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.point_bg);
			view.setEnabled(false); // 把点置为没选中的状态
			llPointGroup.addView(view);
		}
		/*
		 * gridView设置adapter
		 */
		gv_index.setAdapter(adapter);
		/*
		 * 设置item点击事件
		 */
		gv_index.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				/*
				 * 切换到机器人页
				 */
				case 0:
					Intent intent0 = new Intent(IndexActivity.this,
							MainActivity.class);
					intent0.putExtra("currentItem", R.id.rb_robot);
					startActivity(intent0);
					break;
				/*
				 * 切换到我的项目页
				 */
				case 1:
					Intent intent1 = new Intent(IndexActivity.this,
							MainActivity.class);
					intent1.putExtra("currentItem", R.id.rb_myproject);
					startActivity(intent1);
					break;
				/*
				 * 切换到视频会议 页
				 */
				case 2:
					Intent intent2 = new Intent(IndexActivity.this,
							MainActivity.class);
					intent2.putExtra("currentItem", R.id.rb_videosession);
					startActivity(intent2);
					break;
				case 3:
					// 切换到 服务热线 页
					// Intent intent3 = new Intent(IndexActivity.this,
					// MainActivity.class);
					// intent3.putExtra("currentItem", R.id.rb_servicetel);
					// startActivity(intent3);
					startActivity(new Intent(IndexActivity.this,
							Activity_Call.class));
					break;

				default:
					break;
				}
			}
		});
	}

	/**
	 * ViewPager 的适配器
	 * 
	 * @author lixingtao
	 * 
	 */
	class MyPagerAdapter extends PagerAdapter {

		/**
		 * 定义ViewPager的总长度的.
		 */
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * 判断是否使用缓存, 如果true, 使用缓存 arg0 就是拖动的对象 arg1 进来的对象
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 销毁对象 position 就是被销毁的对象的索引
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 删除自带的suport 否则报异常
			// 取得在集合中真正的位置.
			int newPosition = position % imageViewList.size();
			mViewPager.removeView(imageViewList.get(newPosition)); // 从viewpager中移除当前索引的对象
		}

		/**
		 * 加载item position 被加载的item的索引
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 取得在集合中真正的位置.
			int newPosition = position % imageViewList.size();

			// 首先, 向ViewPager中添加一个imageView对象
			mViewPager.addView(imageViewList.get(newPosition));

			// 还需要把当前添加的ImageView对象返回
			return imageViewList.get(newPosition);
		}
	}

	/**
	 * gridview 适配器
	 * 
	 * @author lixingtao
	 * 
	 */
	private class GVAdapter extends BaseAdapter {

		private int[] imgSrc = { R.drawable.robot, R.drawable.myproject,
				R.drawable.videosession, R.drawable.servicecall };
		private String[] name = { "机器人", "我的项目", "视频会议", "服务热线" };

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return name[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(IndexActivity.this,
						R.layout.index_gv_item, null);
			}
			ImageView iv_item_img = (ImageView) convertView
					.findViewById(R.id.iv_item_img);
			TextView tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			iv_item_img.setImageResource(imgSrc[position]);
			tv_item_name.setText(name[position]);
			return convertView;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 把循环的线程停止.
		isStop = true;
	}

	/**
	 * 当页面的滚动状态改变时回调
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	/**
	 * 当滚动时回调
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/**
	 * 当页面被选中时回调
	 */
	@Override
	public void onPageSelected(int position) {
		// System.out.println("当前选中的是: " + position);

		// 取得在集合中真正的位置.
		int newPosition = position % imageViewList.size();

		selectPosition = position % imageViewList.size();
		View view = imageViewList.get(newPosition);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(IndexActivity.this,
						"当前选中" + selectPosition + "TODO", 0).show();

			}
		});
		// 把前一个点置为未选中
		llPointGroup.getChildAt(previousPointPosition).setEnabled(false);
		// 把当前的点置为选中
		llPointGroup.getChildAt(newPosition).setEnabled(true);
		// 把当前的点用前一个点的变量记录下来
		previousPointPosition = newPosition;
	}

	/**
	 * 返回键退出应用
	 * 
	 * @author lixingtao
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			// 退出程序
			PromptManager.showExitSystem(this);
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 点击事件
	 * 
	 * @author lixingtao
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 退出按钮
		case R.id.imgbtn_right:
			// 修改首选项的登陆状态
			SharedPreferencesUtils.saveBoolean(this, "isLoged", false);
			// 启动登陆界面
			startActivity(new Intent(this, Activity_UserLogin.class));
			finish();
			break;

		default:
			break;
		}
	}
}
