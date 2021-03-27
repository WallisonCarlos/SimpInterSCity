package org.interscity.simpinterscity.model.scenario;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phase")
public class Phase {

	@XmlAttribute(name = "origin")
	public long origin;
	@XmlAttribute(name = "greem_duration")
	public int green_duration;
	@XmlAttribute(name = "green_start")
	public int green_start;
	
	public Phase() {
		
	}
	
	public Phase(long origin, int green_duration, int green_start) {
		super();
		this.origin = origin;
		this.green_duration = green_duration;
		this.green_start = green_start;
	}
	
}
