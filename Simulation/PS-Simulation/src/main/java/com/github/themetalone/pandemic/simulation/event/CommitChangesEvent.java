package com.github.themetalone.pandemic.simulation.event;

/**
 * @author steffen
 *
 */
public class CommitChangesEvent extends PandemicSimulationEvent {

  /**
   * The constructor.
   * @param tick
   */
  public CommitChangesEvent(long tick) {
    super(tick);
  }

}
