package com.github.themetalone.pandemic.simulation.objects.population;

import java.util.Collection;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.objects.Provider;

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
    targets.stream().forEach(p -> PandemicSimulationDataWriterProvider.getWriter().putPopulation(p));
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
