package com.github.themetalone.pandemic.simulation.healthState;

/**
 * @author steffen
 *
 */
public final class HealthStateIdentifier {

  /**
   *
   */
  public final int POPULATION_ID;

  /**
   *
   */
  public final int HEALTHSTATE_ID;

  /**
   * The constructor.
   *
   * @param pOPULATION_ID to identify the population
   * @param hEALTHSTATE_ID to identify the healthState
   */
  public HealthStateIdentifier(int pOPULATION_ID, int hEALTHSTATE_ID) {
    super();
    this.POPULATION_ID = pOPULATION_ID;
    this.HEALTHSTATE_ID = hEALTHSTATE_ID;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + this.HEALTHSTATE_ID;
    result = prime * result + this.POPULATION_ID;
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HealthStateIdentifier other = (HealthStateIdentifier) obj;
    if (this.HEALTHSTATE_ID != other.HEALTHSTATE_ID)
      return false;
    if (this.POPULATION_ID != other.POPULATION_ID)
      return false;
    return true;
  }

  @Override
  public String toString() {

    return "HealthStateIdentifier [POPULATION_ID=" + this.POPULATION_ID + ", HEALTHSTATE_ID=" + this.HEALTHSTATE_ID
        + "]";
  }

}
