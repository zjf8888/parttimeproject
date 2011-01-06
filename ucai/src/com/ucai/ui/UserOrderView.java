package com.ucai.ui;

import com.ucai.R;
import com.ucai.po.ResultOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserOrderView extends Activity{
	private TextView TextView01;
	private TextView TextView02;
	private TextView TextView03;
	private TextView TextView04;
	private TextView TextView05;
	private TextView TextView06;
	private TextView TextView07;
	private Button Button01;
	private ProgressDialog progressDialog = null;
	private ResultOrder order = null;
	private Handler handler = new Handler();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderview);
		order = (ResultOrder) getIntent().getExtras().get("resultOrder");
		Button01 = (Button) findViewById(R.id.Button01);
		Button01.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		doSearch();
	}

	/**
	 * 第一次进入该类时调用的查询方法
	 */
	private void doSearch() {
		progressDialog = ProgressDialog.show(UserOrderView.this, "请稍等...",
				"获取查询内容...", true);
		new Thread() {
			public void run() {
				try {
					updateView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				progressDialog.dismiss();
			}
		}.start();
	}

	
	/**
	 * 更新界面
	 */
	private void updateView() {
		handler.post(new Runnable() {
			public void run() {
				try {
					setView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void setView() {
		TextView01 = (TextView) findViewById(R.id.TextView01);
		TextView02 = (TextView) findViewById(R.id.TextView02);
		TextView03 = (TextView) findViewById(R.id.TextView03);
		TextView04 = (TextView) findViewById(R.id.TextView04);
		TextView05 = (TextView) findViewById(R.id.TextView05);
		TextView06 = (TextView) findViewById(R.id.TextView06);
		TextView07 = (TextView) findViewById(R.id.TextView07);		

		System.out.println(order);
		System.out.println(order.getA_FlyNo());
		TextView01.setText("航班号:" + order.getA_FlyNo());
		TextView02.setText("乘客姓名:" + order.getP_Name());
		TextView03.setText("出发城市:" + order.getA_Scity());
		TextView04.setText("目标城市:" + order.getA_Ecity());
		TextView05.setText("起飞日期:" + order.getA_FlyDate().split(" ")[0]);
		TextView06.setText("订单总价:" + order.getTotalPrice());
		TextView07.setText("支付状态:"
				+ (order.getF_PayStatus().equals("1") ? "已支付" : "未支付"));
	}
}
