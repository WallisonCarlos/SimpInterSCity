package org.interscity.simpinterscity.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddIntervalDTO {
    
    private String key;
    private Integer fixed;
    private Integer numberOfIntervals;
    private LocalTime hour;
    private List<IntervalDTO> intervalDTOs;

}
