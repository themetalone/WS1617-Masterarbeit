package com.github.themetalone.pandemic.simulation;

import java.util.Observable;

import com.github.themetalone.pandemic.simulation.event.CommitChangesEvent;
import com.github.themetalone.pandemic.simulation.event.ExecuteTransmissionsEvent;

/**
 * @author steffen
 *
 */
public class Simulation extends Observable {

  private final long SIMULATION_TIME;

  /**
   * The constructor.
   *
   * @param sIMULATION_TIME
   */
  public Simulation(long sIMULATION_TIME) {
    super();
    this.SIMULATION_TIME = sIMULATION_TIME;
  }

  public void run() {

    for (long t = 0; t < this.SIMULATION_TIME; t++) {
      callTransmissions(t);
      callStates(t);
    }
  }

  private void callTransmissions(long t) {

    setChanged();
    this.notifyObservers(new ExecuteTransmissionsEvent(t));
  }

  private void callStates(long t) {

    setChanged();
    this.notifyObservers(new CommitChangesEvent(t));

  }

}
