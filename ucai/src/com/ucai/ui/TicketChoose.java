package com.ucai.ui;

import com.ucai.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 机票信息选择入口,该类作为机票操作的菜单类，有关机票的所有操作都是在此类中首先选择
 * @author lin
 *
 */
public class TicketChoose extends Activity {
	/**
	 * 机票查询按键
	 */
	private Button ticketquery;
	/**
	 * 票务处理按键
	 */
	private Button ticketdeal;
	/**
	 * 订单查询按键
	 */
	private Button orderquery;

	/**
	 * 机票信息选择入口方法，该方法首先指定相应的界面配置文件，<br>
	 * 机票查询按键跳转到SearchTab作为机票查询，<br>
	 * 票务处理会弹出提示窗口告诉用户拨打票务处理电话时行票务处理，具体操作请查看方法dialog（）；<br>
	 * 订单查询会跳转到UserOrder作为订单查询。
	 * @see SearchTab
	 * @see #dialog()
	 * @see UserOrder
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticketchoose);
		ticketquery = (Button) findViewById(R.id.ticketquery);
		ticketquery.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(TicketChoose.this, SearchTab.class);
				startActivity(i);
			}
		});
		ticketdeal = (Button) findViewById(R.id.ticketdeal);
		ticketdeal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog();
			}
		});
		orderquery = (Button) findViewById(R.id.orderquery);
		orderquery.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(TicketChoose.this, UserOrder.class);
				startActivity(i);
			}
		});
	}

	/**
	 * 作为票务处理会弹出提示窗口
	 */
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(TicketChoose.this);
		builder.setMessage("航班的退票改签功能目前尚不能在线上完成,请拨打电话:4006840060完成改签程序");
		builder.setTitle("提示");
		builder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});
		builder.create().show();
	}
}
