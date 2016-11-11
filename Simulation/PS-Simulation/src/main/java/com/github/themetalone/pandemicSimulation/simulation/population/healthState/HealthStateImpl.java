/**
 * 
 */
package com.github.themetalone.pandemicSimulation.simulation.population.healthState;

import com.github.themetalone.pandemicSimulation.simulation.exceptions.NotEnoughIndividualsException;

/**
 * @author themetalone (sholzer)
 *
 */
class HealthStateImpl implements HealthState {
	
	
	private final int id;
	private final String name;
	private long size;
	private long changes;

	/**
	 * @param id
	 * @param name
	 * @param size
	 */
	HealthStateImpl(int id, String name, long size) {
		this.id = id;
		this.name = name;
		this.size = size;
		changes = 0;
	}

	/* (non-Javadoc)
	 * @see com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState#getId()
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState#getSize()
	 */
	@Override
	public long getSize() {
		return this.size;
	}

	/* (non-Javadoc)
	 * @see com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState#addToSize(long)
	 */
	@Override
	public void addToSize(long number) throws NotEnoughIndividualsException {
		if(this.changes + number + this.size < 0){
			throw new NotEnoughIndividualsException(-(this.changes + number + this.size));
		}
		this.changes += number;
	}

	/* (non-Javadoc)
	 * @see com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState#applyChanges()
	 */
	@Override
	public void applyChanges() {
		this.size += this.changes;
	}

}
