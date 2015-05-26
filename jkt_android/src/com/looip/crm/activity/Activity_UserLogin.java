/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.looip.crm.activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.looip.crm.R;
import com.looip.crm.bean.LoginResult;
import com.looip.crm.utils.SharedPreferencesUtils;

public class Activity_UserLogin extends Activity {
	private static final int DIALOG_TEXT_ENTRY = 1;

	private static final int MAX_PROGRESS = 100;

	private ProgressDialog mProgressDialog;
	private int mProgress;
	private Handler mProgressHandler;
	View mView;
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	@ViewInject(R.id.et_userlogin_name)
	private EditText et_userlogin_name;

	@ViewInject(R.id.et_userlogin_password)
	private EditText et_userlogin_password;

	@ViewInject(R.id.bt_userlogin_login)
	private Button bt_userlogin_login;

	@ViewInject(R.id.tv_userlogin_forgetpassword)
	private TextView tv_userlogin_forgetpassword;

	@SuppressLint("InflateParams")
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_TEXT_ENTRY:
			// This example shows how to add a custom layout to an AlertDialog
			LayoutInflater factory = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			// LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(
					R.layout.dialog_forgetpassword, null);
			// AlertDialog ad=new AlertDialog.Builder(AlertDialogSamples.this)
			final Dialog ad = new Dialog(this, R.style.Theme_CustomDialog2);
			ad.setContentView(textEntryView);
			TextView tv_login_sure = (TextView) textEntryView
					.findViewById(R.id.tv_login_sure);
			TextView tv_login_cancel = (TextView) textEntryView
					.findViewById(R.id.tv_login_cancel);
			/**
			 * 自定义dialog取消按钮事件
			 */
			tv_login_cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ad.dismiss();
				}
			});
			/**
			 * 自定义dialog确认按钮事件
			 */
			tv_login_sure.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			return ad;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint({ "HandlerLeak", "WorldReadableFiles" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 去除标题
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_userlogin);
		/**
		 * 注入Activity
		 */
		ViewUtils.inject(this);
		/**
		 * 获得sharedpreferences对象
		 */
		preferences = getSharedPreferences("userinfo", MODE_WORLD_READABLE);
		editor = preferences.edit();
		/**
		 * 取出用户名和密码
		 */
		String getname = preferences.getString("loginName", null);
		String getpassword = preferences.getString("loginPwd", null);
		/**
		 * 为输入框赋值
		 */
		et_userlogin_name.setText(getname);
		et_userlogin_password.setText(getpassword);

		/**
		 * 忘记密码弹出dialog
		 */
		tv_userlogin_forgetpassword.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_TEXT_ENTRY);
			}
		});

		mProgressHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (mProgress >= MAX_PROGRESS) {
					mProgressDialog.dismiss();
				} else {
					mProgress++;
					mProgressDialog.incrementProgressBy(1);
					mProgressHandler.sendEmptyMessageDelayed(0, 100);
				}
			}
		};
	}

	/**
	 * 登陆
	 */
	@OnClick(R.id.bt_userlogin_login)
	public void login(View v) {

		/**
		 * 请求服务器判断登陆结果成功
		 * 
		 * 注意：修改sp中的登陆状态
		 */
		SharedPreferencesUtils.saveBoolean(this, "isLoged", true);
		LoginSuccess();
	}

	/**
	 * 发送网络请求验证登陆
	 */
	public void LoginSuccess(){
		/**
		 * 取出用户名和密码
		 */
		final String loginName = et_userlogin_name.getText().toString().trim();
		final String loginPwd = et_userlogin_password.getText().toString().trim();
		HttpUtils httpUtils=new HttpUtils();
		RequestParams params=new RequestParams("utf8");
		httpUtils.configCurrentHttpCacheExpiry(1000 * 10);  

		params.addBodyParameter("loginName", loginName);
		params.addBodyParameter("loginPwd",loginPwd);

		String m="?loginName="+loginName+"&loginPwd="+loginPwd;
		httpUtils.send(HttpRequest.HttpMethod.GET,"http://crm.test.looip.com/wap/login"+m,new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(Activity_UserLogin.this, "网络请求失败", Toast.LENGTH_SHORT).show();	
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String responseInfo=arg0.result;
				Gson gson = new Gson();// gson 对象
				LoginResult json = gson.fromJson(responseInfo, LoginResult.class);

				if (json.resultcode==200) {
					if(json.results.equals("1")){
						/**
						 * 如果选中记住密码，则将密码保存
						 */

						editor.putString("loginName", loginName);
						editor.putString("loginPwd", loginPwd);
						editor.putInt("id", json.id);
						editor.commit();
						Intent intent=new Intent();
						intent.setClass(Activity_UserLogin.this, IndexActivity.class);
						startActivity(intent);
						Activity_UserLogin.this.finish();
					}else {
						Toast.makeText(Activity_UserLogin.this, "登陆失败", Toast.LENGTH_SHORT).show();
					}

				}

			}

		}); 
	}

}
