package org.interscity.simpinterscity.model.enumeration;

import java.util.List;

import org.interscity.simpinterscity.dto.AddIntervalDTO;
import org.interscity.simpinterscity.model.Interval;

public interface GeneratorIntervals {
    
    public List<Interval> generator(Integer simulationTime, AddIntervalDTO addIntervalDTO);

}
