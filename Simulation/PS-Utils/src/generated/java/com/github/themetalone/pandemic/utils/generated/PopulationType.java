//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.02.13 um 03:11:13 PM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für populationType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="populationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="subpopulation" type="{http://github.com/themetalone/simpleconfig}subpopulationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="uebergang" type="{http://github.com/themetalone/simpleconfig}uebergangType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="lebensstandard" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="migrationsanteil" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "populationType", propOrder = {
    "subpopulation",
    "uebergang"
})
public class PopulationType {

    protected List<SubpopulationType> subpopulation;
    protected List<UebergangType> uebergang;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "lebensstandard", required = true)
    protected float lebensstandard;
    @XmlAttribute(name = "migrationsanteil", required = true)
    protected float migrationsanteil;

    /**
     * Gets the value of the subpopulation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subpopulation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubpopulation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubpopulationType }
     * 
     * 
     */
    public List<SubpopulationType> getSubpopulation() {
        if (subpopulation == null) {
            subpopulation = new ArrayList<SubpopulationType>();
        }
        return this.subpopulation;
    }

    /**
     * Gets the value of the uebergang property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uebergang property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUebergang().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UebergangType }
     * 
     * 
     */
    public List<UebergangType> getUebergang() {
        if (uebergang == null) {
            uebergang = new ArrayList<UebergangType>();
        }
        return this.uebergang;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der lebensstandard-Eigenschaft ab.
     * 
     */
    public float getLebensstandard() {
        return lebensstandard;
    }

    /**
     * Legt den Wert der lebensstandard-Eigenschaft fest.
     * 
     */
    public void setLebensstandard(float value) {
        this.lebensstandard = value;
    }

    /**
     * Ruft den Wert der migrationsanteil-Eigenschaft ab.
     * 
     */
    public float getMigrationsanteil() {
        return migrationsanteil;
    }

    /**
     * Legt den Wert der migrationsanteil-Eigenschaft fest.
     * 
     */
    public void setMigrationsanteil(float value) {
        this.migrationsanteil = value;
    }

}
