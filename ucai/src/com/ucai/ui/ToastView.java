package com.ucai.ui;

import com.ucai.R;
import com.ucai.po.ReturnPo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 支付跳转界面，显示支付状态或者提交进入支付界面
 * 
 * @author lin
 * 
 */
public class ToastView extends Activity {
	/**
	 * 支付信息
	 */
	private TextView info;
	/**
	 * 支付宝支付按键
	 */
	private Button button;
	/**
	 * 返回按键
	 */
	private Button back;
	/**
	 * 已支付按键
	 */
	private Button yes;
	/**
	 * 未支付按键
	 */
	private Button no;
	/**
	 * 等待支付界面
	 */
	private RelativeLayout wait;
	/**
	 * 已支付界面
	 */
	private RelativeLayout had;
	/**
	 * 提交支付连接
	 */
	private String url = "http://www.ecook.cn/accuracy/tradeServlet?forderid=";
	/**
	 * 进程运行窗口
	 */
	private ProgressDialog mProgress = null;
	/**
	 * 支付界面入口方法，在此界面中，当用户点击支付宝支付后，界面便会询问用户是否已经<br>
	 * 支付？点击是时，程序便会连接到服务器查询该订单是否已经支付
	 */ 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toastview);
		final ReturnPo rpo = (ReturnPo) getIntent().getExtras().get("rpo");
		wait = (RelativeLayout) findViewById(R.id.wait);
		had = (RelativeLayout) findViewById(R.id.had);

		info = (TextView) findViewById(R.id.info);

		final String forderid = rpo.getForderId();
		url = url + forderid;
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doSubmit();
			}

		});
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		yes = (Button) findViewById(R.id.yes);
		yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(ToastView.this, OrderView.class);
				i.putExtra("forderid", forderid);
				startActivity(i);
			}
		});
		no = (Button) findViewById(R.id.no);
		no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				wait.setVisibility(View.VISIBLE);
				had.setVisibility(View.GONE);
			}
		});
		if (rpo.getCode().equals("1")) {
			doSubmit();
		} else {
			info.setText(rpo.getInfo());
		}
	}
	/**
	 * 点击支付宝支付后，界面便会变成“支付成功？”，并跳转到支付宝支付页面
	 */
	private void doSubmit() {
		info.setText("支付成功?");
		wait.setVisibility(View.GONE);
		had.setVisibility(View.VISIBLE);
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	/**
	 * 关闭进程
	 */
	// close the progress bar
	void closeProgress() {
		try {
			if (mProgress != null) {
				mProgress.dismiss();
				mProgress = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
