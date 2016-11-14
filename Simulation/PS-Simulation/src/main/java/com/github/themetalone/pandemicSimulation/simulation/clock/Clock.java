package com.github.themetalone.pandemicSimulation.simulation.clock;

import java.util.Observable;

public class Clock extends Observable {

	private final long time;

	public Clock(long time) {
		if (time < 1) {
			throw new Error("Running " + time + " seconds makes no sense");
		}
		this.time = time;

	}

	public void run() {
		for (long t = time; t > 0; t--) {
			super.setChanged();
			super.notifyObservers(new Long(t));
		}
	}

}
