
package com.ucai.webservices.ucaisetorders;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import ucai.FlyOrderResponse;
import ucai.UPSOrderResponse;
import ucai.USOrderResponse;

@WebService(name = "SetOrdersSoap", targetNamespace = "http://www.ucai.com")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SetOrdersSoap {

	@WebMethod(operationName = "UPSOrder", action = "http://www.ucai.com/UPSOrder")
	@WebResult(name = "UPSOrderResponse", targetNamespace = "http://www.ucai.com")
	public UPSOrderResponse uPSOrder(
			@WebParam(name = "UPSOrder", targetNamespace = "http://www.ucai.com")
			ucai.UPSOrder UPSOrder);

	@WebMethod(operationName = "FlyOrder", action = "http://www.ucai.com/FlyOrder")
	@WebResult(name = "FlyOrderResponse", targetNamespace = "http://www.ucai.com")
	public FlyOrderResponse flyOrder(
			@WebParam(name = "FlyOrder", targetNamespace = "http://www.ucai.com")
			ucai.FlyOrder FlyOrder);

	@WebMethod(operationName = "USOrder", action = "http://www.ucai.com/USOrder")
	@WebResult(name = "USOrderResponse", targetNamespace = "http://www.ucai.com")
	public USOrderResponse uSOrder(
			@WebParam(name = "USOrder", targetNamespace = "http://www.ucai.com")
			ucai.USOrder USOrder);

}
