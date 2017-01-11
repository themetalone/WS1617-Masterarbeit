package com.github.themetalone.pandemic.utils.configurator;

import javax.xml.bind.JAXBException;

import com.github.themetalone.pandemic.simulation.Simulation;
import com.github.themetalone.pandemic.utils.generated.SimulationType;

/**
 * @author steffen
 *
 */
public interface ConfigUtil {

  SimulationType parseConfig(String path) throws JAXBException;

  Simulation makeSimulation(SimulationType config);

}
