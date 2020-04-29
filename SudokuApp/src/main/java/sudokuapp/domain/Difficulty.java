package sudokuapp.domain;

/**
 * An enum for managing difficulty settings
 */
public enum Difficulty {
    BEGINNER(40),
    INTERMEDIATE(30),
    ADVANCED(20);
    
    private int clues;

    /**
     * Constructor for difficulty
     * 
     * @param clues number of clues given at difficulty setting
     */
    private Difficulty(int clues) {
        this.clues = clues;
    }

    public int getClues() {
        return clues;
    }

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
