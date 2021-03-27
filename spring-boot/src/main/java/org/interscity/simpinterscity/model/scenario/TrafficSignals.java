package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "traffic-signals")
public class TrafficSignals {
	
	@XmlElement(name = "signal")
	public List<Signal> signals = new ArrayList<Signal>();
	
}
