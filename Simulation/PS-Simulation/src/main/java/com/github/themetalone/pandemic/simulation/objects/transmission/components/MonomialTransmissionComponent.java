package com.github.themetalone.pandemic.simulation.objects.transmission.components;

import java.util.Collection;

import com.github.themetalone.pandemic.simulation.objects.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateProvider;

/**
 * @author steffen
 *
 */
public class MonomialTransmissionComponent implements TransmissionComponent {

  private final double SCALE;

  private final Collection<HealthStateIdentifier> STATES;

  /**
   * The constructor.
   */
  public MonomialTransmissionComponent(double scale, Collection<HealthStateIdentifier> healthStates) {

    this.SCALE = scale;
    this.STATES = healthStates;

  }

  @Override
  public long getValue() {

    return (long) (this.SCALE * this.STATES.parallelStream().map(hsi -> HealthStateProvider.getInstance().get(hsi))
        .mapToLong(HealthState::getSize).sum());
  }

}
