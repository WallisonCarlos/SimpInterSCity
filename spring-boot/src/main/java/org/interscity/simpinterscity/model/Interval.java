package org.interscity.simpinterscity.model;

import static java.time.ZonedDateTime.now;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Interval {

    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer start;
    private Integer end;
    private ZonedDateTime created;
    private ZonedDateTime updated;

    public Interval() {
        this.created = now();
    }

    public Interval(Integer start, Integer end) {
        this();
        this.start = start;
        this.end = end;
    }
    
}
