package com.github.themetalone.pandemic.simulation.event;

/**
 * @author steffen
 *
 */
public class PandemicSimulationEvent {

  public final long TICK;

  /**
   * The constructor.
   */
  public PandemicSimulationEvent(long tick) {
    this.TICK = tick;
  }

}
