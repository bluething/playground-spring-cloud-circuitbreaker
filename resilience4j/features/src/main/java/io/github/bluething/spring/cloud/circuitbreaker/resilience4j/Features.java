package io.github.bluething.spring.cloud.circuitbreaker.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

public class Features {
    void displayDefaultValues() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.ofDefaults();
        System.out.println("failure rate threshold " + circuitBreakerConfig.getFailureRateThreshold());
        System.out.println("minimum number of calls " + circuitBreakerConfig.getMinimumNumberOfCalls());
        System.out.println("permitted number of calls in half open state " + circuitBreakerConfig.getPermittedNumberOfCallsInHalfOpenState());
        System.out.println("maximum wait duration on half open state " + circuitBreakerConfig.getMaxWaitDurationInHalfOpenState());
        System.out.println("sliding window size " + circuitBreakerConfig.getSlidingWindowSize());
        System.out.println("sliding window type " + circuitBreakerConfig.getSlidingWindowType());
        System.out.println("slow call rate threshold " + circuitBreakerConfig.getSlowCallRateThreshold());
        System.out.println("slow call duration threshold " + circuitBreakerConfig.getSlowCallDurationThreshold());
        System.out.println("is automatic transition from open to half open enabled " + circuitBreakerConfig.isAutomaticTransitionFromOpenToHalfOpenEnabled());
        System.out.println("is writeable stack trace enabled " + circuitBreakerConfig.isWritableStackTraceEnabled());
    }
    public static void main(String[] args) {
        Features features = new Features();

        features.displayDefaultValues();
    }
}
