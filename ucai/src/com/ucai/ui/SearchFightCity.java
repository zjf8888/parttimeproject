package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ucai.R;
import com.ucai.tool.CityCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SearchFightCity extends Activity {
	public static final String NAME = "name";
	public static final String CODE = "code";
	private ListView citylist = null;
	private ArrayList<Map<String, String>> data = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchfightcity);
		citylist = (ListView) findViewById(R.id.citylist);
		PrepareData();
		setView();
	}

	private void setView() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { NAME },
				new int[] { R.id.content });
		citylist.setAdapter(adapter);
		citylist.setOnItemClickListener(listListener);
	}

	private void PrepareData() {
		data = new ArrayList<Map<String, String>>();
		String[][] city = CityCode.CityString;
		String[][] code = CityCode.cityMa;
		for (int j = 0; j < city.length; j++) {
			for (int i = 0; i < city[j].length; i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put(CODE, code[j][i]);
				item.put(NAME, city[j][i]);

				data.add(item);
			}
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
}
