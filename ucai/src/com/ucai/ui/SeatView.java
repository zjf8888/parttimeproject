package com.ucai.ui;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.ucai.R;
import com.ucai.api.SeatApi;
import com.ucai.po.Contact;
import com.ucai.po.FlyAir;
import com.ucai.po.FlyOrder;
import com.ucai.po.Passenger;
import com.ucai.po.ReturnPo;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;
import com.ucai.tool.ConstantPo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SeatView extends Activity {
	private TextView level;
	private TextView count;
	private TextView price;
	private EditText name;
	private EditText tel;
	private EditText mobil;
	private EditText email;
	private EditText address;
	private EditText sentaddress;

	private Spinner numSpinner;
	private ArrayAdapter<String> numArrayAdapter = null;
	private ArrayList<String> numpage = null;

	private EditText nameoftraveler;

	private Spinner travelertype;
	private ArrayAdapter<String> travelerAdapter = null;
	private ArrayList<String> travelerpage = null;

	private Spinner idtype;
	private ArrayAdapter<String> idAdapter = null;
	private ArrayList<String> idpage = null;

	private EditText idnumber;

	private Spinner insurancenumber;
	private ArrayAdapter<String> insuranceAdapter = null;
	private ArrayList<String> insurancepage = null;

	private Button commit;
	private Button back;

	private SeatClass seatClasspo;
	private Segment segmentpo;

	private ProgressDialog progressDialog = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setseat);
		seatClasspo = (SeatClass) getIntent().getExtras().get("seatClasspo");
		segmentpo = (Segment) getIntent().getExtras().get("segmentpo");
		level = (TextView) findViewById(R.id.level);
		count = (TextView) findViewById(R.id.count);
		price = (TextView) findViewById(R.id.price);
		name = (EditText) findViewById(R.id.name);
		tel = (EditText) findViewById(R.id.tel);
		mobil = (EditText) findViewById(R.id.mobil);
		email = (EditText) findViewById(R.id.email);
		address = (EditText) findViewById(R.id.address);
		sentaddress = (EditText) findViewById(R.id.sentaddress);
		numSpinner = (Spinner) findViewById(R.id.numSpinner);
		nameoftraveler = (EditText) findViewById(R.id.nameoftraveler);
		travelertype = (Spinner) findViewById(R.id.travelertype);
		idtype = (Spinner) findViewById(R.id.idtype);
		idnumber = (EditText) findViewById(R.id.idnumber);
		insurancenumber = (Spinner) findViewById(R.id.insurancenumber);

		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		commit = (Button) findViewById(R.id.commit);
		commit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doCommit();
			}
		});

		setView();
	}

	private void setView() {
		level.setText("座位等级:" + seatClasspo.getClassname());
		count.setText("座位数量:"
				+ (seatClasspo.getNum().equals("A") ? "大于9" : seatClasspo
						.getNum()));
		price.setText("价格:" + seatClasspo.getSaleprice());

		numpage = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			numpage.add("" + (i + 1));

		}
		numArrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, numpage);
		numArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		numSpinner.setAdapter(numArrayAdapter);
		numSpinner.setSelection(0);// 设置第几页

		travelerpage = new ArrayList<String>();
		for (int i = 0; i < ConstantPo.travelertype.length; i++) {
			String[] traveler = ConstantPo.travelertype;
			travelerpage.add(traveler[i]);
		}
		travelerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, travelerpage);
		travelerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		travelertype.setAdapter(travelerAdapter);
		travelertype.setSelection(0);// 设置第几页

		idpage = new ArrayList<String>();
		for (int i = 0; i < ConstantPo.idtype.length; i++) {
			String[] ider = ConstantPo.idtype;
			idpage.add(ider[i]);
		}
		idAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idpage);
		idAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		idtype.setAdapter(idAdapter);
		idtype.setSelection(0);// 设置第几页

		insurancepage = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			insurancepage.add("" + (1 + i));
		}
		insuranceAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, insurancepage);
		insuranceAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		insurancenumber.setAdapter(insuranceAdapter);
		insurancenumber.setSelection(0);
	}

	private ReturnPo commit() {
		FlyOrder flyOrderpo = new FlyOrder();
		List<FlyAir> flyAirs = new ArrayList<FlyAir>();
		flyOrderpo.setFlyAirs(flyAirs);

		FlyAir flyAir = new FlyAir();
		flyAir.setFlyNo(segmentpo.getFltno());
		flyAir.setFlyClass(seatClasspo.getClasscode());
		flyAir.setFlyPrice(seatClasspo.getSaleprice());
		flyAir.setBuildfee(seatClasspo.getBuildfee());
		flyAir.setFuelfee(seatClasspo.getFuelfee());
		flyAir.setPlanesty(segmentpo.getPlanesty());
		flyAir.setSc(segmentpo.getSc());
		flyAir.setEc(segmentpo.getEc());
		flyAir.setsDate(segmentpo.getDate());
		flyAir.seteDate(segmentpo.getDate());
		flyAir.setsTime(segmentpo.getDeptime());
		flyAir.seteTime(segmentpo.getArrtime());
		flyAirs.add(flyAir);

		Passenger paser = new Passenger();
		paser.setPasName(nameoftraveler.getText().toString().trim());
		paser.setPasType((travelertype.getSelectedItemPosition() + 1) + "");
		paser.setPasYE("");
		paser.setPasBirthday((idtype.getSelectedItemPosition() + 1) + "");
		paser.setPasBirthNo(idnumber.getText().toString());
		paser.setInsurance_num((insurancenumber.getSelectedItemPosition() + 1)
				+ "");
		paser
				.setInsurance_price((insurancenumber.getSelectedItemPosition() + 1)
						* 20 + "");
		List<Passenger> passengers = new ArrayList<Passenger>();
		passengers.add(paser);

		// 这里是在动态生成字段后处理预留的地方.
		flyOrderpo.setPassengers(passengers);
		Contact contact = new Contact();
		contact.setConName(name.getText().toString().trim());
		contact.setConTel(tel.getText().toString().trim());
		contact.setConMobile(mobil.getText().toString().trim());
		contact.setConEmail(email.getText().toString().trim());
		contact.setConAddress(address.getText().toString().trim());
		contact.setPsAddress(sentaddress.getText().toString().trim());
		flyOrderpo.setContact(contact);
		XStream xstream = new XStream();
		xstream.alias("flyOrder", com.ucai.po.FlyOrder.class);
		xstream.alias("flyAir", FlyAir.class);
		xstream.alias("passenger", Passenger.class);
		String xml = xstream.toXML(flyOrderpo);
		System.out.println(xml);
		SeatApi api = new SeatApi();
		ReturnPo rpo = api.setSeat(xml);
		return rpo;
	}

	private void doCommit() {
		progressDialog = ProgressDialog.show(SeatView.this, "请稍等...",
				"正在提交内容...", true);
		new Thread() {
			public void run() {
				try {
					ReturnPo rpo = commit();
					Intent i = new Intent(SeatView.this, ToastView.class);
					i.putExtra("rpo", rpo);
					startActivity(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
				progressDialog.dismiss();
			}
		}.start();
	}

	protected void showToastCollectioned(int type, String message) {
		View view = inflateView(R.layout.toast);
		TextView tv = (TextView) view.findViewById(R.id.tips);
		tv.setText(message);
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(type);
		toast.show();
	}

	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
}
