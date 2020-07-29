package com.example.flightTicketSystem.controller;

import com.example.flightTicketSystem.request.BookTicketRequest;
import com.example.flightTicketSystem.request.FlightScheduleRequest;
import com.example.flightTicketSystem.response.*;
import com.example.flightTicketSystem.service.FlightService;
import com.example.flightTicketSystem.service.TicketBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author sourabhrana
 */

@RestController
@RequestMapping(value = "/flightTicketSystem", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightTicketBookingController {

    private static final Logger log = LoggerFactory.getLogger(FlightTicketBookingController.class);

    @Autowired
    private FlightService flightService;

    @Autowired
    private TicketBookingService ticketBookingService;

    @RequestMapping(value = "/getAvailableSeats", method = RequestMethod.GET)
    public BaseApiResponse<GetFlightDetailResponse> getAvailableSeats(@RequestParam String flightNumber) {
        GetFlightDetailResponse response = flightService.getFlightDetails(flightNumber);
        if (response.isSuccess()) {
            return new BaseSuccessResponse<>(response);
        } else {
            return new BaseFailureResponse<>(response);
        }
    }

    @RequestMapping(value = "/bookSeat", method = RequestMethod.POST)
    public BaseApiResponse<GetTicketCreationResponse> bookSeat(@RequestBody BookTicketRequest bookTicketRequest) {
        GetTicketCreationResponse response = ticketBookingService.bookTicket(bookTicketRequest);
        if (response.isSuccess())
            return new BaseSuccessResponse<>(response);
        else
            return new BaseFailureResponse<>(response);
    }

    @RequestMapping(value = "/scheduleFlight", method = RequestMethod.POST)
    public BaseApiResponse<BaseResponse> bookTicket(@RequestBody FlightScheduleRequest flightScheduleRequest) {
        BaseResponse response = flightService.scheduleFlight(flightScheduleRequest);
        if (response.isSuccess()) {
            return new BaseSuccessResponse<>(response);
        } else {
            return new BaseFailureResponse<>(response);
        }
    }
}
