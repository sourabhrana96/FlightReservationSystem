package com.example.flightTicketSystem.service;

import com.example.flightTicketSystem.entity.models.BookingDetailsEntity;
import com.example.flightTicketSystem.entity.models.FlightDetailsEntity;
import com.example.flightTicketSystem.repositories.BookingDetailsRepository;
import com.example.flightTicketSystem.repositories.FlightDetailsRepository;
import com.example.flightTicketSystem.request.BookTicketRequest;
import com.example.flightTicketSystem.response.GetTicketCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author sourabhrana
 */
@Service
public class TicketBookingService {

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;


    public GetTicketCreationResponse bookTicket(BookTicketRequest bookTicketRequest) {
        GetTicketCreationResponse response = null;
        try {
            FlightDetailsEntity flightDetailsEntity = flightDetailsRepository.findByFlightNumber(bookTicketRequest.getFlightNumber());
            if (flightDetailsEntity != null) {
                Integer availableSeats = flightDetailsEntity.getTotalSeats() - flightDetailsEntity.getBookedSeats();
                if (availableSeats > 0) {
                    BookingDetailsEntity bookingDetailsEntity = createBookingEntity(flightDetailsEntity, bookTicketRequest);
                    updateFlightEntity(flightDetailsEntity);
                    response = new GetTicketCreationResponse();
                    response.setSuccess(true);
                    response.setMessage("Ticket is Booked Successfully");
                    response.setSeatNumber(bookingDetailsEntity.getSeatNumber());
                    return response;
                } else {
                    return createTicketBookingResponse(false, "Tickets full");
                }
            } else
                return createTicketBookingResponse(false, "Flight does not exist.");
        } catch (Exception e) {
            return createTicketBookingResponse(false, e.getMessage());
        }
    }

    private void updateFlightEntity(FlightDetailsEntity flightDetailsEntity) {
        flightDetailsEntity.setBookedSeats(flightDetailsEntity.getBookedSeats() + 1);
        flightDetailsRepository.save(flightDetailsEntity);
    }

    private BookingDetailsEntity createBookingEntity(FlightDetailsEntity flightDetailsEntity, BookTicketRequest request) {

        BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();
        bookingDetailsEntity.setFlightDetailsEntity(flightDetailsEntity);
        bookingDetailsEntity.setUserName(request.getUserName());
        bookingDetailsEntity.setSeatNumber(flightDetailsEntity.getBookedSeats() + 1);
        bookingDetailsEntity.setFlightNumber(flightDetailsEntity.getFlightNumber());
        return bookingDetailsRepository.save(bookingDetailsEntity);
    }


    private GetTicketCreationResponse createTicketBookingResponse(boolean success, String message) {
        GetTicketCreationResponse response = new GetTicketCreationResponse();
        response.setSuccess(success);
        response.setMessage(message);
        return response;
    }
}
