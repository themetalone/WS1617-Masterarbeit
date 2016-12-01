package com.github.themetalone.pandemic.simulation.transmission;

import com.github.themetalone.pandemic.simulation.exceptions.NotEnoughIndividualsException;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateProvider;

/**
 * @author steffen
 *
 */
public abstract class TransmissionParent implements Transmission {

  private final TransmissionIdentifier ID;

  private long tick;

  /**
   * The constructor.
   */
  public TransmissionParent(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId) {
    this.ID = new TransmissionIdentifier(srcPopId, srcHsId, trgtPopId, trgtHsId);
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
  public void transmit() {

    long value = getValue();
    if (value < 0)
      throw new Error(
          "Transmission with " + this.ID.toString() + " calculated less than zero transmission volume " + value);
    try {
      HealthStateProvider.getInstance().get(getSource()).addSize(-value);
    } catch (NotEnoughIndividualsException e) {
      value = e.getOffset();
      try {
        HealthStateProvider.getInstance().get(getSource()).addSize(-value);
      } catch (NotEnoughIndividualsException e1) {
        throw new Error("This shouldn't have happened");
      }
    }
    try {
      HealthStateProvider.getInstance().get(getTarget()).addSize(value);
    } catch (NotEnoughIndividualsException e) {
      throw new Error("This shouldn't have happened");
    }

  }

  protected abstract long getValue();

  @Override
  public void setTick(long tick) {

    this.tick = tick;
  }

}
