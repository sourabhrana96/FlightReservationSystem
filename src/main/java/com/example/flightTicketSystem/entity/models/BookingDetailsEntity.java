package com.example.flightTicketSystem.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author sourabhrana
 */

@Entity
@Table(name = "booking_details")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDetailsEntity extends GenericAbstractEntity<Integer> {
    private static final long serialVersionUID = 7060860002370007234L;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference
    private FlightDetailsEntity flightDetailsEntity;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "flight_number")
    private String flightNumber;
}
