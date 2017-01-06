package com.github.themetalone.pandemic.simulation.transmission;

import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;

/**
 * @author steffen
 *
 */
public abstract class TransmissionParent implements Transmission {

  protected final TransmissionIdentifier ID;

  protected long tick;

  protected final int priority;

  /**
   * The constructor.
   */
  public TransmissionParent(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId, int type, int priority) {
    this.ID = new TransmissionIdentifier(srcPopId, srcHsId, trgtPopId, trgtHsId, type);
    this.priority = priority;
  }

  @Override
  public TransmissionIdentifier getIdentifier() {

    return this.ID;
  }

  @Override
  public HealthStateIdentifier getSource() {

    return this.ID.SOURCE;
  }

  @Override
  public HealthStateIdentifier getTarget() {

    return this.ID.TARGET;
  }

  @Override
  public void setTick(long tick) {

    this.tick = tick;
  }

  /**
   * @return priority
   */
  @Override
  public int getPriority() {

    return this.priority;
  }

  @Override
  public int compareTo(Transmission o) {

    return this.priority - o.getPriority();
  }

}