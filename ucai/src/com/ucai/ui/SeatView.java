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
import com.ucai.po.TravelerView;
import com.ucai.tool.ConstantPo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * 扣位界面,本界面是需要客户填写相应的个人信息跟乘客的信息
 * 
 * @author lin
 * 
 */
public class SeatView extends Activity {
	/**
	 * 仓位
	 */
	private TextView level;
	/**
	 * 机票数量
	 */
	private TextView count;
	/**
	 * 价格
	 */
	private TextView price;
	/**
	 * 联系人姓名
	 */
	private EditText name;
	/**
	 * 联系电话
	 */
	private EditText tel;
	/**
	 * 移动号码
	 */
	private EditText mobil;
	/**
	 * 电邮地址
	 */
	private EditText email;
	/**
	 * 联系人地址
	 */
	private EditText address;
	/**
	 * 配送地址
	 */
	private EditText sentaddress;
	/**
	 * 购买机票数量
	 */
	private Spinner numSpinner;
	/**
	 * 机票数量选择器的适配器
	 */
	private ArrayAdapter<String> numArrayAdapter = null;
	/**
	 * 购买机票数量选择列表
	 */
	private ArrayList<String> numpage = null;
	/**
	 * 第一乘机人姓名
	 */
	private EditText nameoftraveler;
	/**
	 * 乘机人类型
	 */
	private Spinner travelertype;
	/**
	 * 乘机人类型选择适配器
	 */
	private ArrayAdapter<String> travelerAdapter = null;
	/**
	 * 乘机人类型选择列表
	 */
	private ArrayList<String> travelerpage = null;
	/**
	 * 证件类型
	 */
	private Spinner idtype;
	/**
	 * 证件类型适配器
	 */
	private ArrayAdapter<String> idAdapter = null;
	/**
	 * 证件类型选择列表
	 */
	private ArrayList<String> idpage = null;
	/**
	 * 证件编号输入框
	 */
	private EditText idnumber;
	/**
	 * 保险数量
	 */
	private Spinner insurancenumber;
	/**
	 * 保险数量适配器
	 */
	private ArrayAdapter<String> insuranceAdapter = null;
	/**
	 * 保险数量选择列表
	 */
	private ArrayList<String> insurancepage = null;
	/**
	 * 乘机人2
	 */
	private TravelerView travelerView2 = null;
	/**
	 * 乘机人3
	 */
	private TravelerView travelerView3 = null;
	/**
	 * 乘机人4
	 */
	private TravelerView travelerView4 = null;
	/**
	 * 提交按键
	 */
	private Button commit;
	/**
	 * 返回按键
	 */
	private Button back;
	/**
	 * 仓位
	 */
	private SeatClass seatClasspo;
	/**
	 * 航程信息
	 */
	private Segment segmentpo;
	/**
	 * 第二乘机人的数据
	 */
	private ArrayList<LinearLayout> twoList = null;
	/**
	 * 第三乘机人的数据
	 */
	private ArrayList<LinearLayout> threeList = null;
	/**
	 * 第四乘机人的数据
	 */
	private ArrayList<LinearLayout> fourList = null;
	/**
	 * 进程提示窗口
	 */
	private ProgressDialog progressDialog = null;

	/**
	 * 扣位填写的入口方法，通过用户对旅客数量的选择，会同时变更界面，以适应填写的需要<br>
	 * 
	 */
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
		numSpinner.setOnItemSelectedListener(selectListener);
		nameoftraveler = (EditText) findViewById(R.id.nameoftraveler);
		travelertype = (Spinner) findViewById(R.id.travelertype);
		idtype = (Spinner) findViewById(R.id.idtype);
		idnumber = (EditText) findViewById(R.id.idnumber);
		insurancenumber = (Spinner) findViewById(R.id.insurancenumber);
		setTravelerPo2();
		setTravelerPo3();
		setTravelerPo4();
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		setTwoList();
		setThreeList();
		setFourList();
		commit = (Button) findViewById(R.id.commit);
		commit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doCommit();
			}
		});

		setView();
	}

	/**
	 * 把第二个旅客对象初始化
	 */
	private void setTravelerPo2() {
		travelerView2 = new TravelerView();
		travelerView2
				.setNameoftraveler((EditText) findViewById(R.id.nameoftraveler2));
		travelerView2
				.setTravelertype((Spinner) findViewById(R.id.travelertype2));
		travelerView2.setIdtype((Spinner) findViewById(R.id.idtype2));
		travelerView2.setIdnumber((EditText) findViewById(R.id.idnumber2));
		travelerView2
				.setInsurancenumber((Spinner) findViewById(R.id.insurancenumber2));
	}

	/**
	 * 把第三个旅客对象初始化
	 */
	private void setTravelerPo3() {
		travelerView3 = new TravelerView();
		travelerView3
				.setNameoftraveler((EditText) findViewById(R.id.nameoftraveler3));
		travelerView3
				.setTravelertype((Spinner) findViewById(R.id.travelertype3));
		travelerView3.setIdtype((Spinner) findViewById(R.id.idtype3));
		travelerView3.setIdnumber((EditText) findViewById(R.id.idnumber3));
		travelerView3
				.setInsurancenumber((Spinner) findViewById(R.id.insurancenumber3));
	}

	/**
	 * 把第四个旅客对象初始化
	 */
	private void setTravelerPo4() {
		travelerView4 = new TravelerView();
		travelerView4
				.setNameoftraveler((EditText) findViewById(R.id.nameoftraveler4));
		travelerView4
				.setTravelertype((Spinner) findViewById(R.id.travelertype4));
		travelerView4.setIdtype((Spinner) findViewById(R.id.idtype4));
		travelerView4.setIdnumber((EditText) findViewById(R.id.idnumber4));
		travelerView4
				.setInsurancenumber((Spinner) findViewById(R.id.insurancenumber4));
	}

	/**
	 * 对旅客类型的下接列表初始化
	 */
	private void setTravelertype() {
		Spinner travelertype2 = (Spinner) findViewById(R.id.travelertype2);
		Spinner travelertype3 = (Spinner) findViewById(R.id.travelertype3);
		Spinner travelertype4 = (Spinner) findViewById(R.id.travelertype4);
		travelertype2.setAdapter(travelerAdapter);
		travelertype2.setSelection(0);
		travelertype3.setAdapter(travelerAdapter);
		travelertype3.setSelection(0);
		travelertype4.setAdapter(travelerAdapter);
		travelertype4.setSelection(0);
	}

	/**
	 * 对证件类型的下接列表初始化
	 */
	private void setIDtype() {
		Spinner idtype2 = (Spinner) findViewById(R.id.idtype2);
		Spinner idtype3 = (Spinner) findViewById(R.id.idtype3);
		Spinner idtype4 = (Spinner) findViewById(R.id.idtype4);
		idtype2.setAdapter(idAdapter);
		idtype2.setSelection(0);
		idtype3.setAdapter(idAdapter);
		idtype3.setSelection(0);
		idtype4.setAdapter(idAdapter);
		idtype4.setSelection(0);
	}

	/**
	 * 对保险数量的下接列表初始化
	 */
	private void setInsurancenumber() {
		Spinner insurancenumber2 = (Spinner) findViewById(R.id.insurancenumber2);
		Spinner insurancenumber3 = (Spinner) findViewById(R.id.insurancenumber3);
		Spinner insurancenumber4 = (Spinner) findViewById(R.id.insurancenumber4);
		insurancenumber2.setAdapter(insuranceAdapter);
		insurancenumber2.setSelection(0);
		insurancenumber3.setAdapter(insuranceAdapter);
		insurancenumber3.setSelection(0);
		insurancenumber4.setAdapter(insuranceAdapter);
		insurancenumber4.setSelection(0);
	}

	/**
	 * 把第二个旅客的显示位置封装起来
	 */
	private void setTwoList() {
		twoList = new ArrayList<LinearLayout>();
		LinearLayout two1 = (LinearLayout) findViewById(R.id.two1);
		LinearLayout two2 = (LinearLayout) findViewById(R.id.two2);
		LinearLayout two3 = (LinearLayout) findViewById(R.id.two3);
		LinearLayout two4 = (LinearLayout) findViewById(R.id.two4);
		LinearLayout two5 = (LinearLayout) findViewById(R.id.two5);
		twoList.add(two1);
		twoList.add(two2);
		twoList.add(two3);
		twoList.add(two4);
		twoList.add(two5);
	}

	/**
	 * 把第三个旅客的显示位置封装起来
	 */
	private void setThreeList() {
		threeList = new ArrayList<LinearLayout>();
		LinearLayout three1 = (LinearLayout) findViewById(R.id.three1);
		LinearLayout three2 = (LinearLayout) findViewById(R.id.three2);
		LinearLayout three3 = (LinearLayout) findViewById(R.id.three3);
		LinearLayout three4 = (LinearLayout) findViewById(R.id.three4);
		LinearLayout three5 = (LinearLayout) findViewById(R.id.three5);
		threeList.add(three1);
		threeList.add(three2);
		threeList.add(three3);
		threeList.add(three4);
		threeList.add(three5);
	}

	/**
	 * 把第四个旅客的显示位置封装起来
	 */
	private void setFourList() {
		fourList = new ArrayList<LinearLayout>();
		LinearLayout four1 = (LinearLayout) findViewById(R.id.four1);
		LinearLayout four2 = (LinearLayout) findViewById(R.id.four2);
		LinearLayout four3 = (LinearLayout) findViewById(R.id.four3);
		LinearLayout four4 = (LinearLayout) findViewById(R.id.four4);
		LinearLayout four5 = (LinearLayout) findViewById(R.id.four5);
		fourList.add(four1);
		fourList.add(four2);
		fourList.add(four3);
		fourList.add(four4);
		fourList.add(four5);
	}

	/**
	 * 设置显示内容
	 */
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
		setTravelertype();// 对旅客类型的下接列表初始化
		setIDtype();// 初始化证件类型
		setInsurancenumber();// 初始化保险数量
	}

	/**
	 * 提交处理方法，首先格式化信息对象，然后通过XStream转化成xml<br>
	 * 最后调用SeatApi.setSeat(xml) 提交数据到远程服务器
	 * @return 扣位返回信息
	 * @see SeatApi#setSeat(String)
	 */ 
	private ReturnPo commit() {
		FlyOrder flyOrderpo = new FlyOrder();
		List<FlyAir> flyAirs = new ArrayList<FlyAir>();
		flyOrderpo.setFlyAirs(flyAirs);
		// 设置航程数据开始
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
		// 设置航程数据结束
		// 设置第一个旅客数据
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

		// 当多个旅客时外理
		if (numSpinner.getSelectedItemPosition() > 0) {
			for (int i = 2; i <= numSpinner.getSelectedItemPosition() + 1; i++) {
				Passenger pas = new Passenger();
				if (i == 2) {
					pas.setPasName(travelerView2.getNameoftraveler().getText()
							.toString().trim());
					pas.setPasType((travelerView2.getTravelertype()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setPasYE("");
					pas.setPasBirthday((travelerView2.getIdtype()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setPasBirthNo(travelerView2.getIdnumber().getText()
							.toString());
					pas.setInsurance_num((travelerView2.getInsurancenumber()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setInsurance_price((travelerView2.getInsurancenumber()
							.getSelectedItemPosition() + 1)
							* 20 + "");
				}
				if (i == 3) {
					pas.setPasName(travelerView3.getNameoftraveler().getText()
							.toString().trim());
					pas.setPasType((travelerView3.getTravelertype()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setPasYE("");
					pas.setPasBirthday((travelerView3.getIdtype()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setPasBirthNo(travelerView3.getIdnumber().getText()
							.toString());
					pas.setInsurance_num((travelerView3.getInsurancenumber()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setInsurance_price((travelerView3.getInsurancenumber()
							.getSelectedItemPosition() + 1)
							* 20 + "");
				}
				if (i == 4) {
					pas.setPasName(travelerView4.getNameoftraveler().getText()
							.toString().trim());
					pas.setPasType((travelerView4.getTravelertype()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setPasYE("");
					pas.setPasBirthday((travelerView4.getIdtype()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setPasBirthNo(travelerView4.getIdnumber().getText()
							.toString());
					pas.setInsurance_num((travelerView4.getInsurancenumber()
							.getSelectedItemPosition() + 1)
							+ "");
					pas.setInsurance_price((travelerView4.getInsurancenumber()
							.getSelectedItemPosition() + 1)
							* 20 + "");
				}
				passengers.add(pas);
			}
		}
		flyOrderpo.setPassengers(passengers);
		// 封装联系人
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
		String xml = xstream.toXML(flyOrderpo);// 生成相应的xml
		System.out.println(xml);
		SeatApi api = new SeatApi();
		ReturnPo rpo = api.setSeat(xml);// 调用扣位方法
		return rpo;
	}

	/**
	 * 提交方法,具体操作调用方法commit（），提交完成后转入到支付跳转界面,具体请查看ToastView
	 * @see #commit()
	 * @see ToastView
	 */
	private void doCommit() {
		progressDialog = ProgressDialog.show(SeatView.this, "请稍等...",
				"正在提交内容...", true);
		new Thread() {
			public void run() {
				try {
					ReturnPo rpo = commit();// 得到返回对象					
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

	/**
	 * 显示提示窗口
	 * @param type 显示窗口的类弄
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
	 * 初始化显示界面
	 * @param resource 相应的界面ID
 	 * @return 返回初始化后的界面
	 */
	private View inflateView(int resource) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return vi.inflate(resource, null);
	}
	/**
	 * 旅客人数选择器的监听，根据不同的人数，修改相应的界面生成，<br>
	 * 具体使相应的界面是不显示的，请查看setListVisibility和setListGone
	 * @see #setListVisibility(ArrayList)
	 * @see #setListGone(ArrayList)
	 */
	private OnItemSelectedListener selectListener = new OnItemSelectedListener() {
		@SuppressWarnings("unchecked")
		@Override
		public void onItemSelected(AdapterView parent, View v, int position,
				long id) {
			int pos = numSpinner.getSelectedItemPosition();
			if (pos == 0) {
				setListGone(twoList);
				setListGone(threeList);
				setListGone(fourList);
			}
			if (pos == 1) {
				setListGone(threeList);
				setListGone(fourList);
				setListVisibility(twoList);
			}
			if (pos == 2) {
				setListVisibility(threeList);
				setListGone(fourList);
				setListVisibility(twoList);
			}
			if (pos == 3) {
				setListVisibility(threeList);
				setListVisibility(fourList);
				setListVisibility(twoList);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};
	/**
	 * 使相应的界面能显示出来
	 * @param list 要显示出来的元素列表
	 */
	private void setListVisibility(ArrayList<LinearLayout> list) {
		for (int i = 0; i < list.size(); i++) {
			LinearLayout layout = list.get(i);
			layout.setVisibility(View.VISIBLE);
		}
	}
	/**
	 * 使相应的界面隐藏起来
	 * @param list 要隐藏出来的元素列表
	 */
	private void setListGone(ArrayList<LinearLayout> list) {
		for (int i = 0; i < list.size(); i++) {
			LinearLayout layout = list.get(i);
			layout.setVisibility(View.GONE);
		}
	}
}
