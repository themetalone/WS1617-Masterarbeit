package com.github.themetalone.pandemic.products.analysis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.github.themetalone.pandemic.analysis.csv.utils.PopulationSeparationUtil;
import com.github.themetalone.pandemic.products.SystemProperties;

/**
 * @author Steffen
 *
 */
public class AnalysisMain {

  /**
   * The constructor.
   */
  public AnalysisMain() {
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    String folder = System.getProperty(SystemProperties.ANALYSIS_FOLDER);
    String prefix = System.getProperty(SystemProperties.ANALYSIS_PREFIX);
    String csv = System.getProperty(SystemProperties.ANALYSIS_CSV);

    if (folder == null || prefix == null || csv == null) {
      throw new Error("Some properties were not set");
    }
    PopulationSeparationUtil util = new PopulationSeparationUtil();
    util.writeData(util.separateRecords(util.readHealthStateStates(new File(csv))), Paths.get(folder), prefix);
  }

}
