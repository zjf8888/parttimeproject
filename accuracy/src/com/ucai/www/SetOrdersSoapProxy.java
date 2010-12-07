package com.ucai.www;

public class SetOrdersSoapProxy implements com.ucai.www.SetOrdersSoap {
  private String _endpoint = null;
  private com.ucai.www.SetOrdersSoap setOrdersSoap = null;
  
  public SetOrdersSoapProxy() {
    _initSetOrdersSoapProxy();
  }
  
  public SetOrdersSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSetOrdersSoapProxy();
  }
  
  private void _initSetOrdersSoapProxy() {
    try {
      setOrdersSoap = (new com.ucai.www.SetOrdersLocator()).getSetOrdersSoap();
      if (setOrdersSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)setOrdersSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)setOrdersSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (setOrdersSoap != null)
      ((javax.xml.rpc.Stub)setOrdersSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ucai.www.SetOrdersSoap getSetOrdersSoap() {
    if (setOrdersSoap == null)
      _initSetOrdersSoapProxy();
    return setOrdersSoap;
  }
  
  public com.ucai.www.FlyOrderResponseFlyOrderResult flyOrder(java.lang.String orderxml) throws java.rmi.RemoteException{
    if (setOrdersSoap == null)
      _initSetOrdersSoapProxy();
    return setOrdersSoap.flyOrder(orderxml);
  }
  
  public com.ucai.www.UPSOrderResponseUPSOrderResult UPSOrder(java.lang.String fOrders, java.lang.String state, java.lang.String payDoc) throws java.rmi.RemoteException{
    if (setOrdersSoap == null)
      _initSetOrdersSoapProxy();
    return setOrdersSoap.UPSOrder(fOrders, state, payDoc);
  }
  
  public com.ucai.www.USOrderResponseUSOrderResult USOrder(java.lang.String orderStatusXml) throws java.rmi.RemoteException{
    if (setOrdersSoap == null)
      _initSetOrdersSoapProxy();
    return setOrdersSoap.USOrder(orderStatusXml);
  }
  
  
}