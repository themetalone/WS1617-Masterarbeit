package com.github.themetalone.pandemic.simulation.transmission;

import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.utils.provider.Providable;

/**
 * @author steffen
 *
 */
public interface Transmission extends Providable {

  @Override
  TransmissionIdentifier getIdentifier();

  HealthStateIdentifier getSource();

  HealthStateIdentifier getTarget();

  void transmit();

}
