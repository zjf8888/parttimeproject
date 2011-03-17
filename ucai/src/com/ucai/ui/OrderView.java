package com.ucai.ui;

import com.ucai.Main;
import com.ucai.R;
import com.ucai.api.OrderApi;
import com.ucai.po.ResultOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 订单显示界面,把具体订单的信息显示出来。
 * 
 * @author lin
 * 
 */
public class OrderView extends Activity {
	/**
	 * 航班号
	 */
	private TextView TextView01;
	/**
	 * 乘客姓名
	 */
	private TextView TextView02;
	/**
	 * 出发城市
	 */
	private TextView TextView03;
	/**
	 * 目标城市
	 */
	private TextView TextView04;
	/**
	 * 起飞日期
	 */
	private TextView TextView05;
	/**
	 * 订单总价
	 */
	private TextView TextView06;
	/**
	 * 支付状态
	 */
	private TextView TextView07;
	/**
	 * 确定按键
	 */
	private Button Button01;
	/**
	 * 进程窗口
	 */
	private ProgressDialog progressDialog = null;
	/**
	 * 订单查询结果
	 */
	private ResultOrder order = null;
	/**
	 * 订单ID
	 */
	private String forderid;
	/**
	 * 进程通道
	 */
	private Handler handler = new Handler();

	/**
	 * 订单显示的入口方法，从Intent中获取订单id forderid，然后进行相应的查询操作doSearch。
	 * @see #doSearch()
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderview);
		forderid = (String) getIntent().getExtras().get("forderid");
		Button01 = (Button) findViewById(R.id.Button01);
		Button01.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(OrderView.this, Main.class);
				startActivity(i);
			}
		});
		doSearch();
	}

	/**
	 * 具体的查询更新界面操作，主要分两部分，1是获取需显示的数据调用方法setDate，2是更新显示界面调用方法updateView
	 * @see #setDate()
	 * @see #updateView()
	 */
	private void doSearch() {
		progressDialog = ProgressDialog.show(OrderView.this, "请稍等...",
				"获取查询内容...", true);
		new Thread() {
			public void run() {
				try {
					setDate();
					updateView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				progressDialog.dismiss();
			}
		}.start();
	}

	/**
	 * 设置数据,主要是通过OrderApi调用远程方法
	 * @see OrderApi#setSeat(String)
	 */
	private void setDate() {
		OrderApi api = new OrderApi();
		order = api.setSeat(forderid);
	}

	/**
	 * 更新界面,通过在handler中更新，具体操作是调用方法setView
	 * @see #setView()
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

	/**
	 * 设置界面需要更新的内容包括：航班号、乘客姓名、出发城市、目标城市、起飞日期、订单总价、支付状态
	 */
	private void setView() {
		System.out.println(forderid);
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
