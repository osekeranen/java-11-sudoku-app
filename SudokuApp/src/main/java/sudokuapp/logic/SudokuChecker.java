package sudokuapp.logic;

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
     * Method for checking whether sudoku is filled correctly or not
     * 
     * @param cellMap a HashMap containing sudoku cell buttons
     * @param completeSudoku  a complete sudoku to check filled sudoku with
     * @return boolean whether the sudoku is correct or not
     */
    public boolean checkSudoku(HashMap<Integer, Button> cellMap, int[][] completeSudoku) {
        boolean ollKorrect = true;
        
        for (int i = 0; i < 81; i++) {
            if (cellMap.get(i).getText().equals(" ")) {
                ollKorrect = false;
                continue;
            }
            
            int x = i % 9;
            int y = i / 9;

            if (Integer.valueOf(cellMap.get(i).getText()) != completeSudoku[y][x]) {
                cellMap.get(i).setStyle("-fx-text-fill: red");
                ollKorrect = false;
            }
        }
        
        return ollKorrect;
    }
}
