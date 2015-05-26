package com.looip.crm.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.looip.crm.base.BasePager;

/**
 * 视频会议 界面
 * 
 * @author lixingtao
 * 
 */
public class VideoSessionPager extends BasePager {

	private TextView tv;

	public VideoSessionPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		tv = new TextView(ct);

		return tv;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		tv.setText("视频会议");
	}

}
