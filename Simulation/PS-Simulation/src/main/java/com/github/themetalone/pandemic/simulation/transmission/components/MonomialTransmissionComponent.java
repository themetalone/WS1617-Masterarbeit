package com.github.themetalone.pandemic.simulation.transmission.components;

import java.util.Collection;

import com.github.themetalone.pandemic.simulation.healthState.HealthState;

/**
 * @author steffen
 *
 */
public class MonomialTransmissionComponent implements TransmissionComponent {

  private final double SCALE;

  private final Collection<HealthState> STATES;

  /**
   * The constructor.
   */
  public MonomialTransmissionComponent(double scale, Collection<HealthState> healthStates) {

    this.SCALE = scale;
    this.STATES = healthStates;

  }

  @Override
  public long getValue() {

    return (long) (this.SCALE * this.STATES.parallelStream().mapToLong(HealthState::getSize).sum());
  }

}
