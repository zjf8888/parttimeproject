/**
 * UPSOrder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ucai.www;

public class UPSOrder  implements java.io.Serializable {
    private java.lang.String fOrders;

    private java.lang.String state;

    private java.lang.String payDoc;

    public UPSOrder() {
    }

    public UPSOrder(
           java.lang.String fOrders,
           java.lang.String state,
           java.lang.String payDoc) {
           this.fOrders = fOrders;
           this.state = state;
           this.payDoc = payDoc;
    }


    /**
     * Gets the fOrders value for this UPSOrder.
     * 
     * @return fOrders
     */
    public java.lang.String getFOrders() {
        return fOrders;
    }


    /**
     * Sets the fOrders value for this UPSOrder.
     * 
     * @param fOrders
     */
    public void setFOrders(java.lang.String fOrders) {
        this.fOrders = fOrders;
    }


    /**
     * Gets the state value for this UPSOrder.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this UPSOrder.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the payDoc value for this UPSOrder.
     * 
     * @return payDoc
     */
    public java.lang.String getPayDoc() {
        return payDoc;
    }


    /**
     * Sets the payDoc value for this UPSOrder.
     * 
     * @param payDoc
     */
    public void setPayDoc(java.lang.String payDoc) {
        this.payDoc = payDoc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UPSOrder)) return false;
        UPSOrder other = (UPSOrder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fOrders==null && other.getFOrders()==null) || 
             (this.fOrders!=null &&
              this.fOrders.equals(other.getFOrders()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.payDoc==null && other.getPayDoc()==null) || 
             (this.payDoc!=null &&
              this.payDoc.equals(other.getPayDoc())));
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
        if (getFOrders() != null) {
            _hashCode += getFOrders().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getPayDoc() != null) {
            _hashCode += getPayDoc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UPSOrder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.ucai.com", ">UPSOrder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FOrders");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ucai.com", "fOrders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ucai.com", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.ucai.com", "payDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
