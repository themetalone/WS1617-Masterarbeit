package com.github.themetalone.pandemic.utils.configurator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.github.themetalone.pandemic.simulation.Simulation;
import com.github.themetalone.pandemic.simulation.data.H2SQLConnector;
import com.github.themetalone.pandemic.simulation.data.H2SqlPandemicSimulationDataWriter;
import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateProvider;
import com.github.themetalone.pandemic.simulation.objects.healthState.SimpleHealthState;
import com.github.themetalone.pandemic.simulation.objects.population.Population;
import com.github.themetalone.pandemic.simulation.objects.population.PopulationProvider;
import com.github.themetalone.pandemic.simulation.objects.transmission.InnerTransmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.TransmissionProvider;
import com.github.themetalone.pandemic.simulation.objects.transmission.components.MonomialTransmissionComponent;
import com.github.themetalone.pandemic.simulation.objects.transmission.components.TransmissionComponent;
import com.github.themetalone.pandemic.utils.generated.PopulationType;
import com.github.themetalone.pandemic.utils.generated.SimulationType;

/**
 * @author steffen
 *
 */
public class ConfigUtilImpl implements ConfigUtil {

  /**
   * The constructor.
   */
  public ConfigUtilImpl() {
  }

  @Override
  public SimulationType parseConfig(String path) throws JAXBException {

    Path filePath = Paths.get(path);

    Unmarshaller um = JAXBContext.newInstance("com.github.themetalone.pandemic.utils.generated").createUnmarshaller();
    SimulationType simType = (SimulationType) um.unmarshal(filePath.toFile());

    return simType;
  }

  @Override
  public Simulation makeSimulation(SimulationType config) {

    // Init
    Map<PopulationType, Integer> popIdMap = new HashMap<>();
    Map<String, Integer> hsIdMap = new HashMap<>();
    Collection<Population> populations = new HashSet<>();
    Collection<HealthState> healthstates = new HashSet<>();
    Collection<Transmission> transmissions = new HashSet<>();

    PopulationType standardPopulation = config.getPopulationen().getStandard();

    // Simulation Object
    Simulation simulation = new Simulation(config.getZeit());
    // Create Id's
    config.getPopulationen().getPopulation().stream().forEach(p -> popIdMap.put(p, popIdMap.size()));
    standardPopulation.getSubpopulation().stream().forEach(sp -> hsIdMap.put(sp.getName(), hsIdMap.size()));

    // Give the PopulationTypes the standard configuration but do not override existing subpopulations
    config.getPopulationen().getPopulation().stream().forEach(p -> {
      if (p.getLebensstandard() == null) {
        p.setLebensstandard(standardPopulation.getLebensstandard());
      }
      if (p.getMigrationsanteil() == null) {
        p.setMigrationsanteil(standardPopulation.getMigrationsanteil());
      }
      //// Set subpopulations if not present
      standardPopulation.getSubpopulation().forEach(sp -> {
        if (!p.getSubpopulation().stream().filter(psp -> psp.getName().equals(sp.getName())).findAny().isPresent()) {
          p.getSubpopulation().add(sp);
        } else {
          // TODO default values. same by transmissions
        }
      });
      //// Set transmissions if not present
      standardPopulation.getUebergang().forEach(t -> {
        if (!p.getUebergang().stream().filter(pt -> pt.getVon().equals(t.getVon()) && pt.getNach().equals(t.getNach()))
            .findAny().isPresent())
          p.getUebergang().add(t);
      });
    });

    // Create Nodes
    config.getPopulationen().getPopulation().stream().forEach(p -> {

      Collection<Integer> infectousStates = new HashSet<>();
      Collection<Integer> livingStates = new HashSet<>();

      p.getSubpopulation().stream().forEach(sp -> {
        if (hsIdMap.get(sp.getName()) == null) {
          hsIdMap.put(sp.getName(), hsIdMap.size());
        }
        healthstates
            .add(new SimpleHealthState(popIdMap.get(p), hsIdMap.get(sp.getName()), sp.getName(), sp.getGroesse()));
        if (sp.isLebend()) {
          livingStates.add(hsIdMap.get(sp.getName()));
        }
        if (sp.isSichtbarInfiziert()) {
          infectousStates.add(hsIdMap.get(sp.getName()));
        }
      });
      populations.add(new Population(p.getName(), p.getLebensstandard(), p.getMigrationsanteil(), popIdMap.get(p),
          livingStates, infectousStates));
    });

    // Create inner transmissions
    config.getPopulationen().getPopulation().stream().forEach(p -> p.getUebergang().stream().forEach(t -> {
      Collection<TransmissionComponent> components = new HashSet<>();
      t.getKomponente().stream().forEach(c -> {
        Collection<HealthStateIdentifier> refs = new HashSet<>();
        c.getReference().stream().map(r -> new HealthStateIdentifier(popIdMap.get(p), hsIdMap.get(r)))
            .forEach(refs::add);
        components.add(new MonomialTransmissionComponent(c.getScalar(), refs));
      });
      transmissions.add(new InnerTransmission(popIdMap.get(p), hsIdMap.get(t.getVon()), popIdMap.get(p),
          hsIdMap.get(t.getNach()), 0, 0, components));
    }));

    // travel edges

    // TODO above
    // Populate Providers
    PandemicSimulationDataWriterProvider.setWriter(
        new H2SqlPandemicSimulationDataWriter(new H2SQLConnector(config.getDatenbank()), config.getBatchgroesse()));
    new PopulationProvider(populations);
    new HealthStateProvider(healthstates);
    new TransmissionProvider(transmissions);
    return simulation;
  }

}
