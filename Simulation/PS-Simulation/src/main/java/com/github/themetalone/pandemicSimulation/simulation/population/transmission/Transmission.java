package com.github.themetalone.pandemicSimulation.simulation.population.transmission;

/**
 * The unidirectional transmission from one HealthState into another
 * @author themetalone (sholzer)
 *
 */
public interface Transmission {
	
	/**
	 * executes this transmission. Changes are only transmitted to a HealthState, not from one!
	 */
	void transmit();
	

}
