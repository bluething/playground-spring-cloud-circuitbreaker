package io.github.bluething.spring.cloud.circuitbreaker.resilience4j.flight.delay;

public class AlwaysSlowNSeconds implements PotentialDelay {
    private final int delayInSeconds;

    public AlwaysSlowNSeconds(int delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }


    @Override
    public void occur() {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
