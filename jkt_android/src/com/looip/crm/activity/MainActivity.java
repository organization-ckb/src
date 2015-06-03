package com.looip.crm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.looip.crm.R;
import com.looip.crm.base.BasePager;
import com.looip.crm.pager.MyProjectPager;
import com.looip.crm.pager.RobotPager;
import com.looip.crm.pager.ServiceTelPager;
import com.looip.crm.pager.VideoSessionPager;
import com.looip.crm.view.LazyViewPager.OnPageChangeListener;
import com.looip.crm.view.MyViewPager;

/**
 * 程序主界面
 * 
 * @author lixingtao
 * 
 */
public class MainActivity extends Activity implements OnClickListener {
	// 自定义的ViewPager
	private MyViewPager myViewPager;
	// 存放basePager的集合
	private List<BasePager> pagerList = new ArrayList<BasePager>();
	// 当前选中的条目
	private int currentItem = R.id.rb_robot;
	// 单选按按钮组
	private RadioGroup radioGroup;
	// BasePager
	private BasePager basePager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 显示无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 初始化view
	 * 
	 * @author lixingtao
	 */
	private void initView() {
		// 获取intent 得到传递过来的position
		Intent intent = getIntent();
		currentItem = intent.getIntExtra("currentItem", R.id.rb_robot);

		// 获取到radiogroup和viewpager
		radioGroup = (RadioGroup) findViewById(R.id.rg_main_radio);
		myViewPager = (MyViewPager) findViewById(R.id.layout_content);

		/**
		 * 初始化底部tab页面
		 */
		pagerList.clear();
		pagerList.add(new RobotPager(this));
		pagerList.add(new MyProjectPager(this));
		pagerList.add(new VideoSessionPager(this));
		pagerList.add(new ServiceTelPager(this));
		// 设置默认选中
		setSelectPager(currentItem);
		initDate();
	}

	/**
	 * 设置选中的pager
	 * 
	 * @author lixingtao
	 * @param currentItem
	 */
	private void setSelectPager(int current) {
		// 默认选择首页变成红色
		switch (current) {
		/*
		 * 选中机器人
		 */
		case R.id.rb_robot:
			radioGroup.check(0);
			myViewPager.setCurrentItem(0);
			pagerList.get(0).initData();
			break;
		/*
		 * 选中我的项目
		 */
		case R.id.rb_myproject:
			radioGroup.check(1);
			myViewPager.setCurrentItem(1);
			break;
		/*
		 * 选中视频会议
		 */
		case R.id.rb_videosession:
			radioGroup.check(2);
			myViewPager.setCurrentItem(2);
			break;
		/*
		 * 服务热线
		 */
		case R.id.rb_servicetel:
			radioGroup.check(3);
			myViewPager.setCurrentItem(3);
			break;

		default:
			break;
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @author lixingtao
	 */
	private void initDate() {

		ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(pagerList);
		// 给MyViewPager设置适配器
		myViewPager.setAdapter(pagerAdapter);

		/**
		 * 切换底部的tab页面进行展示
		 */
		myViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				basePager = pagerList.get(position);
				basePager.initData();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		/**
		 * pager 状态改变时调用
		 * 
		 * @author lixingtao
		 */
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {

				switch (arg1) {
				// 切换到机器人pager
				case R.id.rb_robot:
					myViewPager.setCurrentItem(0, false);
					break;

				// 切换到我的项目pager
				case R.id.rb_myproject:
					myViewPager.setCurrentItem(1, false);
					break;

				// 切换到视频会议pager
				case R.id.rb_videosession:
					myViewPager.setCurrentItem(2, false);
					break;

				// 切换到服务热线pager
				case R.id.rb_servicetel:
					myViewPager.setCurrentItem(3, false);
					break;
				}

				currentItem = arg1;
			}
		});
		// 默认选择首页变成绿色
		radioGroup.check(currentItem);
	}

	/**
	 * Viewpager adapter
	 * 
	 * @author lixingtao
	 * 
	 */
	public class ViewPagerAdapter extends PagerAdapter {
		private List<BasePager> pages;

		public ViewPagerAdapter(List<BasePager> pagerList) {
			this.pages = pagerList;
		}

		// 销毁的时候调用的方法
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 从MyViewPager中移除销毁的view
			((MyViewPager) container).removeView(pages.get(position)
					.getRootView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((MyViewPager) container).addView(
					pages.get(position).getRootView(), 0);
			return pages.get(position).getRootView();
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	/**
	 * 处理点击事件
	 * 
	 * @author lixingtao
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/**
	 * 返回键的处理
	 * 
	 * @author lixingtao
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {

		// 匹配返回键
		case KeyEvent.KEYCODE_BACK:
			finish();
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

}
