package com.github.themetalone.pandemicSimulation.simulation.population.transmission;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemicSimulation.simulation.exceptions.NotEnoughIndividualsException;
import com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthStateFactory;
import com.github.themetalone.pandemicSimulation.simulation.population.transmission.components.TransmissionComponent;

class TransmissionImpl implements Transmission {
	
	/**
	 * @param destinationId
	 * @param components
	 */
	TransmissionImpl(int destinationId, List<TransmissionComponent> components) {
		super();
		this.destinationId = destinationId;
		this.components = components;
	}
	
	private final int destinationId;
	private final List<TransmissionComponent> components;
	private static final Logger LOG = LoggerFactory.getLogger(Transmission.class);

	@Override
	public void transmit() {
		try {
			HealthStateFactory.getInstance().getHealthState(destinationId).addToSize(
				components.stream().mapToLong(tc->tc.getValue()).sum()
			);
		} catch (NotEnoughIndividualsException e) {
			LOG.info("{}| Could not transmit all Individuals. Transmitting {} instead", this.toString(), e.getMaxDiff());
			try {
				HealthStateFactory.getInstance().getHealthState(destinationId).addToSize(-e.getMaxDiff());
			} catch (Exception e1) {
				LOG.error("{}| Somewhere something went wrong. This exception shouldn't have been thrown");
			}
		} catch (NoSuchElementException e) {
			LOG.error("{}| Could not find destination...");
		}

	}
	
	@Override
	public String toString(){
		return "Transmission Impl: "+destinationId+" -> "+components.stream().map(tc->tc.toString()).reduce("", (s,t)->(s+"+"+t));
	}

}
