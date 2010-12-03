package com.ucai.tool;

import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import com.ucai.webservices.flightquery.IFlightQueryPortType;

public class IFlightQueryTool {
	public void setTimeOut(IFlightQueryPortType iFlightQueryPortType) {
		HttpClientParams params = new HttpClientParams();
		params
				.setParameter(HttpClientParams.USE_EXPECT_CONTINUE,
						Boolean.FALSE);
		params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT,
				new Long(60000));// 单位是毫秒
		Client timeclient = Client.getInstance(iFlightQueryPortType);
		timeclient.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS,
				params);
	}
}