package com.github.themetalone.pandemic.simulation.transmission;

import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;

/**
 * @author steffen
 *
 */
public class TransmissionIdentifier {

  public final HealthStateIdentifier SOURCE;

  public final HealthStateIdentifier TARGET;

  public final int TYPE;

  /**
   * The constructor.
   *
   * @param sOURCE
   * @param tARGET
   * @param tYPE
   */
  public TransmissionIdentifier(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId, int tYPE) {
    super();
    this.SOURCE = new HealthStateIdentifier(srcPopId, srcHsId);
    this.TARGET = new HealthStateIdentifier(trgtPopId, trgtHsId);
    this.TYPE = tYPE;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.SOURCE == null) ? 0 : this.SOURCE.hashCode());
    result = prime * result + ((this.TARGET == null) ? 0 : this.TARGET.hashCode());
    result = prime * result + this.TYPE;
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof TransmissionIdentifier))
      return false;
    TransmissionIdentifier other = (TransmissionIdentifier) obj;
    if (this.SOURCE == null) {
      if (other.SOURCE != null)
        return false;
    } else if (!this.SOURCE.equals(other.SOURCE))
      return false;
    if (this.TARGET == null) {
      if (other.TARGET != null)
        return false;
    } else if (!this.TARGET.equals(other.TARGET))
      return false;
    if (this.TYPE != other.TYPE)
      return false;
    return true;
  }

  @Override
  public String toString() {

    return "TransmissionIdentifier [SOURCE=" + this.SOURCE + ", TARGET=" + this.TARGET + ", TYPE=" + this.TYPE + "]";
  }

}
