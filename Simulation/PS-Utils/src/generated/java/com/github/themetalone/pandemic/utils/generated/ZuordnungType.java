//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.18 um 09:10:02 AM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für zuordnungType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="zuordnungType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="vonSubpopulation" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="nachSubpopulation" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zuordnungType")
public class ZuordnungType {

    @XmlAttribute(name = "vonSubpopulation")
    protected String vonSubpopulation;
    @XmlAttribute(name = "nachSubpopulation")
    protected String nachSubpopulation;

    /**
     * Ruft den Wert der vonSubpopulation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVonSubpopulation() {
        return vonSubpopulation;
    }

    /**
     * Legt den Wert der vonSubpopulation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVonSubpopulation(String value) {
        this.vonSubpopulation = value;
    }

    /**
     * Ruft den Wert der nachSubpopulation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNachSubpopulation() {
        return nachSubpopulation;
    }

    /**
     * Legt den Wert der nachSubpopulation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNachSubpopulation(String value) {
        this.nachSubpopulation = value;
    }

}
