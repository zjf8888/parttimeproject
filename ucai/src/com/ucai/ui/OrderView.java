package com.ucai.ui;

import com.ucai.Main;
import com.ucai.R;
import com.ucai.api.OrderApi;
import com.ucai.po.ResultOrder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderView extends Activity {
	private TextView TextView01;
	private TextView TextView02;
	private TextView TextView03;
	private TextView TextView04;
	private TextView TextView05;
	private TextView TextView06;
	private TextView TextView07;
	private Button Button01;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderview);
		String forderid = (String) getIntent().getExtras().get("forderid");
		System.out.println(forderid);
		TextView01 = (TextView) findViewById(R.id.TextView01);
		TextView02 = (TextView) findViewById(R.id.TextView02);
		TextView03 = (TextView) findViewById(R.id.TextView03);
		TextView04 = (TextView) findViewById(R.id.TextView04);
		TextView05 = (TextView) findViewById(R.id.TextView05);
		TextView06 = (TextView) findViewById(R.id.TextView06);
		TextView07 = (TextView) findViewById(R.id.TextView07);
		Button01 = (Button) findViewById(R.id.Button01);
		OrderApi api = new OrderApi();
		ResultOrder order = api.setSeat(forderid);
		System.out.println(order);
		System.out.println(order.getA_FlyNo());
		TextView01.setText("航班号:" + order.getA_FlyNo());
		TextView02.setText("乘客姓名:" + order.getP_Name());
		TextView03.setText("出发城市:" + order.getA_Scity());
		TextView04.setText("目标城市:" + order.getA_Ecity());
		TextView05.setText("起飞日期:" + order.getA_FlyDate());
		TextView06.setText("订单总价:" + order.getTotalPrice());
		TextView07.setText("支付状态:"
				+ (order.getF_PayStatus().equals("1") ? "已支付" : "未支付"));
		Button01.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(OrderView.this, Main.class);
				startActivity(i);
			}
		});
	}
}
