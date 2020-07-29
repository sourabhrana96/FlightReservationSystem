package com.example.flightTicketSystem.repositories;

import com.example.flightTicketSystem.entity.models.FlightDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author sourabhrana
 */
@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetailsEntity, Integer> {
    FlightDetailsEntity findByFlightNumber(String flightNumber);
}
