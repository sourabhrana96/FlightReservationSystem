package com.example.flightTicketSystem.service;

import com.example.flightTicketSystem.entity.models.FlightDetailsEntity;
import com.example.flightTicketSystem.repositories.FlightDetailsRepository;
import com.example.flightTicketSystem.request.FlightScheduleRequest;
import com.example.flightTicketSystem.response.BaseResponse;
import com.example.flightTicketSystem.response.GetFlightDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sourabhrana
 */

@Service
public class FlightService {

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    public GetFlightDetailResponse getFlightDetails(String flightNumber) {

        try {
            FlightDetailsEntity flightDetailsEntity = flightDetailsRepository.findByFlightNumber(flightNumber);
            if (flightDetailsEntity != null) {
                Integer availableSeats = flightDetailsEntity.getTotalSeats() - flightDetailsEntity.getBookedSeats();
                return createFlightDetailResponse(availableSeats, true, "Success");
            } else {
                return createFlightDetailResponse(null, false, "No flight exist with this flight number");
            }
        } catch (Exception e) {
            return createFlightDetailResponse(null, false, e.getMessage());
        }
    }

    public BaseResponse scheduleFlight(FlightScheduleRequest flightScheduleRequest) {
        FlightDetailsEntity flightDetailsEntity;
        try {
            flightDetailsEntity = flightDetailsRepository.findByFlightNumber(flightScheduleRequest.getFlightNumber());
            if (flightDetailsEntity != null) {
                return createBaseResponse(false, "Flight already exists");
            } else {
                flightDetailsEntity = createFlightDetailEntity(flightScheduleRequest);
                return createBaseResponse(true, "Successfully scheduled a flight");
            }
        } catch (Exception e) {
            return createBaseResponse(false, e.getMessage());
        }
    }

    private FlightDetailsEntity createFlightDetailEntity(FlightScheduleRequest request) {
        FlightDetailsEntity flightDetailsEntity = new FlightDetailsEntity();

        flightDetailsEntity.setFlightNumber(request.getFlightNumber());
        flightDetailsEntity.setTotalSeats(request.getNoOfSeats());
        flightDetailsEntity.setBookedSeats(0);
        return flightDetailsRepository.save(flightDetailsEntity);
    }

    private BaseResponse createBaseResponse(boolean success, String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(success);
        response.setMessage(message);
        return response;
    }

    private GetFlightDetailResponse createFlightDetailResponse(Integer count, boolean success, String message) {
        GetFlightDetailResponse response = new GetFlightDetailResponse();
        response.setCount(count);
        response.setSuccess(success);
        response.setMessage(message);
        return response;
    }

}
