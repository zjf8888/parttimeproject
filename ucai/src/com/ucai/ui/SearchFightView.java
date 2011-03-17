package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucai.api.FightApi;
import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.tool.MethodTool;
import com.ucai.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 搜索航班类,当查询条件满足时，进入到这个类，进行航班的查询，具体操作请查看onCreate方法
 * 
 * 
 * @author lin
 * @see #onCreate(Bundle)
 */
public class SearchFightView extends Activity {
	/**
	 * 出发城市代码
	 */
	private String startcity;
	/**
	 * 到达城市代码
	 */
	private String endcity;
	/**
	 * 出发日期
	 */
	private String date;
	/**
	 * 航空公司代码
	 */
	private String airway;
	/**
	 * 航班信息
	 */
	private Flight flightpo = null;
	/**
	 * 显示列表用
	 */
	private ListView listView = null;
	/**
	 * 进程对话框
	 */
	private ProgressDialog progressDialog = null;
	/**
	 * 更新界面通道
	 */
	private Handler handler = new Handler();
	/**
	 * 航班列表数据
	 */
	private ArrayList<Map<String, Object>> data = null;
	/**
	 * 坐位列表信息
	 */
	private List<Segment> segmentList = null;
	/**
	 * 下一步按键
	 */
	private Button next;
	/**
	 * 上一步按键
	 */
	private Button previous;
	/**
	 * 页码
	 */
	private TextView pageNo;
	/**
	 * 页面总数
	 */
	private TextView totalPages;
	/**
	 * 错误信息
	 */
	private TextView errorinfo;

	/**
	 * 为机票查询的入口方法，该方法从Intent中获取SearchSimple发送过来的参数<br>
	 * 并调用方法doSearch实现航班信息的查询
	 * @see SearchSimple
	 * @see #doSearch()
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlist);
		listView = (ListView) findViewById(R.id.fightList);
		startcity = (String) getIntent().getExtras().get("startcity");
		endcity = (String) getIntent().getExtras().get("endcity");
		date = (String) getIntent().getExtras().get("date");
		airway = (String) getIntent().getExtras().get("airway");
		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (flightpo.getTotalPages() > flightpo.getPageNo()) {
					doNextPage(flightpo.getTransId(),
							(flightpo.getPageNo() + 1) + "");
				} else {
					doNextPage(flightpo.getTransId(), "1");
				}
			}
		});

		previous = (Button) findViewById(R.id.previous);
		previous.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (flightpo.getPageNo() > 1) {
					doNextPage(flightpo.getTransId(),
							(flightpo.getPageNo() - 1) + "");
				} else {
					doNextPage(flightpo.getTransId(), flightpo.getTotalPages()
							+ "");
				}
			}
		});
		pageNo = (TextView) findViewById(R.id.pageNo);
		totalPages = (TextView) findViewById(R.id.totalPages);
		errorinfo = (TextView) findViewById(R.id.errorinfo);
		doSearch();
	}

	/**
	 * 具体的查询航班方法，在线程中查询，其中setDate为航班查询方法，updateView为界面更新方法
	 * @see #setDate()
	 * @see #updateView()
	 */
	private void doSearch() {
		progressDialog = ProgressDialog.show(SearchFightView.this, "请稍等...",
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
	 * 下一页调用的查询方法,当用户在操作下一页查询时调用的查询方法，
	 * <br>其中setNextPageDate为获取下一页航班信息方法，updateView为界面更新方法
	 * 
	 * 
	 * @param transid
	 *            查询流水号
	 * @param pn
	 *            页码
	 * @see #setNextPageDate(String, String)
	 * #see #update()    
	 */
	private void doNextPage(final String transid, final String pn) {
		progressDialog = ProgressDialog.show(SearchFightView.this, "请稍等...",
				"获取查询内容...", true);
		new Thread() {
			public void run() {
				try {
					setNextPageDate(transid, pn);
					updateView();
				} catch (Exception e) {
					e.printStackTrace();
				}
				progressDialog.dismiss();
			}
		}.start();
	}

	/**
	 * 更新界面方法，通过建立通道，再调用具体更新界面方法setView
	 * @see #setView()
	 */
	private void updateView() {
		handler.post(new Runnable() {
			public void run() {
				try {
					setView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 具体更新界面方法,进行建立适配器，设置列表，设置页码等工作
	 */
	private void setView() {
		if (flightpo.getErrorCode().trim().equals("0")) {
			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.searchlist_item, new String[] { "name","fltno", "stime",
							"etime", "price" }, new int[] { R.id.airname,R.id.fltno,
							R.id.stime, R.id.etime, R.id.price });
			listView.setAdapter(adapter);
			listView.setHorizontalScrollBarEnabled(true);
			listView.setOnItemClickListener(listListener);
			totalPages.setText("共" + flightpo.getTotalPages() + "页");
			pageNo.setText("第" + flightpo.getPageNo() + "页");
		} else {
			errorinfo.setText(flightpo.getErrorTips());
		}

	}

	/**
	 * 查询航班具体方法，主要通过FightApi查询航空公司航班数据
	 * @see FightApi#getFlightPo(String, String, String, String, String)
	 */
	private void setDate() {
		data = new ArrayList<Map<String, Object>>();
		FightApi api = new FightApi();
		try {
			flightpo = api.getFlightPo(startcity, endcity, date, airway, "");
			segmentList = flightpo.getSegmentList();
			for (int i = 0; i < segmentList.size(); i++) {
				Segment contentbean = segmentList.get(i);
				Map<String, Object> item = new HashMap<String, Object>();
				String fltno = contentbean.getFltno();
				String fltName = MethodTool.searchName(fltno.substring(0, 2));
				item.put("name", fltName);
				item.put("fltno", fltno);
				item.put("stime", "起飞:" + contentbean.getDeptime());
				item.put("etime", "到达" + contentbean.getArrtime());
				List<SeatClass> classesList = contentbean.getClassesList();
				String minPrice = searchMinPrice(classesList);
				item.put("price", "￥" + minPrice);
				data.add(item);
			}
		} catch (Exception e) {
			flightpo = new Flight();
			flightpo.setErrorCode("104");
			flightpo.setErrorTips("网络未连通");
			e.printStackTrace();
		}
	}

	/**
	 * 设置下一页时调用的方法,主要通过FightApi查询缓存在服务器上的航班数据
	 * 
	 * @param transid 查询单号
	 * @param pn 想获取的页码
	 * @see FightApi#getCacheFlightPo(String, String)
	 */
	private void setNextPageDate(String transid, String pn) {
		data = new ArrayList<Map<String, Object>>();
		FightApi api = new FightApi();
		try {
			flightpo = api.getCacheFlightPo(transid, pn);
			segmentList = flightpo.getSegmentList();
			for (int i = 0; i < segmentList.size(); i++) {
				Segment contentbean = segmentList.get(i);
				Map<String, Object> item = new HashMap<String, Object>();
				String fltno = contentbean.getFltno();
				String fltName = MethodTool.searchName(fltno.substring(0, 2));
				item.put("name", fltName);
				item.put("fltno", fltno);
				item.put("stime", "起飞:" + contentbean.getDeptime());
				item.put("etime", "到达" + contentbean.getArrtime());
				List<SeatClass> classesList = contentbean.getClassesList();
				String minPrice = searchMinPrice(classesList);
				item.put("price", "￥" + minPrice);
				data.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询每航班中价格最低的座位
	 * 
	 * @param classesList 座位列表
	 * @return 最低的价格
	 */
	private String searchMinPrice(List<SeatClass> classesList) {
		int minPrice = 10000000;
		if (classesList != null && classesList.size() > 0) {
			for (int i = 0; i < classesList.size(); i++) {
				SeatClass seatClass = classesList.get(i);
				int price = new Integer(seatClass.getSaleprice());
				if (price < minPrice)
					minPrice = price;
			}
		}
		return "" + minPrice;
	}

	/**
	 * 点击航班列表相应的监听，它会让程序进入用户所点击的航班的具体信息列表（FightView）
	 * @see FightView
	 */
	private OnItemClickListener listListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent i = new Intent(SearchFightView.this, FightView.class);
			Segment segmentpo = segmentList.get(position);
			segmentpo.setDate(date);
			i.putExtra("segmentpo", segmentpo);
			startActivity(i);
		}
	};
}
