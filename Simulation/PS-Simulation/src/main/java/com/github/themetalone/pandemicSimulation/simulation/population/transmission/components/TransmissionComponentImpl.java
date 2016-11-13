package com.github.themetalone.pandemicSimulation.simulation.population.transmission.components;

public abstract class TransmissionComponentImpl implements TransmissionComponent {

	protected final int id;
	protected final int counterpartId;
	protected long upperConstrain = Long.MAX_VALUE;
	protected long lowerConstrain = Long.MIN_VALUE;

	public TransmissionComponentImpl(int id, int counterpartId) {
		super();
		this.id = id;
		this.counterpartId = counterpartId;
	}

	public int getId() {
		return id;
	}

	@Override
	public void removeConstr() {
		upperConstrain = Long.MAX_VALUE;
		lowerConstrain = Long.MIN_VALUE;
	}

	protected long getConstrainedValue(long val) {
		if (val < lowerConstrain) {
			return lowerConstrain;
		} else if (val > upperConstrain) {
			return upperConstrain;
		}
		return val;
	}

	@Override
	public void setLowerBound(long lower) {
		this.lowerConstrain = lower;
	}

	@Override
	public void setUpperBound(long upper) {
		this.upperConstrain = upper;
	}

	@Override
	public int getCounterpartId() {
		return this.counterpartId;
	}

	@Override
	public String toString() {
		return "Component::id:"+id+" counterpardId:"+counterpartId+" in ["+lowerConstrain+";"+upperConstrain+"]";
	}
	
	
	
	

}