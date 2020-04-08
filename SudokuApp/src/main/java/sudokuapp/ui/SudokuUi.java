package sudokuapp.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
        sudokuGrid.setHgap(10);
        sudokuGrid.setVgap(10);
        sudokuGrid.setPadding(new Insets(10, 10, 10, 10));
        
        this.createGrid();
        
        sudokuScene = new Scene(sudokuGrid);
        
        stage.setTitle("Sudoku");
        stage.setScene(sudokuScene);
        stage.show();
    }
    
    private void createGrid() {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] completeSudoku = generator.generateSudoku();
        int[][] emptySudoku = generator.generateEmptySudoku(completeSudoku, 30);
        
        gridMap = new HashMap<>();
        
        for (int i = 0; i < 9; i++) {
            GridPane gridpane = new GridPane();
            
            gridMap.put(i, gridpane);
            sudokuGrid.add(gridpane, i % 3, i / 3);
        }
        
        cellMap = new HashMap<>();
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (emptySudoku[y][x] == 0) {
                    Button button = new Button(" ");
                    button.setFont(Font.font("Monospaced", 16));
                    
                    button.setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.DIGIT1) {
                            button.setText("1");
                        } else if (e.getCode() == KeyCode.DIGIT2) {
                            button.setText("2");
                        } else if (e.getCode() == KeyCode.DIGIT3) {
                            button.setText("3");
                        } else if (e.getCode() == KeyCode.DIGIT4) {
                            button.setText("4");
                        } else if (e.getCode() == KeyCode.DIGIT5) {
                            button.setText("5");
                        } else if (e.getCode() == KeyCode.DIGIT6) {
                            button.setText("6");
                        } else if (e.getCode() == KeyCode.DIGIT7) {
                            button.setText("7");
                        } else if (e.getCode() == KeyCode.DIGIT8) {
                            button.setText("8");
                        } else if (e.getCode() == KeyCode.DIGIT9) {
                            button.setText("9");
                        } else if (e.getCode() == KeyCode.BACK_SPACE) {
                            button.setText(" ");
                        }
                    });
                    
                    int grid = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
                    
                    cellMap.put(y * 9 + x, button);
                    gridMap.get(grid).add(button, x, y);
                } else {
                    Button button = new Button("" + emptySudoku[y][x]);
                    button.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
                    
                    int grid = ((y * 9 + x) / 3) % 3 + ((y * 9 + x) / 3) / 3 - ((y * 9 + x) / 9) % 3;
                    
                    cellMap.put(y * 9 + x, button);
                    gridMap.get(grid).add(button, x, y);
                }
            }
        }
    }
}
