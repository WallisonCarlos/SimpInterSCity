package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "metro")
public class Metro extends Element{
	
	@XmlElement(name = "stations")
	public Stations stations;
	@XmlElement(name = "links")
	public List<LinkMetro> links = new ArrayList<>();
	
	public Metro() {
		setTypeElement(ElementTypeEnum.METRO);
	}
	
	@Override
	public Node getNode(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
