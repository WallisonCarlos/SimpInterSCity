package org.interscity.simpinterscity.model.scenario;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phases")
public class Phases {

	@XmlElement(name = "phase")
	public List<Phase> phases = new ArrayList<Phase>();
	
}
