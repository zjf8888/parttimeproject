package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ucai.R;
import com.ucai.tool.CityCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 查询不同城市的界面
 * 
 * @author lin
 * 
 */
public class SearchFightCity extends Activity {
	public static final String NAME = "name";
	public static final String CODE = "code";
	private ListView citylist = null;
	private EditText searchfightcitytext = null;
	private ArrayList<Map<String, String>> data = null;

	private RemoveWindow mRemoveWindow = new RemoveWindow();
	Handler mHandler = new Handler();
	private WindowManager mWindowManager;
	private TextView mDialogText;
	private boolean mShowing;
	private boolean mReady;
	private String mPrevLetter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchfightcity);

		citylist = (ListView) findViewById(R.id.citylist);
		searchfightcitytext = (EditText) findViewById(R.id.searchfightcitytext);
		searchfightcitytext.setFocusable(false);
		searchfightcitytext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				searchfightcitytext.setFocusable(true);
				searchfightcitytext.setFocusableInTouchMode(true);
				searchfightcitytext.requestFocus();
			}
		});
		searchfightcitytext.addTextChangedListener(textWatcher);

		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		citylist.setOnScrollListener(scrollListener);

		LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mDialogText = (TextView) inflate.inflate(R.layout.list_position, null);
		mDialogText.setVisibility(View.INVISIBLE);

		mHandler.post(new Runnable() {

			public void run() {
				mReady = true;
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT,
						WindowManager.LayoutParams.TYPE_APPLICATION,
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
								| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
						PixelFormat.TRANSLUCENT);
				mWindowManager.addView(mDialogText, lp);
			}
		});

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

	/**
	 * 数据封装
	 */
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

	/**
	 * 点击选择时监听
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
	 * 内部类，对移动时移除小窗口
	 * 
	 * @author lin
	 * 
	 */
	private final class RemoveWindow implements Runnable {
		public void run() {
			removeWindow();
		}
	}

	/**
	 * 设置标志位，隐藏字母显示
	 */
	private void removeWindow() {
		if (mShowing) {
			mShowing = false;
			mDialogText.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mReady = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		removeWindow();
		mReady = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWindowManager.removeView(mDialogText);
		mReady = false;
	}

	/**
	 * 列表移动的监听，根据到达相应的位置，显示相应的字母
	 */
	ListView.OnScrollListener scrollListener = new ListView.OnScrollListener() {
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			String s = searchfightcitytext.getText().toString();
			if (data != null && s.trim().length() < 1) {
				Map<String, String> map = data.get(firstVisibleItem);
				String firstLetter;
				if (mReady) {
					if (firstVisibleItem > 20) {
						char a = 'A';
						int b = (int) a;
						String code = map.get(CODE);
						int num = searchFromCode(code);
						char letter = (char) (num - 1 + b);
						firstLetter = ((Character) letter).toString();
					} else {
						firstLetter = "热门";
					}
					if (!mShowing && firstLetter.equals(mPrevLetter)) {

						mShowing = true;
						mDialogText.setVisibility(View.VISIBLE);

					}
					mDialogText.setText(firstLetter);
					mHandler.removeCallbacks(mRemoveWindow);
					mHandler.postDelayed(mRemoveWindow, 3000);
					mPrevLetter = firstLetter;
				}
			}

		}

		public void onScrollStateChanged(AbsListView view, int scrollState) {
		}

	};

	/**
	 * 查询相应的三字码
	 * 
	 * @param code
	 * @return
	 */
	private int searchFromCode(String code) {
		String cityMa[][] = CityCode.cityMa;
		for (int j = 1; j < cityMa.length; j++) {
			for (int i = 0; i < cityMa[j].length; i++) {
				if (cityMa[j][i].indexOf(code) != -1) {
					return j;
				}

			}
		}
		return 0;
	}

	/**
	 * 文本框变化监听
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
	 * 查询相应的名字
	 */
	private void searchName() {
		String cityMa[][] = CityCode.cityMa;
		String CityString[][] = CityCode.CityString;
		String s = searchfightcitytext.getText().toString();
		data = new ArrayList<Map<String, String>>();
		for (int j = 0; j < CityString.length; j++) {
			for (int i = 0; i < CityString[j].length; i++) {
				String city = CityString[j][i];
				if (city.indexOf(s) != -1) {
					Map<String, String> item = new HashMap<String, String>();
					item.put(CODE, cityMa[j][i]);
					item.put(NAME, CityString[j][i]);
					data.add(item);
				}
			}
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { NAME },
				new int[] { R.id.content });
		citylist.setAdapter(adapter);
		citylist.setOnItemClickListener(listListener);
	}
}
