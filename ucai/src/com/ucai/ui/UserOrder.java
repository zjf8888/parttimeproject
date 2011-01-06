package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucai.R;
import com.ucai.api.UserOrderApi;
import com.ucai.po.ResultOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class UserOrder extends Activity {
	private static final String SDATE = "sdate";
	private static final String ECITY = "ecity";
	private static final String SCITY = "scity";
	private ListView orderList = null;
	private ProgressDialog progressDialog = null;
	private ArrayList<Map<String, String>> data = null;
	private Handler handler = new Handler();
	private List<ResultOrder> list = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_order);
		orderList = (ListView) findViewById(R.id.orderList);
		doSearch();
	}

	/**
	 * 第一次进入该类时调用的查询方法
	 */
	private void doSearch() {
		progressDialog = ProgressDialog.show(UserOrder.this, "请稍等...",
				"获取查询内容...", true);
		new Thread() {
			public void run() {
				try {
					setDate();
					updateView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				progressDialog.dismiss();
			}
		}.start();
	}

	/**
	 * 设置数据
	 */
	private void setDate() {
		UserOrderApi api = new UserOrderApi();
		// 通过userid查询到相应的订单,现用0作为样列
		list = api.setSeat("0");
		data = new ArrayList<Map<String, String>>();
		for (int i = 0; i < list.size(); i++) {
			ResultOrder po = list.get(i);
			Map<String, String> item = new HashMap<String, String>();
			item.put(SCITY, po.getA_Scity());
			item.put(ECITY, po.getA_Ecity());
			item.put(SDATE, po.getA_FlyDate().split(" ")[0]);
			data.add(item);
		}
	}

	/**
	 * 更新界面
	 */
	private void updateView() {
		handler.post(new Runnable() {
			public void run() {
				try {
					upDate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void upDate() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.order_item, new String[] { SCITY, ECITY, SDATE },
				new int[] { R.id.scity, R.id.ecity, R.id.sdate });
		orderList.setAdapter(adapter);
		orderList.setOnItemClickListener(listListener);
	}

	private OnItemClickListener listListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent i = new Intent(UserOrder.this, UserOrderView.class);
			ResultOrder po = list.get(position);
			i.putExtra("resultOrder", po);
			startActivity(i);
		}
	};
}
