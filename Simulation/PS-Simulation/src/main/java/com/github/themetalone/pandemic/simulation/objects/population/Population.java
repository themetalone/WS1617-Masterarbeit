package com.github.themetalone.pandemic.simulation.objects.population;

import java.util.Collection;

import com.github.themetalone.pandemic.simulation.objects.Providable;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateProvider;

/**
 * @author steffen
 *
 */
public class Population implements Providable {

  public final String NAME;

  public final float LIFE_STANDARD;

  public final float MIGRATION_PROPORTION;

  public final int POPULATION_ID;

  protected final Collection<Integer> livingStates;

  protected final Collection<Integer> infectedStates;

  /**
   * The constructor.
   *
   * @param lIFE_STANDARD
   * @param mIGRATION_PROPORTION
   * @param pOPULATION_ID
   */
  public Population(String name, float lIFE_STANDARD, float mIGRATION_PROPORTION, int pOPULATION_ID,
      Collection<Integer> livingStates, Collection<Integer> infectedStates) {
    super();
    this.NAME = name;
    this.LIFE_STANDARD = lIFE_STANDARD;
    this.MIGRATION_PROPORTION = mIGRATION_PROPORTION;
    this.POPULATION_ID = pOPULATION_ID;
    this.infectedStates = infectedStates;
    this.livingStates = livingStates;
  }

  @Override
  public Integer getIdentifier() {

    return this.POPULATION_ID;
  }

  @Override
  public void setTick(long tick) {

  }

  public long livingPopulation() {

    return this.livingStates.stream()
        .mapToLong(
            i -> HealthStateProvider.getInstance().get(new HealthStateIdentifier(this.POPULATION_ID, i)).getSize())
        .sum();
  }

  public long infectedPopulation() {

    return this.infectedStates.stream()
        .mapToLong(
            i -> HealthStateProvider.getInstance().get(new HealthStateIdentifier(this.POPULATION_ID, i)).getSize())
        .sum();
  }

}
