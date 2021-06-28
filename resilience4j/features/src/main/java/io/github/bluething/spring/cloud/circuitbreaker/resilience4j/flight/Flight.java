package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight;

public class Flight {
    private final String flightNumber;
    private final String flightDate;
    private final String from;
    private final String to;

    public Flight(String flightNumber, String flightDate, String from, String to) {
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.from = from;
        this.to = to;
    }

    String getFlightNumber() {
        return flightNumber;
    }

    String getFlightDate() {
        return flightDate;
    }

    String getFrom() {
        return from;
    }

    String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
