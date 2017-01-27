package com.github.themetalone.pandemic.simulation.data.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * @author steffen
 *
 */
public class CsvConnector {

  private CSVPrinter csvPrinter;

  /**
   * The constructor.
   *
   * @throws IOException
   */
  public CsvConnector(Path target) throws IOException {
    target.toFile().createNewFile();
    this.csvPrinter = new CSVPrinter(new FileWriter(target.toFile()), CSVFormat.EXCEL);
  }

  public void writeRecords(List<String[]> records) throws IOException {

    this.csvPrinter.printRecords(records);
  }

  public void close() throws IOException {

    this.csvPrinter.flush();
    this.csvPrinter.close();

  }

}
