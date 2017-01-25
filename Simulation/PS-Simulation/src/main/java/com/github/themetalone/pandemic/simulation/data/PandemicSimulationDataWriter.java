package com.github.themetalone.pandemic.simulation.data;

import com.github.themetalone.pandemic.simulation.objects.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.population.Population;
import com.github.themetalone.pandemic.simulation.objects.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.TransmissionIdentifier;

/**
 * @author steffen
 *
 */
public interface PandemicSimulationDataWriter {

  void putPopulation(Population p);

  /**
   * @param s to be added to the data storage.
   */
  void putHealthState(HealthState s);

  /**
   *
   * @param t to be added to the data storage
   */
  void putTransmission(Transmission t);

  /**
   *
   * @param id of the HealthState
   * @param size of the HealthState
   * @param tick current time of the simulation
   */
  void putHealthStateState(HealthStateIdentifier id, long size, long tick);

  /**
   *
   * @param id of the transmission
   * @param value the transmitted value
   * @param tick current time of the simulation
   */
  void putTransmissionExecution(TransmissionIdentifier id, long value, long tick);

  /**
   * Tells the DataWriter to finish open tasks
   */
  void close();

}
