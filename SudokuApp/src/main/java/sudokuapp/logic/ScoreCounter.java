package sudokuapp.logic;

/**
 * A class for scoring games
 */
public class ScoreCounter {
    private long startTime;
    private long endTime;
    
    private int failedChecks;

    /**
     * Constructor for Score-class
     */
    public ScoreCounter() {
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Adds a failed check to the counter
     */
    public void addFailedCheck() {
        failedChecks++;
    }
    
    /**
     * Method for getting score from played game
     * 
     * @return score
     */
    public int getScore() {
        endTime = System.currentTimeMillis();
        int timeElapsed = (int) (endTime - startTime) / 1000;
        
        if (timeElapsed > 3600) {
            return 0;
        }
        
        int score = 3600 - timeElapsed - (failedChecks * 100);
        
        if (score < 0) {
            return 0;
        } else {
            return score;
        }
    }
}
