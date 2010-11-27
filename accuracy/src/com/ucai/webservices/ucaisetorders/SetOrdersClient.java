
package com.ucai.webservices.ucaisetorders;

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

public class SetOrdersClient {

	private static XFireProxyFactory proxyFactory = new XFireProxyFactory();

	private HashMap endpoints = new HashMap();

	private Service service2;

	public SetOrdersClient() {
		create2();
		Endpoint SetOrdersSoapLocalEndpointEP = service2.addEndpoint(new QName(
				"http://www.ucai.com", "SetOrdersSoapLocalEndpoint"),
				new QName("http://www.ucai.com", "SetOrdersSoapLocalBinding"),
				"xfire.local://SetOrders");
		endpoints.put(new QName("http://www.ucai.com",
				"SetOrdersSoapLocalEndpoint"), SetOrdersSoapLocalEndpointEP);
		Endpoint SetOrdersSoapEP = service2.addEndpoint(new QName(
				"http://www.ucai.com", "SetOrdersSoap"), new QName(
				"http://www.ucai.com", "SetOrdersSoap"),
				"http://www.ucai.com/FlyWebService/SetOrders.asmx");
		endpoints.put(new QName("http://www.ucai.com", "SetOrdersSoap"),
				SetOrdersSoapEP);
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

	private void create2() {
		TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance()
				.getXFire().getTransportManager());
		HashMap props = new HashMap();
		props.put("annotations.allow.interface", true);
		AnnotationServiceFactory asf = new AnnotationServiceFactory(
				new Jsr181WebAnnotations(), tm, new AegisBindingProvider(
						new JaxbTypeRegistry()));
		asf.setBindingCreationEnabled(false);
		service2 = asf
				.create(
						(com.ucai.webservices.ucaisetorders.SetOrdersSoap.class),
						props);
		{
			AbstractSoapBinding soapBinding = asf.createSoap11Binding(service2,
					new QName("http://www.ucai.com",
							"SetOrdersSoapLocalBinding"),
					"urn:xfire:transport:local");
		}
		{
			AbstractSoapBinding soapBinding = asf.createSoap11Binding(service2,
					new QName("http://www.ucai.com", "SetOrdersSoap"),
					"http://schemas.xmlsoap.org/soap/http");
		}
	}

	public SetOrdersSoap getSetOrdersSoapLocalEndpoint() {
		return ((SetOrdersSoap) (this).getEndpoint(new QName(
				"http://www.ucai.com", "SetOrdersSoapLocalEndpoint")));
	}

	public SetOrdersSoap getSetOrdersSoapLocalEndpoint(String url) {
		SetOrdersSoap var = getSetOrdersSoapLocalEndpoint();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public SetOrdersSoap getSetOrdersSoap() {
		return ((SetOrdersSoap) (this).getEndpoint(new QName(
				"http://www.ucai.com", "SetOrdersSoap")));
	}

	public SetOrdersSoap getSetOrdersSoap(String url) {
		SetOrdersSoap var = getSetOrdersSoap();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public static void main(String[] args) {

		SetOrdersClient client = new SetOrdersClient();

		//create a default service endpoint
		SetOrdersSoap setOrdersSoap = client.getSetOrdersSoapLocalEndpoint();

		//TODO: Add custom client code here
		//
		//setOrdersSoap.yourServiceOperationHere();

		System.out.println("test client completed");
		System.exit(0);
	}

}
