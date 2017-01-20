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
 *       &lt;attribute name="datenbank" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="batchgroesse" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="zeit" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="krankheit" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
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
    @XmlAttribute(name = "datenbank")
    protected String datenbank;
    @XmlAttribute(name = "batchgroesse")
    protected Integer batchgroesse;
    @XmlAttribute(name = "zeit")
    protected Integer zeit;
    @XmlAttribute(name = "krankheit")
    protected Float krankheit;

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
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBatchgroesse() {
        return batchgroesse;
    }

    /**
     * Legt den Wert der batchgroesse-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBatchgroesse(Integer value) {
        this.batchgroesse = value;
    }

    /**
     * Ruft den Wert der zeit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getZeit() {
        return zeit;
    }

    /**
     * Legt den Wert der zeit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setZeit(Integer value) {
        this.zeit = value;
    }

    /**
     * Ruft den Wert der krankheit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getKrankheit() {
        return krankheit;
    }

    /**
     * Legt den Wert der krankheit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setKrankheit(Float value) {
        this.krankheit = value;
    }

}