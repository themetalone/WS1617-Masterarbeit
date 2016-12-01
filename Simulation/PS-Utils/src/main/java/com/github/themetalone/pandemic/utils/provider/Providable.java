package com.github.themetalone.pandemic.utils.provider;

/**
 * @author steffen
 *
 */
public interface Providable {

  Object getIdentifier();

  void setTick(long tick);

}
