package com.github.themetalone.pandemicSimulation.simulation.population.healthState;

import java.util.LinkedList;
import java.util.List;

public class HealthStateFactory {
	
	private static HealthStateFactory instance;
	
	private int idCount = -1;
	private LinkedList<HealthState> states;

	private HealthStateFactory() {
		states = new LinkedList<>();
	}
	
	public final void setInstance(HealthStateFactory instance){
		HealthStateFactory.instance = instance;
	}
	
	public static HealthStateFactory getInstance(){
		if(instance == null){
			instance = new HealthStateFactory();
		}
		return instance;
	}
	
	public HealthState getHealthState(String name, long initialSize){
		states.add(new HealthStateImpl(getNextId(),name,initialSize));
		return states.getLast();
	}
	
	private int getNextId(){
		idCount++;
		return idCount;
	}
	
	

}
