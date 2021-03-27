package org.interscity.simpinterscity.model.scenario;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "link")
public class Link extends Element implements Comparable<Link>{

	@XmlAttribute(name = "id")
	public long id;
	@XmlAttribute(name = "from")
	public long from;
	@XmlAttribute(name = "to")
	public long to;
	@XmlAttribute(name = "length")
	public double length;
	@XmlAttribute(name = "freespeed")
	public double freespeed;
	@XmlAttribute(name = "capacity")
	public double capacity;
	@XmlAttribute(name = "permlanes")
	public float permlanes;
	@XmlAttribute(name = "oneway")
	public int oneway;
	@XmlAttribute(name = "modes")
	public String modes;
	@XmlAttribute(name = "originid")
	public long originid;
	@XmlAttribute(name = "type")
	public String type;
	@XmlTransient
	public double weight = length;
	@XmlTransient
	public Node nodeFrom;
	@XmlTransient
	public Node nodeTo;
	
	
	
	public Link(long id, long from, long to, double length, double freespeed, double capacity, float permlanes, int oneway,
			String modes, long originid, String type) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.length = length;
		this.freespeed = freespeed;
		this.capacity = capacity;
		this.permlanes = permlanes;
		this.oneway = oneway;
		this.modes = modes;
		this.originid = originid;
		this.type = type;
	}

	public Link() {
	
	}

	@Override
	public Node getNode(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(Object link) {
		if (link != null) {
			if (link instanceof Link) { 
				return id == ((Link) link).id;
			}
		}
		return false;
	}
	
	public Node[] getNodes(Collection<Node> nodes) {
		Node[] ns = new Node[2];
		for (Node node : nodes) {
			if (node.id == from) {
				ns[0] = node;
				continue;
			}
			if (node.id == to) {
				ns[1] = node;
				continue;
			}
		}
		return ns;
	}

	@Override
	public int compareTo(Link o) {
		return (int) (id - o.id);
	}
}
