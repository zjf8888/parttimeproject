package com.ucai.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucai.R;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.tool.MethodTool;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class FightView extends Activity {

	private static final String PRICE = "price";
	private static final String NUM = "num";
	private static final String CLASSNAME = "classname";
	private TextView hkName;
	private TextView fightno;
	private TextView stime;
	private TextView scAirdrome;
	private TextView etime;
	private TextView ecAirdrome;
	private ListView seatlist = null;

	private ArrayList<Map<String, String>> data = null;
	private Segment segmentpo;
	private List<SeatClass> classesList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fight);
		segmentpo = (Segment) getIntent().getExtras().get("segmentpo");
		hkName = (TextView) findViewById(R.id.hkName);
		hkName.setText("航空公司："
				+ MethodTool.searchName(segmentpo.getFltno().substring(0, 2)));
		fightno = (TextView) findViewById(R.id.fightno);
		fightno.setText("航班号：" + segmentpo.getFltno());
		stime = (TextView) findViewById(R.id.stime);
		stime.setText(segmentpo.getDeptime());
		scAirdrome = (TextView) findViewById(R.id.scAirdrome);
		scAirdrome.setText("从" + segmentpo.getScAirdrome() + "起飞");
		etime = (TextView) findViewById(R.id.etime);
		etime.setText(segmentpo.getArrtime());
		ecAirdrome = (TextView) findViewById(R.id.ecAirdrome);
		ecAirdrome.setText("到达" + segmentpo.getEcAirdrome());
		seatlist = (ListView) findViewById(R.id.seatList);
		PrepareData();
		setDate();
	}

	private void setDate() {
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.fight_item, new String[] { CLASSNAME, NUM, PRICE },
				new int[] { R.id.level, R.id.count, R.id.price });
		seatlist.setAdapter(adapter);
	}

	private void PrepareData() {
		data = new ArrayList<Map<String, String>>();
		classesList = segmentpo.getClassesList();
		for (int i = 0; i < classesList.size(); i++) {
			SeatClass seatClasspo = classesList.get(i);
			Map<String, String> item = new HashMap<String, String>();
			item.put(CLASSNAME, seatClasspo.getClassname());
			item.put(NUM, seatClasspo.getNum().equals("A") ? "大于9"
					: seatClasspo.getNum());
			item.put(PRICE, seatClasspo.getSaleprice());
			data.add(item);
		}
	}
}
