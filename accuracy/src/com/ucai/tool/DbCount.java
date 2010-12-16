package com.ucai.tool;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
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
		ObjectServer server = Db4oClientServer.openServer(Db4oClientServer
				.newServerConfiguration(), DB4OFILENAME, 0);
		int i = 0;
		try {
			ObjectContainer client = server.openClient();
			Count count = new Count();
			ObjectSet<Count> result = client.get(count);
			if (result.size() > 0) {
				count = result.next();
				i = count.getId();
				count.setId(count.getId() + 1);
				client.set(count);

			} else {
				return insert(client);
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
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
