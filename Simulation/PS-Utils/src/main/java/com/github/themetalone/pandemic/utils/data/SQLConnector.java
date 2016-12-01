package com.github.themetalone.pandemic.utils.data;

import java.util.Observer;

/**
 * @author steffen
 *
 */
public interface SQLConnector extends Observer {

  void addStatement(String stmnt);

}
