package com.github.themetalone.pandemic.simulation.exceptions;

/**
 * @author steffen
 *
 */
public class NotEnoughIndividualsException extends PandemicSimulationException {

  /**
   *
   */
  private static final long serialVersionUID = -2036207398704153825L;

  private long offset = 0;

  /**
   * The constructor.
   */
  public NotEnoughIndividualsException() {
  }

  /**
   * The constructor.
   *
   * @param arg0
   */
  public NotEnoughIndividualsException(String arg0) {
    super(arg0);
  }

  public NotEnoughIndividualsException(long offset) {
    this.offset = offset;
  }

  /**
   * The constructor.
   *
   * @param arg0
   */
  public NotEnoughIndividualsException(String arg0, long offset) {
    super(arg0);
    this.offset = offset;
  }

  /**
   * The constructor.
   *
   * @param arg0
   */
  public NotEnoughIndividualsException(Throwable arg0) {
    super(arg0);
  }

  /**
   * The constructor.
   *
   * @param arg0
   * @param arg1
   */
  public NotEnoughIndividualsException(String arg0, Throwable arg1) {
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
  public NotEnoughIndividualsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

  /**
   * @return offset
   */
  public long getOffset() {

    return this.offset;
  }

}
