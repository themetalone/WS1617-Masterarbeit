package com.github.themetalone.pandemicSimulation.simulation.population.transmission.components;

import java.util.List;

import com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState;

/**
 * A TransmissionComponent with the form of scalar * Product(States)
 * 
 * @author themetalone (sholzer)
 *
 */
class ScalarStateDependantTransmissionComponent extends TransmissionComponentImpl {

	private final double scalar;
	private final List<HealthState> healthStates;

	/**
	 * @param scalar
	 * @param healtStates
	 */
	ScalarStateDependantTransmissionComponent(int id, double scalar, List<HealthState> healthStates,
			int counterpartId) {
		super(id, counterpartId);
		this.scalar = scalar;
		this.healthStates = healthStates;
	}

	@Override
	public long getValue() {
		return super.getConstrainedValue((long) (scalar
				* (double) healthStates.stream().mapToLong(hs -> hs.getSize()).reduce(1, (p, q) -> p * q)));
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.scalar
				+ healthStates.stream().map(hs -> hs.getName()).reduce("", (s, t) -> s + "*" + t);
	}

}
