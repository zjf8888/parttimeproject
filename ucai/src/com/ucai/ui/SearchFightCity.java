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
 * 查询不同城市的界面,为用户快捷查找到机场，这个类服务于由出发机场和到达机场的选择
 * 
 * @author lin
 * 
 */
public class SearchFightCity extends Activity {
	/***
	 * 机场名称
	 */
	public static final String NAME = "name";
	/**
	 * 机场代码
	 */
	public static final String CODE = "code";
	/**
	 * 机场列表
	 */
	private ListView citylist = null;
	/**
	 * 搜索框
	 */
	private EditText searchfightcitytext = null;
	/**
	 * 为机场列表适配器使用的数据结对
	 */
	private ArrayList<Map<String, String>> data = null;
	/**
	 * 为移除字母窗口
	 */
	private RemoveWindow mRemoveWindow = new RemoveWindow();
	/**
	 * 进程通道
	 */
	Handler mHandler = new Handler();
	/**
	 * 窗口管理器
	 */
	private WindowManager mWindowManager;
	/**
	 * 提示框
	 */
	private TextView mDialogText;
	/**
	 * 是否显示标志
	 */
	private boolean mShowing;
	/**
	 * 是否准备好标志
	 */
	private boolean mReady;
	/**
	 * 在显示的字母
	 */
	private String mPrevLetter;

	/**
	 * 该方法为搜索城市功能的入口方法，该方法首先初始化城市列表，还有需要显示的提示字母
	 * 
	 */
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

	/**
	 * 数据封装,为显示城市列表封装数据，城市名称和城市代码来自CityCode
	 * @see CityCode#CityString
	 * @see CityCode#cityMa
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
	 * 为城市列表初始化适配器和封装点击监听器
	 * @see #listListener
	 */
	private void setView() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.list_item, new String[] { NAME },
				new int[] { R.id.content });
		citylist.setAdapter(adapter);
		citylist.setOnItemClickListener(listListener);
	}

	/**
	 * 点击选择时监听，该点击监听器的作为是为列表被点击时，把机场名跟机场代码返回给SearchSimple的机场选择<br>
	 * ，不管是出发机场或者到达机场的选择,具体在SearchSimple的处理，请查看SearchSimple.onActivityResult(int , int , Intent )
	 * @see SearchSimple#onActivityResult(int, int, Intent)
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
	/**
	 * Activity开始和用户交互的时候调用, 把mReady设为true
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mReady = true;
	}
	/**
	 * Activity要退出时，被暂停，配置mReady为false
	 */
	@Override
	protected void onPause() {
		super.onPause();
		removeWindow();
		mReady = false;
	}
	/**
	 * Activity被移除时调用，把提示框移除，并把mReady设为false
	 */
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
	 * @param code 三字码
	 * @return 查询出现在屏幕上出现的字母
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
