package org.interscity.simpinterscity.model.scenario;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "network")
public class Map extends Element{
	
	@XmlElement(name = "nodes")
	public Nodes nodes = new Nodes();
	@XmlElement(name = "links")
	public Links links = new Links();

	public Node getNode(Long id) {
		for (Node node : nodes.nodes) {
			if (node.id == id.intValue()) {
				return node;	
			}
		}
		return null;
	}
	
	public Link getLink(Long id) {
		for (Link link : links.links) {
			if (link.id == id.intValue()) {
				return link;	
			}
		}
		return null;
	}

	@Override
	public Node getNode(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

}
