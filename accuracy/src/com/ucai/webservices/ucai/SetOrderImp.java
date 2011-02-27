package com.ucai.webservices.ucai;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.MessageElement;

import com.ucai.po.JDResInfo;
import com.ucai.po.ResultOrder;
import com.ucai.tool.ReturnXml2Po;
import com.ucai.www.FlyOrderResponseFlyOrderResult;
import com.ucai.www.GetFlyOrderListResponseGetFlyOrderListResult;
import com.ucai.www.UPSOrderResponseUPSOrderResult;

/**
 * 扣位webService工具类,整个扣位调用的远程接口均在该类中调用
 * 
 * @author lin
 * 
 */
public class SetOrderImp {

	/**
	 * 扣位方法，主要调用了callService（String）方法
	 * @param orderxml 扣位所需的xml字符串
	 * @return 返回扣位成功后返回的xml
	 * @see #callService(String)
	 */
	public String FlyOrder(String orderxml) {

		try {
			// 这是由于jdom对命名对象中有下行线的bug的修复.
			orderxml = orderxml.replace("__", "_");
			return callService(orderxml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用远程扣位方法,调用远程方法是通过开源工具axis<br>
	 * 调用后返回的结果是通过自动生成类FlyOrderResponseFlyOrderResult保存的，<br>
	 * 要想提取返回后的结果，只需调用FlyOrderResponseFlyOrderResult.get_any()方法便可
	 * 
	 * @param orderxml 处理后的扣位所需的xml字符串
	 * @return 返回扣位结果字符串
	 * @see FlyOrderResponseFlyOrderResult#get_any()
	 */
	private String callService(String orderxml) {
		try {
			String service_url = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
			Service service = new Service();
			Call call = (Call) service.createCall();
			// 设置地址
			call.setTargetEndpointAddress(new java.net.URL(service_url));
			// 设置SOAPAction
			call.setSOAPActionURI("http://www.ucai.com/FlyOrder");
			// 设置设用的方法名
			call.setOperationName(new QName("http://www.ucai.com", "FlyOrder"));
			// 该方法需要的参数
			call.addParameter(new QName("http://www.ucai.com", "orderxml"),
					XMLType.XSD_STRING, ParameterMode.IN);
			// 设置返回类型
			call
					.setReturnType(new javax.xml.namespace.QName(
							"http://www.ucai.com",
							">>FlyOrderResponse>FlyOrderResult"));
			// 设置返回对象
			call
					.setReturnClass(com.ucai.www.FlyOrderResponseFlyOrderResult.class);
			// 设置返回命名空间
			call.setReturnQName(new javax.xml.namespace.QName(
					"http://www.ucai.com", "FlyOrderResult"));
			// 调用远程方法
			FlyOrderResponseFlyOrderResult FlyOrderResponseFlyOrderResult = (FlyOrderResponseFlyOrderResult) call
					.invoke(new Object[] { orderxml });
			// 获取相应的数据
			MessageElement[] me = FlyOrderResponseFlyOrderResult.get_any();
			// 把获取的数据组装成对象
			JDResInfo po = ReturnXml2Po.getJDResInfoFromXml(me[0].toString());
			System.out.println(po.getCode());
			return po.getCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过订单号或者用户ID获取订单信息，其中一个或两个均可，当只查询一个条件时，另一个为""便可,但只返回第一个对象
	 * 该方法通过getResultList（String,String）调用远程接口
	 * @param OrderNumber
	 *            订单信息
	 * @param userId
	 *            用户ID
	 * @return 订单对象
	 * @see #getResultList(String, String)
	 */
	public ResultOrder getFlyOrderList(String OrderNumber, String userId) {
		try {
			List<ResultOrder> resultList = getResultList(OrderNumber, userId);
			ResultOrder po = resultList.get(0);
			System.out.println(po.getF_PayStatus());
			return po;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过订单号或者用户ID获取订单信息，其中一个或两个均可，当只查询一个条件时，另一个为""便可
	 * 调用后返回的结果是通过自动生成类GetFlyOrderListResponseGetFlyOrderListResult保存的，<br>
	 * 要想提取返回后的结果，只需调用GetFlyOrderListResponseGetFlyOrderListResult.get_any()方法便可
	 * @param OrderNumber
	 *            订单信息
	 * @param userId
	 *            用户ID
	 * @return 订单列表
	 * @see GetFlyOrderListResponseGetFlyOrderListResult#get_any()
	 */
	public List<ResultOrder> getResultList(String OrderNumber, String userId) {
		try {
			String service_url = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
			Service service = new Service();
			Call call = (Call) service.createCall();
			// 设置地址
			call.setTargetEndpointAddress(new java.net.URL(service_url));
			// 设置SOAPAction
			call.setSOAPActionURI("http://www.ucai.com/GetFlyOrderList");
			// 设置设用的方法名
			call.setOperationName(new QName("http://www.ucai.com",
					"GetFlyOrderList"));
			// 该方法需要的参数
			call.addParameter(new QName("http://www.ucai.com", "OrderNumber"),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://www.ucai.com", "userId"),
					XMLType.XSD_STRING, ParameterMode.IN);
			// 设置返回类型
			call.setReturnType(new javax.xml.namespace.QName(
					"http://www.ucai.com",
					">>GetFlyOrderListResponse>GetFlyOrderListResult"));
			// 设置返回对象
			call
					.setReturnClass(com.ucai.www.GetFlyOrderListResponseGetFlyOrderListResult.class);
			// 设置返回命名空间
			call.setReturnQName(new javax.xml.namespace.QName(
					"http://www.ucai.com", "GetFlyOrderListResult"));
			// 调用远程方法
			GetFlyOrderListResponseGetFlyOrderListResult GetFlyOrderListResponseGetFlyOrderListResult = (GetFlyOrderListResponseGetFlyOrderListResult) call
					.invoke(new Object[] { OrderNumber, userId });
			// 获取相应的数据
			MessageElement[] me = GetFlyOrderListResponseGetFlyOrderListResult
					.get_any();
			// 把获取的数据组装成对象
			List<ResultOrder> resultList = ReturnXml2Po.getFlyOrderList(me[0]
					.toString());
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新订单方法
	 * 调用后返回的结果是通过自动生成类UPSOrderResponseUPSOrderResult保存的，<br>
	 * 要想提取返回后的结果，只需调用UPSOrderResponseUPSOrderResult.get_any()方法便可
	 * @param OrderNumber
	 *            订单编号
	 * @param price
	 *            支付价格
	 * @return 修改结果
	 * @see UPSOrderResponseUPSOrderResult#get_any()
	 */
	public String updateOrder(String OrderNumber, String price) {
		try {
			String service_url = "http://www.ucai.com/FlyWebService/SetOrders.asmx?WSDL";
			Service service = new Service();
			Call call = (Call) service.createCall();
			// 设置地址
			call.setTargetEndpointAddress(new java.net.URL(service_url));
			// 设置SOAPAction
			call.setSOAPActionURI("http://www.ucai.com/UPSOrder");
			// 设置设用的方法名
			call.setOperationName(new QName("http://www.ucai.com", "UPSOrder"));
			// 该方法需要的参数
			call.addParameter(new QName("http://www.ucai.com", "fOrders"),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://www.ucai.com", "state"),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://www.ucai.com", "price"),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://www.ucai.com", "payDoc"),
					XMLType.XSD_STRING, ParameterMode.IN);
			// 设置返回类型
			call
					.setReturnType(new javax.xml.namespace.QName(
							"http://www.ucai.com",
							">>UPSOrderResponse>UPSOrderResult"));
			// 设置返回对象
			call
					.setReturnClass(com.ucai.www.UPSOrderResponseUPSOrderResult.class);
			// 设置返回命名空间
			call.setReturnQName(new javax.xml.namespace.QName(
					"http://www.ucai.com", "UPSOrderResult"));
			// 调用远程方法
			UPSOrderResponseUPSOrderResult UPSOrderResponseUPSOrderResult = (UPSOrderResponseUPSOrderResult) call
					.invoke(new Object[] { OrderNumber, "1", price, "" });
			// 获取相应的数据
			MessageElement[] me = UPSOrderResponseUPSOrderResult.get_any();
			// 把获取的数据组装成对象
			String re = me[0].toString();
			System.out.println(re);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
