package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure;

public class NoFailure implements PotentialFailure {
    @Override
    public void occur() {}
}
