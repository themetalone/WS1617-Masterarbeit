package com.github.themetalone.pandemic.simulation.transmission;

import java.util.Collection;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.exceptions.NotEnoughIndividualsException;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateProvider;
import com.github.themetalone.pandemic.simulation.transmission.components.TransmissionComponent;

/**
 * @author steffen
 *
 */
public class TransmissionImpl implements Transmission {

  private final TransmissionIdentifier ID;

  private long tick;

  private final int priority;

  private final Collection<TransmissionComponent> COMPONENTS;

  /**
   *
   * The constructor.
   *
   * @param srcPopId the population id of the source health state
   * @param srcHsId the health state id of the source health state
   * @param trgtPopId the population id of the target health state
   * @param trgtHsId the health state id of the target health state
   * @param type the type of the transmission. Used to distinguish between different Transmissions between the same
   *        health states
   * @param priority low values are executed first
   * @param components {@link Collection}&lt;{@link TransmissionComponent}> the components of this transmission
   */
  public TransmissionImpl(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId, int type, int priority,
      Collection<TransmissionComponent> components) {
    this.ID = new TransmissionIdentifier(srcPopId, srcHsId, trgtPopId, trgtHsId, type);
    this.priority = priority;
    this.COMPONENTS = components;
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
    PandemicSimulationDataWriterProvider.getWriter().putTransmissionExecution(this.ID, value, this.tick);

  }

  @Override
  public void setTick(long tick) {

    this.tick = tick;
  }

  /**
   * @return the summed up value of all components
   */
  protected long getValue() {

    return this.COMPONENTS.parallelStream().mapToLong(TransmissionComponent::getValue).sum();
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
