package org.interscity.simpinterscity.model.enumeration;

import java.util.ArrayList;
import java.util.List;

import org.interscity.simpinterscity.dto.AddIntervalDTO;
import org.interscity.simpinterscity.dto.IntervalDTO;
import org.interscity.simpinterscity.model.Interval;

public enum GeneratorIntervalsEnum implements GeneratorIntervals{

    

    FIXED() {
        @Override
        public List<Interval> generator(Integer simulationTime, AddIntervalDTO addIntervalDTO) {
            if (addIntervalDTO.getFixed() != null && addIntervalDTO.getFixed() > 0) {
                Integer i = 0;
                List<Interval> intervals = new ArrayList<>();
                while (i <= simulationTime) {
                    Integer end = i + addIntervalDTO.getFixed();
                    if (end > simulationTime) {
                        end = simulationTime;
                    } 
                    intervals.add(new Interval(i, end));
                    i += addIntervalDTO.getFixed();
                }
                return intervals;
            } else {
                return NUMBER_OF_INTERVALS.generator(simulationTime, addIntervalDTO);
            }
        }

    },
    NUMBER_OF_INTERVALS() {

        public List<Interval> generator(Integer simulationTime, AddIntervalDTO addIntervalDTO) {
            if (addIntervalDTO.getNumberOfIntervals() != null && addIntervalDTO.getNumberOfIntervals() > 0) {
                Integer i = 0;
                Integer span = simulationTime / addIntervalDTO.getNumberOfIntervals();
                List<Interval> intervals = new ArrayList<>();
                while (i <= simulationTime) {
                    Integer end = i + span;
                    if (end > simulationTime) {
                        end = simulationTime;
                    } 
                    intervals.add(new Interval(i, end));
                    i += span;
                }
                return intervals;
            } else {
                return HOUR.generator(simulationTime, addIntervalDTO);
            }
        }

    },
    HOUR() {

        @Override
        public List<Interval> generator(Integer simulationTime, AddIntervalDTO addIntervalDTO) {
            if (addIntervalDTO.getHour() != null) {
                Integer i = 0;
                Integer span = addIntervalDTO.getHour().getHour() * HOUR_IN_SECONDS + addIntervalDTO.getHour().getMinute() * MINUTES_IN_SECONDS;
                List<Interval> intervals = new ArrayList<>();
                while (i <= simulationTime) {
                    Integer end = i + span;
                    if (end > simulationTime) {
                        end = simulationTime;
                    } 
                    intervals.add(new Interval(i, end));
                    i += span;
                }
                return intervals;
            } else {
                return LIST.generator(simulationTime, addIntervalDTO);
            }
        }

    },
    LIST() {

        @Override
        public List<Interval> generator(Integer simulationTime, AddIntervalDTO addIntervalDTO) {
            if (addIntervalDTO != null) {
                List<Interval> intervals = new ArrayList<>();
                for (IntervalDTO interval : addIntervalDTO.getIntervalDTOs()) {
                    intervals.add(new Interval(interval.getStart(), interval.getEnd()));
                }
                return intervals;
            } else {
                throw new NullPointerException("Inform a way to generate the scenarios");
            }
        }

    };

    private static final Integer HOUR_IN_SECONDS = 3600;
    private static final Integer MINUTES_IN_SECONDS = 60;

    private GeneratorIntervalsEnum() {

    }
    
    public static GeneratorIntervalsEnum instance() {
        return FIXED;
    }

}
