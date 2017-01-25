package com.github.themetalone.pandemic.simulation.objects.transmission;

import com.github.themetalone.pandemic.simulation.objects.population.Population;
import com.github.themetalone.pandemic.simulation.objects.population.PopulationProvider;

/**
 * @author steffen
 *
 */
public class MigrationTransmission extends TransmissionParent {

  protected final float severity;

  protected final float travelProportion;

  /**
   * The constructor.
   *
   * @param srcPopId
   * @param srcHsId
   * @param trgtPopId
   * @param trgtHsId
   * @param type
   * @param priority
   */
  public MigrationTransmission(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId, int type, int priority,
      float travelProportion, float severity) {
    super(srcPopId, srcHsId, trgtPopId, trgtHsId, type, priority);
    this.severity = severity;
    this.travelProportion = travelProportion;
  }

  @Override
  public void transmit() {

    Population src = PopulationProvider.getInstance().get(super.ID.SOURCE.POPULATION_ID);
    Population trg = PopulationProvider.getInstance().get(super.ID.TARGET.POPULATION_ID);

    double ia = src.infectedPopulation() / src.livingPopulation();
    double ib = trg.infectedPopulation() / trg.livingPopulation();

    double infection = Math.pow(ia / ib, this.severity);

    double value =
        (trg.LIFE_STANDARD / src.LIFE_STANDARD) * (src.MIGRATION_PROPORTION) * this.travelProportion * infection;

  }

  @Override
  public String toString() {

    return "Travel:Proportion=" + this.travelProportion + ";Severity:" + this.severity;
  }

}
