package org.interscity.simpinterscity.repository;

import org.interscity.simpinterscity.model.SimulationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationPointRepository extends JpaRepository<SimulationPoint, String> {
    
}
