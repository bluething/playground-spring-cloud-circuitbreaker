`CircuitBreakerConfig` encapsulates all the configurations

Config property | Default Value | Description
--- | --- | ---
failureRateThreshold | 50 | When the failure rate is equal or greater than the threshold the CircuitBreaker transitions to open and starts short-circuiting calls.
minimumNumberOfCalls | 100 | Configures the minimum number of calls which are required (per sliding window period) before the CircuitBreaker can calculate the error rate or slow call rate. For example, if minimumNumberOfCalls is 10, then at least 10 calls must be recorded, before the failure rate can be calculated. If only 9 calls have been recorded the CircuitBreaker will not transition to open even if all 9 calls have failed.
permittedNumberOfCallsInHalfOpenState | 10 | Configures the number of permitted calls when the CircuitBreaker is half open.
maxWaitDurationInHalfOpenState | 0 | Configures a maximum wait duration which controls the longest amount of time a CircuitBreaker could stay in Half Open state, before it switches to open. Value 0 means Circuit Breaker would wait infinitely in HalfOpen State until all permitted calls have been completed.
slidingWindowSize | 100 | Configures the size of the sliding window which is used to record the outcome of calls when the CircuitBreaker is closed.
slidingWindowType | COUNT_BASED | Configures the type of the sliding window which is used to record the outcome of calls when the CircuitBreaker is closed. Sliding window can either be count-based or time-based. If the sliding window is COUNT_BASED, _the last_ `slidingWindowSize` _calls_ are recorded and aggregated. If the sliding window is TIME_BASED, _the calls of the last_ `slidingWindowSize` _seconds_ recorded and aggregated.
slowCallRateThreshold | 100 | Configures a threshold in percentage. The CircuitBreaker considers a call as slow when the call duration is greater than `slowCallDurationThreshold`. When the percentage of slow calls is equal or greater the threshold, the CircuitBreaker transitions to open and starts short-circuiting calls.
slowCallDurationThreshold | 60000 [ms] | Configures the duration threshold above which calls are considered as slow and increase the rate of slow calls.
automaticTransitionFromOpenToHalfOpenEnabled | false | If set to true it means that the CircuitBreaker will automatically transition from open to half-open state and no call is needed to trigger the transition. A _thread is created_ to monitor all the instances of CircuitBreakers to transition them to HALF_OPEN once waitDurationInOpenState passes. Whereas, if set to false the transition to HALF_OPEN only happens _if a call is made_, even after waitDurationInOpenState is passed. The advantage here is no thread monitors the state of all CircuitBreakers.
recordExceptions | empty | A list of exceptions that are recorded as a failure and thus increase the failure rate. Any exception matching or inheriting from one of the list counts as a failure, unless explicitly ignored via `ignoreExceptions`. If you specify a list of exceptions, all other exceptions count as a success, unless they are explicitly ignored by `ignoreExceptions`.
ignoreExceptions | empty | A list of exceptions that are ignored and neither count as a failure nor success. Any exception matching or inheriting from one of the list will not count as a failure nor success, even if the exceptions is part of `recordExceptions`.
writableStackTraceEnabled | true | Enables writable stack traces. When set to false, Exception.getStackTrace() returns a zero length array. This may be used to reduce log spam when the circuit breaker is open as the cause of the exceptions is already known (the circuit breaker is short-circuiting calls).

#### Count-based sliding window

The count-based sliding window is implemented with a circular array of N measurements.  If the count window size is 10, the circular array has always 10 measurements.  The sliding window incrementally updates a total aggregation. The total aggregation is updated when a new call outcome is recorded. When the oldest measurement is evicted, the measurement is subtracted from the total aggregation and the bucket is reset. (Subtract-on-Evict)  
The time to retrieve a Snapshot is constant O(1), since the Snapshot is pre-aggregated and is independent of the window size.  The space requirement (memory consumption) of this implementation should be O(n).