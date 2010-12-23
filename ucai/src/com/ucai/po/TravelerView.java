package com.ucai.po;

import android.widget.EditText;
import android.widget.Spinner;

public class TravelerView {
	private EditText nameoftraveler;
	private Spinner travelertype;
	private Spinner idtype;
	private EditText idnumber;
	private Spinner insurancenumber;

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
