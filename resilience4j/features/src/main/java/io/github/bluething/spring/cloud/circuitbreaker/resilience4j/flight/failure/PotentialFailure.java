package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure;

public interface PotentialFailure {
    void occur();
}
