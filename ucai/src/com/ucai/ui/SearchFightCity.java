package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ucai.R;
import com.ucai.tool.CityCode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchFightCity extends Activity {
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
				R.layout.list_item, new String[] { "name" },
				new int[] { R.id.content });
		citylist.setAdapter(adapter);
	}

	private void PrepareData() {
		data = new ArrayList<Map<String, String>>();
		String[][] city = CityCode.CityString;
		String[][] code = CityCode.cityMa;
		for (int j = 0; j < city.length; j++) {
			for (int i = 0; i < city[j].length; i++) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("code", code[j][i]);
				item.put("name", city[j][i]);
				data.add(item);
			}
		}
	}
}
