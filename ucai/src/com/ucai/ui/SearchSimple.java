package com.ucai.ui;

import java.util.Calendar;
import java.util.Locale;

import com.ucai.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class SearchSimple extends Activity {

	private static final int SCITY_REQUEST_CODE = 1;
	private static final int ECITY_REQUEST_CODE = 2;
	private static final int AIRLINES_REQUEST_CODE = 3;
	private static final int DATE_DIALOG_ID = 1;
	private EditText sctiy;
	private EditText ectiy;
	private EditText sdate;
	private EditText airlines;
	private Button search;
	private Button back;
	private String scode = null;
	private String sname = null;
	private String ecode = null;
	private String ename = null;
	private String acode = null;
	private String aname = null;
	private int mYear;
	private int mMonth;
	private int mDay;

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

		sdate = (EditText) findViewById(R.id.sdate);
		sdate.setFocusable(false);
		sdate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		airlines = (EditText) findViewById(R.id.airlines);
		airlines.setFocusable(false);
		airlines.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(SearchSimple.this, SearchHangKong.class);
				startActivityForResult(i, AIRLINES_REQUEST_CODE);
			}
		});

		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(SearchSimple.this, SearchFightView.class);
				i.putExtra("startcity", scode);
				i.putExtra("endcity", ecode);
				i.putExtra("date", sdate.getText().toString());
				i.putExtra("airway", acode);
				startActivity(i);
			}
		});

		final Calendar c = Calendar.getInstance(Locale.CHINA);
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
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
				} else if (requestCode == AIRLINES_REQUEST_CODE) {
					acode = bundle.getString(SearchHangKong.CODE);
					aname = bundle.getString(SearchHangKong.NAME);
					airlines.setText(aname);
				}
			}
		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private void updateDisplay() {
		sdate.setText(new StringBuilder().append(mYear).append("-").append(
				mMonth + 1).append("-").append(mDay));

	}
}
