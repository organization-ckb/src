package com.looip.crm.projectinfo;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.looip.crm.R;

public class Activity_Photo extends Activity{
	private ImageView iv_photo1,iv_photo2,iv_photo3,iv_photo4;
	private Button bt_toleft,bt_toright;
	ViewPager pager = null;
	ArrayList<View> viewContainter = new ArrayList<View>();
	ArrayList<String> titleContainer = new ArrayList<String>();
	public String TAG = "tag";

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_project_photo);

		/**
		 * 得到viewpager布局,得到主页面左右按钮
		 */
		pager = (ViewPager) this.findViewById(R.id.viewpager);
		bt_toleft=(Button) findViewById(R.id.bt_toleft);
		bt_toright=(Button) findViewById(R.id.bt_toright);
		bt_toleft.setVisibility(View.INVISIBLE);
		/**
		 * 得到翻页的4个布局
		 */
		View view1 = LayoutInflater.from(this).inflate(R.layout.project_photo1, null);
		View view2 = LayoutInflater.from(this).inflate(R.layout.project_photo2, null);
		View view3 = LayoutInflater.from(this).inflate(R.layout.project_photo3, null);
		View view4 = LayoutInflater.from(this).inflate(R.layout.project_photo4, null);
		/**
		 * 声明4个布局中显示图片的控件
		 */

		iv_photo1=(ImageView) view1.findViewById(R.id.iv_photo1);
		iv_photo2=(ImageView) view2.findViewById(R.id.iv_photo2);
		iv_photo3=(ImageView) view3.findViewById(R.id.iv_photo3);
		iv_photo4=(ImageView) view4.findViewById(R.id.iv_photo4);

		/**
		 * 动态设置显示图片
		 */
		BitmapUtils bitmapUtils=new BitmapUtils(view1.getContext());
		bitmapUtils.display(iv_photo1,"assets/ic_launcher.png");
		bitmapUtils.display(iv_photo2,"assets/img1.jpg");
		bitmapUtils.display(iv_photo3,"assets/img2.jpg");
		bitmapUtils.display(iv_photo4,"assets/img3.jpg");

		//viewpager开始添加view
		viewContainter.add(view1);
		viewContainter.add(view2);
		viewContainter.add(view3);
		viewContainter.add(view4);
		bt_toleft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int p=pager.getCurrentItem();
				pager.setCurrentItem(p-1);
				if(p!=1){
					bt_toright.setVisibility(View.VISIBLE);
					bt_toleft.setVisibility(View.VISIBLE);
				}else{
					bt_toleft.setVisibility(View.INVISIBLE);
					bt_toright.setVisibility(View.VISIBLE);
				}
				//Toast.makeText(MainActivity.this,"ss", Toast.LENGTH_SHORT).show();
			}
		});
		bt_toright.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int p=pager.getCurrentItem();
				pager.setCurrentItem(p+1);
				if(p!=2){
					bt_toright.setVisibility(View.VISIBLE);
					bt_toleft.setVisibility(View.VISIBLE);
				}else{
					bt_toleft.setVisibility(View.VISIBLE);
					bt_toright.setVisibility(View.INVISIBLE);
				}
				//Toast.makeText(MainActivity.this,"ss", Toast.LENGTH_SHORT).show();
			}
		});

		pager.setAdapter(new PagerAdapter() {

			//viewpager中的组件数量
			@Override
			public int getCount() {
				return viewContainter.size();
			}
			//滑动切换的时候销毁当前的组件
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				((ViewPager) container).removeView(viewContainter.get(position));

			}
			//每次滑动的时候生成的组件
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				((ViewPager) container).addView(viewContainter.get(position));

				return viewContainter.get(position);

			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getItemPosition(Object object) {
				return super.getItemPosition(object);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titleContainer.get(position);
			}
		});

		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.d(TAG, "--------changed:" + arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.d(TAG, "-------scrolled arg0:" + arg0);
				Log.d(TAG, "-------scrolled arg1:" + arg1);
				Log.d(TAG, "-------scrolled arg2:" + arg2);
			}

			@Override
			public void onPageSelected(int position) {
				//Toast.makeText(Activity_Photo.this,position+"", Toast.LENGTH_SHORT).show();			}
				if(position==0){
					bt_toleft.setVisibility(View.INVISIBLE);	

				}else{
					bt_toleft.setVisibility(View.VISIBLE);

				}

				if(position==3){
					bt_toright.setVisibility(View.INVISIBLE);
				}else {
					bt_toright.setVisibility(View.VISIBLE);
				}

			}

		});

	}

}
