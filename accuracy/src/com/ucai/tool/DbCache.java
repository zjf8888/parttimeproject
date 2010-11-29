package com.ucai.tool;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.ucai.po.Flight;

/**
 * 系统缓存类
 * @李卓林
 *
 */
public class DbCache {
	final static String DB4OFILENAME = System.getProperty("user.home")
			+ "/auto.yap";

	@SuppressWarnings("deprecation")
	public void insertFlight(Flight flightpo) {
		ObjectContainer db = Db4o.openFile(DB4OFILENAME);
		try {
			db.set(flightpo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	@SuppressWarnings("deprecation")
	public void query(final String transId) {
		ObjectContainer db = Db4o.openFile(DB4OFILENAME);
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

	@SuppressWarnings("deprecation")
	public void delete(final String transId) {
		Db4o.configure().objectClass("com.ucai.po.Flight").cascadeOnDelete(true);
		ObjectContainer db = Db4o.openFile(DB4OFILENAME);
		try {
			ObjectSet<Flight> result = db.query(new Predicate<Flight>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Flight arg0) {
					return arg0.getTransId().equals(transId);
				}
			});
			Flight flightpo = result.next();
			db.delete(flightpo);
		} finally {
			// 关闭连接
			db.close();
		}

	}

}
