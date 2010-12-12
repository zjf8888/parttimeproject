package com.ucai;

import com.ucai.ui.TicketChoose;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private Button travel;
	private Button hotel;
	private Button ticket;
	private Button train;
	private Button bus;
	private Button config;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		travel = (Button) findViewById(R.id.travel);
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
		ticket.setOnClickListener(new View.OnClickListener() {
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

	protected void showToastCollectioned(int type) {
		View view = inflateView(R.layout.toast);
		TextView tv = (TextView) view.findViewById(R.id.tips);
		tv.setText("正在研发中");
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}

	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
}