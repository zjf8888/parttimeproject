/**
 * GetFlyOrderListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ucai.www;

public class GetFlyOrderListResponse  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.ucai.www.GetFlyOrderListResponseGetFlyOrderListResult getFlyOrderListResult;

    public GetFlyOrderListResponse() {
    }

    public GetFlyOrderListResponse(
           com.ucai.www.GetFlyOrderListResponseGetFlyOrderListResult getFlyOrderListResult) {
           this.getFlyOrderListResult = getFlyOrderListResult;
    }


    /**
     * Gets the getFlyOrderListResult value for this GetFlyOrderListResponse.
     * 
     * @return getFlyOrderListResult
     */
    public com.ucai.www.GetFlyOrderListResponseGetFlyOrderListResult getGetFlyOrderListResult() {
        return getFlyOrderListResult;
    }


    /**
     * Sets the getFlyOrderListResult value for this GetFlyOrderListResponse.
     * 
     * @param getFlyOrderListResult
     */
    public void setGetFlyOrderListResult(com.ucai.www.GetFlyOrderListResponseGetFlyOrderListResult getFlyOrderListResult) {
        this.getFlyOrderListResult = getFlyOrderListResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFlyOrderListResponse)) return false;
        GetFlyOrderListResponse other = (GetFlyOrderListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getFlyOrderListResult==null && other.getGetFlyOrderListResult()==null) || 
             (this.getFlyOrderListResult!=null &&
              this.getFlyOrderListResult.equals(other.getGetFlyOrderListResult())));
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
        if (getGetFlyOrderListResult() != null) {
            _hashCode += getGetFlyOrderListResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFlyOrderListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">GetFlyOrderListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getFlyOrderListResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ucai.com", "GetFlyOrderListResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">>GetFlyOrderListResponse>GetFlyOrderListResult"));
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
