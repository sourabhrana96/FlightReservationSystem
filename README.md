# Flight Reservation System
This is a Flight Reservation System project made using java Spring Boot and postgres database.

This project contains following APIs:
A) Get Available Seats
  Request: /flightTicketSystem/getAvailableSeats?flightNumber=abc
  
B) Book ticket
  Request: /flightTicketSystem/bookSeat
  Request (Json) {“flightNumber” : “abc”, “userName”:”John”}
  
C) Schedule Flight
  Request: /flightTicketSystem/scheduleFlight
  Request (Json) {“flightNumber” : “abc”, “noOfSeats”:50}
