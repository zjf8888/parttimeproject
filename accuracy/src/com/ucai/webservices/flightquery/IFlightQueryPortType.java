package com.ucai.webservices.flightquery;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * 机票查询webService的查询接口，由工具自动生成
 * 
 * @author lin
 * 
 */
@WebService(name = "IFlightQueryPortType", targetNamespace = "http://jdtx")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IFlightQueryPortType {

	@WebMethod(operationName = "getOrderSeat", action = "")
	@WebResult(name = "out", targetNamespace = "http://jdtx")
	public String getOrderSeat(
			@WebParam(name = "in0", targetNamespace = "http://jdtx")
			String in0);

	@WebMethod(operationName = "getFlightInfo", action = "")
	@WebResult(name = "out", targetNamespace = "http://jdtx")
	/**
	 * 调用远程查询航班的接口方法
	 * 
	 * @param in0
	 *            出发城市
	 * @param in1
	 *            目标城市
	 * @param in2
	 *            起飞日期
	 * @param in3
	 *            航空公司
	 * @param in4
	 *            精度用户名
	 * @param in5
	 *            航班号
	 * @return 返回航班信息xml字符串
	 */
	public String getFlightInfo(
			@WebParam(name = "in0", targetNamespace = "http://jdtx")
			String in0,
			@WebParam(name = "in1", targetNamespace = "http://jdtx")
			String in1,
			@WebParam(name = "in2", targetNamespace = "http://jdtx")
			String in2,
			@WebParam(name = "in3", targetNamespace = "http://jdtx")
			String in3,
			@WebParam(name = "in4", targetNamespace = "http://jdtx")
			String in4,
			@WebParam(name = "in5", targetNamespace = "http://jdtx")
			String in5);

	@WebMethod(operationName = "visitCommon", action = "")
	@WebResult(name = "out", targetNamespace = "http://jdtx")
	public String visitCommon(
			@WebParam(name = "in0", targetNamespace = "http://jdtx")
			String in0);

}
