package com.github.themetalone.pandemic.simulation;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.event.CommitChangesEvent;
import com.github.themetalone.pandemic.simulation.event.ExecuteTransmissionsEvent;

/**
 * @author steffen
 *
 */
public class Simulation extends Observable {

  private final long SIMULATION_TIME;

  private static final Logger LOG = LoggerFactory.getLogger(Simulation.class);

  /**
   * The constructor.
   *
   * @param sIMULATION_TIME the duration of the simulation in discrete time steps
   */
  public Simulation(long sIMULATION_TIME) {
    super();
    this.SIMULATION_TIME = sIMULATION_TIME;
  }

  /**
   * Runs the simulation
   */
  public void run() {

    LOG.info("Running simulation for {} ticks", this.SIMULATION_TIME);
    LOG.debug("Currently known observers: {}", countObservers());
    for (long t = 0; t < this.SIMULATION_TIME; t++) {
      LOG.debug("tick {}", t);
      callTransmissions(t);
      callStates(t);
    }
    PandemicSimulationDataWriterProvider.getWriter().close();
  }

  /**
   * notifies all observers with a ExecuteTransmissionEvent and the current simulation time
   *
   * @param t
   */
  private void callTransmissions(long t) {

    LOG.debug("Calling transmissions at {}", t);
    setChanged();
    this.notifyObservers(new ExecuteTransmissionsEvent(t));
  }

  /**
   *
   * notifies all observers with a CommitChangesEvent and the current simulation time
   *
   * @param t
   */
  private void callStates(long t) {

    LOG.debug("Calling states at {}", t);
    setChanged();
    this.notifyObservers(new CommitChangesEvent(t));

  }

  public void setObservers(Collection<Observer> observers) {

    observers.parallelStream().forEach(o -> addObserver(o));
  }

}
