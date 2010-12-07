/**
 * SetOrdersSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ucai.www;

public interface SetOrdersSoap extends java.rmi.Remote {

    /**
     * 国内机票订单录入
     */
    public com.ucai.www.FlyOrderResponseFlyOrderResult flyOrder(java.lang.String orderxml) throws java.rmi.RemoteException;

    /**
     * 修改机票订单支付状态
     */
    public com.ucai.www.UPSOrderResponseUPSOrderResult UPSOrder(java.lang.String fOrders, java.lang.String state, java.lang.String payDoc) throws java.rmi.RemoteException;

    /**
     * 修改机票订单状态
     */
    public com.ucai.www.USOrderResponseUSOrderResult USOrder(java.lang.String orderStatusXml) throws java.rmi.RemoteException;
}
