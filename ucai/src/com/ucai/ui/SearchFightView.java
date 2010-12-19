package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucai.api.FightApi;
import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.tool.CityCode;
import com.ucai.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchFightView extends Activity {
	private String startcity;
	private String endcity;
	private String date;
	private String airway;
	private Flight flightpo = null;
	private ListView listView = null;
	private ProgressDialog progressDialog = null;
	private Handler handler = new Handler();
	private ArrayList<Map<String, Object>> data = null;
	private List<Segment> segmentList = null;

	private Button next;
	private Button previous;
	private TextView pageNo;
	private TextView totalPages;
	private TextView errorinfo;

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

	private void setView() {
		if (flightpo.getErrorCode().trim().equals("0")) {
			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.searchlist_item, new String[] { "name", "stime",
							"etime", "price" }, new int[] { R.id.airname,
							R.id.stime, R.id.etime, R.id.price });
			listView.setAdapter(adapter);
			totalPages.setText("共" + flightpo.getTotalPages() + "页");
			pageNo.setText("第" + flightpo.getPageNo() + "页");
		} else {
			errorinfo.setText(flightpo.getErrorTips());
		}

	}

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
				String fltName = searchName(fltno.substring(0, 2));
				item.put("name", fltName);
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
				String fltName = searchName(fltno.substring(0, 2));
				item.put("name", fltName);
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

	private String searchName(String s) {
		String hangKongCode[] = CityCode.hangKongCode;
		String hangKongName[] = CityCode.hangKongName;
		for (int i = 0; i < hangKongCode.length; i++) {
			String code = hangKongCode[i];
			if (code.indexOf(s) != -1) {
				String name = hangKongName[i] + "航空";
				return name;

			}
		}
		return s;
	}

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
}
