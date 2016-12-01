package com.github.themetalone.pandemic.simulation.healthState;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import com.github.themetalone.pandemic.simulation.event.CommitChangesEvent;
import com.github.themetalone.pandemic.simulation.event.PandemicSimulationEvent;
import com.github.themetalone.pandemic.utils.provider.Provider;

/**
 * @author steffen
 *
 */
public class HealthStateProvider extends Provider<HealthStateIdentifier, HealthState> implements Observer {

  private static HealthStateProvider instance;

  public static void setInstance(HealthStateProvider p) {

    instance = p;
  }

  public static HealthStateProvider getInstance() {

    if (instance == null)
      throw new Error("No instance set");
    return instance;
  }

  /**
   * The constructor.
   *
   *
   * @param targets
   */
  public HealthStateProvider(Collection<HealthState> targets) {
    super(targets);
    instance = this;
  }

  @Override
  public void update(Observable o, Object arg) {

    if (arg == null || !(arg instanceof PandemicSimulationEvent))
      return;
    if (arg instanceof CommitChangesEvent) {

      this.targets.parallelStream().forEach(h -> h.setTick(((CommitChangesEvent) arg).TICK));

      this.targets.parallelStream().forEach(h -> h.applyChanges());
    }

  }

}
