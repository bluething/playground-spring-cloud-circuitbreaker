package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.delay;

public class NoDelay implements PotentialDelay {
    @Override
    public void occur() {}
}
