package com.github.themetalone.pandemic.utils.exception;

/**
 * @author steffen
 *
 */
public class PandemicException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 8868426915703605501L;

  /**
   * The constructor.
   */
  public PandemicException() {
  }

  /**
   * The constructor.
   *
   * @param arg0
   */
  public PandemicException(String arg0) {
    super(arg0);
  }

  /**
   * The constructor.
   *
   * @param arg0
   */
  public PandemicException(Throwable arg0) {
    super(arg0);
  }

  /**
   * The constructor.
   *
   * @param arg0
   * @param arg1
   */
  public PandemicException(String arg0, Throwable arg1) {
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
  public PandemicException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}
