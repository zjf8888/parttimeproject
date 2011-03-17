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

/**
 * 用户订单具体信息界面
 * 
 * @author lin
 * 
 */
public class UserOrderView extends Activity {
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
	 * 返回按键，实则关闭本界面
	 */
	private Button Button01;
	/**
	 * 进程提示框
	 */
	private ProgressDialog progressDialog = null;
	/**
	 * 订单查询结果
	 */
	private ResultOrder order = null;
	/**
	 * 进程通道
	 */
	private Handler handler = new Handler();

	/**
	 * 入口方法，同时为按键注册点击监听，点击显示的内容通过doSearch（）完成
	 * @see #doSearch()
	 */
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
	 * 第一次进入该类时调用的查询方法，更新界面的方法主要通过updateView（）完成
	 * @see #updateView()
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
	 * 更新界面,建立通道，并调用setView（）方法完成
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
	 * 更新界面操作，主要是对TextView根据数据更新
	 */
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
