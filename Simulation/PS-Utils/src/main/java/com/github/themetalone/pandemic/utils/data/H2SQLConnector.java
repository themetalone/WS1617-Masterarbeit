package com.github.themetalone.pandemic.utils.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 * @author steffen
 *
 */
public class H2SQLConnector implements SQLConnector {

  private Connection connection;

  /**
   * The constructor.
   */
  public H2SQLConnector(String location) {

    String jdbcPrefix = "jdbc:h2";
    String jdbcUrl = jdbcPrefix + ":" + location + ";MV_STORE=FALSE;MVCC=FALSE";
    try {
      String jdbcDriver = "org.h2.Driver";
      Class.forName(jdbcDriver);
      Connection initConnection = DriverManager.getConnection(jdbcUrl, "", "");
      Statement stmnt = initConnection.createStatement();
      stmnt.addBatch("CREATE USER IF NOT EXISTS simulation PASSWORD 'simulation' ADMIN");
      stmnt.addBatch("CREATE SCHEMA IF NOT EXISTS PANDEMIC AUTHORIZATION simulation");

      stmnt.addBatch(
          "CREATE TABLE IF NOT EXISTS PANDEMIC.HEALTHSTATES (POPID INTEGER, HSID INTEGER ,NAME VARCHAR(255), SIZE BIGINT, "
              + "PRIMARY KEY (POPID, HSID));");
      stmnt.addBatch(
          "CREATE TABLE IF NOT EXISTS PANDEMIC.HEALTHSTATESTATES (POPID INTEGER, HSID INTEGER, TICK BIGINT, SIZE BIGINT, "
              + "PRIMARY KEY(POPID, HSID, TICK), "
              + "FOREIGN KEY (POPID, HSID) REFERENCES PANDEMIC.HEALTHSTATES (POPID, HSID));");
      stmnt.addBatch(
          "CREATE TABLE IF NOT EXISTS PANDEMIC.TRANSMISSIONS (SRCPOPID INTEGER, SRCHSID INTEGER, TRGPOPID INTEGER, TRGHSID INTEGER, TYPE INTEGER, PRIORITY INTEGER, DESCRIPTION VARCHAR(255), "
              + "PRIMARY KEY(SRCPOPID, SRCHSID, TRGPOPID, TRGHSID, TYPE), "
              + "FOREIGN KEY (SRCPOPID, SRCHSID) REFERENCES PANDEMIC.HEALTHSTATES (POPID, HSID), "
              + "FOREIGN KEY (TRGPOPID, TRGHSID) REFERENCES PANDEMIC.HEALTHSTATES (POPID, HSID));");
      stmnt.addBatch(
          "CREATE TABLE IF NOT EXISTS PANDEMIC.TRANSMISSIONSTATES (SRCPOPID INTEGER, SRCHSID INTEGER, TRGPOPID INTEGER, TRGHSID INTEGER, TYPE INTEGER, TICK BIGINT, VALUE BIGINT, "
              + "PRIMARY KEY(SRCPOPID, SRCHSID, TRGPOPID, TRGHSID, TYPE, TICK), "
              + "FOREIGN KEY (SRCPOPID, SRCHSID, TRGPOPID, TRGHSID, TYPE) REFERENCES PANDEMIC.TRANSMISSIONS (SRCPOPID, SRCHSID, TRGPOPID, TRGHSID, TYPE));");

      stmnt.addBatch("DELETE FROM PANDEMIC.HEALTHSTATES WHERE TRUE");
      stmnt.addBatch("DELETE FROM PANDEMIC.HEALTHSTATESTATES WHERE TRUE");
      stmnt.addBatch("DELETE FROM PANDEMIC.TRANSMISSIONS WHERE TRUE");
      stmnt.addBatch("DELETE FROM PANDEMIC.TRANSMISSIONSTATES WHERE TRUE");

      stmnt.executeBatch();
      stmnt.close();
      this.connection = DriverManager.getConnection(jdbcUrl, "simulation", "simulation");
    } catch (ClassNotFoundException e) {
      throw new Error("Could not load h2sql driver!\n Fallback to Logger");
    } catch (SQLException e) {
      throw new Error("Encountered a SQLException:" + e.getMessage(), e);
    }

  }

  @Override
  public void update(Observable arg0, Object arg1) {

    // TODO Auto-generated method stub

  }

  @Override
  public Connection getConnection() {

    return this.connection;
  }

}
