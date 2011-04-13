package com.szqb.bean;

import com.szqb.util.QbExp;
import com.szqb.web.AbstractBean;

public class Refresh extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String refreshPage(){
		QbExp qbexp=new QbExp();
		QbExp.setMi("");
		qbexp.run();
		return SUCCESS;
	}

}
