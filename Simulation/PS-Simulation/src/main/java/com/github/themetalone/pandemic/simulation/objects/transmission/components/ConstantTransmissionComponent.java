package com.github.themetalone.pandemic.simulation.objects.transmission.components;

/**
 * @author Steffen
 *
 */
public class ConstantTransmissionComponent implements TransmissionComponent {

  private final long constant;

  /**
   * The constructor.
   */
  public ConstantTransmissionComponent(long c) {
    this.constant = c;
  }

  @Override
  public long getValue() {

    return this.constant;
  }

}
