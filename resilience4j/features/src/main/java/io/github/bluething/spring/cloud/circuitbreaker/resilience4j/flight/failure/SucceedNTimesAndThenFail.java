package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure;

import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.exception.FlightServiceException;

public class SucceedNTimesAndThenFail implements PotentialFailure {

    private final int n;
    private int successCount;

    public SucceedNTimesAndThenFail(int n) {
        this.n = n;
        successCount = 0;
    }

    @Override
    public void occur() {
        if (successCount < n) {
            successCount++;
            return;
        }
        throw new FlightServiceException("Error occurred during flight search");
    }
}
