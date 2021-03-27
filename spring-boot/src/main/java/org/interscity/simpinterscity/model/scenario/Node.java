package org.interscity.simpinterscity.model.scenario;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "node")
public class Node extends Element{

	@XmlAttribute(name = "id")
	public long id;
	@XmlAttribute(name = "x")
	public double x;
	@XmlAttribute(name = "y")
	public double y;
	@XmlTransient
	public boolean matched;
	
	public Node() {
	
	}
	
	public Node(long id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Node(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public double distance(Node node) {
		return Math.sqrt(Math.pow((node.y - this.y), 2) + Math.pow((node.x - this.x), 2));

	}

	@Override
	public Node getNode(Map map) {
		return this;
	}
	
	@Override
	public boolean equals(Object node) {
		if (node != null) {
			if (node instanceof Node) {
				return id == ((Node) node).id;
			}
		}
		return false;
	}	
	
}
