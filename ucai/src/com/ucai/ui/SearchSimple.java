package com.ucai.ui;

import com.ucai.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchSimple extends Activity {

	private static final int SCITY_REQUEST_CODE = 1;
	private static final int ECITY_REQUEST_CODE = 2;
	private EditText sctiy;
	private EditText ectiy;
	private String scode = null;
	private String sname = null;
	private String ecode = null;
	private String ename = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchsimgle);
		sctiy = (EditText) findViewById(R.id.sctiy);
		sctiy.setFocusable(false);
		sctiy.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(SearchSimple.this, SearchFightCity.class);
				startActivityForResult(i, SCITY_REQUEST_CODE);
			}
		});

		ectiy = (EditText) findViewById(R.id.ectiy);
		ectiy.setFocusable(false);
		ectiy.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(SearchSimple.this, SearchFightCity.class);
				startActivityForResult(i, ECITY_REQUEST_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle bundle = data.getExtras();
		System.out.println("----------" + requestCode);
		if (bundle != null) {
			if (requestCode == SCITY_REQUEST_CODE) {
				scode = bundle.getString(SearchFightCity.CODE);
				sname = bundle.getString(SearchFightCity.NAME);
				sctiy.setText(sname);
			} else if (requestCode == ECITY_REQUEST_CODE) {
				ecode = bundle.getString(SearchFightCity.CODE);
				ename = bundle.getString(SearchFightCity.NAME);
				ectiy.setText(ename);
			}
		}
	}
}
