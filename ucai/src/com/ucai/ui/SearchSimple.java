package com.ucai.ui;

import java.util.Calendar;
import java.util.Locale;

import com.ucai.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 单程查询界面，该功能主要是通过在界面上操作出发城市、到达城市、出发日期和航空公司进行查询<br>
 * 其中出发城市、到达城市和航空公司是通过选择来完成的，但出发（到达）城市跟航空公司都很多，所以是<br>
 * 通过模糊查询再选择来完成的，故当操作到出发（到达）城市时，程序就会弹出查询选择界面，具体弹出的<br>
 * 操作请查看SearchFightCity.<br>
 * 当查询操作完成后，点击查询时，程序便会把参数传递到SearchFightView进行航班的查询，具体请查看SearchFightView
 * @author 李卓林
 * @see SearchFightCity
 * @see SearchFightView
 *
 */
public class SearchSimple extends Activity {
	/**
	 * 查询出发城市时的标志
	 */
	private static final int SCITY_REQUEST_CODE = 1;
	/**
	 * 查询到达城市时的标志
	 */	
	private static final int ECITY_REQUEST_CODE = 2;
	/**
	 * 查询航空公司的标志
	 */
	private static final int AIRLINES_REQUEST_CODE = 3;
	/**
	 * 选择日期的标志
	 */
	private static final int DATE_DIALOG_ID = 1;
	/**
	 * 出发城市的输入框
	 */
	private EditText sctiy;
	/**
	 * 到达城市的输入框
	 */
	private EditText ectiy;
	/**
	 * 出发日期的输入框
	 */
	private EditText sdate;
	/**
	 * 航空公司的输入框
	 */
	private EditText airlines;
	/**
	 * 查询按键
	 */
	private Button search;
	/**
	 * 返回按键
	 */
	private Button back;
	/**
	 * 出发城市代码
	 */
	private String scode = null;
	/**
	 * 出发城市名
	 */
	private String sname = null;
	/**
	 * 到达城市代码
	 */
	private String ecode = null;
	/**
	 * 到达城市名字
	 */
	private String ename = null;
	/**
	 * 航空公司代码
	 */
	private String acode = null;
	/**
	 * 航空公司名字
	 */
	private String aname = null;
	/**
	 * 出发时间的年
	 */
	private int mYear;
	/**
	 * 出发时间的月
	 */
	private int mMonth;
	/**
	 * 出发时间的日
	 */
	private int mDay;

	/**
	 * 该方法为单程查询的入口方法，为输入框、按键准备相应的处理方法
	 */
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
				if (sctiy.getText().toString().length() == 0) {// 判断是否已作相应
					showToastCollectioned(Toast.LENGTH_SHORT, "请选择出发城市");
				} else if (ectiy.getText().toString().length() == 0) {
					showToastCollectioned(Toast.LENGTH_SHORT, "请选择到达城市");
				} else if (sdate.getText().toString().length() == 0) {
					showToastCollectioned(Toast.LENGTH_SHORT, "请选择出发日期");
				} else {
					Intent i = new Intent(SearchSimple.this,
							SearchFightView.class);
					i.putExtra("startcity", scode);
					i.putExtra("endcity", ecode);
					i.putExtra("date", sdate.getText().toString());
					i.putExtra("airway", acode);
					startActivity(i);
				}
			}
		});

		final Calendar c = Calendar.getInstance(Locale.CHINA);
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回参数方法接口，当从查询机场或航空公司后返回时调用的方法
	 * @param requestCode 请求码，当为SCITY_REQUEST_CODE时，为从查询起飞机场返回，当为ECITY_REQUEST_CODE<br>
	 * 						为从查询到达机场返回，当为AIRLINES_REQUEST_CODE时，为从查询航空公司返回
	 * @param resultCode 结果码，在此方法中没有用到
	 * @param data 返回的数据Intent，从Intent中取回返回的数据
	 * @see #SCITY_REQUEST_CODE
	 * @see #ECITY_REQUEST_CODE
	 * @see #AIRLINES_REQUEST_CODE
	 */	
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

	/**
	 * 时间显示监听,为当时间选择时准备的监听。<br>
	 * 当选择时间时，调用方法updateDisplay作处理
	 * @see #updateDisplay()
	 */
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	/**
	 * 时间选择显示功能
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	/**
	 * 操作时间返回的数据<br>
	 * 当操作时间选择时，调用的方法，配值给时间字段，更新起飞时间输入框
	 */
	private void updateDisplay() {
		String month;
		String day;
		if (((mMonth + 1) + "").length() == 1) {
			month = "0" + (mMonth + 1);
		} else {
			month = "" + (mMonth + 1);
		}
		if ((mDay + "").length() == 1) {
			day = "0" + mDay;
		} else {
			day = "" + mDay;
		}
		sdate.setText(new StringBuilder().append(mYear).append("-").append(
				month).append("-").append(day));
	}

	/**
	 * 显示提示界面方法
	 * 
	 * @param type 显示类型，短显示还是长显示
	 * @param message 显示的文字说明
	 */
	protected void showToastCollectioned(int type, String message) {
		View view = inflateView(R.layout.toast);
		TextView tv = (TextView) view.findViewById(R.id.tips);
		tv.setText(message);
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}

	/**
	 * 显示提示界面
	 * 
	 * @param resource 配置文字对应的代码
	 * @return 提示界面
	 */
	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
}
