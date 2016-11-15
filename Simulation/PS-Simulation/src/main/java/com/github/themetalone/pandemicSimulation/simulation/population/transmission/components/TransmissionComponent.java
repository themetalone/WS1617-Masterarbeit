package com.github.themetalone.pandemicSimulation.simulation.population.transmission.components;


/**
 * A component of a transmission
 * @author themetalone (sholzer)
 *
 */
public interface TransmissionComponent {
		
	/**
	 * 
	 * @return the numerical value of the component
	 */
	long getValue();
	
	void setLowerBound(long lower);
	
	void setUpperBound(long upper);
	
	void removeConstr();
	
	int getId();
	
	int getCounterpartId();

}
