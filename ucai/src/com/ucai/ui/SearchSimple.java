package com.ucai.ui;

import com.ucai.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchSimple extends Activity{
	private EditText sctiy;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchsimgle);
		sctiy = (EditText) findViewById(R.id.sctiy);
		sctiy.setFocusable(false);
		sctiy.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sctiy.setFocusable(true);
				sctiy.setFocusableInTouchMode(true);
				sctiy.requestFocus();
			}
		});
	}
}
