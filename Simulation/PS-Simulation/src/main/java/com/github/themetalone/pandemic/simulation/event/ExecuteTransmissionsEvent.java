package com.github.themetalone.pandemic.simulation.event;

/**
 * @author steffen
 *
 */
public class ExecuteTransmissionsEvent extends PandemicSimulationEvent {

  /**
   * The constructor.
   */
  public ExecuteTransmissionsEvent(long tick) {
    super(tick);
  }

}
