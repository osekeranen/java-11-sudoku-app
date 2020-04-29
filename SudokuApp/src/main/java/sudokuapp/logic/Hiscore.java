package sudokuapp.logic;

/**
 * An object for hiscores
 */
public class Hiscore implements Comparable<Hiscore> {
    private int score;
    private String name;
    private Difficulty difficulty;

    /**
     * Constructor for an hiscore
     * 
     * @param difficulty difficulty on which the game was completed
     * @param score amount of points
     * @param name name of player
     */
    public Hiscore(Difficulty difficulty, int score, String name) {
        this.difficulty = difficulty;
        this.score = score;
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Hiscore t) {
        return t.score - this.score;
    }
}
