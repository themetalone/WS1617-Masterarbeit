package com.github.themetalone.pandemic.simulation.objects.healthState;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.event.CommitChangesEvent;
import com.github.themetalone.pandemic.simulation.event.PandemicSimulationEvent;
import com.github.themetalone.pandemic.simulation.objects.Provider;

/**
 * @author steffen
 *
 */
public class HealthStateProvider extends Provider<HealthStateIdentifier, HealthState> implements Observer {

  private static HealthStateProvider instance;

  /**
   * @param p a provider
   */
  public static void setInstance(HealthStateProvider p) {

    instance = p;
  }

  /**
   * @return HealthStateProvider
   * @throws Error if no provider is set
   */
  public static HealthStateProvider getInstance() {

    if (instance == null)
      throw new Error("No instance set");
    return instance;
  }

  /**
   * The constructor.
   *
   * registers itself as the current provider
   *
   * @param targets the HealtStates this provider provides
   */
  public HealthStateProvider(Collection<HealthState> targets) {
    super(targets);
    instance = this;
    super.targets.stream().forEach(hs -> PandemicSimulationDataWriterProvider.getWriter().putHealthState(hs));
  }

  @Override
  public void update(Observable o, Object arg) {

    if (arg == null || !(arg instanceof PandemicSimulationEvent))
      return;
    if (arg instanceof CommitChangesEvent) {

      this.targets.stream().forEach(h -> h.setTick(((CommitChangesEvent) arg).TICK));

      this.targets.stream().forEach(h -> h.applyChanges());
    }

  }

}
