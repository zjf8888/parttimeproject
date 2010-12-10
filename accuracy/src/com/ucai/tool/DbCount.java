package com.ucai.tool;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.ucai.po.Count;

public class DbCount {
	final static String DB4OFILENAME = System.getProperty("user.home")
			+ "/auto.yap";

	/**
	 * 获取查询流水号
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int query() {
		ObjectContainer db = Db4o.openFile(DB4OFILENAME);
		int i = 0;
		try {
			Count count = new Count();
			ObjectSet<Count> result = db.get(count);
			if (result.size() > 0) {
				count = result.next();
				i = count.getId();
				count.setId(count.getId() + 1);
				db.set(count);

			} else {
				return insert(db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return i;
	}

	@SuppressWarnings("deprecation")
	private int insert(ObjectContainer db) {
		try {
			Count count = new Count();
			count.setId(1);
			db.set(count);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
