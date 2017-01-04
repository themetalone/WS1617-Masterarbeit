package com.github.themetalone.pandemic.simulation.data;

/**
 * @author steffen
 *
 */
public class PandemicSimulationDataWriterProvider {

  private static PandemicSimulationDataWriter writer;

  public PandemicSimulationDataWriterProvider(PandemicSimulationDataWriter writer) {
    PandemicSimulationDataWriterProvider.writer = writer;
  }

  /**
   * @return instance
   */
  public static PandemicSimulationDataWriter getWriter() {

    if (writer == null)
      throw new Error("No PandemicSimulationDataWriter set");
    return writer;
  }

  /**
   * @param writer new value of {@link #getwriter}.
   */
  public static void setWriter(PandemicSimulationDataWriter writer) {

    PandemicSimulationDataWriterProvider.writer = writer;
  }

}
