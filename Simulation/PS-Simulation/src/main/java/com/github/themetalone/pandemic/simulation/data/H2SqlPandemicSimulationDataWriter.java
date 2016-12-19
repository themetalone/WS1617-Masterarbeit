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
      this.statements.put(Tables.TRANSMISSIONS,
          new BufferedStatement(batchSize, sql.getConnection().prepareStatement("INSERT INTO PANDEMIC.TRANSMISSIONS")));// TODO

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
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void putHealthStateState(HealthStateIdentifier id, long size, long tick) {

    // TODO Auto-generated method stub

  }

  @Override
  public void putTransmissionExecution(TransmissionIdentifier id, long value, long tick) {

    // TODO Auto-generated method stub

  }

  @Override
  public void close() {

    // TODO Auto-generated method stub

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
        this.statement.execute();
        this.buffer = 0;
      }
      return this.statement;
    }

  }

}
