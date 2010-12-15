package com.ucai.ui;

import com.ucai.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchSimple extends Activity{
	private EditText sctiy;
	private String scode=null;
	private String sname=null;
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
				startActivityForResult(i,1);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		Bundle bundle =data.getExtras();
		System.out.println("----------"+requestCode);
		if(bundle!=null){
			scode=bundle.getString(SearchFightCity.CODE);
			sname=bundle.getString(SearchFightCity.NAME);
			sctiy.setText(sname);
		}
	}
}
