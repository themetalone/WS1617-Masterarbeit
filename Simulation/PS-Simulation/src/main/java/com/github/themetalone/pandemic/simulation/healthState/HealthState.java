package com.github.themetalone.pandemic.simulation.healthState;

import com.github.themetalone.pandemic.utils.provider.Providable;

/**
 * @author steffen
 *
 */
public interface HealthState extends Providable {

  @Override
  HealthStateIdentifier getIdentifier();

}
