package Game;

import Utilies.Easymeth;

public class Stopwatch {

    private long startTime;   // Timestamp when it starts
    private long timeLimit;   // Time limit in milliseconds
    private boolean running;  // Indicates if the stopwatch is currently active

    public Stopwatch() {
        this.running = false;
    }

    /**
     * Starts the stopwatch with a given time limit.
     *
     * @param secondsLimit The time limit in seconds.
     */
    public void start(int secondsLimit) {
        this.startTime = System.currentTimeMillis();
        this.timeLimit = secondsLimit * 1000L; // Convert seconds to milliseconds
        this.running = true;
    }

    /**
     * Checks if the time limit has been reached.
     *
     * @return true if the time limit has expired, false otherwise or if the stopwatch is not running.
     */
    public boolean hasExpired() {
        if (!running) return false;
        return System.currentTimeMillis() - startTime >= timeLimit;
    }

    /**
     * Stops the stopwatch and returns the elapsed time.
     *
     * @return The elapsed time in milliseconds since the stopwatch started, or 0 if it was not running.
     */
    public long stop() {
        if (!running) return 0;
        long elapsedTime = System.currentTimeMillis() - startTime;
        this.running = false;
        return elapsedTime;
    }

    /**
     * Returns the remaining time until the limit is reached.
     *
     * @return The remaining time in seconds, or 0 if the stopwatch is not running or the time has already expired.
     */
    public int getRemainingTime() {
        if (!running) return 0;
        long remaining = timeLimit - (System.currentTimeMillis() - startTime);
        return (int) Math.max(remaining / 1000, 0); // Ensure it doesn't return negative seconds
    }

    /**
     * Checks if the stopwatch is currently active.
     *
     * @return true if the stopwatch is running, false otherwise.
     */
    public boolean isRunning() {
        return running;
    }


}
