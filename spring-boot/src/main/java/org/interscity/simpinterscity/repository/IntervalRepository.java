package org.interscity.simpinterscity.repository;

import org.interscity.simpinterscity.model.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, String>{
    
}
