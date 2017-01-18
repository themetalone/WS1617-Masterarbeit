package com.github.themetalone.pandemic.products.simulation;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import com.github.themetalone.pandemic.products.SystemProperties;
import com.github.themetalone.pandemic.simulation.Simulation;
import com.github.themetalone.pandemic.utils.configurator.ConfigUtil;
import com.github.themetalone.pandemic.utils.configurator.ConfigUtilImpl;

/**
 * @author steffen
 *
 */
public class SimulationMain {

  /**
   * The constructor.
   */
  public SimulationMain() {
  }

  /**
   * @param args
   * @throws JAXBException
   */
  public static void main(String[] args) throws JAXBException {

    String configProperty = System.getProperty(SystemProperties.CONFIG_PROPERTY);
    if (configProperty == null) {
      throw new Error("No configuration file specified. Do so with the -D" + SystemProperties.CONFIG_PROPERTY
          + " flag. e.g. java -jar -D" + SystemProperties.CONFIG_PROPERTY
          + "=/path/to/config.xml PandemicSimulation.jar");
    }
    if (!Files.exists(Paths.get(configProperty))) {
      throw new Error("Specified Config \"" + configProperty + "\" isn't a file");
    }
    ConfigUtil configUtil = new ConfigUtilImpl();
    Simulation simulation = configUtil.makeSimulation(configUtil.parseConfig(configProperty));
    simulation.run();
  }

}
