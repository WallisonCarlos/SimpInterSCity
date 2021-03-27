package org.interscity.simpinterscity.model.scenario;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "stations")
public class Stations {

	@XmlElement(name = "station")
	public List<Station> stations;
}
