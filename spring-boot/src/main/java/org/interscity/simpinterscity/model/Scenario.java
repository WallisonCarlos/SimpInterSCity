package org.interscity.simpinterscity.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Scenario {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String description;
	private String tripsFile;
	private String mapFile;
	private String outputFile;
	private String metroFile;
	private String busFile;
	private String digitalRailsFile;
	private Integer simulationTime;
	private ZonedDateTime created;
	private ZonedDateTime updated;

}
