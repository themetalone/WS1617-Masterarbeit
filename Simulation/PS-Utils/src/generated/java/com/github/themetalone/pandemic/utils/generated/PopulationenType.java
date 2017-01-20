//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.19 um 09:44:53 AM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für populationenType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="populationenType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="standard" type="{http://github.com/themetalone/simpleconfig}populationType"/&gt;
 *         &lt;element name="population" type="{http://github.com/themetalone/simpleconfig}populationType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "populationenType", propOrder = {
    "standard",
    "population"
})
public class PopulationenType {

    @XmlElement(required = true)
    protected PopulationType standard;
    @XmlElement(required = true)
    protected List<PopulationType> population;

    /**
     * Ruft den Wert der standard-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PopulationType }
     *     
     */
    public PopulationType getStandard() {
        return standard;
    }

    /**
     * Legt den Wert der standard-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PopulationType }
     *     
     */
    public void setStandard(PopulationType value) {
        this.standard = value;
    }

    /**
     * Gets the value of the population property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the population property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPopulation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PopulationType }
     * 
     * 
     */
    public List<PopulationType> getPopulation() {
        if (population == null) {
            population = new ArrayList<PopulationType>();
        }
        return this.population;
    }

}
