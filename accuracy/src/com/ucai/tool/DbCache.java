package com.ucai.tool;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.ucai.po.Flight;

public class DbCache {
	final static String DB4OFILENAME = System.getProperty("user.home")
			+ "/formula1.db4o";

	public void insertFlight(Flight flightpo) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), DB4OFILENAME);
		try {
			db.store(flightpo);
		} finally {
			db.close();
		}
	}
	public void query(String transId){
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), DB4OFILENAME);
		try {
			Flight flightpo = new Flight();
			flightpo.setTransId(transId);
			ObjectSet<Flight> result = db.queryByExample(flightpo);
			flightpo=result.next();			
			System.out.println(flightpo.getSegmentList());
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
}
