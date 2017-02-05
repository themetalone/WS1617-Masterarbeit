package com.github.themetalone.pandemic.analysis.exception;

/**
 * @author Steffen
 *
 */
public class HeaderException extends Exception {

  /**
   * The constructor.
   */
  public HeaderException() {
  }

  /**
   * The constructor.
   * @param message
   */
  public HeaderException(String message) {
    super(message);
  }

  /**
   * The constructor.
   * @param cause
   */
  public HeaderException(Throwable cause) {
    super(cause);
  }

  /**
   * The constructor.
   * @param message
   * @param cause
   */
  public HeaderException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * The constructor.
   * @param message
   * @param cause
   * @param enableSuppression
   * @param writableStackTrace
   */
  public HeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
