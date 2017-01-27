package com.github.themetalone.pandemic.simulation.data.sql;

import java.sql.Connection;
import java.util.Observer;

/**
 * @author steffen
 *
 */
public interface SQLConnector extends Observer {

  Connection getConnection();

}
