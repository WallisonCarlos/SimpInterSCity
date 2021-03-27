package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "scsimulator_matrix")
public class SCSimulatorMatrix {

	@XmlElement(name = "trip")
	public List<Trip> trips = new ArrayList<>();
}
