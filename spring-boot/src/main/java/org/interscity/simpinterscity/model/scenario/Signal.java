package org.interscity.simpinterscity.model.scenario;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "signal")
public class Signal extends Element{
	
	@XmlAttribute(name = "cycle_duration")
	public int cycle_duration;
	@XmlAttribute(name = "offset")
	public int offset;
	@XmlElement(name = "nodes")
	public Nodes nodes = new Nodes();
	@XmlElement(name = "phases")
	public Phases phases = new Phases();
	
	public Signal() {
		
	}
	
	public Signal(int cycle_duration, int offset, Nodes nodes, Phases phases) {
		super();
		this.cycle_duration = cycle_duration;
		this.offset = offset;
		this.nodes = nodes;
		this.phases = phases;
	}

	@Override
	public Node getNode(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
