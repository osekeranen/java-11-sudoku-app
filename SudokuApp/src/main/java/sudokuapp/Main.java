package sudokuapp;

import javafx.application.Application;
import sudokuapp.logic.*;
import sudokuapp.ui.*;

public class Main {
    public static void main(String[] args) {
//        Application.launch(SudokuUi.class);
        SudokuGenerator generator = new SudokuGenerator();
        
        int[][] sudoku = generator.generateSudoku();
        
        generator.printSudoku(sudoku);
        
        System.out.println("");
        
        generator.printSudoku(generator.generateEmptySudoku(sudoku, 17));
    }
}
