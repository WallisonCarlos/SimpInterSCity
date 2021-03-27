package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nodes")
public class Nodes {

	public Nodes() {
		
	}
	
	public Nodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	
	@XmlElement(name = "node")
	public List<Node> nodes = new ArrayList<>();

	
}
