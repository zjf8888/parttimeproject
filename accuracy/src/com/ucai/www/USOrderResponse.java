/**
 * USOrderResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ucai.www;

public class USOrderResponse  implements java.io.Serializable {
    private com.ucai.www.USOrderResponseUSOrderResult USOrderResult;

    public USOrderResponse() {
    }

    public USOrderResponse(
           com.ucai.www.USOrderResponseUSOrderResult USOrderResult) {
           this.USOrderResult = USOrderResult;
    }


    /**
     * Gets the USOrderResult value for this USOrderResponse.
     * 
     * @return USOrderResult
     */
    public com.ucai.www.USOrderResponseUSOrderResult getUSOrderResult() {
        return USOrderResult;
    }


    /**
     * Sets the USOrderResult value for this USOrderResponse.
     * 
     * @param USOrderResult
     */
    public void setUSOrderResult(com.ucai.www.USOrderResponseUSOrderResult USOrderResult) {
        this.USOrderResult = USOrderResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof USOrderResponse)) return false;
        USOrderResponse other = (USOrderResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.USOrderResult==null && other.getUSOrderResult()==null) || 
             (this.USOrderResult!=null &&
              this.USOrderResult.equals(other.getUSOrderResult())));
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
        if (getUSOrderResult() != null) {
            _hashCode += getUSOrderResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(USOrderResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">USOrderResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USOrderResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ucai.com", "USOrderResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">>USOrderResponse>USOrderResult"));
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
