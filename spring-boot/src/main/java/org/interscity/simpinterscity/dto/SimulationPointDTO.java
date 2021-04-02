package org.interscity.simpinterscity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationPointDTO {

    private String title;
    private String description;
    private String author;
    private Integer numberOfClusters;
    private Float percentageOfTrips;
    private String base;
    private String other;
    
}
