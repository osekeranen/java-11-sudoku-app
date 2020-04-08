package sudokuapp.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sudokuapp.logic.SudokuGenerator;

public class SudokuUi extends Application {
    private Scene sudokuScene;
    
    private GridPane sudokuGrid;
    
    private HashMap<Integer, GridPane> gridMap;
    private HashMap<Integer, Button> cellMap;
    
    @Override
    public void start(Stage stage) {
        sudokuGrid = new GridPane();
        sudokuGrid.setHgap(5);
        sudokuGrid.setVgap(5);
        sudokuGrid.setPadding(new Insets(10, 10, 10, 10));
        
        this.createGrid();
        
        sudokuScene = new Scene(sudokuGrid);
        
        stage.setTitle("Sudoku");
        stage.setScene(sudokuScene);
        stage.show();
    }
    
    private void createGrid() {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] sudoku = generator.generateSudoku();
        
        gridMap = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            GridPane gridpane = new GridPane();
            
            gridMap.put(i, gridpane);
            sudokuGrid.add(gridpane, i % 3, i / 3);
        }
        
        cellMap = new HashMap<>();
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button button = new Button("" + sudoku[y][x]);
                int grid = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
                
                cellMap.put(y * 9 + x, button);
                gridMap.get(grid).add(button, x, y);
            }
        }
    }
}
