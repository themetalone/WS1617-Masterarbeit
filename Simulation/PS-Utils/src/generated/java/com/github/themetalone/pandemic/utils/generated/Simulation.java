//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.12.30 um 04:59:48 PM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="standards"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="uebertragung" type="{http://github.com/themetalone/simpleconfig}innereuebertragungtyp" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="subpopulation" type="{http://github.com/themetalone/simpleconfig}subpopulationComplexType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="population" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="subpopulation" type="{http://github.com/themetalone/simpleconfig}subpopulationComplexType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="uebertragung" type="{http://github.com/themetalone/simpleconfig}innereuebertragungtyp" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="erweiterung" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="reise-wege"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="zeit" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="puffer" default="1000"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *             &lt;minExclusive value="0"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="datenbank" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="modell" default="SEIR"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="SE?IH?F?D?RQ?"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "standards",
    "population",
    "reiseWege"
})
@XmlRootElement(name = "simulation")
public class Simulation {

    @XmlElement(required = true)
    protected Simulation.Standards standards;
    @XmlElement(required = true)
    protected List<Simulation.Population> population;
    @XmlElement(name = "reise-wege", required = true)
    protected Simulation.ReiseWege reiseWege;
    @XmlAttribute(name = "zeit", required = true)
    protected int zeit;
    @XmlAttribute(name = "puffer")
    protected Integer puffer;
    @XmlAttribute(name = "datenbank", required = true)
    protected String datenbank;
    @XmlAttribute(name = "modell")
    protected String modell;

    /**
     * Ruft den Wert der standards-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Simulation.Standards }
     *     
     */
    public Simulation.Standards getStandards() {
        return standards;
    }

    /**
     * Legt den Wert der standards-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Simulation.Standards }
     *     
     */
    public void setStandards(Simulation.Standards value) {
        this.standards = value;
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
     * {@link Simulation.Population }
     * 
     * 
     */
    public List<Simulation.Population> getPopulation() {
        if (population == null) {
            population = new ArrayList<Simulation.Population>();
        }
        return this.population;
    }

    /**
     * Ruft den Wert der reiseWege-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Simulation.ReiseWege }
     *     
     */
    public Simulation.ReiseWege getReiseWege() {
        return reiseWege;
    }

    /**
     * Legt den Wert der reiseWege-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Simulation.ReiseWege }
     *     
     */
    public void setReiseWege(Simulation.ReiseWege value) {
        this.reiseWege = value;
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
     * Ruft den Wert der puffer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPuffer() {
        if (puffer == null) {
            return  1000;
        } else {
            return puffer;
        }
    }

    /**
     * Legt den Wert der puffer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPuffer(Integer value) {
        this.puffer = value;
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
     * Ruft den Wert der modell-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModell() {
        if (modell == null) {
            return "SEIR";
        } else {
            return modell;
        }
    }

    /**
     * Legt den Wert der modell-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModell(String value) {
        this.modell = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="subpopulation" type="{http://github.com/themetalone/simpleconfig}subpopulationComplexType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="uebertragung" type="{http://github.com/themetalone/simpleconfig}innereuebertragungtyp" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="erweiterung" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "subpopulation",
        "uebertragung"
    })
    public static class Population {

        protected List<SubpopulationComplexType> subpopulation;
        protected List<Innereuebertragungtyp> uebertragung;
        @XmlAttribute(name = "name")
        protected String name;
        @XmlAttribute(name = "erweiterung")
        protected String erweiterung;

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
         * {@link SubpopulationComplexType }
         * 
         * 
         */
        public List<SubpopulationComplexType> getSubpopulation() {
            if (subpopulation == null) {
                subpopulation = new ArrayList<SubpopulationComplexType>();
            }
            return this.subpopulation;
        }

        /**
         * Gets the value of the uebertragung property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the uebertragung property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUebertragung().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Innereuebertragungtyp }
         * 
         * 
         */
        public List<Innereuebertragungtyp> getUebertragung() {
            if (uebertragung == null) {
                uebertragung = new ArrayList<Innereuebertragungtyp>();
            }
            return this.uebertragung;
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
         * Ruft den Wert der erweiterung-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErweiterung() {
            return erweiterung;
        }

        /**
         * Legt den Wert der erweiterung-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErweiterung(String value) {
            this.erweiterung = value;
        }

    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ReiseWege {


    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="uebertragung" type="{http://github.com/themetalone/simpleconfig}innereuebertragungtyp" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="subpopulation" type="{http://github.com/themetalone/simpleconfig}subpopulationComplexType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "uebertragung",
        "subpopulation"
    })
    public static class Standards {

        protected List<Innereuebertragungtyp> uebertragung;
        protected List<SubpopulationComplexType> subpopulation;

        /**
         * Gets the value of the uebertragung property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the uebertragung property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUebertragung().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Innereuebertragungtyp }
         * 
         * 
         */
        public List<Innereuebertragungtyp> getUebertragung() {
            if (uebertragung == null) {
                uebertragung = new ArrayList<Innereuebertragungtyp>();
            }
            return this.uebertragung;
        }

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
         * {@link SubpopulationComplexType }
         * 
         * 
         */
        public List<SubpopulationComplexType> getSubpopulation() {
            if (subpopulation == null) {
                subpopulation = new ArrayList<SubpopulationComplexType>();
            }
            return this.subpopulation;
        }

    }

}
