//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.19 um 09:44:53 AM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für simulationType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="simulationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="populationen" type="{http://github.com/themetalone/simpleconfig}populationenType"/&gt;
 *         &lt;element name="routen" type="{http://github.com/themetalone/simpleconfig}routenType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="datenbank" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="batchgroesse" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="zeit" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="krankheit" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "simulationType", propOrder = {
    "populationen",
    "routen"
})
public class SimulationType {

    @XmlElement(required = true)
    protected PopulationenType populationen;
    @XmlElement(required = true)
    protected RoutenType routen;
    @XmlAttribute(name = "datenbank", required = true)
    protected String datenbank;
    @XmlAttribute(name = "batchgroesse", required = true)
    protected int batchgroesse;
    @XmlAttribute(name = "zeit", required = true)
    protected int zeit;
    @XmlAttribute(name = "krankheit", required = true)
    protected float krankheit;

    /**
     * Ruft den Wert der populationen-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PopulationenType }
     *     
     */
    public PopulationenType getPopulationen() {
        return populationen;
    }

    /**
     * Legt den Wert der populationen-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PopulationenType }
     *     
     */
    public void setPopulationen(PopulationenType value) {
        this.populationen = value;
    }

    /**
     * Ruft den Wert der routen-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RoutenType }
     *     
     */
    public RoutenType getRouten() {
        return routen;
    }

    /**
     * Legt den Wert der routen-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RoutenType }
     *     
     */
    public void setRouten(RoutenType value) {
        this.routen = value;
    }

    /**
     * Ruft den Wert der datenbank-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatenbank() {
        return datenbank;
    }

    /**
     * Legt den Wert der datenbank-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatenbank(String value) {
        this.datenbank = value;
    }

    /**
     * Ruft den Wert der batchgroesse-Eigenschaft ab.
     * 
     */
    public int getBatchgroesse() {
        return batchgroesse;
    }

    /**
     * Legt den Wert der batchgroesse-Eigenschaft fest.
     * 
     */
    public void setBatchgroesse(int value) {
        this.batchgroesse = value;
    }

    /**
     * Ruft den Wert der zeit-Eigenschaft ab.
     * 
     */
    public int getZeit() {
        return zeit;
    }

    /**
     * Legt den Wert der zeit-Eigenschaft fest.
     * 
     */
    public void setZeit(int value) {
        this.zeit = value;
    }

    /**
     * Ruft den Wert der krankheit-Eigenschaft ab.
     * 
     */
    public float getKrankheit() {
        return krankheit;
    }

    /**
     * Legt den Wert der krankheit-Eigenschaft fest.
     * 
     */
    public void setKrankheit(float value) {
        this.krankheit = value;
    }

}
