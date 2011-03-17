package com.ucai;

import com.ucai.ui.TicketChoose;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主程序入口类,该类通过继承Activity实现界面的功能<br>
 * 并在文件/AndroidManifest.xml的&lt;activity android:name=".Main" android:label="@string/app_name"&gt;配置项上添加了"android.intent.action.MAIN" 属性<br>
 * 从而使Main成为主程序入口,入口方法为：onCreate(Bundle)
 * @author lin
 * @see #onCreate(Bundle)
 *
 */
public class Main extends Activity {
	/**
	 * 旅游功能按键
	 */
	private Button travel;
	/**
	 * 酒店功能按键
	 */
	private Button hotel;
	/**
	 * 机票功能按键
	 */
	private Button ticket;
	/**
	 * 火车功能按键
	 */
	private Button train;
	/**
	 * 公交车按键
	 */
	private Button bus;
	/**
	 * 系统配置功能按键
	 */
	private Button config;

	/**
	 * 程序主入口方法, 该方法首先把主界面中的按键配置好他们的动作<br>
	 * 通过调用showToastCollectioned(int)方法作为提示功能。<br>
	 * 并在机票的按键中提供跳转到机票信息选择功能（TicketChoose）
	 * @see #showToastCollectioned(int)
	 * @see TicketChoose
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);// 加载主界面
		travel = (Button) findViewById(R.id.travel);// 获取控件
		travel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showToastCollectioned(Toast.LENGTH_SHORT);
			}
		});
		hotel = (Button) findViewById(R.id.hotel);
		hotel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showToastCollectioned(Toast.LENGTH_SHORT);
			}
		});
		ticket = (Button) findViewById(R.id.ticket);
		ticket.setOnClickListener(new View.OnClickListener() {// 监听机票点击，转到机票选择界面
					public void onClick(View v) {
						Intent i = new Intent(Main.this, TicketChoose.class);
						startActivity(i);
					}
				});
		train = (Button) findViewById(R.id.train);
		train.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showToastCollectioned(Toast.LENGTH_SHORT);
			}
		});
		bus = (Button) findViewById(R.id.bus);
		bus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showToastCollectioned(Toast.LENGTH_SHORT);
			}
		});
		config = (Button) findViewById(R.id.config);
		config.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showToastCollectioned(Toast.LENGTH_SHORT);
			}
		});
	}

	/**
	 * 显示提示窗口方示
	 * 
	 * @param type
	 *            显示时间类型
	 */
	protected void showToastCollectioned(int type) {
		View view = inflateView(R.layout.toast);
		TextView tv = (TextView) view.findViewById(R.id.tips);
		tv.setText("正在研发中");
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}

	/**
	 * 显示界面<br>
	 * 对指定的界面实现化
	 * 
	 * @param resource 指定界面在系统中的编号
	 * @return 实例化后的界面
	 */
	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}

	/**
	 * 捕获返回按键,使流转更规范<br>
	 * 通过配对系统的keyCode来判断是否为BACK键，如果是，从而实现流程
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 按下的如果是BACK，同时没有重复
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
		}

		return super.onKeyDown(keyCode, event);
	}
}