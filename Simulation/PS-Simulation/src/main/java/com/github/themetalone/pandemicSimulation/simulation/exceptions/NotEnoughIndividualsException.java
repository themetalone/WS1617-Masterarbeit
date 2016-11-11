package com.github.themetalone.pandemicSimulation.simulation.exceptions;

public class NotEnoughIndividualsException extends PandemicSimulationException {

	/**
	 * The maximum in individuals the population can change
	 */
	private final long maxDiff;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5705254688300547004L;

	public NotEnoughIndividualsException(long maxDiff) {
		this.maxDiff = maxDiff;
	}

	public NotEnoughIndividualsException(long maxDiff, String arg0) {
		super(arg0);
		this.maxDiff = maxDiff;
	}

	public NotEnoughIndividualsException(long maxDiff, Throwable arg0) {
		super(arg0);
		this.maxDiff = maxDiff;
	}

	public NotEnoughIndividualsException(long maxDiff, String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.maxDiff = maxDiff;
	}

	public NotEnoughIndividualsException(long maxDiff, String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		this.maxDiff = maxDiff;
	}

	/**
	 * @return the maxDiff. Always greater than 0
	 */
	public long getMaxDiff() {
		return maxDiff;
	}

}
