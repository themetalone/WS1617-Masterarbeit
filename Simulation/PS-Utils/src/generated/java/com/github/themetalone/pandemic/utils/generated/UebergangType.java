//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.23 um 02:37:33 PM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für uebergangType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="uebergangType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="komponente" type="{http://github.com/themetalone/simpleconfig}uebergangKomponenteType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="von" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="nach" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uebergangType", propOrder = {
    "komponente"
})
public class UebergangType {

    @XmlElement(required = true)
    protected List<UebergangKomponenteType> komponente;
    @XmlAttribute(name = "von", required = true)
    protected String von;
    @XmlAttribute(name = "nach", required = true)
    protected String nach;

    /**
     * Gets the value of the komponente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the komponente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKomponente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UebergangKomponenteType }
     * 
     * 
     */
    public List<UebergangKomponenteType> getKomponente() {
        if (komponente == null) {
            komponente = new ArrayList<UebergangKomponenteType>();
        }
        return this.komponente;
    }

    /**
     * Ruft den Wert der von-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVon() {
        return von;
    }

    /**
     * Legt den Wert der von-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVon(String value) {
        this.von = value;
    }

    /**
     * Ruft den Wert der nach-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNach() {
        return nach;
    }

    /**
     * Legt den Wert der nach-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNach(String value) {
        this.nach = value;
    }

}
