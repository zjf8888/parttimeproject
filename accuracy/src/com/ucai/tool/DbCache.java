package com.ucai.tool;

import java.util.Calendar;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.ucai.po.Flight;
import com.ucai.po.SeatClass;
import com.ucai.po.Segment;

/**
 * 系统缓存类
 * 
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
	public void delete() {
		Db4o.configure().objectClass("com.ucai.po.Flight")
				.cascadeOnDelete(true);
		ObjectContainer db = Db4o.openFile(DB4OFILENAME);
		try {
			System.out.println("haltHour:"+Calendar.getInstance().getTime().getTime());
			ObjectSet<Flight> result = db.query(new Predicate<Flight>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Flight arg0) {
					long haltHour=Calendar.getInstance().getTime().getTime()-1800000;
					return arg0.getRightNow()<haltHour;
				}
			});
			while (result.hasNext()) {
				Flight flightpo = result.next();
				deleteSeatClass(db,flightpo.getSegmentList());//删除机票座位信息
				db.delete(flightpo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			db.close();
		}
	}
	private void deleteSeatClass(ObjectContainer db,List<Segment> segmentList){
		try{
			for(int i=0;i<segmentList.size();i++){
				Segment segment=segmentList.get(i);
				List<SeatClass> seatClassList=segment.getClassesList();
				for(int j=0;j<seatClassList.size();j++){
					SeatClass seatClass=seatClassList.get(j);
					db.delete(seatClass);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
