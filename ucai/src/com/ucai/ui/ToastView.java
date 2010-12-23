package com.ucai.ui;

import com.ucai.Main;
import com.ucai.R;
import com.ucai.po.ReturnPo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ToastView extends Activity {
	private TextView info;
	private Button button;
	private Button back;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toastview);
		ReturnPo rpo = (ReturnPo) getIntent().getExtras().get("rpo");

		info = (TextView) findViewById(R.id.info);
		if (rpo.getCode().equals("1")) {
			info.setText("订票成功，pnr码为:" + rpo.getPnr());
		} else {
			info.setText(rpo.getInfo());
		}
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(ToastView.this, Main.class);
				startActivity(i);
			}
		});
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
