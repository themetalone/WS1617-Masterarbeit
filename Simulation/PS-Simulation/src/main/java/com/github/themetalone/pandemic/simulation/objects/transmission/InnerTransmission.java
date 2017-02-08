package com.github.themetalone.pandemic.simulation.objects.transmission;

import java.util.Collection;

import com.github.themetalone.pandemic.simulation.objects.transmission.components.TransmissionComponent;

/**
 * @author steffen
 *
 */
public class InnerTransmission extends TransmissionParent {

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
  public InnerTransmission(int srcPopId, int srcHsId, int trgtPopId, int trgtHsId, int type, int priority,
      Collection<TransmissionComponent> components) {
    super(srcPopId, srcHsId, trgtPopId, trgtHsId, type, priority);
    this.COMPONENTS = components;
  }

  /**
   * @return the summed up value of all components
   */
  @Override
  protected long getValue() {

    return this.COMPONENTS.parallelStream().mapToLong(TransmissionComponent::getValue).sum();
  }

  @Override
  public String toString() {

    return "Transition:" + this.COMPONENTS.stream().map(c -> c.toString()).reduce("", (s, t) -> s + "+" + t);
  }

}
