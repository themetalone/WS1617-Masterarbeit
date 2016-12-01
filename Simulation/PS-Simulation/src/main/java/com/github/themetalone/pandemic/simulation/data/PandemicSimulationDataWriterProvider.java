package com.github.themetalone.pandemic.simulation.data;

/**
 * @author steffen
 *
 */
public class PandemicSimulationDataWriterProvider {

  private static PandemicSimulationDataWriterProvider instance;

  private final PandemicSimulationDataWriter writer;

  /**
   * The constructor.
   */
  public PandemicSimulationDataWriterProvider(PandemicSimulationDataWriter writer) {
    this.writer = writer;
  }

  /**
   * @return instance
   */
  public static PandemicSimulationDataWriterProvider getInstance() {

    if (instance == null)
      throw new Error("No PandemicSimulationDataWriterProvider set");
    return instance;
  }

  /**
   * @param instance new value of {@link #getinstance}.
   */
  public void setInstance(PandemicSimulationDataWriterProvider instance) {

    PandemicSimulationDataWriterProvider.instance = instance;
  }

}
