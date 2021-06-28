package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure;

import java.util.Random;

public class FailHalfTheTime implements PotentialFailure {
    private final int times;
    private int failedCount;
    Random random = new Random();

    public FailHalfTheTime(int times) {
        this.times = times;
        failedCount = 0;
    }

    @Override
    public void occur() {
        if (failedCount++ < times && random.nextInt() % 2 == 0) {
            throw new RuntimeException("Operation failed");
        }
    }
}
