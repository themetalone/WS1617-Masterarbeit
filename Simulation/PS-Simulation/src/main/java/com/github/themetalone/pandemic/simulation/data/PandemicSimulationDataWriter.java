package com.github.themetalone.pandemic.simulation.data;

import com.github.themetalone.pandemic.simulation.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.transmission.TransmissionIdentifier;

/**
 * @author steffen
 *
 */
public interface PandemicSimulationDataWriter {

  void putHealthState(HealthState s);

  void putTransmission(Transmission t);

  void putHealthStateState(HealthStateIdentifier id, long size, long tick);

  void putTransmissionExecution(TransmissionIdentifier id, long value, long tick);

}
