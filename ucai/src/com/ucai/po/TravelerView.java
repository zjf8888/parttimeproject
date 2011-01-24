package com.ucai.po;

import android.widget.EditText;
import android.widget.Spinner;

/**
 * 旅客信息对象
 * 
 * @author lin
 * 
 */
public class TravelerView {
	private EditText nameoftraveler; // 旅客姓名
	private Spinner travelertype;// 旅客类型
	private Spinner idtype;// 证件类型
	private EditText idnumber;// 证件号码
	private Spinner insurancenumber;// 保险数量

	public EditText getNameoftraveler() {
		return nameoftraveler;
	}

	public void setNameoftraveler(EditText nameoftraveler) {
		this.nameoftraveler = nameoftraveler;
	}

	public Spinner getTravelertype() {
		return travelertype;
	}

	public void setTravelertype(Spinner travelertype) {
		this.travelertype = travelertype;
	}

	public Spinner getIdtype() {
		return idtype;
	}

	public void setIdtype(Spinner idtype) {
		this.idtype = idtype;
	}

	public EditText getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(EditText idnumber) {
		this.idnumber = idnumber;
	}

	public Spinner getInsurancenumber() {
		return insurancenumber;
	}

	public void setInsurancenumber(Spinner insurancenumber) {
		this.insurancenumber = insurancenumber;
	}
}
