package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "scsimulator_buses")
public class SimulatorBuses {
	
	
	private List<Bus> buses = new ArrayList<>();

	@XmlElement
	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	
	
}
