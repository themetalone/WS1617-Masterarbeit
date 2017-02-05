package com.github.themetalone.pandemic.analysis.csv.record;

import org.apache.commons.csv.CSVRecord;

/**
 * @author Steffen
 *
 */
public interface RecordObject {

  CSVRecord toCsv();

}
