package com.example.flightTicketSystem.repositories;

import com.example.flightTicketSystem.entity.models.BookingDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sourabhrana
 */

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetailsEntity, Integer> {

}
