package sudokuapp.logic;

import java.util.ArrayList;

/**
 * This is a class for checking sudokus.
 */
public class SudokuChecker {

    /**
     * Constructs the object that checks sudokus.
     */
    public SudokuChecker() {
    }

    /**
     * Checks if the sudoku is correct.
     *
     * @param sudoku the sudoku to be checked
     * @param completeSudoku the correct sudoku
     * @return a list of indexes of sudoku cells that are incorrect
     */
    public ArrayList<Integer> checkSudoku(int[][] sudoku, int[][] completeSudoku) {
        ArrayList<Integer> mistakes = new ArrayList<>();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (sudoku[y][x] != completeSudoku[y][x]) {
                    mistakes.add(y * 9 + x);
                }
            }
        }

        return mistakes;
    }
}
