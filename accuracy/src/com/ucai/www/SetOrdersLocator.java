/**
 * SetOrdersLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ucai.www;

public class SetOrdersLocator extends org.apache.axis.client.Service implements com.ucai.www.SetOrders {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetOrdersLocator() {
    }


    public SetOrdersLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SetOrdersLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SetOrdersSoap
    private java.lang.String SetOrdersSoap_address = "http://www.ucai.com/FlyWebService/SetOrders.asmx";

    public java.lang.String getSetOrdersSoapAddress() {
        return SetOrdersSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SetOrdersSoapWSDDServiceName = "SetOrdersSoap";

    public java.lang.String getSetOrdersSoapWSDDServiceName() {
        return SetOrdersSoapWSDDServiceName;
    }

    public void setSetOrdersSoapWSDDServiceName(java.lang.String name) {
        SetOrdersSoapWSDDServiceName = name;
    }

    public com.ucai.www.SetOrdersSoap getSetOrdersSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SetOrdersSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSetOrdersSoap(endpoint);
    }

    public com.ucai.www.SetOrdersSoap getSetOrdersSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ucai.www.SetOrdersSoapStub _stub = new com.ucai.www.SetOrdersSoapStub(portAddress, this);
            _stub.setPortName(getSetOrdersSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSetOrdersSoapEndpointAddress(java.lang.String address) {
        SetOrdersSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ucai.www.SetOrdersSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ucai.www.SetOrdersSoapStub _stub = new com.ucai.www.SetOrdersSoapStub(new java.net.URL(SetOrdersSoap_address), this);
                _stub.setPortName(getSetOrdersSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SetOrdersSoap".equals(inputPortName)) {
            return getSetOrdersSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.ucai.com", "SetOrders");
    }

    private java.util.HashSet ports = null;
    @SuppressWarnings("unchecked")
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.ucai.com", "SetOrdersSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SetOrdersSoap".equals(portName)) {
            setSetOrdersSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
