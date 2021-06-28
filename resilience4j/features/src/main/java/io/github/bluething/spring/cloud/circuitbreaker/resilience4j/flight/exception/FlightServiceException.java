package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.exception;

public class FlightServiceException extends RuntimeException {
    public FlightServiceException(String message) {
        super(message);
    }
}
