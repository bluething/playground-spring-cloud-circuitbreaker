![circuit breaker pattern](https://github.com/bluething/spring-cloud-circuitbreaker-samples/blob/main/images/circuit%20breaker.png?raw=true)

We want to prevent a network or service failure from cascading to other services.  
The idea of circuit breakers is to prevent calls to a remote service if we know that the call is likely to fail or time out.  
A circuit breaker keeps track of the responses by wrapping the call to the remote service.  
When a remote service returns an error or times out, the circuit breaker increments an internal counter.

A circuit breaker can be count-based or time-based.  
A count-based circuit breaker switches state from closed to open if the last N number of calls failed or were slow.  
A time-based circuit breaker switches to an open state if the responses in the last N seconds failed or were slow.  
In both circuit breakers, we can also specify the threshold for failure or slow calls.

#### Reference:

[Implementing a Circuit Breaker with Resilience4j](https://reflectoring.io/circuitbreaker-with-resilience4j/)