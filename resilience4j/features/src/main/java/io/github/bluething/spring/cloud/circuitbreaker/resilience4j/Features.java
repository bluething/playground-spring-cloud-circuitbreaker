package io.github.bluething.spring.cloud.circuitbreaker.resilience4j;

import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.Flight;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.SearchRequest;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.Service;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.delay.AlwaysSlowNSeconds;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.delay.NoDelay;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure.NoFailure;
import io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.failure.SucceedNTimesAndThenFail;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

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

    void countBasedSlidingWindowFailedCalls() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                .failureRateThreshold(70.0f)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("flightSearchService");

        Service service = new Service(new SucceedNTimesAndThenFail(3), new NoDelay());
        SearchRequest request = new SearchRequest("NYC", "LAX", "12/31/2020");

        Supplier<List<Flight>> flightSupplier = () -> service.searchFlights(request);
        Supplier<List<Flight>> decoratedFlightSupplier = circuitBreaker.decorateSupplier(flightSupplier);

        for (int i=0; i<20; i++) {
            try {
                System.out.println(decoratedFlightSupplier.get());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    void countBasedSlidingWindowSlowCalls() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                .slowCallRateThreshold(70.0f)
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("flightSearchService");

        Service service = new Service(new NoFailure(), new AlwaysSlowNSeconds(2));
        SearchRequest request = new SearchRequest("NYC", "LAX", "12/31/2020");

        Supplier<List<Flight>> flightSupplier = circuitBreaker.decorateSupplier(() -> service.searchFlights(request));

        for (int i=0; i<20; i++) {
            try {
                System.out.println(flightSupplier.get());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Features features = new Features();

        features.displayDefaultValues();
        System.out.println(" ===== ");
        features.countBasedSlidingWindowFailedCalls();
        System.out.println(" ===== ");
        features.countBasedSlidingWindowSlowCalls();
    }
}
