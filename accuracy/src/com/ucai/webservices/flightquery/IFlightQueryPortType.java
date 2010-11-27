
package com.ucai.webservices.flightquery;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

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
