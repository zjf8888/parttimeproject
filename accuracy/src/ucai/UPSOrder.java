
package ucai;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fOrders" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fOrders",
    "state",
    "payDoc"
})
@XmlRootElement(name = "UPSOrder")
public class UPSOrder {

    protected String fOrders;
    protected String state;
    protected String payDoc;

    /**
     * Gets the value of the fOrders property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFOrders() {
        return fOrders;
    }

    /**
     * Sets the value of the fOrders property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFOrders(String value) {
        this.fOrders = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the payDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayDoc() {
        return payDoc;
    }

    /**
     * Sets the value of the payDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayDoc(String value) {
        this.payDoc = value;
    }

}
