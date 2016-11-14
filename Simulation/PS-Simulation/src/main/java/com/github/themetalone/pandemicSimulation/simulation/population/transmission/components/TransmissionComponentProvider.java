package com.github.themetalone.pandemicSimulation.simulation.population.transmission.components;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class TransmissionComponentProvider {
	
	
	private static TransmissionComponentProvider instance;
	
	private Set<TransmissionComponent> components;
	
	public TransmissionComponentProvider(){
		components = new HashSet<>();
	}
	
	public static TransmissionComponentProvider getInstance(){
		if(instance==null){
			instance = new TransmissionComponentProvider();
		}
		return instance;
	}
	
	public void setComponents(Set<TransmissionComponent> components){
		this.components = components;
	}
	
	public TransmissionComponent getTransmissionComponent(int id){
		Optional<TransmissionComponent> result =  components.stream().parallel().filter(tc->tc.getId()==id).findFirst();
		if(result.isPresent()){
			return result.get();
		}
		throw new NoSuchElementException();
	}
	
	public void RemoveConstrains(){
		components.stream().forEach(tc->tc.removeConstr());
	}
	
}
