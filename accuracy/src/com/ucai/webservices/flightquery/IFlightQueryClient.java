
package com.ucai.webservices.flightquery;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class IFlightQueryClient {

	private static XFireProxyFactory proxyFactory = new XFireProxyFactory();

	private HashMap endpoints = new HashMap();

	private Service service0;

	public IFlightQueryClient() {
		create0();
		Endpoint IFlightQueryHttpPortEP = service0.addEndpoint(new QName(
				"http://jdtx", "IFlightQueryHttpPort"), new QName(
				"http://jdtx", "IFlightQueryHttpBinding"),
				"http://flight.95080.com/frontend/jdtx.ws");
		endpoints.put(new QName("http://jdtx", "IFlightQueryHttpPort"),
				IFlightQueryHttpPortEP);
		Endpoint IFlightQueryPortTypeLocalEndpointEP = service0.addEndpoint(
				new QName("http://jdtx", "IFlightQueryPortTypeLocalEndpoint"),
				new QName("http://jdtx", "IFlightQueryPortTypeLocalBinding"),
				"xfire.local://IFlightQuery");
		endpoints.put(new QName("http://jdtx",
				"IFlightQueryPortTypeLocalEndpoint"),
				IFlightQueryPortTypeLocalEndpointEP);
	}

	public Object getEndpoint(Endpoint endpoint) {
		try {
			return proxyFactory.create((endpoint).getBinding(), (endpoint)
					.getUrl());
		} catch (MalformedURLException e) {
			throw new XFireRuntimeException("Invalid URL", e);
		}
	}

	public Object getEndpoint(QName name) {
		Endpoint endpoint = ((Endpoint) endpoints.get((name)));
		if ((endpoint) == null) {
			throw new IllegalStateException("No such endpoint!");
		}
		return getEndpoint((endpoint));
	}

	public Collection getEndpoints() {
		return endpoints.values();
	}

	private void create0() {
		TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance()
				.getXFire().getTransportManager());
		HashMap props = new HashMap();
		props.put("annotations.allow.interface", true);
		AnnotationServiceFactory asf = new AnnotationServiceFactory(
				new Jsr181WebAnnotations(), tm, new AegisBindingProvider(
						new JaxbTypeRegistry()));
		asf.setBindingCreationEnabled(false);
		service0 = asf.create(
				(com.ucai.webservices.flightquery.IFlightQueryPortType.class), props);
		{
			AbstractSoapBinding soapBinding = asf
					.createSoap11Binding(service0, new QName("http://jdtx",
							"IFlightQueryPortTypeLocalBinding"),
							"urn:xfire:transport:local");
		}
		{
			AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0,
					new QName("http://jdtx", "IFlightQueryHttpBinding"),
					"http://schemas.xmlsoap.org/soap/http");
		}
	}

	public IFlightQueryPortType getIFlightQueryHttpPort() {
		return ((IFlightQueryPortType) (this).getEndpoint(new QName(
				"http://jdtx", "IFlightQueryHttpPort")));
	}

	public IFlightQueryPortType getIFlightQueryHttpPort(String url) {
		IFlightQueryPortType var = getIFlightQueryHttpPort();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public IFlightQueryPortType getIFlightQueryPortTypeLocalEndpoint() {
		return ((IFlightQueryPortType) (this).getEndpoint(new QName(
				"http://jdtx", "IFlightQueryPortTypeLocalEndpoint")));
	}

	public IFlightQueryPortType getIFlightQueryPortTypeLocalEndpoint(String url) {
		IFlightQueryPortType var = getIFlightQueryPortTypeLocalEndpoint();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public static void main(String[] args) {

		IFlightQueryClient client = new IFlightQueryClient();

		//create a default service endpoint
		IFlightQueryPortType iFlightQueryPortType = client
				.getIFlightQueryHttpPort();
		String flightInfo=iFlightQueryPortType.getFlightInfo("szx", "kwe", "2010-12-1", "", "jdtx", "");
		//TODO: Add custom client code here
		//
		//iFlightQueryPortType.yourServiceOperationHere();

		System.out.println(flightInfo);
		System.exit(0);
	}

}
