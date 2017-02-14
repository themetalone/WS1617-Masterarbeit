package com.github.themetalone.pandemic.utils.configurator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.Simulation;
import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.data.csv.CsvPandemicDataWriter;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateProvider;
import com.github.themetalone.pandemic.simulation.objects.healthState.SimpleHealthState;
import com.github.themetalone.pandemic.simulation.objects.population.Population;
import com.github.themetalone.pandemic.simulation.objects.population.PopulationProvider;
import com.github.themetalone.pandemic.simulation.objects.transmission.InnerTransmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.MigrationTransmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.TransmissionProvider;
import com.github.themetalone.pandemic.simulation.objects.transmission.components.ConstantTransmissionComponent;
import com.github.themetalone.pandemic.simulation.objects.transmission.components.MonomialTransmissionComponent;
import com.github.themetalone.pandemic.simulation.objects.transmission.components.TransmissionComponent;
import com.github.themetalone.pandemic.utils.generated.PopulationType;
import com.github.themetalone.pandemic.utils.generated.SimulationType;
import com.github.themetalone.pandemic.utils.generated.UebergangType;

/**
 * @author steffen
 *
 */
public class ConfigUtilImpl implements ConfigUtil {

  public final static int INNERTRANSMISSION = 0;

  public final static int TRAVELBYPLANE = 1;

  public final static int OTHERTRAVEL = 2;

  private final Logger LOG = LoggerFactory.getLogger("ConfigUtil");

  /**
   * The constructor.
   */
  public ConfigUtilImpl() {
  }

  @Override
  public SimulationType parseConfig(String path) throws JAXBException {

    Path filePath = Paths.get(path);

    Unmarshaller um = JAXBContext.newInstance("com.github.themetalone.pandemic.utils.generated").createUnmarshaller();
    Object object = um.unmarshal(filePath.toFile());
    if (object instanceof JAXBElement && ((JAXBElement<?>) object).getValue() instanceof SimulationType) {
      return (SimulationType) ((JAXBElement<?>) object).getValue();
    }
    throw new Error("Could not parse " + path + " as Pandemic Simulation Config");
  }

  @Override
  public Simulation makeSimulation(SimulationType config) {

    // Init
    Map<PopulationType, Integer> popIdMap = new TreeMap<>((a, b) -> a.getName().compareTo(b.getName()));
    Map<String, Integer> hsIdMap = new LinkedHashMap<>();
    Collection<Population> populations = new LinkedList<>();
    Collection<HealthState> healthstates = new LinkedList<>();
    Collection<Transmission> transmissions = new LinkedList<>();
    Map<Integer, Float> popIdMigrationCorrectionMap = new TreeMap<>();

    PopulationType standardPopulation = config.getPopulationen().getStandard();

    // Simulation Object
    Simulation simulation = new Simulation(config.getZeit());
    // Create Id'S
    config.getPopulationen().getPopulation().stream()
        .forEach(p -> popIdMap.put(p, config.getPopulationen().getPopulation().indexOf(p)));
    standardPopulation.getSubpopulation().stream()
        .forEach(sp -> hsIdMap.put(sp.getName(), standardPopulation.getSubpopulation().indexOf(sp)));
    // sanity checks
    //// travel percentage
    config.getPopulationen().getPopulation().stream().forEach(srcP -> {
      this.LOG.debug("Calculating travel volumina for {}", srcP.getName());
      float travelPercentage = config.getRouten().getRoute().stream().filter(r -> r.getVon().equals(srcP.getName()))
          .map(r -> r.getAnteil()).reduce(new Float(0), (a, b) -> a + b);
      if (travelPercentage > 1) {
        float correction = 1 / travelPercentage;
        this.LOG.debug("Travel volumina is {}, correcting it by {}", travelPercentage, correction);
        config.getRouten().getRoute().stream().filter(r -> r.getVon().equals(srcP.getName()))
            .forEach(r -> r.setAnteil(r.getAnteil() * correction));
      }
    });

    // Give the PopulationTypes the standard configuration but do not override existing configurations
    config.getPopulationen().getPopulation().stream().forEach(p -> {
      if (p.getLebensstandard() == 0) {
        p.setLebensstandard(standardPopulation.getLebensstandard());
      }
      if (p.getMigrationsanteil() == 0) {
        p.setMigrationsanteil(standardPopulation.getMigrationsanteil());
      }
      //// Set subpopulations if not present
      standardPopulation.getSubpopulation().forEach(sp -> {
        if (!p.getSubpopulation().stream().filter(psp -> psp.getName().equals(sp.getName())).findAny().isPresent()) {
          p.getSubpopulation().add(sp);
        }
      });
      //// Set transmissions if not present
      standardPopulation.getUebergang().forEach(t -> {
        if (!p.getUebergang().stream().filter(pt -> pt.getVon().equals(t.getVon()) && pt.getNach().equals(t.getNach()))
            .findAny().isPresent()) {
          p.getUebergang().add(t);
        } else {
          // If the inner transmission is already there just fill in the gaps
          UebergangType existingT = p.getUebergang().stream()
              .filter(pt -> pt.getVon().equals(t.getVon()) && pt.getNach().equals(t.getNach())).findAny().get();
          if (existingT.getKomponente().isEmpty() && !t.getKomponente().isEmpty()) {
            existingT.getKomponente().addAll(t.getKomponente());
          }
        }
      });
    });

    // Create Nodes
    config.getPopulationen().getPopulation().stream().forEach(p -> {

      Collection<Integer> infectousStates = new LinkedList<>();
      Collection<Integer> livingStates = new LinkedList<>();

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
      Collection<TransmissionComponent> components = new LinkedList<>();
      t.getKomponente().stream().forEach(c -> {
        Collection<HealthStateIdentifier> refs = new LinkedList<>();
        c.getReference().stream().map(r -> new HealthStateIdentifier(popIdMap.get(p), hsIdMap.get(r)))
            .forEach(refs::add);
        components.add(c.getKonstante() > 0 ? new ConstantTransmissionComponent(c.getKonstante())
            : new MonomialTransmissionComponent(c.getScalar(), refs));
      });
      transmissions.add(new InnerTransmission(popIdMap.get(p), hsIdMap.get(t.getVon()), popIdMap.get(p),
          hsIdMap.get(t.getNach()), INNERTRANSMISSION, 0, components));
    }));

    // travel edges
    Collection<String> travelingSubpopulationNames =
        parseTravelSubpopulations(config.getRouten().getReisendeSubpopulationen());
    // Make Population Id Name Maps
    Map<String, Integer> popNameIdMap = new TreeMap<>((a, b) -> a.compareTo(b));
    Map<Integer, String> popIdNameMap = new TreeMap<>();
    config.getPopulationen().getPopulation().stream().forEach(p -> {
      popNameIdMap.put(p.getName(), popIdMap.get(p));
      popIdNameMap.put(popIdMap.get(p), p.getName());
    });

    // make migration correction
    config.getPopulationen().getPopulation().stream().forEach(p -> {
      // sum of all neighbors life standards
      float lsSum = config.getRouten().getRoute().stream()
          .filter(r -> r.getNach().equals(p.getName())).map(r -> popIdMap.keySet().stream()
              .filter(filterPop -> filterPop.getName().equals(r.getNach())).findAny().get())
          .map(targetPop -> targetPop.getLebensstandard()).reduce(0F, (a, b) -> a + b).floatValue();
      this.LOG.debug("Migration correction for {}: ls:{} lsum:{} final value:{}", p.getName(), p.getLebensstandard(),
          lsSum, 1 / (lsSum / p.getLebensstandard()));
      popIdMigrationCorrectionMap.put(popIdMap.get(p), 1 / (lsSum / p.getLebensstandard()));
    });

    config.getRouten().getRoute().stream().forEach(r -> {
      // If no additional mapping is given
      if (r.getZuordnung().isEmpty()) {
        travelingSubpopulationNames.stream()
            .forEach(sp -> transmissions.add(new MigrationTransmission(popNameIdMap.get(r.getVon()), hsIdMap.get(sp),
                popNameIdMap.get(r.getNach()), hsIdMap.get(sp), r.isFlug() ? TRAVELBYPLANE : OTHERTRAVEL, 1,
                r.getAnteil() * popIdMigrationCorrectionMap.get(popNameIdMap.get(r.getVon())), config.getKrankheit())));
      } else {
        r.getZuordnung().stream()
            .forEach(mapping -> transmissions
                .add(new MigrationTransmission(popNameIdMap.get(r.getVon()), hsIdMap.get(mapping.getVonSubpopulation()),
                    popNameIdMap.get(r.getNach()), hsIdMap.get(mapping.getNachSubpopulation()),
                    r.isFlug() ? TRAVELBYPLANE : OTHERTRAVEL, 1, r.getAnteil(), config.getKrankheit())));
      }
    });
    // If a global plane network is requested
    if (config.getRouten().isFlugverkehr()) {
      populations.stream().forEach(srcP -> {
        // Filter all populations that are not connected via plane to srcP
        Predicate<Population> unconnectedPopulationFilter = filterP -> filterP.POPULATION_ID != srcP.POPULATION_ID
            && /* ...that has no travel by plane edge coming from src */!transmissions.stream()
                .filter(t -> t.getIdentifier().TYPE == TRAVELBYPLANE
                    && t.getIdentifier().SOURCE.POPULATION_ID == srcP.POPULATION_ID
                    && t.getIdentifier().TARGET.POPULATION_ID == filterP.POPULATION_ID)
                .findAny().isPresent();
        // number of unconnected other populations
        int unconnectedPopulations = populations.stream()
            /* all other populations ... */
            .filter(unconnectedPopulationFilter).mapToInt(p -> new Integer(1)).sum();
        // defined percentage of traveling population from srcP. Well defined if less or equal 1
        float connectionLevel =
            config.getRouten().getRoute().stream().filter(r -> r.getVon().equals(popIdNameMap.get(srcP.POPULATION_ID)))
                .map(r -> r.getAnteil()).reduce(new Float(0), (a, b) -> a + b);
        // connectionLevel > 1 should be impossible by sanity checks above
        if (connectionLevel < 1) {
          // travel percentage of what a single travel by plane route can
          float flightPercentage = (1 - connectionLevel) / unconnectedPopulations;
          // add a route from srcP to each unconnected population for each traveling subpopulation
          populations.stream().filter(unconnectedPopulationFilter)
              .forEach(trgP -> travelingSubpopulationNames.stream().map(s -> hsIdMap.get(s)).forEach(hs -> {
                transmissions.add(new MigrationTransmission(srcP.POPULATION_ID, hs, trgP.POPULATION_ID, hs,
                    TRAVELBYPLANE, 1, flightPercentage, config.getKrankheit()));
              }));
        }

      });
    }
    // Populate Providers
    this.LOG.debug("Making Providers");
    // PandemicSimulationDataWriterProvider.setWriter(
    // new H2SqlPandemicSimulationDataWriter(new H2SQLConnector(config.getDatenbank()), config.getBatchgroesse()));
    PandemicSimulationDataWriterProvider
        .setWriter(new CsvPandemicDataWriter(config.getDatenbank(), config.getBatchgroesse()));
    this.LOG.debug("Population Provider");
    new PopulationProvider(populations);
    this.LOG.debug("HealthState Provider");
    new HealthStateProvider(healthstates);
    this.LOG.debug("Transmission Provider");
    new TransmissionProvider(transmissions);
    simulation.addObserver(HealthStateProvider.getInstance());
    simulation.addObserver(TransmissionProvider.getInstance());
    return simulation;
  }

  private Collection<String> parseTravelSubpopulations(String input) {

    String[] split = input.split(",");
    Collection<String> output = new LinkedList<>();
    for (String s : split) {
      output.add(s.trim());
    }
    return output;
  }

}
