package com.ucai.tool;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.ucai.po.Flight;

public class DbCache {
	final static String DB4OFILENAME = System.getProperty("user.home")
			+ "/formula1.db4o";

	@SuppressWarnings("deprecation")
	public void insertFlight(Flight flightpo) {
		ObjectContainer db = Db4o.openFile("auto.yap");
		try {
			db.set(flightpo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	public void query(final String transId) {
		ObjectContainer db = Db4o.openFile("auto.yap");
		try {
			Flight flightpo = new Flight();
			ObjectSet<Flight> result = db.query(new Predicate<Flight>() {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Flight arg0) {
					return arg0.getTransId().equals(transId);

				}
			});

			System.out.println(result.size());
			flightpo = result.next();
			System.out.println(flightpo.getSegmentList().size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

}
