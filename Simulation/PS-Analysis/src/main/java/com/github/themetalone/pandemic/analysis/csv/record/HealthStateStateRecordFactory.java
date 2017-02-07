package com.github.themetalone.pandemic.analysis.csv.record;

import org.apache.commons.csv.CSVRecord;

import com.github.themetalone.pandemic.analysis.exception.HeaderException;

/**
 * @author Steffen
 *
 */
public class HealthStateStateRecordFactory {

  public HealthStateStateRecord parse(CSVRecord csv) throws HeaderException {

    try {
      Integer.valueOf(csv.get(0));
    } catch (NumberFormatException e) {
      throw new HeaderException("This seems to be the header:" + csv.toString(), e);
    }

    HealthStateStateRecord result = new HealthStateStateRecord();
    result.setCsv(csv);
    result.setPopId(Integer.valueOf(csv.get(0)));
    result.setHsId(Integer.valueOf(csv.get(1)));
    result.setTick(Long.valueOf(csv.get(2)));
    result.setSize(Long.valueOf(csv.get(3)));

    return result;
  }

}
