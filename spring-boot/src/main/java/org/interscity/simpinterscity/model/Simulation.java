package org.interscity.simpinterscity.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Simulation {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String title;
	private String description;
	@ManyToOne(targetEntity = Scenario.class, optional = false)
	private Scenario scenario;
	private ZonedDateTime created;
	private ZonedDateTime updated;
	private ZonedDateTime buildStart;
	private ZonedDateTime buildFinished;
	private ZonedDateTime started;
	private ZonedDateTime finished;

}
