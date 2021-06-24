package io.github.bluething.spring.cloud.gscloudcircuitbreaker.reading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final WebClient webClient;
    private final ReactiveCircuitBreaker reactiveCircuitBreaker;

    public BookService(ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8090").build();
        this.reactiveCircuitBreaker = reactiveCircuitBreakerFactory.create("recommended");
    }

    public Mono<String> readingList() {
        return reactiveCircuitBreaker.run(webClient.get().uri("/recommended").retrieve().bodyToMono(String.class), throwable -> {
            LOGGER.warn("Error making request to book service", throwable);
            return Mono.just("Cloud Native Java (O'Reilly)");
        });
    }
}
