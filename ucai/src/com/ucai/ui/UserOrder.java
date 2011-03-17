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

/**
 * 用户订单查询列表界面,该类是用作已注册用户查询他自己已经扣位的订单
 * @author lin
 *
 */
public class UserOrder extends Activity {
	/**
	 * 出发日期
	 */
	private static final String SDATE = "sdate";
	/**
	 * 到达机场三字码
	 */
	private static final String ECITY = "ecity";
	/**
	 * 出发机场三字码
	 */
	private static final String SCITY = "scity";
	/**
	 * 订单列表
	 */
	private ListView orderList = null;
	/**
	 * 进程提示框
	 */
	private ProgressDialog progressDialog = null;
	/**
	 * 订单列表适配器数据结构
	 */
	private ArrayList<Map<String, String>> data = null;
	/**
	 * 更新通道进程
	 */
	private Handler handler = new Handler();
	/**
	 * 订单结果列表
	 */
	private List<ResultOrder> list = null;

	/**
	 * 程序入口方法，同时调用方法doSearch（）进行初始化
	 * @see #doSearch()
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_order);
		orderList = (ListView) findViewById(R.id.orderList);
		doSearch();
	}

	/**
	 * 第一次进入该类时调用的查询方法,该方法通过进程进行初始化<br>
	 * 并同时通过方法setDate（）对数据初始化，通过updateView（）更新界面。
	 * @see #setDate()
	 * @see #updateView()
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
	 * 对数据初始化的方法，主要通过UserOrderApi.setSeat(String)方法查询出用户的订单<br>
	 * 数据，然后把数据封装成订单列表适配器所需的数据列表
	 * @see UserOrderApi#setSeat(String)
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
	 * 更新界面方法，通过建立通道，然后调用方法upDate（）对方法进行更新
	 * @see #upDate()
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
	/**
	 * 更新界面，并配置监听器，监听器为listListener
	 * @see #listListener
	 */
	private void upDate() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.order_item, new String[] { SCITY, ECITY, SDATE },
				new int[] { R.id.scity, R.id.ecity, R.id.sdate });
		orderList.setAdapter(adapter);
		orderList.setOnItemClickListener(listListener);
	}
	/**
	 * 点击列表数据的监听器，该监听器的作用是点击列表时，把所点击订单数据发送到UserOrderView<br>
	 * 进行处理
	 * @see UserOrderView
	 */
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
