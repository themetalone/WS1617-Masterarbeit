package com.github.themetalone.pandemic.simulation.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.objects.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.objects.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.objects.population.Population;
import com.github.themetalone.pandemic.simulation.objects.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.objects.transmission.TransmissionIdentifier;

/**
 * @author steffen
 *
 */
public class H2SqlPandemicSimulationDataWriter implements PandemicSimulationDataWriter {

  private final SQLConnector SQL;

  private final Map<Tables, BufferedStatement> statements;

  private final static Logger LOG = LoggerFactory.getLogger("PandemicDataWriter");

  /**
   * The constructor.
   */
  public H2SqlPandemicSimulationDataWriter(SQLConnector sql, long batchSize) {
    PandemicSimulationDataWriterProvider.setWriter(this);
    this.SQL = sql;
    this.statements = new HashMap<>();
    try {
      this.statements.put(Tables.POPULATIONS, new BufferedStatement(batchSize, sql.getConnection()
          .prepareStatement("INSERT INTO PANDEMIC.POPULATIONS (POPID, NAME, LIFE, MIGRATION) VALUES (?,?,?,?);")));
      this.statements.put(Tables.HEALTHSTATES, new BufferedStatement(batchSize, sql.getConnection()
          .prepareStatement("INSERT INTO PANDEMIC.HEALTHSTATES (POPID, HSID, NAME, SIZE) VALUES (?,?,?,?);")));
      this.statements.put(Tables.TRANSMISSIONS, new BufferedStatement(batchSize, sql.getConnection().prepareStatement(
          "INSERT INTO PANDEMIC.TRANSMISSIONS (SRCPOPID, SRCHSID, TRGPOPID, TRGHSID, TYPE, PRIORITY, DESCRIPTION) VALUES (?,?,?,?,?,?,?);")));
      this.statements.put(Tables.HEALTHSTATESTATES, new BufferedStatement(batchSize, sql.getConnection()
          .prepareStatement("INSERT INTO PANDEMIC.HEALTHSTATESTATES (POPID, HSID, TICK, SIZE) VALUES (?,?,?,?);))")));
      this.statements.put(Tables.TRANSMISSIONSTATES,
          new BufferedStatement(batchSize, sql.getConnection().prepareStatement(
              "INSERT INTO PANDEMIC.TRANSMISSIONSTATES (SRCPOPID, SRCHSID, TRGPOPID, TRGHSID, TYPE,TICK, VALUE) VALUES (?,?,?,?,?,?,?);")));
    } catch (SQLException e) {
      throw new Error("Could not prepare statements:" + e.getMessage(), e);
    }

  }

  @Override
  public void putPopulation(Population p) {

    LOG.debug(p.toString());
    try {
      PreparedStatement stmnt = this.statements.get(Tables.POPULATIONS).getStatement();
      stmnt.setInt(1, p.POPULATION_ID);
      stmnt.setString(2, p.NAME);
      stmnt.setFloat(3, p.LIFE_STANDARD);
      stmnt.setFloat(4, p.MIGRATION_PROPORTION);
      LOG.debug(stmnt.toString());
      stmnt.addBatch();
    } catch (SQLException e) {
      close();
      throw new Error("Encountered a SQL Exception while preparing a statement:" + e.getMessage(), e);
    }

  }

  @Override
  public void putHealthState(HealthState s) {

    LOG.debug(s.toString());
    try {

      if (this.statements.get(Tables.POPULATIONS).buffer > 0) {
        LOG.debug("Executing Population Statements");
        this.statements.get(Tables.POPULATIONS).execute();
      }

      PreparedStatement stmnt = this.statements.get(Tables.HEALTHSTATES).getStatement();
      stmnt.setInt(1, s.getIdentifier().POPULATION_ID);
      stmnt.setInt(2, s.getIdentifier().HEALTHSTATE_ID);
      stmnt.setString(3, s.getName());
      stmnt.setLong(4, s.getSize());
      stmnt.addBatch();
    } catch (SQLException e) {
      close();
      throw new Error("Encountered a SQL Exception while preparing a statement:" + e.getMessage(), e);

    }
  }

  @Override
  public void putTransmission(Transmission t) {

    LOG.debug(t.toString());
    try {
      PreparedStatement stmnt = this.statements.get(Tables.TRANSMISSIONS).getStatement();
      stmnt.setInt(1, t.getIdentifier().SOURCE.POPULATION_ID);
      stmnt.setInt(2, t.getIdentifier().SOURCE.HEALTHSTATE_ID);
      stmnt.setInt(3, t.getIdentifier().TARGET.POPULATION_ID);
      stmnt.setInt(4, t.getIdentifier().TARGET.HEALTHSTATE_ID);
      stmnt.setInt(5, t.getIdentifier().TYPE);
      stmnt.setInt(6, t.getPriority());
      stmnt.setString(7, t.toString());
      stmnt.addBatch();
    } catch (SQLException e) {
      close();
      throw new Error("Encountered a SQL Exception while preparing a statement:" + e.getMessage(), e);

    }
  }

  @Override
  public void putHealthStateState(HealthStateIdentifier id, long size, long tick) {

    try {
      PreparedStatement stmnt = this.statements.get(Tables.HEALTHSTATESTATES).getStatement();
      stmnt.setInt(1, id.POPULATION_ID);
      stmnt.setInt(2, id.HEALTHSTATE_ID);
      stmnt.setLong(3, tick);
      stmnt.setLong(4, size);
      stmnt.addBatch();
    } catch (SQLException e) {
      close();
      throw new Error("Encountered a SQL Exception while preparing a statement:" + e.getMessage(), e);

    }
  }

  @Override
  public void putTransmissionExecution(TransmissionIdentifier id, long value, long tick) {

    try {
      PreparedStatement stmnt = this.statements.get(Tables.TRANSMISSIONSTATES).getStatement();
      stmnt.setInt(1, id.SOURCE.POPULATION_ID);
      stmnt.setInt(2, id.SOURCE.HEALTHSTATE_ID);
      stmnt.setInt(3, id.TARGET.POPULATION_ID);
      stmnt.setInt(4, id.TARGET.HEALTHSTATE_ID);
      stmnt.setInt(5, id.TYPE);
      stmnt.setLong(6, tick);
      stmnt.setLong(7, value);
      stmnt.addBatch();
    } catch (SQLException e) {
      close();
      throw new Error("Encountered a SQL Exception while preparing a statement:" + e.getMessage(), e);

    }

  }

  @Override
  public void close() {

    this.statements.values().parallelStream().forEach(s -> {
      try {
        s.execute();
      } catch (SQLException e) {
        LOG.warn("Encountered SQL Error during final batch execution:" + e.getMessage());
      }
    });
    try {
      this.SQL.getConnection().commit();
      this.SQL.getConnection().close();
    } catch (SQLException e) {
      LOG.warn("Closing SQL connection caused an {}: {}", e.getClass().getName(), e.getMessage());
    }

  }

  enum Tables {
    POPULATIONS, HEALTHSTATES, HEALTHSTATESTATES, TRANSMISSIONS, TRANSMISSIONSTATES;
  }

  class BufferedStatement {

    long bufferSize;

    long buffer = 0;

    private PreparedStatement statement;

    BufferedStatement(long bufferSize, PreparedStatement statement) {
      this.bufferSize = bufferSize;
      this.statement = statement;
    }

    synchronized PreparedStatement getStatement() throws SQLException {

      this.buffer++;
      if (this.buffer > this.bufferSize) {
        execute();
      }
      return this.statement;
    }

    synchronized void execute() throws SQLException {

      this.statement.execute();
      this.buffer = 0;
      H2SqlPandemicSimulationDataWriter.this.SQL.getConnection().commit();
    }

  }

}
