package com.github.themetalone.pandemicSimulation.simulation.population.healthState;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
	
	public HealthState makeHealthState(String name, long initialSize){
		states.add(new HealthStateImpl(getNextId(),name,initialSize));
		return states.getLast();
	}
	
	public HealthState getHealthState(int id) throws NoSuchElementException{
		Optional<HealthState> result =  states.parallelStream().filter(h->h.getId()==id).findAny();
		if(result.isPresent()){
			return result.get();
		}
		throw new NoSuchElementException("Could not find HealthState with id "+id);
		
	}
	
	private int getNextId(){
		idCount++;
		return idCount;
	}
	
	

}
