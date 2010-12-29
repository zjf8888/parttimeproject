package com.ucai.ui;

import com.ucai.R;
import com.ucai.po.ReturnPo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToastView extends Activity {
	private TextView info;
	private Button button;
	private Button back;
	private Button yes;
	private Button no;
	private RelativeLayout wait;
	private RelativeLayout had;
	private String url = "http://www.ecook.cn/accuracy/tradeServlet?forderid=";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toastview);
		ReturnPo rpo = (ReturnPo) getIntent().getExtras().get("rpo");
		wait = (RelativeLayout) findViewById(R.id.wait);
		had = (RelativeLayout) findViewById(R.id.had);

		info = (TextView) findViewById(R.id.info);
		if (rpo.getCode().equals("1")) {
			info.setText("订票成功，pnr码为:" + rpo.getPnr());
		} else {
			info.setText(rpo.getInfo());
		}
		final String forderid=rpo.getForderId();
		url = url + forderid;
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				info.setText("支付成功?" );
				wait.setVisibility(View.GONE);
				had.setVisibility(View.VISIBLE);
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
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
	}
}
