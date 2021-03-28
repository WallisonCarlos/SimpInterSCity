package org.interscity.simpinterscity.repository;

import org.interscity.simpinterscity.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, String> {

}
