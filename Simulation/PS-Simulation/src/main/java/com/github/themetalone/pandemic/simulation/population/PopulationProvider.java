package com.github.themetalone.pandemic.simulation.population;

import java.util.Collection;

import com.github.themetalone.pandemic.utils.provider.Provider;

/**
 * @author steffen
 *
 */
public class PopulationProvider extends Provider<Integer, Population> {

  private static PopulationProvider instance;

  /**
   * The constructor.
   *
   * @param targets
   */
  public PopulationProvider(Collection<Population> targets) {
    super(targets);
    instance = this;
  }

  /**
   * @return instance
   */
  public static PopulationProvider getInstance() {

    if (instance == null) {
      throw new Error("No PopulationProvider set");
    }
    return instance;
  }

}
