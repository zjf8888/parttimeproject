package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ucai.R;
import com.ucai.tool.CityCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SearchHangKong extends Activity {
	public static final String NAME = "name";
	public static final String CODE = "code";
	private ListView hangKonglist = null;
	private EditText searchHangKongtext = null;
	private ArrayList<Map<String, String>> data = null;

	Handler mHandler = new Handler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchfightcity);

		hangKonglist = (ListView) findViewById(R.id.citylist);
		searchHangKongtext = (EditText) findViewById(R.id.searchfightcitytext);
		searchHangKongtext.setFocusable(false);
		searchHangKongtext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				searchHangKongtext.setFocusable(true);
				searchHangKongtext.setFocusableInTouchMode(true);
				searchHangKongtext.requestFocus();
			}
		});
		searchHangKongtext.addTextChangedListener(textWatcher);
		PrepareData();
		setView();
	}

	private void setView() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { NAME },
				new int[] { R.id.content });
		hangKonglist.setAdapter(adapter);
		hangKonglist.setOnItemClickListener(listListener);
	}

	private void PrepareData() {
		data = new ArrayList<Map<String, String>>();
		String[] hangKong = CityCode.hangKongName;
		String[] code = CityCode.hangKongCode;
		for (int i = 0; i < hangKong.length; i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put(CODE, code[i]);
			item.put(NAME, hangKong[i]+"航空");
			data.add(item);
		}

	}

	private OnItemClickListener listListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent i = new Intent();
			Bundle bundle = new Bundle();
			Map<String, String> map = data.get(position);
			String code = map.get(CODE);
			String name = map.get(NAME);
			bundle.putString(CODE, code);
			bundle.putString(NAME, name);
			i.putExtras(bundle);
			setResult(RESULT_OK, i);
			finish();
		}
	};

	TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			searchName();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
		}
	};

	private void searchName() {
		String hangKongCode[] = CityCode.hangKongCode;
		String hangKongName[] = CityCode.hangKongName;
		String s = searchHangKongtext.getText().toString();
		data = new ArrayList<Map<String, String>>();

		for (int i = 0; i < hangKongName.length; i++) {
			String city = hangKongName[i];
			if (city.indexOf(s) != -1) {
				Map<String, String> item = new HashMap<String, String>();
				item.put(CODE, hangKongCode[i]);
				item.put(NAME, hangKongName[i]+"航空");
				data.add(item);
			}
		}

		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { NAME },
				new int[] { R.id.content });
		hangKonglist.setAdapter(adapter);
		hangKonglist.setOnItemClickListener(listListener);
	}
}
