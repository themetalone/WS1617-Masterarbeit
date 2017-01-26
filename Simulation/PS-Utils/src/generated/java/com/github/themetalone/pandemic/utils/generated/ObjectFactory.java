//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.01.25 um 11:06:12 AM CET 
//


package com.github.themetalone.pandemic.utils.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.github.themetalone.pandemic.utils.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Simulation_QNAME = new QName("http://github.com/themetalone/simpleconfig", "simulation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.github.themetalone.pandemic.utils.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SimulationType }
     * 
     */
    public SimulationType createSimulationType() {
        return new SimulationType();
    }

    /**
     * Create an instance of {@link PopulationenType }
     * 
     */
    public PopulationenType createPopulationenType() {
        return new PopulationenType();
    }

    /**
     * Create an instance of {@link PopulationType }
     * 
     */
    public PopulationType createPopulationType() {
        return new PopulationType();
    }

    /**
     * Create an instance of {@link SubpopulationType }
     * 
     */
    public SubpopulationType createSubpopulationType() {
        return new SubpopulationType();
    }

    /**
     * Create an instance of {@link UebergangType }
     * 
     */
    public UebergangType createUebergangType() {
        return new UebergangType();
    }

    /**
     * Create an instance of {@link UebergangKomponenteType }
     * 
     */
    public UebergangKomponenteType createUebergangKomponenteType() {
        return new UebergangKomponenteType();
    }

    /**
     * Create an instance of {@link RoutenType }
     * 
     */
    public RoutenType createRoutenType() {
        return new RoutenType();
    }

    /**
     * Create an instance of {@link RouteType }
     * 
     */
    public RouteType createRouteType() {
        return new RouteType();
    }

    /**
     * Create an instance of {@link ZuordnungType }
     * 
     */
    public ZuordnungType createZuordnungType() {
        return new ZuordnungType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimulationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://github.com/themetalone/simpleconfig", name = "simulation")
    public JAXBElement<SimulationType> createSimulation(SimulationType value) {
        return new JAXBElement<SimulationType>(_Simulation_QNAME, SimulationType.class, null, value);
    }

}
