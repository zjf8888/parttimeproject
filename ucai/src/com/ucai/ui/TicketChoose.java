package com.ucai.ui;

import com.ucai.Main;
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

public class TicketChoose extends Activity {
	private Button ticketquery;
	private Button ticketdeal;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticketchoose);
		ticketdeal = (Button) findViewById(R.id.ticketdeal);
		ticketdeal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog();
			}
		});
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(TicketChoose.this);
		builder.setMessage("�������Ʊ��ǩ����Ŀǰ�в������������,�벦��绰:4006840060�����Ʊ��ǩ����");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}
