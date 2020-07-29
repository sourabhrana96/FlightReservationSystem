package com.example.flightTicketSystem.entity.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * @author sourabhrana
 */

@Entity
@Table(name = "flight_details")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDetailsEntity extends GenericAbstractEntity<Integer> {
    private static final long serialVersionUID = 7060860002370007234L;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name = "booked_seats")
    private Integer bookedSeats;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightDetailsEntity", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(FetchMode.SUBSELECT)
    private List<BookingDetailsEntity> bookingDetailsEntityList;
}
