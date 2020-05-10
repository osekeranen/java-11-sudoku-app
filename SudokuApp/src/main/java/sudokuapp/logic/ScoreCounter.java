package sudokuapp.logic;

/**
 * This is a class for counting the score.
 */
public class ScoreCounter {

    private long startTime;
    private long endTime;

    private int failedChecks;

    /**
     * Constructs the counter and immediately starts the timer.
     */
    public ScoreCounter() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Adds a failed check to the counter.
     */
    public void addFailedCheck() {
        failedChecks++;
    }

    /**
     * Returns the score.
     *
     * @return the score
     */
    public int getScore() {
        endTime = System.currentTimeMillis();
        int timeElapsed = (int) (endTime - startTime) / 1000;

        int score = 3600 - timeElapsed - (failedChecks * 100);

        if (score < 0) {
            return 0;
        } else {
            return score;
        }
    }
}
