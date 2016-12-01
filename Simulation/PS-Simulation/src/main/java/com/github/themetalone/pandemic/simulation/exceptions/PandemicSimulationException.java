package com.github.themetalone.pandemic.simulation.exceptions;

import com.github.themetalone.pandemic.utils.exception.PandemicException;

/**
 * @author steffen
 *
 */
public class PandemicSimulationException extends PandemicException {

  /**
   *
   */
  private static final long serialVersionUID = -4239191650151394429L;

  /**
   * The constructor.
   */
  public PandemicSimulationException() {
  }

  /**
   * The constructor.
   * 
   * @param arg0
   */
  public PandemicSimulationException(String arg0) {
    super(arg0);
  }

  /**
   * The constructor.
   * 
   * @param arg0
   */
  public PandemicSimulationException(Throwable arg0) {
    super(arg0);
  }

  /**
   * The constructor.
   * 
   * @param arg0
   * @param arg1
   */
  public PandemicSimulationException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  /**
   * The constructor.
   * 
   * @param arg0
   * @param arg1
   * @param arg2
   * @param arg3
   */
  public PandemicSimulationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}
