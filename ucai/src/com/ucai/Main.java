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
 * 主程序入口类
 * @author lin
 *
 */
public class Main extends Activity {
	private Button travel;
	private Button hotel;
	private Button ticket;
	private Button train;
	private Button bus;
	private Button config;

	/**
	 * 程序主入口方法
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
	 * 显示界面
	 * 
	 * @param resource
	 * @return
	 */
	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}

	/**
	 * 捕获返回按键,使流转更规范
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