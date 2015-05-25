package com.looip.crm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import com.looip.crm.R;
import com.looip.crm.utils.PromptManager;
import com.looip.crm.utils.SharedPreferencesUtils;

/**
 * 欢迎界面
 * 
 * @author lixingtao
 * 
 */
public class SplashActivity extends Activity {
	// 是否登陆过
	private boolean isLoged;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 设置无标题显示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		// 判断网络状态
		if (!PromptManager.isConn(this)) {
			Toast.makeText(this, "当前网络不可用", 0).show();
		} else {
			Toast.makeText(this, "网络联接成功", 0).show();
		}
		// 获取登陆状态
		isLoged = SharedPreferencesUtils.getBoolean(this, "isLoged", false);
		new Handler() {
			public void handleMessage(android.os.Message msg) {

				/**
				 * 1 判断当前用户是否登陆了app，如果登陆过跳转到主界面，否则跳转到登陆界面
				 * @author lixingtao
				 */

				if (isLoged) {
					startActivity(new Intent(SplashActivity.this,
							IndexActivity.class));
					finish();
				} else {
					startActivity(new Intent(SplashActivity.this,
							Activity_UserLogin.class));
					finish();
				}
			};
		}.sendEmptyMessageDelayed(0, 2000);
	}

}
