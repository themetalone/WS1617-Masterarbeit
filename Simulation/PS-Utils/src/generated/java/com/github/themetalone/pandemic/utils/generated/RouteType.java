//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.25 um 11:06:12 AM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für routeType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="routeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="zuordnung" type="{http://github.com/themetalone/simpleconfig}zuordnungType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="von" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="nach" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="flug" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *       &lt;attribute name="anteil" type="{http://www.w3.org/2001/XMLSchema}float" default="1" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "routeType", propOrder = {
    "zuordnung"
})
public class RouteType {

    protected List<ZuordnungType> zuordnung;
    @XmlAttribute(name = "von", required = true)
    protected String von;
    @XmlAttribute(name = "nach", required = true)
    protected String nach;
    @XmlAttribute(name = "flug")
    protected Boolean flug;
    @XmlAttribute(name = "anteil")
    protected Float anteil;

    /**
     * Gets the value of the zuordnung property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zuordnung property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZuordnung().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZuordnungType }
     * 
     * 
     */
    public List<ZuordnungType> getZuordnung() {
        if (zuordnung == null) {
            zuordnung = new ArrayList<ZuordnungType>();
        }
        return this.zuordnung;
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

    /**
     * Ruft den Wert der flug-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isFlug() {
        if (flug == null) {
            return false;
        } else {
            return flug;
        }
    }

    /**
     * Legt den Wert der flug-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlug(Boolean value) {
        this.flug = value;
    }

    /**
     * Ruft den Wert der anteil-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public float getAnteil() {
        if (anteil == null) {
            return  1.0F;
        } else {
            return anteil;
        }
    }

    /**
     * Legt den Wert der anteil-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAnteil(Float value) {
        this.anteil = value;
    }

}
