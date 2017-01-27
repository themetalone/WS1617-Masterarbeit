package com.github.themetalone.pandemic.simulation.objects.transmission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;

/**
 * @author steffen
 *
 */
public abstract class TransmissionParent implements Transmission {

  protected final TransmissionIdentifier ID;

  protected long tick;

  protected final int priority;

  protected final Logger LOG;

  /**
   * The constructor.
   */
  public TransmissionParent(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId, int type, int priority) {
    this.ID = new TransmissionIdentifier(srcPopId, srcHsId, trgtPopId, trgtHsId, type);
    this.priority = priority;
    this.LOG = LoggerFactory.getLogger(
        this.getClass().getSimpleName() + ":" + this.ID.SOURCE.POPULATION_ID + "." + this.ID.SOURCE.HEALTHSTATE_ID
            + "-(" + this.ID.TYPE + ")->" + this.ID.TARGET.POPULATION_ID + "." + this.ID.TARGET.HEALTHSTATE_ID);
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