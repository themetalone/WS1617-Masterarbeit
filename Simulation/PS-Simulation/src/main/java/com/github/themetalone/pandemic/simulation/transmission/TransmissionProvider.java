package com.github.themetalone.pandemic.simulation.transmission;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import com.github.themetalone.pandemic.simulation.event.ExecuteTransmissionsEvent;
import com.github.themetalone.pandemic.simulation.event.PandemicSimulationEvent;
import com.github.themetalone.pandemic.utils.provider.Provider;

/**
 * @author steffen
 *
 */
public class TransmissionProvider extends Provider<TransmissionIdentifier, Transmission> implements Observer {

  private static TransmissionProvider instance;

  public static void setProvider(TransmissionProvider p) {

    instance = p;
  }

  public static TransmissionProvider getInstance() {

    if (instance == null)
      throw new Error("No instance set");
    return instance;
  }

  /**
   * The constructor.
   *
   * @param targets
   */
  public TransmissionProvider(Collection<Transmission> targets) {
    super(targets);
  }

  @Override
  public void update(Observable arg0, Object arg1) {

    if (arg1 == null || !(arg1 instanceof PandemicSimulationEvent))
      return;
    if (arg1 instanceof ExecuteTransmissionsEvent) {
      ExecuteTransmissionsEvent ete = (ExecuteTransmissionsEvent) arg1;

      this.targets.parallelStream().forEach(t -> t.setTick(ete.TICK));

      this.targets.parallelStream().forEach(t -> t.transmit());

    }
  }

}
