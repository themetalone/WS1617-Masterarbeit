package com.github.themetalone.pandemic.simulation.objects.transmission;

import com.github.themetalone.pandemic.simulation.objects.Providable;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;

/**
 * @author steffen
 *
 */
public interface Transmission extends Providable, Comparable<Transmission> {

  @Override
  TransmissionIdentifier getIdentifier();

  HealthStateIdentifier getSource();

  HealthStateIdentifier getTarget();

  void transmit();

  int getPriority();

}
