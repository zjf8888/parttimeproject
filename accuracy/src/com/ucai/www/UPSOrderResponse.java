/**
 * UPSOrderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ucai.www;

public class UPSOrderResponse  implements java.io.Serializable {
    private com.ucai.www.UPSOrderResponseUPSOrderResult UPSOrderResult;

    public UPSOrderResponse() {
    }

    public UPSOrderResponse(
           com.ucai.www.UPSOrderResponseUPSOrderResult UPSOrderResult) {
           this.UPSOrderResult = UPSOrderResult;
    }


    /**
     * Gets the UPSOrderResult value for this UPSOrderResponse.
     * 
     * @return UPSOrderResult
     */
    public com.ucai.www.UPSOrderResponseUPSOrderResult getUPSOrderResult() {
        return UPSOrderResult;
    }


    /**
     * Sets the UPSOrderResult value for this UPSOrderResponse.
     * 
     * @param UPSOrderResult
     */
    public void setUPSOrderResult(com.ucai.www.UPSOrderResponseUPSOrderResult UPSOrderResult) {
        this.UPSOrderResult = UPSOrderResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UPSOrderResponse)) return false;
        UPSOrderResponse other = (UPSOrderResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.UPSOrderResult==null && other.getUPSOrderResult()==null) || 
             (this.UPSOrderResult!=null &&
              this.UPSOrderResult.equals(other.getUPSOrderResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUPSOrderResult() != null) {
            _hashCode += getUPSOrderResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UPSOrderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">UPSOrderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UPSOrderResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ucai.com", "UPSOrderResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">>UPSOrderResponse>UPSOrderResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
