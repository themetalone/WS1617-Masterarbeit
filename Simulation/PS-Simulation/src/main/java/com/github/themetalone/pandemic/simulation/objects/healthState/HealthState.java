package com.github.themetalone.pandemic.simulation.objects.healthState;

import com.github.themetalone.pandemic.simulation.exceptions.NotEnoughIndividualsException;
import com.github.themetalone.pandemic.simulation.objects.Providable;

/**
 * @author steffen
 *
 */
public interface HealthState extends Providable {

  @Override
  HealthStateIdentifier getIdentifier();

  String getName();

  long getSize();

  void addSize(long addition) throws NotEnoughIndividualsException;

  void applyChanges();

}
