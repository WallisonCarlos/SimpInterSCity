package org.interscity.simpinterscity.repository;

import org.interscity.simpinterscity.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, String> {

}
