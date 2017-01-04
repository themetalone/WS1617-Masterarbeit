package com.github.themetalone.pandemic.utils.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author steffen
 *
 */
public class H2SQLConnector implements SQLConnector {

  private Connection connection;

  private final String jdbcPrefix = "jdbc:h2";

  private final String jdbcUrl;

  private final String jdbcDriver = "org.h2.Driver";

  private static final Logger LOG = LoggerFactory.getLogger(SQLConnector.class);

  private final String user = "simulation";

  private final String pw = "simulation";

  /**
   * The constructor.
   */
  public H2SQLConnector(String location) {

    LOG.info("Using H2SQLConnector with database @ {}", location);
    this.jdbcUrl = this.jdbcPrefix + ":" + location + ";MV_STORE=FALSE;MVCC=FALSE";
    try {
      Class.forName(this.jdbcDriver);
      Connection initConnection = DriverManager.getConnection(this.jdbcUrl, "", "");
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
      this.connection = DriverManager.getConnection(this.jdbcUrl, this.user, this.pw);
    } catch (ClassNotFoundException e) {
      throw new Error("Could not load h2sql driver!", e);
    } catch (SQLException e) {
      throw new Error("Encountered a SQLException:" + e.getMessage(), e);
    }

  }

  @Override
  public void update(Observable arg0, Object arg1) {

  }

  @Override
  public Connection getConnection() {

    try {
      if (this.connection == null || this.connection.isClosed()) {
        this.connection = DriverManager.getConnection(this.jdbcUrl, this.user, this.pw);
      }
    } catch (SQLException e) {
      throw new Error("Encountered a SQLException:" + e.getMessage(), e);
    }
    return this.connection;
  }

}
