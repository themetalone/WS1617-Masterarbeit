package com.github.themetalone.pandemic.simulation.data.csv;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriter;
import com.github.themetalone.pandemic.simulation.data.Tables;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.population.Population;
import com.github.themetalone.pandemic.simulation.objects.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.TransmissionIdentifier;

/**
 * @author steffen
 *
 */
public class CsvPandemicDataWriter implements PandemicSimulationDataWriter {

  private Map<Tables, BufferedCsvTable> buffers;

  private Logger LOG = LoggerFactory.getLogger("PDW");

  /**
   * The constructor.
   */
  public CsvPandemicDataWriter(String location, int bufferSize) {
    Paths.get(location).toFile().getParentFile().mkdirs();
    this.buffers = new HashMap<>();
    try {
      this.buffers.put(Tables.POPULATIONS,
          new BufferedCsvTable(bufferSize, new String[] { "POPID", "NAME", "LIFE", "MIGRATION" },
              new CsvConnector(Paths.get(location + "-population.csv"))));
      this.buffers.put(Tables.HEALTHSTATES,
          new BufferedCsvTable(bufferSize, new String[] { "POPID", "HSID", "NAME", "SIZE" },
              new CsvConnector(Paths.get(location + "-healthstates.csv"))));
      this.buffers.put(Tables.TRANSMISSIONS,
          new BufferedCsvTable(bufferSize,
              new String[] { "SRCPOPID", "SRCHSID", "TRGPOPID", "TRGHSID", "TYPE", "PRIORITY", "DESCRIPTION" },
              new CsvConnector(Paths.get(location + "-transmissiosn.csv"))));
      this.buffers.put(Tables.HEALTHSTATESTATES, new BufferedCsvTable(bufferSize,
          new String[] { "POPID", "HSID", "TICK", "SIZE" }, new CsvConnector(Paths.get(location + "-hsstates.csv"))));
      this.buffers.put(Tables.TRANSMISSIONSTATES,
          new BufferedCsvTable(bufferSize,
              new String[] { "SRCPOPID", "SRCHSID", "TRGPOPID", "TRGHSID", "TYPE", "TICK", "VOLUME" },
              new CsvConnector(Paths.get(location + "-tstates.csv"))));
    } catch (IOException e) {
      throw new Error("Cannot initalize csv writer", e);
    }

  }

  @Override
  public void putPopulation(Population p) {

    this.LOG.debug("Adding {}", p.toString());
    BufferedCsvTable table = this.buffers.get(Tables.POPULATIONS);
    table.setInt(0, p.POPULATION_ID);
    table.setString(1, p.NAME);
    table.setFloat(2, p.LIFE_STANDARD);
    table.setFloat(3, p.MIGRATION_PROPORTION);
    try {
      table.addToBuffer();
    } catch (IOException e) {
      throw new Error("Couldn't add Population to batch", e);
    }

  }

  @Override
  public void putHealthState(HealthState s) {

    this.LOG.debug("Adding {}", s.toString());
    BufferedCsvTable table = this.buffers.get(Tables.HEALTHSTATES);
    table.setInt(0, s.getIdentifier().POPULATION_ID);
    table.setInt(1, s.getIdentifier().HEALTHSTATE_ID);
    table.setString(2, s.getName());
    table.setLong(3, s.getSize());
    try {
      table.addToBuffer();
    } catch (IOException e) {
      throw new Error("Couldn't add HealthState to batch", e);
    }
  }

  @Override
  public void putTransmission(Transmission t) {

    this.LOG.debug("Adding {}", t.toString());
    BufferedCsvTable table = this.buffers.get(Tables.TRANSMISSIONS);
    table.setInt(0, t.getIdentifier().SOURCE.POPULATION_ID);
    table.setInt(1, t.getIdentifier().SOURCE.HEALTHSTATE_ID);
    table.setInt(2, t.getIdentifier().TARGET.POPULATION_ID);
    table.setInt(3, t.getIdentifier().TARGET.HEALTHSTATE_ID);
    table.setInt(4, t.getIdentifier().TYPE);
    table.setInt(5, t.getPriority());
    table.setString(6, t.toString());
    try {
      table.addToBuffer();
    } catch (IOException e) {
      throw new Error("Couldn't add Transmission to batch", e);
    }

  }

  @Override
  public void putHealthStateState(HealthStateIdentifier id, long size, long tick) {

    this.LOG.debug("Adding HealthState state at {}:{} with {}", tick, id.toString(), size);
    BufferedCsvTable table = this.buffers.get(Tables.HEALTHSTATESTATES);
    table.setInt(0, id.POPULATION_ID);
    table.setInt(1, id.HEALTHSTATE_ID);
    table.setLong(2, tick);
    table.setLong(3, size);
    try {
      table.addToBuffer();
    } catch (IOException e) {
      throw new Error("Couldn't add HealthState to batch", e);
    }

  }

  @Override
  public void putTransmissionExecution(TransmissionIdentifier id, long value, long tick) {

    this.LOG.debug("Adding Transmission execution at {}:{} with {}", tick, id.toString(), value);
    BufferedCsvTable table = this.buffers.get(Tables.TRANSMISSIONSTATES);
    table.setInt(0, id.SOURCE.POPULATION_ID);
    table.setInt(1, id.SOURCE.HEALTHSTATE_ID);
    table.setInt(2, id.TARGET.POPULATION_ID);
    table.setInt(3, id.TARGET.HEALTHSTATE_ID);
    table.setInt(4, id.TYPE);
    table.setLong(5, tick);
    table.setLong(6, value);
    try {
      table.addToBuffer();
    } catch (IOException e) {
      throw new Error("Couldn't add Transmission execution to batch", e);
    }

  }

  @Override
  public void close() {

    this.buffers.forEach((k, v) -> {
      try {
        v.close();
      } catch (IOException e) {
        this.LOG.warn("Caught IOException during shutdown of {}:{}", k.name(), e.getMessage());
      }
    });
  }

  class BufferedCsvTable {

    private final List<String[]> entries;

    private final int columns;

    private String[] currentEntry;

    private final int bufferSize;

    private final CsvConnector csv;

    BufferedCsvTable(int bufferSize, String[] header, CsvConnector csv) {
      this.entries = new ArrayList<>(bufferSize);
      this.entries.add(header);
      this.columns = header.length;
      this.bufferSize = bufferSize;
      this.csv = csv;
      this.currentEntry = new String[this.columns];

    }

    void setString(int index, String value) {

      this.currentEntry[index] = "'" + value + "'";
    }

    void setInt(int index, int value) {

      this.currentEntry[index] = String.valueOf(value);
    }

    void setLong(int index, long value) {

      this.currentEntry[index] = String.valueOf(value);
    }

    void setFloat(int index, float value) {

      this.currentEntry[index] = String.valueOf(value);
    }

    void addToBuffer() throws IOException {

      this.entries.add(this.currentEntry);
      if (this.entries.size() >= this.bufferSize) {
        CsvPandemicDataWriter.this.LOG.debug("Writing Batch");
        this.csv.writeRecords(this.entries);
        this.entries.clear();
      }
      this.currentEntry = new String[this.columns];
    }

    void close() throws IOException {

      this.csv.writeRecords(this.entries);
      this.csv.close();
    }

  }

}
