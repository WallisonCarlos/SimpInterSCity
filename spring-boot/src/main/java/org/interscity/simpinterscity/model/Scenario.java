package org.interscity.simpinterscity.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Scenario {
	
	@Id
	private String id;
	private String tripsFile;
	private String mapFile;
	private String outputFile;
	private String metroFile;
	private String busFile;
	private String digitalRailsFile;
	private Integer simulationTime;

}
