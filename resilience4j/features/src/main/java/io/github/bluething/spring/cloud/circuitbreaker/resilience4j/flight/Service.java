package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight;

import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.delay.PotentialDelay;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure.PotentialFailure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Service {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
    private final PotentialFailure potentialFailure;
    private final PotentialDelay potentialDelay;

    public Service(PotentialFailure potentialFailure, PotentialDelay potentialDelay) {
        this.potentialFailure = potentialFailure;
        this.potentialDelay = potentialDelay;
    }

    public List<Flight> searchFlights(SearchRequest request) {
        System.out.println("Searching for flights; current time = " + LocalDateTime.now().format(DATE_TIME_FORMATTER));

        potentialFailure.occur();
        potentialDelay.occur();

        List<Flight> flights = Arrays.asList(
                new Flight("XY 765", request.getFlightDate(), request.getFrom(), request.getTo()),
                new Flight("XY 781", request.getFlightDate(), request.getFrom(), request.getTo()),
                new Flight("XY 732", request.getFlightDate(), request.getFrom(), request.getTo()),
                new Flight("XY 746", request.getFlightDate(), request.getFrom(), request.getTo())
        );

        System.out.println("Flight search successful");
        return flights;

    }
}
