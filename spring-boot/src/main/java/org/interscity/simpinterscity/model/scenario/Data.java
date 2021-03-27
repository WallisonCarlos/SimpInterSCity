package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private SCSimulatorMatrix scSimulatorMatrix;
	private List<Signal> signals = new ArrayList<>();
	private Map map;
	private Metro metro;
	private SimulatorBuses simulatorBuses;
	
	public SCSimulatorMatrix getScSimulatorMatrix() {
		return scSimulatorMatrix;
	}

	public void setScSimulatorMatrix(SCSimulatorMatrix scSimulatorMatrix) {
		this.scSimulatorMatrix = scSimulatorMatrix;
	}

	public List<Signal> getSignals() {
		return signals;
	}
	
	public void setSignals(List<Signal> signals) {
		this.signals = signals;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Metro getMetro() {
		return metro;
	}
	
	public void setMetro(Metro metro) {
		this.metro = metro;
	}
	
	public SimulatorBuses getSimulatorBuses() {
		return simulatorBuses;
	}

	public void setSimulatorBuses(SimulatorBuses simulatorBuses) {
		this.simulatorBuses = simulatorBuses;
	}
	
	public List<Element> getElements() {
		List<Element> elements = new ArrayList<>();
//		elements.addAll(simulatorBuses.getBuses());
//		elements.addAll(scSimulatorMatrix.trips);
		elements.addAll(map.nodes.nodes);
//		elements.addAll(metro.stations.stations);
		return elements;
	}

}
