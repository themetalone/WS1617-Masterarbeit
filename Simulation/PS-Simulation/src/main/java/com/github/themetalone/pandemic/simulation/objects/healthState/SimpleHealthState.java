package com.github.themetalone.pandemic.simulation.objects.healthState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.themetalone.pandemic.simulation.data.PandemicSimulationDataWriterProvider;
import com.github.themetalone.pandemic.simulation.exceptions.NotEnoughIndividualsException;

/**
 * @author steffen
 *
 */
public class SimpleHealthState implements HealthState {

  private final HealthStateIdentifier ID;

  private final String NAME;

  private long size;

  private long changes;

  private long tick;

  private final Logger LOG;

  public SimpleHealthState(int popId, int hsId, String name, long initSize) {
    this.ID = new HealthStateIdentifier(popId, hsId);
    this.NAME = name;
    this.size = initSize;
    this.changes = 0;
    this.LOG = LoggerFactory.getLogger("SimpleHealthState-" + popId + "." + hsId);
  }

  @Override
  public HealthStateIdentifier getIdentifier() {

    return this.ID;
  }

  @Override
  public String getName() {

    return this.NAME;
  }

  @Override
  public long getSize() {

    return this.size;
  }

  @Override
  synchronized public void addSize(long addition) throws NotEnoughIndividualsException {

    if (this.size + this.changes + addition < 0) {
      this.LOG.debug("Exceeded current size. Actual {}, Requested {}", this.size, this.size + this.changes + addition);
      throw new NotEnoughIndividualsException(this.size);
    }

    this.changes += addition;
  }

  @Override
  synchronized public void applyChanges() {

    this.size += this.changes;
    this.changes = 0;
    PandemicSimulationDataWriterProvider.getWriter().putHealthStateState(this.ID, this.size, this.tick);

  }

  /**
   * @param tick new value of {@link #gettick}.
   */
  @Override
  public void setTick(long tick) {

    this.tick = tick;
  }

  @Override
  public String toString() {

    return "SimpleHealthState [ID=" + this.ID + ", NAME=" + this.NAME + ", size=" + this.size + "]";
  }

}
