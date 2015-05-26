package com.looip.crm.projectinfo;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.looip.crm.R;


public class Activity_Viewpager extends Activity implements OnPageChangeListener{


	private int selectPosition;
	private ViewPager viewPager;    //图片列表的 “父控件”
	private TextView vDescription;  //文字描述列表的 “父控件”
	private LinearLayout vPoints;   //圆点列表的 “父控件”
	private String[] imageDescriptionArrays;  //用来存储文字描述的 “字符数组”
	private List<ImageView> imageViews;   // List 集合用来存储 ImageView 图片来显示到界面上
	private int previousPosition=0;  //始化的第一个圆点 “0”
	private boolean isLooper = true;
	//接到 “空消息” 就切换到下一页 ，实现滚动播放
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 每两秒钟执行一下此方法, 切换一下view

			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 去除标题
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_viewpager);


		init();

		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() {
				while(isLooper){

					SystemClock.sleep(3000);					
					// 切换ViewPager
					handler.sendEmptyMessage(0);					

				}



			}});           
		thread.start();

	}



	private void init() {
		//初始化布局
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		vDescription = (TextView) findViewById(R.id.tv_description);
		vPoints = (LinearLayout) findViewById(R.id.ll_points);
		int[] imageResIDs=getImageResIDs();   //用来存储图片的 id
		imageDescriptionArrays = getImageDescriptionArray();  //用来存储文字描述的 “字符数组”
		//我们需要定义一个 List 集合用来存储 ImageView 图片来显示到界面上
		imageViews = new ArrayList<ImageView>();

		ImageView iv;
		View view;
		LayoutParams lp;

		for(int i=0;i<imageDescriptionArrays.length;i++){
			// 创建一个ImageView, 并且添加到集合中
			iv=new ImageView(this);
			iv.setBackgroundResource(imageResIDs[i]);   //将图片 id 数组中对应的图片作为 iv 的背景资源
			imageViews.add(iv);   //将 iv 添加到  imageViews 集合中

			// 把对应的圆点  ， 添加到线性布局中
			view=new View(this);
			lp=new LayoutParams(14, 14);  //把 LayoutParams 属性设置给 lp
			//Ctrl+Shift+o 导入：import android.widget.LinearLayout.LayoutParams;
			lp.leftMargin=5; //设置 “圆点相隔的间距” 为 5dip
			lp.rightMargin=5;
			view.setLayoutParams(lp);  //将 view 对象的宽高都设置为 10 ，这样圆角半径为 5 的四个角拼起来正好是一个圆

			view.setBackgroundResource(R.drawable.point_background); //设置圆点 view 的背景布局

			view.setEnabled(false);  //将  view 设置为 false 普通圆点 ，“灰暗”
			vPoints.addView(view);
		}

		//将数据填充到  viewPager 中
		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(this);

		// 将 int 最大值除以2 ，然后再 “模” 图片数组长度 ，这样得到的余数就是循环播放的页码
		int m = (Integer.MAX_VALUE / 2) % imageViews.size();
		int item = (Integer.MAX_VALUE / 2) - m;
		viewPager.setCurrentItem(item);

		//初始化要显示在界面的  “图片” ，“文字” ，“圆点”
		vDescription.setText(imageDescriptionArrays[0]); //第一串文字
		vPoints.getChildAt(0).setEnabled(true);  //获取圆点集合  vPoints 中的第一个圆点为 “亮起” 
	}

	//创建一个  PagerAdapter 来对数据进行筛选 .
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/** ViewPager 有一个 “预加载” 功能 ，会预加载 “左右两边” 的图片
		 *  当前显示的是 arg0 ，预加载的是下一个 view 对象： arg1 ，如果arg0滑动到一半又重新跑回原来的位置 ，
		 *  那么 arg0=arg1 ，那么我们就 “复用”这个 view 对象
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		//重写   PagerAdapter 中的  destroyItem 销毁方法 和  instantiateItem 初始化方法
		/**根据  isViewFromObject 加载完 “左，中，右” 三个 view 对象后 ，将用过的 view 的 position 传过来 ， 销毁掉该 view
		 * ☆注意：destroyItem 方法的父类是抛出一个异常 ，所以我们应该不适用被注释的那句 super
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//			super.destroyItem(container, position, object);
			//获取传递过来的 position ，使用 container 对象去销毁 ViewGroup 中的  ImageView.
			container.removeView(imageViews.get(position%imageViews.size()));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//将  “预加载” 的 view 对象添加到  ViewGroup 中 .
			container.addView(imageViews.get(position%imageViews.size()));
			return imageViews.get(position%imageViews.size());  //添加之后还需要返回
		}

	}

	//定义  “滚动图片” 的数组 ，来存储图片的 id
	private int[] getImageResIDs() {
		return new int[] {
				R.drawable.mm,
				R.drawable.nn,
				R.drawable.mm,
				R.drawable.nn

		};
	}

	//定义 “图片说明” 的数组 ，来存储描述的 字符串
	private String[] getImageDescriptionArray() {
		return new String[] {
				"",
				"",
				"",
				""

		};
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	/**当 “图片切换选择” 的时候回调 ，图片切换时同时改变下面的文字描述跟圆点
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
				//Toast.makeText(Activity_ProjectInfo.this, selectPosition + "", 0).show();
				Intent intent=new Intent();
				intent.setClass(Activity_Viewpager.this,Activity_Photo.class);
				startActivity(intent);

			}
		});
		vPoints.getChildAt(previousPosition).setEnabled(false); // 把前一个点置为未选中
		vPoints.getChildAt(newPosition).setEnabled(true);// 把当前的点置为选中

		previousPosition = newPosition; // 把当前的点用前一个点的变量记录下来
	}

}
