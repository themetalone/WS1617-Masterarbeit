package com.github.themetalone.pandemic.analysis.csv.record;

import org.apache.commons.csv.CSVRecord;

/**
 * @author Steffen
 *
 */
public class HealthStateStateRecord implements RecordObject, Comparable {

  private int popId;

  private int hsId;

  private long tick;

  private long size;

  private CSVRecord csv;

  @Override
  public CSVRecord toCsv() {

    return this.csv;
  }

  /**
   * @return popId
   */
  public int getPopId() {

    return this.popId;
  }

  /**
   * @param popId new value of {@link #getpopId}.
   */
  public void setPopId(int popId) {

    this.popId = popId;
  }

  /**
   * @return hsId
   */
  public int getHsId() {

    return this.hsId;
  }

  /**
   * @param hsId new value of {@link #gethsId}.
   */
  public void setHsId(int hsId) {

    this.hsId = hsId;
  }

  /**
   * @return tick
   */
  public long getTick() {

    return this.tick;
  }

  /**
   * @param tick new value of {@link #gettick}.
   */
  public void setTick(long tick) {

    this.tick = tick;
  }

  /**
   * @return size
   */
  public long getSize() {

    return this.size;
  }

  /**
   * @param size new value of {@link #getsize}.
   */
  public void setSize(long size) {

    this.size = size;
  }

  /**
   * @param csv new value of {@link #getcsv}.
   */
  public void setCsv(CSVRecord csv) {

    this.csv = csv;
  }

  @Override
  public int compareTo(Object o) {

    if (!(o instanceof HealthStateStateRecord)) {
      return 0;
    }

    HealthStateStateRecord that = (HealthStateStateRecord) o;
    long thisValue = this.tick;
    long thatValue = that.tick;

    if (this.tick == that.tick) {
      thisValue = this.popId;
      thatValue = that.popId;

      if (this.popId == that.popId) {
        thisValue = this.hsId;
        thatValue = that.hsId;
        if (this.hsId == that.hsId) {
          return 0;
        }
      }
    }
    return thisValue - thatValue > 0 ? 1 : -1;
  }

}
