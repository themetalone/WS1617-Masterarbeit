package com.github.themetalone.pandemic.simulation.objects;

/**
 * @author steffen
 *
 */
public interface Providable {

  Object getIdentifier();

  void setTick(long tick);

}
