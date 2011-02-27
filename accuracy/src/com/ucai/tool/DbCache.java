package com.ucai.tool;

import java.util.Calendar;

import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;
import com.db4o.query.Predicate;
import com.ucai.po.Flight;

/**
 * 系统缓存类
 * 
 * @author 李卓林
 * 
 */
public class DbCache {
	/**
	 * 禁用DbCache对象初始化
	 */
	private DbCache() {
	}
	/**
	 * DbCache静态对象
	 */
	private static DbCache ca;
	/**
	 * 获取DbCache对象方法，如果静态对象ca为空，则初始化对象后返回<br>
	 * 如果ca已实例化，则直接返回ca对象
	 * @return DbCache静态对象
	 */
	public static DbCache getDbcache() {
		if (ca == null) {
			ca = new DbCache();
		}
		return ca;
	}
	/**
	 * 缓存数据的物理地址
	 */
	private String DB4OFILENAME = System.getProperty("user.home") + "/auto.yap";
	/**
	 * 开启db4o连接
	 * @param config 服务端参数
	 * @return 开启的服务对象
	 */
	private synchronized ObjectServer newObjectServer(ServerConfiguration config) {
		return Db4oClientServer.openServer(config, DB4OFILENAME, 0);
	}

	/**
	 * 插入查询飞机信息
	 * 
	 * @param flightpo 航班信息
	 */
	public synchronized void insertFlight(Flight flightpo) {
		System.out.println("insertFlight" + "开始");
		ObjectServer server = newObjectServer(Db4oClientServer
				.newServerConfiguration());
		// ObjectContainer db = Db4o.openFile(DB4OFILENAME);
		try {
			ObjectContainer client = server.openClient();
			client.store(flightpo);
			client.close();
			System.out.println("insertFlight" + "结束");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
		}
	}

	/**
	 * 通过transId查询飞机信息
	 * 
	 * @param transId 航班信息对象编号
	 * @return 航班信息对象
	 */
	public synchronized Flight query(final String transId) {
		System.out.println("query" + "开始");
		ObjectServer server = newObjectServer(Db4oClientServer
				.newServerConfiguration());
		try {
			ObjectContainer client = server.openClient();
			Flight flightpo = new Flight();
			ObjectSet<Flight> result = client.query(new Predicate<Flight>() {

				private static final long serialVersionUID = 1L;

				@Override
				public boolean match(Flight arg0) {
					return arg0.getTransId().equals(transId);

				}
			});
			flightpo = result.next();
			client.close();
			System.out.println("query" + "结束");
			return flightpo;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
		}
		return null;
	}

	/**
	 * 删除过期查询信息，无需参数，程序能自动删除过期航班信息
	 * 
	 */

	public synchronized void delete() {
		System.out.println("delete" + "开始");
		ServerConfiguration config = Db4oClientServer.newServerConfiguration();
		config.common().objectClass("com.ucai.po.Flight").cascadeOnDelete(true);
		config.common().objectClass("com.ucai.po.Segment")
				.cascadeOnDelete(true);
		ObjectServer server = newObjectServer(config);
		try {
			ObjectContainer db = server.openClient();
			System.out.println("haltHour:"
					+ Calendar.getInstance().getTime().getTime());
			ObjectSet<Flight> result = db.query(new Predicate<Flight>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Flight arg0) {
					long haltHour = Calendar.getInstance().getTime().getTime() - 1800000;
					return arg0.getRightNow() < haltHour;
				}
			});
			while (result.hasNext()) {
				Flight flightpo = result.next();
				db.delete(flightpo);
			}
			System.out.println("delete" + "结束");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			server.close();
		}
	}
}
