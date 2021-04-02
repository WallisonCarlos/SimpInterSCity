package org.interscity.simpinterscity.service;

import static java.time.ZonedDateTime.now;

import java.util.List;
import java.util.Optional;

import org.interscity.simpinterscity.model.Interval;
import org.interscity.simpinterscity.repository.IntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class IntervalService {
    
    @Autowired
	private IntervalRepository intervalRepository;
	
	public Interval findBy(String id) {
		Optional<Interval> entity  = intervalRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}
	
	public List<Interval> findAll() {
		return intervalRepository.findAll();
	}
	
	public Interval create(Interval entity) {
		entity.setCreated(now());
		return intervalRepository.save(entity);
	}
	
	public Interval update(Interval entity) {
		entity.setUpdated(now());
		return intervalRepository.save(entity);
	}
	
	public ResponseEntity<?> delete(String id) {
		intervalRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
