package com.github.themetalone.pandemic.simulation.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.github.themetalone.pandemic.simulation.healthState.HealthState;
import com.github.themetalone.pandemic.simulation.healthState.HealthStateIdentifier;
import com.github.themetalone.pandemic.simulation.transmission.Transmission;
import com.github.themetalone.pandemic.simulation.transmission.TransmissionIdentifier;
import com.github.themetalone.pandemic.utils.data.SQLConnector;

/**
 * @author steffen
 *
 */
public class H2SqlPandemicSimulationDataWriter implements PandemicSimulationDataWriter {

  private final SQLConnector SQL;

  private final Map<Tables, BufferedStatement> statements;

  /**
   * The constructor.
   */
  public H2SqlPandemicSimulationDataWriter(SQLConnector sql, long batchSize) {
    PandemicSimulationDataWriterProvider.setWriter(this);
    this.SQL = sql;
    this.statements = new HashMap<>();
    try {
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void putHealthState(HealthState s) {

    try {
      PreparedStatement stmnt = this.statements.get(Tables.HEALTHSTATES).getStatement();
      stmnt.setInt(1, s.getIdentifier().POPULATION_ID);
      stmnt.setInt(2, s.getIdentifier().HEALTHSTATE_ID);
      stmnt.setString(3, s.getName());
      stmnt.setLong(4, s.getSize());
      stmnt.addBatch();
    } catch (SQLException e) {

    }
  }

  @Override
  public void putTransmission(Transmission t) {

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

    }

  }

  @Override
  public void close() {

    this.statements.values().parallelStream().forEach(s -> {
      try {
        s.execute();
      } catch (SQLException e) {
      }
    });
    try {
      this.SQL.getConnection().close();
    } catch (SQLException e) {

    }

  }

  enum Tables {
    HEALTHSTATES, HEALTHSTATESTATES, TRANSMISSIONS, TRANSMISSIONSTATES;
  }

  class BufferedStatement {

    long bufferSize;

    long buffer = 0;

    private PreparedStatement statement;

    BufferedStatement(long bufferSize, PreparedStatement statement) {
      this.bufferSize = bufferSize;
      this.statement = statement;
    }

    PreparedStatement getStatement() throws SQLException {

      this.buffer++;
      if (this.buffer > this.bufferSize) {
        execute();
        this.buffer = 0;
      }
      return this.statement;
    }

    void execute() throws SQLException {

      this.statement.execute();
    }

  }

}
