package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure;

import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.exception.FlightServiceException;

public class SucceedXTimesFailYTimesAndThenSucceed implements PotentialFailure {
    private final int successHowMany;
    private final int failHowMany;
    private int successCount;
    private int failCount;

    public SucceedXTimesFailYTimesAndThenSucceed(int successHowMany, int failHowMany) {
        this.successHowMany = successHowMany;
        this.failHowMany = failHowMany;
        successCount = 0;
        failCount = 0;
    }

    @Override
    public void occur() {
        if (successCount < successHowMany) {
            successCount++;
            return;
        }
        if (failCount < failHowMany) {
            failCount++;
            throw new FlightServiceException("Flight search failed");
        }
        return;
    }
}
