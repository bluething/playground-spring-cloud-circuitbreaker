package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight;

public class SearchRequest {
    private final String from;
    private final String to;
    private final String flightDate;

    public SearchRequest(String from, String to, String flightDate) {
        this.from = from;
        this.to = to;
        this.flightDate = flightDate;
    }

    String getFrom() {
        return from;
    }

    String getTo() {
        return to;
    }

    String getFlightDate() {
        return flightDate;
    }
}
