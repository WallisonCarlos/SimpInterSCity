package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "links")
public class Links {

	@XmlAttribute(name = "capperiod")
	public String capPeriod;
	@XmlAttribute(name = "effectivecellsize")
	public float effectiveCellSize;
	@XmlAttribute(name = "effectivelanewidth")
	public float effectiveLaneWidth;
	@XmlElement(name = "link")
	public List<Link> links = new ArrayList<>();
	
	public Links() {
		
	}
	
	public Links(List<Link> links) {
		this.links  = links;
	}
	
}
