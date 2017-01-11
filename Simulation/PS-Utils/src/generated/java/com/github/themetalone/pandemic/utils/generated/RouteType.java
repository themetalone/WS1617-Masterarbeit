//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.11 um 09:36:49 AM CET 
//


package com.github.themetalone.pandemic.utils.generated;

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
 *       &lt;attribute name="von" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="nach" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="typ" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="anteil" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "routeType")
public class RouteType {

    @XmlAttribute(name = "von")
    protected String von;
    @XmlAttribute(name = "nach")
    protected String nach;
    @XmlAttribute(name = "typ")
    protected Integer typ;
    @XmlAttribute(name = "anteil")
    protected Float anteil;

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
     * Ruft den Wert der typ-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyp() {
        return typ;
    }

    /**
     * Legt den Wert der typ-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyp(Integer value) {
        this.typ = value;
    }

    /**
     * Ruft den Wert der anteil-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAnteil() {
        return anteil;
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
