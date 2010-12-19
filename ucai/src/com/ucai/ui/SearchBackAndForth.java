package com.ucai.ui;

import com.ucai.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchBackAndForth extends Activity {

	private Button search;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchbackandforth);
		search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showToastCollectioned(Toast.LENGTH_SHORT, "正在研发中");
			}
		});
	}

	protected void showToastCollectioned(int type, String message) {
		View view = inflateView(R.layout.toast);
		TextView tv = (TextView) view.findViewById(R.id.tips);
		tv.setText(message);
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
