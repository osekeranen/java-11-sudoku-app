package sudokuapp.logic;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Button;

/**
 * A class for checking sudokus
 */
public class SudokuChecker {

    /**
     * Constructor for SudokuChecker
     */
    public SudokuChecker() {
    }
    
    /**
     * Method for checking if the sudoku is correct or not
     * 
     * @param sudoku sudoku that will be checked
     * @param completeSudoku sudoku that is correct
     * @return an ArrayList of indexes of sudoku cells that are not correct
     */
    public ArrayList<Integer> checkSudoku(int[][] sudoku, int[][] completeSudoku) {
        ArrayList<Integer> incorrectCells = new ArrayList<>();
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (sudoku[y][x] != completeSudoku[y][x]) {
                    incorrectCells.add(y * 9 + x);
                }
            }
        }
        
        return incorrectCells;
    }
}
