package org.interscity.simpinterscity.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SimulationPoint {

    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String title;
    private String description;
    private String author;
    private Simulation base;
    private Simulation other;
    @ManyToMany
    @JoinTable(
        name = "intervals_simulation_point", 
        joinColumns = @JoinColumn(name = "simulation_point_id"), 
        inverseJoinColumns = @JoinColumn(name = "interval_id"))
    private List<Interval> intervals;
    private Integer numberOfClusters;
    private Float percentageOfTrips;
    private ZonedDateTime created;
    private ZonedDateTime updated;

    public SimulationPoint() {
    
    }

    public SimulationPoint(String title, String description, String author, Integer numberOfClusters, Float percentageOfTrips) {
        this(title, description, author, null, null, numberOfClusters, percentageOfTrips);
    }

    public SimulationPoint(String title, String description, String author, Simulation base, Simulation other, Integer numberOfClusters, Float percentageOfTrips) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.base = base;
        this.other = other;
        this.numberOfClusters = numberOfClusters;
        this.percentageOfTrips = percentageOfTrips;
    }
    
}
