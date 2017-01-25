package com.github.themetalone.pandemic.simulation.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    InputStream sqlFileIS = this.getClass().getResourceAsStream("/src/main/resources/sql/h2ini.sql");
    try {
      Class.forName(this.jdbcDriver);
      Connection initConnection = DriverManager.getConnection(this.jdbcUrl, "", "");
      Statement stmnt = initConnection.createStatement();

      BufferedReader bufferedSqlFileIS = new BufferedReader(new InputStreamReader(sqlFileIS));

      for (String line; (line = bufferedSqlFileIS.readLine()) != null;) {
        stmnt.addBatch(line.trim());
      }
      bufferedSqlFileIS.close();
      sqlFileIS.close();

      stmnt.executeBatch();
      stmnt.close();
      this.connection = DriverManager.getConnection(this.jdbcUrl, this.user, this.pw);
    } catch (ClassNotFoundException e) {
      throw new Error("Could not load h2sql driver!", e);
    } catch (SQLException e) {
      throw new Error("Encountered a SQLException:" + e.getMessage(), e);
    } catch (IOException e) {
      throw new Error("Encountered IO Exceptions during database initialization:" + e.getMessage(), e);
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
