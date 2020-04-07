package sudokuapp.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sudokuapp.logic.SudokuGenerator;

public class SudokuUi extends Application {
    private Scene sudokuScene;
    
    private HashMap<Integer, Button> sudokuMap;
    private GridPane sudokuGrid;
    
    @Override
    public void start(Stage stage) {
        sudokuGrid = new GridPane();
        this.createGrid();
        
        sudokuScene = new Scene(sudokuGrid);
        
        stage.setTitle("Sudoku");
        stage.setScene(sudokuScene);
        stage.show();
    }
    
    private void createGrid() {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] sudoku = generator.generateSudoku();
        
        sudokuMap = new HashMap<>();
        
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Button button = new Button("" + sudoku[y][x]);
                
                sudokuMap.put(y * 9 + x, button);
                sudokuGrid.add(button, x, y);
            }
        }
    }
}
