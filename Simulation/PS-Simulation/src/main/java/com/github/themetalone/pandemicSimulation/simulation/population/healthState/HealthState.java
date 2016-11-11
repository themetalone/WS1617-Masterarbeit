package com.github.themetalone.pandemicSimulation.simulation.population.healthState;

import com.github.themetalone.pandemicSimulation.simulation.exceptions.NotEnoughIndividualsException;

/**
 * A single SIR-Class population (e.g. the S class). 
 * @author themetalone (sholzer)
 *
 */
public interface HealthState {
	
	/**
	 * 
	 * @return the identifier of this population
	 */
	int getId();
	
	/**
	 * 
	 * @return the name of this population.
	 */
	String getName();
	
	/**
	 * 
	 * @return the current size of the class. Always greater than or equal to 0
	 */
	long getSize();
	
	/**
	 * Add the number to the class size. Although the change in size happens only after calling {@link #applyChanges()}. Multiple calls are summed up.
	 * @param number
	 * @throws {@link NotEnoughIndividualsException} iff the current changes plus this parameter are negativ and in total greater than the current size. Contains the maximum difference the size can be reduced by before hitting zero
	 */
	void addToSize(long number) throws NotEnoughIndividualsException;
	
	/**
	 * Applies any changes to the size of the class
	 */
	void applyChanges();

}
