package com.github.themetalone.pandemic.simulation.data;

import com.github.themetalone.pandemic.simulation.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.transmission.TransmissionIdentifier;

/**
 * @author steffen
 *
 */
public class H2SqlPandemicSimulationDataWriter implements PandemicSimulationDataWriter {

  /**
   * The constructor.
   */
  public H2SqlPandemicSimulationDataWriter() {
  }

  @Override
  public void putHealthState(HealthState s) {

    // TODO Auto-generated method stub

  }

  @Override
  public void putTransmission(Transmission t) {

    // TODO Auto-generated method stub

  }

  @Override
  public void putHealthStateState(HealthStateIdentifier id, long size, long tick) {

    // TODO Auto-generated method stub

  }

  @Override
  public void putTransmissionExecution(TransmissionIdentifier id, long value, long tick) {

    // TODO Auto-generated method stub

  }

}
