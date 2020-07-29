package com.example.flightTicketSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration()
@EnableAsync
@EnableCaching
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class FlightTicketSystemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FlightTicketSystemApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(FlightTicketSystemApplication.class, args);
        System.out.println("-----------------------------------\n");
        System.out.println("Flight Ticket Booking system mein apka swagat hai ...\n");
    }

}
