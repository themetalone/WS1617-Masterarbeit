package com.github.themetalone.pandemicSimulation.simulation.population.transmission;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemicSimulation.simulation.exceptions.NotEnoughIndividualsException;
import com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthState;
import com.github.themetalone.pandemicSimulation.simulation.population.healthState.HealthStateFactory;
import com.github.themetalone.pandemicSimulation.simulation.population.transmission.components.TransmissionComponent;
import com.github.themetalone.pandemicSimulation.simulation.population.transmission.components.TransmissionComponentProvider;

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
		final HealthState hs = HealthStateFactory.getInstance().getHealthState(destinationId);
		components.stream().forEach(tc -> {
			try {
				hs.addToSize(tc.getValue());
			} catch (NotEnoughIndividualsException e) {
				tc.setLowerBound(e.getMaxDiff());
				TransmissionComponentProvider.getInstance().getTransmissionComponent(tc.getCounterpartId()).setUpperBound(-e.getMaxDiff());
				try {
					hs.addToSize(tc.getValue());
				} catch (NotEnoughIndividualsException e1) {
					throw new Error("Transmission failed:"+tc.toString());
				}
			}
		});
	}
	
	@Override
	public String toString(){
		return "TransmissionImpl: "+destinationId+" -> "+components.stream().map(tc->tc.toString()).reduce("", (s,t)->(s+"+"+t));
	}

}
