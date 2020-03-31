package sudokuapp;

import javafx.application.Application;
import sudokuapp.logic.*;
import sudokuapp.ui.*;

public class Main {
    public static void main(String[] args) {
//        Application.launch(SudokuUi.class);
        SudokuCreator creator = new SudokuCreator();
        creator.printSudoku(creator.createSudoku());
    }
}
