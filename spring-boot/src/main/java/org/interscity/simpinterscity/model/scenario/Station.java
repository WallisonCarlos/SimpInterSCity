package org.interscity.simpinterscity.model.scenario;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "station")
public class Station extends Element{
	
	@XmlAttribute(name = "name")
	public String name;
	@XmlAttribute(name = "idNode")
	public int idNode;

	@Override
	public Node getNode(Map map) {
		return map.getNode((long)idNode);
	}

}
