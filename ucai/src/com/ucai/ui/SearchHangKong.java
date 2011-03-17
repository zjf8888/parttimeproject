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
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 选择相应航空公司的类
 * 
 * @author lin
 * 
 */
public class SearchHangKong extends Activity {
	/**
	 * 航空公司名字的关键字
	 */
	public static final String NAME = "name";
	/**
	 * 航空公司代码的关键字
	 */
	public static final String CODE = "code";
	/**
	 * 航空公司列表
	 */
	private ListView hangKonglist = null;
	/**
	 * 查询框
	 */
	private EditText searchHangKongtext = null;
	/**
	 * 数据映射
	 */
	private ArrayList<Map<String, String>> data = null;

	Handler mHandler = new Handler();

	/** 
	 * 查询航空公司的入口方法，该方法为初始化航空公司列表（PrepareData），更新查询界面（setView）。
	 * @see #PrepareData()
	 * @see #setView()
	 */
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

	/**
	 * 初始化界相<br>
	 * 主要包括（1）初始化航空公司列表的适配器；（2）配置列表监听器
	 * 
	 */
	private void setView() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { NAME },
				new int[] { R.id.content });
		hangKonglist.setAdapter(adapter);
		hangKonglist.setOnItemClickListener(listListener);
	}

	/**
	 * 设置数据，其中航空公司名字作CityCode.hangKongName获取，航空公司代码从CityCode.hangKongCode获取
	 * @see CityCode#hangKongName
	 * @see CityCode#hangKongCode
	 */
	private void PrepareData() {
		data = new ArrayList<Map<String, String>>();
		String[] hangKong = CityCode.hangKongName;
		String[] code = CityCode.hangKongCode;
		for (int i = 0; i < hangKong.length; i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put(CODE, code[i]);
			item.put(NAME, hangKong[i] + "航空");
			data.add(item);
		}

	}

	/**
	 * 捕获返回按键,使流转更规范
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 按下的如果是BACK，同时没有重复
			Intent i = new Intent();
			Bundle bundle = new Bundle();
			String code = "";
			String name = "";
			bundle.putString(CODE, code);
			bundle.putString(NAME, name);
			i.putExtras(bundle);
			setResult(RESULT_OK, i);
			finish();
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 相应的列表单击监听,当点击相应的航空公司时，便把航空公司代码和航空公司名称返回给SearchSimple
	 * @see SearchSimple
	 */
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
	/**
	 * 对文本框内容变化的监听,主要是对afterTextChanged配置更新界面方法searchName
	 * @see #searchName()
	 */
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

	/**
	 * 搜索相应内容,当搜索框内的内容变化时，调用的方法，更新航空公司列表，使选择更容易。
	 */
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
				item.put(NAME, hangKongName[i] + "航空");
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
