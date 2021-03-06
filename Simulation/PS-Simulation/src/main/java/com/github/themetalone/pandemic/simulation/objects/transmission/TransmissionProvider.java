package com.github.themetalone.pandemic.simulation.objects.transmission;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.event.ExecuteTransmissionsEvent;
import com.github.themetalone.pandemic.simulation.event.PandemicSimulationEvent;
import com.github.themetalone.pandemic.simulation.objects.Provider;

/**
 * @author steffen
 *
 */
public class TransmissionProvider extends Provider<TransmissionIdentifier, Transmission> implements Observer {

  private final Logger LOG = LoggerFactory.getLogger("TransmissionProvider");

  private static TransmissionProvider instance;

  /**
   *
   * @param p provider
   */
  public static void setProvider(TransmissionProvider p) {

    instance = p;
  }

  /**
   *
   * @return TransmissionProvider
   * @throws Error if no provider is set
   */
  public static TransmissionProvider getInstance() {

    if (instance == null)
      throw new Error("No instance set");
    return instance;
  }

  /**
   * The constructor.
   *
   * @param targets that will be provided
   */
  public TransmissionProvider(Collection<Transmission> targets) {
    super(targets);
    instance = this;
    this.LOG.debug("Putting targets to Database");
    super.targets.stream().sorted((a, b) -> {
      if (a.getSource().POPULATION_ID == b.getSource().POPULATION_ID) {
        if (a.getSource().HEALTHSTATE_ID == b.getSource().HEALTHSTATE_ID) {
          if (a.getTarget().POPULATION_ID == b.getTarget().POPULATION_ID) {
            if (a.getTarget().HEALTHSTATE_ID == b.getTarget().HEALTHSTATE_ID) {
              if (a.getIdentifier().TYPE == b.getIdentifier().TYPE) {
                return a.getPriority() - b.getPriority();
              }
              return a.getIdentifier().TYPE - b.getIdentifier().TYPE;
            }
            return a.getTarget().HEALTHSTATE_ID - b.getTarget().HEALTHSTATE_ID;
          }
          return a.getTarget().POPULATION_ID - b.getTarget().POPULATION_ID;
        }
        return a.getSource().HEALTHSTATE_ID - b.getSource().HEALTHSTATE_ID;
      }
      return a.getSource().POPULATION_ID - b.getSource().POPULATION_ID;
    }).forEach(t -> PandemicSimulationDataWriterProvider.getWriter().putTransmission(t));
  }

  @Override
  public void update(Observable arg0, Object arg1) {

    if (arg1 == null || !(arg1 instanceof PandemicSimulationEvent))
      return;
    if (arg1 instanceof ExecuteTransmissionsEvent) {
      ExecuteTransmissionsEvent ete = (ExecuteTransmissionsEvent) arg1;

      this.targets.parallelStream().forEach(t -> t.setTick(ete.TICK));
      this.targets.stream().sorted().forEachOrdered(t -> {
        this.LOG.debug("Executing transmission {}", t.toString());
        t.transmit();
      });

    }
  }

}
